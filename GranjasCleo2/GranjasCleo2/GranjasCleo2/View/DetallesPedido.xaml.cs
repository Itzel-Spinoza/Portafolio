using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using GranjasCleo2.ViewModel;

namespace GranjasCleo2.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class DetallesPedido : ContentPage
    {
        public DetallesPedido(string idPedido)
        {
            InitializeComponent();
            BindingContext = new DetallesPedidoViewModel(idPedido);
        }
    }
}



