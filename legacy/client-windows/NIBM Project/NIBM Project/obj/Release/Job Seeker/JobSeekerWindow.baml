   M S B A M L   `   `   `  ���� T  PMahApps.Metro, Version=1.1.2.0, Culture=neutral, PublicKeyToken=f4fb5a3c4d1e5b4fV;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.MetroMahApps.Metro.Controls   NIBM Project"   NIBM_Project.JobSeekerWindow   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     PControls;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.Metro       � Title$  Registration��$	��730��$	��800�� � WindowStartupLocation$ CenterScreen=�$��14(�
�������$��0q�(   "MahApps.Metro.Controls.MetroWindow   	GlowBrush$ Black�� � 
ResizeMode$ CanResizeWithGrip=�   WindowTransitionsEnabled .  .��� -   $%��jobSeekerPersonalInfoWindowGrid��$��0,0,2,1q�.+��� $��Personal Details��$
��Left=�$��
60,120,0,0q�$	��Top=�$	��242��$	��674��$��18(����	#FF49B4E8$��Segoe UI Light)�$��0.3,1,0.3,0.3q�
���   ����	#FF49B4E8�� $��Preferences��$
��Left=�$��
60,379,0,0q�$	��Top=�$	��166��$	��674��$��0.3,1,0.3,0.3q�$��18(����	#FF49B4E8���	#FF49B4E8$��Segoe UI Light)��� -    ��Name$ 	FirstName��*   $MahApps.Metro.Controls.TextBoxHelper   ClearTextButton .    	Watermark$  Enter your First Name here��$
��Left=�$��26��$��165,180,0,0q� �� TextWrapping$
 Wrap=�$	��Top=�$	��183��$��Segoe UI Light)����� *	firstName+	 �� UpdateSourceTrigger$	 PropertyChanged=��� -   $ LastName�� . $ Enter your Last Name here��$
��Left=�$��26��$��523,180,0,0q�$
 Wrap=�$	��Top=�$	��183��$��Segoe UI Light)����� *
lastName+$	 PropertyChanged=��� -   $ AddressLine1�� . $ Address Line 1��$
��Left=�$��26��$��523,215,0,0q�$
 Wrap=�$	��Top=�$	��183��
 J� RenderTransformOrigin$
 0.475,2.885&�$��Segoe UI Light)����� *addressLine1+$	 PropertyChanged=��� -   $ AddressLine2�� . $ Address Line 2��$
��Left=�$��26��$��523,250,0,0q�$
 Wrap=�$	��Top=�$	��183��$
 0.475,2.885&�$��Segoe UI Light)����� *addressLine2+$	 PropertyChanged=��� -   $ Email�� . $ E-Mail Address��$
��Left=�$��26��$��523,320,0,0q�$
 Wrap=�$	��Top=�$	��183��$
 0.475,2.885&�$��Segoe UI Light)����� *email+$	 PropertyChanged=��� -   $ HomeTelephone�� . $
 Home��$
��Left=�$��26��$��165,320,0,0q�$
 Wrap=�$	��Top=�$	��183��$
 0.475,2.885&�$��Segoe UI Light)����� *	telMobile+$	 PropertyChanged=��� -   $ MobileTelephone�� . $ Mobile��$
��Left=�$��26��$��165,285,0,0q�$
 Wrap=�$	��Top=�$	��183��$
 0.475,2.885&�$��Segoe UI Light)����� *	telHome+$	 PropertyChanged=��� $��
First Name��$
��Left=�$��
70,180,0,0q�$	��Top=�$��26��$��89��
���   �$��Segoe UI Light)��� $��	Last Name��$
��Left=�$��395,180,0,0q�$	��Top=�$��26��$��89��
���   �$��Segoe UI Light)��� $��Address��$
��Left=�$��395,215,0,0q�$	��Top=�$��88��$��89��
���   �$��Segoe UI Light)��� $��	Telephone��$
��Left=�$��
70,285,0,0q�$	��Top=�$��57��$��89��
���   �$��Segoe UI Light)��� $ ��Tell us about Yourself..!!��$
��Left=�$��	50,25,0,0q�$	��Top=�$	��350��$��61��$��36(�
���   �$��Segoe UI Light)��� $��Email��$
��Left=�$��395,320,0,0q�$	��Top=�$��26��$��89��
���   �$��Segoe UI Light)��� -	   $ AddressLine3�� . $ Address Line 3��$
��Left=�$��26��$��523,285,0,0q�$
 Wrap=�$	��Top=�$	��183��$
 0.475,2.885&�$��Segoe UI Light)����� *addressLine3+$	 PropertyChanged=��� $
��Left=�$	��100��$��278,205,0,0q�$	��Top=�$	��195��$
 0.5,0.5&�$��Segoe UI Light)�z�V� .~��� �� ��  �� Angle$ -89.885Q�T� �� $��Date of Birth��$
��Left=�$��
70,250,0,0q�$	��Top=�$��26��$��89��
���   �$��Segoe UI Light)�(  "System.Windows.Controls.DatePicker  -
     Name$ DataOfBirth��$
��Left=�$��165,250,0,0q�$	��Top=�$	��183��$��27��$��Segoe UI Light)�   SelectedDate �� *dateOfBirth+$	 PropertyChanged=��� $��Title��$
��Left=�$��
70,215,0,0q�$	��Top=�$��26��$��89��
���   �$��Segoe UI Light)��� -   $��TitleBox�� �� SelectedValuePath$ Content��$
��Left=�$��165,215,0,0q�$	��Top=�$	��183��$��25�� �� SelectedIndex$ 0��$��Segoe UI Light)� �� SelectedValue �� *title+$	 PropertyChanged=�.@��� $��Select a Title�� �� 
IsSelected . 
��������� $��Mr���� $	��Mrs���� $��Ms���� $��Dr���� $
��Left=�$��19��$��	50,75,0,0q�$	��Top=�$	��700��$��Segoe UI Light)����	#FF2D2D30
���   ����	#FF49B4E8�� -      AccentedSquareButtonStyle#��[  $��Clear��$	��145��$��57��$
��Left=�$��200,590,0,0q�$	��Top=�$��Segoe UI Light)�$
 0.538,-0.615&�$��16(����	#FF49B4E8
���   �
���   ��� -   #��[  $��Next ��$	��145��$��57��$
��Left=�$��480,590,0,0q�$	��Top=�$��Segoe UI Light)�$
 0.538,-0.615&�$��16(����	#FF49B4E8
���   �
���   ��� -    ��Name$ QualifiedField��$ Content��$
��Left=�$��476,415,0,0q�$	��Top=�$	��230��$��30��$ 0����.  $��Segoe UI Light)� �� *qualifiedField+$	 PropertyChanged=�.@��� $��Select a Field�� . 
��������� $��Information Technology���� $��
Management���� $��Engineering���� $��	Education���� $��Sales and Marketing���� $��Other���� $ ��Field you are qualified in��$
��Left=�$��
75,415,0,0q�$	��Top=�$	��352��
���   �$��29��$��Segoe UI Light)��� $*��$What kind of Job you are looking for��$
��Left=�$��
75,450,0,0q�$	��Top=�$	��352��
���   �$��29��$��Segoe UI Light)��� -   $ LastJob��$ Content��$
��Left=�$��476,485,0,0q�$	��Top=�$	��230��$��29��$ 0��$��Segoe UI Light)� �� *	lastJob+$	 PropertyChanged=�.@��� $
��Temp���� $��Intern���� $�� ���� �� $��Last Job (if any)��$
��Left=�$��
75,485,0,0q�$	��Top=�$	��352��
���   �$��29��$��Segoe UI Light)��� -   $ InterestedJob��$ Content��$
��Left=�$��476,450,0,0q�$	��Top=�$	��230��$��29��$ 0��$��Segoe UI Light)� �� *interestedPosition+$	 PropertyChanged=�.@��� $��Select a Job field�� . 
��������� $	��CIO���� $��Director of Engineering���� $��Software Engineer���� $��Technical Lead���� $��Solution Architecture���� $��Solution Engineer���� $��System Engineer���� $��Sales Representative���� $��General Manager���� $��Accounts Executive���� $��Technical Supervisor���� $��Marketing Executive���� $%��Channel Sales Operation Manager���� $��Other��