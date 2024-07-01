using Firebase.Database;
using Firebase.Database.Query;
using GranjasCleo2.Model;
using GranjasCleo2.View;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Input;
using Xamarin.Forms;

namespace GranjasCleo2.ViewModel
{
    public class AgregarTarjetaViewModel : BaseViewModel
    {
        private readonly FirebaseClient firebase;
        private string cardHolderName;
        private string cardNumber;
        private DateTime expiryDate;
        private string cvv;
        private PedidoGeneral pedido;
        private List<CarritoP> productosCarrito;

        public string CardHolderName
        {
            get => cardHolderName;
            set => SetProperty(ref cardHolderName, value);
        }

        public string CardNumber
        {
            get => cardNumber;
            set => SetProperty(ref cardNumber, value);
        }

        public DateTime ExpiryDate
        {
            get => expiryDate;
            set => SetProperty(ref expiryDate, value);
        }

        public string CVV
        {
            get => cvv;
            set => SetProperty(ref cvv, value);
        }

        public ICommand RealizarPedidoCommand { get; }

        // Constructor sin parámetros público
        public AgregarTarjetaViewModel()
        {
        }

        public AgregarTarjetaViewModel(PedidoGeneral pedido, List<CarritoP> productosCarrito)
        {
            firebase = new FirebaseClient("https://granjas-cleo-default-rtdb.firebaseio.com/");
            ExpiryDate = DateTime.Today; // Inicializar con la fecha actual o una fecha predeterminada
            this.pedido = pedido;
            this.productosCarrito = productosCarrito;

            RealizarPedidoCommand = new Command(async () => await RealizarPedido());
        }

        private async Task RealizarPedido()
        {
            if (string.IsNullOrWhiteSpace(CardHolderName) ||
                string.IsNullOrWhiteSpace(CardNumber) ||
                string.IsNullOrWhiteSpace(CVV))
            {
                await Application.Current.MainPage.DisplayAlert("Error", "Todos los campos de la tarjeta son obligatorios.", "OK");
                return;
            }

            if (string.IsNullOrWhiteSpace(pedido.NombreCliente) ||
                string.IsNullOrWhiteSpace(pedido.Calle) ||
                string.IsNullOrWhiteSpace(pedido.Colonia) ||
                string.IsNullOrWhiteSpace(pedido.NoExterior) ||
                string.IsNullOrWhiteSpace(pedido.CP) ||
                string.IsNullOrWhiteSpace(pedido.Municipio) ||
                string.IsNullOrWhiteSpace(pedido.Estado))
            {
                await Application.Current.MainPage.DisplayAlert("Error", "Todos los campos de dirección son obligatorios.", "OK");
                return;
            }

            try
            {
                pedido.FechaPedido = DateTime.Now; // Asignar la fecha actual

                var pedidoResult = await firebase
                    .Child("Pedidos")
                    .PostAsync(new PedidoGeneral
                    {
                        NombreCliente = pedido.NombreCliente,
                        Calle = pedido.Calle,
                        Colonia = pedido.Colonia,
                        NoExterior = pedido.NoExterior,
                        CP = pedido.CP,
                        Municipio = pedido.Municipio,
                        Estado = pedido.Estado,
                        TotalPedido = pedido.TotalPedido,
                        CorreoCliente = pedido.CorreoCliente,
                        FechaPedido = pedido.FechaPedido // Añadir esta línea
                    });

                string pedidoId = pedidoResult.Key;

                foreach (var producto in productosCarrito)
                {
                    var productoPedido = new ProductoPedido
                    {
                        ImagenPedido = producto.Imagen,
                        CorreoCliente = pedido.CorreoCliente,
                        NombreProducto = producto.NombreProducto,
                        Cantidad = producto.Cantidad,
                        PrecioUnitario = producto.PrecioUnitario,
                        Subtotal = producto.Subtotal,
                        PedidoId = pedidoId,
                        FechaPedido = pedido.FechaPedido // Añadir esta línea
                    };

                    await firebase
                        .Child("ProductosPedido")
                        .PostAsync(productoPedido);
                }

                // Eliminar productos del carrito
                var carritoData = await firebase
                    .Child("Carrito")
                    .OnceAsync<CarritoP>();

                foreach (var item in carritoData.Where(item => item.Object.Correo == pedido.CorreoCliente))
                {
                    await firebase
                        .Child("Carrito")
                        .Child(item.Key)
                        .DeleteAsync();
                }

                await Application.Current.MainPage.DisplayAlert("Éxito", "El pedido se ha realizado con éxito.", "OK");
                await Application.Current.MainPage.Navigation.PushAsync(new Inicio()); // Navegar a la pantalla de Inicio
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine($"Error al realizar el pedido: {ex.Message}");
                await Application.Current.MainPage.DisplayAlert("Error", "Hubo un problema al realizar el pedido.", "OK");
            }
        }
    }
}
