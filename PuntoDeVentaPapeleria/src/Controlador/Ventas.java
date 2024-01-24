/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author itzel
 */
public class Ventas {
    private String NoVenta; 
    private String FechaVenta; 
    private float TotalVenta; 
    private String NoEmpleado; 
    
    private String CodigoProducto; 
    private String NombreProducto; 
    private float Precio; 
    private int Cantidad; 
    private float Subtotal; 

    public String getNoVenta() {
        return NoVenta;
    }

    public void setNoVenta(String NoVenta) {
        this.NoVenta = NoVenta;
    }

    public String getFechaVenta() {
        return FechaVenta;
    }

    public void setFechaVenta(String FechaVenta) {
        this.FechaVenta = FechaVenta;
    }

    public float getTotalVenta() {
        return TotalVenta;
    }

    public void setTotalVenta(float TotalVenta) {
        this.TotalVenta = TotalVenta;
    }

    public String getNoEmpleado() {
        return NoEmpleado;
    }

    public void setNoEmpleado(String NoEmpleado) {
        this.NoEmpleado = NoEmpleado;
    }

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

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public float getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(float Subtotal) {
        this.Subtotal = Subtotal;
    }
    
    
    
}
