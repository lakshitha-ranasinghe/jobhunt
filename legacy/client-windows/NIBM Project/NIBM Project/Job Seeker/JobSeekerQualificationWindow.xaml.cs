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
    /// Interaction logic for JobSeekerQualificationWindow.xaml
    /// </summary>
    public partial class JobSeekerQualificationWindow : MetroWindow
    {
        private JobSeeker jobSeekerPersonalData; //Personal Data object from previous Screen
        private JobSeekerPreferences jobSeekerPreferencesData; //Job Seeker Preferences Data from Previous Menu

        private OrdinaryLevel ordinaryLevel; //Ordinary Level Reference
        private AdvanceLevel advanceLevel; //Advance Level Reference
        private HigherEducation higherEducation; //Higher Education Reference

        private JobSeekerEducation jobSeekerEducation; //Job Seeker Education Reference

        private JobSeekerProfessionalQualificationWindow forward; //To Forward Objects to next Screen
        private int ID; //JOB SEEKER ID

        bool oldUser = false;
        Validate val;

        //Ordinary Level Subjects
        public String ordinaryLevelYear { get; set; }
        public String ordinaryLevelSchool { get; set; } 
        public String firstSubject { get; set; }
        public String secondSubject { get; set; }
        public String thirdSubject { get; set; }
        public String fourthSubject { get; set; }
        public String fifthSubject { get; set; }
        public String sixthSubject { get; set; }
        
        //Ordinary Level Results

        public String firstSubjectR { get; set; }
        public String secondSubjectR { get; set; }
        public String thirdSubjectR { get; set; }
        public String fourthSubjectR { get; set; }
        public String fifthSubjectR { get; set; }
        public String sixthSubjectR { get; set; }

        //Advance Level Subjects
        public String firstSubjectA { get; set; }
        public String secondSubjectA { get; set; }
        public String thirdSubjectA { get; set; }
        public String fourthSubjectA { get; set; }

        //Advance Level Results
        public String firstSubjectAR { get; set; }
        public String secondSubjectAR { get; set; }
        public String thirdSubjectAR { get; set; }
        public String fourthSubjectAR { get; set; }

        public String advanceLevelYear { get; set; }
        public String advanceLevelSchool { get; set; }

        //Higher Education 

        //Institute
        public String firstInstitute { get; set; }
        public String secondInstitute { get; set; }
        public String thirdInstitute { get; set; }

        //Course
        public String firstCourse { get; set; }
        public String secondCourse { get; set; }
        public String thirdCourse { get; set; }

        //Year
        public String firstYear { get; set; }
        public String secondYear { get; set; }
        public String thirdYear { get; set; }

        //GPA 
        public String firstGPA { get; set; }
        public String secondGPA { get; set; }
        public String thirdGPA { get; set; }

        public JobSeekerQualificationWindow()
        {
            InitializeComponent();
            DataContext = this;
        }

        //Receive Objects Containing Data from previous Screen
        public void recieveJobSeekerData(JobSeeker jobSeekerPersonalData, JobSeekerPreferences jobSeekerPreferencesData, int id) 
        {
            this.jobSeekerPersonalData = jobSeekerPersonalData;
            this.jobSeekerPreferencesData = jobSeekerPreferencesData;
            this.ID = id;
        }

        //Set Old Profile Values for Exsiting Users
        public void setOldUser(bool oldUser)
        {
            Console.Write("Old User");
            this.oldUser = oldUser;

            this.jobSeekerEducation = StaticJobSeekerData.getJobSeekerEducation();
         
            OLSchool.Text = jobSeekerEducation.getOLSchool();
            OLYear.Text = jobSeekerEducation.getOLYear().ToString();
            OLFirstSubject.Text = jobSeekerEducation.getOLSubjects()[0];
            OLSecondSubject.Text = jobSeekerEducation.getOLSubjects()[1];
            OLThirdSubject.Text = jobSeekerEducation.getOLSubjects()[2];
            OLFourthSubject.Text = jobSeekerEducation.getOLSubjects()[3];
            OLFifthSubject.Text = jobSeekerEducation.getOLSubjects()[4];
            OLSixthSubject.Text = jobSeekerEducation.getOLSubjects()[5];
            FirstOLResult.SelectedValue = jobSeekerEducation.getOLResults()[0];
            SecondOLResult.SelectedValue = jobSeekerEducation.getOLResults()[1];
            ThirdOLResult.SelectedValue = jobSeekerEducation.getOLResults()[2];
            FourthOLResult.SelectedValue = jobSeekerEducation.getOLResults()[3];
            FifthOLResult.SelectedValue = jobSeekerEducation.getOLResults()[4];
            SixthOLResult.SelectedValue = jobSeekerEducation.getOLResults()[5];

            ALYear.SelectedValue = jobSeekerEducation.getALYear();
            ALSchool.Text = jobSeekerEducation.getALSchool();
            FirstALSubject.Text = jobSeekerEducation.getALSubjects()[0];
            SecondALSubject.Text = jobSeekerEducation.getALSubjects()[1];
            ThirdALSubject.Text = jobSeekerEducation.getALSubjects()[2];
            FourthALSubject.Text = jobSeekerEducation.getALSubjects()[3];
            FirstALResult.SelectedValue = jobSeekerEducation.getALResults()[0];
            SecondALResult.SelectedValue = jobSeekerEducation.getALResults()[1];
            ThirdALResult.SelectedValue = jobSeekerEducation.getALResults()[2];
            FourthALResult.SelectedValue = jobSeekerEducation.getALResults()[3];

            FirstInstitute.Text = jobSeekerEducation.getInstitute()[0];
            SecondInstitute.Text = jobSeekerEducation.getInstitute()[1];
            ThirdInstitute.Text = jobSeekerEducation.getInstitute()[2];
            FirstCourse.Text = jobSeekerEducation.getCourse()[0];
            SecondCourse.Text = jobSeekerEducation.getCourse()[1];
            ThirdCourse.Text = jobSeekerEducation.getCourse()[2];
            FirstYear.Text = jobSeekerEducation.getYear()[0].ToString();
            SecondYear.Text = jobSeekerEducation.getYear()[1].ToString();
            ThirdYear.Text = jobSeekerEducation.getYear()[2].ToString();
            FirstGPA.Text = jobSeekerEducation.getGPA()[0].ToString();
            SecondGPA.Text = jobSeekerEducation.getGPA()[1].ToString();
            ThirdGPA.Text = jobSeekerEducation.getGPA()[2].ToString();     
        }

        private async void Button_Click(object sender, RoutedEventArgs e)
        {            
            val = new Validate();

            if (OLYear.Text.Length==0)
            {
                await this.ShowMessageAsync("Warning", "Please Select the Year of your Ordinary Level Exam", MessageDialogStyle.Affirmative);
            }
            else if (OLYear.SelectedIndex==0)
            {
                await this.ShowMessageAsync("Warning", "Ordinary Level School Should not be empty", MessageDialogStyle.Affirmative);
            }
            else if (OLFirstSubject.Text.Length == 0 || OLSecondSubject.Text.Length==0 || OLThirdSubject.Text.Length == 0 || OLFourthSubject.Text.Length == 0 || OLFifthSubject.Text.Length == 0 || OLSixthSubject.Text.Length == 0)
            {
                await this.ShowMessageAsync("Warning", "All main six subjects in ordinary level exam must be completed", MessageDialogStyle.Affirmative);
            }
            else if (FirstOLResult.SelectedIndex == 0 || SecondOLResult.SelectedIndex == 0 || ThirdOLResult.SelectedIndex == 0 || FourthOLResult.SelectedIndex == 0 || FifthOLResult.SelectedIndex == 0 || SixthOLResult.SelectedIndex == 0)
            {
                await this.ShowMessageAsync("Warning", "Result of each and every Ordinary level exam subject mmust be selected", MessageDialogStyle.Affirmative);
            }
            else if (ALYear.SelectedIndex==0)
            {
                await this.ShowMessageAsync("Warning", "Please Select the Year of your Advanced Level Exam", MessageDialogStyle.Affirmative);
            }           

            else{
                if (ALSchool.Text.Length==0)
                {
                    advanceLevelSchool = "Not Specified";
                }
                if (FirstALSubject.Text.Length == 0)
                {
                    firstSubjectA = "Not Specified";
                }
                if (SecondALSubject.Text.Length == 0)
                {
                    secondSubjectA = "Not Specified";
                }
                if (ThirdALSubject.Text.Length == 0)
                {
                    thirdSubjectA = "Not Specified";
                }
                if (FourthALSubject.Text.Length == 0)
                {
                    fourthSubjectA = "Not Specified";                   
                }
                if (FirstInstitute.Text.Length == 0)
                {
                    firstInstitute = "Not Specified";
                }
                if (SecondInstitute.Text.Length == 0)
                {
                    secondInstitute = "Not Specified";
                }
                if (ThirdInstitute.Text.Length == 0)
                {
                    thirdInstitute = "Not Specified";
                }
                if(FirstCourse.Text.Length == 0)
                {
                    firstCourse = "Not Specified";
                }
                if (SecondCourse.Text.Length == 0)
                {
                    secondCourse = "Not Specified";
                }
                if (ThirdCourse.Text.Length == 0)
                {
                    thirdCourse = "Not Specified";
                }
                if (FirstYear.Text.Length == 0)
                {
                    firstYear = "0";
                }
                if (SecondYear.Text.Length == 0)
                {
                    secondYear = "0";
                }
                if (ThirdYear.Text.Length == 0)
                {
                    thirdYear = "0";
                }
                if (FirstGPA.Text.Length == 0)
                {
                    firstGPA = "0";
                }
                if (SecondGPA.Text.Length == 0)
                {
                    secondGPA = "0";
                }
                if (ThirdGPA.Text.Length == 0)
                {
                    thirdGPA = "0";
                }
             
                ordinaryLevel = new OrdinaryLevel();
                advanceLevel = new AdvanceLevel();
                higherEducation = new HigherEducation();

                //Set O/L Subjects to the final Object

                ordinaryLevel.setYear(Convert.ToInt32(ordinaryLevelYear));
                ordinaryLevel.setSchool(ordinaryLevelSchool);

                ordinaryLevel.setSubjects(0, firstSubject);
                ordinaryLevel.setSubjects(1, secondSubject);
                ordinaryLevel.setSubjects(2, thirdSubject);
                ordinaryLevel.setSubjects(3, fourthSubject);
                ordinaryLevel.setSubjects(4, fifthSubject);
                ordinaryLevel.setSubjects(5, sixthSubject);

                //Set O/L Results to the final Object
                ordinaryLevel.setResults(0, firstSubjectR);
                ordinaryLevel.setResults(1, secondSubjectR);
                ordinaryLevel.setResults(2, thirdSubjectR);
                ordinaryLevel.setResults(3, fourthSubjectR);
                ordinaryLevel.setResults(4, fifthSubjectR);
                ordinaryLevel.setResults(5, sixthSubjectR);

                //Set A/L Subjects to the final Object

                advanceLevel.setYear(Convert.ToInt32(advanceLevelYear));
                advanceLevel.setSchool(advanceLevelSchool);

                advanceLevel.setSubjects(0, firstSubjectA);
                advanceLevel.setSubjects(1, secondSubjectA);
                advanceLevel.setSubjects(2, thirdSubjectA);
                advanceLevel.setSubjects(3, fourthSubjectA);

                //Set A/L Results to the final Object
                advanceLevel.setResults(0, firstSubjectAR);
                advanceLevel.setResults(1, secondSubjectAR);
                advanceLevel.setResults(2, thirdSubjectAR);
                advanceLevel.setResults(3, fourthSubjectAR);

                //Set Higher Education to the final Object
                higherEducation.setInstitute(0, firstInstitute);
                higherEducation.setInstitute(1, secondInstitute);
                higherEducation.setInstitute(2, thirdInstitute);

                higherEducation.setCourse(0, firstCourse);
                higherEducation.setCourse(1, secondCourse);
                higherEducation.setCourse(2, thirdCourse);

                higherEducation.setYear(0, Convert.ToInt32(firstYear));
                higherEducation.setYear(1, Convert.ToInt32(secondYear));
                higherEducation.setYear(2, Convert.ToInt32(thirdYear));

                higherEducation.setGpa(0, Convert.ToDouble(firstGPA));
                higherEducation.setGpa(1, Convert.ToDouble(secondGPA));
                higherEducation.setGpa(2, Convert.ToDouble(thirdGPA));

                jobSeekerEducation = new JobSeekerEducation();
                jobSeekerEducation.setId(ID);
                jobSeekerEducation.setOrdinaryLevel(ordinaryLevel);
                jobSeekerEducation.setAdvanceLevel(advanceLevel);
                jobSeekerEducation.setHigherEducation(higherEducation);

                //Forwarding Data
                forward = new JobSeekerProfessionalQualificationWindow();
                forward.receiveJobSeekerData(jobSeekerPersonalData, jobSeekerPreferencesData, jobSeekerEducation, ID);

                if (oldUser)
                {
                    forward.Show();
                    this.Close();
                    forward.setOldUser(oldUser);
                }

                forward.Show();
                this.Close();
            }
        }
        private async void Button_Click_1(object sender, RoutedEventArgs e)
        {
            MessageDialogResult result =  await this.ShowMessageAsync("Error", "Do you want to close the window? All your unsaved data will be lost", MessageDialogStyle.AffirmativeAndNegative);
            if (result == MessageDialogResult.Affirmative)
            {
                foreach (UIElement element in jobSeekerQualificationWindowGrid.Children)
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

    public class OrdinaryLevel
    {
        private int year;
        private String school;
        private String[] ordinaryLevelSubjects;
        private String[] ordinaryLevelSubjectsResuls;
        private int[] ordinaryLevelId = new int[6];

        public OrdinaryLevel()
        {
            ordinaryLevelSubjects = new String[6];
            ordinaryLevelSubjectsResuls = new String[6];
        }
        public void setSubjects(int location, String value)
        {
            ordinaryLevelSubjects[location] = value;
        }
        public void setResults(int location, String value)
        {
            ordinaryLevelSubjectsResuls[location] = value;
        }
        public void setYear(int year)
        {
            this.year = year;
        }
        public void setSchool(String school)
        {
            this.school = school;
        }
        public void setOrdinaryLevelId(int location, int id)
        {
            ordinaryLevelId[location] = id;           
        }

        //Get
        public int getYear()
        {
            return year;
        }
        public String getSchool()
        {
            return school;
        }
        public String[] getSubjects()
        {
            return ordinaryLevelSubjects;
        }
        public String[] getResults()
        {
            return ordinaryLevelSubjectsResuls;
        }
        public int[] getOrdinaryLevelId()
        {
            return ordinaryLevelId;
        }
    }

    public class AdvanceLevel
    {
        private int year;
        private String school;
        private String[] advanceLevelSubjects;
        private String[] advanceLevelSubjectsResults;
        private int[] advanceLevelId = new int[4];

        public AdvanceLevel()
        {
            advanceLevelSubjects = new String[4];
            advanceLevelSubjectsResults = new String[4];

        }
        public void setSubjects(int location, String value)
        {
            advanceLevelSubjects[location] = value;
        }
        public void setResults(int location, String value)
        {
            advanceLevelSubjectsResults[location] = value;
        }
        public void setYear(int year)
        {
            this.year = year;
        }
        public void setSchool(String school)
        {
            this.school = school;
        }
        public void setAdvanceLevelId(int location, int id)
        {
            advanceLevelId[location] = id;
        }

        //Get
        public int getYear()
        {
            return year;
        }
        public String getSchool()
        {
            return school;
        }
        public String[] getSubjects()
        {
            return advanceLevelSubjects;
        }
        public String[] getResults()
        {
            return advanceLevelSubjectsResults;
        }
        public int[] getAdvanceLevelId()
        {
            return advanceLevelId;
        }
        
    }
    public class HigherEducation
    {
        private String[] institute;
        private String[] course;
        private int[] year;
        private double[] gpa;
        private int[] higherEducationId;

        public HigherEducation()
        {
            institute = new String[3];
            course = new String[3];
            year = new int[3];
            gpa = new double[3];
            higherEducationId = new int[3];
        }
        public void setInstitute(int location, String value)
        {
            institute[location] = value;
        }
        public void setCourse(int location, String value)
        {
            course[location] = value;
        }
        public void setYear(int location, int value)
        {
            year[location] = value;
        }
        public void setGpa(int location, double value)
        {
            gpa[location] = value;
        }
        public void setHigherEducationId(int location, int id)
        {
            higherEducationId[location] = id;
        }

        //Get
        public String[] getInstitue()
        {
            return institute;
        }
        public String[] getCourse()
        {
            return course;
        }
        public int[] getYear()
        {
            return year;
        }
        public double[] getGPA()
        {
            return gpa;
        }
        public int[] getHigherEducationId()
        {
            return higherEducationId;
        }
    }
}
