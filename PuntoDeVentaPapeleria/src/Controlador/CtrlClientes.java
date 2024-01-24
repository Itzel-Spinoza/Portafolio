/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ConsultasClientes;
import Vistas.VentanaClientes;
import static java.awt.PageAttributes.MediaType.C;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author itzel
 */
public class CtrlClientes implements ActionListener, KeyListener{
    private Clientes mClientes; 
    private ConsultasClientes conClientes; 
    private VentanaClientes visClientes; 
    DefaultTableModel modelo = new DefaultTableModel();    
    public CtrlClientes(Clientes mClientes, ConsultasClientes conClientes, VentanaClientes visClientes){
       this.mClientes = mClientes; 
       this.conClientes = conClientes; 
       this.visClientes = visClientes; 

       this.visClientes.btnAgregarCliente.addActionListener(this);
       this.visClientes.btnActualizarCliente.addActionListener(this);
       this.visClientes.btnGuardar.addActionListener(this);     
       this.visClientes.btnBuscar.addActionListener(this); 
       
       this.visClientes.txtNoCliente.addKeyListener(this);
       this.visClientes.txtNoClienteBus.addKeyListener(this);
       this.visClientes.txtNombre.addKeyListener(this);
       this.visClientes.txtApellidoPat.addKeyListener(this); 
       this.visClientes.txtApellidoMat.addKeyListener(this); 
       this.visClientes.txtNoExt.addKeyListener(this); 
       this.visClientes.txtCP.addKeyListener(this); 
       this.visClientes.txtColonia.addKeyListener(this);
       this.visClientes.txtEstado.addKeyListener(this);
       this.visClientes.txtMunicipio.addKeyListener(this); 
       this.visClientes.txtTelefono.addKeyListener(this); 
       this.visClientes.tablaClientes.addKeyListener(this);
       
    } 
    
    public void iniciarCli(){
        visClientes.setTitle("Clientes");
        visClientes.setLocationRelativeTo(null);
        visClientes.setVisible(true);
        listar(visClientes.tablaClientes);
    }  
    
    public void listar(JTable tablaClientes) {
        centrarCeldas(tablaClientes);
        modelo = (DefaultTableModel) tablaClientes.getModel();
        tablaClientes.setModel(modelo);
        List<Clientes> lista = (List<Clientes>) conClientes.listar();
        Object[] objeto = new Object[11];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getNoCliente();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getApellidoPat();
            objeto[3] = lista.get(i).getApellidoMat();
            objeto[4] = lista.get(i).getCalle();
            objeto[5] = lista.get(i).getNoExt();
            objeto[6] = lista.get(i).getColonia();
            objeto[7] = lista.get(i).getCP();
            objeto[8] = lista.get(i).getMunicipio();
            objeto[9] = lista.get(i).getEstado();
            objeto[10] = lista.get(i).getNoTelefono();
     
            modelo.addRow(objeto);
        }
        tablaClientes.setRowHeight(35);
        tablaClientes.setRowMargin(10);

    }
    void centrarCeldas(JTable tablaClientes) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < visClientes.tablaClientes.getColumnCount(); i++) {
            tablaClientes.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }  

    public void limpiarTabla(){
        for (int i = 0; i < visClientes.tablaClientes.getRowCount(); i++) {
            modelo.removeRow(i);
            i-=1;
        }
    } 
    
        public void limpiar(){
        visClientes.txtNoCliente.setText(null);
        visClientes.txtNombre.setText(null); 
        visClientes.txtApellidoPat.setText(null); 
        visClientes.txtApellidoMat.setText(null);
        visClientes.txtCalle.setText(null);
        visClientes.txtCalle.setText(null);
        visClientes.txtColonia.setText(null);
        visClientes.txtNoExt.setText(null); 
        visClientes.txtCP.setText(null);
        visClientes.txtMunicipio.setText(null);
        visClientes.txtEstado.setText(null);
        visClientes.txtTelefono.setText(null);

    }   
    
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == visClientes.btnAgregarCliente){
            limpiar();
            if(conClientes.hacerCodigoClientes(mClientes)){
                visClientes.txtNoCliente.setText(mClientes.getNoCliente()); 
                limpiarTabla();
                listar(visClientes.tablaClientes); 
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo generar el código del empleados");
            }
        }
        
        if(e.getSource() == visClientes.btnGuardar){
            mClientes.setNoCliente(visClientes.txtNoCliente.getText());
            mClientes.setNombre(visClientes.txtNombre.getText());
            mClientes.setApellidoPat(visClientes.txtApellidoPat.getText());
            mClientes.setApellidoMat(visClientes.txtApellidoMat.getText());
            mClientes.setCalle(visClientes.txtCalle.getText()); 
            mClientes.setNoExt(visClientes.txtNoExt.getText());
            mClientes.setColonia(visClientes.txtColonia.getText());
            mClientes.setCP(visClientes.txtCP.getText());
            mClientes.setMunicipio(visClientes.txtMunicipio.getText());
            mClientes.setEstado(visClientes.txtEstado.getText());
            mClientes.setNoTelefono(visClientes.txtTelefono.getText());
            if(conClientes.agregarCliente(mClientes)){
                JOptionPane.showMessageDialog(null, "Registo Guardado");
                limpiar();
                limpiarTabla(); 
                listar(visClientes.tablaClientes);
                
            } else{
                JOptionPane.showMessageDialog(null, "Error al Guardar");
 
            }                      
        }
        
        if(e.getSource() == visClientes.btnActualizarCliente){
            mClientes.setNoCliente(visClientes.txtNoCliente.getText());
            mClientes.setNombre(visClientes.txtNombre.getText());
            mClientes.setApellidoPat(visClientes.txtApellidoPat.getText());
            mClientes.setApellidoMat(visClientes.txtApellidoMat.getText());
            mClientes.setCalle(visClientes.txtCalle.getText()); 
            mClientes.setNoExt(visClientes.txtNoExt.getText());
            mClientes.setColonia(visClientes.txtColonia.getText());
            mClientes.setCP(visClientes.txtCP.getText());
            mClientes.setMunicipio(visClientes.txtMunicipio.getText());
            mClientes.setEstado(visClientes.txtEstado.getText());
            mClientes.setNoTelefono(visClientes.txtTelefono.getText());
            if(conClientes.actualizarCliente(mClientes)){
                JOptionPane.showMessageDialog(null, "Registo actualizado");
                limpiar();
                limpiarTabla(); 
                listar(visClientes.tablaClientes);
                
            } else{
                JOptionPane.showMessageDialog(null, "Error al guardar");
 
            }                   
        }
        
        if(e.getSource() == visClientes.btnBuscar){
            mClientes.setNoCliente(visClientes.txtNoClienteBus.getText());
            if(conClientes.buscar(mClientes)){
                visClientes.txtNoCliente.setText(mClientes.getNoCliente());
                visClientes.txtNombre.setText(mClientes.getNombre()); 
                visClientes.txtApellidoPat.setText(mClientes.getApellidoPat()); 
                visClientes.txtApellidoMat.setText(mClientes.getApellidoMat());
                visClientes.txtCalle.setText(mClientes.getCalle());
                visClientes.txtNoExt.setText(mClientes.getNoExt());
                visClientes.txtColonia.setText(mClientes.getColonia());
                visClientes.txtCP.setText(mClientes.getCP());
                visClientes.txtMunicipio.setText(mClientes.getMunicipio());
                visClientes.txtEstado.setText(mClientes.getEstado());
                visClientes.txtTelefono.setText(mClientes.getNoTelefono());                
            }
        }
    }    

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == visClientes.txtNombre || e.getSource() == visClientes.txtApellidoPat || e.getSource() == visClientes.txtApellidoMat || e.getSource() == visClientes.txtMunicipio || e.getSource() == visClientes.txtEstado){
            char c = e.getKeyChar();
            if(c >= 33 && c <= 64
                     || c >= 91 && c <= 96
                     || c >= 123 && c <= 208
                     || c >= 210 && c <= 240
                     || c >= 242 && c <= 255){
                e.consume(); 
                JOptionPane.showMessageDialog(null, "Solamente se pueden ingresar letras");
            }
        }
        
        if(e.getSource() == visClientes.txtNoExt || e.getSource() == visClientes.txtCP || e.getSource() == visClientes.txtTelefono ){
            char c = e.getKeyChar();
            if(c >= 33 && c <= 47
                     || c >= 58 && c <= 8482){
                e.consume(); 
                JOptionPane.showMessageDialog(null, "Solamente se pueden ingresar numeros");
            }
        } 
        
        if(e.getSource() == visClientes.txtNoClienteBus){
            char c = e.getKeyChar();
            if(c >= 33 && c <= 44 || c >= 46 && c <= 47 || c >= 91 && c <= 96 ||
                    c >= 58 && c < 64 || c >= 91 && c <= 96 || 
                    c >= 123 && c <= 208
                 || c >= 210 && c <= 240
                 || c >= 242 && c <= 255
                ){
                e.consume(); 
                JOptionPane.showMessageDialog(null, "Solamente se pueden ingresar letras, números y guiones");
            }
        }          
        if(e.getSource() == visClientes.txtNoCliente){
        if (e.getKeyChar() >= 1 && e.getKeyChar() <= 8482)
            {
                e.consume();
                JOptionPane.showMessageDialog(null, "No se puede editar");
            }
        }        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_ENTER)) {
            int seleccionar = visClientes.tablaClientes.getSelectedRow();
            if (seleccionar == -1) {
                JOptionPane.showMessageDialog(null, "No se selecciono ninguna fila");

           }else{
        
        visClientes.txtNoCliente.setText(String.valueOf(visClientes.tablaClientes.getValueAt(seleccionar, 0)));
        visClientes.txtNombre.setText(String.valueOf(visClientes.tablaClientes.getValueAt(seleccionar, 1)));
        visClientes.txtApellidoPat.setText(String.valueOf(visClientes.tablaClientes.getValueAt(seleccionar,2)));
        visClientes.txtApellidoMat.setText(String.valueOf(visClientes.tablaClientes.getValueAt(seleccionar,3)));
        visClientes.txtCalle.setText(String.valueOf(visClientes.tablaClientes.getValueAt(seleccionar,4)));
        visClientes.txtNoExt.setText(String.valueOf(visClientes.tablaClientes.getValueAt(seleccionar,5)));
        visClientes.txtColonia.setText(String.valueOf(visClientes.tablaClientes.getValueAt(seleccionar,6)));
        visClientes.txtCP.setText(String.valueOf(visClientes.tablaClientes.getValueAt(seleccionar,7)));
        visClientes.txtMunicipio.setText(String.valueOf(visClientes.tablaClientes.getValueAt(seleccionar,8)));
        visClientes.txtEstado.setText(String.valueOf(visClientes.tablaClientes.getValueAt(seleccionar,9)));
        visClientes.txtTelefono.setText(String.valueOf(visClientes.tablaClientes.getValueAt(seleccionar,10))); 
            }            
        }               
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
