/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Manuel
 */
public class comprobacion {
    
    public boolean comprobacion(String cadena){
        
        try {
            Integer.parseInt(cadena);
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
}
