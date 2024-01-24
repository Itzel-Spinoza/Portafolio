/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ConsultasInventario;
import Vistas.VentanaInventario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author itzel
 */
public class CtrlInventario implements ActionListener, KeyListener{
    private Inventario mInventario; 
    private ConsultasInventario conInventario; 
    private VentanaInventario visInventario;
    
    DefaultTableModel modelo = new DefaultTableModel(); 
    
    public CtrlInventario(Inventario mInventario, ConsultasInventario conInventario, VentanaInventario visInventario){
        this.mInventario = mInventario; 
        this.conInventario = conInventario; 
        this.visInventario = visInventario; 
        
        this.visInventario.btnAgregarProducto.addActionListener(this);
        this.visInventario.btnGuardar.addActionListener(this);
        this.visInventario.btnActualizarProducto.addActionListener(this);
        this.visInventario.btnBuscar.addActionListener(this);
        this.visInventario.btnCostoTotal.addActionListener(this);
        this.visInventario.btnStockTotal.addActionListener(this);
        
        this.visInventario.txtCodigoProducto.addKeyListener(this);
        this.visInventario.txtNombreProducto.addKeyListener(this); 
        this.visInventario.txtDescripcion.addKeyListener(this); 
        this.visInventario.txtPrecio.addKeyListener(this);
        this.visInventario.txtCodigoProveedor.addKeyListener(this); 
        this.visInventario.txtCodigoCategoria.addKeyListener(this);
        this.visInventario.txtCodigoProductoBus.addKeyListener(this);
        this.visInventario.txtCodigoProductoBus.addKeyListener(this);
        this.visInventario.tablaInventario.addKeyListener(this);
    }
    
    public void iniciarInv(){
        visInventario.setTitle("Inventario");
        visInventario.setLocationRelativeTo(null);
        visInventario.setVisible(true);
        listar(visInventario.tablaInventario);
    }      

    public void listar(JTable tablaInventario) {
        centrarCeldas(tablaInventario);
        modelo = (DefaultTableModel) tablaInventario.getModel();
        tablaInventario.setModel(modelo);
        List<Inventario> lista = (List<Inventario>) conInventario.listar();
        Object[] objeto = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getCodigoProducto();
            objeto[1] = lista.get(i).getNombreProducto();
            objeto[2] = lista.get(i).getDescripcion();
            objeto[3] = lista.get(i).getStock();
            objeto[4] = lista.get(i).getPrecio();
            objeto[5] = lista.get(i).getCodigoProveedor();
            objeto[6] = lista.get(i).getClaveCategoria();
            modelo.addRow(objeto);
        }
        tablaInventario.setRowHeight(35);
        tablaInventario.setRowMargin(10);

    }
    
    
    void centrarCeldas(JTable tablaInventario) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < visInventario.tablaInventario.getColumnCount(); i++) {
            tablaInventario.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    public void limpiar(){
        visInventario.txtCodigoProducto.setText(null);
        visInventario.txtNombreProducto.setText(null);
        visInventario.txtDescripcion.setText(null);
        
        visInventario.txtPrecio.setText(null);
        visInventario.txtCodigoProveedor.setText(null);
        visInventario.txtCodigoCategoria.setText(null);
        
    }    
    public void limpiarTabla(){
        for (int i = 0; i < visInventario.tablaInventario.getRowCount(); i++) {
            modelo.removeRow(i);
            i-=1;
        }
    }    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == visInventario.btnAgregarProducto){
            limpiar(); 
            if(conInventario.hacerCodigo(mInventario)){
                visInventario.txtCodigoProducto.setText(mInventario.getCodigoProducto());
                limpiarTabla(); 
                listar(visInventario.tablaInventario);
            } else{
                JOptionPane.showMessageDialog(null, "No se pudo generar el código del producto");
            }
        }
        
        if(e.getSource() == visInventario.btnGuardar){
            mInventario.setCodigoProducto(visInventario.txtCodigoProducto.getText());
            mInventario.setNombreProducto(visInventario.txtNombreProducto.getText());
            mInventario.setDescripcion(visInventario.txtDescripcion.getText());
            mInventario.setStock((int) visInventario.spinStock.getValue());
            mInventario.setPrecio(Float.parseFloat(visInventario.txtPrecio.getText()));
            mInventario.setCodigoProveedor(visInventario.txtCodigoProveedor.getText());
            mInventario.setClaveCategoria(visInventario.txtCodigoCategoria.getText());             
            if(conInventario.agregarProducto(mInventario)){
                JOptionPane.showMessageDialog(null, "Registo Guardado");
                limpiar();
                limpiarTabla(); 
                listar(visInventario.tablaInventario);
                
            } else{
                JOptionPane.showMessageDialog(null, "Error al Guardar");
 
            }                
            
        }
        
        /*if(e.getSource()==visInventario.btnEliminarProducto){
            if(conInventario.eliminar(mInventario)){
                mInventario.setCodigoProducto(visInventario.txtCodigoProducto.getText());
            
                if(conInventario.eliminar(mInventario)){
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    limpiar();
                } else{
                    JOptionPane.showMessageDialog(null, "Error al Eliminar");
                    limpiar();                
                } 
            limpiarTabla(); 
            listar(visInventario.tablaInventario);            
        }           
        }*/
       
        
       if(e.getSource() == visInventario.btnActualizarProducto){
            mInventario.setCodigoProducto(visInventario.txtCodigoProducto.getText());
            mInventario.setNombreProducto(visInventario.txtNombreProducto.getText());
            mInventario.setDescripcion(visInventario.txtDescripcion.getText());
            mInventario.setStock((int) visInventario.spinStock.getValue());
            mInventario.setPrecio(Float.parseFloat(visInventario.txtPrecio.getText()));
            mInventario.setCodigoProveedor(visInventario.txtCodigoProveedor.getText());
            mInventario.setClaveCategoria(visInventario.txtCodigoCategoria.getText()); 

            if(conInventario.actualizar(mInventario)){
                JOptionPane.showMessageDialog(null, "Registo Modificado");
                limpiar();
            } else{
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();                
            }
            
            limpiarTabla(); 
            listar(visInventario.tablaInventario);
            limpiar();
        }
       
       if(e.getSource() == visInventario.btnBuscar){
           mInventario.setCodigoProducto(visInventario.txtCodigoProductoBus.getText());
           if(conInventario.buscar(mInventario)){
               visInventario.txtCodigoProducto.setText(mInventario.getCodigoProducto());
               visInventario.txtNombreProducto.setText(mInventario.getNombreProducto());
               visInventario.txtDescripcion.setText(mInventario.getDescripcion());
               visInventario.spinStock.setValue(mInventario.getStock());
               visInventario.txtPrecio.setText(String.valueOf(mInventario.getPrecio()));
               visInventario.txtCodigoProveedor.setText(mInventario.getCodigoProveedor());
               visInventario.txtCodigoCategoria.setText(mInventario.getClaveCategoria());
           }
       }
       
       if(e.getSource() == visInventario.btnCostoTotal){
           if(conInventario.valorTotalInv(mInventario)){
               float valortot = mInventario.getPrecio();
               JDialog dialog = new JDialog(visInventario, "Stock total");
               JLabel valor = new JLabel("El valor total del inventaro es de: $" + String.valueOf(valortot));
               dialog.add(valor); 
               dialog.setSize(300,300); 
               dialog.setVisible(true);
               
               
           }
       }
       
       if(e.getSource() == visInventario.btnStockTotal){
           if(conInventario.cantidadStock(mInventario)){
               int valortot = mInventario.getStock();
               JDialog dialog = new JDialog(visInventario, "Stock total");
               JLabel cantidad = new JLabel("La cantidad total de inventario es de: " + String.valueOf(valortot));
               dialog.add(cantidad); 
               dialog.setSize(300,300); 
               dialog.setVisible(true);               
           }
       }
    }

    @Override
    public void keyTyped(KeyEvent e) {       
        
        if(e.getSource() == visInventario.txtNombreProducto|| e.getSource() == visInventario.txtDescripcion ){
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
        if(e.getSource() == visInventario.txtPrecio){
            char c = e.getKeyChar();
            if(c >= 33 && c <= 45
                     || c >= 58 && c <= 8482){
                e.consume(); 
                JOptionPane.showMessageDialog(null, "Solamente se pueden ingresar numeros");
            }
        }   
        
        if(e.getSource() == visInventario.txtCodigoProducto || e.getSource() == visInventario.txtCodigoProveedor || e.getSource() == visInventario.txtCodigoCategoria || e.getSource() == visInventario.txtCodigoProductoBus){
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
        
        if(e.getSource() == visInventario.txtCodigoProducto){
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
            int seleccionar = visInventario.tablaInventario.getSelectedRow();
            if (seleccionar == -1) {
                JOptionPane.showMessageDialog(null, "No se selecciono ninguna fila");

           }else{
                visInventario.txtCodigoProducto.setText(String.valueOf(visInventario.tablaInventario.getValueAt(seleccionar, 0))); 
                visInventario.txtNombreProducto.setText(String.valueOf(visInventario.tablaInventario.getValueAt(seleccionar, 1)));
                visInventario.txtDescripcion.setText(String.valueOf(visInventario.tablaInventario.getValueAt(seleccionar, 2)));
                visInventario.txtPrecio.setText(String.valueOf(visInventario.tablaInventario.getValueAt(seleccionar, 4)));
                visInventario.txtCodigoProveedor.setText(String.valueOf(visInventario.tablaInventario.getValueAt(seleccionar, 5)));
                visInventario.txtCodigoCategoria.setText(String.valueOf(visInventario.tablaInventario.getValueAt(seleccionar, 6))); 
            }            
        }          
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
