/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ConsultasProveedores;
import Vistas.VentanaProveedores;
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
public class CtrlProveedores implements ActionListener, KeyListener{
    private Proveedores mProveedores; 
    private ConsultasProveedores conProveedores; 
    private VentanaProveedores visProveedores; 
    
    DefaultTableModel modelo = new DefaultTableModel();

    public CtrlProveedores(Proveedores mProveedores, ConsultasProveedores conProveedores, VentanaProveedores visProveedores){
        this.mProveedores = mProveedores; 
        this.conProveedores = conProveedores; 
        this.visProveedores = visProveedores; 
        
        this.visProveedores.btnAgregarProveedor.addActionListener(this); 
        this.visProveedores.btnActualizarProveedor.addActionListener(this);
        this.visProveedores.btnGuardar.addActionListener(this);
        this.visProveedores.btnBuscar.addActionListener(this);
        
        this.visProveedores.txtCodigoProveedor.addKeyListener(this);
        this.visProveedores.txtCodigoProveedor2.addKeyListener(this);
        this.visProveedores.txtCompania.addKeyListener(this);
        this.visProveedores.txtNombre.addKeyListener(this);
        this.visProveedores.txtApellidoPat.addKeyListener(this);
        this.visProveedores.txtApellidoMat.addKeyListener(this);
        this.visProveedores.txtNoExt.addKeyListener(this);
        this.visProveedores.txtCP.addKeyListener(this);
        this.visProveedores.txtMunicipio.addKeyListener(this); 
        this.visProveedores.txtEstado.addKeyListener(this);
        this.visProveedores.txtTelefono.addKeyListener(this); 
        this.visProveedores.txtEmail.addKeyListener(this);
        this.visProveedores.tablaProveedores.addKeyListener(this);
    }
    
    public void iniciar(){
        visProveedores.setTitle("Proveedores");
        visProveedores.setLocationRelativeTo(null);
        visProveedores.setVisible(true);
        listar(visProveedores.tablaProveedores);
    }  
   
    public void listar(JTable tablaProveedores) {
        centrarCeldas(tablaProveedores);
        modelo = (DefaultTableModel) tablaProveedores.getModel();
        tablaProveedores.setModel(modelo);
        List<Proveedores> lista = (List<Proveedores>) conProveedores.listarProveedores();
        Object[] objeto = new Object[13];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getCodigoProveedor();
            objeto[1] = lista.get(i).getCompania();
            objeto[2] = lista.get(i).getNombreContacto();
            objeto[3] = lista.get(i).getApellidoPat();
            objeto[4] = lista.get(i).getApellidoMat();
            objeto[5] = lista.get(i).getCalle();
            objeto[6] = lista.get(i).getNoExterior();
            objeto[7] = lista.get(i).getColonia();
            objeto[8] = lista.get(i).getCP();
            objeto[9] = lista.get(i).getMunicipio();
            objeto[10] = lista.get(i).getEstado();
            objeto[11] = lista.get(i).getTelefono();
            objeto[12] = lista.get(i).getEmail();
            
            modelo.addRow(objeto);
        }
        tablaProveedores.setRowHeight(35);
        tablaProveedores.setRowMargin(10);

    }
    
    void centrarCeldas(JTable tablaProveedores) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < visProveedores.tablaProveedores.getColumnCount(); i++) {
            tablaProveedores.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }  
    
    public void limpiar(){
        visProveedores.txtCodigoProveedor.setText(null);
        visProveedores.txtCompania.setText(null);
        visProveedores.txtNombre.setText(null); 
        visProveedores.txtApellidoPat.setText(null); 
        visProveedores.txtApellidoMat.setText(null);
        visProveedores.txtCalle.setText(null);
        visProveedores.txtNoExt.setText(null);
        visProveedores.txtColonia.setText(null);
        visProveedores.txtCP.setText(null);
        visProveedores.txtMunicipio.setText(null);
        visProveedores.txtEstado.setText(null);
        visProveedores.txtTelefono.setText(null);
        visProveedores.txtEmail.setText(null); 

    }
    public void limpiarTabla(){
        for (int i = 0; i < visProveedores.tablaProveedores.getRowCount(); i++) {
            modelo.removeRow(i);
            i-=1;
        }
    } 
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == visProveedores.btnAgregarProveedor){
            limpiar(); 
            if(conProveedores.hacerCodigoProv(mProveedores)){
                visProveedores.txtCodigoProveedor.setText(mProveedores.getCodigoProveedor());
                limpiarTabla(); 
                listar(visProveedores.tablaProveedores); 
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo generar el código del proveedor");
            }
            
        }
        
        if(e.getSource() == visProveedores.btnGuardar){
            mProveedores.setCodigoProveedor(visProveedores.txtCodigoProveedor.getText());
            mProveedores.setCompania(visProveedores.txtCompania.getText());
            mProveedores.setNombreContacto(visProveedores.txtNombre.getText());
            mProveedores.setApellidoPat(visProveedores.txtApellidoPat.getText());
            mProveedores.setApellidoMat(visProveedores.txtApellidoMat.getText());
            mProveedores.setCalle(visProveedores.txtCalle.getText()); 
            mProveedores.setNoExterior(visProveedores.txtNoExt.getText());
            mProveedores.setColonia(visProveedores.txtColonia.getText());
            mProveedores.setCP(visProveedores.txtCP.getText());
            mProveedores.setMunicipio(visProveedores.txtMunicipio.getText());
            mProveedores.setEstado(visProveedores.txtEstado.getText());
            mProveedores.setTelefono(visProveedores.txtTelefono.getText()); 
            mProveedores.setEmail(visProveedores.txtEmail.getText());
            if(conProveedores.agregarProveedor(mProveedores)){
                JOptionPane.showMessageDialog(null, "Registo Guardado");
                limpiar();
                limpiarTabla(); 
                listar(visProveedores.tablaProveedores);
                
            } else{
                JOptionPane.showMessageDialog(null, "Error al Guardar");
 
            }                  
        }
        
        if(e.getSource() == visProveedores.btnActualizarProveedor){
            mProveedores.setCodigoProveedor(visProveedores.txtCodigoProveedor.getText());
            mProveedores.setCompania(visProveedores.txtCompania.getText());
            mProveedores.setNombreContacto(visProveedores.txtNombre.getText());
            mProveedores.setApellidoPat(visProveedores.txtApellidoPat.getText());
            mProveedores.setApellidoMat(visProveedores.txtApellidoMat.getText());
            mProveedores.setCalle(visProveedores.txtCalle.getText()); 
            mProveedores.setNoExterior(visProveedores.txtNoExt.getText());
            mProveedores.setColonia(visProveedores.txtColonia.getText());
            mProveedores.setCP(visProveedores.txtCP.getText());
            mProveedores.setMunicipio(visProveedores.txtMunicipio.getText());
            mProveedores.setEstado(visProveedores.txtEstado.getText());
            mProveedores.setTelefono(visProveedores.txtTelefono.getText()); 
            mProveedores.setEmail(visProveedores.txtEmail.getText());
            if(conProveedores.actualizarProveedor(mProveedores)){
                JOptionPane.showMessageDialog(null, "Registo Modificado");
                limpiar();
                limpiarTabla(); 
                listar(visProveedores.tablaProveedores);
                
            } else{
                JOptionPane.showMessageDialog(null, "Error al Modificar");
 
            }                   
        }
        
        if(e.getSource() == visProveedores.btnBuscar){
            mProveedores.setCodigoProveedor(visProveedores.txtCodigoProveedor2.getText());
            if(conProveedores.buscar(mProveedores)){
                visProveedores.txtCodigoProveedor.setText(mProveedores.getCodigoProveedor());
                visProveedores.txtCompania.setText(mProveedores.getCompania()); 
                visProveedores.txtNombre.setText(mProveedores.getNombreContacto()); 
                visProveedores.txtApellidoPat.setText(mProveedores.getApellidoPat()); 
                visProveedores.txtApellidoMat.setText(mProveedores.getApellidoMat());
                visProveedores.txtCalle.setText(mProveedores.getCalle());
                visProveedores.txtNoExt.setText(mProveedores.getNoExterior());
                visProveedores.txtColonia.setText(mProveedores.getColonia());
                visProveedores.txtCP.setText(mProveedores.getCP());
                visProveedores.txtMunicipio.setText(mProveedores.getMunicipio());
                visProveedores.txtEstado.setText(mProveedores.getEstado());
                visProveedores.txtTelefono.setText(mProveedores.getTelefono());  
                visProveedores.txtEmail.setText(mProveedores.getEmail());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {    
        
        if(e.getSource() == visProveedores.txtCompania || e.getSource() == visProveedores.txtNombre || e.getSource() == visProveedores.txtApellidoPat || e.getSource() == visProveedores.txtApellidoMat || e.getSource() == visProveedores.txtMunicipio || e.getSource() == visProveedores.txtEstado){
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
        
        if(e.getSource() == visProveedores.txtNoExt || e.getSource() == visProveedores.txtCP || e.getSource() == visProveedores.txtTelefono ){
            char c = e.getKeyChar();
            if(c >= 33 && c <= 47
                     || c >= 58 && c <= 8482){
                e.consume(); 
                JOptionPane.showMessageDialog(null, "Solamente se pueden ingresar numeros");
            }
        } 
        
        if(e.getSource() == visProveedores.txtCodigoProveedor){
        if (e.getKeyChar() >= 1 && e.getKeyChar() <= 8482)
            {
                e.consume();
                JOptionPane.showMessageDialog(null, "No se puede editar");
            }
        }      
        if(e.getSource() == visProveedores.txtCodigoProveedor2){
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
        
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_ENTER)) {
            int seleccionar = visProveedores.tablaProveedores.getSelectedRow();
            if (seleccionar == -1) {
                JOptionPane.showMessageDialog(null, "No se selecciono ninguna fila");

           }else{
            visProveedores.txtCodigoProveedor.setText((String) visProveedores.tablaProveedores.getValueAt(seleccionar, 0));
            visProveedores.txtCompania.setText((String) visProveedores.tablaProveedores.getValueAt(seleccionar, 1));
            visProveedores.txtNombre.setText((String) visProveedores.tablaProveedores.getValueAt(seleccionar, 2));
            visProveedores.txtApellidoPat.setText((String) visProveedores.tablaProveedores.getValueAt(seleccionar, 3));
            visProveedores.txtApellidoMat.setText((String) visProveedores.tablaProveedores.getValueAt(seleccionar, 4));
            visProveedores.txtCalle.setText((String) visProveedores.tablaProveedores.getValueAt(seleccionar, 5)); 
            visProveedores.txtNoExt.setText((String) visProveedores.tablaProveedores.getValueAt(seleccionar, 6));
            visProveedores.txtColonia.setText((String) visProveedores.tablaProveedores.getValueAt(seleccionar, 7));
            visProveedores.txtCP.setText((String) visProveedores.tablaProveedores.getValueAt(seleccionar, 8));
            visProveedores.txtMunicipio.setText((String) visProveedores.tablaProveedores.getValueAt(seleccionar, 9));
            visProveedores.txtTelefono.setText(String.valueOf(visProveedores.tablaProveedores.getValueAt(seleccionar, 10)));         
            visProveedores.txtEmail.setText(String.valueOf(visProveedores.tablaProveedores.getValueAt(seleccionar, 11)));                
            }            
        }          
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
