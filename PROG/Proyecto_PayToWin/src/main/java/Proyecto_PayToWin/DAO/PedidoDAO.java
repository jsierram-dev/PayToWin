/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto_PayToWin.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import Proyecto_PayToWin.DTO.Direccion;
import Proyecto_PayToWin.DTO.TiposPago;
import Proyecto_PayToWin.DTO.Pedido;
import Proyecto_PayToWin.DTO.Producto;
import Proyecto_PayToWin.DTO.Usuario;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author jrmd
 */
public class PedidoDAO extends TablaDAO<Pedido> {

    public PedidoDAO() {
        this.tabla = "PROYECTO_pedido";
    }

    @Override
    public int actualizar(Pedido p) throws SQLException {
        //No necesario para el proyecto
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int anyadir(Pedido p) throws SQLException {
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, p.getCodigo());
        prepared.setTimestamp(2, Timestamp.valueOf(p.getFecha()));
        prepared.setString(3, String.valueOf(p.getMetpago()));
        prepared.setString(4, p.getFacturado());
        prepared.setInt(5, p.getDireccion().getCodigo());
        prepared.setString(6, p.getCliente().getNombreUsuario());
        
        int resultado = prepared.executeUpdate();
        anyadirLineas(p);
        return resultado;
        
    }

    @Override
    public Pedido eliminar(Pedido p) throws SQLException {
        if (p == null) {
            return null;
        } else {
            return eliminar(p.getCodigo()) != null ? p : null;
        }
    }

    @Override
    public boolean existe(Pedido p) throws SQLException {
        return existe(p.getCodigo());
    }

    @Override
    public ArrayList<Pedido> getAll() throws SQLException {
        ArrayList<Pedido> lista = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY codigo";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");
            LocalDateTime fecha = resultSet.getTimestamp("fecha").toLocalDateTime();
            TiposPago metodoPago = TiposPago.valueOf(resultSet.getString("metpago"));
            
            //Comprovar Facturado
            boolean facturadoTS = estaFacturado(codigo);
            String facturado = facturadoTS ? "SI" : "NO";

            Direccion direccion = new DireccionDAO().getByCodigo(resultSet.getInt("direccion"));
            Usuario usuario = new UsuarioDAO().getByUsuario(resultSet.getString("cliente"));
            HashMap<Producto, Map.Entry<Integer, Double>> lineasPedido = getLineas(codigo);
            
            lista.add(new Pedido(codigo, fecha, metodoPago, facturado, direccion, usuario, lineasPedido));
        }

        return lista;
    }

    @Override
    public Pedido getByCodigo(int codigo) throws SQLException {
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE codigo=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codigo);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            LocalDateTime fecha = resultSet.getTimestamp("fecha").toLocalDateTime();
            TiposPago metodoPago = TiposPago.valueOf(resultSet.getString("metPago"));
            
            //Comprovar facturado
            boolean facturadoTS = estaFacturado(codigo);
            String facturado = facturadoTS ? "SI" : "NO"; 
            
            Direccion direccion = new DireccionDAO().getByCodigo(resultSet.getInt("direccion"));
            Usuario usuario = new UsuarioDAO().getByUsuario(resultSet.getString("cliente"));
            HashMap<Producto, Map.Entry<Integer, Double>> lineasPedido = getLineas(codigo);
            
            return new Pedido(codigo, fecha, metodoPago, facturado, direccion, usuario, lineasPedido);
        }

        return null;
    }
    
    public ArrayList<Pedido> getByCodigoUsuario(String nombreUsuario) throws SQLException {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE cliente=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setString(1, nombreUsuario);
        ResultSet resultSet = prepared.executeQuery();
        Usuario usuario = new UsuarioDAO().getByUsuario(nombreUsuario);
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");       
            LocalDateTime fecha = resultSet.getTimestamp("fecha").toLocalDateTime();
            TiposPago metodoPago = TiposPago.valueOf(resultSet.getString("metpago"));
            
            //Comprovar facturado
            boolean facturadoTS = estaFacturado(codigo);
            String facturado = facturadoTS ? "SI" : "NO"; 
            
            Direccion direccion = new DireccionDAO().getByCodigo(resultSet.getInt("direccion"));
            HashMap<Producto, Map.Entry<Integer, Double>> lineasPedido = getLineas(codigo);
            pedidos.add(new Pedido(codigo, fecha, metodoPago, facturado, direccion, usuario, lineasPedido));
        }

        return pedidos;
    }

    public HashMap<Producto, Map.Entry<Integer, Double>> getLineas(int codPedido) throws SQLException {
        ProductoDAO productoDAO = new ProductoDAO();
        HashMap<Producto, Map.Entry<Integer, Double>> lineas = new HashMap<>();
        String sentenciaSQL = "SELECT productos, precio, cantidad FROM PROYECTO_lineaPedido WHERE pedido = ?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codPedido);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            Producto producto = productoDAO.getByCodigo(resultSet.getInt("productos"));
            Double precio = resultSet.getDouble("precio");
            Integer cantidad = resultSet.getInt("cantidad");
            lineas.put(producto, Map.entry(cantidad, precio));
        }

        return lineas;

    }

    private void anyadirLineas(Pedido p) throws SQLException {
        
        
        for (Map.Entry<Producto, Map.Entry<Integer, Double>> linea: p.getLineas().entrySet()) {
            String sentenciaSQL = "INSERT INTO PROYECTO_lineaPedido VALUES(?, ?, ?, ?)";
            PreparedStatement prepared = getPrepared(sentenciaSQL);
            prepared.setInt(1, p.getCodigo());
            prepared.setInt(2, linea.getKey().getCodigo()); //COGER CODIGO PRODUCTO
            prepared.setInt(3, linea.getValue().getKey()); //COGER CANTIDAD
            prepared.setDouble(4, linea.getValue().getValue()); //COGER PRECIO
            
            prepared.executeUpdate();
        }
    }

    private void eliminarLineas(Pedido p) throws SQLException {
        String sentenciaSQL = "DELETE FROM PROYECTO_lineaPedido WHERE pedido=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, p.getCodigo());
        prepared.executeUpdate();

    }

    public boolean estaFacturado(int codpedido) throws SQLException {
        String sentenciaSQL = "SELECT * FROM PROYECTO_factura WHERE pedido=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codpedido);
        // H2 no permite executeUpdate con un SELECT (Oracle sí lo toleraba)
        try (ResultSet resultSet = prepared.executeQuery()) {
            return resultSet.next();
        }

    }
    
    public void descontarStock(Pedido pedido) {
        ProductoDAO productoDAO = new ProductoDAO();
        for (Map.Entry<Producto, Map.Entry<Integer, Double>> linea : pedido.getLineas().entrySet()) {
            try {
                Producto productoAntiguo = linea.getKey();
                int stock = productoAntiguo.getStock() - linea.getValue().getKey();
                Producto productoNuevo = new Producto(productoAntiguo.getCodigo(), productoAntiguo.getNombre(), productoAntiguo.getPrecio(), productoAntiguo.getIva(), productoAntiguo.getStockMinimo(), stock, productoAntiguo.getFoto(), productoAntiguo.getUsuarioCrea(), productoAntiguo.getFechaCrea(), productoAntiguo.getUsuarioModif(), productoAntiguo.getFechaModif(), productoAntiguo.getDescripcion(), productoAntiguo.getCategoria());
                productoDAO.actualizar(productoNuevo);
            } catch (SQLException ex) {
                System.out.println("Error SQL");
            }
        }
    }

}
