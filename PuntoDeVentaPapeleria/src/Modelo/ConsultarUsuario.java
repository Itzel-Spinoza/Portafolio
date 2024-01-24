
package Modelo;

import Controlador.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author itzel
 */
public class ConsultarUsuario extends Conexion {
    public boolean Login(Usuario usuario){
        PreparedStatement ps = null; 
        ResultSet rs = null; 
       Connection conexion = getConexion();
       
       String sql = "SELECT * FROM Usuarios WHERE NoEmpleado=?";
       
       try{
           ps = conexion.prepareStatement(sql);
           ps.setString(1, usuario.getCodigoEmpleado());
           
           rs = ps.executeQuery();
           
           if (rs.next()){
               if (usuario.getContraseña().equals(rs.getString(2))){
                  return true; 
               }
               else{
                   return false; 
               }
           }
           return false;          
       } catch (SQLException ex){
           Logger.getLogger(ConsultarUsuario.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
        
    }

    public boolean regEntrada(Usuario usuario){
        PreparedStatement ps = null; 
        ResultSet rs = null; 
        Connection conexion = getConexion();    
        
        String sql = String.format("CALL registrarEntrada(?);");
        try{
            ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario.getCodigoEmpleado());
            ps.execute();
            return true;
        }catch(SQLException e){
            
            System.err.println(e);
            return false;
            
        } 
        
    }
}
