/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author itzel
 */
public class Inventario {
    private String CodigoProducto; 
    private String NombreProducto; 
    private String Descripcion; 
    private int Stock; 
    private float Precio; 
    private String CodigoProveedor; 
    private String ClaveCategoria; 

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String CodigoProducto) {
        this.CodigoProducto = CodigoProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    public String getCodigoProveedor() {
        return CodigoProveedor;
    }

    public void setCodigoProveedor(String CodigoProveedor) {
        this.CodigoProveedor = CodigoProveedor;
    }

    public String getClaveCategoria() {
        return ClaveCategoria;
    }

    public void setClaveCategoria(String ClaveCategoria) {
        this.ClaveCategoria = ClaveCategoria;
    }
    
    
}
