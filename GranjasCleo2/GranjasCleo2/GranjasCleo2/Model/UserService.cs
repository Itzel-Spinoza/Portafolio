using System;
using System.Collections.Generic;
using System.Text;

namespace GranjasCleo2.Model
{
    public class UserService : IUserService
    {
        public string UserEmail { get; set; }
        public string TipoUsuario { get; set; }
    }

}
