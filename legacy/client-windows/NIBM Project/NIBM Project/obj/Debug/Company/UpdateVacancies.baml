   M S B A M L   `   `   `  ����T  PMahApps.Metro, Version=1.1.2.0, Culture=neutral, PublicKeyToken=f4fb5a3c4d1e5b4fV;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.MetroMahApps.Metro.Controls  5   	    NIBM Project"   NIBM_Project.UpdateVacancies   5      \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   PControls;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.Metro     5   	     � Title$  Availble Vacancies��5   	   $	��730��6$   $	��800��61    � WindowStartupLocation$ CenterScreen=�6=   $��14(�6b    � 
ResizeMode$ CanMinimize=�6p    � 	Resources 5       �� MergedDictionaries �� Source�� 5   
    5      �� 5	      $O Ipack://application:,,,/MahApps.Metro;component/Styles/Accents/Purple.xaml?�6%   %    5      .��5      � .+�5   
   �� $��Update Vacancies....��6   $
��Left=�6/   $��	50,24,0,0q�6J   $	��Top=�6]   $	��350��6u   $��61��6�   $��36(�6�   
���   �$��Segoe UI Light)�6�   �� 5   
   $
��Left=�6   $	��100��6/   $��	50,41,0,0q�6<   $	��Top=�6O   $	��700��6g   $��Segoe UI Light)�6s   
���   �6�   �� 5   
      AccentedSquareButtonStyle#��[  6   $
��Back��6D   $
��Left=�6S   $��177,596,0,0q�6n   $	��Top=�6�   $	��168��6�   $��57��6�   $��Segoe UI Light)�6�    J� RenderTransformOrigin$ 0.538,-0.615&�6�   $��16(�6�   ���	#FF874CEE
���   �
���   �6-  �� 5   
   -   5        #��[  5      $��Update��6D   $
��Left=�6U   $��481,596,0,0q�6p   $	��Top=�6�   $	��168��6�   $��57��6�   $��Segoe UI Light)�6�   $ 0.538,-0.615&�6�   $��16(�6�   ���	#FF874CEE
���   �
���   �6B  &   System.Windows.Controls.DataGrid  5   
   -   5          Name$ dataGrid��5      $
��Left=�6%   $��
61,122,0,0q�6@   $	��Top=�6T   $	��700��6l   $	��254��6x      CanUserResizeRows .  	   AutoGenerateColumns	 .  ��5      �� 
 �� Path$
 LoadDataBinding�   Columns 5      0  *System.Windows.Controls.DataGridTextColumn  5      ,  &System.Windows.Controls.DataGridColumn   Header$ ID��6I      Width5  /System.Windows.Controls.DataGridLengthConverter$ 50 6U      
IsReadOnly . 1  +System.Windows.Controls.DataGridBoundColumn   Binding 6%   �� $
 
vacancy_Id�6`     5      $ 	Job Title��6H   $	 200 6[    .  6%   �� $
 	job_title�6g     5      $ Location��6G   $	 150 6Y    .  6%   �� $
 location�6e     5      $ Salary��6E   $	 150 6U    .  6%   �� $
 salary�6a     5      $ Closing Date��6K   $	 150 6a    .  6%   �� $
 closing_date�6m   5       �� 5"   
   $��Update Vacancy Details��6   $
��Left=�63   $	��190��6N   $��
50,381,0,0q�6[   $	��Top=�6p   $	��700��6�   $��0.3,1,0.3,0.3q�6�   $��18(�6�   
����������	#FF874CEE���	#FF874CEE6�   �� 5#   
   $��Location / Branch��6   $
��Left=�6,   $��100,429,0,0q�6G   $	��Top=�6\   $��28��6t   $	��118��6�   
���   �$��Segoe UI Light)�6�   �� 5$   
   -   5         ��Name$ Location��5$      $
��Left=�6G    �� SelectedValuePath$ Content��6b   $��26��6~   $��260,429,0,0q�6�   $	��Top=�6�   $	��416��6�   $ 0.475,2.885&�6�    �� SelectedIndex$ 0��6�   $��Segoe UI Light)�6�    �� Text 6%   �� *updatedLocation+.@�5%      �� $��Select a Location��6    �� 
IsSelected . 
�������6I   �� 5&      $��Central Province��6   �� 5'      $��Eastern Province��6   �� 5(      $��North Central Province��6   �� 5)      $��Northern Province��6   �� 5*      $��
Sabaragamu��6   �� 5+      $��Southern Province��6   �� 5,      $��Uva Province��6   �� 5-      $��Werstern Province��6   5.      �� 5/   
   $��Closing Date��6   $
��Left=�6'   $��100,470,0,0q�6B   $	��Top=�6W   $��28��6o   $��97��6{   
���   �$��Segoe UI Light)�6�   (  "System.Windows.Controls.DatePicker  50   
   -   5          Name$ ClosingDate��50      $
��Left=�6V   $��28��6q   $��260,470,0,0q�6}   $	��Top=�6�   $	��142��6�   $��Segoe UI Light)�6�      SelectedDate 6*   �� *updatedClosingDate+6�   �� 51   
   $��Salary (Rs)��6   $
��Left=�6&   $��100,516,0,0q�6A   $	��Top=�6V   $��28��6n   $��75��6z   
���   �$��Segoe UI Light)�6�   �� 52   
   -   5         ��Name$ Salary��52      *   $MahApps.Metro.Controls.TextBoxHelper   ClearTextButton . $
��Left=�6p   $��26��6�   $��260,516,0,0q�6�    �� TextWrapping$
 Wrap=�6�   $	��Top=�6�   $	��142��6�   $ 0.475,2.885&�6�   $��Segoe UI Light)�6  ��6"   �� *updatedSalary+6  54      55      