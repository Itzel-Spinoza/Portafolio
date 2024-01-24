/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import Controlador.Ventas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author itzel
 */
public class ConsultasVentas extends Conexion{
    PreparedStatement ps; 
    ResultSet rs; 
    Connection conexion; 
    Conexion conectar = new Conexion();
    Ventas ventas = new Ventas();
    
    
    public java.util.List listar() {
        java.util.List<Ventas> datos = new ArrayList<>();
        try{
            conexion = conectar.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM ventasFisicas");
            rs = ps.executeQuery();
            while (rs.next()){
                Ventas ventas = new Ventas();
                ventas.setNoVenta(rs.getString(1));
                ventas.setFechaVenta(rs.getString(2)); 
                ventas.setTotalVenta(Float.parseFloat(rs.getString(3))); 
                ventas.setNoEmpleado(rs.getString(4)); 
                datos.add(ventas);
            }
        } catch (Exception e){
            
        }
        return datos;
    }
    
    public List<Ventas> listarProductosTiene() {
        java.util.List<Ventas> datos = new ArrayList<>();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion(); 
        try{
            
            conexion = conectar.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM tiene");
            rs = ps.executeQuery();
            while (rs.next()){
                Ventas ventas = new Ventas();
                ventas.setNoVenta(rs.getString(1));
                ventas.setCodigoProducto(rs.getString(2));
                ventas.setNombreProducto(rs.getString(3)); 
                ventas.setCantidad(Integer.parseInt(rs.getString(4)));
                ventas.setPrecio(Float.parseFloat(rs.getString(5)));
                ventas.setSubtotal(Float.parseFloat(rs.getString(6)));
                

                datos.add(ventas);
               
                 
                
            }
        } catch (Exception e){
            System.err.println(e);
                          
        }
        return datos;
        
    }  

   public boolean productosEnVenta(Ventas ventas){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();   
       
       String sql = "CALL verProductosVenta(?)";
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, ventas.getNoVenta());  
            rs = ps.executeQuery();
            
            if(rs.next()){
                ventas.setCodigoProducto(rs.getString("codigoProducto"));
                ventas.setNombreProducto(rs.getString("NombreProducto"));
                ventas.setCantidad(Integer.parseInt(rs.getString("CantidadProducto")));
                ventas.setPrecio(Float.parseFloat(rs.getString("PrecioProducto")));
                ventas.setSubtotal(Float.parseFloat(rs.getString("Subtotal")));

                return true;
            }  
            return false;
            
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        }      
       
    }
   
    public boolean sacarSubtotal(Ventas ventas){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();      
        
        String sql = String.format("CALL subtotalDeLaVenta(?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, ventas.getNoVenta());
            rs = ps.executeQuery();
            if(rs.next()){
                ventas.setSubtotal(rs.getInt("SUM(Subtotal)"));
   
                return true; 
            }
      
            return false;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }              
    }   
    
    public boolean sacarTotal(Ventas ventas){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();      
        
        String sql = String.format("CALL verTotalVenta(?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, ventas.getNoVenta());
            rs = ps.executeQuery();
            if(rs.next()){
                ventas.setTotalVenta(rs.getInt("TotalVenta"));
   
                return true; 
            }
      
            return false;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }              
    }       
    
}
