using System;
using System.Windows;
using System.Threading;
using System.Threading.Tasks;
using System.ComponentModel;
using System.Windows.Input;
using System.Net;
using MahApps.Metro.Controls;
using MahApps.Metro.Controls.Dialogs;

namespace NIBM_Project
{
    /// <summary>
    /// Interaction logic for MainMenuCompany.xaml
    /// </summary>
    public partial class MainMenuCompany : MetroWindow
    {
        private HandleDatabase handleDatabase;
        private BackgroundWorker worker;

        public MainMenuCompany()
        {
            InitializeComponent();
            handleDatabase = new HandleDatabase();
            try
            {
                //handleDatabase.getCompanyVacancyData();
                welcome.Content = StaticCompanyID.getCompanyName() + " !";

                worker = new BackgroundWorker();
                worker.DoWork += doWork;
                worker.RunWorkerCompleted += completed;
                worker.ProgressChanged += progressChange;
                State.Content = "Ready";
            }
            catch (WebException ex)
            {
                throw ex;
            }
            catch (IndexOutOfRangeException ex)
            {
                throw ex;
            }
            catch (FormatException ex)
            {
                throw ex;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        private void RegisterButton_Click(object sender, RoutedEventArgs e)
        {    
            bool isOldUser;

            try
            {
                Rest r = new Rest("Users/CheckProfile/" + StaticCompanyID.getID());

                if (r.Execute().Equals("true"))
                {
                    isOldUser = true;
                }
                else
                {
                    isOldUser = false;
                }

                CompanyWindow companyWindow = new CompanyWindow();

                if (isOldUser)
                {                  
                    handleDatabase = new HandleDatabase();
                    handleDatabase.getCompanyData(StaticCompanyID.getID());
                    companyWindow.Show();
                    companyWindow.setOldUser(isOldUser);
                }
                companyWindow.Show();                       
            }
            catch (WebException ex)
            {
                Console.WriteLine(ex.ToString());
                MessageBox.Show("Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
                MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);               
            }

        }

        private void VacancyButton_Click(object sender, RoutedEventArgs e)
        {
            CompanyVacanciesAddWindow companyVacancyWindow = new CompanyVacanciesAddWindow();
            companyVacancyWindow.Show();          
        }

        private void AvailableVacancy_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                AvailableCompanyVacanciesWndow availbleCompanyVacancyWindow = new AvailableCompanyVacanciesWndow();
                availbleCompanyVacancyWindow.Show();
            }
            catch (WebException ex)
            {
                Console.WriteLine(ex.ToString());
                MessageBox.Show("Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
                MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
        private void updateVacancy_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                UpdateVacancies update = new UpdateVacancies();
                update.Show();
            }
            catch (WebException ex)
            {
                Console.WriteLine(ex.ToString());
                MessageBox.Show("Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
                MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }

        }

        private async void logout_Click(object sender, RoutedEventArgs e)
        {
            MessageDialogResult result =  await this.ShowMessageAsync("Warning", "Do you want to close all the Windows and Logout? All your unsaved data will be lost", MessageDialogStyle.AffirmativeAndNegative);
            if (result == MessageDialogResult.Affirmative)
            {
                for (int intCounter = App.Current.Windows.Count - 1; intCounter >= 0; intCounter--)
                {
                    MainWindow main = new MainWindow();
                    main.Show();
                    App.Current.Windows[intCounter].Close();
                }
            }
        }

        private async void delete_Click(object sender, RoutedEventArgs e)
        {
            MessageDialogResult result =  await this.ShowMessageAsync("Warning", "Deleting your profile will wipe out all your profile data from our databases. Do you want to proceed?", MessageDialogStyle.AffirmativeAndNegative);
            if (result == MessageDialogResult.Affirmative)
            {
                try
                {
                    Rest rest = new Rest("Company/Delete/" + StaticJobSeekerID.getID());
                    rest.SetMethod("DELETE");
                    rest.Execute();

                    for (int intCounter = App.Current.Windows.Count - 1; intCounter >= 0; intCounter--)
                    {
                        MainWindow main = new MainWindow();
                        main.Show();
                        App.Current.Windows[intCounter].Close();
                    }
                }
                catch (WebException ex)
                {
                    Console.WriteLine(ex.ToString());
                    MessageBox.Show("Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.ToString());
                    MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);                  
                }
            }
        }

        private void jobSeekers_Click(object sender, RoutedEventArgs e)
        {
            worker.RunWorkerAsync();
        }

        public void doWork(object sender, DoWorkEventArgs e)
            {
                try
                {
                    handleDatabase.getJobSeekerDetailsForCompany();
                }
                catch (WebException ex)
                {
                    Console.WriteLine(ex.ToString());
                    MessageBox.Show("Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                    e.Cancel = true;
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.ToString());
                    MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                    e.Cancel = true;
                }
            }

        private void completed(object sender, RunWorkerCompletedEventArgs e)
        {
            if (e.Cancelled)
            {
                this.Cursor = Cursors.Arrow;
            }
            else
            {
                this.Cursor = Cursors.Arrow;
                JobSeekerDetailsForCompany jobSeekers = new JobSeekerDetailsForCompany();
                jobSeekers.Show();
            }
        }
        private void progressChange(object sender, ProgressChangedEventArgs e)
        {
                this.Cursor = Cursors.Wait;
        }

    }
}
