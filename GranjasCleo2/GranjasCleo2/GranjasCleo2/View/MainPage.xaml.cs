using GranjasCleo2.ViewModel;
using GranjasCleo2.Vista;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace GranjasCleo2
{
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
            BindingContext = new MainViewModel();
        }

        private async void BotonLogin(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new IniciarSesion());
        }

        private async void BotonCrearCuenta(object sender, EventArgs e)
        {
            //await Navigation.PushAsync(new SecondPage());
        }
    }
}
