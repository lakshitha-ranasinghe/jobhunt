using System;
using System.Windows;
using System.Windows.Controls;
using MahApps.Metro.Controls;
using MahApps.Metro.Controls.Dialogs;

namespace NIBM_Project
{
    /// <summary>
    /// Interaction logic for CompanyDetailsForJobSeeker.xaml
    /// </summary>
    public partial class CompanyDetailsForJobSeeker : MetroWindow
    {


        public CompanyDetailsForJobSeeker()
        {
            InitializeComponent();
            setListBoxData();
        }

        public void setListBoxData()
        {          
            CompanyDataListBox.ItemsSource = StaticCompanyList.getCompanyList();
        }

        private void CompanyDataListBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            String selectedItem = CompanyDataListBox.SelectedItem.ToString();
            Company selectedCompany = StaticCompanyList.getCompanyFromList(selectedItem);

            CompanyName.Text = selectedCompany.getCompanyName();
            JobType.Text = selectedCompany.getOfferedJobTypes();
            Telephone.Text = "0"+selectedCompany.getFirstTelephone().ToString();
            Email.Text  = selectedCompany.getEmail();
            Website.Text = selectedCompany.getWebsite();
        }
    }
}
