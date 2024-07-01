using System;
using System.Collections.Generic;
using System.Text;

namespace GranjasCleo2.Model
{
    public class ProductoPedido
    {
        internal DateTime FechaPedido;
        public string ImagenPedido { get; set; }
        public string CorreoCliente { get; set; }
        public string NombreProducto { get; set; }
        public int Cantidad { get; set; }
        public double PrecioUnitario { get; set; }
        public double Subtotal { get; set; }
        public string PedidoId { get; set; }
    }
}
