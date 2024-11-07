/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilitarios;

/**
 *
 * @author teito
 */
public class Comando {

    private static final String END = "\r\n";

    public static String user(String user) {
        return "USER " + user + END;
    }

    public static String pass(String pass) {
        return "PASS " + pass + END;
    }

    public static String stat() {
        return "STAT " + END;
    }

    public static String list() {
        return "LIST " + END;
    }
    
    public static String quit(){
        return "QUIT" + END;
    }
    
    public static String retr(int email){
        return "RETR " + email + END;
    }
    
    public static String dele(int email){
        return "DELE " + email + END;
    }
}
