using System;
using System.Collections.Generic;
using System.Text;

namespace GranjasCleo2.Model
{
    public class PedidoGeneral
    {
        public string CorreoCliente { get; set; }
        public string NombreCliente { get; set; }
        public string Calle { get; set; }
        public string Colonia { get; set; }
        public string NoExterior { get; set; }
        public string CP { get; set; }
        public string Municipio { get; set; }
        public string Estado { get; set; }
        public string IdPedido { get; set; }
        public DateTime FechaPedido { get; set; }
        public double TotalPedido { get; set; }
    }
}
