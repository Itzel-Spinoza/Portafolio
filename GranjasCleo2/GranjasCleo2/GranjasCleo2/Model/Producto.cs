using System;
using System.Collections.Generic;
using System.Text;

namespace GranjasCleo2.Model
{
    public class Producto
    {
        public string NombreProducto { get; set; }
        public string CategoriaProducto { get; set; }
        public string Subcategoria { get; set; }
        public string Descripcion { get; set; }
        public string Imagen { get; set; }
        public int Precio { get; set; }
        public int Stock { get; set; }

        public string Idproducto { get; set; }
    }
}
