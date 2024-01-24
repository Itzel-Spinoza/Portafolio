/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Proveedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author itzel
 */
public class ConsultasProveedores extends Conexion{
    PreparedStatement ps; 
    ResultSet rs; 
    Connection conexion; 
    Conexion conectar = new Conexion();      
    Proveedores proveedores = new Proveedores(); 
    
    public java.util.List listarProveedores(){
        java.util.List<Proveedores> datos = new ArrayList<>();   
        try{
            conexion = conectar.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM vistaProveedores");
            rs = ps.executeQuery();
            while (rs.next()){
                Proveedores proveedores = new Proveedores();
                proveedores.setCodigoProveedor(rs.getString(1));
                proveedores.setCompania(rs.getString(2));
                proveedores.setNombreContacto(rs.getString(3));
                proveedores.setApellidoPat(rs.getString(4));
                proveedores.setApellidoMat(rs.getString(5));
                proveedores.setCalle(rs.getString(6));
                proveedores.setNoExterior(rs.getString(7));
                proveedores.setColonia(rs.getString(8));
                proveedores.setCP(rs.getString(9));
                proveedores.setMunicipio(rs.getString(10));
                proveedores.setEstado(rs.getString(11));              
                proveedores.setTelefono(rs.getString(12));
                proveedores.setEmail(rs.getString(13));
                
                
              
                datos.add(proveedores);
            }
        } catch (Exception e){
            
        }
        return datos;        
        
    }
    
    public boolean hacerCodigoProv(Proveedores proveedores){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();    

        String sql = String.format("CALL registrarNuevoProv();");  
        try{
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                proveedores.setCodigoProveedor(rs.getString("CodigoProveedor"));
                
                return true; 
            }
                      
            return false;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }           
    }
    
    public boolean agregarProveedor(Proveedores proveedores){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();    
        String sql = String.format("CALL insertarProveedor2(?,?,?,?,?,?,?,?,?,?,?,?,?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, proveedores.getCodigoProveedor());
            ps.setString(2, proveedores.getCompania());
            ps.setString(3, proveedores.getNombreContacto());
            ps.setString(4, proveedores.getApellidoPat());
            ps.setString(5, proveedores.getApellidoMat());
            ps.setString(6, proveedores.getCalle());
            ps.setString(7, proveedores.getNoExterior());
            ps.setString(8, proveedores.getColonia());
            ps.setString(9, proveedores.getCP());
            ps.setString(10, proveedores.getMunicipio());
            ps.setString(11, proveedores.getEstado());
            ps.setString(12, proveedores.getTelefono());
            ps.setString(13, proveedores.getEmail());
                        
            ps.execute();
            return true;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }                 
    }

    public boolean actualizarProveedor(Proveedores proveedores){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();    
        String sql = String.format("CALL actualizarProveedor(?,?,?,?,?,?,?,?,?,?,?,?,?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, proveedores.getCodigoProveedor());
            ps.setString(2, proveedores.getCompania());
            ps.setString(3, proveedores.getNombreContacto());
            ps.setString(4, proveedores.getApellidoPat());
            ps.setString(5, proveedores.getApellidoMat());
            ps.setString(6, proveedores.getCalle());
            ps.setString(7, proveedores.getNoExterior());
            ps.setString(8, proveedores.getColonia());
            ps.setString(9, proveedores.getCP());
            ps.setString(10, proveedores.getMunicipio());
            ps.setString(11, proveedores.getEstado());
            ps.setString(12, proveedores.getTelefono());
            ps.setString(13, proveedores.getEmail());
                        
            ps.execute();
            return true;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }                 
    }
    
    public boolean buscar(Proveedores proveedores){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();      
       
       String sql = "SELECT * FROM vistaProveedores WHERE CodigoProveedor=?";
       
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, proveedores.getCodigoProveedor());  
            rs = ps.executeQuery();
            if(rs.next()){
                proveedores.setCodigoProveedor(rs.getString("CodigoProveedor"));
                proveedores.setCompania(rs.getString("Compañia")); 
                proveedores.setNombreContacto(rs.getString("NombreContacto")); 
                proveedores.setApellidoPat(rs.getString("ApellidoPat")); 
                proveedores.setApellidoMat(rs.getString("ApellidoMat"));
                proveedores.setCalle(rs.getString("Calle"));
                proveedores.setNoExterior(rs.getString("NoExterior"));
                proveedores.setColonia(rs.getString("Colonia"));
                proveedores.setCP(rs.getString("CP"));
                proveedores.setMunicipio(rs.getString("Municipio"));
                proveedores.setEstado(rs.getString("Estado"));
                proveedores.setTelefono(rs.getString("telefono"));
                proveedores.setEmail(rs.getString("eMail"));
                
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
    
    
    
    
}
