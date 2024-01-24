/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Clientes;
import Controlador.Entregas;
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
public class ConsultasEntregas extends Conexion{
    PreparedStatement ps; 
    ResultSet rs; 
    Connection conexion; 
    Conexion conectar = new Conexion();
    Entregas entregas = new Entregas(); 
    
    public java.util.List listarEntregas(){
        java.util.List<Entregas> datos = new ArrayList<>();
        try{
            conexion = conectar.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM vistaEntregas");
            rs = ps.executeQuery();
            while (rs.next()){
                Entregas entregas = new Entregas();
                entregas.setNoEntrega(rs.getString(1));
                entregas.setFechaPedido(rs.getString(2));
                entregas.setFechaEntrega(rs.getString(3));
                entregas.setTotalEntrega(Float.parseFloat(rs.getString(4)));
                entregas.setNoEmpleado(rs.getString(5)); 
                entregas.setNoCliente(rs.getString(6));
                entregas.setTipoEnvio(rs.getString(7));
                entregas.setMetodoPago(rs.getString(8));
                entregas.setEstatus(rs.getString(9));
                
                datos.add(entregas);
            }
        } catch (Exception e){
            
        }
        return datos;        
    }
    
    public List<Entregas> listarProductosEntregas() {
        java.util.List<Entregas> datos = new ArrayList<>();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion(); 
        try{
            
            conexion = conectar.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM existe");
            rs = ps.executeQuery();
            while (rs.next()){
                Entregas entregas = new Entregas();
                entregas.setNoEntrega(rs.getString(1));
                entregas.setCodigoProducto(rs.getString(2));
                entregas.setNombreProducto(rs.getString(3)); 
                entregas.setCantidadE(Integer.parseInt(rs.getString(4)));
                entregas.setPrecioProductoE(Float.parseFloat(rs.getString(5)));
                entregas.setSubtotalE(Float.parseFloat(rs.getString(6)));

                datos.add(entregas);        
            }
        } catch (Exception e){
            System.err.println(e);
                          
        }
        return datos;
        
    }      
    
    public boolean datosClientes(Clientes clientes){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();   
       
       String sql = "CALL datosEntregas(?)";
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, clientes.getNoCliente());  
            rs = ps.executeQuery();
            
            if(rs.next()){
                clientes.setNoCliente(rs.getString("NoCliente"));
                clientes.setNombre(rs.getString("Nombre"));
                clientes.setApellidoPat(rs.getString("ApellidoPat"));
                clientes.setApellidoMat(rs.getString("ApellidoMat"));
                clientes.setCalle(rs.getString("Calle"));
                clientes.setNoExt(rs.getString("noExterior"));
                clientes.setColonia(rs.getString("colonia"));
                clientes.setCP(rs.getString("CP"));
                clientes.setMunicipio(rs.getString("Municipio"));
                clientes.setEstado(rs.getString("Estado"));

                return true;
            }  
            return false;
            
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        }             
    }
    
    
    public boolean subtotalTotalEntregas(Entregas entregas){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();   
       
       String sql = "CALL subtotalTotalEntrega(?)";
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, entregas.getNoEntrega());  
            rs = ps.executeQuery();
            
            if(rs.next()){
                entregas.setSubtotalE(Float.parseFloat(rs.getString("SUM(SubtotalE)")));
                
                return true;
            }  
            return false;
            
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        }             
    }    
    
    
    public boolean actualizarEntregas(Entregas entregas){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();   
       
       String sql = "CALL actualizarEntrega(?,?)";
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, entregas.getNoEntrega());  
            ps.setString(2, entregas.getFechaEntrega());
            
            rs = ps.executeQuery();
            return true;
          
            
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        }             
    }
    
    public boolean estadoProcesando(Entregas entregas){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();   
       
       String sql = "CALL estadoProce(?)";
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, entregas.getNoEntrega());  

            rs = ps.executeQuery();
            return true;
      
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        }              
    }

    public boolean estadoEnProceso(Entregas entregas){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();   
       
       String sql = "CALL estadoEnProceso(?)";
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, entregas.getNoEntrega());  

            rs = ps.executeQuery();
            return true;
      
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        }              
    }  
    
    public boolean estadoNoEntre(Entregas entregas){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();   
       
       String sql = "CALL estadoNoEntre(?)";
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, entregas.getNoEntrega());  

            rs = ps.executeQuery();
            return true;
      
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        }              
    }

    public boolean estadoEntre(Entregas entregas){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();   
       
       String sql = "CALL estadoEntregado(?)";
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, entregas.getNoEntrega());  

            rs = ps.executeQuery();
            return true;
      
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        }              
    }        
}
