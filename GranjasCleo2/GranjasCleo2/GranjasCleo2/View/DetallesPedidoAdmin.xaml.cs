using GranjasCleo2.ViewModel;
using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace GranjasCleo2.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class DetallesPedidoAdmin : ContentPage
    {
        public DetallesPedidoAdmin(string idPedido)
        {
            InitializeComponent();
            BindingContext = new DetallesPedidoAdminViewModel(idPedido);
        }
    }
}
