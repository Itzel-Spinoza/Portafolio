/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Controlador.Inventario;
import java.sql.ResultSet;
import Controlador.NuevaEntrega;
import java.sql.SQLException;


/**
 *
 * @author itzel
 */
public class ConsultasNuevaEntrega extends Conexion{
    PreparedStatement ps; 
    ResultSet rs; 
    Connection conexion; 
    Conexion conectar = new Conexion();   
    NuevaEntrega nuevaEntrega = new NuevaEntrega(); 
    Inventario inventario = new Inventario();    
    
    public boolean iniciarEntrega(NuevaEntrega nuevaEntrega){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();   

        String sql = String.format("CALL hacerNuevaEntrega(?);");   
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevaEntrega.getNoCliente());
            rs = ps.executeQuery();
            if(rs.next()){
                nuevaEntrega.setNoEntrega(rs.getString("NoEntrega"));
                nuevaEntrega.setNoEmpleado(rs.getString("NoEmpleado"));
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
    
    public boolean agregarProducto(NuevaEntrega nuevaEntrega){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();      
        
        String sql = String.format("CALL agregarProductoaEntrega(?,?,?);");  
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevaEntrega.getNoEntrega());
            ps.setString(2, nuevaEntrega.getCodigoProducto());
            ps.setInt(3, nuevaEntrega.getCantidadE());
            ps.execute();
    
            
   
            
            return true;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }      
    }   
    
    public boolean listarProductoExiste(NuevaEntrega nuevaEntrega){
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        Connection conexion = getConexion();  
        String sql = String.format("CALL productoEntrega(?,?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevaEntrega.getNoEntrega());
            ps.setString(2, nuevaEntrega.getCodigoProducto());
            rs = ps.executeQuery();
            if(rs.next()){
                
                nuevaEntrega.setCodigoProducto(rs.getString("codigoProducto"));
                nuevaEntrega.setNombreProducto(rs.getString("NombreProducto"));
                nuevaEntrega.setCantidadE(rs.getInt("CantidadE")); 
                nuevaEntrega.setPrecioProductoE(rs.getFloat("PrecioProductoE")); 
                nuevaEntrega.setSubtotalE(rs.getFloat("SubtotalE")); 
   
                return true; 
            }
      
            return false;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }               
    }
    
    public boolean sacarSubtotal(NuevaEntrega nuevaEntrega){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();      
        
        String sql = String.format("CALL subtotalDeLaEntrega(?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevaEntrega.getNoEntrega());
            rs = ps.executeQuery();
            if(rs.next()){
                nuevaEntrega.setSubtotalE(rs.getInt("SUM(SubtotalE)"));
   
                return true; 
            }
      
            return false;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }              
    }
    
    public boolean registrarEntrega(NuevaEntrega nuevaEntrega){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();  
        String sql = String.format("CALL registrarEntrega(?,?,?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevaEntrega.getNoEntrega());
            ps.setFloat(2, nuevaEntrega.getTotalEntrega()); 
            ps.setString(3, nuevaEntrega.getNoCliente());

            ps.execute();
            return true;
        }catch(SQLException e){
            
            System.err.println(e);
            return false;
            
        }              
    }
    
    public boolean borrarProductoEntrega(NuevaEntrega nuevaEntrega){
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        Connection conexion = getConexion();    
        
        String sql = String.format("CALL eliminarProductoEntrega(?,?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevaEntrega.getNoEntrega());
            ps.setString(2, nuevaEntrega.getCodigoProducto());
            ps.execute();
            return true;
        }catch(SQLException e){
            
            System.err.println(e);
            return false;
            
        }         
    } 
    
    public boolean eliminarEntrega (NuevaEntrega nuevaEntrega){
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        Connection conexion = getConexion();    
        
        String sql = String.format("CALL eliminarEntrega(?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevaEntrega.getNoEntrega());
      
            ps.execute();
            return true;
        }catch(SQLException e){
            
            System.err.println(e);
            return false;
            
        }                 
    }
    
    public boolean envioDomicilio(NuevaEntrega nuevaEntrega){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();   
       
       String sql = "CALL aDomicilio(?)";
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, nuevaEntrega.getNoEntrega());  

            rs = ps.executeQuery();
            return true;
      
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        }              
    }   
    
    public boolean puntoEntrega(NuevaEntrega nuevaEntrega){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();   
       
       String sql = "CALL puntoEntre(?)";
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, nuevaEntrega.getNoEntrega());  

            rs = ps.executeQuery();
            return true;
      
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        }              
    }     
    
    public boolean paqueteria(NuevaEntrega nuevaEntrega){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();   
       
       String sql = "CALL paqueteria(?)";
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, nuevaEntrega.getNoEntrega());  

            rs = ps.executeQuery();
            return true;
      
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        }              
    }     
    
    public boolean metoEnEfecti(NuevaEntrega nuevaEntrega){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();   
       
       String sql = "CALL enEfecti(?)";
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, nuevaEntrega.getNoEntrega());  

            rs = ps.executeQuery();
            return true;
      
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        }              
    }    
    
    public boolean transferencia(NuevaEntrega nuevaEntrega){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();   
       
       String sql = "CALL transfe(?)";
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, nuevaEntrega.getNoEntrega());  

            rs = ps.executeQuery();
            return true;
      
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        }              
    }     
}
