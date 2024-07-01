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
	public partial class CrearCuenta : ContentPage
	{
		public CrearCuenta ()
		{
			InitializeComponent ();
            //BindingContext = new MainViewModel();

            BindingContext = new CrearCuentaViewModel();

        }
	}
}