   M S B A M L   `   `   `  ���� T  PMahApps.Metro, Version=1.1.2.0, Culture=neutral, PublicKeyToken=f4fb5a3c4d1e5b4fV;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.MetroMahApps.Metro.Controls   NIBM Project"   NIBM_Project.UpdateVacancies   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     PControls;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.Metro       � Title$  Availble Vacancies��$	��730��$	��800�� � WindowStartupLocation$ CenterScreen=�$��14(� � 
ResizeMode$ CanMinimize=� � 	Resources  �� MergedDictionaries �� Source��  �� $O Ipack://application:,,,/MahApps.Metro;component/Styles/Accents/Purple.xaml?�%    .��� .+��� $��Update Vacancies....��$
��Left=�$��	50,24,0,0q�$	��Top=�$	��350��$��61��$��36(�
���   �$��Segoe UI Light)��� $
��Left=�$	��100��$��	50,41,0,0q�$	��Top=�$	��700��$��Segoe UI Light)�
���   ���    AccentedSquareButtonStyle#��[  $
��Back��$
��Left=�$��177,596,0,0q�$	��Top=�$	��168��$��57��$��Segoe UI Light)� J� RenderTransformOrigin$ 0.538,-0.615&�$��16(����	#FF874CEE
���   �
���   ��� #��[  $��Update��$
��Left=�$��481,596,0,0q�$	��Top=�$	��168��$��57��$��Segoe UI Light)�$ 0.538,-0.615&�$��16(����	#FF874CEE
���   �
���   �&   System.Windows.Controls.DataGrid  -     Name$ dataGrid��$
��Left=�$��
50,130,0,0q�$	��Top=�$	��700��$��30��   CanUserResizeRows .  	   AutoGenerateColumns	 .  ���� 
 �� Path$
 LoadDataBinding�   Columns 0  *System.Windows.Controls.DataGridTextColumn  ,  &System.Windows.Controls.DataGridColumn   Header$ ID��   Width5  /System.Windows.Controls.DataGridLengthConverter$ 50    
IsReadOnly . 1  +System.Windows.Controls.DataGridBoundColumn   Binding �� $
 
vacancy_Id�  $ 	Job Title��$	 200  .  �� $
 	job_title�  $ Location��$	 150  .  �� $
 location�  $ Salary��$	 150  .  �� $
 salary�  $ Closing Date��$	 150  .  �� $
 closing_date��� $��Update Vacancy Details��$
��Left=�$	��190��$��
50,381,0,0q�$	��Top=�$	��700��$��0.3,1,0.3,0.3q�$��18(�
����������	#FF874CEE���	#FF874CEE�� $��Location / Branch��$
��Left=�$��100,429,0,0q�$	��Top=�$��28��$	��118��
���   �$��Segoe UI Light)��� -    ��Name$ Location��$
��Left=�$��26��$��260,429,0,0q�$	��Top=�$	��416��$ 0.475,2.885&� �� SelectedIndex$ 0��$��Segoe UI Light)� �� Text �� *
location+.@��� $��Select a Location�� �� 
IsSelected . 
��������� $��Central Province���� $��Eastern Province���� $��North Central Province���� $��Northern Province���� $��
Sabaragamu���� $��Southern Province���� $��Uva Province���� $��Werstern Province���� $��Closing Date��$
��Left=�$��100,470,0,0q�$	��Top=�$��28��$��97��
���   �$��Segoe UI Light)�(  "System.Windows.Controls.DatePicker  -     Name$ ClosingDate��$
��Left=�$��28��$��260,470,0,0q�$	��Top=�$	��142��$��Segoe UI Light)�   SelectedDate �� *closingDate+�� $��Salary (Rs)��$
��Left=�$��100,516,0,0q�$	��Top=�$��28��$��75��
���   �$��Segoe UI Light)��� -    ��Name$ Salary��*   $MahApps.Metro.Controls.TextBoxHelper   ClearTextButton . $
��Left=�$��26��$��260,516,0,0q� �� TextWrapping$
 Wrap=�$	��Top=�$	��142��$ 0.475,2.885&�$��Segoe UI Light)����� *salary+