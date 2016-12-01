using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Net;
using MahApps.Metro.Controls;
using MahApps.Metro.Controls.Dialogs;

namespace NIBM_Project
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : MetroWindow
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private async void Button_Click_1(object sender, RoutedEventArgs e)
        {
            if (!CheckForInternetConnection())
            {
                await this.ShowMessageAsync("Error", "Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", MessageDialogStyle.Affirmative);
            }
            else
            {
                SignInWindow signin = new SignInWindow();
                signin.Show();
                this.Close();
            }
        }

        private async void Button_Click_2(object sender, RoutedEventArgs e)
        {       
            if (!CheckForInternetConnection())
            {
                await this.ShowMessageAsync("Error", "Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", MessageDialogStyle.Affirmative);
            }
            else
            {
                JobSeekerSignupWindow signup = new JobSeekerSignupWindow();
                signup.Show();
                this.Close();
            }
        }

        private async void Button_Click(object sender, RoutedEventArgs e)
        {    
            if (!CheckForInternetConnection())
            {
                await this.ShowMessageAsync("Error", "Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", MessageDialogStyle.Affirmative);

            }
            else
            {
                CompanySignUpWindow signup = new CompanySignUpWindow();
                signup.Show();
                this.Close();
            }
        }

        public static bool CheckForInternetConnection()
        {
            try
            {
                using (var client = new WebClient())
                using (var stream = client.OpenRead("http://www.google.com"))
                {
                    return true;
                }
            }
            catch
            {
                return false;
            }
        }

    }
}
