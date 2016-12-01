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
using System.Data;
using MahApps.Metro.Controls;

namespace NIBM_Project
{
    /// <summary>
    /// Interaction logic for JobSeekerDetailsForCompany.xaml
    /// </summary>
    public partial class JobSeekerDetailsForCompany : MetroWindow
    {
        Dictionary<int, RecommendedJobSeekers> JobSeekerData = new Dictionary<int, RecommendedJobSeekers>();
        DataTable seekerData = new DataTable();

        public JobSeekerDetailsForCompany()
        {
            InitializeComponent();
            JobSeekerData = StaticRecommendedJobSeekers.getRecommendedJobSeekers();

            foreach (KeyValuePair<int, RecommendedJobSeekers> seeker in JobSeekerData)
            {
                SeekerList.Items.Add(seeker.Value.getSeekerName());
            }
        }

        private void SeekerList_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (SeekerList.SelectedItem != null)
            {
                String selectedItem = SeekerList.SelectedItem.ToString();
                foreach (KeyValuePair<int, RecommendedJobSeekers> seeker in JobSeekerData)
                {
                    if (seeker.Value.getSeekerName().Equals(selectedItem))
                    {
                        Email.Text = seeker.Value.getEmail();
                        TeleOne.Text = "0"+seeker.Value.getTeleOne().ToString();
                        TeleTwo.Text = "0"+seeker.Value.getTeleTwo().ToString();
                    }
                }
            }
        }

        private void Sort_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {      
            if (Sort.SelectedValue.ToString().Equals("Name A > Z"))
            {
                SeekerList.Items.Clear();

                foreach (KeyValuePair<int, RecommendedJobSeekers> seeker in JobSeekerData.OrderBy(key => key.Value.getSeekerName()))
                {
                    if (InterestedPosition.SelectedValue.ToString().Equals(seeker.Value.getInterestedPosition()) || InterestedPosition.SelectedValue.ToString().Equals("Not Specified"))
                    {
                        if (QualifiedField.SelectedValue.ToString().Equals(seeker.Value.getQualifiedField()) || QualifiedField.SelectedValue.ToString().Equals("Not Specified"))
                        {
                            SeekerList.Items.Add(seeker.Value.getSeekerName());
                        }
                    }
                }
            }
            else if (Sort.SelectedValue.ToString().Equals("Default"))
            {
                SeekerList.Items.Clear();

                foreach (KeyValuePair<int, RecommendedJobSeekers> seeker in JobSeekerData)
                {
                    if (InterestedPosition.SelectedValue.ToString().Equals(seeker.Value.getInterestedPosition()) || InterestedPosition.SelectedValue.ToString().Equals("Not Specified"))
                    {
                        if (QualifiedField.SelectedValue.ToString().Equals(seeker.Value.getQualifiedField()) || QualifiedField.SelectedValue.ToString().Equals("Not Specified"))
                        {
                            SeekerList.Items.Add(seeker.Value.getSeekerName());
                        }
                    }
                }
            }

        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            SeekerList.Items.Clear();

            TeleOne.Text = "";
            TeleTwo.Text = "";
            Email.Text = "";

            foreach (KeyValuePair<int, RecommendedJobSeekers> seeker in JobSeekerData)
            {
                if (InterestedPosition.SelectedValue.ToString().Equals(seeker.Value.getInterestedPosition()) || InterestedPosition.SelectedValue.ToString().Equals("Not Specified"))
                {
                    if (QualifiedField.SelectedValue.ToString().Equals(seeker.Value.getQualifiedField()) || QualifiedField.SelectedValue.ToString().Equals("Not Specified"))
                    {
                        SeekerList.Items.Add(seeker.Value.getSeekerName());
                    }
                }
            }

        }



    }
}
