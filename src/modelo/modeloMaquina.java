/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public class modeloMaquina extends conexion implements interfazMaquina {
    public DefaultTableModel listarMaquinas()
    {
        DefaultTableModel tablemodel = new DefaultTableModel();
    
      int registros = 0;
      String[] columNames = {"Identificador", "Nombre", "Clase"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
       CallableStatement cstmt = this.getConexion().prepareCall("{call numeroMaquinas}");
        ResultSet res = cstmt.executeQuery();
         res.next();
         registros = res.getInt("todo");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
     Object[][] data = new String[registros][3];
     try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
        CallableStatement cstmt = this.getConexion().prepareCall("{call listarMaquinas}");
        ResultSet res = cstmt.executeQuery();
         int i=0;
         while(res.next()){
                
                data[i][0] = res.getString("idMaquina");
                data[i][1] = res.getString("descripcion");
                data[i][2] = res.getString("idClase");
                
                
                     
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
    
    public boolean añadirMaquina(int idClase, String descripcion){
     boolean res=false;
        
        try {
            CallableStatement cstm = this.getConexion().prepareCall("{call añadirMaquina(?,?)}");
            
            cstm.setInt(1, idClase);
            cstm.setString(2, descripcion);
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
    public DefaultComboBoxModel comboClases(){
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        try{
          PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM clase");
          ResultSet res = pstm.executeQuery();
          
          while(res.next()){
              m.addElement(res.getString("nombre"));
              
          }
        }catch(SQLException e){
            e.printStackTrace();
            
        }
        return m;
    }
    @Override
    public boolean eliminarMaquina(int idMaquina){
        boolean res=false;
        try {
            //Preparamos la funcion que va a ejecutar la eliminacion
            CallableStatement cstm = this.getConexion().prepareCall("{call eliminarMaquina(?)}");
            //Indicas el tipo de dato que devuelve
            //Indicas el parametro que le pasas, en este caso el codigo del bar y el dni
            cstm.setInt(1, idMaquina);
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
