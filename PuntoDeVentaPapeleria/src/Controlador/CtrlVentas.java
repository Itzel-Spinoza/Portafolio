
package Controlador;

import Modelo.ConsultasVentas;
import Vistas.VentanaVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author itzel
 */
public class CtrlVentas implements ActionListener, KeyListener{
    private Ventas mVentas; 
    private ConsultasVentas conVentas; 
    private VentanaVenta visVenta; 
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();
    DefaultTableModel modelo3 = new DefaultTableModel(); 
    public CtrlVentas(Ventas mVentas, ConsultasVentas conVentas, VentanaVenta visVenta){
        this.mVentas = mVentas; 
        this.conVentas = conVentas; 
        this.visVenta = visVenta; 
        
        this.visVenta.btnVerProductos.addActionListener(this);
        this.visVenta.txtNoVenta.addKeyListener(this); 
        this.visVenta.txtNoEmpleado.addKeyListener(this);
        this.visVenta.txtFechaVenta.addKeyListener(this);
        this.visVenta.txtTotal.addKeyListener(this); 
        this.visVenta.txtTotalVenta.addKeyListener(this);
        this.visVenta.txtSubtotal.addKeyListener(this); 
        this.visVenta.tablaVentas.addKeyListener(this);
        
        
    }
    
    public void Iniciar(){
        visVenta.setTitle("Ventas");
        visVenta.setLocationRelativeTo(null);
        visVenta.setVisible(true);
        listar(visVenta.tablaVentas);
        listarProductos(visVenta.tablaProductosVenta);
    }
    
    public void listar(JTable tablaVentas) {
        centrarCeldas(tablaVentas);
        modelo = (DefaultTableModel) tablaVentas.getModel();
        tablaVentas.setModel(modelo);
        List<Ventas> lista = (List<Ventas>) conVentas.listar();
        Object[] objeto = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getNoVenta();
            objeto[1] = lista.get(i).getFechaVenta();
            objeto[2] = lista.get(i).getTotalVenta();
            objeto[3] = lista.get(i).getNoEmpleado();

            modelo.addRow(objeto);
        }
        tablaVentas.setRowHeight(35);
        tablaVentas.setRowMargin(10);

    }
    
    
    
    void centrarCeldas(JTable tablaVentas) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < visVenta.tablaVentas.getColumnCount(); i++) {
            tablaVentas.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }
 

    public void listarProductos(JTable tablaProductosVenta) {
        centrarCeldas2(tablaProductosVenta);
        modelo2 = (DefaultTableModel) tablaProductosVenta.getModel();
        tablaProductosVenta.setModel(modelo2);
        
        List<Ventas> lista = (List<Ventas>)conVentas.listarProductosTiene();
        Object[] objeto = new Object[6];
        for (int i = 0; i < lista.size(); i++){
            objeto[0] = lista.get(i).getNoVenta();
            objeto[1] = lista.get(i).getCodigoProducto();
            objeto[2] = lista.get(i).getNombreProducto();
            objeto[3] = lista.get(i).getCantidad();
            objeto[4] = lista.get(i).getPrecio();
            objeto[5] = lista.get(i).getSubtotal(); 
            modelo2.addRow(objeto);
        }
        tablaProductosVenta.setRowHeight(35);
        tablaProductosVenta.setRowMargin(10);        

    }
    void centrarCeldas2(JTable tablaProductosVenta) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < visVenta.tablaProductosVenta.getColumnCount(); i++) {
            tablaProductosVenta.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    } 
    
    public void limpiar(){
        for (int i = 0; i < visVenta.tablaProductosVenta.getRowCount(); i++) {
            modelo2.removeRow(i);
            i-=1;
        }
    }    
    
    public void filtrarVentas(String noventa, JTable tablaProductosVenta){
        modelo3 = (DefaultTableModel) tablaProductosVenta.getModel(); 
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(modelo3);
        tablaProductosVenta.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(noventa));
    
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == visVenta.btnVerProductos){
            //limpiar(); 
            //listarProductos(visVenta.tablaProductosVenta);
            String noventa; 
            noventa = visVenta.txtNoVenta.getText();
            visVenta.lblNoVenta.setText(noventa);
            filtrarVentas(visVenta.txtNoVenta.getText(), visVenta.tablaProductosVenta);
            mVentas.setNoVenta(visVenta.txtNoVenta.getText());
            if(conVentas.sacarSubtotal(mVentas)){
                visVenta.txtSubtotal.setText(String.valueOf(mVentas.getSubtotal()));
            }
            if(conVentas.sacarTotal(mVentas)){
                visVenta.txtTotal.setText(String.valueOf(mVentas.getTotalVenta()));
            }
                
            
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
       /* if(e.getSource() == visVenta.txtNoVenta || e.getSource() == visVenta.txtNoEmpleado){
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
        
        if(e.getSource() == visVenta.txtTotal || e.getSource() == visVenta.txtSubtotal){
            char c = e.getKeyChar();
            if(c >= 33 && c <= 45
                     || c >= 58 && c <= 8482){
                e.consume(); 
                JOptionPane.showMessageDialog(null, "Solamente se pueden ingresar numeros");
            }
        } */

        if(e.getSource() == visVenta.txtFechaVenta || e.getSource() == visVenta.txtNoVenta || e.getSource() == visVenta.txtNoEmpleado || e.getSource() == visVenta.txtTotal || e.getSource() == visVenta.txtSubtotal || e.getSource() == visVenta.txtTotalVenta){
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
            int seleccionar = visVenta.tablaVentas.getSelectedRow();
            if (seleccionar == -1) {
                JOptionPane.showMessageDialog(null, "No se selecciono ninguna fila");

           }else{
        visVenta.txtNoVenta.setText(String.valueOf(visVenta.tablaVentas.getValueAt(seleccionar, 0)));
        visVenta.txtFechaVenta.setText(String.valueOf(visVenta.tablaVentas.getValueAt(seleccionar, 1)));
        visVenta.txtTotalVenta.setText(String.valueOf(visVenta.tablaVentas.getValueAt(seleccionar, 2)));
        visVenta.txtNoEmpleado.setText(String.valueOf(visVenta.tablaVentas.getValueAt(seleccionar, 3)));
            }            
        }        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
