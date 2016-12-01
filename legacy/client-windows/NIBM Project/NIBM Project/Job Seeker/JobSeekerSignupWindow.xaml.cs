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
using MahApps.Metro.Controls.Dialogs;

namespace NIBM_Project
{
    /// <summary>
    /// Interaction logic for JobSeekerSignupWindow.xaml
    /// </summary>
    public partial class JobSeekerSignupWindow : MetroWindow
    {
        public int id;
        public String name { get; set; }
        public String userName { get; set; }
        bool boolResult = true;
        Validate validate;

        public JobSeekerSignupWindow()
        {
            InitializeComponent();
            DataContext = this;
        }

        private async void Button_Click(object sender, RoutedEventArgs e)
        {   
            validate = new Validate();
            String pw = password.Password.ToString();
            String confirmPw = ConfirmPassword.Password.ToString();

            if(validate.isEmpty(name))
            { 
                await this.ShowMessageAsync("Error", "Name Should not be Empty", MessageDialogStyle.Affirmative);            }

            else if(validate.isEmpty(userName))
            {
                await this.ShowMessageAsync("Error", "Username Should not be Empty", MessageDialogStyle.Affirmative);
            }

            else if (validate.isEmpty(pw))
            {
                await this.ShowMessageAsync("Error", "Password should not be Empty", MessageDialogStyle.Affirmative);
            }

            else if (password.Password.Length < 5)
            {
                await this.ShowMessageAsync("Error", "Password should be more than 6 characters", MessageDialogStyle.Affirmative);
            }

            else if (!pw.Equals(confirmPw))
            {
                await this.ShowMessageAsync("Error", "Password do not Match", MessageDialogStyle.Affirmative);
            }

            else
            {
                try
                {
                    Rest rest = new Rest("Users/Post");
                    rest.SetMethod("POST");

                    Dictionary<string, string> postData = new Dictionary<string, string>();

                    postData.Add("fName", name);
                    postData.Add("username", userName);
                    postData.Add("type", "JobSeeker");
                    postData.Add("password", pw);

                    rest.setFormData(postData);
                    rest.Execute();
                    /*
                    //Temporary Local Database Test
                    HandleDatabase handleDatabase = new HandleDatabase();
                    id = new GetLastID().getID();
                    handleDatabase.execute("INSERT INTO signup (signin_ID, name, user_name,password,type) values (" + id + ",'" + name + "','" + userName + "','" + pw + "','JobSeeker')");
                    */
                    await this.ShowMessageAsync("Message", "Profile Succefullly Created", MessageDialogStyle.Affirmative);
                    //Temporary Local Database Test

                    MainWindow mainwindow = new MainWindow();
                    mainwindow.Show();
                    this.Close();
                }
                catch (WebException ex)
                {
                    Console.WriteLine(ex.ToString());
                    boolResult = false;
                    //MessageBox.Show("Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                    MainWindow mainWindow = new MainWindow();
                    mainWindow.Show();
                    this.Close();         
                }

                catch (Exception ex)
                {
                    Console.WriteLine(ex.ToString());
                    boolResult = false;
                    //MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                    MainWindow mainWindow = new MainWindow();
                    mainWindow.Show();
                    this.Close();          
                }
                if (boolResult == false)
                {
                    await this.ShowMessageAsync("Error", "Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", MessageDialogStyle.Affirmative);
                }
            }
        }

        private async void Button_Click_1(object sender, RoutedEventArgs e)
        {
           MessageDialogResult result =  await this.ShowMessageAsync("Error", "Do you want to close the window? All your unsaved data will be lost", MessageDialogStyle.AffirmativeAndNegative);
           if (result == MessageDialogResult.Affirmative)
            {
                MainWindow main = new MainWindow();
                main.Show();
                this.Close();
            }
        }

        private async void Button_Click_2(object sender, RoutedEventArgs e)
        {
            MessageDialogResult result =  await this.ShowMessageAsync("Error", "Do you want to close the window? All your unsaved data will be lost", MessageDialogStyle.AffirmativeAndNegative);
            if (result == MessageDialogResult.Affirmative)
            {
                foreach (UIElement element in jobSeekerSignupGrid.Children)
                {
                    TextBox textbox = element as TextBox;
                    if (textbox != null)
                    {
                        textbox.Text = String.Empty;
                    }
                }
            }
        }
    }
}
