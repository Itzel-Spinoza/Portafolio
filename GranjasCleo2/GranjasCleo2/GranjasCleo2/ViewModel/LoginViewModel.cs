using Firebase.Auth;
using Firebase.Database;
using Firebase.Database.Query;
using GranjasCleo2.Model;
using GranjasCleo2.View;
using System;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Input;
using Xamarin.Forms;

namespace GranjasCleo2.ViewModel
{
    class LoginViewModel : BaseViewModel
    {
        private string email;
        private string clave;

        public string TxtEmail
        {
            get { return email; }
            set { SetProperty(ref email, value); }
        }
        public string TxtClave
        {
            get { return clave; }
            set { SetProperty(ref clave, value); }
        }

        public ICommand LoginCommand { get; }

        private readonly FirebaseClient firebase;

        public LoginViewModel()
        {
            firebase = new FirebaseClient("https://granjas-cleo-default-rtdb.firebaseio.com/");
            LoginCommand = new Command(async () => await LoginUsuario());
        }

        private async Task LoginUsuario()
        {
            var objusuario = new UserModel()
            {
                EmailField = TxtEmail,
                PasswordField = TxtClave,
            };

            try
            {
                var config = new FirebaseConfig("AIzaSyBPYobdEZQTpHGBjP4ru3gqzzgS1tUBMcE");
                var authProvider = new FirebaseAuthProvider(config);
                var authUser = await authProvider.SignInWithEmailAndPasswordAsync(objusuario.EmailField, objusuario.PasswordField);
                var user = authUser.User;

                if (!user.IsEmailVerified)
                {
                    await Application.Current.MainPage.DisplayAlert("Advertencia", "El correo electrónico no ha sido verificado. Por favor, verifica tu correo.", "Aceptar");
                    return;
                }

                string obtenerToken = authUser.FirebaseToken;

                // Obtener el tipo de usuario desde Firebase
                var tipoUsuario = await GetTipoUsuario(user.Email);

                if (string.IsNullOrEmpty(tipoUsuario))
                {
                    await Application.Current.MainPage.DisplayAlert("Error", "No se pudo obtener el tipo de usuario.", "Aceptar");
                    return;
                }

                // Guardar el correo y tipo de usuario en el servicio de usuario
                var userService = DependencyService.Get<IUserService>();
                userService.UserEmail = user.Email;
                userService.TipoUsuario = tipoUsuario;

                
                Page targetPage;
                if (tipoUsuario == "Administrador")
                {
                    targetPage = new InicioAdmin(); 
                }
                else 
                {
                    targetPage = new Inicio(); 
                }

                var navigationPage = new NavigationPage(targetPage)
                {
                    BarBackgroundColor = Color.RoyalBlue
                };
                Application.Current.MainPage = navigationPage;
            }
            catch (Exception ex)
            {
                await Application.Current.MainPage.DisplayAlert("Advertencia", $"Los datos introducidos son incorrectos o el usuario se encuentra inactivo. {ex.Message}", "Aceptar");
            }
        }

        private async Task<string> GetTipoUsuario(string email)
        {
            try
            {
                var usuarios = await firebase
                    .Child("usuarios")
                    .OnceAsync<Usuario>();

                if (usuarios == null || !usuarios.Any())
                {
                    System.Diagnostics.Debug.WriteLine("No se encontraron usuarios en la base de datos.");
                    return null;
                }

                foreach (var u in usuarios)
                {
                    var correo = u.Object?.Correo ?? "(null)";
                    var tipoUsuario = u.Object?.TipoUsuario ?? "(null)";
                    System.Diagnostics.Debug.WriteLine($"Correo: {correo}, TipoUsuario: {tipoUsuario}");
                }

                var user = usuarios.FirstOrDefault(u => u.Object?.Correo != null && u.Object.Correo.Equals(email, StringComparison.OrdinalIgnoreCase));

                if (user == null)
                {
                    System.Diagnostics.Debug.WriteLine($"No se encontró el usuario con correo: {email}");
                }
                else
                {
                    System.Diagnostics.Debug.WriteLine($"Usuario encontrado: {user.Object.Correo}, TipoUsuario: {user.Object.TipoUsuario}");
                }

                return user?.Object?.TipoUsuario;
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine($"Error al obtener el tipo de usuario: {ex.Message}");
                return null;
            }
        }
    }
}
