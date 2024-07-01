using GranjasCleo2.View;
using GranjasCleo2.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace GranjasCleo2.Vista
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class IniciarSesion : ContentPage
	{
        public IniciarSesion()
        {
            InitializeComponent();
            BindingContext = new LoginViewModel();
        }
    }
}
