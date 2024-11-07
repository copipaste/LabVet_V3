/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import Utilitarios.Email;
import java.util.List;

/**
 *
 * @author teito
 */
public interface IEmailEventListener {
    void onReciveEmailEvent(List<Email> emails);
    
}
