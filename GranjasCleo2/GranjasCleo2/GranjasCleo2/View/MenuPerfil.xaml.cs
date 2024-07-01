using GranjasCleo2.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace GranjasCleo2.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class MenuPerfil : ContentPage
    {
        public MenuPerfil()
        {
            InitializeComponent();
            BindingContext = new MenuViewModel();

        }
    }
}