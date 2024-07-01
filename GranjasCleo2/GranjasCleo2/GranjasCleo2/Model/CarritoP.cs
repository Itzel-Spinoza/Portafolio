using System;
using System.Collections.Generic;
using System.Text;

namespace GranjasCleo2.Model
{
    public class CarritoP
    {
        public string Correo { get; set; }
        public string NombreProducto { get; set; }
        public int Cantidad { get; set; }
        public double PrecioUnitario { get; set; }
        public double Subtotal { get; set; }
        public string Imagen { get; set; }
        public string Idproducto { get; set; }
        public string IdProductoCarrito { get; set; }

    }
}
