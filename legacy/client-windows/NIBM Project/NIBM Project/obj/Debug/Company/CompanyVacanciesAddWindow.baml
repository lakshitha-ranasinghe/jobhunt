   M S B A M L   `   `   `  ����T  PMahApps.Metro, Version=1.1.2.0, Culture=neutral, PublicKeyToken=f4fb5a3c4d1e5b4fV;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.MetroMahApps.Metro.Controls  5   	    NIBM Project,   &NIBM_Project.CompanyVacanciesAddWindow   5      \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   PControls;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.Metro     5   	     � Title$  Add Vacancy��5   	   $	��730��6   $	��800��6*    � WindowStartupLocation$ CenterScreen=�66   $��14(�6[    � 
ResizeMode$ CanMinimize=�6i    � 	Resources 5       �� MergedDictionaries �� Source�� 5   
    5      �� 5	      $O Ipack://application:,,,/MahApps.Metro;component/Styles/Accents/Purple.xaml?�6%   %    5      .��5      � -   5        $!��CompanyVacancyAddWindowGrid��5      $��Stretch=�6.   .+�5   
   �� $��General Details��6   $
��Left=�6,   $	��286��6G   $��
50,120,0,0q�6T   $	��Top=�6i   $	��700��6�   $��Segoe UI Light)�6�   $��0.3,1,0.3,0.3q�6�   
���   ����	#FF874CEE$��18(�6�   ���	#FF874CEE6  �� 5   
   $��Internal Details��6   $
��Left=�6-   $	��120��6H   $��
50,420,0,0q�6U   $	��Top=�6i   $	��700��6�   $��0.3,1,0.3,0.3q�6�   $��18(�6�   ���	#FF874CEE$��Segoe UI Light)�6�   
���   ����	#FF874CEE6  �� 5   
   $��Add Vacancy....��6   $
��Left=�6*   $��	50,24,0,0q�6E   $	��Top=�6X   $	��350��6p   $��61��6|   $��36(�6�   
���   �$��Segoe UI Light)�6�   �� 5   
   $��	Job Title��6   $
��Left=�6$   $��
70,170,0,0q�6?   $	��Top=�6S   $��28��6k   $��75��6w   
���   �$��Segoe UI Light)�6�   �� 5   
   -   5         ��Name$ JobTitle��5      $
��Left=�6@   $��26��6[   $��290,170,0,0q�6g   $	��Top=�6|   $	��416��6�    J� RenderTransformOrigin$ 0.475,2.885&�6�   $��Segoe UI Light)�6�    �� Text 6%   �� *
jobTitle+.@�5      �� $��Select a Job Title��6   
�������	 �� 
IsSelected	 . 6N   �� 5      $	��CIO��6   �� 5      $��Director of Engineering��6   �� 5      $��Software Engineer��6   �� 5      $��Technical Lead��6   �� 5      $��Solution Architecture��6   �� 5      $��Solution Engineer��6   �� 5      $��System Engineer��6   �� 5      $��Sales Representative��6   �� 5      $��General Manager��6   �� 5      $��Accounts Executive��6   �� 5       $��Technical Supervisor��6   �� 5!      $��Marketing Executive��6   �� 5"      $%��Channel Sales Operation Manager��6   �� 5#      $��Other��6   5$      �� 5%   
   $��Vacancy Description��6   $
��Left=�6.   $��
70,205,0,0q�6I   $	��Top=�6]   $��28��6u   $	��135��6�   
���   �$��Segoe UI Light)�6�   �� 5&   
   -   5        
 ��Name$
 VacancyDescription��5&      *   $MahApps.Metro.Controls.TextBoxHelper   ClearTextButton .    	Watermark$% Tell us about the job you offer��6]   $
��Left=�6�   $��71��6�   $��290,205,0,0q�6�    �� TextWrapping$
 Wrap=�6  $	��Top=�6  $	��416��6-  $ 0.475,2.885&�69  $��Segoe UI Light)�6]  ��6�   �� *vacancyDescription+6]  �� 5'   
   $��Prerequisites��6   $
��Left=�6(   $��
70,285,0,0q�6C   $	��Top=�6W   $��28��6o   $	��103��6{   
���   �$��Segoe UI Light)�6�   �� 5(   
   -   5        $
 Prerequisites��5(       . $ Prerequites you seeks��6x   $
��Left=�6�   $��71��6�   $��290,285,0,0q�6�   $
 Wrap=�6�   $	��Top=�6  $	��416��6  $ 0.475,2.885&�6&  $��Segoe UI Light)�6J  ��6)   �� *prerequisites+6J  �� 5)   
   $��Location / Branch��6   $
��Left=�6,   $��
70,365,0,0q�6G   $	��Top=�6[   $��28��6s   $	��118��6   
���   �$��Segoe UI Light)�6�   �� 5*   
   -   5        $ Location��5*      $
��Left=�6@   $��26��6[   $��290,365,0,0q�6g   $	��Top=�6|   $	��416��6�   $ 0.475,2.885&�6�    �� SelectedIndex$ 0��6�   $��Segoe UI Light)�6�    6%   �� *
location+.@�5+      �� $��Select a Location��6   	 . 
�������6I   �� 5,      $��Central Province��6   �� 5-      $��Eastern Province��6   �� 5.      $��North Central Province��6   �� 5/      $��Northern Province��6   �� 50      $��
Sabaragamu��6   �� 51      $��Southern Province��6   �� 52      $��Uva Province��6   �� 53      $��Werstern Province��6   54      �� 55   
   $��Closing Date��6   $
��Left=�6'   $��
70,465,0,0q�6B   $	��Top=�6V   $��28��6n   $��97��6z   
���   �$��Segoe UI Light)�6�   (  "System.Windows.Controls.DatePicker  56   
   -   5          Name$ ClosingDate��56      $
��Left=�6P   $��28��6k   $��225,465,0,0q�6w   $	��Top=�6�   $	��142��6�   $��Segoe UI Light)�6�      SelectedDate 6+   �� *closingDate+6�   �� 57   
   $��Vacant Place Count��6   $
��Left=�6-   $��425,470,0,0q�6H   $	��Top=�6]   $��28��6u   $	��136��6�   
���   �$��Segoe UI Light)�6�   �� 58   
   -   5        $
 NoOfVacancy��58       . $
��Left=�6v   $��26��6�   $��610,470,0,0q�6�   $
 Wrap=�6�   $	��Top=�6�   $��91��6�   $ 0.475,2.885&�6�   $��Segoe UI Light)�6  ��6'   �� *noOfVacancies+6  �� 59   
   $��Salary (Rs)��6   $
��Left=�6&   $��
70,500,0,0q�6A   $	��Top=�6U   $��28��6m   $��75��6y   
���   �$��Segoe UI Light)�6�   �� 5:   
   -   5        $
 Salary��5:       . $
��Left=�6j   $��26��6�   $��225,500,0,0q�6�   $
 Wrap=�6�   $	��Top=�6�   $	��142��6�   $ 0.475,2.885&�6�   $��Segoe UI Light)�6  ��6"   �� *salary+6  �� 5;   
   -	   5           AccentedSquareButtonStyle#��[  5;      $
��Back��6D   $
��Left=�6S   $��
90,590,0,0q�6n   $	��Top=�6�   $	��168��6�   $��57��6�   $��Segoe UI Light)�6�   $ 0.538,-0.615&�6�   $��16(�6�   ���	#FF874CEE
���   �
���   �6?  �� 5<   
   -
   5        #��[  5<      $��Clear��6D   $
��Left=�6T   $��310,590,0,0q�6o   $	��Top=�6�   $	��168��6�   $��57��6�   $��Segoe UI Light)�6�   $ 0.538,-0.615&�6�   $��16(�6�   ���	#FF874CEE
���   �
���   �6A  �� 5=   
   -   5        #��[  5=      $��Submit��6D   $
��Left=�6U   $��535,590,0,0q�6p   $	��Top=�6�   $	��168��6�   $��57��6�   $��Segoe UI Light)�6�   $ 0.538,-0.615&�6�   $��16(�6�   ���	#FF874CEE
���   �
���   �6B  �� 5>   
   $
��Left=�6   $	��100��6/   $��365,445,0,0q�6<   $	��Top=�6Q   $��60��6i   $ 0.5,0.5&�6t   z�5?      V� 5@      .~�5A      �� �� 5B      �� 5C       �� Angle$ -89.885Q�6&   T� 5D      5E      5G      �� 5H   
   $
��Left=�6   $	��100��6/   $��	50,38,0,0q�6<   $	��Top=�6O   $	��700��6g   $��Segoe UI Light)�6s   
���   �6�   5I      5J      