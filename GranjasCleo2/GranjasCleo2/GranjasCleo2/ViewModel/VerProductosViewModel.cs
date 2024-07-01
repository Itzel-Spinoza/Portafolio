using Firebase.Database;
using Firebase.Database.Query;
using GranjasCleo2.Model;
using GranjasCleo2.View;
using System.Collections.ObjectModel;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace GranjasCleo2.ViewModel
{
    public class VerProductosViewModel : BaseViewModel
    {
        private ObservableCollection<Producto> productos;
        public ObservableCollection<Producto> Productos
        {
            get { return productos; }
            set { SetProperty(ref productos, value); }
        }

        public Command LoadProductsCommand { get; }
        public Command<Producto> NavigateToDetailsCommand { get; }

        public VerProductosViewModel()
        {
            Productos = new ObservableCollection<Producto>();
            LoadProductsCommand = new Command(async () => await LoadProducts());
            NavigateToDetailsCommand = new Command<Producto>(async (producto) => await NavigateToDetails(producto));
            LoadProductsCommand.Execute(null); // Ejecuta la carga de productos cuando se inicializa el ViewModel
        }

        private async Task LoadProducts()
        {
            var firebase = new FirebaseClient("https://granjas-cleo-default-rtdb.firebaseio.com/");

            var productos = await firebase
                .Child("Productos")
                .OnceAsync<Producto>();

            Productos.Clear();
            foreach (var producto in productos)
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

        private async Task NavigateToDetails(Producto producto)
        {
            var detallesProductoPage = new DetallesProducto
            {
                BindingContext = new DetallesProductoViewModel(producto)
            };

            await Application.Current.MainPage.Navigation.PushAsync(detallesProductoPage);
        }
    }
}
