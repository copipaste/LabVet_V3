/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionPgSQL;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author teito
 */
public class ConexionPgSQL {

    private Connection conectar = null;
    private String usuario;
    private String contrasenia;
    private String nombre_db;
    private String host;
    private String puerto;
    private String cadena;

    public ConexionPgSQL(String usuario, String contrasenia, String nombre_db, String host, String puerto) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre_db = nombre_db;
        this.host = host;
        this.puerto = puerto;
        this.cadena = "jdbc:postgresql://" + host + ":" + puerto + "/" + nombre_db;

    }

    public Connection establecerConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conectar = DriverManager.getConnection(this.cadena, this.usuario, this.contrasenia);
            System.out.println("[ Servidor de BD: Conectado ]");
        } catch (Exception e) {
            System.out.println("Error en conectar a la base de datos" + e.toString());
        }
        return this.conectar;
    }

    public void cerrarConexion() {
        try {
            this.conectar.close();
            System.out.println("[ Servidor de BD: desconectado ]");
        } catch (Exception e) {
            System.out.println("Error en cerrar la conexion a la base de datos" + e.toString());
        }
    }
}
