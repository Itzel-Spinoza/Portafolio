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
    public class EditarProductoViewModel : BaseViewModel
    {
        private readonly FirebaseClient firebase;
        private Producto producto;

        public Producto Producto
        {
            get { return producto; }
            set { SetProperty(ref producto, value); }
        }

        public ICommand GuardarProductoCommand { get; }
        public ICommand EliminarProductoCommand { get; }

        public EditarProductoViewModel()
        {
            // Constructor predeterminado
        }

        public EditarProductoViewModel(Producto producto)
        {
            firebase = new FirebaseClient("https://granjas-cleo-default-rtdb.firebaseio.com/");
            Producto = producto;
            GuardarProductoCommand = new Command(async () => await GuardarProducto());
            EliminarProductoCommand = new Command(async () => await EliminarProducto());
        }

        private async Task GuardarProducto()
        {
            try
            {
                // Actualizar el producto en Firebase usando el ID del producto
                await firebase
                    .Child("Productos")
                    .Child(Producto.Idproducto)
                    .PutAsync(Producto);

                // Mostrar mensaje de confirmación
                await Application.Current.MainPage.DisplayAlert("Éxito", "El producto se ha actualizado correctamente.", "OK");

                // Navegar a la página InicioAdmin después de guardar el producto
                await Application.Current.MainPage.Navigation.PushAsync(new InicioAdmin());
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine($"Error al guardar el producto: {ex.Message}");
                await Application.Current.MainPage.DisplayAlert("Error", "Hubo un problema al guardar el producto.", "OK");
            }
        }

        private async Task EliminarProducto()
        {
            try
            {
                // Eliminar el producto en Firebase usando el ID del producto
                await firebase
                    .Child("Productos")
                    .Child(Producto.Idproducto)
                    .DeleteAsync();

                // Mostrar mensaje de confirmación
                await Application.Current.MainPage.DisplayAlert("Éxito", "El producto se ha eliminado correctamente.", "OK");

                // Navegar a la página InicioAdmin después de eliminar el producto
                await Application.Current.MainPage.Navigation.PushAsync(new InicioAdmin());
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine($"Error al eliminar el producto: {ex.Message}");
                await Application.Current.MainPage.DisplayAlert("Error", "Hubo un problema al eliminar el producto.", "OK");
            }
        }
    }
}

