/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.Empleados;
import Controlador.Inventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author itzel
 */
public class ConsultasEmpleados extends Conexion{
    PreparedStatement ps; 
    ResultSet rs; 
    Connection conexion; 
    Conexion conectar = new Conexion();      
    Empleados empleados = new Empleados(); 
    
    
    public java.util.List listarEmpleados(){
        java.util.List<Empleados> datos = new ArrayList<>();
        try{
            conexion = conectar.getConexion();
            ps = conexion.prepareStatement("SELECT * FROM vistaEmpleados");
            rs = ps.executeQuery();
            while (rs.next()){
                Empleados empleados = new Empleados();
                empleados.setNoEmpleado(rs.getString(1));
                empleados.setNombre(rs.getString(2));
                empleados.setApellidoPat(rs.getString(3));
                empleados.setApellidoMat(rs.getString(4));
                empleados.setCalle(rs.getString(5));
                empleados.setNoExterior(rs.getString(6));
                empleados.setColonia(rs.getString(7));
                empleados.setCP(rs.getString(8));
                empleados.setMunicipio(rs.getString(9));
                empleados.setEstado(rs.getString(10));
                empleados.setSueldo(Float.parseFloat(rs.getString(11)));
                empleados.setPuesto(rs.getString(12));
                empleados.setCURP(rs.getString(13));
                empleados.setNoTelefono(rs.getString(14));
                
                
              
                datos.add(empleados);
            }
        } catch (Exception e){
            
        }
        return datos;
    } 
    
    public boolean hacerCodigoEmp(Empleados empleados){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();    
        
        String sql = String.format("CALL generarCodigoEmpleado();");  
        try{
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                empleados.setNoEmpleado(rs.getString("NoEmpleado"));
                
                return true; 
            }
                      
            return false;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }   
    }  
    public boolean agregarProducto(Empleados empleados){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();    
        String sql = String.format("CALL insertarDatosEmpleado2(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, empleados.getNoEmpleado());
            ps.setString(2, empleados.getNombre());
            ps.setString(3, empleados.getApellidoPat());
            ps.setString(4, empleados.getApellidoMat());
            ps.setString(5, empleados.getCalle());
            ps.setString(6, empleados.getNoExterior());
            ps.setString(7, empleados.getColonia());
            ps.setString(8, empleados.getCP());
            ps.setString(9, empleados.getMunicipio());
            ps.setString(10, empleados.getEstado());
            ps.setFloat(11, empleados.getSueldo());
            ps.setString(12, empleados.getPuesto());
            ps.setString(13, empleados.getCURP());
            ps.setString(14, empleados.getNoTelefono());
                        
            ps.execute();
            return true;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }         
    }  
    
    public boolean actualizarEmpleado(Empleados empleados){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConexion();      
        
        String sql = String.format("CALL actualizarEmpleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?);");  
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, empleados.getNoEmpleado());
            ps.setString(2, empleados.getNombre());
            ps.setString(3, empleados.getApellidoPat());
            ps.setString(4, empleados.getApellidoMat());
            ps.setString(5, empleados.getCalle());
            ps.setString(6, empleados.getNoExterior());
            ps.setString(7, empleados.getColonia());
            ps.setString(8, empleados.getCP());
            ps.setString(9, empleados.getMunicipio());
            ps.setString(10, empleados.getEstado());
            ps.setFloat(11, empleados.getSueldo());
            ps.setString(12, empleados.getPuesto());
            ps.setString(13, empleados.getCURP());
            ps.setString(14, empleados.getNoTelefono());
            ps.execute();
                 
            return true;            
        }catch(SQLException e){      
            System.err.println(e);
            return false;     
        }              
    }
    
    public boolean eliminar(Empleados empleados){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();  
       
       String sql = "DELETE FROM empleados WHERE NoEmpleado=?";
       try{
        ps = conexion.prepareStatement(sql);      
        ps.setString(1, empleados.getNoEmpleado());
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
    
    public boolean buscar(Empleados empleados){
       PreparedStatement ps = null; 
       ResultSet rs = null; 
       Connection conexion = getConexion();      
       
       String sql = "SELECT * FROM vistaEmpleados WHERE NoEmpleado=?";
       
       try{
            ps = conexion.prepareStatement(sql);      
            ps.setString(1, empleados.getNoEmpleado()); 
            rs = ps.executeQuery();
            
            if(rs.next()){
                empleados.setNoEmpleado(rs.getString("NoEmpleado"));
                empleados.setNombre(rs.getString("Nombre")); 
                empleados.setApellidoPat(rs.getString("ApellidoPat")); 
                empleados.setApellidoMat(rs.getString("ApellidoMat"));
                empleados.setCalle(rs.getString("Calle"));
                empleados.setNoExterior(rs.getString("NoExterior"));
                empleados.setColonia(rs.getString("Colonia"));
                empleados.setCP(rs.getString("CP"));
                empleados.setMunicipio(rs.getString("Municipio"));
                empleados.setEstado(rs.getString("Estado"));
                empleados.setSueldo(Float.parseFloat(rs.getString("Sueldo")));
                empleados.setPuesto(rs.getString("Puesto"));
                empleados.setCURP(rs.getString("CURP"));
                empleados.setNoTelefono(rs.getString("NoTelefono"));
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
    
