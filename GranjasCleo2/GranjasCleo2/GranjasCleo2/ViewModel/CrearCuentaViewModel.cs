using Firebase.Auth;
using Firebase.Database;
using Firebase.Database.Query;
using GranjasCleo2.Model;
using GranjasCleo2.Vista;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace GranjasCleo2.ViewModel
{
    class CrearCuentaViewModel : BaseViewModel
    {
        private string nombre;
        private string apellidoPaterno;
        private string apellidoMaterno;
        private string correo;
        private string contraseña;
        private string confirmarContraseña;

        public string Nombre
        {
            get { return nombre; }
            set { SetProperty(ref nombre, value); }
        }
        public string ApellidoPaterno
        {
            get { return apellidoPaterno; }
            set { SetProperty(ref apellidoPaterno, value); }
        }
        public string ApellidoMaterno
        {
            get { return apellidoMaterno; }
            set { SetProperty(ref apellidoMaterno, value); }
        }
        public string Correo
        {
            get { return correo; }
            set { SetProperty(ref correo, value); }
        }
        public string Contraseña
        {
            get { return contraseña; }
            set { SetProperty(ref contraseña, value); }
        }
        public string ConfirmarContraseña
        {
            get { return confirmarContraseña; }
            set { SetProperty(ref confirmarContraseña, value); }
        }

        public Command RegistrarCommand { get; }

        public CrearCuentaViewModel()
        {
            RegistrarCommand = new Command(async () => await RegistrarUsuario());
        }

        private async Task RegistrarUsuario()
        {
            if (Contraseña != ConfirmarContraseña)
            {
                await Application.Current.MainPage.DisplayAlert("Error", "Las contraseñas no coinciden.", "Aceptar");
                return;
            }

            try
            {
                var authProvider = new FirebaseAuthProvider(new FirebaseConfig("AIzaSyBPYobdEZQTpHGBjP4ru3gqzzgS1tUBMcE"));
                var auth = await authProvider.CreateUserWithEmailAndPasswordAsync(Correo, Contraseña);
                var token = auth.FirebaseToken;

                // Enviar correo de verificación
                await authProvider.SendEmailVerificationAsync(token);

                await Application.Current.MainPage.DisplayAlert("Éxito", "Cuenta creada exitosamente. Por favor, verifica tu correo electrónico.", "Aceptar");

                // Registrar datos del usuario en la base de datos
                var usuario = new Usuario
                {
                    Nombre = Nombre,
                    ApellidoPaterno = ApellidoPaterno,
                    ApellidoMaterno = ApellidoMaterno,
                    Correo = Correo,
                    TipoUsuario = "Cliente"
                };

                var database = new FirebaseClient("https://granjas-cleo-default-rtdb.firebaseio.com/");
                await database
                    .Child("usuarios")
                    .Child(auth.User.LocalId)
                    .PutAsync(usuario);

                // Navegar a la página de inicio de sesión o principal después de registrar los datos
                var navigationPage = new NavigationPage(new IniciarSesion())
                {
                    BarBackgroundColor = Color.RoyalBlue
                };
                Application.Current.MainPage = navigationPage;
            }
            catch (Exception ex)
            {
                await Application.Current.MainPage.DisplayAlert("Error", $"Exception occurred while processing the request: {ex.Message}", "Aceptar");
            }
        }
    }
}



