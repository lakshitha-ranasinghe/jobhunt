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
    /// Interaction logic for CompanyVacanciesAddWindow.xaml
    /// </summary>
    public partial class CompanyVacanciesAddWindow : MetroWindow
    {
        FinalCompany finalCompany; 

        public String jobTitle { get; set; }
        public String vacancyDescription { get; set; }
        public String prerequisites { get; set; }
        public String location { get; set; }
        public String noOfVacancies { get; set; }
        public String salary { get; set; }
        public DateTime closingDate { get; set; }
        
        public int id;
        Validate val;
        CompanyVacancy company;

        public CompanyVacanciesAddWindow()
        {
            InitializeComponent();
            DataContext = this;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            val =new Validate();

            if(JobTitle.SelectedIndex==0)
            {
                MessageBox.Show("Job title should not be Empty", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }

            else if (VacancyDescription.Text.Length==0)
            {
                MessageBox.Show("Vacancy Description should not be Empty", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }

            else if (Prerequisites.Text.Length == 0)
            {
                MessageBox.Show("Prerequisites should not be Empty", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }

            else if (Location.SelectedIndex==0)
            {
                MessageBox.Show("Location should not be Empty", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }

            else if (NoOfVacancy.Text.Length == 0)
            {
                MessageBox.Show("No of Vacancies you offer should not be Empty", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }

            else if (Salary.Text.Length == 0)
            {
                MessageBox.Show("Salary should not be Empty", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }

            else if (!val.isNumeric(NoOfVacancy.Text))
            {
                MessageBox.Show("No of vanacies should be Numeric", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }

            else if (!val.isNumeric(Salary.Text))
            {
                MessageBox.Show("Salary should be Numeric", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }

            else
            {

                id = StaticCompanyID.getID(); //Get the relevent ID from Static variable in StaticCompanyID class

                company = new CompanyVacancy();

                company.setJobTitle(jobTitle);
                company.setVacancyDescription(vacancyDescription);
                company.setPrerequesties(prerequisites);
                company.setLocation(location);
                company.setNoOfVacancies(Convert.ToInt32(noOfVacancies));
                company.setSalary(Convert.ToDouble(salary));
                company.setClosingDate(closingDate);

                try
                {
                    finalCompany = new FinalCompany();
                    finalCompany.receiveCompanyVacancyData(company);
                    finalCompany.executeVacancy();

                    MessageBox.Show("Vacancy Successfully Added.", "Message", MessageBoxButton.OK, MessageBoxImage.Asterisk);                    
                    this.Close();
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

        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            MessageBoxResult result = MessageBox.Show("Do you want to clear everything? All your unsaved data will be lost", "Warning", MessageBoxButton.OKCancel, MessageBoxImage.Warning);
            if (result == MessageBoxResult.OK)
            {
                foreach (UIElement element in CompanyVacancyAddWindowGrid.Children)
                {
                    TextBox textbox = element as TextBox;
                    if (textbox != null)
                    {
                        textbox.Text = String.Empty;
                    }
                }
            }
        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            MessageBoxResult result = MessageBox.Show("Do you want to cancel? All your unsaved data will be lost", "Warning", MessageBoxButton.OKCancel, MessageBoxImage.Warning);
            if (result == MessageBoxResult.OK)
            {
                this.Close();
            }
        }
    }
}
