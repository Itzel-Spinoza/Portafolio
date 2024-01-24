/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ConsultasInventario;
import Modelo.ConsultasNuevaEntrega;
import Vistas.VentanaInicio;
import Vistas.VentanaNuevaEntrega;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
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
public class CtrlNuevaEntrega implements ActionListener, KeyListener{
    private NuevaEntrega mNuevaEntrega; 
    private ConsultasNuevaEntrega conNuevaEntrega; 
    private VentanaNuevaEntrega visNuevaEntrega; 
    DefaultTableModel modelo = new DefaultTableModel(); 
    DefaultTableModel modelo2 = new DefaultTableModel(); 
    
    Inventario inventario = new Inventario(); 
    ConsultasInventario conInventario = new ConsultasInventario(); 
    
    VentanaInicio visInicio = new VentanaInicio(); 
    
    public CtrlNuevaEntrega(NuevaEntrega mNuevaEntrega, ConsultasNuevaEntrega conNuevaEntrega, VentanaNuevaEntrega visNuevaEntrega){
        this.mNuevaEntrega = mNuevaEntrega; 
        this.conNuevaEntrega = conNuevaEntrega; 
        this.visNuevaEntrega = visNuevaEntrega; 
        
        this.visNuevaEntrega.btnNuevaEntrega.addActionListener(this);
        this.visNuevaEntrega.btnRealizarNuevaEntrega.addActionListener(this);
        this.visNuevaEntrega.btnAgregarProducto.addActionListener(this); 
        this.visNuevaEntrega.btnCupon.addActionListener(this);
        this.visNuevaEntrega.btnCancelarEntrega.addActionListener(this); 
        this.visNuevaEntrega.btnEliminarProducto.addActionListener(this);
        
        this.visNuevaEntrega.txtCodigoProducto.addKeyListener(this);
        this.visNuevaEntrega.txtNombreProducto.addKeyListener(this);
        this.visNuevaEntrega.txtSubtotal.addKeyListener(this);
        this.visNuevaEntrega.txtDescuento.addKeyListener(this);
        this.visNuevaEntrega.txtTotal.addKeyListener(this);         
        this.visNuevaEntrega.tablaProductos.addKeyListener(this);        
    }
    public void Iniciar(){
        visNuevaEntrega.setTitle("Nueva Entrega"); 
        visNuevaEntrega.setLocationRelativeTo(null);
        visNuevaEntrega.setVisible(true);
        

    }    
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == visNuevaEntrega.btnNuevaEntrega){
            limpiar();
            limpiarTablaCarrito(); 
            
            if(conNuevaEntrega.iniciarEntrega(mNuevaEntrega)){
                JOptionPane.showMessageDialog(null, "Entrega iniciada");
                listar(visNuevaEntrega.tablaProductos);
                visNuevaEntrega.lblEntrega.setText(mNuevaEntrega.getNoEntrega());
                visNuevaEntrega.lblUsuario.setText(mNuevaEntrega.getNoEmpleado());
                limpiarTablaCarrito(); 
            } else{
                JOptionPane.showMessageDialog(null, "Error en iniciar la entrega");
            }  
        }
        
        if(e.getSource() == visNuevaEntrega.btnAgregarProducto){
            Integer cantidadCom = (Integer)visNuevaEntrega.spinCantidad.getValue(); 
            
            inventario.setCodigoProducto(visNuevaEntrega.txtCodigoProducto.getText());
            if(conNuevaEntrega.comprobarStock(inventario)){
                int cantidadStock = inventario.getStock(); 
                if (cantidadCom > cantidadStock){
                   JOptionPane.showMessageDialog(null, "La cantidad elegida es mayor a la que hay en el stock"); 
                }
                else{
                    mNuevaEntrega.setNoEntrega(visNuevaEntrega.lblEntrega.getText());
                    mNuevaEntrega.setCodigoProducto(visNuevaEntrega.txtCodigoProducto.getText());
                    mNuevaEntrega.setCantidadE((int) visNuevaEntrega.spinCantidad.getValue());
                    if(conNuevaEntrega.agregarProducto(mNuevaEntrega)){
                        JOptionPane.showMessageDialog(null, "Producto agregado");
                        
                        if(conNuevaEntrega.listarProductoExiste(mNuevaEntrega)){
                            String codigoProd = mNuevaEntrega.getCodigoProducto(); 
                            String nombreProd = mNuevaEntrega.getNombreProducto(); 
                            int canti = mNuevaEntrega.getCantidadE(); 
                            float precio = mNuevaEntrega.getPrecioProductoE(); 
                            float subtot = mNuevaEntrega.getSubtotalE(); 
                            
                            centrarCeldasCarrito(visNuevaEntrega.tablaVentaCarrito); 
                            modelo2 = (DefaultTableModel) visNuevaEntrega.tablaVentaCarrito.getModel(); 
                            visNuevaEntrega.tablaVentaCarrito.setModel(modelo2);
                            Object[] objeto = new Object[5];
                            objeto[0] = codigoProd; 
                            objeto[1] = nombreProd;
                            objeto[2] = canti;
                            objeto[3] = precio;
                            objeto[4] = subtot;
                            modelo2.addRow(objeto);
                        }
                        
                        //mNuevaEntrega.setNoEntrega(visNuevaEntrega.lblEntrega.getText());
                        if(conNuevaEntrega.sacarSubtotal(mNuevaEntrega)){
                            visNuevaEntrega.txtSubtotal.setText(String.valueOf(mNuevaEntrega.getSubtotalE())); 
                            visNuevaEntrega.txtTotal.setText(String.valueOf(mNuevaEntrega.getSubtotalE())); 
                        }
                        
                       
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Producto no agregado");
                    }
                    limpiarTabla();
                    listar(visNuevaEntrega.tablaProductos);
                }
            }
        }
        
        if(e.getSource() == visNuevaEntrega.btnCupon){
            int descuento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el porcentaje de descuento:"));
            double descuentoDec, totalDesc, total;
            DecimalFormat df = new DecimalFormat("#.00");
           
            descuentoDec = descuento * 0.01; 
            float subtotal = Float.parseFloat(visNuevaEntrega.txtSubtotal.getText()); 
            totalDesc = subtotal * descuentoDec; 
            visNuevaEntrega.txtDescuento.setText(String.valueOf(df.format(totalDesc)));
            total = subtotal - totalDesc; 
            visNuevaEntrega.txtTotal.setText(String.valueOf(total));               
        }
        
        if(e.getSource() == visNuevaEntrega.btnRealizarNuevaEntrega){
            mNuevaEntrega.setNoEntrega(visNuevaEntrega.lblEntrega.getText());
            mNuevaEntrega.setTotalEntrega(Float.parseFloat(visNuevaEntrega.txtTotal.getText())); 
            mNuevaEntrega.setNoCliente(visNuevaEntrega.txtNoCliente.getText());
            
            
            String tipoEnvio = visNuevaEntrega.comboTipoEnvio.getSelectedItem().toString();

                if(tipoEnvio.equals("A domicilio")){
                    mNuevaEntrega.setNoEntrega(visNuevaEntrega.lblEntrega.getText());
                    conNuevaEntrega.envioDomicilio(mNuevaEntrega);
                }
                else{
                    if(tipoEnvio.equals("Punto de entrega")){
                        mNuevaEntrega.setNoEntrega(visNuevaEntrega.lblEntrega.getText());
                        conNuevaEntrega.puntoEntrega(mNuevaEntrega);
                    }  
                    else{
                        if(tipoEnvio.equals("Paquetería")){
                            mNuevaEntrega.setNoEntrega(visNuevaEntrega.lblEntrega.getText());
                            conNuevaEntrega.paqueteria(mNuevaEntrega);
                        }                          
                    }
                }
            
            String MetodoPago = visNuevaEntrega.comboMetodoPago.getSelectedItem().toString();
            if(MetodoPago.equals("En efectivo")){
                mNuevaEntrega.setNoEntrega(visNuevaEntrega.lblEntrega.getText());
                conNuevaEntrega.metoEnEfecti(mNuevaEntrega);
            }
            else{
                mNuevaEntrega.setNoEntrega(visNuevaEntrega.lblEntrega.getText());
                conNuevaEntrega.transferencia(mNuevaEntrega);
            }
            
            mNuevaEntrega.setMetodoPago(MetodoPago);
            
            if(conNuevaEntrega.registrarEntrega(mNuevaEntrega)){
                JOptionPane.showMessageDialog(null, "Entrega registrada");
            }
            else{
                JOptionPane.showMessageDialog(null, "Entrega no registrada");
            }
            
            
        }
        
        if(e.getSource() == visNuevaEntrega.btnEliminarProducto){
            int row = visNuevaEntrega.tablaVentaCarrito.getSelectedRow(); 
            String codigoPro = visNuevaEntrega.tablaVentaCarrito.getValueAt(row, 0).toString(); 
            
            mNuevaEntrega.setNoEntrega(visNuevaEntrega.lblEntrega.getText());
            mNuevaEntrega.setCodigoProducto(codigoPro);
            if(conNuevaEntrega.borrarProductoEntrega(mNuevaEntrega)){

                JOptionPane.showMessageDialog(null, "Producto eliminado");
                DefaultTableModel dtm = (DefaultTableModel) visNuevaEntrega.tablaVentaCarrito.getModel(); 
                dtm.removeRow(visNuevaEntrega.tablaVentaCarrito.getSelectedRow());
                limpiar(); 
                if(conNuevaEntrega.sacarSubtotal(mNuevaEntrega)){
                    visNuevaEntrega.txtSubtotal.setText(String.valueOf(mNuevaEntrega.getSubtotalE()));
                    visNuevaEntrega.txtTotal.setText(String.valueOf(mNuevaEntrega.getSubtotalE()));
                }    
            }
            else{
                JOptionPane.showMessageDialog(null, "Error al eliminar el producto");
            }            
        }
        
        if(e.getSource() == visNuevaEntrega.btnCancelarEntrega){
            mNuevaEntrega.setNoEntrega(visNuevaEntrega.lblEntrega.getText());
            if(conNuevaEntrega.eliminarEntrega(mNuevaEntrega)){
                JOptionPane.showMessageDialog(null, "Entrega eliminada");
                limpiar2(); 
                limpiarTablaCarrito();
                //limpiarTabla();
            }
            else{
                JOptionPane.showMessageDialog(null, "Entrega no eliminada");
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
        for (int i = 0; i < visNuevaEntrega.tablaProductos.getColumnCount(); i++) {
            tablaProductos.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    } 
    void centrarCeldasCarrito(JTable tablaVentaCarrito){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(); 
        tcr.setHorizontalAlignment(SwingConstants.CENTER); 
        for (int i = 0; i < visNuevaEntrega.tablaVentaCarrito.getColumnCount(); i++) {
            tablaVentaCarrito.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }   
    public void limpiarTabla(){
        for (int i = 0; i < visNuevaEntrega.tablaProductos.getRowCount(); i++) {
            modelo.removeRow(i);
            i-=1;
        }
    }    
    public void limpiarTablaCarrito(){
        for (int i = 0; i < visNuevaEntrega.tablaVentaCarrito.getRowCount(); i++) {
            modelo2.removeRow(i);
            i-=1;
        }        
    }

    public void limpiar(){
        visNuevaEntrega.txtCodigoProducto.setText(null);
        visNuevaEntrega.txtNombreProducto.setText(null);  
        visNuevaEntrega.txtDescuento.setText(null); 
        visNuevaEntrega.txtNoCliente.setText(null);
        visNuevaEntrega.txtSubtotal.setText(null);
        visNuevaEntrega.txtTotal.setText(null);
    }    
    public void limpiar2(){
        visNuevaEntrega.txtCodigoProducto.setText(null);
        visNuevaEntrega.txtNombreProducto.setText(null);  
        visNuevaEntrega.txtDescuento.setText(null); 
        visNuevaEntrega.txtNoCliente.setText(null);
        visNuevaEntrega.txtSubtotal.setText(null);
        visNuevaEntrega.txtTotal.setText(null);
        visNuevaEntrega.lblEntrega.setText(null);
    }        

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == visNuevaEntrega.txtCodigoProducto ||
        e.getSource() == visNuevaEntrega.txtNombreProducto ||
        e.getSource() == visNuevaEntrega.txtSubtotal ||
        e.getSource() == visNuevaEntrega.txtTotal||
        e.getSource() == visNuevaEntrega.txtDescuento){
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
            int seleccionar = visNuevaEntrega.tablaProductos.getSelectedRow();
            if (seleccionar == -1) {
                JOptionPane.showMessageDialog(null, "No se selecciono ninguna fila");

           }else{
                visNuevaEntrega.txtCodigoProducto.setText(String.valueOf(visNuevaEntrega.tablaProductos.getValueAt(seleccionar, 0))); 
                visNuevaEntrega.txtNombreProducto.setText(String.valueOf(visNuevaEntrega.tablaProductos.getValueAt(seleccionar, 1)));                
            }            
        }        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
