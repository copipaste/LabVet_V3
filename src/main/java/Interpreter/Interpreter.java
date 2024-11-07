/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interpreter;
import Interpreter.Events.TokenEvent;
import Interpreter.Interface.ITokenEventListener;
import Interpreter.Models.TokenCommand;
import Interpreter.Utilitarios.Token;
/**
 *
 * @author Usuario
 */
public class Interpreter implements Runnable{
    
    
    private ITokenEventListener listener; // Dispara las funciones de la interface
    private AnalizadorLex analex;

    private String command;
    private String sender;

    public Interpreter(String command, String sender) {
        this.command = command;
        this.sender = sender;
    }

    public ITokenEventListener getListener() {
        return listener;
    }

    public void setListener(ITokenEventListener listener) {
        this.listener = listener;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    private void filterEvent(TokenCommand token_command) {
        TokenEvent token_event = new TokenEvent(this, sender);
        token_event.setAction(token_command.getAction()); // dame la accion

        int count_params = token_command.countParams(); // cuantos parametros
        for (int i = 0; i < count_params; i++) {
            int pos = token_command.getParams(i);
            token_event.addParams(analex.getParam(pos)); // agrega los parametros
        }

        //ajustar de acuerdo a su casos de uso
        switch (token_command.getName()) {
            case Token.USUARIO:
                listener.usuario(token_event);
                break;
            case Token.PACIENTE:
                listener.paciente(token_event);
                break;
            case Token.PRODUCTO:
                listener.producto(token_event);
                break;
            case Token.SERVICIO:
                listener.servicio(token_event);
                break;
            case Token.PROMOCION:
                listener.promocion(token_event);
                break;
            case Token.CITA:
                listener.cita(token_event);
                break;
            case Token.PAGO:
                listener.pago(token_event);
                break;
            case Token.REPORTE:
                listener.reporte(token_event);
                break;
        }

    }

    private void tokenError(Token token, String error) {
        TokenEvent token_event = new TokenEvent(this, sender);
        token_event.setAction(token.getAttribute());
        token_event.addParams(command);
        token_event.addParams(error);
        listener.error(token_event);
    }

    public void run() {
        analex = new AnalizadorLex(command);
        TokenCommand token_command = new TokenCommand();
        Token token;

        //while(analex.Preanalisis() != null) {
        //token = analex.Preanalisis();
        //if (token.getName() == Token.END && token.getName() == Token.ERROR) {
        //break;
        //}
        //}
        while ((token = analex.Preanalisis()).getName() != Token.END && token.getName() != Token.ERROR) {
            if (token.getName() == Token.CU) {
                token_command.setName(token.getAttribute());// id del CU
            } else if (token.getName() == Token.ACTION) {
                token_command.setAction(token.getAttribute());// id de la accion
            } else if (token.getName() == Token.PARAMS) {
                token_command.addParams(token.getAttribute());// la posicion del parametro en el tsp
            }
            analex.next();
        }

        if (token.getName() == Token.END) {
            filterEvent(token_command);// se analizo el comando con exito
        } else if (token.getName() == Token.ERROR) {
            tokenError(token, analex.lexeme()); // se produjo un error en el analisis
        }

    }
}
