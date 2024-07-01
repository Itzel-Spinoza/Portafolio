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
    public class VerPedidosAdminViewModel : BaseViewModel
    {
        private readonly FirebaseClient firebase;
        private ObservableCollection<PedidoGeneral> pedidos;

        public ObservableCollection<PedidoGeneral> Pedidos
        {
            get { return pedidos; }
            set { SetProperty(ref pedidos, value); }
        }

        public ICommand VerDetallesCommand { get; }

        public VerPedidosAdminViewModel()
        {
            firebase = new FirebaseClient("https://granjas-cleo-default-rtdb.firebaseio.com/");
            Pedidos = new ObservableCollection<PedidoGeneral>();
            VerDetallesCommand = new Command<PedidoGeneral>(async (pedido) => await VerDetalles(pedido));

            // Load orders when the ViewModel is created
            LoadPedidosCommand = new Command(async () => await LoadPedidos());
            LoadPedidosCommand.Execute(null);
        }

        public Command LoadPedidosCommand { get; }

        private async Task LoadPedidos()
        {
            try
            {
                var pedidosData = await firebase
                    .Child("Pedidos")
                    .OnceAsync<PedidoGeneral>();

                Pedidos.Clear();
                foreach (var pedido in pedidosData)
                {
                    var p = pedido.Object;
                    p.IdPedido = pedido.Key; // Asignar la clave de Firebase como IdPedido
                    Pedidos.Add(p);
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
                await Application.Current.MainPage.Navigation.PushAsync(new DetallesPedidoAdmin(pedido.IdPedido));
            }
            else
            {
                await Application.Current.MainPage.DisplayAlert("Error", "No se puede cargar los detalles del pedido.", "OK");
            }
        }
    }
}


