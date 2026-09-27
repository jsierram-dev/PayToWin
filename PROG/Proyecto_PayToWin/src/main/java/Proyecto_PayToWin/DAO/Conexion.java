/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto_PayToWin.DAO;

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

    public static Conexion getConexion(){
        if(instancia == null) instancia = new Conexion();
        return instancia;
    }

    private Conexion() {
        pool = new PoolProperties();
        // Credenciales desde variables de entorno (nunca en el código)
        pool.setUrl(env("DB_URL"));
        pool.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        pool.setUsername(env("DB_USER"));
        pool.setPassword(env("DB_PASSWORD"));
        pool.setMaxActive(15);
        pool.setMaxIdle(10);
        pool.setMaxWait(5000);
        // Oracle Cloud cierra las conexiones inactivas: se validan antes de usarlas
        pool.setTestOnBorrow(true);
        pool.setValidationQuery("SELECT 1 FROM DUAL");
        pool.setValidationInterval(30000);
        datasource = new DataSource();
        datasource.setPoolProperties(pool);
    }

    private static String env(String nombre) {
        String valor = System.getenv(nombre);
        if (valor == null || valor.isEmpty()) {
            throw new IllegalStateException("Falta la variable de entorno " + nombre);
        }
        return valor;
    }

    public DataSource getDatasource() {
        return datasource;
    }



}
