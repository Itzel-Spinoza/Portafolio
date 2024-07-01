using Firebase.Database;
using Firebase.Database.Query;
using GranjasCleo2.Model;
using System;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Input;
using Xamarin.Forms;

namespace GranjasCleo2.ViewModel
{
    public class DetallesProductoViewModel : BaseViewModel
    {
        private readonly FirebaseClient firebase;
        private Producto producto;
        private int cantidad;

        public Producto Producto
        {
            get { return producto; }
            set { SetProperty(ref producto, value); }
        }

        public int Cantidad
        {
            get { return cantidad; }
            set { SetProperty(ref cantidad, value); }
        }

        public ICommand IncreaseCommand { get; }
        public ICommand DecreaseCommand { get; }
        public ICommand AddToCartCommand { get; }

        public DetallesProductoViewModel(Producto producto)
        {
            firebase = new FirebaseClient("https://granjas-cleo-default-rtdb.firebaseio.com/");
            Producto = producto;
            Cantidad = 1; // Iniciar con una cantidad de 1

            IncreaseCommand = new Command(Increase);
            DecreaseCommand = new Command(Decrease);
            AddToCartCommand = new Command(async () => await AddToCart());
        }

        private void Increase()
        {
            Cantidad++;
        }

        private void Decrease()
        {
            if (Cantidad > 1)
                Cantidad--;
        }

        private async Task AddToCart()
        {
            var userService = DependencyService.Get<IUserService>();
            var userEmail = userService.UserEmail;

            try
            {
                // Verificar si el producto ya está en el carrito
                var carritoData = await firebase
                    .Child("Carrito")
                    .OnceAsync<CarritoP>();

                var productoEnCarrito = carritoData
                    .FirstOrDefault(item => item.Object.Correo == userEmail && item.Object.NombreProducto == Producto.NombreProducto);

                if (productoEnCarrito != null)
                {
                    // Actualizar cantidad y subtotal en el carrito
                    var nuevaCantidad = productoEnCarrito.Object.Cantidad + Cantidad;
                    var nuevoSubtotal = nuevaCantidad * productoEnCarrito.Object.PrecioUnitario;

                    await firebase
                        .Child("Carrito")
                        .Child(productoEnCarrito.Key)
                        .PutAsync(new CarritoP
                        {
                            Correo = userEmail,
                            NombreProducto = Producto.NombreProducto,
                            Cantidad = nuevaCantidad,
                            PrecioUnitario = productoEnCarrito.Object.PrecioUnitario,
                            Subtotal = nuevoSubtotal,
                            Imagen = Producto.Imagen,
                            Idproducto = Producto.Idproducto,
                            IdProductoCarrito = productoEnCarrito.Object.IdProductoCarrito
                        });
                }
                else
                {
                    // Agregar nuevo producto al carrito
                    var nuevoProductoCarrito = new CarritoP
                    {
                        Correo = userEmail,
                        NombreProducto = Producto.NombreProducto,
                        Cantidad = Cantidad,
                        PrecioUnitario = Producto.Precio,
                        Subtotal = Cantidad * Producto.Precio,
                        Imagen = Producto.Imagen,
                        Idproducto = Producto.Idproducto,
                        IdProductoCarrito = Producto.Idproducto // Usar el Id del producto como IdProductoCarrito
                    };

                    await firebase
                        .Child("Carrito")
                        .PostAsync(nuevoProductoCarrito);
                }

                await Application.Current.MainPage.DisplayAlert("Carrito", $"Producto {Producto.NombreProducto} agregado al carrito.", "OK");
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine($"Error al agregar el producto al carrito: {ex.Message}");
                await Application.Current.MainPage.DisplayAlert("Error", "Hubo un problema al agregar el producto al carrito.", "OK");
            }
        }
    }
}

