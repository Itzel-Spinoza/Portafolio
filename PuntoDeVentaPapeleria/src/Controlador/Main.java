/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controlador;

import Modelo.ConsultarUsuario;
import Vistas.VentanaInicioSesion;
import Vistas.VentanaInicio;
import Vistas.VentanaNuevaVenta;

/**
 *
 * @author itzel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //VentanaNuevaVenta vLogin = new VentanaNuevaVenta(); 
        //vLogin.setVisible(true);
        
        VentanaInicioSesion visInicioSesion = new VentanaInicioSesion();
        Usuario mUsuario = new Usuario(); 
        ConsultarUsuario conUsuario = new ConsultarUsuario();
        
        CtrlUsuario c = new CtrlUsuario(mUsuario, conUsuario,visInicioSesion);
        c.Iniciar();
    }
    
}
