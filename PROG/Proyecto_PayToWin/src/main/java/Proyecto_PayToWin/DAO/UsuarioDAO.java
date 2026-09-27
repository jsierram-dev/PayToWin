/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto_PayToWin.DAO;

import Proyecto_PayToWin.DTO.Direccion;
import Proyecto_PayToWin.DTO.TiposDireccion;
import Proyecto_PayToWin.DTO.TiposUsuario;
import Proyecto_PayToWin.DTO.Usuario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ciclost
 */
public class UsuarioDAO extends TablaDAO<Usuario> {

    public UsuarioDAO() {
        this.tabla = "PROYECTO_usuario";
    }

    @Override
    public int actualizar(Usuario u) {
        // NO SE UTILIZA EN NUESTRO PROYECTO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //FUNCIONA
    @Override
    public int anyadir(Usuario u) throws SQLException {
        String sentenciaSQL = "INSERT INTO " + tabla + " VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setString(1, u.getNombreUsuario());
        prepared.setString(2, u.getNombreReal());
        prepared.setString(3, u.getCorreo());
        prepared.setString(4, u.getPassw());
        prepared.setDate(5, Date.valueOf(u.getFnac()));
        
        //Insertar telefono
        if(u.getTel() == 0){
            prepared.setNull(6, java.sql.Types.INTEGER);
        } else {
            prepared.setInt(6, u.getTel());
        }
        
        
        //Insertar Ultima Conexión
        LocalDateTime ultimaConexion = u.getUltconex();
        if (ultimaConexion == null) {
            prepared.setNull(7, java.sql.Types.TIMESTAMP);
        } else {
            prepared.setTimestamp(7, Timestamp.valueOf(ultimaConexion));
        }
        
        prepared.setString(8, String.valueOf(u.getTipo()));
        
        //Insertar foto
        String foto = u.getFoto();
        if(foto == null){
            prepared.setNull(9, java.sql.Types.VARCHAR);
        } else {
            prepared.setString(9, u.getFoto());
        }
        
        return prepared.executeUpdate();

    }

    
    public Usuario eliminar(String usuario) throws SQLException {
        Usuario aux = this.getByUsuario(usuario);
        String sentenciaSQL = "DELETE FROM " + tabla + " WHERE nombreUsuario = ?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setString(1, usuario);
        prepared.executeUpdate();
        return aux;
    }

    
    @Override
    public boolean existe(Usuario u) throws SQLException {
        return this.getByUsuario(u.getNombreUsuario()) != null;
    }
    


    //FALTA ESTE
    @Override
    public ArrayList<Usuario> getAll() throws SQLException {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + tabla + " ORDER BY nombreUsuario";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            String usuario = resultSet.getString("nombreUsuario");
            String nombreCompleto = resultSet.getString("nombreReal");
            String email = resultSet.getString("correo");
            String contrasenya = resultSet.getString("passw");
            LocalDate fechaNacimiento = resultSet.getDate("fnac").toLocalDate();
            
            //Comprovar telefono
            int telefonoTS = resultSet.getInt("tel");
            int telefono = telefonoTS == 0 ? 0 : telefonoTS;
            
            //Comprovar ultima Conexión
            Timestamp ultimaConexionTS = resultSet.getTimestamp("ultconex");
            LocalDateTime ultimaConexion = ultimaConexionTS == null ? null : ultimaConexionTS.toLocalDateTime();
            
            TiposUsuario tipoUsuario = TiposUsuario.valueOf(resultSet.getString("tipo"));
            
            //Comprobar Foto
            String fotoTS = resultSet.getString("foto");
            String foto = fotoTS == null ? null : fotoTS;
            
            lista.add(new Usuario(usuario, nombreCompleto, email, contrasenya, fechaNacimiento, telefono, ultimaConexion, foto, tipoUsuario));
        }

        return lista;
    }
    
    public Usuario getByUsuario(String usuario) throws SQLException {
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE nombreUsuario = ?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setString(1, usuario);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            String nombreCompleto = resultSet.getString("nombreReal");
            String email = resultSet.getString("correo");
            String contrasenya = resultSet.getString("passw");
            LocalDate fechaNacimiento = resultSet.getDate("fnac").toLocalDate();
            
            //Comprobar Telefono
            int telefonoTS = resultSet.getInt("tel");
            int telefono = telefonoTS == 0 ? 0 : telefonoTS;

            
            //Comprobar Ultima Conexión
            Timestamp ultimaConexionTS = resultSet.getTimestamp("ultconex");
            LocalDateTime ultimaConexion = ultimaConexionTS == null ? null : ultimaConexionTS.toLocalDateTime();
            
            TiposUsuario tipoUsuario = TiposUsuario.valueOf(resultSet.getString("tipo"));
            
            //Comprobar Foto
            String fotoTS = resultSet.getString("foto");
            String foto = fotoTS == null ? null : fotoTS;
            
            return new Usuario(usuario, nombreCompleto, email, contrasenya, fechaNacimiento, telefono, ultimaConexion, foto, tipoUsuario);
        }
        
        return null;
    }
    
    public Usuario getUsuario(String nombreUsuario, String contrasenya) throws SQLException{
        // Se busca solo por usuario y la contraseña se comprueba contra el hash BCrypt guardado
        String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE nombreUsuario = ?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setString(1, nombreUsuario);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            String hash = resultSet.getString("passw");
            if (contrasenya == null || !passwordCorrecta(contrasenya, hash)) {
                return null;
            }
            String nombreCompleto = resultSet.getString("nombreReal");
            String email = resultSet.getString("correo");
            LocalDate fechaNacimiento = resultSet.getDate("fnac").toLocalDate();
            
            //Comprobar Telefono
            int telefonoTS = resultSet.getInt("tel");
            int telefono = telefonoTS == 0 ? 0 : telefonoTS;

            
            //Comprobar Ultima Conexión
            Timestamp ultimaConexionTS = resultSet.getTimestamp("ultconex");
            LocalDateTime ultimaConexion = ultimaConexionTS == null ? null : ultimaConexionTS.toLocalDateTime();
            
            TiposUsuario tipoUsuario = TiposUsuario.valueOf(resultSet.getString("tipo"));
            
            //Comprobar Foto
            String fotoTS = resultSet.getString("foto");
            String foto = fotoTS == null ? null : fotoTS;
            
            return new Usuario(nombreUsuario, nombreCompleto, email, hash, fechaNacimiento, telefono, ultimaConexion, foto, tipoUsuario);
        }

        return null;
    }

    private static boolean passwordCorrecta(String contrasenya, String hash) {
        try {
            return hash != null && BCrypt.checkpw(contrasenya, hash);
        } catch (IllegalArgumentException ex) {
            // El valor guardado no es un hash BCrypt válido
            return false;
        }
    }
    
    //MI USUARIO NO TIENE CODIGO ASI QUE TENEMOS GETBYUSUARIO
    @Override
    public Usuario getByCodigo(int codigo) throws SQLException {
        /*String sentenciaSQL = "SELECT * FROM " + tabla + " WHERE codigo=?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setInt(1, codigo);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            String nombreCompleto = resultSet.getString("nombrecompleto");
            String contrasenya = resultSet.getString("contrasenya");
            String email = resultSet.getString("email");
            LocalDate fechaNacimiento = resultSet.getDate("fnacimiento").toLocalDate();
            int telefono = resultSet.getInt("telefono");
            TipoUsuario tipoUsuario = TipoUsuario.valueOf(resultSet.getString("tipo"));
            Timestamp ultimaConexionTS = resultSet.getTimestamp("ultconex");
            LocalDateTime ultimaConexion = ultimaConexionTS == null ? null : ultimaConexionTS.toLocalDateTime();
            return new Usuario(codigo, nombreCompleto, contrasenya, email, fechaNacimiento, telefono, tipoUsuario, ultimaConexion);
        }*/
        
        return null;
    }
    
    public ArrayList<Direccion> getDirecciones(String nombreUsuario) throws SQLException{
        ArrayList<Direccion> lista = new ArrayList<>();
        String sentenciaSQL = "SELECT d.* FROM PROYECTO_direccion d LEFT JOIN PROYECTO_usuario u on u.nombreUsuario = d.usuario where d.usuario = ?";
        PreparedStatement prepared = getPrepared(sentenciaSQL);
        prepared.setString(1, nombreUsuario);
        ResultSet resultSet = prepared.executeQuery();
        while (resultSet.next()) {
            int codigo = resultSet.getInt("codigo");
            String calle = resultSet.getString("calle");
            String ciudad = resultSet.getString("ciudad");
            String provincia = resultSet.getString("provincia");
            int cp = resultSet.getInt("cp");
            String pais = resultSet.getString("pais");
            Usuario usuario = new UsuarioDAO().getByUsuario(resultSet.getString("usuario"));
            TiposDireccion tipoDireccion = TiposDireccion.valueOf(resultSet.getString("tipo"));
            
            lista.add(new Direccion(codigo, calle, ciudad, provincia, cp, pais, usuario, tipoDireccion));
        }
        return lista;
    }

    @Override
    public Usuario eliminar(Usuario objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
