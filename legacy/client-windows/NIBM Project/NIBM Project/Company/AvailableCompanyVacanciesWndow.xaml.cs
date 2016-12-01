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
    /// Interaction logic for AvailableCompanyVacanciesWndow.xaml
    /// </summary>
    public partial class AvailableCompanyVacanciesWndow : MetroWindow
    {
        HandleDatabase handleDataBase;

        public AvailableCompanyVacanciesWndow()
        {
            try
            {
                InitializeComponent();
                handleDataBase = new HandleDatabase();
                handleDataBase.getCompanyVacancyData();
                setComboBoxData();
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

        public void setComboBoxData() 
        {
            VacancyIdCombo.ItemsSource = StaticCompanyVacancyList.getCompanyVacancyList(); 
        }

        private void ComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            int selectedComboItem = Convert.ToInt32(VacancyIdCombo.SelectedValue.ToString());
            CompanyVacancy selectedCompanyVacancy = StaticCompanyVacancyList.getCompanyVacancyFromList(selectedComboItem);

            String tt = selectedCompanyVacancy.getJobTitle(); Console.WriteLine(tt);
            Location.Text = selectedCompanyVacancy.getLocation();
            JobTitle.Text = selectedCompanyVacancy.getJobTitle();
            Salary.Text = selectedCompanyVacancy.getSalary().ToString()+".00";
            NoOfVacancy.Text = selectedCompanyVacancy.getNoOfVacancies().ToString();
            ClosingDate.Text = selectedCompanyVacancy.getClosingDate().ToString();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            if (VacancyIdCombo.SelectedValue==null)
            {
                MessageBox.Show("Please select the Vacancy ID you want to delete", "Message", MessageBoxButton.OKCancel, MessageBoxImage.Warning);
            }
            else
            {
                if (CheckVacancy.IsChecked == true)
                {
                    MessageBox.Show("Do you want to delete the checked vacancy", "Message", MessageBoxButton.OKCancel, MessageBoxImage.Warning);

                    int selectedComboBoxId = Convert.ToInt32(VacancyIdCombo.SelectedValue.ToString());
                    try
                    {
                        Rest rest = new Rest("Company/DeleteVacancy/" + selectedComboBoxId);
                        string result = rest.Execute();

                        if (result.Equals("1"))
                        {
                            MessageBox.Show("Vacancy Deleted Sucessfully", "Message", MessageBoxButton.OK, MessageBoxImage.Information);
                            this.Close();
                            AvailableCompanyVacanciesWndow availableCompanyVacanciesWindow = new AvailableCompanyVacanciesWndow();
                            availableCompanyVacanciesWindow.Show();
                        }
                        else
                        {
                            MessageBox.Show("Could not Delete the Vacancy", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                        }
                    }
                    catch (WebException ex)
                    {
                        Console.WriteLine(ex.ToString());
                        MessageBox.Show("Cannot connect to the Job Hunt Server. Please Check your Internet Connection and try again", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                        this.Close();
                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine(ex.ToString());
                        MessageBox.Show("Error Occured. Please try to Again.", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                        this.Close();
                    }

                }
                else
                {
                    MessageBox.Show("You Need to Confirm your Action before Processing. ", "Message", MessageBoxButton.OK, MessageBoxImage.Warning);
                }
                // this.Close();
                //   AvailableCompanyVacanciesWndow availableCompanyVacanciesWindow = new AvailableCompanyVacanciesWndow();
                //  availableCompanyVacanciesWindow.Show();
            }
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {         
            this.Close();
        }
    }
}
