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
using MahApps.Metro.Controls;
using MahApps.Metro.Controls.Dialogs;

namespace NIBM_Project
{
    /// <summary>
    /// Interaction logic for JobSeekerWindow.xaml
    /// </summary>
    public partial class JobSeekerWindow : MetroWindow, INotifyPropertyChanged 
    {
        //Accessors to get Data from TextFields 
        public event PropertyChangedEventHandler PropertyChanged;

        public String firstName { get; set; }
        public String lastName { get; set; }
        public String addressLine1 { get; set; }
        public String addressLine2 { get; set; }
        public String addressLine3 { get; set; }
        public String telMobile { get; set; }
        public String telHome { get; set; }
        public String email { get; set; }
        public DateTime dateOfBirth { get; set; }
        public String title { get; set; }
        public String qualifiedField { get; set; }
        public String interestedPosition { get; set; }
        public String lastJob { get; set; }
        
        public int ID;
        public bool oldUser = false;

        Validate val; //Validate Class Reference
        JobSeeker seeker; //Job Seeker Class Reference
        JobSeekerPreferences seekerPreferences; //Job Seeker Preference Reference
        JobSeekerQualificationWindow forward; //Job Seeker Qualification Window Reference /*to forward objects /*

        public JobSeekerWindow()
        {
            InitializeComponent();
            DataContext = this;
        }
        public void setOldUser(bool oldUser)
        {
            Console.Write("Old User");
            this.oldUser = oldUser;

            this.seeker = StaticJobSeekerData.getJobSeekerPersonalData();           
            String[] name = seeker.getName().Split();
            String[] address = seeker.getAddress().Split();
            FirstName.Text = name[0];           
            LastName.Text = name[1];
            TitleBox.SelectedValue = seeker.getTitle();
            HomeTelephone.Text = "0" + seeker.getTelephoneHome().ToString();
            MobileTelephone.Text = "0" + seeker.getTelephoneMobile().ToString();
            AddressLine1.Text = address[0];
            AddressLine2.Text = address[1];
            AddressLine3.Text = address[2];
            Email.Text = seeker.getEmail();
            DataOfBirth.SelectedDate = seeker.getBirthday();

            this.seekerPreferences = StaticJobSeekerData.getJobSeekerPreferences();
       
            QualifiedField.SelectedValue = seekerPreferences.getQualifiedField();
            InterestedJob.SelectedValue = seekerPreferences.getinterestedPosition();
            LastJob.SelectedValue = seekerPreferences.getLastJob();           
        }

        private async void Button_Click(object sender, RoutedEventArgs e)
        {
            val = new Validate();

            //Validation

            //Check for Null Fields
            if (FirstName.Text.Length == 0)
            {
                await this.ShowMessageAsync("Warning", "First Name should not be Empty", MessageDialogStyle.Affirmative);
            }
            else if (LastName.Text.Length==0)
            {
                await this.ShowMessageAsync("Warning", "Last Name should not be Empty", MessageDialogStyle.Affirmative);
            }
            else if (AddressLine1.Text.Length == 0 || AddressLine2.Text.Length == 0)
            {
                await this.ShowMessageAsync("Warning", "Address should not be Empty", MessageDialogStyle.Affirmative);
            }
            else if (HomeTelephone.Text.Length==0 || MobileTelephone.Text.Length==0)
            {
                await this.ShowMessageAsync("Warning", "Telephone Number should not be Empty", MessageDialogStyle.Affirmative);
            }
            else if (Email.Text.Length==0)
            {
                await this.ShowMessageAsync("Warning", "E-Mail Address should not be Empty", MessageDialogStyle.Affirmative);
            }
            else if (Email.Text.Contains("@") == false)
            {
                await this.ShowMessageAsync("Warning", "E-Mail Address you entered is invalid", MessageDialogStyle.Affirmative);
            } 
            else if (TitleBox.SelectedIndex == 0)
            {
                await this.ShowMessageAsync("Warning", "Title Should be Selected", MessageDialogStyle.Affirmative);
            }

            else if (QualifiedField.SelectedIndex == 0)
            {
                await this.ShowMessageAsync("Warning", "Qualified field Should be Selected", MessageDialogStyle.Affirmative);
            }

            else if (InterestedJob.SelectedIndex == 0)
            {
                await this.ShowMessageAsync("Warning", "Interested job field must be selected", MessageDialogStyle.Affirmative);
            }

            //Check Numeric Fields
            else if (!val.isNumeric(MobileTelephone.Text) || !val.isNumeric(HomeTelephone.Text))
            {
                await this.ShowMessageAsync("Warning", "Telephone Number should be Numeric", MessageDialogStyle.Affirmative);
            }

            //Check Field Lengths
            else if (!val.isTooShort(HomeTelephone.Text) || !val.isTooShort(MobileTelephone.Text))
            {
                await this.ShowMessageAsync("Warning", "Telephone Number should not be less than 10 Characters", MessageDialogStyle.Affirmative);
            }

            else
            {
                seeker = new JobSeeker();
                seekerPreferences = new JobSeekerPreferences();
                ID = StaticJobSeekerID.getID(); //Get the relevent ID from Static variable in StaticJobSeekerID class

                //Set Seeker Personal Info
                seeker.setId(ID);
                seeker.setFullName(firstName, lastName);
                seeker.setTitle(title);
                seeker.setDateOfBirth(dateOfBirth);
                seeker.setTelHome(Convert.ToInt32(telHome));
                seeker.setTelMobile(Convert.ToInt32(telMobile));
                seeker.setAddress(addressLine1, addressLine2, addressLine3);
                seeker.setEmail(email);

                //Set Seeker Preferences
                seekerPreferences.setId(ID);
                seekerPreferences.setInterestedPosition(interestedPosition);
                seekerPreferences.setQualifiedField(qualifiedField);
                seekerPreferences.setLastJob(lastJob);

                forward = new JobSeekerQualificationWindow();
                forward.recieveJobSeekerData(seeker, seekerPreferences, ID);

                if (oldUser)
                {                   
                    forward.Show();
                    this.Close();
                    forward.setOldUser(oldUser);
                }

                this.Close();
                forward.Show();

            }
        }

        private async void Button_Click_1(object sender, RoutedEventArgs e)
        {
            MessageDialogResult result =  await this.ShowMessageAsync("Error", "Do you want to close the window? All your unsaved data will be lost", MessageDialogStyle.AffirmativeAndNegative);
            if (result == MessageDialogResult.Affirmative)
            {
                foreach (UIElement element in jobSeekerPersonalInfoWindowGrid.Children)
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
