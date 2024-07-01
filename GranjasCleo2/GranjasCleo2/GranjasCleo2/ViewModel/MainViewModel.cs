using Firebase.Database;
using Firebase.Database.Query;
using GranjasCleo2.Model;
using GranjasCleo2.View;
using GranjasCleo2.Vista;
using System;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.Threading.Tasks;
using System.Windows.Input;
using Xamarin.Forms;

namespace GranjasCleo2.ViewModel
{
    class MainViewModel : BaseViewModel
    {
        private readonly FirebaseClient firebase;

        private ObservableCollection<Producto> productos;
        public ObservableCollection<Producto> Productos
        {
            get { return productos; }
            set { SetProperty(ref productos, value); }
        }

        public ICommand NavigateCommand { get; private set; }
        public ICommand LoadProductsCommand { get; private set; }
        public ICommand LogoutCommand { get; private set; }
        public ObservableCollection<CardModel> Cards { get; set; }

        public MainViewModel()
        {
            firebase = new FirebaseClient("https://granjas-cleo-default-rtdb.firebaseio.com/");
            Productos = new ObservableCollection<Producto>();
            Cards = new ObservableCollection<CardModel>
            {
                new CardModel { ImageUrl = "https://i.ibb.co/XXCBvp5/cono-huevo-rancho.png" },
                // Agrega más tarjetas según sea necesario
            };

            NavigateCommand = new Command<object>(Navigate);
            LoadProductsCommand = new Command(async () => await LoadProducts());
            LogoutCommand = new Command(async () => await Logout());
            LoadProductsCommand.Execute(null); // Ejecuta la carga de productos cuando se inicializa el ViewModel
        }

        public class CardModel
        {
            public string ImageUrl { get; set; }
        }

        private async Task LoadProducts()
        {
            try
            {
                var productosData = await firebase
                    .Child("Productos")
                    .OnceAsync<Producto>();

                if (productosData != null)
                {
                    Productos.Clear();
                    foreach (var producto in productosData)
                    {
                        Productos.Add(new Producto
                        {
                            NombreProducto = producto.Object.NombreProducto,
                            CategoriaProducto = producto.Object.CategoriaProducto,
                            Subcategoria = producto.Object.Subcategoria,
                            Descripcion = producto.Object.Descripcion,
                            Imagen = producto.Object.Imagen,
                            Precio = producto.Object.Precio,
                            Stock = producto.Object.Stock,
                            Idproducto = producto.Key // Asegurarse de guardar la clave Firebase como Idproducto
                        });
                    }
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine($"Error al cargar los productos: {ex.Message}");
            }
        }

        private async void Navigate(object parameter)
        {
            if (parameter is string pageName)
            {
                Page page;
                switch (pageName)
                {
                    case "IniciarSesion":
                        page = new IniciarSesion();
                        break;
                    case "CrearCuenta":
                        page = new CrearCuenta();
                        break;
                    case "Inicio":
                        page = new Inicio();
                        break;
                    case "Productos":
                        page = new Productos();
                        break;
                    case "MenuPerfil":
                        page = new MenuPerfil();
                        break;
                    case "Carrito":
                        page = new Carrito();
                        break;
                    case "AgregarDireccion":
                        page = new Carrito();
                        break;
                    case "MainPage":
                    default:
                        page = new MainPage();
                        break;
                }

                await Application.Current.MainPage.Navigation.PushAsync(page);
            }
            else if (parameter is Producto producto)
            {
                var detallesProductoPage = new DetallesProducto
                {
                    BindingContext = new DetallesProductoViewModel(producto)
                };

                await Application.Current.MainPage.Navigation.PushAsync(detallesProductoPage);
            }
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
