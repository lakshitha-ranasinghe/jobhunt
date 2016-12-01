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
using System.Windows.Shapes;
using System.Net;
using MahApps.Metro.Controls;

namespace NIBM_Project
{
    /// <summary>
    /// Interaction logic for CompanySignUpWindow.xaml
    /// </summary>
    public partial class CompanySignUpWindow : MetroWindow
    {
        public String companyName { get; set; }
        public String userName { get; set; }
       
        private int id;
        private Validate validate;

        public CompanySignUpWindow()
        {
            InitializeComponent();
            DataContext = this;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            String pw = password.Password;
            String confirmPw = confirmPassword.Password;

            validate = new Validate();

            if (validate.isEmpty(companyName))
            {
                MessageBox.Show("Name should not be Empty");
            }

            else if (validate.isEmpty(userName))
            {
                MessageBox.Show("Username should not be Empty");
            }

            else if (password.Password.Length < 5)
            {
                MessageBox.Show("Password should be more than 6 characters", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }

            else if (validate.isEmpty(pw))
            {
                MessageBox.Show("Password should not be Empty");
            }

            else if (!pw.Equals(confirmPw))
            {
                MessageBox.Show("Passwords do not Match");
            }

            else
            {
                try
                {
                    Rest rest = new Rest("Users/Post");
                    rest.SetMethod("POST");

                    Dictionary<string, string> postData = new Dictionary<string, string>();

                    postData.Add("fName", companyName);
                    postData.Add("username", userName);
                    postData.Add("type", "Company");
                    postData.Add("password", pw);

                    rest.setFormData(postData);
                    rest.Execute();

                    MessageBox.Show("Profile Successfully Created");

                    MainWindow mainwindow = new MainWindow();
                    mainwindow.Show();
                    this.Close();
                }
                catch (WebException ex)
                {
                    Console.WriteLine(ex.ToString());
                    MessageBox.Show("Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                    MainWindow mainWindow = new MainWindow();
                    mainWindow.Show();
                    this.Close();
                    
                }
               
                catch (Exception ex)
                {
                    Console.WriteLine(ex.ToString());
                    MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                    MainWindow mainWindow = new MainWindow();
                    mainWindow.Show();
                    this.Close();
                }
            }
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            MessageBoxResult result = MessageBox.Show("Do you want to close the window? All your unsaved data will be lost", "Warning", MessageBoxButton.OKCancel, MessageBoxImage.Warning);
            if (result == MessageBoxResult.OK)
            {
                MainWindow mainwindow = new MainWindow();
                mainwindow.Show();
                this.Close();
            }
        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            MessageBoxResult result = MessageBox.Show("Do you want to clear everything? All unsaved data will be lost", "Warning", MessageBoxButton.OKCancel, MessageBoxImage.Warning);
            if (result == MessageBoxResult.OK)
            {
                foreach (UIElement element in companySignUpWindowGrid.Children)
                {
                    TextBox textbox = element as TextBox;
                    if (textbox != null)
                    {
                        textbox.Text = String.Empty;
                    }
                }
            }
        }

        private void TextBox_TextChanged(object sender, TextChangedEventArgs e)
        {

        }
    }

}
