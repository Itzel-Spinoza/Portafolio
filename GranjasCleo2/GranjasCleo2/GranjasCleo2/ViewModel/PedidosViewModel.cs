using Firebase.Database;
using GranjasCleo2.Model;
using GranjasCleo2.View;
using System;
using System.Collections.ObjectModel;
using System.Linq;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace GranjasCleo2.ViewModel
{
    public class PedidosViewModel : BaseViewModel
    {
        private readonly FirebaseClient firebase;
        private readonly string userEmail;
        private ObservableCollection<PedidoGeneral> pedidos;

        public ObservableCollection<PedidoGeneral> Pedidos
        {
            get { return pedidos; }
            set { SetProperty(ref pedidos, value); }
        }

        public Command LoadPedidosCommand { get; }
        public Command<PedidoGeneral> VerDetallesCommand { get; }

        public PedidosViewModel()
        {
            firebase = new FirebaseClient("https://granjas-cleo-default-rtdb.firebaseio.com/");
            var userService = DependencyService.Get<IUserService>();
            userEmail = userService.UserEmail;

            Pedidos = new ObservableCollection<PedidoGeneral>();
            LoadPedidosCommand = new Command(async () => await LoadPedidos());
            VerDetallesCommand = new Command<PedidoGeneral>(async (pedido) => await VerDetalles(pedido));
            LoadPedidosCommand.Execute(null);
        }

        private async Task LoadPedidos()
        {
            try
            {
                var pedidosData = await firebase
                    .Child("Pedidos")
                    .OnceAsync<PedidoGeneral>();

                var pedidosList = pedidosData
                    .Where(p => p.Object.CorreoCliente == userEmail)
                    .Select(p => new PedidoGeneral
                    {
                        IdPedido = p.Key,
                        NombreCliente = p.Object.NombreCliente,
                        Calle = p.Object.Calle,
                        Colonia = p.Object.Colonia,
                        NoExterior = p.Object.NoExterior,
                        CP = p.Object.CP,
                        Municipio = p.Object.Municipio,
                        Estado = p.Object.Estado,
                        TotalPedido = p.Object.TotalPedido,
                        CorreoCliente = p.Object.CorreoCliente,
                        FechaPedido = p.Object.FechaPedido
                    })
                    .ToList();

                Pedidos.Clear();
                foreach (var pedido in pedidosList)
                {
                    Pedidos.Add(pedido);
                }
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine($"Error al cargar los pedidos: {ex.Message}");
                await Application.Current.MainPage.DisplayAlert("Error", "Hubo un problema al cargar los pedidos.", "OK");
            }
        }

        private async Task VerDetalles(PedidoGeneral pedido)
        {
            if (pedido != null)
            {
                await Application.Current.MainPage.Navigation.PushAsync(new DetallesPedido(pedido.IdPedido));
            }
            else
            {
                await Application.Current.MainPage.DisplayAlert("Error", "No se puede cargar los detalles del pedido.", "OK");
            }
        }
    }
}
