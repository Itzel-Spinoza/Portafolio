using GranjasCleo2.View;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using Xamarin.Forms;

namespace GranjasCleo2.ViewModel
{
    public class MenuViewModel : BaseViewModel
    {
        public ICommand NavigateToVerPedidosCommand { get; }

        public MenuViewModel()
        {
            NavigateToVerPedidosCommand = new Command(async () => await NavigateToVerPedidos());
        }

        private async Task NavigateToVerPedidos()
        {
            await Application.Current.MainPage.Navigation.PushAsync(new VerPedidos());
        }
    }
}

