/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ConsultasInventario;
import Modelo.ConsultasNuevaVenta;
import Vistas.VentanaInicio;
import Vistas.VentanaNuevaVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
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
public class CtrlNuevaVenta implements ActionListener, KeyListener{
    private NuevaVenta mNuevaVenta; 
    private ConsultasNuevaVenta conNuevaVenta; 
    private VentanaNuevaVenta visNuevaVenta; 
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();   
    DefaultTableModel modelo3 = new DefaultTableModel(); 
    
    Inventario inventario = new Inventario(); 
    ConsultasInventario conInventario = new ConsultasInventario();
    
    VentanaInicio visInicio = new VentanaInicio();
   
    public CtrlNuevaVenta(NuevaVenta mNuevaVenta, ConsultasNuevaVenta conNuevaVenta, VentanaNuevaVenta visNuevaVenta){
        this.mNuevaVenta = mNuevaVenta; 
        this.conNuevaVenta = conNuevaVenta; 
        this.visNuevaVenta = visNuevaVenta; 
        
        this.visNuevaVenta.btnNuevaVenta.addActionListener(this);
        this.visNuevaVenta.btnAgregarProducto.addActionListener(this);
        this.visNuevaVenta.btnCupon.addActionListener(this); 
        this.visNuevaVenta.btnRealizarVenta.addActionListener(this); 
        this.visNuevaVenta.btnEliminarProducto.addActionListener(this); 
        this.visNuevaVenta.btnCancelarVenta.addActionListener(this); 
        
        this.visNuevaVenta.txtCodigoProducto.addKeyListener(this);
        this.visNuevaVenta.txtNombreProducto.addKeyListener(this);
        this.visNuevaVenta.txtSubtotal.addKeyListener(this);
        this.visNuevaVenta.txtDescuento.addKeyListener(this);
        this.visNuevaVenta.txtTotal.addKeyListener(this); 
        this.visNuevaVenta.tablaProductos.addKeyListener(this);
            
    }
    
    public void Iniciar(){
        visNuevaVenta.setTitle("Nueva Venta"); 
        visNuevaVenta.setLocationRelativeTo(null);
        visNuevaVenta.setVisible(true);
        

    }
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == visNuevaVenta.btnNuevaVenta){
            limpiar();
            limpiarTablaCarrito(); 

            if(conNuevaVenta.iniciarVenta(mNuevaVenta)){
                JOptionPane.showMessageDialog(null, "Venta iniciada");
                listar(visNuevaVenta.tablaProductos);
                visNuevaVenta.lblVenta.setText(mNuevaVenta.getNoVenta());
                visNuevaVenta.lblUsuario.setText(mNuevaVenta.getNoEmpleado());
                limpiarTablaCarrito(); 
            } else{
                JOptionPane.showMessageDialog(null, "Error en iniciar la venta");
            }   
            
        }
        
        if(e.getSource() == visNuevaVenta.btnAgregarProducto){
            Integer cantidadCom = (Integer)visNuevaVenta.spinCantidad.getValue();

            inventario.setCodigoProducto(visNuevaVenta.txtCodigoProducto.getText());
            if(conNuevaVenta.comprobarStock(inventario)){
                int cantidadStock = inventario.getStock(); 
                if (cantidadCom > cantidadStock){
                    JOptionPane.showMessageDialog(null, "La cantidad elegida es mayor a la que hay en el stock");
                }
                else{

                    mNuevaVenta.setNoVenta(visNuevaVenta.lblVenta.getText());
                    mNuevaVenta.setCodigoProducto(visNuevaVenta.txtCodigoProducto.getText());
                    mNuevaVenta.setCantidad((int) visNuevaVenta.spinCantidad.getValue());
                    if(conNuevaVenta.agregarProducto(mNuevaVenta)){
                        JOptionPane.showMessageDialog(null, "Producto agregado");
   
                        if(conNuevaVenta.listarProductoTiene2(mNuevaVenta)){
                            String codigoProd = mNuevaVenta.getCodigoProducto(); 
                            String nombreProd = mNuevaVenta.getNombreProducto();
                            int canti = mNuevaVenta.getCantidad(); 
                            float precio = mNuevaVenta.getPrecio(); 
                            float subtot = mNuevaVenta.getSubtotal(); 
                                    
                            centrarCeldasCarrito(visNuevaVenta.tablaVentaCarrito); 
                            modelo3 = (DefaultTableModel) visNuevaVenta.tablaVentaCarrito.getModel();
                            visNuevaVenta.tablaVentaCarrito.setModel(modelo3);   
                            Object[] objeto = new Object[5];
                            objeto[0] = codigoProd; 
                            objeto[1] = nombreProd;
                            objeto[2] = canti;
                            objeto[3] = precio;
                            objeto[4] = subtot;
    
                            modelo3.addRow(objeto);    
                        }

                        if(conNuevaVenta.sacarSubtotal(mNuevaVenta)){
                            visNuevaVenta.txtSubtotal.setText(String.valueOf(mNuevaVenta.getSubtotal()));
                            visNuevaVenta.txtTotal.setText(String.valueOf(mNuevaVenta.getSubtotal())); 
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Producto no agregado");
                    }   
                }
                limpiarTabla();
                listar(visNuevaVenta.tablaProductos);           
            }
        }
        
        if(e.getSource() == visNuevaVenta.btnCupon){
            int descuento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el porcentaje de descuento:"));
            double descuentoDec, totalDesc, total;
            DecimalFormat df = new DecimalFormat("#.00");
           
            descuentoDec = descuento * 0.01; 
            float subtotal = Float.parseFloat(visNuevaVenta.txtSubtotal.getText()); 
            totalDesc = subtotal * descuentoDec; 
            visNuevaVenta.txtDescuento.setText(String.valueOf(df.format(totalDesc)));
            total = subtotal - totalDesc; 
            visNuevaVenta.txtTotal.setText(String.valueOf(total));   
        }
        if(e.getSource() == visNuevaVenta.btnRealizarVenta){
            mNuevaVenta.setNoVenta(visNuevaVenta.lblVenta.getText());
            mNuevaVenta.setTotalVenta(Float.parseFloat(visNuevaVenta.txtTotal.getText()));            
            if(conNuevaVenta.registrarTotalVenta(mNuevaVenta)){
                JOptionPane.showMessageDialog(null, "Venta registrada");
            }
            else{
                JOptionPane.showMessageDialog(null, "Venta no registrada");
            }
        }
        
        if(e.getSource() == visNuevaVenta.btnEliminarProducto){
            int row = visNuevaVenta.tablaVentaCarrito.getSelectedRow();
            String codigoPro = visNuevaVenta.tablaVentaCarrito.getValueAt(row, 0).toString();
            String nombrePro = visNuevaVenta.tablaVentaCarrito.getValueAt(row, 1).toString();
            
            mNuevaVenta.setNoVenta(visNuevaVenta.lblVenta.getText());
            mNuevaVenta.setCodigoProducto(codigoPro);            
            if(conNuevaVenta.borrarProductoVenta(mNuevaVenta)){
               
                JOptionPane.showMessageDialog(null, "Producto eliminado");
                DefaultTableModel dtm = (DefaultTableModel) visNuevaVenta.tablaVentaCarrito.getModel(); 
                dtm.removeRow(visNuevaVenta.tablaVentaCarrito.getSelectedRow());
                limpiar(); 
                if(conNuevaVenta.sacarSubtotal(mNuevaVenta)){
                    visNuevaVenta.txtSubtotal.setText(String.valueOf(mNuevaVenta.getSubtotal()));
                    visNuevaVenta.txtTotal.setText(String.valueOf(mNuevaVenta.getSubtotal()));
                }    
            }
            else{
                JOptionPane.showMessageDialog(null, "Error al eliminar el producto");
            }
        }
        
        if(e.getSource() == visNuevaVenta.btnCancelarVenta){
            mNuevaVenta.setNoVenta(visNuevaVenta.lblVenta.getText());
            if(conNuevaVenta.cancelarVenta(mNuevaVenta)){
                JOptionPane.showMessageDialog(null, "Venta cancelada");
                limpiar2();
                limpiarTablaCarrito();                 
   
            }
            else{
                JOptionPane.showMessageDialog(null, "Error al cancelar venta");
            }            
        }
        
    }
    
    
    
        public void listar(JTable tablaProductos) {
        
        centrarCeldas(tablaProductos);
        modelo = (DefaultTableModel) tablaProductos.getModel();
        tablaProductos.setModel(modelo);
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
        tablaProductos.setRowHeight(35);
        tablaProductos.setRowMargin(10);

    }
    
        
    void centrarCeldas(JTable tablaProductos) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < visNuevaVenta.tablaProductos.getColumnCount(); i++) {
            tablaProductos.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }
    public void listarProductosVentaa(JTable tablaVentaCarrito){
        
        centrarCeldasCarrito(tablaVentaCarrito);
        modelo2 = (DefaultTableModel) tablaVentaCarrito.getModel();
        tablaVentaCarrito.setModel(modelo2);
        mNuevaVenta.setNoVenta(visNuevaVenta.lblVenta.getText());
        List<NuevaVenta> lista = (List<NuevaVenta>) conNuevaVenta.listarProductosTiene(mNuevaVenta);
        
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getCodigoProducto();
           
            objeto[1] = lista.get(i).getNombreProducto();
            objeto[2] = lista.get(i).getCantidad();
            objeto[3] = lista.get(i).getPrecio();
            objeto[4] = lista.get(i).getSubtotal();
    
            modelo2.addRow(objeto);
            
        }
        tablaVentaCarrito.setRowHeight(35);
        tablaVentaCarrito.setRowMargin(10);
    }
    
    public void carrito2(JTable tablaVentaCarrito){
        //limpiarTablaCarrito(); 
        centrarCeldasCarrito(tablaVentaCarrito); 
        modelo3 = (DefaultTableModel) tablaVentaCarrito.getModel();
        tablaVentaCarrito.setModel(modelo3);   
        List<NuevaVenta> lista = (List<NuevaVenta>)conNuevaVenta.listarProductosTiene(mNuevaVenta);; 
        int tamanoTabla = modelo3.getRowCount(); 
        Object[] objeto = new Object[5];
        if(tamanoTabla == 0){
            
            for (int i = 0; i < 1; i++) {
                //objeto[0] = lista.get(i).getNoVenta();
                objeto[0] = lista.get(i).getCodigoProducto();
            
                objeto[1] = lista.get(i).getNombreProducto();
                objeto[2] = lista.get(i).getCantidad();
                objeto[3] = lista.get(i).getPrecio();
                objeto[4] = lista.get(i).getSubtotal();
    
                modelo3.addRow(objeto);
            
            }
                tablaVentaCarrito.setRowHeight(35);
                tablaVentaCarrito.setRowMargin(10);               
        }
        else{
            
            if (tamanoTabla > 0){
                for (int i = 0; i < tamanoTabla; i++) {
                    
                    //Object[] objeto = new Object[5];
 
                    objeto[0] = lista.get(i).getCodigoProducto();
                    objeto[1] = lista.get(i).getNombreProducto();
                    objeto[2] = lista.get(i).getCantidad();
                    objeto[3] = lista.get(i).getPrecio();
                    objeto[4] = lista.get(i).getSubtotal();
                
                    modelo3.addRow(objeto); 
                }              

                tablaVentaCarrito.setRowHeight(35);
                tablaVentaCarrito.setRowMargin(10);                  
            }
        }
            
        
    }
    

    
    
    public void carrito(JTable tablaVentaCarrito){
        centrarCeldasCarrito(tablaVentaCarrito);
        modelo3 = (DefaultTableModel) tablaVentaCarrito.getModel();
        tablaVentaCarrito.setModel(modelo3);   
        List<NuevaVenta> lista = (List<NuevaVenta>)conNuevaVenta.listarTiene(); 
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            //objeto[0] = lista.get(i).getNoVenta();
            objeto[0] = lista.get(i).getCodigoProducto();
            
            objeto[1] = lista.get(i).getNombreProducto();
            objeto[2] = lista.get(i).getCantidad();
            objeto[3] = lista.get(i).getPrecio();
            objeto[4] = lista.get(i).getSubtotal();
    
            modelo3.addRow(objeto);
            
        }
        tablaVentaCarrito.setRowHeight(35);
        tablaVentaCarrito.setRowMargin(10);        
    }

    void centrarCeldasCarrito(JTable tablaVentaCarrito){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(); 
        tcr.setHorizontalAlignment(SwingConstants.CENTER); 
        for (int i = 0; i < visNuevaVenta.tablaVentaCarrito.getColumnCount(); i++) {
            tablaVentaCarrito.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }
    
    public void limpiarTabla(){
        for (int i = 0; i < visNuevaVenta.tablaProductos.getRowCount(); i++) {
            modelo.removeRow(i);
            i-=1;
        }
    }
    
    public void limpiarTablaCarrito(){
        for (int i = 0; i < visNuevaVenta.tablaVentaCarrito.getRowCount(); i++) {
            modelo3.removeRow(i);
            i-=1;
        }        
    }
    
    public void limpiar(){
        visNuevaVenta.txtCodigoProducto.setText(null);
        visNuevaVenta.txtNombreProducto.setText(null);  
        visNuevaVenta.txtSubtotal.setText(null); 
        visNuevaVenta.txtTotal.setText(null);
        visNuevaVenta.txtDescuento.setText(null);
    }
    
    public void limpiar2(){
        visNuevaVenta.lblVenta.setText(null);
        visNuevaVenta.txtCodigoProducto.setText(null);
        visNuevaVenta.txtNombreProducto.setText(null);  
        visNuevaVenta.txtSubtotal.setText(null); 
        visNuevaVenta.txtTotal.setText(null);
        visNuevaVenta.txtDescuento.setText(null);
    }   
    
   /* public void imprimirTicket(){
       try{
           String 
           
       }catch (IOException e) {
            System.out.println("ERROR: Ocurrio un problema al salvar el archivo!" + e.getMessage());
        } 
    }*/

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == visNuevaVenta.txtCodigoProducto ||
        e.getSource() == visNuevaVenta.txtNombreProducto ||
        e.getSource() == visNuevaVenta.txtSubtotal ||
        e.getSource() == visNuevaVenta.txtTotal||
        e.getSource() == visNuevaVenta.txtDescuento){
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
            int seleccionar = visNuevaVenta.tablaProductos.getSelectedRow();
            if (seleccionar == -1) {
                JOptionPane.showMessageDialog(null, "No se selecciono ninguna fila");

           }else{
                visNuevaVenta.txtCodigoProducto.setText(String.valueOf(visNuevaVenta.tablaProductos.getValueAt(seleccionar, 0))); 
                visNuevaVenta.txtNombreProducto.setText(String.valueOf(visNuevaVenta.tablaProductos.getValueAt(seleccionar, 1)));                
            }            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
