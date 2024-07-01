using System.Collections.Generic;
using System.Threading.Tasks;
using System.Windows.Input;
using Xamarin.Forms;
using GranjasCleo2.Model;
using GranjasCleo2.View;

namespace GranjasCleo2.ViewModel
{
    public class AgregarDireccionViewModel : BaseViewModel
    {
        private double total;
        private string nombreCompleto;
        private string calle;
        private string colonia;
        private string noExterior;
        private string cp;
        private string municipio;
        private string estado;
        private string userEmail;
        private List<CarritoP> productosCarrito;

        public double Total
        {
            get { return total; }
            set { SetProperty(ref total, value); }
        }

        public string UserEmail
        {
            get { return userEmail; }
            set { SetProperty(ref userEmail, value); }
        }

        public string NombreCompleto
        {
            get { return nombreCompleto; }
            set { SetProperty(ref nombreCompleto, value); }
        }

        public string Calle
        {
            get { return calle; }
            set { SetProperty(ref calle, value); }
        }

        public string Colonia
        {
            get { return colonia; }
            set { SetProperty(ref colonia, value); }
        }

        public string NoExterior
        {
            get { return noExterior; }
            set { SetProperty(ref noExterior, value); }
        }

        public string CP
        {
            get { return cp; }
            set { SetProperty(ref cp, value); }
        }

        public string Municipio
        {
            get { return municipio; }
            set { SetProperty(ref municipio, value); }
        }

        public string Estado
        {
            get { return estado; }
            set { SetProperty(ref estado, value); }
        }

        public ICommand NavigateToAddCardCommand { get; }

        public AgregarDireccionViewModel(double total, string userEmail, List<CarritoP> productosCarrito)
        {
            Total = total;
            UserEmail = userEmail;
            this.productosCarrito = productosCarrito;
            NavigateToAddCardCommand = new Command(async () => await NavigateToAddCard());
        }

        private async Task NavigateToAddCard()
        {
            if (string.IsNullOrWhiteSpace(NombreCompleto) ||
                string.IsNullOrWhiteSpace(Calle) ||
                string.IsNullOrWhiteSpace(Colonia) ||
                string.IsNullOrWhiteSpace(NoExterior) ||
                string.IsNullOrWhiteSpace(CP) ||
                string.IsNullOrWhiteSpace(Municipio) ||
                string.IsNullOrWhiteSpace(Estado))
            {
                await Application.Current.MainPage.DisplayAlert("Error", "Todos los campos de dirección son obligatorios.", "OK");
                return;
            }

            var pedido = new PedidoGeneral
            {
                NombreCliente = NombreCompleto,
                Calle = Calle,
                Colonia = Colonia,
                NoExterior = NoExterior,
                CP = CP,
                Municipio = Municipio,
                Estado = Estado,
                TotalPedido = Total,
                CorreoCliente = UserEmail
            };

            await Application.Current.MainPage.Navigation.PushAsync(new AgregarTarjetaCredito(pedido, productosCarrito));
        }
    }
}
