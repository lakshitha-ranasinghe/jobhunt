using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
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
    /// Interaction logic for CompanyWindow.xaml
    /// </summary>
    public partial class CompanyWindow : MetroWindow
    {
        private Company companyDetails; //Company Reference      
        private FinalCompany finalCompany; //Final Company Reference


        public String companyName { get; set; }
        public String companyType { get; set; }
        public String firstTelephone { get; set; }
        public String secondTelephone { get; set; }
        public String addressLine1 { get; set; }
        public String addressLine2 { get; set; }
        public String addressLine3 { get; set; }
        public String website { get; set; }
        public String offeredJobTypes { get; set; }
        public String description { get; set; }
        public String email { get; set; }

        Validate val;
        Company company;
        public int id;
        public bool oldUser = false;

        public CompanyWindow()
        {
            InitializeComponent();
            DataContext = this;

        }

        public void setOldUser(bool oldUser)
        {
            company = new Company();

            Console.Write("Old User");
            this.oldUser = oldUser;

            this.company = StaticCompanyData.getCompany();

            CompanyName.Text = company.getCompanyName();
            String[] address = company.getAddress().Split();
            AddressLine1.Text = address[0];
            AddressLine2.Text = address[1];
            AddressLine3Box.SelectedValue = address[2];
            CompanyTypeBox.SelectedValue = company.getCompanyType();
            TeleOne.Text = "0"+company.getFirstTelephone().ToString();
            TeleTwo.Text ="0"+ company.getSecondTelephone().ToString();
            EMail.Text = company.getEmail();
            WebSite.Text = company.getWebsite();
            JobTypeBox.SelectedValue = company.getOfferedJobTypes();
            Description.Text = company.getDescription(); 
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            val = new Validate();

            if(CompanyName.Text.Length==0)
            {
                MessageBox.Show("Company Name should not be Empty", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }
            else if (AddressLine1.Text.Length == 0 || AddressLine2.Text.Length == 0)
            {
                MessageBox.Show("Company Address should not be Empty", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }
            else if (AddressLine3Box.SelectedIndex == 0)
            {
                MessageBox.Show("Please select the province you belong to", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }
            else if (CompanyTypeBox.SelectedIndex == 0)
            {
                MessageBox.Show("Company Type should be selected", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }
            else if (TeleOne.Text.Length == 0 || TeleTwo.Text.Length == 0)
            {
                MessageBox.Show("Telephone number should not be empty", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }

            else if (EMail.Text.Length == 0)
            {
                MessageBox.Show("E-Mail address should not be Empty", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }

            else if (JobTypeBox.SelectedIndex == 0)
            {
                MessageBox.Show("Job type must be selected", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }
            else if (Description.Text.Length == 0)
            {
                MessageBox.Show("Please enter a short description about your company", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }

            //Check Numeric Fields
            else if (!val.isNumeric(TeleOne.Text) || !val.isNumeric(TeleTwo.Text))
            {
                MessageBox.Show("Telephone Number should be Numeric", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }

            //Check Field Lengths
            else if (!val.isTooShort(TeleOne.Text) || !val.isTooShort(TeleTwo.Text))
            {
                MessageBox.Show("Telephone Number should not be less than 10 Characters", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            }

            else if (EMail.Text.Contains("@") == false)
            {
                MessageBox.Show("Emal Address you entered is invalid", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning);
            } 

            else
            {
                id = StaticCompanyID.getID();//Get the relevent ID from Static variable in StaticCompanyID class
                companyDetails = new Company();

                companyDetails.setId(id);
                companyDetails.setCompanyName(companyName);
                companyDetails.setCompanyType(companyType);
                companyDetails.setFirstTelephone(Convert.ToInt64(firstTelephone));
                companyDetails.setSecondTelephone(Convert.ToInt64(secondTelephone));
                companyDetails.setEmail(email);
                companyDetails.setAddress(addressLine1, addressLine2, addressLine3);
                companyDetails.setWebsite(website);
                companyDetails.setOfferedJobTypes(offeredJobTypes);
                companyDetails.setDescription(description);

                finalCompany = new FinalCompany();

                try
                {
                    finalCompany.receiveCompanyData(id, companyDetails); //Forward Data to Final Company Class
                    finalCompany.executeCompanyData(oldUser);
                    MessageBox.Show("Update Sucessful", "Message", MessageBoxButton.OK,MessageBoxImage.Information);
                    MainMenuCompany maincompanyWindow = new MainMenuCompany();
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
            MessageBoxResult result = MessageBox.Show("Do you want to clear everything? All unsaved data will be lost", "Warning", MessageBoxButton.OKCancel, MessageBoxImage.Warning);
            if (result == MessageBoxResult.OK)
            {
                foreach (UIElement element in CompanyWindowGrid.Children)
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
            MessageBoxResult result = MessageBox.Show("Do you want to close the window? All your unsaved data will be lost", "Warning", MessageBoxButton.OKCancel, MessageBoxImage.Warning);
            if (result == MessageBoxResult.OK)
            {          
                this.Close();
            }
        }

    }
}
