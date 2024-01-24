/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ConsultarInicio;
import Modelo.ConsultasClientes;
import Modelo.ConsultasEmpleados;
import Modelo.ConsultasEntregas;
import Modelo.ConsultasInventario;
import Modelo.ConsultasNuevaEntrega;
import Modelo.ConsultasNuevaVenta;
import Modelo.ConsultasProveedores;
import Modelo.ConsultasVentas;
import Vistas.VentanaClientes;
import Vistas.VentanaEmpleados;
import Vistas.VentanaEntregas;
import Vistas.VentanaInicio;
import Vistas.VentanaInventario;
import Vistas.VentanaNuevaEntrega;
import Vistas.VentanaNuevaVenta;
import Vistas.VentanaProveedores;
import Vistas.VentanaVenta;

/**
 *
 * @author itzel
 */
public class CtrlMenu {
    public void moduloInicio(){
        VentanaInicio visInicio = new VentanaInicio(); 
        ConsultarInicio conInicio = new ConsultarInicio(); 
        CtrlInicio cini = new CtrlInicio(visInicio, conInicio); 
        visInicio.dispose();
        cini.Inicio();
    }
    
    public void moduloVentas(){
        VentanaVenta visVenta = new VentanaVenta();
        Ventas mVentas = new Ventas();
        ConsultasVentas conVentas = new ConsultasVentas();
        
        CtrlVentas cvent = new CtrlVentas(mVentas, conVentas, visVenta); 
        cvent.Iniciar();      
    }
    
    public void moduloNuevaVenta(){
        VentanaNuevaVenta visNuevaVenta = new VentanaNuevaVenta(); 
        NuevaVenta mNuevaVenta = new NuevaVenta(); 
        ConsultasNuevaVenta conNuevaVenta = new ConsultasNuevaVenta();
        
        CtrlNuevaVenta cNueVent = new CtrlNuevaVenta(mNuevaVenta, conNuevaVenta, visNuevaVenta);
        cNueVent.Iniciar();    
    }
    
    public void moduloInventario(){
        VentanaInventario visInventario = new VentanaInventario(); 
        Inventario mInventario = new Inventario(); 
        ConsultasInventario conInventario = new ConsultasInventario(); 
        
        CtrlInventario cInven = new CtrlInventario(mInventario, conInventario, visInventario); 
        cInven.iniciarInv();
        
    }
    
    public void moduloEmpleados(){
        VentanaEmpleados visEmpleados = new VentanaEmpleados(); 
        Empleados mEmpleados = new Empleados();
        ConsultasEmpleados conEmpleados = new ConsultasEmpleados();
        CtrlEmpleados cempl = new CtrlEmpleados(mEmpleados, conEmpleados, visEmpleados);
        cempl.iniciarEmp();
    }   
    
    public void moduloClientes(){
        VentanaClientes visClientes = new VentanaClientes(); 
        Clientes mClientes = new Clientes(); 
        ConsultasClientes conClientes = new ConsultasClientes(); 
        CtrlClientes ccli = new CtrlClientes(mClientes, conClientes, visClientes); 
        ccli.iniciarCli();        
    }
    
    public void moduloNuevaEntrega(){
        VentanaNuevaEntrega visNuevaEntrega = new VentanaNuevaEntrega();
        NuevaEntrega mNuevaEntrega = new NuevaEntrega(); 
        ConsultasNuevaEntrega conNuevaEntrega = new ConsultasNuevaEntrega(); 
        CtrlNuevaEntrega cnueen = new CtrlNuevaEntrega(mNuevaEntrega, conNuevaEntrega, visNuevaEntrega);
        cnueen.Iniciar();
    }
    
    public void moduloProveedores(){
        VentanaProveedores visProveedores = new VentanaProveedores();
        Proveedores mProveedores = new Proveedores(); 
        ConsultasProveedores conProveedores = new ConsultasProveedores(); 
        CtrlProveedores conprov = new CtrlProveedores(mProveedores, conProveedores, visProveedores); 
        conprov.iniciar();    
    }
    
    public void moduloEntregas(){
        VentanaEntregas visEntregas = new VentanaEntregas();
        Entregas mEntregas = new Entregas(); 
        ConsultasEntregas conEntregas = new ConsultasEntregas();
        CtrlEntregas conentre = new CtrlEntregas(mEntregas, conEntregas, visEntregas);
        conentre.Iniciar();
        
    }
}
