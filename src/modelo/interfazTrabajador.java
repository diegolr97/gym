/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import clases.Trabajador;
import javax.swing.DefaultListModel;

/**
 *
 * @author diego
 */
public interface interfazTrabajador {
    
    public DefaultListModel listTrabajador();
    public boolean añadirTrabajador(String idEmpleado, String clave, int admin, String nombre, String apellidos, String direccion, int telefono, String correo, int codPostal);
    public Trabajador datosTrabajador(String idEmpleado);
    public boolean eliminarTrabajador(String idEmpleado2);
    public boolean modificarTrabajador(String idEmpleado, String clave, String nombre, String apellidos, String direccion, int telefono, String correo, int codPostal);
}
