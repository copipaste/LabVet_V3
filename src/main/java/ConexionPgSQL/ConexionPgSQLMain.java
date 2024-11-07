/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionPgSQL;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author teito
 */
public class ConexionPgSQLMain {

    public static void main(String[] args) {
        String usuario = "grupo06sc";
        String contrasenia = "grup006grup006*";
        String nombre_db = "db_grupo06sc";
        String host = "mail.tecnoweb.org.bo";
        String puerto = "5432";
        ConexionPgSQL conexion = new ConexionPgSQL(usuario, contrasenia, nombre_db, host, puerto);

        String query = "SELECT * FROM appuser";
        String TablaPersonas = "";
        Statement st;

        try {
            st = conexion.establecerConexion().createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                TablaPersonas = TablaPersonas
                        + rs.getString("id") + " || "
                        + rs.getString("name") + " || "
                        + rs.getString("email") + " || "
                        + rs.getString("phone") + " || "
                        + rs.getString("password") + " || "
                        + rs.getString("roleid") + " || "
                        + "\n";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

        conexion.cerrarConexion();

        System.out.println(TablaPersonas);
    }
}
