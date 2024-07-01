using Firebase.Database;
using Firebase.Database.Query;
using System.Collections.ObjectModel;
using System.Linq;
using System.Threading.Tasks;
using Xamarin.Forms;
using GranjasCleo2.Model;
using System;
using System.Collections.Generic;
using GranjasCleo2.View;

namespace GranjasCleo2.ViewModel
{
    public class CarritoViewModel : BaseViewModel
    {
        private readonly FirebaseClient firebase;
        private readonly string userEmail;
        private ObservableCollection<CarritoP> productosCarrito;
        private double total;

        public ObservableCollection<CarritoP> ProductosCarrito
        {
            get { return productosCarrito; }
            set { SetProperty(ref productosCarrito, value); }
        }

        public double Total
        {
            get { return total; }
            set { SetProperty(ref total, value); }
        }

        public Command LoadCarritoCommand { get; }
        public Command<CarritoP> EliminarProductoCommand { get; }
        public Command NavigateToAddAddressCommand { get; }

        public CarritoViewModel()
        {
            firebase = new FirebaseClient("https://granjas-cleo-default-rtdb.firebaseio.com/");

            // Obtener el correo del usuario del servicio de usuario
            var userService = DependencyService.Get<IUserService>();
            userEmail = userService.UserEmail;

            ProductosCarrito = new ObservableCollection<CarritoP>();
            LoadCarritoCommand = new Command(async () => await CargarCarrito());
            EliminarProductoCommand = new Command<CarritoP>(async (producto) => await EliminarProducto(producto));
            NavigateToAddAddressCommand = new Command(async () => await NavigateAgregarDireccion());
            LoadCarritoCommand.Execute(null);
        }

        private async Task CargarCarrito()
        {
            try
            {
                var carritoData = await firebase
                    .Child("Carrito")
                    .OnceAsync<CarritoP>();

                var productos = carritoData
                    .Where(item => item.Object.Correo == userEmail)
                    .Select(item => new CarritoP
                    {
                        Idproducto = item.Key,
                        Correo = item.Object.Correo,
                        NombreProducto = item.Object.NombreProducto,
                        Cantidad = item.Object.Cantidad,
                        PrecioUnitario = item.Object.PrecioUnitario,
                        Subtotal = item.Object.Subtotal,
                        Imagen = item.Object.Imagen
                    })
                    .ToList();

                ProductosCarrito.Clear();
                foreach (var producto in productos)
                {
                    ProductosCarrito.Add(producto);
                }

                // Calcular el total
                Total = ProductosCarrito.Sum(p => p.Subtotal);
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine($"Error al cargar el carrito: {ex.Message}");
            }
        }

        private async Task EliminarProducto(CarritoP producto)
        {
            try
            {
                var itemToDelete = (await firebase
                    .Child("Carrito")
                    .OnceAsync<CarritoP>())
                    .FirstOrDefault(a => a.Object.Correo == userEmail && a.Key == producto.Idproducto);

                if (itemToDelete != null)
                {
                    await firebase
                        .Child("Carrito")
                        .Child(itemToDelete.Key)
                        .DeleteAsync();

                    ProductosCarrito.Remove(producto);
                    Total = ProductosCarrito.Sum(p => p.Subtotal); // Recalcular el total después de eliminar
                }
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine($"Error al eliminar el producto: {ex.Message}");
            }
        }

        private async Task NavigateAgregarDireccion()
        {
            if (Total > 0)
            {
                var productos = ProductosCarrito.ToList();
                await Application.Current.MainPage.Navigation.PushAsync(new AgregarDireccion(Total, userEmail, productos));
            }
            else
            {
                await Application.Current.MainPage.DisplayAlert("ADVERTENCIA", "No has agregado productos a tu carrito de compras.", "OK");
            }
        }
    }
}

