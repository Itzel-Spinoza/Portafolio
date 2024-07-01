using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using GranjasCleo2.ViewModel;
using GranjasCleo2.Model;
using System.Collections.Generic;

namespace GranjasCleo2.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class AgregarTarjetaCredito : ContentPage
    {
        public AgregarTarjetaCredito(PedidoGeneral pedido, List<CarritoP> productosCarrito)
        {
            InitializeComponent();
            BindingContext = new AgregarTarjetaViewModel(pedido, productosCarrito);
        }
    }
}


