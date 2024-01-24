/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Inventario;
import Controlador.NuevaEntrega;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author itzel
 */
public class ConsultarInicio extends Conexion{
    PreparedStatement ps; 
    ResultSet rs; 
    Connection conexion; 
    Conexion conectar = new Conexion();         
    
    public boolean CerrarSesion(){
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        Connection conexion = getConexion();    
        
        String sql = String.format("CALL registrarSalida();");
        try{
            ps = conexion.prepareStatement(sql);
            ps.execute();
            return true;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }         
        
        
    }
    
    public java.util.List listarBajoStock(){
        java.util.List<Inventario> datos = new ArrayList<>(); 
        try{
            conexion = conectar.getConexion();
            ps = conexion.prepareStatement("CALL verificarStockBajo()");
            rs = ps.executeQuery();
            while (rs.next()){
                Inventario inventario = new Inventario();
                inventario.setCodigoProducto(rs.getString(1));
                inventario.setNombreProducto(rs.getString(2)); 
                inventario.setStock(Integer.parseInt(rs.getString(3)));

                datos.add(inventario);
            }
        } catch (Exception e){
            
        }
        return datos;           
    }
    
    public java.util.List listarEntregas(){
        java.util.List<NuevaEntrega> datos = new ArrayList<>(); 
        try{
            conexion = conectar.getConexion();
            ps = conexion.prepareStatement("CALL verificarEntregasSinEntregar()");
            rs = ps.executeQuery();
            while (rs.next()){
                NuevaEntrega nuevaEntrega = new NuevaEntrega();
                nuevaEntrega.setNoEntrega(rs.getString(1));
                nuevaEntrega.setEstatus(rs.getString(2));

                datos.add(nuevaEntrega);
            }
        } catch (Exception e){
            
        }
        return datos;           
    }    
}
