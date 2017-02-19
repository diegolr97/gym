/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import clases.Administrador;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author diego
 */
public class modeloAdministrador extends conexion implements interfazAdministrador {
    
    public modeloAdministrador(){
        
    }

    @Override
    public DefaultListModel listAdmin() {
        DefaultListModel model = new DefaultListModel();
        try {
           PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM empleado");
           ResultSet res = pstm.executeQuery();
            
            while (res.next()) //go through each row that your query returns
            {
                String itemCode = res.getString("idEmpleado") + " - " + res.getString("nombre") + " " + res.getString("apellidos"); 
                model.addElement(itemCode); //add each item to the model
            }
            
            
            res.close();
            pstm.close();
          } catch (SQLException ex) {
            Logger.getLogger(modeloCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
       return model;
    }
    
    @Override
    public Administrador datosAdmin(String idEmpleado) {
        Administrador a = new Administrador();
           try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT idEmpleado, clave, nombre, apellidos, direccion, telefono, correo, codPostal from empleado where idEmpleado ='" + idEmpleado + "' " );
            ResultSet res = pstm.executeQuery();
            
            while(res.next()){
                
            String idEmpleado2= res.getString("idEmpleado");
            String clave= res.getString("clave");
            String nombre= res.getString("nombre");
            String apellidos= res.getString("apellidos");
            String direccion= res.getString("direccion");
            int telefono= res.getInt("telefono");
            String correo= res.getString("correo");
            int codPostal= res.getInt("codPostal");
            
            
            
           
            
            a.setIdEmpleado(idEmpleado2);
            a.setClave(clave);
            a.setNombre(nombre);
            a.setApellidos(apellidos);
            a.setDireccion(direccion);
            a.setTelefono(telefono);
            a.setCorreo(correo);
            a.setCodPostal(codPostal);
            
            
            
            }
            res.close();
            pstm.close();
            
           }catch(SQLException ex){
               Logger.getLogger(modeloCliente.class.getName()).log(Level.SEVERE, null, ex);
               
           } 
           return a;
        
            
        
        }
    
   
    
    
    
}
