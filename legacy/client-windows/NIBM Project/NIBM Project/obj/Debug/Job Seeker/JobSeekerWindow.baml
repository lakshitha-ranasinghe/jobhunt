   M S B A M L   `   `   `  ����T  PMahApps.Metro, Version=1.1.2.0, Culture=neutral, PublicKeyToken=f4fb5a3c4d1e5b4fV;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.MetroMahApps.Metro.Controls  5   	    NIBM Project"   NIBM_Project.JobSeekerWindow   5      \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   PControls;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.Metro     5   	     � Title$  Registration��5   	   $	��730��6   $	��800��6+    � WindowStartupLocation$ CenterScreen=�67   $��14(�6\   
�������$��0q�5      (   "MahApps.Metro.Controls.MetroWindow   	GlowBrush$ Black��5       � 
ResizeMode$ CanResizeWithGrip=�5         WindowTransitionsEnabled .  .��5      � -   5        $%��jobSeekerPersonalInfoWindowGrid��5      $��0,0,2,1q�62   .+�5   
   �� $��Personal Details��6   $
��Left=�6-   $��
60,120,0,0q�6H   $	��Top=�6\   $	��242��6t   $	��674��6�   $��18(�6�   ���	#FF49B4E8$��Segoe UI Light)�6�   $��0.3,1,0.3,0.3q�6�   
���   ����	#FF49B4E86  �� 5   
   $��Preferences��6   $
��Left=�6(   $��
60,379,0,0q�6C   $	��Top=�6W   $	��166��6o   $	��674��6|   $��0.3,1,0.3,0.3q�6�   $��18(�6�   ���	#FF49B4E8���	#FF49B4E8$��Segoe UI Light)�6�   �� 5   
   -   5         ��Name$ 	FirstName��5      *   $MahApps.Metro.Controls.TextBoxHelper   ClearTextButton .    	Watermark$  Enter your First Name here��6�   $
��Left=�6�   $��26��6�   $��165,180,0,0q�6�    �� TextWrapping$
 Wrap=�6  $	��Top=�6"  $	��183��6:  $��Segoe UI Light)�6F  ��6%   �� *	firstName+	 �� UpdateSourceTrigger$	 PropertyChanged=�6F  �� 5   
   -   5        $ LastName��5       . $ Enter your Last Name here��6�   $
��Left=�6�   $��26��6�   $��523,180,0,0q�6�   $
 Wrap=�6  $	��Top=�6  $	��183��67  $��Segoe UI Light)�6C  ��6$   �� *
lastName+$	 PropertyChanged=�6C  �� 5   
   -   5        $ AddressLine1��5       . $ Address Line 1��6�   $
��Left=�6�   $��26��6�   $��523,215,0,0q�6�   $
 Wrap=�6  $	��Top=�6  $	��183��64  
 J� RenderTransformOrigin$
 0.475,2.885&�6@  $��Segoe UI Light)�6d  ��6(   �� *addressLine1+$	 PropertyChanged=�6d  �� 5   
   -   5        $ AddressLine2��5       . $ Address Line 2��6�   $
��Left=�6�   $��26��6�   $��523,250,0,0q�6�   $
 Wrap=�6  $	��Top=�6  $	��183��64  $
 0.475,2.885&�6@  $��Segoe UI Light)�6d  ��6(   �� *addressLine2+$	 PropertyChanged=�6d  �� 5   
   -   5        $ Email��5       . $ E-Mail Address��6�   $
��Left=�6�   $��26��6�   $��523,320,0,0q�6�   $
 Wrap=�6�   $	��Top=�6  $	��183��6&  $
 0.475,2.885&�62  $��Segoe UI Light)�6V  ��6!   �� *email+$	 PropertyChanged=�6V  �� 5   
   -   5        $ HomeTelephone��5       . $
 Home��6�   $
��Left=�6�   $��26��6�   $��165,320,0,0q�6�   $
 Wrap=�6�   $	��Top=�6  $	��183��6(  $
 0.475,2.885&�64  $��Segoe UI Light)�6X  ��6)   �� *	telMobile+$	 PropertyChanged=�6X  �� 5   
   -   5        $ MobileTelephone��5       . $ Mobile��6�   $
��Left=�6�   $��26��6�   $��165,285,0,0q�6�   $
 Wrap=�6�   $	��Top=�6  $	��183��6*  $
 0.475,2.885&�66  $��Segoe UI Light)�6Z  ��6+   �� *	telHome+$	 PropertyChanged=�6Z  �� 5   
   $��
First Name��6   $
��Left=�6%   $��
70,180,0,0q�6@   $	��Top=�6T   $��26��6l   $��89��6x   
���   �$��Segoe UI Light)�6�   �� 5   
   $��	Last Name��6   $
��Left=�6$   $��395,180,0,0q�6?   $	��Top=�6T   $��26��6l   $��89��6x   
���   �$��Segoe UI Light)�6�   �� 5   
   $��Address��6   $
��Left=�6"   $��395,215,0,0q�6=   $	��Top=�6R   $��88��6j   $��89��6v   
���   �$��Segoe UI Light)�6�   �� 5   
   $��	Telephone��6   $
��Left=�6$   $��
70,285,0,0q�6?   $	��Top=�6S   $��57��6k   $��89��6w   
���   �$��Segoe UI Light)�6�   �� 5   
   $ ��Tell us about Yourself..!!��6   $
��Left=�65   $��	50,25,0,0q�6P   $	��Top=�6c   $	��350��6{   $��61��6�   $��36(�6�   
���   �$��Segoe UI Light)�6�   �� 5   
   $��Email��6   $
��Left=�6    $��395,320,0,0q�6;   $	��Top=�6P   $��26��6h   $��89��6t   
���   �$��Segoe UI Light)�6�   �� 5   
   -	   5        $ AddressLine3��5       . $ Address Line 3��6�   $
��Left=�6�   $��26��6�   $��523,285,0,0q�6�   $
 Wrap=�6  $	��Top=�6  $	��183��64  $
 0.475,2.885&�6@  $��Segoe UI Light)�6d  ��6(   �� *addressLine3+$	 PropertyChanged=�6d  �� 5   
   $
��Left=�6   $	��100��6/   $��278,205,0,0q�6<   $	��Top=�6Q   $	��195��6i   $
 0.5,0.5&�6u   $��Segoe UI Light)�6�   z�5      V� 5      .~�5       �� �� 5!      �� 5"       �� Angle$ -89.885Q�6&   T� 5#      5$      5&      �� 5'   
   $��Date of Birth��6   $
��Left=�6(   $��
70,250,0,0q�6C   $	��Top=�6W   $��26��6o   $��89��6{   
���   �$��Segoe UI Light)�6�   (  "System.Windows.Controls.DatePicker  5(   
   -
   5          Name$ DataOfBirth��5(      $
��Left=�6t   $��165,250,0,0q�6�   $	��Top=�6�   $	��183��6�   $��27��6�   $��Segoe UI Light)�6�      SelectedDate 6*   �� *dateOfBirth+$	 PropertyChanged=�6�   �� 5)   
   $��Title��6   $
��Left=�6    $��
70,215,0,0q�6;   $	��Top=�6O   $��26��6g   $��89��6s   
���   �$��Segoe UI Light)�6�   �� 5*   
   -   5        $��TitleBox��5*       �� SelectedValuePath$ Content��6h   $
��Left=�6�   $��165,215,0,0q�6�   $	��Top=�6�   $	��183��6�   $��25��6�    �� SelectedIndex$ 0��6�   $��Segoe UI Light)�6�    �� SelectedValue 6#   �� *title+$	 PropertyChanged=�.@�5+      �� $��Select a Title��6    �� 
IsSelected . 
�������6F   �� 5,      $��Mr��6   �� 5-      $	��Mrs��6   �� 5.      $��Ms��6   �� 5/      $��Dr��6   50      �� 51   
   $
��Left=�6   $��19��6/   $��	50,75,0,0q�6;   $	��Top=�6N   $	��700��6f   $��Segoe UI Light)�6r   ���	#FF2D2D30
���   ����	#FF49B4E86�   �� 52   
   -   5           AccentedSquareButtonStyle#��[  52      $��Clear��6E   $	��145��6U   $��57��6a   $
��Left=�6m   $��200,590,0,0q�6�   $	��Top=�6�   $��Segoe UI Light)�6�   $
 0.538,-0.615&�6�   $��16(�6�   ���	#FF49B4E8
���   �
���   �6F  �� 53   
   -   5        #��[  53      $��Next ��6E   $	��145��6U   $��57��6a   $
��Left=�6m   $��480,590,0,0q�6�   $	��Top=�6�   $��Segoe UI Light)�6�   $
 0.538,-0.615&�6�   $��16(�6�   ���	#FF49B4E8
���   �
���   �6D  �� 54   
   -   5         ��Name$ QualifiedField��54      $ Content��6y   $
��Left=�6�   $��476,415,0,0q�6�   $	��Top=�6�   $	��230��6�   $��30��6�   $ 0��6�   ��.  $��Segoe UI Light)�6   6+   �� *qualifiedField+$	 PropertyChanged=�.@�55      �� $��Select a Field��6    . 
�������6F   �� 56      $��Information Technology��6   �� 57      $��
Management��6   �� 58      $��Engineering��6   �� 59      $��	Education��6   �� 5:      $��Sales and Marketing��6   �� 5;      $��Other��6   5<      �� 5=   
   $ ��Field you are qualified in��6   $
��Left=�65   $��
75,415,0,0q�6P   $	��Top=�6d   $	��352��6|   
���   �$��29��6�   $��Segoe UI Light)�6�   �� 5>   
   $*��$What kind of Job you are looking for��6   $
��Left=�6?   $��
75,450,0,0q�6Z   $	��Top=�6n   $	��352��6�   
���   �$��29��6�   $��Segoe UI Light)�6�   �� 5?   
   -   5        $ LastJob��5?      $ Content��6k   $
��Left=�6�   $��476,485,0,0q�6�   $	��Top=�6�   $	��230��6�   $��29��6�   $ 0��6�   $��Segoe UI Light)�6�    6$   �� *	lastJob+$	 PropertyChanged=�.@�5@      �� $
��Temp��6   �� 5A      $��Intern��6   �� 5B      $�� ��6   �� 5C      5D      �� 5E   
   $��Last Job (if any)��6   $
��Left=�6,   $��
75,485,0,0q�6G   $	��Top=�6[   $	��352��6s   
���   �$��29��6�   $��Segoe UI Light)�6�   �� 5F   
   -   5        $ InterestedJob��5F      $ Content��6|   $
��Left=�6�   $��476,450,0,0q�6�   $	��Top=�6�   $	��230��6�   $��29��6�   $ 0��6�   $��Segoe UI Light)�6
   6*   �� *interestedPosition+$	 PropertyChanged=�.@�5G      �� $��Select a Job field��6    . 
�������6J   �� 5H      $	��CIO��6   �� 5I      $��Director of Engineering��6   �� 5J      $��Software Engineer��6   �� 5K      $��Technical Lead��6   �� 5L      $��Solution Architecture��6   �� 5M      $��Solution Engineer��6   �� 5N      $��System Engineer��6   �� 5O      $��Sales Representative��6   �� 5P      $��General Manager��6   �� 5Q      $��Accounts Executive��6   �� 5R      $��Technical Supervisor��6   �� 5S      $��Marketing Executive��6   �� 5T      $%��Channel Sales Operation Manager��6   �� 5U      $��Other��6   5V      5W      5X      