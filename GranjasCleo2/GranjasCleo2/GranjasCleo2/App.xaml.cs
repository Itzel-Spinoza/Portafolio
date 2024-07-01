using GranjasCleo2.Model;
using GranjasCleo2.Vista;
using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace GranjasCleo2
{
    public partial class App : Application
    {
        public App()
        {
            InitializeComponent();
            // Registro del servicio de usuario
            DependencyService.Register<IUserService, UserService>();

            MainPage = new NavigationPage(new MainPage());
            //MainPage = new NavigationPage(new IniciarSesion());
            // Registro del servicio de usuario
            

        }

        protected override void OnStart()
        {
        }

        protected override void OnSleep()
        {
        }

        protected override void OnResume()
        {
        }
    }
}
