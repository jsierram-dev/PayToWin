/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto_PayToWin.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 *
 * @author juanp
 */
public class Conexion {
    private static Conexion instancia = null;
    private final DataSource datasource;
    private final PoolProperties pool;

    public static synchronized Conexion getConexion(){
        if(instancia == null) instancia = new Conexion();
        return instancia;
    }

    private Conexion() {
        pool = new PoolProperties();
        // Demo: H2 en memoria en modo Oracle. Vive mientras la app esté arrancada
        // y se recarga desde los scripts (copia de BDAW/) en cada arranque.
        pool.setUrl("jdbc:h2:mem:paytowin;MODE=Oracle;DB_CLOSE_DELAY=-1");
        pool.setDriverClassName("org.h2.Driver");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxActive(15);
        pool.setMaxIdle(10);
        pool.setMaxWait(5000);
        datasource = new DataSource();
        datasource.setPoolProperties(pool);
        cargarDatos();
    }

    private void cargarDatos() {
        try (Connection conn = datasource.getConnection();
             Statement st = conn.createStatement()) {
            st.execute("RUNSCRIPT FROM 'classpath:/db/tablas.sql' CHARSET 'UTF-8'");
            st.execute("RUNSCRIPT FROM 'classpath:/db/inserciones.sql' CHARSET 'UTF-8'");
        } catch (SQLException ex) {
            throw new IllegalStateException("No se pudo cargar la base de datos de la demo", ex);
        }
    }

    public DataSource getDatasource() {
        return datasource;
    }



}
