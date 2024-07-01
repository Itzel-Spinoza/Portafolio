using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using GranjasCleo2.ViewModel;
using System.Collections.Generic;
using GranjasCleo2.Model;

namespace GranjasCleo2.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class AgregarDireccion : ContentPage
    {
        public AgregarDireccion(double total, string userEmail, List<CarritoP> productos)
        {
            InitializeComponent();
            BindingContext = new AgregarDireccionViewModel(total, userEmail, productos);
        }
    }
}

