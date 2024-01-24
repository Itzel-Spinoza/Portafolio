
package Controlador;

import Modelo.ConsultarInicio;
import Modelo.ConsultarUsuario;
import Vistas.VentanaInicio;
import Vistas.VentanaInicioSesion;
import Vistas.VentanaNuevaVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author itzel
 */
public class CtrlUsuario implements ActionListener{
    private Usuario mUsuario; 
    private ConsultarUsuario conUsuario; 
    private VentanaInicioSesion visInicioSesion; 
    
    
    public String usuario; 
    public String usuario2; 
    
    VentanaInicio visInicio = new VentanaInicio(); 
    ConsultarInicio conInicio = new ConsultarInicio();
    VentanaNuevaVenta visNuevaVenta = new VentanaNuevaVenta();
    
    CtrlInicio v = new CtrlInicio(visInicio, conInicio);
    
    public CtrlUsuario (Usuario mUsuario, ConsultarUsuario conUsuario, VentanaInicioSesion visInicioSesion){
        this.visInicioSesion = visInicioSesion; 
        this.mUsuario = mUsuario; 
        this.conUsuario = conUsuario; 
        
        this.visInicioSesion.btnIngresar.addActionListener(this);
    }
    
    public void Iniciar(){
        visInicioSesion.setLocationRelativeTo(null);
        visInicioSesion.setVisible(true);
    }
    
    public void actionPerformed (ActionEvent e){
        if(e.getSource() == visInicioSesion.btnIngresar){
            if(visInicioSesion.txtUsuario.getText().equals("") || visInicioSesion.txtContraseña.getPassword().equals("")){
                JOptionPane.showMessageDialog(null, "Ingrese sus datos en los campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
                
            }
            else{
                mUsuario.setCodigoEmpleado(visInicioSesion.txtUsuario.getText());
                mUsuario.setContraseña(String.valueOf(visInicioSesion.txtContraseña.getPassword()));
                String usuario = visInicioSesion.txtUsuario.getText();
                //String usuario2 = visInicioSesion.txtUsuario.getText();                
                visInicio.lblUsuario.setText(usuario);
                //visNuevaVenta.lblUsuario.setText(usuario2);
                
                
                
                if(conUsuario.Login(mUsuario)){
                    
                    JOptionPane.showMessageDialog(null, "Ingreso exitoso");
                    mUsuario.setCodigoEmpleado(visInicioSesion.txtUsuario.getText());
                    if(conUsuario.regEntrada(mUsuario)){
                        JOptionPane.showMessageDialog(null, "Entrada registrada");
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Entrada no registrada");
                    }
                    
                    visInicioSesion.dispose();
                    v.Inicio();
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Datos incorrectos.", "Usuario no encontrado. ERROR.", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        
    }
    
}
