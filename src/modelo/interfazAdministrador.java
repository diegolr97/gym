/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import clases.Administrador;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author diego
 */
public interface interfazAdministrador {
    
    public DefaultListModel listAdmin();
    public Administrador datosAdmin(String idEmpleado);
    public boolean eliminarEmpleado(String idEmpleado2);
    public boolean modificarEmpleado(String idEmpleado, String clave, String nombre, String apellidos, String direccion, int telefono, String correo, int codPostal);
    
    
}
