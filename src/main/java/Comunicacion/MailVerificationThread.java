/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comunicacion;

import Interfaces.IEmailEventListener;
import Utilitarios.Comando;
import Utilitarios.Email;
import Utilitarios.Extractor;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.AuthenticationException;

/**
 *
 * @author teito
 */
public class MailVerificationThread implements Runnable {

    
    private final static int PORT_POP = 110;
    private final static String HOST = "mail.tecnoweb.org.bo";
    private final static String USER = "grupo06sc";
    private final static String PASSWORD = "grup006grup006*";
    

    /*
     private final static int PORT_POP = 110;
     private final static String HOST = "www.agropartnerseta.com";
     private final static String USER = "teo.montalvo";
     private final static String PASSWORD = "1234";
     */
    private Socket socket;
    private BufferedReader input;
    private DataOutputStream output;

    private IEmailEventListener emailEventListener;

    public IEmailEventListener getEmailEventListener() {
        return emailEventListener;
    }

    public void setEmailEventListener(IEmailEventListener emailEventListener) {
        this.emailEventListener = emailEventListener;
    }

    public MailVerificationThread() {
        socket = null;
        input = null;
        output = null;
    }

    @Override
    public void run() {
        while (true) {
            try {
                List<Email> emails = null;
                socket = new Socket(HOST, PORT_POP);
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new DataOutputStream(socket.getOutputStream());
                System.out.println("[ Servidor de Correo: Conectado ]");

                authUser(USER, PASSWORD);

                int count = getEmailCount();
                if (count > 0) {
                    emails = getEmails(count);
                    System.out.println(emails);
                    deleteEmails(count);
                }
                output.writeBytes(Comando.quit());
                input.readLine();
                input.close();
                output.close();
                socket.close();
                System.out.println("[ Servidor de Correo: desconectado ]");

                if (count > 0) {
                    emailEventListener.onReciveEmailEvent(emails);
                }

                Thread.sleep(5000);

            } catch (IOException ex) {
                Logger.getLogger(MailVerificationThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(MailVerificationThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void authUser(String email, String password) throws IOException {
        if (socket != null && input != null && output != null) {
            input.readLine();
            output.writeBytes(Comando.user(email));
            input.readLine();
            output.writeBytes(Comando.pass(password));
            String message = input.readLine();
            if (message.contains("-ERR")) {
                throw new javax.security.sasl.AuthenticationException();
            }
        }
    }
    
    //Elimina los mensajes del servidor.
    private void deleteEmails(int emails) throws IOException {
        for (int i = 1; i <= emails; i++) {
            output.writeBytes(Comando.dele(i));
        }
    }

    //Obtiene el número de correos electrónicos disponibles en la bandeja de entrada.
    private int getEmailCount() throws IOException {
        output.writeBytes(Comando.stat());
        String line = input.readLine();
        String[] data = line.split(" ");
        return Integer.parseInt(data[1]);
    }

    private List<Email> getEmails(int count) throws IOException {
        List<Email> emails = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            output.writeBytes(Comando.retr(i));
            String text = readMultiline();
            emails.add(Extractor.getEmail(text));
        }
        return emails;
    }

    private String readMultiline() throws IOException {
        String lines = "";
        while (true) {
            String line = input.readLine();
            if (line == null) {
                throw new IOException("Server no responde (ocurrio un error al abrir el correo)");
            }
            if (line.equals(".")) {
                break;
            }
            lines = lines + "\n" + line;
        }
        return lines;
    }
}
