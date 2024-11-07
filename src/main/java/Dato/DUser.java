/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dato;

import ConexionPgSQL.ConexionPgSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class DUser {

    // Constantes que definen los nombres de las columnas para visualización
    public static final String[] HEADERS = {"ID", "NOMBRE", "CORREO", "CONTRASEÑA", "TELEFONO", "ROL", "FECHA CREACION", "FECHA ACTUALIZACION"};
    public static final String[] HEADERSROL = {"ID", "ROL"};

    // Atributo para establecer la conexión con la base de datos
    private ConexionPgSQL conexion;

    // Constructor de la clase, inicializa la conexión con PostgreSQL usando credenciales específicas
    public DUser() {
        this.conexion = new ConexionPgSQL("postgres", "toor", "grupo17sc", "174.138.38.169", "5432");
    }

    // Método para crear un nuevo registro en la tabla AppUser
    public void crear(String nombre, String email, String contrasenia, String telefono, String roleId) throws SQLException {

        String consulta = "INSERT INTO AppUser(name, email, password, phone, roleId, created_at, updated_at)" // Consulta SQL para insertar un usuario en la tabla AppUser
                + " values(?,?,?,?,?,?,?)";
        PreparedStatement ps = this.conexion.establecerConexion().prepareStatement(consulta);   // Prepara la consulta para ejecutar con valores específicos
        LocalDateTime fechaHoraActual = LocalDateTime.now();    // Obtiene la fecha y hora actuales para los campos de creación y actualización

        // Asigna los valores de los parámetros en la consulta SQL
        ps.setString(1, nombre);
        ps.setString(2, email);
        ps.setString(3, contrasenia);
        ps.setString(4, telefono);
        ps.setInt(5, Integer.parseInt(roleId));
        ps.setTimestamp(6, Timestamp.valueOf(fechaHoraActual));
        ps.setTimestamp(7, Timestamp.valueOf(fechaHoraActual));

        // Ejecuta la consulta de inserción y verifica si tuvo éxito
        if (ps.executeUpdate() == 0) {
            System.err.println("DUsuario.java: Error en crear()");
            throw new SQLException();  // Lanza una excepción si no se realiza la inserción
        }
    }

    // Método para actualizar un usuario existente en la tabla AppUser
    public void editar(int id, String nombre, String email, String contrasenia, String telefono, String roleId) throws SQLException {
        // Consulta SQL para actualizar un usuario específico
        String consulta = "UPDATE AppUser SET name=?, email=?, password=?, phone=?, roleId=?, updated_at=? WHERE id=?";

        PreparedStatement ps = this.conexion.establecerConexion().prepareStatement(consulta);  // Prepara la consulta para ejecutar con valores específicos
        LocalDateTime fechaHoraActual = LocalDateTime.now();   // Obtiene la fecha y hora actuales para el campo de actualización
        ps.setString(1, nombre);
        ps.setString(2, email);
        ps.setString(3, contrasenia);
        ps.setString(4, telefono);
        ps.setInt(5, Integer.parseInt(roleId));
        ps.setTimestamp(6, Timestamp.valueOf(fechaHoraActual));
        ps.setInt(7, id);                      // Asigna el ID del usuario a modificar
        if (ps.executeUpdate() == 0) {          // Ejecuta la consulta de actualización y verifica si tuvo éxito
            System.err.println("DUsuario.java: Error en editar()");
            throw new SQLException();  // Lanza una excepción si no se realiza la actualización
        }
    }

    // Método para eliminar un usuario de la tabla AppUser
    public void eliminar(int id) throws SQLException {
        String consulta = "DELETE FROM AppUser WHERE id=?";  // Consulta SQL para eliminar un usuario por ID
        PreparedStatement ps = this.conexion.establecerConexion().prepareStatement(consulta);
        ps.setInt(1, id);  // Asigna el ID del usuario a eliminar
        if (ps.executeUpdate() == 0) {
            System.err.println("DUsuario.java: Error en eliminar()");
            throw new SQLException();  // Lanza una excepción si no se realiza la eliminación
        }
    }

    // Método para ver detalles de un usuario específico por su ID
    public List<String[]> ver(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM AppUser WHERE id = ?";  // Consulta para verificar existencia del usuario
        PreparedStatement statement = this.conexion.establecerConexion().prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);  // Obtiene el conteo de registros con el ID dado

        // Si el usuario existe, se recuperan sus detalles
        if (count > 0) {
            List<String[]> usuarios = new ArrayList<>();
            String consulta = "SELECT AppUser.id, AppUser.name, AppUser.email, AppUser.password, AppUser.phone, "
                    + "Role.name AS role, AppUser.created_at, AppUser.updated_at "
                    + "FROM AppUser INNER JOIN Role ON AppUser.roleId = Role.id WHERE AppUser.id = ?";
            PreparedStatement ps = this.conexion.establecerConexion().prepareStatement(consulta);
            ps.setInt(1, id);
            ResultSet set = ps.executeQuery();

            // Si hay un resultado, se agrega a la lista de usuarios
            if (set.next()) {
                usuarios.add(new String[]{
                    String.valueOf(set.getInt("id")),
                    set.getString("name"),
                    set.getString("email"),
                    set.getString("password"),
                    set.getString("phone"),
                    set.getString("role"),
                    set.getString("created_at"),
                    set.getString("updated_at")
                });
            }
            return usuarios;
        } else {
            throw new SQLException();  // Lanza una excepción si el usuario no existe
        }
    }

    // Método para listar todos los usuarios en la tabla AppUser
    public List<String[]> listar() throws SQLException {
        List<String[]> usuarios = new ArrayList<>();
        String consulta = "SELECT AppUser.id, AppUser.name, AppUser.email, AppUser.password, AppUser.phone, "
                + "Role.name AS role, AppUser.created_at, AppUser.updated_at "
                + "FROM AppUser INNER JOIN Role ON AppUser.roleId = Role.id";
        PreparedStatement ps = this.conexion.establecerConexion().prepareStatement(consulta);
        ResultSet set = ps.executeQuery();

        // Itera sobre los resultados y añade cada usuario a la lista
        while (set.next()) {
            usuarios.add(new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("name"),
                set.getString("email"),
                set.getString("password"),
                set.getString("phone"),
                set.getString("role"),
                set.getString("created_at"),
                set.getString("updated_at")
            });
        }
        return usuarios;
    }

    // Método para listar roles disponibles
    public List<String[]> listarRoles() throws SQLException {
        List<String[]> roles = new ArrayList<>();
        String consulta = "SELECT id, name FROM Role";  // Consulta SQL para listar roles
        PreparedStatement ps = this.conexion.establecerConexion().prepareStatement(consulta);
        ResultSet set = ps.executeQuery();

        // Agrega cada rol a la lista
        while (set.next()) {
            roles.add(new String[]{
                String.valueOf(set.getInt("id")),
                set.getString("name"),});
        }
        return roles;
    }

    // Verifica si un usuario es administrativo según su correo
    public boolean esAdministrativo(String correo) throws SQLException {
        boolean resp = false;
        String query = "SELECT * FROM AppUser WHERE roleId = 1 AND email = ?";  // Verifica si el rol es administrativo
        PreparedStatement statement = this.conexion.establecerConexion().prepareStatement(query);
        statement.setString(1, correo);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            resp = true;
        }
        return resp;
    }

    // Verifica si existe un usuario según su correo
    public boolean esUsuario(String correo) throws SQLException {
        boolean resp = false;
        String query = "SELECT * FROM AppUser WHERE email = ?";  // Consulta SQL para verificar existencia por email
        PreparedStatement statement = this.conexion.establecerConexion().prepareStatement(query);
        statement.setString(1, correo);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            resp = true;
        }
        return resp;
    }

    public void desconectar() {
        if (this.conexion != null) {
            this.conexion.cerrarConexion();
        }
    }
}
