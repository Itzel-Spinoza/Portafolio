using GranjasCleo2.Model;
using GranjasCleo2.ViewModel;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace GranjasCleo2.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class EditarProducto : ContentPage
    {
        public EditarProducto(Producto producto)
        {
            InitializeComponent();
            BindingContext = new EditarProductoViewModel(producto);
        }
    }
}

