/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author itzel
 */
public class ConsultasClientes extends Conexion{
    PreparedStatement ps; 
    ResultSet rs; 
    Connection conexion; 
    Conexion conectar = new Conexion();   
    Clientes clientes = new Clientes(); 
    
    public java.util.List listar() {
        java.util.List<Clientes> datos = new ArrayList<>();
        try{
            conexion = conectar.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM vistaClientes");
            rs = ps.executeQuery();
            while (rs.next()){
                Clientes clientes = new Clientes();
                clientes.setNoCliente(rs.getString(1));
                clientes.setNombre(rs.getString(2)); 
                clientes.setApellidoPat(rs.getString(3)); 
                clientes.setApellidoMat(rs.getString(4));
                clientes.setCalle(rs.getString(5));
                clientes.setNoExt(rs.getString(6));
                clientes.setColonia(rs.getString(7));
                clientes.setCP(rs.getString(8));
                clientes.setMunicipio(rs.getString(9));
                clientes.setEstado(rs.getString(10));
                clientes.setNoTelefono(rs.getString(11)); 

                datos.add(clientes);
            }
        } catch (Exception e){
            
        }
        return datos;
    } 
    
    public boolean hacerCodigoClientes(Clientes clientes){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();    
        
        String sql = String.format("CALL generarCodigoCliente();");  
        try{
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                clientes.setNoCliente(rs.getString("NoCliente"));
                
                return true; 
            }
                      
            return false;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }           
    }
    
    public boolean agregarCliente(Clientes clientes){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion(); 
        String sql = String.format("CALL insertarDatosClientes(?,?,?,?,?,?,?,?,?,?,?);");     
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, clientes.getNoCliente());
            ps.setString(2, clientes.getNombre());
            ps.setString(3, clientes.getApellidoPat());
            ps.setString(4, clientes.getApellidoMat());
            ps.setString(5, clientes.getCalle());
            ps.setString(6, clientes.getNoExt());
            ps.setString(7, clientes.getColonia());
            ps.setString(8, clientes.getCP());
            ps.setString(9, clientes.getMunicipio());
            ps.setString(10, clientes.getEstado());
            ps.setString(11, clientes.getNoTelefono());
                        
            ps.execute();
            return true;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }        
    }
    
    public boolean actualizarCliente(Clientes clientes){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion(); 
        String sql = String.format("CALL actualizarClientes(?,?,?,?,?,?,?,?,?,?,?);");     
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, clientes.getNoCliente());
            ps.setString(2, clientes.getNombre());
            ps.setString(3, clientes.getApellidoPat());
            ps.setString(4, clientes.getApellidoMat());
            ps.setString(5, clientes.getCalle());
            ps.setString(6, clientes.getNoExt());
            ps.setString(7, clientes.getColonia());
            ps.setString(8, clientes.getCP());
            ps.setString(9, clientes.getMunicipio());
            ps.setString(10, clientes.getEstado());
            ps.setString(11, clientes.getNoTelefono());
                        
            ps.execute();
            return true;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }              
    }
    
    public boolean buscar(Clientes clientes){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();      
       
       String sql = "SELECT * FROM vistaClientes WHERE NoCliente=?";
       
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
                clientes.setNoExt(rs.getString("NoExterior"));
                clientes.setColonia(rs.getString("Colonia"));
                clientes.setCP(rs.getString("CP"));
                clientes.setMunicipio(rs.getString("Municipio"));
                clientes.setEstado(rs.getString("Estado"));
                clientes.setNoTelefono(rs.getString("NoTelefono"));
                return true;
            }  
            return false;
            
       }catch(SQLException e){     
            System.err.println(e);
            return false;
        }       
    }
}
