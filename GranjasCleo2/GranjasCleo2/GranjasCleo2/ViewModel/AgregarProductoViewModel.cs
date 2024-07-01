using Firebase.Database;
using Firebase.Database.Query;
using GranjasCleo2.Model;
using GranjasCleo2.View;
using System;
using System.Threading.Tasks;
using System.Windows.Input;
using Xamarin.Forms;

namespace GranjasCleo2.ViewModel
{
    public class AgregarProductoViewModel : BaseViewModel
    {
        private string nombreProducto;
        private string categoriaProducto;
        private string subcategoria;
        private string descripcion;
        private string imagen;
        private int precio;
        private int stock;

        private readonly FirebaseClient firebase;

        public string NombreProducto
        {
            get { return nombreProducto; }
            set { SetProperty(ref nombreProducto, value); }
        }

        public string CategoriaProducto
        {
            get { return categoriaProducto; }
            set { SetProperty(ref categoriaProducto, value); }
        }

        public string Subcategoria
        {
            get { return subcategoria; }
            set { SetProperty(ref subcategoria, value); }
        }

        public string Descripcion
        {
            get { return descripcion; }
            set { SetProperty(ref descripcion, value); }
        }

        public string Imagen
        {
            get { return imagen; }
            set { SetProperty(ref imagen, value); }
        }

        public int Precio
        {
            get { return precio; }
            set { SetProperty(ref precio, value); }
        }

        public int Stock
        {
            get { return stock; }
            set { SetProperty(ref stock, value); }
        }

        public ICommand GuardarProductoCommand { get; }
        public ICommand LimpiarCamposCommand { get; }

        public AgregarProductoViewModel()
        {
            firebase = new FirebaseClient("https://granjas-cleo-default-rtdb.firebaseio.com/");
            GuardarProductoCommand = new Command(async () => await GuardarProducto());
            LimpiarCamposCommand = new Command(LimpiarCampos);
        }

        private async Task GuardarProducto()
        {
            try
            {
                var producto = new Producto
                {
                    NombreProducto = NombreProducto,
                    CategoriaProducto = CategoriaProducto,
                    Subcategoria = Subcategoria,
                    Descripcion = Descripcion,
                    Imagen = Imagen,
                    Precio = Precio,
                    Stock = Stock
                };

                await firebase
                    .Child("Productos")
                    .PostAsync(producto);

                await Application.Current.MainPage.DisplayAlert("Éxito", "Producto agregado exitosamente.", "OK");

                // Navegar a InicioAdmin
                await Application.Current.MainPage.Navigation.PushAsync(new InicioAdmin());
            }
            catch (Exception ex)
            {
                await Application.Current.MainPage.DisplayAlert("Error", $"Hubo un problema al agregar el producto: {ex.Message}", "OK");
            }
        }

        private void LimpiarCampos()
        {
            NombreProducto = string.Empty;
            CategoriaProducto = string.Empty;
            Subcategoria = string.Empty;
            Descripcion = string.Empty;
            Imagen = string.Empty;
            Precio = 0;
            Stock = 0;
        }
    }
}


