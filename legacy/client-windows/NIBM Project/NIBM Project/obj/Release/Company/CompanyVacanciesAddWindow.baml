   M S B A M L   `   `   `  ���� T  PMahApps.Metro, Version=1.1.2.0, Culture=neutral, PublicKeyToken=f4fb5a3c4d1e5b4fV;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.MetroMahApps.Metro.Controls   NIBM Project,   &NIBM_Project.CompanyVacanciesAddWindow   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     PControls;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.Metro       � Title$  Add Vacancy��$	��730��$	��800�� � WindowStartupLocation$ CenterScreen=�$��14(� � 
ResizeMode$ CanMinimize=� � 	Resources  �� MergedDictionaries �� Source��  �� $O Ipack://application:,,,/MahApps.Metro;component/Styles/Accents/Purple.xaml?�%    .��� -   $!��CompanyVacancyAddWindowGrid��$��Stretch=�.+��� $��General Details��$
��Left=�$	��286��$��
50,120,0,0q�$	��Top=�$	��700��$��Segoe UI Light)�$��0.3,1,0.3,0.3q�
���   ����	#FF874CEE$��18(����	#FF874CEE�� $��Internal Details��$
��Left=�$	��120��$��
50,420,0,0q�$	��Top=�$	��700��$��0.3,1,0.3,0.3q�$��18(����	#FF874CEE$��Segoe UI Light)�
���   ����	#FF874CEE�� $��Add Vacancy....��$
��Left=�$��	50,24,0,0q�$	��Top=�$	��350��$��61��$��36(�
���   �$��Segoe UI Light)��� $��	Job Title��$
��Left=�$��
70,170,0,0q�$	��Top=�$��28��$��75��
���   �$��Segoe UI Light)��� -    ��Name$ JobTitle��$
��Left=�$��26��$��290,170,0,0q�$	��Top=�$	��416�� J� RenderTransformOrigin$ 0.475,2.885&�$��Segoe UI Light)� �� Text �� *
jobTitle+.@��� $��Select a Job Title��
�������	 �� 
IsSelected	 . �� $	��CIO���� $��Director of Engineering���� $��Software Engineer���� $��Technical Lead���� $��Solution Architecture���� $��Solution Engineer���� $��System Engineer���� $��Sales Representative���� $��General Manager���� $��Accounts Executive���� $��Technical Supervisor���� $��Marketing Executive���� $%��Channel Sales Operation Manager���� $��Other���� $��Vacancy Description��$
��Left=�$��
70,205,0,0q�$	��Top=�$��28��$	��135��
���   �$��Segoe UI Light)��� -   
 ��Name$
 VacancyDescription��*   $MahApps.Metro.Controls.TextBoxHelper   ClearTextButton .    	Watermark$% Tell us about the job you offer��$
��Left=�$��71��$��290,205,0,0q� �� TextWrapping$
 Wrap=�$	��Top=�$	��416��$ 0.475,2.885&�$��Segoe UI Light)����� *vacancyDescription+�� $��Prerequisites��$
��Left=�$��
70,285,0,0q�$	��Top=�$��28��$	��103��
���   �$��Segoe UI Light)��� -   $
 Prerequisites�� . $ Prerequites you seeks��$
��Left=�$��71��$��290,285,0,0q�$
 Wrap=�$	��Top=�$	��416��$ 0.475,2.885&�$��Segoe UI Light)����� *prerequisites+�� $��Location / Branch��$
��Left=�$��
70,365,0,0q�$	��Top=�$��28��$	��118��
���   �$��Segoe UI Light)��� -   $ Location��$
��Left=�$��26��$��290,365,0,0q�$	��Top=�$	��416��$ 0.475,2.885&� �� SelectedIndex$ 0��$��Segoe UI Light)� �� *
location+.@��� $��Select a Location��	 . 
��������� $��Central Province���� $��Eastern Province���� $��North Central Province���� $��Northern Province���� $��
Sabaragamu���� $��Southern Province���� $��Uva Province���� $��Werstern Province���� $��Closing Date��$
��Left=�$��
70,465,0,0q�$	��Top=�$��28��$��97��
���   �$��Segoe UI Light)�(  "System.Windows.Controls.DatePicker  -     Name$ ClosingDate��$
��Left=�$��28��$��225,465,0,0q�$	��Top=�$	��142��$��Segoe UI Light)�   SelectedDate �� *closingDate+�� $��Vacant Place Count��$
��Left=�$��425,470,0,0q�$	��Top=�$��28��$	��136��
���   �$��Segoe UI Light)��� -   $
 NoOfVacancy�� . $
��Left=�$��26��$��610,470,0,0q�$
 Wrap=�$	��Top=�$��91��$ 0.475,2.885&�$��Segoe UI Light)����� *noOfVacancies+�� $��Salary (Rs)��$
��Left=�$��
70,500,0,0q�$	��Top=�$��28��$��75��
���   �$��Segoe UI Light)��� -   $
 Salary�� . $
��Left=�$��26��$��225,500,0,0q�$
 Wrap=�$	��Top=�$	��142��$ 0.475,2.885&�$��Segoe UI Light)����� *salary+�� -	      AccentedSquareButtonStyle#��[  $
��Back��$
��Left=�$��
90,590,0,0q�$	��Top=�$	��168��$��57��$��Segoe UI Light)�$ 0.538,-0.615&�$��16(����	#FF874CEE
���   �
���   ��� -
   #��[  $��Clear��$
��Left=�$��310,590,0,0q�$	��Top=�$	��168��$��57��$��Segoe UI Light)�$ 0.538,-0.615&�$��16(����	#FF874CEE
���   �
���   ��� -   #��[  $��Submit��$
��Left=�$��535,590,0,0q�$	��Top=�$	��168��$��57��$��Segoe UI Light)�$ 0.538,-0.615&�$��16(����	#FF874CEE
���   �
���   ��� $
��Left=�$	��100��$��365,445,0,0q�$	��Top=�$��60��$ 0.5,0.5&�z�V� .~��� �� ��  �� Angle$ -89.885Q�T� �� $
��Left=�$	��100��$��	50,38,0,0q�$	��Top=�$	��700��$��Segoe UI Light)�
���   �