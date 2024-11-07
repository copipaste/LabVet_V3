/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interpreter.Interface;
import Interpreter.Events.TokenEvent;

/**
 *
 * @author Usuario
 */
public interface ITokenEventListener {
        // uno por cada caso de uso
    void usuario(TokenEvent event);

    void paciente(TokenEvent event);

    void producto(TokenEvent event);

    void servicio(TokenEvent event);

    void promocion(TokenEvent event);

    void cita(TokenEvent event);

    void pago(TokenEvent event);

    void reporte(TokenEvent event);

    void error(TokenEvent event);
    //agregar mas casos de uso
}
