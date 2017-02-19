/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import clases.Administrador;
import java.sql.CallableStatement;
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
           PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM empleado where admin = 1");
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
    @Override
public boolean modificarEmpleado(String idEmpleado, String clave, String nombre, String apellidos, String direccion, int telefono, String correo, int codPostal){
        boolean res=false;
        try {
            CallableStatement cstm = this.getConexion().prepareCall("{call modificarEmpleado(?,?,?,?,?,?,?,?)}");
            
            cstm.setString(1, idEmpleado);
            cstm.setString(2, clave);
            cstm.setString(3, nombre);
            cstm.setString(4, apellidos);
            cstm.setString(5, direccion);
            cstm.setInt(6, telefono);
            cstm.setString(7, correo);
            cstm.setInt(8, codPostal);
            
            
          
            
            cstm.executeUpdate();
            
            cstm.close();
            res=true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage() + "     \n  " + ex.getSQLState());
        }
        return res;
    }
    @Override
public boolean eliminarEmpleado(String idEmpleado2){
        boolean res=false;
        try {
            //Preparamos la funcion que va a ejecutar la eliminacion
            CallableStatement cstm = this.getConexion().prepareCall("{call eliminarEmpleado(?)}");
            //Indicas el tipo de dato que devuelve
            //Indicas el parametro que le pasas, en este caso el codigo del bar y el dni
            cstm.setString(1, idEmpleado2);
            //Ejecutas la funcion
            cstm.executeUpdate();
             //Recoges el resultado
            cstm.close();
            res=true;
            
            
        } catch (Exception e) {
        }
        return res;
    }

   
    
    
    
}
