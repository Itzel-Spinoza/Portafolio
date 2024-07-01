using Firebase.Database;
using Firebase.Database.Query;
using GranjasCleo2.Model;
using System;
using System.Collections.ObjectModel;
using System.Linq;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace GranjasCleo2.ViewModel
{
    public class DetallesPedidoViewModel : BaseViewModel
    {
        private readonly FirebaseClient firebase;
        private string idPedido;
        private PedidoGeneral pedido;
        private ObservableCollection<ProductoPedido> productos;

        public PedidoGeneral Pedido
        {
            get { return pedido; }
            set { SetProperty(ref pedido, value); }
        }

        public ObservableCollection<ProductoPedido> Productos
        {
            get { return productos; }
            set { SetProperty(ref productos, value); }
        }

        // Constructor predeterminado
        public DetallesPedidoViewModel()
        {
            firebase = new FirebaseClient("https://granjas-cleo-default-rtdb.firebaseio.com/");
            Productos = new ObservableCollection<ProductoPedido>();
        }

        // Constructor que acepta el idPedido
        public DetallesPedidoViewModel(string idPedido) : this()
        {
            this.idPedido = idPedido;
            LoadPedidoDetallesCommand = new Command(async () => await LoadPedidoDetalles());
            LoadPedidoDetallesCommand.Execute(null);
        }

        public Command LoadPedidoDetallesCommand { get; }

        private async Task LoadPedidoDetalles()
        {
            try
            {
                var pedidoData = await firebase
                    .Child("Pedidos")
                    .Child(idPedido)
                    .OnceSingleAsync<PedidoGeneral>();

                Pedido = pedidoData;
                Pedido.IdPedido = idPedido; // Ensure IdPedido is set

                var productosData = await firebase
                    .Child("ProductosPedido")
                    .OnceAsync<ProductoPedido>();

                var productosList = productosData
                    .Where(p => p.Object.PedidoId == idPedido)
                    .Select(p => new ProductoPedido
                    {
                        NombreProducto = p.Object.NombreProducto,
                        Cantidad = p.Object.Cantidad,
                        PrecioUnitario = p.Object.PrecioUnitario,
                        Subtotal = p.Object.Subtotal,
                        PedidoId = p.Key,
                        ImagenPedido = p.Object.ImagenPedido
                    })
                    .ToList();

                Productos.Clear();
                foreach (var producto in productosList)
                {
                    Productos.Add(producto);
                }
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine($"Error al cargar los detalles del pedido: {ex.Message}");
                await Application.Current.MainPage.DisplayAlert("Error", "Hubo un problema al cargar los detalles del pedido.", "OK");
            }
        }
    }
}



