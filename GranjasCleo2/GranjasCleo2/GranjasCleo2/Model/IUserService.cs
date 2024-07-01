using System;
using System.Collections.Generic;
using System.Text;

namespace GranjasCleo2.Model
{
    public interface IUserService
    {
        string UserEmail { get; set; }
        string TipoUsuario { get; set; }
    }
}

