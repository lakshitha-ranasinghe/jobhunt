   M S B A M L   `   `   `  ���� T  PMahApps.Metro, Version=1.1.2.0, Culture=neutral, PublicKeyToken=f4fb5a3c4d1e5b4fV;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.MetroMahApps.Metro.Controls   NIBM Project$   NIBM_Project.JobRecommendation   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     PControls;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.Metro       � Title$  JobRecommendation��$	��730��$	��900�� � WindowStartupLocation$ CenterScreen=� � 
ResizeMode$ CanMinimize=�.��� .+��� $��What we have for you..��$
��Left=�$��	30,24,0,0q�$	��Top=�$	��392��$��61��$��36(�
���   �$��Segoe UI Light)���Z� �� $��Job List��$
��Left=�$	��335��$��
30,210,0,0q�$	��Top=�$	��411��$��0.3,1,0.3,0.3q�$��18(����	#FF49B4E8���	#FF49B4E8$��Segoe UI Light)��� $��Job Details��$
��Left=�$	��335��$��460,210,0,0q�$	��Top=�$	��402��$��0.3,1,0.3,0.3q�$��18(����	#FF49B4E8���	#FF49B4E8$��Segoe UI Light)��� $
��Left=�$	��100��$��	30,35,0,0q�$	��Top=�$	��800��$��Segoe UI Light)�
���   �
���   ��� -   $��JobType��$
��Left=� �� SelectedValuePath$ Content��$��100,125,0,0q�$	��Top=�$	��148��$��28�� �� SelectedIndex$ 0��{�.  $��Segoe UI Light)���Z� .@��� $	��CIO���� $��Director of Engineering���� $��Software Engineer���� $��Technical Lead���� $��Solution Architecture���� $��Solution Engineer���� $��System Engineer���� $��Sales Representative���� $��General Manager���� $��Accounts Executive���� $��Technical Supervisor���� $��Marketing Executive���� $%��Channel Sales Operation Manager���� $��Not Specified�� �� 
IsSelected . �� $��Job Type��$
��Left=�$��
40,125,0,0q�$	��Top=�$��28��$��57��
���   �$��Segoe UI Light)��� $��Expected Salary��$
��Left=�$��280,125,0,0q�$	��Top=�$��28��$��95�����	#FF151515$��Segoe UI Light)��� -   $��ExpectedSalary��$
��Left=�$ Content��$��380,125,0,0q�$	��Top=�$	��148��$��28��$ 0��{�.  $��Segoe UI Light)���Z� .@��� $��10000 - 30000���� $��30000 - 60000���� $��60000 - 100000���� $��100000 - 150000���� $��More than 150000���� $��Not Specified�� . �� $��Prefered Area��$
��Left=�$��555,125,0,0q�$	��Top=�$��28��$��95�����	#FF151515$��Segoe UI Light)��� -   $��PreferedArea��$
��Left=�$ Content��$��640,125,0,0q�$	��Top=�$	��148��$��28��$ 0��{�.  $��Segoe UI Light)���Z� .@��� $��Western Province���� $��Eastern Province���� $��Uva Province���� $��Central Province���� $��Southern Province���� $��Northen Province���� $��North Western Province���� $��North Central Province���� $��Sabaragamu Province���� $��Not Specified�� . �� -    ��Name$ JobList��$
��Left=�$	��240��$��
45,295,0,0q�$	��Top=�$	��377��
���   �
�������$��14(�$��Segoe UI Light)���Z� 	� -    	�Name$ AutoRB��$��Automatic Data Filter��$
��Left=�$��
531,56,0,0q�$	��Top=� J� RenderTransformOrigin$ 0.188,-0.733&�
���   �	 	� 	GroupName$
	 Type��
 d� 	IsChecked$

 TrueY�$��Segoe UI Light)���Z� 	� -   $ ManualRB��$��Manual Data Filter��$
��Left=�$��
715,56,0,0q�$	��Top=�$ 0.188,-0.733&�
���   �$
	 Type��$��Segoe UI Light)���Z� �� -      AccentedSquareButtonStyle#��[  $
��Left=�$��818,110,0,0q�$	��Top=�$��50��$��50������  �� ImageSource$: 4/NIBM Project;component/Icons/appbar.page.search.png�� j� TileMode$
 Tile=� j� Stretch$ UniformToFill=��� $��	Job Title��$
��Left=�$��470,255,0,0q�$	��Top=�$��25��$��75��
���   �$��12(�$��Segoe UI Light)��� -    ��Name$ JobTitle��$
��Left=�$��Center=�$��25��$��620,255,0,0q� �� TextWrapping$
 Wrap=�$	��Top=�$	��230��$ 0.475,2.885&�{�.  
���   �$��14(�$��0q�$
��Bold"�$��Segoe UI Light)����� *
jobTitle+��Z� �� $��Vacancy Description��$
��Left=�$��470,285,0,0q�$	��Top=�$��25��$	��135��
���   �$��Segoe UI Light)��� -	   $ VacancyDescription��$
��Left=�$	��Top=�$��50��$��620,285,0,0q�$
 Wrap=�$	��Top=�$	��230��$ 0.475,2.885&�{�.  
���   �$��0q�$��14(�$��Segoe UI Light)����� *vacancyDescription+��Z� �� $��Prerequisites��$
��Left=�$��470,340,0,0q�$	��Top=�$��25��$	��103��
���   �$��Segoe UI Light)��� -
   $ Prerequisites��$
��Left=�$	��Top=�$��60��$��620,340,0,0q�$
 Wrap=�$	��Top=�$	��230��$ 0.475,2.885&�{�.  
���   �$��14(�$��0q�$��Segoe UI Light)����� *prerequisites+��Z� �� $��Location / Branch��$
��Left=�$��470,405,0,0q�$	��Top=�$��25��$	��118��
���   �$��Segoe UI Light)��� -   $ ClosingDate��$��Center=�$
��Left=�$��26��$��620,435,0,0q�$
 Wrap=�$	��Top=�$	��230��$ 0.475,2.885&�{�.  
���   �$��0q�$��14(�$��Segoe UI Light)����� *closingDate+��Z� �� $��Closing Date��$
��Left=�$��470,435,0,0q�$	��Top=�$��25��$��97��
���   �$��Segoe UI Light)��� -   $ Location��$��Center=�$
��Left=�$��25��$��620,405,0,0q�$
 Wrap=�$	��Top=�$	��230��$ 0.475,2.885&�{�.  
���   �$��0q�$��14(�$��Segoe UI Light)����� *
location+��Z� �� $��Vacant Place Count��$
��Left=�$��470,495,0,0q�$	��Top=�$��25��$	��136��
���   �$��Segoe UI Light)��� -   $ NoOfVacancy��$��Center=�$
��Left=�$��25��$��620,495,0,0q�$
 Wrap=�$	��Top=�$	��229��$ 0.475,2.885&�{�.  
���   �$��0q�$��14(�$��Segoe UI Light)����� *noOfVacancies+��Z� �� $��Salary (Rs)��$
��Left=�$��470,465,0,0q�$	��Top=�$��25��$��75��
���   �$��Segoe UI Light)��� -   $ Salary��$��Center=�$
��Left=�$��25��$��620,465,0,0q�$
 Wrap=�$	��Top=�$	��230��$ 0.475,2.885&�{�.  
���   �$��14(�$��0q�$��Segoe UI Light)����� *salary+��Z� �� -    ��Name$
 Sort��$
��Left=�$ Content��$��100,255,0,0q�$	��Top=�$	��148��$��28��$��Segoe UI Light)�.@��� $��Default�� . �� $��By Closing Date���� $��By Salary Low > High���� $��By Location A-Z���� $��Sort By��$
��Left=�$��
45,255,0,0q�$	��Top=�$��28��$��51��
���   �$��Segoe UI Light)��� $
��Left=�$	��100��$��
30,130,0,0q�$	��Top=�$	��800��$��Segoe UI Light)�
���   �