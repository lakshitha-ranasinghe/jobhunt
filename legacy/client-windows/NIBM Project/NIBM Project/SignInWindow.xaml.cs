using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows;
using Newtonsoft.Json.Linq;
using System.ComponentModel;
using System.Threading;
using System.Threading.Tasks;
using System.Net;
using MahApps.Metro.Controls;
using System.Windows.Media;
using MahApps.Metro.Controls.Dialogs;

namespace NIBM_Project
{
    /// <summary>
    /// Interaction logic for SignInWindow.xaml
    /// </summary>
    public partial class SignInWindow : MetroWindow
    {
        public String userName { get; set; }
        private BackgroundWorker worker;

        private bool isJobSeeker;
        private bool isCompany;
        private bool boolResult;
        public SignInWindow()
        {
            InitializeComponent();
            DataContext = this;

            worker = new BackgroundWorker();
            worker.DoWork += doWork;
            worker.RunWorkerCompleted += completed;
            worker.ProgressChanged += progressChange;
            worker.WorkerReportsProgress = true;
            worker.WorkerSupportsCancellation = true;
            progressBar.Visibility = System.Windows.Visibility.Collapsed;
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            worker.RunWorkerAsync();
        }

        private async void Button_Click(object sender, RoutedEventArgs e)
        {
            MessageDialogResult result = await this.ShowMessageAsync("Warning", "Do You Realy Want To Cancel ", MessageDialogStyle.Affirmative);
            if (result == MessageDialogResult.Affirmative)
            {
                MainWindow main = new MainWindow();
                main.Show();
                this.Close();
            }
        }

        private void doWork(object sender, DoWorkEventArgs e)
        {
            String password = signPassword.Password;

            worker.ReportProgress(25);

            try
            {
                Rest rest = new Rest("Users/SignIn");
                rest.SetMethod("POST");

                Dictionary<string, string> logindata = new Dictionary<string, string>();

                logindata.Add("username", userName);
                logindata.Add("password", password);

                rest.setFormData(logindata);

                worker.ReportProgress(50);

                string json = rest.Execute();

                //MessageBox.Show(json);
                worker.ReportProgress(70);

                JArray ja = JArray.Parse(json);

                if (ja.Count == 0)
                {
                    this.ShowMessageAsync("Error", "Invalid User", MessageDialogStyle.Affirmative);
                    worker.ReportProgress(0);
                }
                else
                {
                    worker.ReportProgress(90);
                    if (ja[0]["type"].ToString().Equals("JobSeeker"))
                    {
                        StaticJobSeekerID.setID(Convert.ToInt32(ja[0]["signin_ID"]));
                        StaticJobSeekerID.setName(ja[0]["name"].ToString());
                        isJobSeeker = true;
                    }
                    else if (ja[0]["type"].ToString().Equals("Company"))
                    {
                        StaticCompanyID.setID(Convert.ToInt32(ja[0]["signin_ID"]));
                        StaticCompanyID.setCompanyName(ja[0]["name"].ToString());
                        isCompany = true;
                    }
                    worker.ReportProgress(100);
                }
                boolResult = true;
                
            }
            catch (WebException ex)
            {
                Console.WriteLine(ex.ToString());
                boolResult = false;
               // MessageBox.Show("Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                e.Cancel = true;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
                boolResult = false;
                //MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                e.Cancel = true;
            }
            if (boolResult == false)
            {
                this.ShowMessageAsync("Error", "Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", MessageDialogStyle.Affirmative);
            }
        }

        private void completed(object sender, RunWorkerCompletedEventArgs e)
        {
            if (e.Cancelled)
            {
                MainWindow mainWindow = new MainWindow();
                this.Close();         
                mainWindow.Show();
            }

            else
            {
                if (isJobSeeker == true)
                {
                    try
                    {
                        Console.WriteLine(isJobSeeker.ToString());
                        MainMenu mainSeeker = new MainMenu();
                        mainSeeker.Show();
                        this.Close();
                    }
                    catch (WebException ex)
                    {
                        Console.WriteLine(ex.ToString());
                        this.ShowMessageAsync("Error", "Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", MessageDialogStyle.Affirmative);
                //        MessageBox.Show("Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                        this.Close();
                    }
                    catch (IndexOutOfRangeException ex)
                    {
                        Console.WriteLine(ex.ToString());
                        MessageBox.Show("Index Range is out of Bound. Press Ok to Exit", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                        this.Close();
                    }
                    catch (FormatException ex)
                    {
                        Console.WriteLine(ex.ToString());
                        MessageBox.Show("Data Conversion Failed. Press Ok to Exit", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                        this.Close();
                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine(ex.ToString());
                        MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                        this.Close();
                    }
                }
                else if (isCompany == true)
                {
                    try
                    {
                        Console.WriteLine(isCompany.ToString());
                        MainMenuCompany mainCompany = new MainMenuCompany();
                        mainCompany.Show();
                        this.Close();
                    }
                    catch (WebException ex)
                    {
                        Console.WriteLine(ex.ToString());
                        MessageBox.Show("Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                        this.Close();
                    }
                    catch (IndexOutOfRangeException ex)
                    {
                        Console.WriteLine(ex.ToString());
                        MessageBox.Show("Index Range is out of Bound. Press Ok to Exit", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                        this.Close();
                    }
                    catch (FormatException ex)
                    {
                        Console.WriteLine(ex.ToString());
                        MessageBox.Show("Data Conversion Failed. Press Ok to Exit", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                        this.Close();
                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine(ex.ToString());
                        MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                        this.Close();
                    }
                }
            }
        }
        private void progressChange(object sender, ProgressChangedEventArgs e)
        {
            progressBar.Visibility = System.Windows.Visibility.Visible;
            progressName.Content = "Processing.... "+e.ProgressPercentage.ToString()+"%";
        }
        
    }
}
