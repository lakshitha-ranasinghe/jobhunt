   M S B A M L   `   `   `  ����T  PMahApps.Metro, Version=1.1.2.0, Culture=neutral, PublicKeyToken=f4fb5a3c4d1e5b4fV;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.MetroMahApps.Metro.Controls  5   	    NIBM Project;   5NIBM_Project.JobSeekerProfessionalQualificationWindow   5      \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   PControls;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.Metro     5   	     � Title$   Professional Qualification��5   	   $	��730��6,   $	��800��69    � WindowStartupLocation$ CenterScreen=�6E   $��14(�6j   
������� � 
ResizeMode$ CanMinimize=�6�   .��5      � -   5        $%��jobSeekerProfessionalWindowGrid��5      .+�5	   
   �� $��Work Experience��6   $
��Left=�6,   $	��319��6G   $��
50,100,0,0q�6T   $	��Top=�6h   $	��700��6�   $��0.3,1,0.3,0.3q�6�   $��18(�6�   
����������	#FF49B4E8���	#FF49B4E8$��Segoe UI Light)�6�   �� 5
   
   $��Other ��6   $
��Left=�6#   $	��154��6>   $��
50,433,0,0q�6K   $	��Top=�6_   $	��700��6w   $��0.3,1,0.3,0.3q�6�   $��18(�6�   
����������	#FF49B4E8���	#FF49B4E8$��Segoe UI Light)�6�   �� 5   
   $!��Professional Qualifications��6   $
��Left=�66   $��	30,20,0,0q�6Q   $	��Top=�6d   $	��400��6|   $��61��6�   $��36(�6�   
���   �$��Segoe UI Light)�6�   �� 5   
   $)��#(  Relevant to the post applying  )��6   $
��Left=�6>   $��
50,125,0,0q�6Y   $	��Top=�6m   $��31��6�   $	��220��6�   
���   �$��12(�6�   $��Segoe UI Light)�6�   �� 5   
   $/��)Total No of years of experience as of now��6   $
��Left=�6D   $��
75,155,0,0q�6_   $	��Top=�6s   $��28��6�   $	��276��6�   
���   �$��Segoe UI Light)�6�   �� 5   
   -   5         ��Name$ ExpYears��5      *   $MahApps.Metro.Controls.TextBoxHelper   ClearTextButton . $
��Left=�6�   $��26��6�   $��360,155,0,0q�6�    �� TextWrapping$
 Wrap=�6�   $	��Top=�6�   $��60��6�   $��Segoe UI Light)�6  ��6R   �� *
expYears+ �� UpdateSourceTrigger$ PropertyChanged=�6  �� 5   
   $��Years��6   $
��Left=�6    $��425,155,0,0q�6;   $	��Top=�6P   $��26��6h   $��43��6t   
���   �$��Segoe UI Light)�6�   �� 5   
   $��Designation��6   $
��Left=�6&   $��
75,210,0,0q�6A   $	��Top=�6U   $��30��6m   $	��100��6y   
���   �$��Segoe UI Light)�6�   �� 5   
   -   5        $ FirstDesignation��5       .    	Watermark$% Enter your previous designation��6Z   $
��Left=�6�   $��26��6�   $��240,210,0,0q�6  $
 Wrap=�6   $	��Top=�64  $	��381��6L  $��Segoe UI Light)�6X  ��6�   �� *firstDesignation+$ PropertyChanged=�6X  �� 5   
   $��Company��6   $
��Left=�6"   $��
75,240,0,0q�6=   $	��Top=�6Q   $��30��6i   $	��100��6u   
���   �$��Segoe UI Light)�6�   �� 5   
   -   5        $ FirstCompany��5       . $ Enter Company Name��6V   $
��Left=�6�   $��26��6�   $��240,240,0,0q�6�   $
 Wrap=�6  $	��Top=�6  $	��381��67  $��Segoe UI Light)�6C  ��6�   �� *firstCompany+$ PropertyChanged=�6C  �� 5   
   $��Duration��6   $
��Left=�6#   $��
75,270,0,0q�6>   $	��Top=�6R   $��30��6j   $	��100��6v   
���   � J� RenderTransformOrigin$ 
0.252,0.51&�6�   $��Segoe UI Light)�6�   �� 5   
   $
��From��6   $
��Left=�6   $��240,270,0,0q�6:   $	��Top=�6O   $��26��6g   $��51��6s   
���   �$ 
0.252,0.51&�6�   $��Segoe UI Light)�6�   (  "System.Windows.Controls.DatePicker  5   
   -   5        	  Name$	 FirstStartDate��5      $
��Left=�6z   $��300,270,0,0q�6�   $	��Top=�6�   $��30��6�   $	��118��6�   $��Segoe UI Light)�6�   
   SelectedDate
 6-   �� *firstStartDate+$ PropertyChanged=�6�   �� 5   
   $��To��6   $
��Left=�6   $��450,270,0,0q�68   $	��Top=�6M   $��26��6e   $��38��6q   
���   �$ 
0.252,0.51&�6�   $��Segoe UI Light)�6�     5   
   -   5        $	 FirstEndDate��5      $
��Left=�6v   $��503,270,0,0q�6�   $	��Top=�6�   $��30��6�   $	��118��6�   $��Segoe UI Light)�6�   
 6+   �� *firstEndDate+$ PropertyChanged=�6�   �� 5   
   $��Designation��6   $
��Left=�6&   $��
75,315,0,0q�6A   $	��Top=�6U   $��30��6m   $	��100��6y   
���   �$��Segoe UI Light)�6�   �� 5   
   -   5        $ SecondDesignation��5       . $% Enter your previous designation��6[   $
��Left=�6�   $��26��6  $��240,315,0,0q�6  $
 Wrap=�6"  $	��Top=�66  $	��381��6N  $��Segoe UI Light)�6Z  ��6�   �� *secondDesignation+$ PropertyChanged=�6Z  �� 5   
   $��Company��6   $
��Left=�6"   $��
75,345,0,0q�6=   $	��Top=�6Q   $��30��6i   $	��100��6u   
���   �$��Segoe UI Light)�6�   �� 5   
   -   5        $ SecondCompany��5       . $ Enter Company Name��6W   $
��Left=�6�   $��26��6�   $��240,345,0,0q�6�   $
 Wrap=�6  $	��Top=�6!  $	��381��69  $��Segoe UI Light)�6E  ��6�   �� *secondCompany+$ PropertyChanged=�6E  �� 5   
   $��Duration��6   $
��Left=�6#   $��
75,375,0,0q�6>   $	��Top=�6R   $��30��6j   $	��100��6v   
���   �$ 
0.252,0.51&�6�   $��Segoe UI Light)�6�   �� 5   
   $
��From��6   $
��Left=�6   $��240,375,0,0q�6:   $	��Top=�6O   $��30��6g   $��51��6s   
���   �$ 
0.252,0.51&�6�   $��Segoe UI Light)�6�     5    
   -	   5        $	 SecondStartDate��5       $
��Left=�6|   $��300,375,0,0q�6�   $	��Top=�6�   $��30��6�   $	��118��6�   $��Segoe UI Light)�6�   
 6.   �� *secondStartDate+$ PropertyChanged=�6�   �� 5!   
   $��To��6   $
��Left=�6   $��450,375,0,0q�68   $	��Top=�6M   $��30��6e   $��38��6q   
���   �$ 
0.252,0.51&�6�   $��Segoe UI Light)�6�     5"   
   -
   5        $	 SecondEndDate��5"      $
��Left=�6x   $��503,375,0,0q�6�   $	��Top=�6�   $��30��6�   $	��118��6�   $��Segoe UI Light)�6�   
 6,   �� *secondEndDate+$ PropertyChanged=�6�   �� 5#   
   $)��#Projects, Publications, Reaseraches��6   $
��Left=�6>   $��
50,462,0,0q�6Y   $	��Top=�6m   $��26��6�   $	��248��6�   
���   �$ 
0.252,0.51&�6�   $��12(�6�   $��Segoe UI Light)�6�   �� 5$   
   $��Title��6   $
��Left=�6    $��
70,480,0,0q�6;   $	��Top=�6O   $��26��6g   $��93��6s   
���   �$ 
0.252,0.51&�6�   $��Segoe UI Light)�6�   �� 5%   
   $
��Year��6   $
��Left=�6   $��315,480,0,0q�6:   $	��Top=�6O   $��26��6g   $��93��6s   
���   �$ 
0.252,0.51&�6�   $��Segoe UI Light)�6�   �� 5&   
   $��Details��6   $
��Left=�6"   $��450,480,0,0q�6=   $	��Top=�6R   $��26��6j   $��93��6v   
���   �$ 
0.252,0.51&�6�   $��Segoe UI Light)�6�   �� 5'   
   -   5        $ OtherFirstTitle��5'       . $
��Left=�6�   $��26��6�   $��
70,506,0,0q�6�   $
 Wrap=�6�   $	��Top=�6�   $	��224��6  $��Segoe UI Light)�6  ��6Y   �� *otherFirstTitle+$ PropertyChanged=�6  �� 5(   
   -   5        $ OtherFirstYear��5(       . $
��Left=�6�   $��26��6�   $��315,506,0,0q�6�   $
 Wrap=�6�   $	��Top=�6�   $	��112��6  $��Segoe UI Light)�6  ��6X   �� *otherFirstYear+$ PropertyChanged=�6  �� 5)   
   -   5        $ OtherFirstDetails��5)       . $
��Left=�6�   $��26��6�   $��450,506,0,0q�6�   $
 Wrap=�6�   $	��Top=�6�   $	��273��6  $��Segoe UI Light)�6  ��6[   �� *otherFirstDetails+$ PropertyChanged=�6  �� 5*   
   -   5        $ OtherSecondTitle��5*       . $
��Left=�6�   $��26��6�   $��
70,537,0,0q�6�   $
 Wrap=�6�   $	��Top=�6�   $	��224��6  $��Segoe UI Light)�6  ��6Z   �� *otherSecondTitle+$ PropertyChanged=�6  �� 5+   
   -   5        $ OtherSecondYear��5+       . $
��Left=�6�   $��26��6�   $��315,537,0,0q�6�   $
 Wrap=�6�   $	��Top=�6�   $	��112��6  $��Segoe UI Light)�6  ��6Y   �� *otherSecondYear+$ PropertyChanged=�6  �� 5,   
   -   5        $ OtherSecondDetails��5,       . $
��Left=�6�   $��26��6�   $��450,537,0,0q�6�   $
 Wrap=�6�   $	��Top=�6�   $	��273��6  $��Segoe UI Light)�6  ��6\   �� *otherSecondDetails+$ PropertyChanged=�6  �� 5-   
   $
��Left=�6   $	��100��6/   $��	30,25,0,0q�6<   $	��Top=�6O   $	��650��6g   $��Segoe UI Light)�6s   
���   �
���   �6�   �� 5.   
   $
��Left=�6   $��44��6/   $��
75,284,0,0q�6;   $	��Top=�6O   $	��648��6g   $��Segoe UI Light)�6s   
���   �
���   �6�   �� 5/   
   $
��Left=�6   $��50��6/   $��
75,174,0,0q�6;   $	��Top=�6O   $	��648��6g   $��Segoe UI Light)�6s   
���   �
���   �6�   �� 50   
   -   5        $��Clear��50         AccentedSquareButtonStyle#��[  6!   $
��Left=�6T   $��163,616,0,0q�6o   $	��Top=�6�   $	��165��6�   $��55��6�   
���   ����	#FF49B4E8
���   �$��16(�6�   $��Segoe UI Light)�6  �� 51   
   -   5        $��Submit��51      #��[  6"   $
��Left=�6U   $��473,616,0,0q�6p   $	��Top=�6�   $	��165��6�   $��55��6�   
���   ����	#FF49B4E8
���   �$��16(�6�   $��Segoe UI Light)�6  52      53      