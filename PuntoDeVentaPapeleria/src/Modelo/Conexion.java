/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author itzel
 */
public class Conexion {
    private final String base = "ohmymarkers3";
    private final String user = "root";
    private final String password = "Itzel12527";
    private final String url = "jdbc:mysql://localhost:3306/" + base;
    private Connection con = null;

    
    public Connection getConexion(){       
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password); 
            System.out.println("Conexión lista");
        }catch(SQLException e){
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return con;    
    }    
}
