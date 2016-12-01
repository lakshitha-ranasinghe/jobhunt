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
using System.ComponentModel;
using System.Threading;
using System.Net;
using MahApps.Metro.Controls;
using MahApps.Metro.Controls.Dialogs;

namespace NIBM_Project
{
    /// <summary>
    /// Interaction logic for JobSeekerProfessionalQualificationWindow.xaml
    /// </summary>
    public partial class JobSeekerProfessionalQualificationWindow : MetroWindow
    {
        //To get Data from Previous Screen

        private JobSeeker jobSeekerPersonalData;
        private JobSeekerPreferences jobSeekerPreferencesData;
        private JobSeekerEducation jobSeekerEducationData;

        private JobSeekerProfessionalQualification jobSeekerProfessionalQualification;

        private FinalJobSeeker forward; //To Pass Data

        private int id; //Job Seeker ID

        public String expYears { get; set; }

        public String firstDesignation { get; set; }
        public String firstCompany { get; set; }
        public DateTime firstStartDate { get; set; }
        public DateTime firstEndDate { get; set; }

        public String secondDesignation { get; set; }
        public String secondCompany { get; set; }
        public DateTime secondStartDate { get; set; }
        public DateTime secondEndDate { get; set; }

        public String otherFirstTitle { get; set; }
        public String otherFirstYear { get; set; }
        public String otherFirstDetails { get; set; }

        public String otherSecondTitle { get; set; }
        public String otherSecondYear { get; set; }
        public String otherSecondDetails { get; set; }

        public bool oldUser = false;

        public JobSeekerProfessionalQualificationWindow()
        {
            InitializeComponent();
            DataContext = this;
        }

        public void receiveJobSeekerData(JobSeeker jobSeekerPersonalData, JobSeekerPreferences jobSeekerPreferencesData, JobSeekerEducation jobSeekerEducationData, int id)
        {
            this.jobSeekerPersonalData = jobSeekerPersonalData;
            this.jobSeekerPreferencesData = jobSeekerPreferencesData;
            this.jobSeekerEducationData = jobSeekerEducationData;
            this.id = id;
        }
        public void setOldUser(bool oldUser)
        {
            Console.Write("Old User");
            this.oldUser = oldUser;

            jobSeekerProfessionalQualification = StaticJobSeekerData.getJobSeekerProfessionalQualification();

            FirstCompany.Text = jobSeekerProfessionalQualification.getFirstCompany();
            SecondCompany.Text = jobSeekerProfessionalQualification.getSecondCompany();
            FirstDesignation.Text = jobSeekerProfessionalQualification.getFirstDesignation();
            SecondDesignation.Text = jobSeekerProfessionalQualification.getSecondDesignation();
            FirstStartDate.SelectedDate = jobSeekerProfessionalQualification.getFirstStartDate();
            FirstEndDate.SelectedDate = jobSeekerProfessionalQualification.getFirstEndDate();
            SecondStartDate.SelectedDate = jobSeekerProfessionalQualification.getSecondStartDate();
            SecondEndDate.SelectedDate = jobSeekerProfessionalQualification.getSecondEndDate();

            OtherFirstTitle.Text = jobSeekerProfessionalQualification.getOtherFirstTitle();
            if (jobSeekerProfessionalQualification.getOtherFirstYear() == 1111)
            {
                OtherFirstYear.Text = "Not Specified";
            }
            else
            {
                OtherFirstYear.Text = jobSeekerProfessionalQualification.getOtherFirstYear().ToString();
            }

            OtherFirstDetails.Text = jobSeekerProfessionalQualification.getOtherFirstDetails();

            OtherSecondTitle.Text = jobSeekerProfessionalQualification.getOtherSecondTitle();

            if (jobSeekerProfessionalQualification.getOtherSecondYear() == 1111)
            {
                OtherSecondYear.Text = "Not Specified";
            }
            else
            {
                OtherSecondYear.Text = jobSeekerProfessionalQualification.getOtherSecondYear().ToString();
            }

            OtherSecondDetails.Text = jobSeekerProfessionalQualification.getOtherSecondDetails();

            if (jobSeekerProfessionalQualification.getExpYears() == 1111)
            {
                ExpYears.Text = "0";
            }
            else
            {
                ExpYears.Text = jobSeekerProfessionalQualification.getExpYears().ToString();
            }
        }
        private async void Button_Click(object sender, RoutedEventArgs e)
        {
            if(FirstCompany.Text == "")
            {
               firstCompany = "Not Specified";
            }
            if(SecondCompany.Text == "")
            {
               secondCompany= "Not Specified";
            }
            if (FirstDesignation.Text == "")
            {
                firstDesignation= "Not Specified";
            }
            if (SecondDesignation.Text == "")
            {
                secondDesignation= "Not Specified";
            }
            if (OtherFirstTitle.Text == "")
            {
                otherFirstTitle = "Not Specified";
            }
            if (OtherFirstYear.Text == "" || OtherFirstYear.Text.Equals("Not Specified"))
            {
                otherFirstYear= "1111";
            }
            if (OtherFirstDetails.Text == "")
            {
                otherFirstDetails = "Not Specified";
            }
            if (OtherSecondTitle.Text == "")
            {
                otherSecondTitle = "Not Specified";
            }
            if (OtherSecondYear.Text == "" || OtherSecondYear.Text.Equals("Not Specified"))
            {
                otherSecondYear ="1111";
            }
            if (OtherSecondDetails.Text == "")
            {
                otherSecondDetails = "Not Specified";
            }
            if (ExpYears.Text == "" || ExpYears.Text.Equals("0"))
            {
                expYears = "1111";
            }

                jobSeekerProfessionalQualification = new JobSeekerProfessionalQualification();
                jobSeekerProfessionalQualification.setExpYears(Convert.ToInt32(expYears));
                jobSeekerProfessionalQualification.setFirstCompany(firstDesignation, firstCompany, firstStartDate, firstEndDate);
                jobSeekerProfessionalQualification.setSecondCompany(secondDesignation, secondCompany, secondStartDate, secondEndDate);
                jobSeekerProfessionalQualification.setFirstOther(otherFirstTitle, Convert.ToInt32(otherFirstYear), otherFirstDetails);
                jobSeekerProfessionalQualification.setSecondOther(otherSecondTitle, Convert.ToInt32(otherSecondYear), otherSecondDetails);

                //Forward Data to final Screen
                forward = new FinalJobSeeker();
                forward.receiveData(id, jobSeekerPersonalData, jobSeekerPreferencesData, jobSeekerEducationData, jobSeekerProfessionalQualification);

                //Database Execution
                try
                {
                    forward.excute(oldUser);
                    StaticJobSeekerData.setJobSeeker(jobSeekerPersonalData, jobSeekerPreferencesData, jobSeekerEducationData, jobSeekerProfessionalQualification);

                    await this.ShowMessageAsync("Message", "Profile Succefullly Created", MessageDialogStyle.Affirmative);
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

        private async void Button_Click_1(object sender, RoutedEventArgs e)
        {
            MessageDialogResult result =  await this.ShowMessageAsync("Error", "Do you want to close the window? All your unsaved data will be lost", MessageDialogStyle.AffirmativeAndNegative);
            if (result == MessageDialogResult.Affirmative)
            {
                foreach (UIElement element in jobSeekerProfessionalWindowGrid.Children)
                {
                    TextBox textbox = element as TextBox;
                    if (textbox != null)
                    {
                        textbox.Text = String.Empty;
                    }
                }
            }
        }

    }
}
