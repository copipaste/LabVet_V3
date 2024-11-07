/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Dato.DUser;

/**
 *
 * @author Usuario
 */
public class NUsuario {
    private DUser dUsuario;

    public NUsuario() {
        dUsuario = new DUser();
    }

    public void crear(List<String> parametros) throws SQLException {
        dUsuario.crear(parametros.get(0), parametros.get(1),
                parametros.get(2), parametros.get(3),
                parametros.get(4));
        dUsuario.desconectar();
    }

    public void editar(List<String> parametros) throws SQLException {
        dUsuario.editar(Integer.parseInt(parametros.get(0)), parametros.get(1),
                parametros.get(2), parametros.get(3),
                parametros.get(4), parametros.get(5));
        dUsuario.desconectar();
    }

    public void eliminar(List<String> parametros) throws SQLException {
        dUsuario.eliminar(Integer.parseInt(parametros.get(0)));
        dUsuario.desconectar();
    }

    public List<String[]> ver(List<String> parametros) throws SQLException {
        List<String[]> usuarios = dUsuario.ver(Integer.parseInt(parametros.get(0)));
        return usuarios;
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> usuarios = (ArrayList<String[]>) dUsuario.listar();
        dUsuario.desconectar();
        return usuarios;
    }

//    public List<String[]> listarRoles() throws SQLException {
//        List<String[]> roles = (ArrayList<String[]>) dUsuario.listarRoles();
//        dUsuario.desconectar();
//        return roles;
//    }

//    public List<String[]> ayuda() throws SQLException {
//        List<String[]> ayudas = (ArrayList<String[]>) dUsuario.ayuda();
//        return ayudas;
//    }

//    public boolean esAdministrativo(String correo) throws SQLException {
//        boolean resp = dUsuario.esAdministrativo(correo);
//        dUsuario.desconectar();
//        return resp;
//    }

//    public boolean esUsuario(String correo) throws SQLException {
//        boolean resp = dUsuario.esUsuario(correo);
//        dUsuario.desconectar();
//        return resp;
//    }
}
