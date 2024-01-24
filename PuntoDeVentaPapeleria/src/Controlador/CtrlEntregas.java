/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ConsultasEntregas;
import Vistas.VentanaEntregas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

/**
 *
 * @author itzel
 */
public class CtrlEntregas implements ActionListener, KeyListener{
    private Entregas mEntregas; 
    private ConsultasEntregas conEntregas; 
    private VentanaEntregas visEntregas; 
    
    Clientes clientes = new Clientes(); 
    DefaultTableModel modelo = new DefaultTableModel(); 
    DefaultTableModel modelo2 = new DefaultTableModel(); 
    
    public CtrlEntregas(Entregas mEntregas, ConsultasEntregas conEntregas, VentanaEntregas visEntregas){
        this.mEntregas = mEntregas; 
        this.conEntregas = conEntregas; 
        this.visEntregas = visEntregas; 
        
        this.visEntregas.btnVerProductos.addActionListener(this);
        this.visEntregas.btnActualizar.addActionListener(this);
        
       
        this.visEntregas.btnDireccion.addActionListener(this);
        
        this.visEntregas.txtNoEntrega.addKeyListener(this); 
        this.visEntregas.txtFechaPedido.addKeyListener(this); 
        this.visEntregas.txtFechaEntrega.addKeyListener(this); 
        this.visEntregas.txtTotalEntrega.addKeyListener(this);
        this.visEntregas.txtNoEmpleado.addKeyListener(this); 
        this.visEntregas.txtNoCliente.addKeyListener(this);
        this.visEntregas.txtNoCliente2.addKeyListener(this); 
        this.visEntregas.txtTipoEnvio.addKeyListener(this); 
        this.visEntregas.txtMetodoPago.addKeyListener(this); 
        
        this.visEntregas.txtNoCliente2.addKeyListener(this);
        this.visEntregas.txtNombre.addKeyListener(this);
        this.visEntregas.txtApellidoPat.addKeyListener(this); 
        this.visEntregas.txtApellidoMat.addKeyListener(this);
        this.visEntregas.txtCalle.addKeyListener(this);
        this.visEntregas.txNoExt.addKeyListener(this); 
        this.visEntregas.txtColonia.addKeyListener(this); 
        this.visEntregas.txtCP.addKeyListener(this);
        this.visEntregas.txtMunicipio.addKeyListener(this);   
        this.visEntregas.txtEstado.addKeyListener(this); 
        this.visEntregas.txtSubtotal.addKeyListener(this);
        this.visEntregas.tablaEntregas.addKeyListener(this);
       
        
        
    }
    
    public void Iniciar(){
        visEntregas.setTitle("Entregas");
        visEntregas.setLocationRelativeTo(null);
        visEntregas.setVisible(true);
        listar(visEntregas.tablaEntregas);
        listarProductos(visEntregas.tablaProductos);
           
        
    }  
    
    
    public void listar(JTable tablaEntregas){
        centrarCeldas(tablaEntregas);
        modelo = (DefaultTableModel) tablaEntregas.getModel();
        tablaEntregas.setModel(modelo);
        List<Entregas> lista = (List<Entregas>) conEntregas.listarEntregas();
        Object[] objeto = new Object[9];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getNoEntrega();
            objeto[1] = lista.get(i).getFechaPedido();
            objeto[2] = lista.get(i).getFechaEntrega();
            objeto[3] = lista.get(i).getTotalEntrega();
            objeto[4] = lista.get(i).getNoEmpleado();
            objeto[5] = lista.get(i).getNoCliente(); 
            objeto[6] = lista.get(i).getTipoEnvio(); 
            objeto[7] = lista.get(i).getMetodoPago();
            objeto[8] = lista.get(i).getEstatus();  
            

            modelo.addRow(objeto);
        }
        tablaEntregas.setRowHeight(35);
        tablaEntregas.setRowMargin(10);        
    }
    
    void centrarCeldas(JTable tablaEntregas) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < visEntregas.tablaEntregas.getColumnCount(); i++) {
            tablaEntregas.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }   

    public void limpiar(){
        for (int i = 0; i < visEntregas.tablaEntregas.getRowCount(); i++) {
            modelo.removeRow(i);
            i-=1;
        }
    }      
    public void listarProductos(JTable tablaProductos){
        centrarCeldas2(tablaProductos);
        modelo2 = (DefaultTableModel) tablaProductos.getModel();
        tablaProductos.setModel(modelo2);
        
        List<Entregas> lista = (List<Entregas>)conEntregas.listarProductosEntregas();
        Object[] objeto = new Object[6];
        for (int i = 0; i < lista.size(); i++){
            objeto[0] = lista.get(i).getNoEntrega();
            objeto[1] = lista.get(i).getCodigoProducto();
            objeto[2] = lista.get(i).getNombreProducto();
            objeto[3] = lista.get(i).getCantidadE();
            objeto[4] = lista.get(i).getPrecioProductoE();
            objeto[5] = lista.get(i).getSubtotalE(); 
            modelo2.addRow(objeto);
        }
        tablaProductos.setRowHeight(35);
        tablaProductos.setRowMargin(10);  
    }
    
    void centrarCeldas2(JTable tablaProductos) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < visEntregas.tablaProductos.getColumnCount(); i++) {
            tablaProductos.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    } 
    
    public void filtrarProductosEnt(String noEntrega, JTable tablaProductos){
        modelo2 = (DefaultTableModel) tablaProductos.getModel(); 
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(modelo2);
        tablaProductos.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(noEntrega));
    
    }    
    
    public void actionPerformed(ActionEvent e){
    
        
        if(e.getSource() == visEntregas.btnDireccion){
            clientes.setNoCliente(visEntregas.txtNoCliente.getText());
            if(conEntregas.datosClientes(clientes)){
                visEntregas.txtNoCliente2.setText(String.valueOf(clientes.getNoCliente()));
                visEntregas.txtNombre.setText(String.valueOf(clientes.getNombre()));
                visEntregas.txtApellidoPat.setText(String.valueOf(clientes.getApellidoPat())); 
                visEntregas.txtApellidoMat.setText(String.valueOf(clientes.getApellidoMat()));
                visEntregas.txtCalle.setText(String.valueOf(clientes.getCalle()));
                visEntregas.txNoExt.setText(String.valueOf(clientes.getNoExt())); 
                visEntregas.txtColonia.setText(String.valueOf(clientes.getColonia())); 
                visEntregas.txtCP.setText(String.valueOf(clientes.getCP()));
                visEntregas.txtMunicipio.setText(String.valueOf(clientes.getMunicipio()));   
                visEntregas.txtEstado.setText(String.valueOf(clientes.getEstado())); 
            }
        }
        
        if(e.getSource() == visEntregas.btnVerProductos){
            String noentre; 
            noentre = visEntregas.txtNoEntrega.getText();
            visEntregas.lblNoEntrega.setText(noentre);
            filtrarProductosEnt(visEntregas.txtNoEntrega.getText(), visEntregas.tablaProductos);
            mEntregas.setNoEntrega(visEntregas.txtNoEntrega.getText());
            if(conEntregas.subtotalTotalEntregas(mEntregas)){
                visEntregas.txtSubtotal.setText(String.valueOf(mEntregas.getSubtotalE()));
                
            }
        }
        
        if(e.getSource() == visEntregas.btnActualizar){
            mEntregas.setNoEntrega(visEntregas.txtNoEntrega.getText());
            mEntregas.setFechaEntrega(visEntregas.txtFechaEntrega.getText());

            if(conEntregas.actualizarEntregas(mEntregas)){
                String Estatus = visEntregas.comboEstatus.getSelectedItem().toString(); 
                if(Estatus.equals("Procesando")){
                    mEntregas.setNoEntrega(visEntregas.txtNoEntrega.getText());
                    conEntregas.estadoProcesando(mEntregas);
                }
                else{
                    if(Estatus.equals("No Entregado")){
                        mEntregas.setNoEntrega(visEntregas.txtNoEntrega.getText());
                        conEntregas.estadoNoEntre(mEntregas);
                    }   
                    else{
                        if(Estatus.equals("En proceso")){
                            mEntregas.setNoEntrega(visEntregas.txtNoEntrega.getText());
                            conEntregas.estadoEnProceso(mEntregas);
                        }     
                        else{
                            if(Estatus.equals("Entregado")){
                                mEntregas.setNoEntrega(visEntregas.txtNoEntrega.getText());
                                conEntregas.estadoEntre(mEntregas);
                            }                              
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Registo Modificado");    
                limpiar();
                listar(visEntregas.tablaEntregas);
            }
            else{
                JOptionPane.showMessageDialog(null, "Error al Modificar");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        /*if(e.getSource() == visEntregas.txtNoCliente2){
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
        
        if(e.getSource() == visEntregas.txtTotalEntrega ){
            char c = e.getKeyChar();
            if(c >= 33 && c <= 45
                     || c >= 58 && c <= 8482){
                e.consume(); 
                JOptionPane.showMessageDialog(null, "Solamente se pueden ingresar numeros");
            }
        }   
        
        if(e.getSource() == visEntregas.txtTipoEnvio || e.getSource() == visEntregas.txtMetodoPago ){
            char c = e.getKeyChar();
            if(c >= 33 && c <= 64
                     || c >= 91 && c <= 96
                     || c >= 123 && c <= 208
                     || c >= 210 && c <= 240
                     || c >= 242 && c <= 255){
                e.consume(); 
                JOptionPane.showMessageDialog(null, "Solamente se pueden ingresar letras");
            }
        }*/
        
        if(e.getSource() == visEntregas.txtFechaEntrega){
        if (e.getKeyChar() >= 32 && e.getKeyChar() <= 44
            || e.getKeyChar() >= 46 && e.getKeyChar() <= 47
            || e.getKeyChar() >= 58 && e.getKeyChar() <= 8482)
            {
                e.consume();
                JOptionPane.showMessageDialog(null, "Caracteres no permitidos, recuerde que la fecha lleva el fromato \"yyyy-MM-dd\"");
            }
        } 
       
        if(e.getSource() == visEntregas.txtNoEntrega || e.getSource() == visEntregas.txtNoCliente || e.getSource() == visEntregas.txtNoCliente2 || e.getSource() == visEntregas.txtNoEmpleado
                || e.getSource() == visEntregas.txtTotalEntrega || e.getSource() == visEntregas.txtTipoEnvio || e.getSource() == visEntregas.txtMetodoPago || e.getSource() == visEntregas.txtFechaPedido
                || e.getSource() == visEntregas.txtNombre || e.getSource() ==  visEntregas.txtApellidoPat|| e.getSource() == visEntregas.txtApellidoMat
                || e.getSource() == visEntregas.txtCalle || e.getSource() == visEntregas.txNoExt|| e.getSource() == visEntregas.txtColonia
                || e.getSource() == visEntregas.txtCP || e.getSource() == visEntregas.txtMunicipio || e.getSource() == visEntregas.txtEstado || e.getSource() == visEntregas.txtNoCliente2 ||e.getSource() == visEntregas.txtSubtotal
           ){
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
            int seleccionar = visEntregas.tablaEntregas.getSelectedRow();
            if (seleccionar == -1) {
                JOptionPane.showMessageDialog(null, "No se selecciono ninguna fila");

           }else{
                visEntregas.txtNoEntrega.setText(String.valueOf(visEntregas.tablaEntregas.getValueAt(seleccionar, 0)));
                visEntregas.txtFechaPedido.setText(String.valueOf(visEntregas.tablaEntregas.getValueAt(seleccionar, 1)));
                visEntregas.txtFechaEntrega.setText(String.valueOf(visEntregas.tablaEntregas.getValueAt(seleccionar, 2)));
                visEntregas.txtTotalEntrega.setText(String.valueOf(visEntregas.tablaEntregas.getValueAt(seleccionar, 3)));
                visEntregas.txtNoEmpleado.setText(String.valueOf(visEntregas.tablaEntregas.getValueAt(seleccionar, 4)));
                visEntregas.txtNoCliente.setText(String.valueOf(visEntregas.tablaEntregas.getValueAt(seleccionar, 5)));     
                visEntregas.txtTipoEnvio.setText(String.valueOf(visEntregas.tablaEntregas.getValueAt(seleccionar, 6)));
                visEntregas.txtMetodoPago.setText(String.valueOf(visEntregas.tablaEntregas.getValueAt(seleccionar, 7)));        
            }            
        }         
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }



 


}
