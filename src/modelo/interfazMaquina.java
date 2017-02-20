/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public interface interfazMaquina {
    public DefaultTableModel listarMaquinas();
    public boolean añadirMaquina(int idClase, String descripcion);
    public DefaultComboBoxModel comboClases();
    public boolean eliminarMaquina(int idMaquina);
    public boolean modificarMaquina(int idMaquina, int idClase, String descripcion);
}
