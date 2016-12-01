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
using System.Windows.Threading;
using MahApps.Metro.Controls;
using MahApps.Metro.Controls.Dialogs;

namespace NIBM_Project
{
    /// <summary>
    /// Interaction logic for MainMenu.xaml
    /// </summary>
    public partial class MainMenu : MetroWindow
    {
        private HandleDatabase handleDatabase;
        private bool isOldUser;
        private int checkCathch;

        public MainMenu()
        {
            InitializeComponent();
            handleDatabase = new HandleDatabase();
            checkUser();
            try
            {
                handleDatabase.getCompanyData();
                handleDatabase.getVacancyData();
                welcome.Content = StaticJobSeekerID.getName().Split()[0] + "!";
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

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            JobSeekerWindow jobseeker = new JobSeekerWindow();
            checkUser();

            if (isOldUser)   
            {               
                jobseeker.Show();
                jobseeker.setOldUser(isOldUser);                              
            }
            jobseeker.Show();
        }

        private async void Button_Click_1(object sender, RoutedEventArgs e)
        {
            try
            {
                JobRecommendation jobRecommendation = new JobRecommendation();
                jobRecommendation.Show();
            }
            catch (NullReferenceException ex)
            {
                Console.WriteLine(ex.ToString());
                checkCathch = 1;
                //MessageBox.Show("Cannot Find your current Profile Data. Make Sure You have Completed you Profile.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);              
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
                checkCathch = 2;
                //MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);              
            }
            if (checkCathch == 1)
            {
                await this.ShowMessageAsync("Incompete Profile", "Cannot Find your current Profile Data. Make Sure You have Completed you Profile.", MessageDialogStyle.AffirmativeAndNegative);
            }
            if (checkCathch == 2)
            {
                await this.ShowMessageAsync("Error..!!", "Error has been Occured. Please check your internet connection and try to Again.", MessageDialogStyle.AffirmativeAndNegative);
            }
        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            CompanyDetailsForJobSeeker companydetailsWindow = new CompanyDetailsForJobSeeker();
            companydetailsWindow.Show();
        }

        private async void checkUser()
        {
            handleDatabase = new HandleDatabase();
            try
            {
                Rest r = new Rest("Users/CheckProfile/" + StaticJobSeekerID.getID());

                if (r.Execute().Equals("true"))
                    isOldUser = true;
                else
                    isOldUser = false;

                if (isOldUser)
                {
                    handleDatabase.getJobSeekerData(StaticJobSeekerID.getID());
                }
            }
            catch (WebException ex)
            {
                Console.WriteLine(ex.ToString());
                //MessageBox.Show("Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                checkCathch = 3;
                this.Close();
            }
            catch (IndexOutOfRangeException ex)
            {
                Console.WriteLine(ex.ToString());
                //MessageBox.Show("Index Range is out of Bound. Press Ok to Exit", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                checkCathch = 4;
                this.Close();
            }
            catch (FormatException ex)
            {
                Console.WriteLine(ex.ToString());
                //MessageBox.Show("Data Conversion Failed. Press Ok to Exit", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                checkCathch = 5;
                this.Close();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
                //MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                checkCathch = 6;
                this.Close();
            }
            if (checkCathch == 3)
            {
                await this.ShowMessageAsync("Error..!!", "Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", MessageDialogStyle.AffirmativeAndNegative);
            }
            if (checkCathch == 4)
            {
                await this.ShowMessageAsync("Error..!!", "Index Range is out of Bound. Press Ok to Exit", MessageDialogStyle.AffirmativeAndNegative);
            }
            if (checkCathch == 5)
            {
                await this.ShowMessageAsync("Error..!!", "Data Conversion Failed. Press Ok to Exit", MessageDialogStyle.AffirmativeAndNegative);
            }
            if (checkCathch == 6)
            {
                await this.ShowMessageAsync("Error..!!", "Error Occured. Please try to Again.", MessageDialogStyle.AffirmativeAndNegative);
            }
        }

        private async void delete_Click(object sender, RoutedEventArgs e)
        {
            MessageDialogResult result =  await this.ShowMessageAsync("Warning..!!", "Deleting your profile will wipe out all your profile data from our databases. Do you want to proceed?", MessageDialogStyle.AffirmativeAndNegative);
            if (result == MessageDialogResult.Affirmative)
            {
                try
                {
                    Rest rest = new Rest("Jobseeker/Delete/" + StaticJobSeekerID.getID());
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
                    checkCathch = 3;
                    //MessageBox.Show("Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", "Error", MessageBoxButton.OK, MessageBoxImage.Error);                   
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.ToString());
                    checkCathch = 6;
                    //MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                    this.Close();
                }
                if (checkCathch == 6)
                {
                    await this.ShowMessageAsync("Error..!!", "Error Occured. Please try to Again.", MessageDialogStyle.AffirmativeAndNegative);
                }
                if (checkCathch == 3)
                {
                    await this.ShowMessageAsync("Error..!!", "Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", MessageDialogStyle.AffirmativeAndNegative);
                }
            }
        }

        private async void logout_Click(object sender, RoutedEventArgs e)
        {
            MessageDialogResult result =  await this.ShowMessageAsync("Error", "Do you want to close the window and logout? All your unsaved data will be lost", MessageDialogStyle.AffirmativeAndNegative);
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

        private async void nothing_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                handleDatabase = new HandleDatabase();
                State.Content = "Busy...";
                State.Dispatcher.Invoke(DispatcherPriority.Background, new Action(delegate() { State.UpdateLayout(); }));
                using (new WaitCursor())
                {
                    checkUser();
                    handleDatabase.getCompanyData();
                    handleDatabase.getVacancyData();
                }
                State.Content = "Ready";
            }
            catch (WebException ex)
            {
                Console.WriteLine(ex.ToString());
                checkCathch = 3;
                //MessageBox.Show("Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", "Error", MessageBoxButton.OK, MessageBoxImage.Error);            
            }
            catch (IndexOutOfRangeException ex)
            {
                Console.WriteLine(ex.ToString());
                checkCathch = 4;
                //MessageBox.Show("Index Range is out of Bound. Press Ok to Exit", "Error", MessageBoxButton.OK, MessageBoxImage.Error);            
            }
            catch (FormatException ex)
            {
                Console.WriteLine(ex.ToString());
                checkCathch = 5;
                //MessageBox.Show("Data Conversion Failed. Press Ok to Exit", "Error", MessageBoxButton.OK, MessageBoxImage.Error);               
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
                checkCathch = 6;
                //MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);                
            }
            if (checkCathch == 3)
            {
                await this.ShowMessageAsync("Error..!!", "Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", MessageDialogStyle.AffirmativeAndNegative);
            }
            if (checkCathch == 4)
            {
                await this.ShowMessageAsync("Error..!!", "Index Range is out of Bound. Press Ok to Exit", MessageDialogStyle.AffirmativeAndNegative);
            }
            if (checkCathch == 5)
            {
                await this.ShowMessageAsync("Error..!!", "Data Conversion Failed. Press Ok to Exit", MessageDialogStyle.AffirmativeAndNegative);
            }
            if (checkCathch == 6)
            {
                await this.ShowMessageAsync("Error..!!", "Error Occured. Please try to Again.", MessageDialogStyle.AffirmativeAndNegative);
            }
        }
    }
}
