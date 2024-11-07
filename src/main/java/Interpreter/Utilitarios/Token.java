/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interpreter.Utilitarios;

/**
 *
 * @author Usuario
 */
public class Token {
    private int name;// si es CU, ACTION o ERROR
    private int attribute; // que tipo ya sea CU, ACTION o ERROR


    //constantes numericas para manejar el analizadorLex
    public static final int CU = 0;
    public static final int ACTION = 1;
    public static final int PARAMS = 2;
    public static final int END = 3;
    public static final int ERROR = 4;    
    
    
    // ajustar de acuerdo a sus casos de uso con valores entre 100 a 199
    //Titulos de casos de uso en numero
    public static final int USUARIO = 100;
    public static final int PACIENTE = 101;
    public static final int PRODUCTO = 102;
    public static final int SERVICIO = 103;
    public static final int PROMOCION = 104;
    public static final int CITA = 105;
    public static final int PAGO = 106;
    public static final int REPORTE = 107;
    
    
    
    
    
    //ajustar de acuerdo a sus acciones con valores entre 200 a 299
    //Titulos de las acciones generales

    public static final int REGISTRAR = 200;
    public static final int ELIMINAR = 201;
    public static final int EDITAR = 202;
    public static final int LISTAR = 203;
    public static final int VERIFICAR = 204;
    public static final int CANCELAR = 205;
    public static final int VER = 206;
    public static final int AYUDA = 207;

    public static final int SOLICITAR = 208;
    public static final int RECOMENDAR = 209;
    public static final int RECLAMAR = 210;
    public static final int ATENDER = 211;
    public static final int FINALIZAR = 212;

    public static final int ERROR_COMMAND = 300;
    public static final int ERROR_CHARACTER = 301;
    
    
    
    //constantes literales para realizar un efecto de impresión
    public static final String LEXEME_CU = "caso de uso";
    public static final String LEXEME_ACTION = "action";
    public static final String LEXEME_PARAMS = "params";
    public static final String LEXEME_END = "end";
    public static final String LEXEME_ERROR = "error";

    // ajustar de acuerdo a sus casos de uso con valores en string
    //Titulos de casos de uso con string
    public static final String LEXEME_USUARIO = "usuario";
    public static final String LEXEME_PACIENTE = "paciente";
    public static final String LEXEME_PRODUCTO = "producto";
    public static final String LEXEME_SERVICIO = "servicio";
    public static final String LEXEME_PROMOCION = "promocion";
    public static final String LEXEME_CITA = "cita";
    public static final String LEXEME_PAGO = "pago";
    public static final String LEXEME_REPORTE = "reporte";
    
    
    
    
    //ajustar de acuerdo a sus acciones con valores en string
    //Titulos de las acciones generales en string

    public static final String LEXEME_REGISTRAR = "registrar";
    public static final String LEXEME_ELIMINAR = "eliminar";
    public static final String LEXEME_EDITAR = "editar";
    public static final String LEXEME_LISTAR = "listar";
    public static final String LEXEME_VERIFICAR = "verificar";
    public static final String LEXEME_CANCELAR = "cancelar";
    public static final String LEXEME_VER = "ver";
    public static final String LEXEME_AYUDA = "ayuda";

    public static final String LEXEME_SOLICITAR = "solicitar";
    public static final String LEXEME_RECOMENDAR = "recomendar";
    public static final String LEXEME_RECLAMAR = "reclamar";
    public static final String LEXEME_ATENDER = "atender";
    public static final String LEXEME_FINALIZAR = "finalizar";

    public static final String LEXEME_ERROR_COMMAND = "UNKNOWN COMMAND";
    public static final String LEXEME_ERROR_CHARACTER = "UNKNOWN CHARACTER";
    

    public Token() {}
    
    
    
    
    
    
    
    
    
    /**
     * Constructor parametrizado por el literal del token
     *
     * @param token
     */
    //No Tocar
    public Token(String token) {
        int id = findByLexeme(token);
        if (id != -1) {
            if (100 <= id && id < 200) {
                this.name = CU;
                this.attribute = id;
            } else if (200 <= id && id < 300) {
                this.name = ACTION;
                this.attribute = id;
            }
        } else {
            this.name = ERROR;
            this.attribute = ERROR_COMMAND;
            System.err.println("Class Token.Constructor dice: \n "
                    + " El lexema enviado al constructor no es reconocido como un token \n"
                    + "Lexema: " + token);
        }
    }

    /**
     * Constructor parametrizado 2.
     *
     * @param name
     */
    public Token(int name) {
        this.name = name;
    }

    /**
     * Constructor parametrizado 3.
     *
     * @param name
     * @param attribute
     */
    public Token(int name, int attribute) {
        this.name = name;
        this.attribute = attribute;
    }

    // Setters y Getters
    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        if (0 <= name && name <= 1) {
            return "< " + getStringToken(name) + " , " + getStringToken(attribute) + ">";
        } else if (name == 2) {
            return "< " + getStringToken(name) + " , " + attribute + ">";
        } else if (3 == name) {
            return "< " + getStringToken(name) + " , " + "_______ >";
        } else if (name == 4) {
            return "< " + getStringToken(name) + " , " + getStringToken(attribute) + ">";
        }
        return "< TOKEN , DESCONOCIDO>";
    }

    /**
     * Devuelve el valor literal del token enviado Si no lo encuentra retorna N:
     * token.
     *
     * @param token
     * @return
     */
    //ajustar de acuerdo a sus CU
    public String getStringToken(int token) {
        switch (token) {
            case CU:
                return LEXEME_CU;
            case ACTION:
                return LEXEME_ACTION;
            case PARAMS:
                return LEXEME_PARAMS;
            case END:
                return LEXEME_END;
            case ERROR:
                return LEXEME_ERROR;

            //CU
            case USUARIO:
                return LEXEME_USUARIO;
            case PACIENTE:
                return LEXEME_PACIENTE;
            case PRODUCTO:
                return LEXEME_PRODUCTO;
            case SERVICIO:
                return LEXEME_SERVICIO;
            case PROMOCION:
                return LEXEME_PROMOCION;
            case CITA:
                return LEXEME_CITA;
            case PAGO:
                return LEXEME_PAGO;
            case REPORTE:
                return LEXEME_REPORTE;

            //ACCION
            case REGISTRAR:
                return LEXEME_REGISTRAR;
            case ELIMINAR:
                return LEXEME_ELIMINAR;
            case EDITAR:
                return LEXEME_EDITAR;
            case LISTAR:
                return LEXEME_LISTAR;
            case VERIFICAR:
                return LEXEME_VERIFICAR;
            case CANCELAR:
                return LEXEME_CANCELAR;
            case VER:
                return LEXEME_VER;
            case AYUDA:
                return LEXEME_AYUDA;

            case SOLICITAR:
                return LEXEME_SOLICITAR;
            case RECOMENDAR:
                return LEXEME_RECOMENDAR;
            case RECLAMAR:
                return LEXEME_RECLAMAR;
            case ATENDER:
                return LEXEME_ATENDER;
            case FINALIZAR:
                return LEXEME_FINALIZAR;

            case ERROR_COMMAND:
                return LEXEME_ERROR_COMMAND;
            case ERROR_CHARACTER:
                return LEXEME_ERROR_CHARACTER;
            default:
                return "N: " + token;
        }
    }

    /**
     * Devuelve el valor numerico del lexema enviado Si no lo encuentra retorna
     * -1.
     *
     * @param lexeme
     * @return
     */
    //ajustar de acuerdo a sus CU
    private int findByLexeme(String lexeme) {
        switch (lexeme) {
            case LEXEME_CU:
                return CU;
            case LEXEME_ACTION:
                return ACTION;
            case LEXEME_PARAMS:
                return PARAMS;
            case LEXEME_END:
                return END;
            case LEXEME_ERROR:
                return ERROR;

            //CU 
            case LEXEME_USUARIO:
                return USUARIO;
            case LEXEME_PACIENTE:
                return PACIENTE;
            case LEXEME_PRODUCTO:
                return PRODUCTO;
            case LEXEME_SERVICIO:
                return SERVICIO;
            case LEXEME_PROMOCION:
                return PROMOCION;
            case LEXEME_CITA:
                return CITA;
            case LEXEME_PAGO:
                return PAGO;
            case LEXEME_REPORTE:
                return REPORTE;

            //ACTION    
            case LEXEME_REGISTRAR:
                return REGISTRAR;
            case LEXEME_ELIMINAR:
                return ELIMINAR;
            case LEXEME_EDITAR:
                return EDITAR;
            case LEXEME_LISTAR:
                return LISTAR;
            case LEXEME_VERIFICAR:
                return VERIFICAR;
            case LEXEME_CANCELAR:
                return CANCELAR;
            case LEXEME_VER:
                return VER;
            case LEXEME_AYUDA:
                return AYUDA;

            case LEXEME_SOLICITAR:
                return SOLICITAR;
            case LEXEME_RECOMENDAR:
                return RECOMENDAR;
            case LEXEME_RECLAMAR:
                return RECLAMAR;
            case LEXEME_ATENDER:
                return ATENDER;
            case LEXEME_FINALIZAR:
                return FINALIZAR;

            case LEXEME_ERROR_COMMAND:
                return ERROR_COMMAND;
            case LEXEME_ERROR_CHARACTER:
                return ERROR_CHARACTER;
            default:
                return -1;
        }
    }
    
    
}
