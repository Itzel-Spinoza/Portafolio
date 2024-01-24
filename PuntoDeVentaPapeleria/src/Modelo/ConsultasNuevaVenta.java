/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Inventario;
import Controlador.NuevaVenta;
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
public class ConsultasNuevaVenta extends Conexion{
    PreparedStatement ps; 
    ResultSet rs; 
    Connection conexion; 
    Conexion conectar = new Conexion();   
    NuevaVenta nuevaVenta = new NuevaVenta(); 
    Inventario inventario = new Inventario();

    
    public boolean iniciarVenta(NuevaVenta nuevaVenta){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();      
        
        String sql = String.format("CALL hacerNuevaVenta();");   
        try{
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                nuevaVenta.setNoVenta(rs.getString("NoVenta"));
                nuevaVenta.setNoEmpleado(rs.getString("NoEmpleado"));
                return true; 
            }
            
   
            
            return false;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }         
    }
    
    public boolean comprobarStock(Inventario inventario){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();      
        
        String sql = String.format("CALL verificarStock(?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, inventario.getCodigoProducto());
            rs = ps.executeQuery();
            if(rs.next()){
                inventario.setStock(rs.getInt("Stock"));
   
                return true; 
            }
            
   
            
            return false;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }           
    }
    public boolean agregarProducto(NuevaVenta nuevaVenta){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();      
        
        String sql = String.format("CALL agregarProductoaVenta(?,?,?);");  
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevaVenta.getNoVenta());
            ps.setString(2, nuevaVenta.getCodigoProducto());
            ps.setInt(3, nuevaVenta.getCantidad());
            ps.execute();
    
            
   
            
            return true;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }      
    }
    
    public boolean sacarSubtotal(NuevaVenta nuevaVenta){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();      
        
        String sql = String.format("CALL subtotalDeLaVenta(?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevaVenta.getNoVenta());
            rs = ps.executeQuery();
            if(rs.next()){
                nuevaVenta.setSubtotal(rs.getInt("SUM(Subtotal)"));
   
                return true; 
            }
      
            return false;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }              
    }
    
    public boolean registrarTotalVenta(NuevaVenta nuevaVenta){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();  
        String sql = String.format("CALL calcularTotalVen2(?,?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevaVenta.getNoVenta());
            ps.setFloat(2, nuevaVenta.getTotalVenta()); 
            ps.execute();
            return true;
        }catch(SQLException e){
            
            System.err.println(e);
            return false;
            
        }         
    }
    
    public boolean borrarProductoVenta(NuevaVenta nuevaVenta){
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        Connection conexion = getConexion();    
        
        String sql = String.format("CALL eliminarProductoVenta(?,?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevaVenta.getNoVenta());
            ps.setString(2, nuevaVenta.getCodigoProducto());
            ps.execute();
            return true;
        }catch(SQLException e){
            
            System.err.println(e);
            return false;
            
        }         
    }
    
   
    public boolean listarProductoTiene2(NuevaVenta nuevaVenta){
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        Connection conexion = getConexion();    
        
        String sql = String.format("CALL productoVenta(?,?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevaVenta.getNoVenta());
            ps.setString(2, nuevaVenta.getCodigoProducto());
            rs = ps.executeQuery();
            if(rs.next()){
                
                nuevaVenta.setCodigoProducto(rs.getString("codigoProducto"));
                nuevaVenta.setNombreProducto(rs.getString("NombreProducto"));
                nuevaVenta.setCantidad(rs.getInt("CantidadProducto")); 
                nuevaVenta.setPrecio(rs.getFloat("PrecioProducto")); 
                nuevaVenta.setSubtotal(rs.getFloat("Subtotal")); 
   
                return true; 
            }
      
            return false;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }                      
    }
    public List<NuevaVenta> listarProductosTiene(NuevaVenta nuevaVenta) {
        java.util.List<NuevaVenta> datos = new ArrayList<>();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion(); 
        try{
            
            String sql = String.format("CALL mostrarProductosVen(?)"); 
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevaVenta.getNoVenta());
            rs = ps.executeQuery();
            while (rs.next()){
                
                nuevaVenta.setCodigoProducto(rs.getString(1));
                nuevaVenta.setNombreProducto(rs.getString(2)); 
                nuevaVenta.setCantidad(Integer.parseInt(rs.getString(3)));
                nuevaVenta.setPrecio(Float.parseFloat(rs.getString(4)));
                nuevaVenta.setSubtotal(Float.parseFloat(rs.getString(5)));
                

                datos.add(nuevaVenta);
               
                 
                
            }
        } catch (Exception e){
            System.err.println(e);
                          
        }
        return datos;
        
    }  
        
     public java.util.List listarTiene(){
         java.util.List<NuevaVenta> datos = new ArrayList<>();
        try{
            conexion = conectar.getConexion();
            ps = conexion.prepareStatement("SELECT CodigoProducto, NombreProducto, CantidadProducto, PrecioProducto, Subtotal FROM tiene");
            rs = ps.executeQuery();
            while (rs.next()){
                NuevaVenta nuevaVenta = new NuevaVenta();
                //nuevaVenta.setNoVenta(rs.getString(1));
                nuevaVenta.setCodigoProducto(rs.getString(1)); 
                nuevaVenta.setNombreProducto(rs.getString(2)); 
                nuevaVenta.setCantidad(Integer.parseInt(rs.getString(3)));
                nuevaVenta.setPrecio(Float.parseFloat(rs.getString(4))); 
                nuevaVenta.setSubtotal(Float.parseFloat(rs.getString(5))); 
     
                datos.add(nuevaVenta);
            }
        } catch (Exception e){
            
        }
        return datos;         
    }  
     
    public boolean cancelarVenta(NuevaVenta nuevaVenta){
       PreparedStatement ps = null; 
        ResultSet rs = null; 
        Connection conexion = getConexion();    
        
        String sql = String.format("CALL cancelarVenta(?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevaVenta.getNoVenta());
            ps.execute();
            return true;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }            
    }
}
