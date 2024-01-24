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

/**
 *
 * @author itzel
 */
public class ConsultasInventario extends Conexion{
   PreparedStatement ps; 
    ResultSet rs; 
    Connection conexion; 
    Conexion conectar = new Conexion();
    Inventario inventario = new Inventario();       

    public java.util.List listar() {
        java.util.List<Inventario> datos = new ArrayList<>();
        try{
            conexion = conectar.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM productos");
            rs = ps.executeQuery();
            while (rs.next()){
                Inventario inventario = new Inventario();
                inventario.setCodigoProducto(rs.getString(1));
                inventario.setNombreProducto(rs.getString(2)); 
                inventario.setDescripcion(rs.getString(3)); 
                inventario.setStock(Integer.parseInt(rs.getString(4)));
                inventario.setPrecio(Float.parseFloat(rs.getString(5))); 
                inventario.setCodigoProveedor(rs.getString(6)); 
                inventario.setClaveCategoria(rs.getString(7)); 
                datos.add(inventario);
            }
        } catch (Exception e){
            
        }
        return datos;
    }   
    
    
    
    public boolean hacerCodigo(Inventario inventario){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();    
        
        String sql = String.format("CALL codigoProductos();");  
        try{
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                inventario.setCodigoProducto(rs.getString("codigoProducto"));
                
                return true; 
            }
                      
            return false;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }   
    }
    
    public boolean agregarProducto(Inventario inventario){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();    
        String sql = String.format("CALL insertarProductos(?,?,?,?,?,?,?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, inventario.getCodigoProducto());
            ps.setString(2, inventario.getNombreProducto());
            ps.setString(3, inventario.getDescripcion());
            ps.setInt(4, inventario.getStock());
            ps.setFloat(5, inventario.getPrecio());
            ps.setString(6, inventario.getCodigoProveedor());
            ps.setString(7, inventario.getClaveCategoria());
            
            ps.execute();
            return true;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }         
    }
    
    public boolean actualizar(Inventario inventario){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion(); 
        
        String sql = "UPDATE productos SET NombreProducto=?, Descripcion=?, Stock=?, Precio=?, CodigoProveedor=?, claveCategoria=? WHERE codigoProducto=?";
        try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(7, inventario.getCodigoProducto());    
            ps.setString(1, inventario.getNombreProducto()); 
            ps.setString(2, inventario.getDescripcion()); 
            ps.setInt(3, inventario.getStock());
            ps.setFloat(4, inventario.getPrecio()); 
            ps.setString(5, inventario.getCodigoProveedor()); 
            ps.setString(6, inventario.getClaveCategoria()); 
            ps.execute(); 
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        } finally{
            try{
                conexion.close();
            } catch(SQLException e){
                System.err.println(e);
            }
        }
    }
    public boolean buscar(Inventario inventario){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();      
       
       String sql = "SELECT * FROM productos WHERE CodigoProducto=?";
       
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, inventario.getCodigoProducto());  
            rs = ps.executeQuery();
            
            if(rs.next()){
                inventario.setCodigoProducto(rs.getString("CodigoProducto"));
                inventario.setNombreProducto(rs.getString("NombreProducto")); 
                inventario.setDescripcion(rs.getString("Descripcion")); 
                inventario.setStock(Integer.parseInt(rs.getString("Stock")));
                inventario.setPrecio(Float.parseFloat(rs.getString("Precio"))); 
                inventario.setCodigoProveedor(rs.getString("CodigoProveedor")); 
                inventario.setClaveCategoria(rs.getString("ClaveCategoria")); 
                return true;
            }  
            return false;
            
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        } finally{
            try{
                conexion.close();
            } catch(SQLException e){
                System.err.println(e);
            }
        }
     
    }    
    public boolean eliminar(Inventario inventario){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();  
       
       String sql = "DELETE FROM productos WHERE CodigoProducto=?";
       try{
        ps = conexion.prepareStatement(sql);      
        ps.setString(1, inventario.getCodigoProducto());  
        ps.execute(); 
        return true;           
       }catch(SQLException e){
            System.err.println(e);
            return false;
        } finally{
            try{
                conexion.close();
            } catch(SQLException e){
                System.err.println(e);
            }
        }    
    }  
    
    public boolean valorTotalInv(Inventario inventario){
         PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();    
        
        String sql = String.format("CALL costoTotalInv();");  
        try{
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                inventario.setPrecio(Float.parseFloat(rs.getString("SUM(Precio*Stock)")));
                
                return true; 
            }
                      
            return false;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }          
    }
    
    public boolean cantidadStock(Inventario inventario){
         PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();    
        
        String sql = String.format("CALL totalProductosInv();");  
        try{
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                inventario.setStock(Integer.parseInt(rs.getString("SUM(Stock)")));
                
                return true; 
            }
                      
            return false;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }          
    }    
    

}
