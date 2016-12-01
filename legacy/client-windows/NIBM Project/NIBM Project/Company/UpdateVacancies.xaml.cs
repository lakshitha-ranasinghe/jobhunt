using MahApps.Metro.Controls;
using System;
using System.Collections.Generic;
using System.Data;
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
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;
using System.Net;

namespace NIBM_Project
{
    /// <summary>
    /// Interaction logic for UpdateVacancies.xaml
    /// </summary>
    public partial class UpdateVacancies : MetroWindow
    {
        private const int LOCATION_POSITION = 4;
        private const int CLOSINGDATE_POSITION = 5;
        private const int SALARY_POSTION = 6;
        private const int ID_POSITION = 0;

        private long selectedVacancyID;
        private CompanyVacancy vacancyToUpdate;

        public DateTime updatedClosingDate { get; set; }
        public string updatedLocation { get; set; }
        public string updatedSalary { get; set; }

        private HandleDatabase handleDatabase;

        public UpdateVacancies()
        {
            InitializeComponent();
            populateDataGrid();
            DataContext = this;
        }

        public void populateDataGrid()
        {
            Rest rest = new Rest("Company/GetVacancies/" + StaticCompanyID.getID());
            string response = rest.Execute();

            DataTable dt = JsonConvert.DeserializeObject<DataTable>(response);

            dataGrid.AutoGenerateColumns = false;
            dataGrid.ItemsSource = dt.AsDataView();
        }

        private void dataGrid_MouseDoubleClick(object sender, MouseButtonEventArgs e)
        {
            DataRowView row = (DataRowView)dataGrid.SelectedItems[0];

            selectedVacancyID = (Int64)row[ID_POSITION];
            string location = (string)row[LOCATION_POSITION];
            long salary = (Int64)row[SALARY_POSTION];
            DateTime closingDate = (DateTime)row[CLOSINGDATE_POSITION];

            Location.SelectedValue = location;
            Salary.Text = salary.ToString();
            ClosingDate.SelectedDate = closingDate;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            if(handleDatabase== null)
            {
                handleDatabase = new HandleDatabase();
            }

            handleDatabase.getCompanyVacancyData();
            Dictionary<Int32, CompanyVacancy> vacancies = StaticCompanyVacancyList.getCompanyVacancyDictionary();
            foreach (KeyValuePair<int, CompanyVacancy> vacancy in vacancies)
            {
                if(vacancy.Value.intGetVacanyID() == selectedVacancyID)
                {
                    vacancyToUpdate = vacancy.Value;

                }
            }

            if (vacancyToUpdate != null)
            {
                vacancyToUpdate.setClosingDate(updatedClosingDate);
                vacancyToUpdate.setSalary(Convert.ToDouble(updatedSalary));
                vacancyToUpdate.setLocation(updatedLocation);
            }
        }
    }
}
