
package Controlador;

import Modelo.ConsultarInicio;
import Vistas.VentanaInicio;
import Vistas.VentanaInicioSesion;
import Vistas.VentanaNuevaVenta;
import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.nio.file.Files.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author itzel
 */
public class CtrlInicio implements ActionListener{
    private VentanaInicio visInicio; 
    private ConsultarInicio conInicio; 
    //public String usuario; 
    CtrlMenu menu = new CtrlMenu();
    VentanaNuevaVenta visNuevaVenta = new VentanaNuevaVenta();
    //public String usuario; 
    
    
    public CtrlInicio (VentanaInicio visInicio, ConsultarInicio conInicio){
        this.visInicio = visInicio; 
        this.conInicio = conInicio; 
        
       
        
        this.visInicio.btnInicio.addActionListener(this);
        this.visInicio.btnVentas.addActionListener(this);
        this.visInicio.btnNuevaVenta.addActionListener(this);
        this.visInicio.btnEntregas.addActionListener(this);
        this.visInicio.btnNuevaEntrega.addActionListener(this);
        this.visInicio.btnClientes.addActionListener(this);
        this.visInicio.btnInventario.addActionListener(this);
        this.visInicio.btnEmpleados.addActionListener(this);
        this.visInicio.btnProveedores.addActionListener(this);
        this.visInicio.btnActualizar.addActionListener(this);
        
        
        
        visInicio.addWindowListener(new WindowAdapter() {
	@Override
	public void windowClosing(WindowEvent e) {
                //Este método finaliza el programa una vez que el usuario hace clic en el botón de cierre.
		cerrar();
	}
});
        
    }
    
    public void listarNotificaciones(JList ListaNotificaciones){
        List<Inventario> lista = (List<Inventario>)conInicio.listarBajoStock(); 
        List<NuevaEntrega> listar = (List <NuevaEntrega>)conInicio.listarEntregas();

        DefaultListModel<String> modelo = new DefaultListModel<>();
        ListaNotificaciones.setModel(modelo); 
        for (int i = 0; i < lista.size(); i++) {
            String codigo = lista.get(i).getCodigoProducto(); 
            String nombrepro = lista.get(i).getNombreProducto(); 
            int stock = lista.get(i).getStock(); 
            
            modelo.addElement(nombrepro + " " + "con codigo: " + " " + codigo + " tiene stock bajo. Stock actual: " + stock);
                    
            
        }
        
        for (int i = 0; i < listar.size(); i++) {
            String codigo = listar.get(i).getNoEntrega(); 
            String estatus = listar.get(i).getEstatus(); 
            
            modelo.addElement("Entrega con codigo " + codigo + " " + "tiene estatus de" + " " + estatus);
                    
            
        }
        
    }
    
    public void actionPerformed(ActionEvent e){

        
        if (e.getSource() == visInicio.btnVentas){
            menu.moduloVentas();
        }
        
        if (e.getSource() == visInicio.btnNuevaVenta){
            
            String usuario = visInicio.lblUsuario.getText();
            visNuevaVenta.lblUsuario.setText(usuario);
            menu.moduloNuevaVenta();
        }
        
        if(e.getSource() == visInicio.btnInventario){
            menu.moduloInventario();
        }
        
        if(e.getSource() == visInicio.btnEmpleados){
            menu.moduloEmpleados();
        }
        
        if(e.getSource() == visInicio.btnClientes){
            menu.moduloClientes();
        }
        
        if(e.getSource() == visInicio.btnNuevaEntrega){
            menu.moduloNuevaEntrega();
        }
        
        if(e.getSource() == visInicio.btnProveedores){
            menu.moduloProveedores();
        }
        
        if(e.getSource() == visInicio.btnEntregas){
            menu.moduloEntregas();
        }
        
        if(e.getSource() == visInicio.btnActualizar){
            listarNotificaciones(visInicio.ListaNotificaciones); 
        }
    }
    


    public void cerrar(){
        Object [] opciones = {"Sí", "No"};
        int eleccion = JOptionPane.showOptionDialog(visInicio, "¿Desea cerrar sesión?", "Mensaje de confirmación", JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,null,opciones,"Sí");
        
        if(eleccion == JOptionPane.YES_OPTION){
            if(conInicio.CerrarSesion()){
                JOptionPane.showMessageDialog(null, "Sesión terminada");
                //visInicio.setDefaultCloseOperation(EXIT_ON_CLOSE); 
                System.exit(0);
            }
            
        }else{
            
        }
    }
    
    public void Inicio(){
        visInicio.setTitle("Inicio"); 
        visInicio.setLocationRelativeTo(null);
        visInicio.setVisible(true);
        listarNotificaciones(visInicio.ListaNotificaciones); 
         
    }
    
}
