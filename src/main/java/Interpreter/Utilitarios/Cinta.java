/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interpreter.Utilitarios;

/**
 *
 * @author Usuario
 */
public class Cinta {

    public static final char EOF = 0;  //Es una constante que representa el final de la entrada
    private String cinta; // lo que contendra el texto que se analizara
    private int i;  // es el indice que se utilizara para recorrer el texto(cinta)

    /**
     * El procedimiento init reinicia el cabezal al inicio.
     */
    public void init() {
        i = 0;
    }

    /**
     * Contructor recibe como parametro un String para trabajarlo como cinta
     *
     * @param cinta
     */
    public Cinta(String cinta) {
        this.cinta = cinta;
        this.i = 0;
    }

    /**
     * El procedimiento forward avanza el cabezal de la cinta.
     */
    public void forward() {
        if (i <= cinta.length()) {
            i++;
        } else {
            System.err.println("Class Cinta.forward dice: \n"
                    + " No se puede avanzar mas el cabezal.");
        }
    }

    /**
     * La funcion CC devuelve el character en el que esta el cabezal de la cinta
     *
     * @return
     */
    public char CC() {
        if (i < cinta.length()) {
            return cinta.charAt(i);
        }
        return EOF;
    }

}
