using System;
using System.Collections.Generic;
using System.Text;

namespace GranjasCleo2.Model
{
    public class Usuario
    {
        public string Nombre { get; set; }
        public string ApellidoPaterno { get; set; }
        public string ApellidoMaterno { get; set; }
        public string Correo { get; set; }

        public string TipoUsuario { get; set; } = "Cliente";
    }
}
