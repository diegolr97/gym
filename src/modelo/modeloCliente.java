/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public class modeloCliente extends conexion implements interfazCliente {
    
    
    public modeloCliente(){
        
    }
    
    
    
  @Override
 public DefaultListModel listClases(String dni)
{
    DefaultListModel model = new DefaultListModel();
        try {
           PreparedStatement pstm = this.getConexion().prepareStatement( "select d.nombre from cliente a, matricula b, tarifa c, clase d where a.dni=b.idCliente and b.idMatricula=c.idMatricula and c.idClase=d.idClase and a.dni='" + dni + "'");
           ResultSet res = pstm.executeQuery();
            
            while (res.next()) //go through each row that your query returns
            {
                String itemCode = res.getString("d.nombre"); //get the element in column "item_code"
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
      public DefaultTableModel listarClientes()
    {
        DefaultTableModel tablemodel = new DefaultTableModel();
    
      int registros = 0;
      String[] columNames = {"DNI", "Nombre", "Apellidos", "Dirección", "Ciudad", "CodPostal", "Teléfono", "Nacimiento", "correo"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
       CallableStatement cstmt = this.getConexion().prepareCall("{call numeroClientes}");
        ResultSet res = cstmt.executeQuery();
         res.next();
         registros = res.getInt("todo");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
     Object[][] data = new String[registros][9];
     try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
        CallableStatement cstmt = this.getConexion().prepareCall("{call listarClientes}");
        ResultSet res = cstmt.executeQuery();
         int i=0;
         while(res.next()){
                
                data[i][0] = res.getString("dni");
                data[i][1] = res.getString("nombre");
                data[i][2] = res.getString("apellidos");
                data[i][3] = res.getString("direccion");
                data[i][4] = res.getString("ciudad");
                data[i][5] = res.getString("codPostal");
                data[i][6] = res.getString("telefono");
                data[i][7] = res.getString("fechaNacimiento");
                data[i][8] = res.getString("correo");
                
                     
            i++;
         }
         res.close();
        //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
        }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
        
    }
      @Override
      public boolean añadirCliente(String dni, String nombre, String apellidos, String fechaNacimiento, String direccion, int codPostal, String ciudad, int telefono, String correo){
     boolean res=false;
        
        try {
            CallableStatement cstm = this.getConexion().prepareCall("{call añadirCliente(?,?,?,?,?,?,?,?,?)}");
            
            cstm.setString(1, dni);
            cstm.setString(2, nombre);
            cstm.setString(3, apellidos);
            cstm.setString(4, fechaNacimiento);
            cstm.setString(5, direccion);
            cstm.setInt(6, codPostal);
            cstm.setString(7, ciudad);
            cstm.setInt(8, telefono);
            cstm.setString(9, correo);
            cstm.executeUpdate();
            cstm.close();
            res=true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage() + "     \n  " + ex.getSQLState());
        }
        return res;
        }
     
      public boolean eliminarCliente(String dni){
        boolean res=false;
        try {
            //Preparamos la funcion que va a ejecutar la eliminacion
            CallableStatement cstm = this.getConexion().prepareCall("{call eliminarCliente(?)}");
            //Indicas el tipo de dato que devuelve
            //Indicas el parametro que le pasas, en este caso el codigo del bar y el dni
            cstm.setString(1, dni);
            //Ejecutas la funcion
            cstm.executeUpdate();
             //Recoges el resultado
            cstm.close();
            res=true;
            
            
        } catch (Exception e) {
        }
        return res;
    }
      @Override
      public boolean modificarClienteTable(String dni, String nombre, String apellidos, String fecha, String direccion, int codPostal, String ciudad, int telefono, String correo){
     boolean res=false;
        
        try {
            CallableStatement cstm = this.getConexion().prepareCall("{call modificarCliente(?,?,?,?,?,?,?,?,?)}");
            
            cstm.setString(1, dni);
            cstm.setString(2, nombre);
            cstm.setString(3, apellidos);
            cstm.setString(4, fecha);
            cstm.setString(5, direccion);
            cstm.setInt(6, codPostal);
            cstm.setString(7, ciudad);
            cstm.setInt(8, telefono);
            cstm.setString(9, correo);
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
      public DefaultTableModel listarClientesLetra(String nombre2)
    {
      
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"DNI", "Nombre", "Apellidos", "Dirección", "Ciudad", "CodPostal", "Teléfono", "Nacimiento", "correo"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as todo FROM cliente where nombre like'"+nombre2+"%'");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("todo");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][9];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM cliente where nombre like'"+nombre2+"%'");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                
                data[i][0] = res.getString("dni");
                data[i][1] = res.getString("nombre");
                data[i][2] = res.getString("apellidos");
                data[i][3] = res.getString("direccion");
                data[i][4] = res.getString("ciudad");
                data[i][5] = res.getString("codPostal");
                data[i][6] = res.getString("telefono");
                data[i][7] = res.getString("fechaNacimiento");
                data[i][8] = res.getString("correo");
                    
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
        
    }
        
    }
      
      


 




    
    

