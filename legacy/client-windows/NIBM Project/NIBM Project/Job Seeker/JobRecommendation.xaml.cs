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
using MahApps.Metro.Controls;
using MahApps.Metro.Controls.Dialogs;

namespace NIBM_Project
{
    /// <summary>
    /// Interaction logic for JobRecommendation.xaml
    /// </summary>
    public partial class JobRecommendation : MetroWindow
    {
        private Dictionary<Int32, CompanyVacancy> recommendData;
        private Dictionary<String, Int32> recommendList;
        private Recommendation recommendation;

        public JobRecommendation()
        {          
            recommendation = new Recommendation();
            try
            {
                recommendation.automatic();
                recommendData = recommendation.getData();
                recommendList = recommendation.getList();

                InitializeComponent();
                JobList.ItemsSource = recommendList.Keys;   //DO NOT CHANGE THE ORDER OF THE LINES IN THE CONSTRUCTOR
            }
            catch (NullReferenceException ex)
            {
                throw ex;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        private void RadioButton_Checked(object sender, RoutedEventArgs e)
        {
            JobType.IsEnabled = true;
            ExpectedSalary.IsEnabled = true;
            PreferedArea.IsEnabled = true;

            if (Sort != null)
            {
                Sort.SelectedIndex = 0;
            }

            JobList.ItemsSource = null;
            JobList.Items.Clear();

        }

        private void RadioButton_Checked_1(object sender, RoutedEventArgs e)
        {
            JobType.IsEnabled = false;
            ExpectedSalary.IsEnabled = false;
            PreferedArea.IsEnabled = false;

            recommendation.automatic();
            recommendData = recommendation.getData();
            recommendList = recommendation.getList();
            JobList.ItemsSource = recommendList.Keys;

            if (Sort != null)
            {
                Sort.SelectedIndex = 0;
            }
        }

        private async void Button_Click(object sender, RoutedEventArgs e)
        {
            if (AutoRB.IsChecked == true)
            {
                await this.ShowMessageAsync("", "Please Select Manual Filter to Filter you data Manually", MessageDialogStyle.Affirmative);
            }
            else
            {
                String jobType = JobType.SelectedValue.ToString();
                String expectedSalary = ExpectedSalary.SelectedValue.ToString();
                String preferedArea = PreferedArea.SelectedValue.ToString();

                String[] manualPreferences = { jobType, expectedSalary, preferedArea };

                recommendation.manual(manualPreferences);
                recommendData = recommendation.getData();
                recommendList = recommendation.getList();

                JobList.ItemsSource = recommendList.Keys;
            }
        }

        private void JobList_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (JobList.ItemsSource != null && JobList.SelectedValue != null)
            {
                String selectedItem = JobList.SelectedValue.ToString();
                int selectedVacancyId = recommendList[selectedItem];
                CompanyVacancy selectedVacancy = recommendData[selectedVacancyId];

                JobTitle.Text = selectedVacancy.getJobTitle();
                VacancyDescription.Text = selectedVacancy.getVacancyDescription();
                Prerequisites.Text = selectedVacancy.getPrerequesties();
                Location.Text = selectedVacancy.getLocation();
                Salary.Text = selectedVacancy.getSalary().ToString() + ".00";
                NoOfVacancy.Text = selectedVacancy.getNoOfVacancies().ToString();
                ClosingDate.Text = selectedVacancy.getClosingDate().Date.ToString();
            }
            else
            {
                JobTitle.Text = "";
                VacancyDescription.Text = "";
                Prerequisites.Text = "";
                Location.Text = "";
                Salary.Text = "";
                NoOfVacancy.Text = "";
                ClosingDate.Text = "";
            }

        }

        private void ComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Dictionary<String, Int32> toSort = new Dictionary<String, Int32>();

            if(Sort.SelectedValue.ToString().Equals("By Closing Date"))
            {
                foreach (KeyValuePair<Int32, CompanyVacancy> dataSort in recommendData.OrderBy(key => key.Value.getClosingDate()))
                {
                    foreach (KeyValuePair<String, Int32> listSort in recommendList)
                    {
                        if (dataSort.Key == listSort.Value)
                        {
                            toSort.Add(listSort.Key, listSort.Value);
                        }
                    }
                }

                recommendList = toSort;
                JobList.ItemsSource = recommendList.Keys;
            }

            if (Sort.SelectedValue.ToString().Equals("By Salary Low > High"))
            {
                foreach (KeyValuePair<Int32, CompanyVacancy> dataSort in recommendData.OrderBy(key => key.Value.getSalary()))
                {
                    foreach (KeyValuePair<String, Int32> listSort in recommendList)
                    {
                        if (dataSort.Key == listSort.Value)
                        {
                            toSort.Add(listSort.Key, listSort.Value);
                        }
                    }
                }

                recommendList = toSort;
                JobList.ItemsSource = recommendList.Keys;
            }

            if (Sort.SelectedValue.ToString().Equals("By Location A-Z"))
            {
                foreach (KeyValuePair<Int32, CompanyVacancy> dataSort in recommendData.OrderBy(key => key.Value.getLocation()))
                {
                    foreach (KeyValuePair<String, Int32> listSort in recommendList)
                    {
                        if (dataSort.Key == listSort.Value)
                        {
                            toSort.Add(listSort.Key, listSort.Value);
                        }
                    }
                }

                recommendList = toSort;
                JobList.ItemsSource = recommendList.Keys;
            }

        }
    }
}
