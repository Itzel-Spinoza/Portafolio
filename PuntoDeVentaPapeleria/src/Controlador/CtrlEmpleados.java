/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ConsultasEmpleados;
import Vistas.VentanaEmpleados;
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
public class CtrlEmpleados implements ActionListener, KeyListener{
    private Empleados mEmpleados; 
    private ConsultasEmpleados conEmpleados; 
    private VentanaEmpleados visEmpleados;
    DefaultTableModel modelo = new DefaultTableModel();    
    
    public CtrlEmpleados(Empleados mEmpleados, ConsultasEmpleados conEmpleados, VentanaEmpleados visEmpleados){
       this.mEmpleados = mEmpleados; 
       this.conEmpleados = conEmpleados; 
       this.visEmpleados = visEmpleados; 

       this.visEmpleados.btnAgregarEmpleado.addActionListener(this);
       this.visEmpleados.btnActualizarEmpleado.addActionListener(this);
       this.visEmpleados.btnGuardar.addActionListener(this); 
       this.visEmpleados.btnBuscar.addActionListener(this);
       
       this.visEmpleados.txtNoEmpleado.addKeyListener(this);
       this.visEmpleados.txtNoEmpleadoBus.addKeyListener(this);
       this.visEmpleados.txtNombre.addKeyListener(this);
       this.visEmpleados.txtApellidoPat.addKeyListener(this); 
       this.visEmpleados.txtApellidoMat.addKeyListener(this); 
       this.visEmpleados.txtNoExt.addKeyListener(this); 
       this.visEmpleados.txtCP.addKeyListener(this); 
       this.visEmpleados.txtColonia.addKeyListener(this);
       this.visEmpleados.txtEstado.addKeyListener(this);
       this.visEmpleados.txtMunicipio.addKeyListener(this); 
       this.visEmpleados.txtTelefono.addKeyListener(this); 
       this.visEmpleados.txtSueldo.addKeyListener(this);
       this.visEmpleados.txtPuesto.addKeyListener(this);
       this.visEmpleados.txtCURP.addKeyListener(this);
       this.visEmpleados.tablaEmpleados.addKeyListener(this);
    }    
    public void iniciarEmp(){
        visEmpleados.setTitle("Empleados");
        visEmpleados.setLocationRelativeTo(null);
        visEmpleados.setVisible(true);
        listar(visEmpleados.tablaEmpleados);
    }     
    public void listar(JTable tablaEmpleados) {
        centrarCeldas(tablaEmpleados);
        modelo = (DefaultTableModel) tablaEmpleados.getModel();
        tablaEmpleados.setModel(modelo);
        List<Empleados> lista = (List<Empleados>) conEmpleados.listarEmpleados();
        Object[] objeto = new Object[14];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getNoEmpleado();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getApellidoPat();
            objeto[3] = lista.get(i).getApellidoMat();
            objeto[4] = lista.get(i).getCalle();
            objeto[5] = lista.get(i).getNoExterior();
            objeto[6] = lista.get(i).getColonia();
            objeto[7] = lista.get(i).getCP();
            objeto[8] = lista.get(i).getMunicipio();
            objeto[9] = lista.get(i).getEstado();
            objeto[10] = lista.get(i).getSueldo();
            objeto[11] = lista.get(i).getPuesto();
            objeto[12] = lista.get(i).getCURP();
            objeto[13] = lista.get(i).getNoTelefono();
            
            modelo.addRow(objeto);
        }
        tablaEmpleados.setRowHeight(35);
        tablaEmpleados.setRowMargin(10);

    }
    
    void centrarCeldas(JTable tablaEmpleados) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < visEmpleados.tablaEmpleados.getColumnCount(); i++) {
            tablaEmpleados.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }  
    public void limpiar(){
        visEmpleados.txtNoEmpleado.setText(null);
        visEmpleados.txtNombre.setText(null); 
        visEmpleados.txtApellidoPat.setText(null); 
        visEmpleados.txtApellidoMat.setText(null);
        visEmpleados.txtCalle.setText(null);
        visEmpleados.txtNoExt.setText(null);
        //visEmpleados.combo.setText(null);
        visEmpleados.txtCP.setText(null);
        visEmpleados.txtMunicipio.setText(null);
        visEmpleados.txtEstado.setText(null);
        visEmpleados.txtSueldo.setText(null);
        visEmpleados.txtCURP.setText(null);
        visEmpleados.txtTelefono.setText(null);
        visEmpleados.txtPuesto.setText(null);

    }
    public void limpiarTabla(){
        for (int i = 0; i < visEmpleados.tablaEmpleados.getRowCount(); i++) {
            modelo.removeRow(i);
            i-=1;
        }
    }   
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == visEmpleados.btnAgregarEmpleado){
            limpiar();
            if(conEmpleados.hacerCodigoEmp(mEmpleados)){
                visEmpleados.txtNoEmpleado.setText(mEmpleados.getNoEmpleado());
                limpiarTabla(); 
                listar(visEmpleados.tablaEmpleados);
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo generar el código del empleado");
            }
        }
        
        if(e.getSource() == visEmpleados.btnGuardar){
            mEmpleados.setNoEmpleado(visEmpleados.txtNoEmpleado.getText());
            mEmpleados.setNombre(visEmpleados.txtNombre.getText());
            mEmpleados.setApellidoPat(visEmpleados.txtApellidoPat.getText());
            mEmpleados.setApellidoMat(visEmpleados.txtApellidoMat.getText());
            mEmpleados.setCalle(visEmpleados.txtCalle.getText()); 
            mEmpleados.setNoExterior(visEmpleados.txtNoExt.getText());
            mEmpleados.setColonia(visEmpleados.txtColonia.getText());
            mEmpleados.setCP(visEmpleados.txtCP.getText());
            mEmpleados.setMunicipio(visEmpleados.txtMunicipio.getText());
            mEmpleados.setEstado(visEmpleados.txtEstado.getText());
            mEmpleados.setSueldo(Float.parseFloat(visEmpleados.txtSueldo.getText()));
            mEmpleados.setPuesto(visEmpleados.txtPuesto.getText());
            mEmpleados.setCURP(visEmpleados.txtCURP.getText()); 
            mEmpleados.setNoTelefono(visEmpleados.txtTelefono.getText());
            if(conEmpleados.agregarProducto(mEmpleados)){
                JOptionPane.showMessageDialog(null, "Registo Guardado");
                limpiar();
                limpiarTabla(); 
                listar(visEmpleados.tablaEmpleados);
                
            } else{
                JOptionPane.showMessageDialog(null, "Error al Guardar");
 
            }               
            
        }
        
        if(e.getSource() == visEmpleados.btnActualizarEmpleado){
            mEmpleados.setNoEmpleado(visEmpleados.txtNoEmpleado.getText());
            mEmpleados.setNombre(visEmpleados.txtNombre.getText());
            mEmpleados.setApellidoPat(visEmpleados.txtApellidoPat.getText());
            mEmpleados.setApellidoMat(visEmpleados.txtApellidoMat.getText());
            mEmpleados.setCalle(visEmpleados.txtCalle.getText()); 
            mEmpleados.setNoExterior(visEmpleados.txtNoExt.getText());
            mEmpleados.setColonia(visEmpleados.txtColonia.getText());
            mEmpleados.setCP(visEmpleados.txtCP.getText());
            mEmpleados.setMunicipio(visEmpleados.txtMunicipio.getText());
            mEmpleados.setEstado(visEmpleados.txtEstado.getText());
            mEmpleados.setSueldo(Float.parseFloat(visEmpleados.txtSueldo.getText()));
            mEmpleados.setPuesto(visEmpleados.txtPuesto.getText());
            mEmpleados.setCURP(visEmpleados.txtCURP.getText()); 
            mEmpleados.setNoTelefono(visEmpleados.txtTelefono.getText());

            if(conEmpleados.actualizarEmpleado(mEmpleados)){
                JOptionPane.showMessageDialog(null, "Registo Modificado");
                limpiar();                
            }else{
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();                
            }
            limpiarTabla(); 
            listar(visEmpleados.tablaEmpleados);
            limpiar();
        }
        if(e.getSource() == visEmpleados.btnBuscar){
            mEmpleados.setNoEmpleado(visEmpleados.txtNoEmpleadoBus.getText());
           if(conEmpleados.buscar(mEmpleados)){
                visEmpleados.txtNoEmpleado.setText(mEmpleados.getNoEmpleado());
                visEmpleados.txtNombre.setText(mEmpleados.getNombre()); 
                visEmpleados.txtApellidoPat.setText(mEmpleados.getApellidoPat()); 
                visEmpleados.txtApellidoMat.setText(mEmpleados.getApellidoMat());
                visEmpleados.txtCalle.setText(mEmpleados.getCalle());
                visEmpleados.txtNoExt.setText(mEmpleados.getNoExterior());
                visEmpleados.txtColonia.setText(mEmpleados.getColonia());
                visEmpleados.txtCP.setText(mEmpleados.getCP());
                visEmpleados.txtMunicipio.setText(mEmpleados.getMunicipio());
                visEmpleados.txtEstado.setText(mEmpleados.getEstado());
                visEmpleados.txtSueldo.setText(String.valueOf(mEmpleados.getSueldo()));
                
                visEmpleados.txtPuesto.setText(mEmpleados.getPuesto());
                visEmpleados.txtCURP.setText(mEmpleados.getCURP());
                visEmpleados.txtTelefono.setText(mEmpleados.getNoTelefono());
                
                
            } else{
                JOptionPane.showMessageDialog(null, "No se encontró registro"); 
                limpiar();
            }            
        }
                        
        
    }    

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == visEmpleados.txtNombre || e.getSource() == visEmpleados.txtApellidoPat || e.getSource() == visEmpleados.txtApellidoMat || e.getSource() == visEmpleados.txtMunicipio || e.getSource() == visEmpleados.txtEstado || e.getSource() == visEmpleados.txtPuesto){
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
        if(e.getSource() == visEmpleados.txtNoExt || e.getSource() == visEmpleados.txtCP || e.getSource() == visEmpleados.txtTelefono || e.getSource() == visEmpleados.txtSueldo){
            char c = e.getKeyChar();
            if(c >= 33 && c <= 47
                     || c >= 58 && c <= 8482){
                e.consume(); 
                JOptionPane.showMessageDialog(null, "Solamente se pueden ingresar numeros");
            }
        }    
        
        if(e.getSource() == visEmpleados.txtCURP){
            char c = e.getKeyChar();
            if(c >= 33 && c <= 64
                     || c >= 91 && c <= 96
                     || c >= 123 && c <= 208
                     || c >= 210 && c <= 240
                     || c >= 242 && c <= 255 || c >= 33 && c <= 47
                     || c >= 58 && c <= 8482){
                e.consume(); 
                JOptionPane.showMessageDialog(null, "Solamente se pueden ingresar letras y numeros");
            }           
        }
        
        if(e.getSource() == visEmpleados.txtNoEmpleadoBus){
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
        if(e.getSource() == visEmpleados.txtNoEmpleado){
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
            int seleccionar = visEmpleados.tablaEmpleados.getSelectedRow();
            if (seleccionar == -1) {
                JOptionPane.showMessageDialog(null, "No se selecciono ninguna fila");

           }else{
        
            visEmpleados.txtNoEmpleado.setText((String) visEmpleados.tablaEmpleados.getValueAt(seleccionar, 0));
            visEmpleados.txtNombre.setText((String) visEmpleados.tablaEmpleados.getValueAt(seleccionar, 1));
            visEmpleados. txtApellidoPat.setText((String) visEmpleados.tablaEmpleados.getValueAt(seleccionar, 2));
            visEmpleados.txtApellidoMat.setText((String) visEmpleados.tablaEmpleados.getValueAt(seleccionar, 3));
            visEmpleados.txtCalle.setText((String) visEmpleados.tablaEmpleados.getValueAt(seleccionar, 4)); 
            visEmpleados.txtNoExt.setText((String) visEmpleados.tablaEmpleados.getValueAt(seleccionar, 5));
            visEmpleados.txtColonia.setText((String) visEmpleados.tablaEmpleados.getValueAt(seleccionar, 6));
            visEmpleados.txtCP.setText((String) visEmpleados.tablaEmpleados.getValueAt(seleccionar, 7));
            visEmpleados.txtMunicipio.setText((String) visEmpleados.tablaEmpleados.getValueAt(seleccionar, 8));
            visEmpleados.txtEstado.setText((String) visEmpleados.tablaEmpleados.getValueAt(seleccionar, 9));
            
            visEmpleados.txtSueldo.setText(String.valueOf(visEmpleados.tablaEmpleados.getValueAt(seleccionar, 10)));
            visEmpleados.txtPuesto.setText(String.valueOf(visEmpleados.tablaEmpleados.getValueAt(seleccionar, 11)));
            visEmpleados.txtCURP.setText(String.valueOf(visEmpleados.tablaEmpleados.getValueAt(seleccionar, 12))); 
            visEmpleados.txtTelefono.setText(String.valueOf(visEmpleados.tablaEmpleados.getValueAt(seleccionar, 13)));     
            }            
        }         
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
