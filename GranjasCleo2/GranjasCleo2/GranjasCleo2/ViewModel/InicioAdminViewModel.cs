using Firebase.Database;
using Firebase.Database.Query;
using GranjasCleo2.Model;
using GranjasCleo2.View;
using System;
using System.Collections.ObjectModel;
using System.Threading.Tasks;
using System.Windows.Input;
using Xamarin.Forms;

namespace GranjasCleo2.ViewModel
{
    public class InicioAdminViewModel : BaseViewModel
    {
        private readonly FirebaseClient firebase;
        private ObservableCollection<Producto> productos;

        public ObservableCollection<Producto> Productos
        {
            get { return productos; }
            set { SetProperty(ref productos, value); }
        }

        public ICommand NavigateCommand { get; }
        public ICommand VerPedidosCommand { get; }
        public ICommand LogoutCommand { get; }

        public InicioAdminViewModel()
        {
            firebase = new FirebaseClient("https://granjas-cleo-default-rtdb.firebaseio.com/");
            Productos = new ObservableCollection<Producto>();
            NavigateCommand = new Command<object>(async (param) => await Navigate(param));
            VerPedidosCommand = new Command(async () => await VerPedidos());
            LogoutCommand = new Command(async () => await Logout());

            // Load products when the ViewModel is created
            LoadProductosCommand = new Command(async () => await LoadProductos());
            LoadProductosCommand.Execute(null);
        }

        public Command LoadProductosCommand { get; }

        private async Task LoadProductos()
        {
            try
            {
                var productosData = await firebase
                    .Child("Productos")
                    .OnceAsync<Producto>();

                Productos.Clear();
                foreach (var producto in productosData)
                {
                    var p = producto.Object;
                    p.Idproducto = producto.Key; // Asignar la clave de Firebase como Idproducto
                    Productos.Add(p);
                }
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine($"Error al cargar los productos: {ex.Message}");
                await Application.Current.MainPage.DisplayAlert("Error", "Hubo un problema al cargar los productos.", "OK");
            }
        }

        private async Task Navigate(object param)
        {
            if (param is Producto producto)
            {
                await Application.Current.MainPage.Navigation.PushAsync(new EditarProducto(producto));
            }
            else if (param is string pageName && pageName == "AgregarProducto")
            {
                await Application.Current.MainPage.Navigation.PushAsync(new AgregarProducto());
            }
        }

        private async Task VerPedidos()
        {
            await Application.Current.MainPage.Navigation.PushAsync(new VerPedidosAdmin());
        }

        private async Task Logout()
        {
            // Lógica de cierre de sesión
            await Application.Current.MainPage.DisplayAlert("Cerrar Sesión", "Sesión cerrada correctamente", "OK");

            // Navegar a la página de inicio de sesión
            Application.Current.MainPage = new NavigationPage(new MainPage());
        }
    }
}
