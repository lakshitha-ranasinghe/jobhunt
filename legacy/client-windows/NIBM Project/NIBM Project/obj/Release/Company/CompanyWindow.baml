   M S B A M L   `   `   `  ���� T  PMahApps.Metro, Version=1.1.2.0, Culture=neutral, PublicKeyToken=f4fb5a3c4d1e5b4fV;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.MetroMahApps.Metro.Controls   NIBM Project    NIBM_Project.CompanyWindow   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     PControls;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.Metro       � Title$  Registration��$	��730��$	��800�� � WindowStartupLocation$ CenterScreen=�$��14(� � 
ResizeMode$ CanMinimize=� � 	Resources  �� MergedDictionaries �� Source��  �� $O Ipack://application:,,,/MahApps.Metro;component/Styles/Accents/Purple.xaml?�%    .��� -   $��CompanyWindowGrid��$��Stretch=�.+��� $$��Tell us about your Company..!!��$
��Left=�$��	50,24,0,0q�$	��Top=�$	��483��$��61��$��36(�
���   �$��Segoe UI Light)��� $��Company Details��$
��Left=�$	��248��$��
50,108,0,0q�$	��Top=�$	��699��$��18(����	#FF874CEE$��Segoe UI Light)�$��0.3,1,0.3,0.3q�
���   ����	#FF874CEE�� $��Company Name��$
��Left=�$��
70,155,0,0q�$	��Top=�$��28��$	��116��
���   �$��Segoe UI Light)��� -    ��Name$ CompanyName��*   $MahApps.Metro.Controls.TextBoxHelper   ClearTextButton .    	Watermark$ Enter Company Name��$
��Left=�$��26��$��200,155,0,0q�	 �� TextWrapping$
	 Wrap=�$	��Top=�$	��176��$��Segoe UI Light)����� *companyName+
 �� UpdateSourceTrigger$
 PropertyChanged=��� $��Company Type��$
��Left=�$��
70,190,0,0q�$	��Top=�$��28��$	��116��
���   �$��Segoe UI Light)��� -    ��Name$ CompanyTypeBox��+   %MahApps.Metro.Controls.ComboBoxHelper'    EnableVirtualizationWithGrouping .  �� SelectedValuePath$ Content��$
��Left=�$��200,190,0,0q�$	��Top=�$	��176��
���   �$��Segoe UI Light)� �� SelectedValue �� *companyType+$
 PropertyChanged=�.@��� $��Select a Type��
������� �� 
IsSelected . �� $��Private���� $��Public���� $��Subsidiaries���� $��Offshore Company���� $��Joint Venture Company���� $��	Telephone��$
��Left=�$��
70,225,0,0q�$	��Top=�$��28��$��86��
���   �$��Segoe UI Light)��� -   $ TeleOne�� . $ Telephone Number��$
��Left=�$��26��$��200,225,0,0q�$
	 Wrap=�$	��Top=�$	��176�� J� RenderTransformOrigin$ 0.475,2.885&�$��Segoe UI Light)����� *firstTelephone+$
 PropertyChanged=��� -   $ TeleTwo�� . $ Mobile Number��$
��Left=�$��26��$��200,260,0,0q�$
	 Wrap=�$	��Top=�$	��176��$ 0.475,2.885&�$��Segoe UI Light)����� *secondTelephone+$
 PropertyChanged=��� $��E-Mail��$
��Left=�$��
70,295,0,0q�$	��Top=�$��28��$��69��
���   �$��Segoe UI Light)��� -   $ EMail�� . $ E-Mail Address��$
��Left=�$��26��$��200,295,0,0q�$
	 Wrap=�$	��Top=�$	��176��$��Segoe UI Light)����� *email+$
 PropertyChanged=��� $��Address��$
��Left=�$��435,155,0,0q�$	��Top=�$��28��$��69��
���   �$��Segoe UI Light)��� -   $ AddressLine1�� . $ Address Line 1��$
��Left=�$��26��$��540,155,0,0q�$
	 Wrap=�$	��Top=�$	��176��$��Segoe UI Light)����� *addressLine1+$
 PropertyChanged=��� -   $ AddressLine2�� . $ Address Line 2��$
��Left=�$��26��$��540,190,0,0q�$
	 Wrap=�$	��Top=�$	��176��$��Segoe UI Light)����� *addressLine2+$
 PropertyChanged=�.���� -	   $ AddressLine3Box��$ Content��$
��Left=�$��540,225,0,0q�$	��Top=�$	��176��
���   �$��Segoe UI Light)� �� *addressLine3+$
 PropertyChanged=�.@��� $��Province��
������� . �� $��Central���� $��Eastern���� $��North Central���� $��Northern���� $��
Sabaragamu���� $��Southern���� $	��Uva���� $��Werstern���� $��Web Site��$
��Left=�$��435,260,0,0q�$	��Top=�$��28��$��69��
���   �$��Segoe UI Light)��� -
   $ WebSite�� . $ Web Site Address��$
��Left=�$��26��$��540,260,0,0q�$
	 Wrap=�$	��Top=�$	��176��$��Segoe UI Light)����� *	website+$
 PropertyChanged=��� $��Preferences��$
��Left=�$	��172��$��
50,378,0,0q�$	��Top=�$	��699��$��18(����	#FF874CEE$��Segoe UI Light)�$��0.3,1,0.3,0.3q�
���   ����	#FF874CEE�� $��Job types you offer��$
��Left=�$��
70,420,0,0q�$	��Top=�$��28��$	��137��
���   �$��Segoe UI Light)��� $��Description��$
��Left=�$��
70,455,0,0q�$	��Top=�$��28��$��97��
���   �$��Segoe UI Light)��� $
��Left=�$	��100��$��	50,35,0,0q�$	��Top=�$	��699��$��Segoe UI Light)�
���   ��� $
��Left=�$	��100��$��300,190,0,0q�$	��Top=�$	��196��$ 0.5,0.5&�z�V� .~��� �� ��  �� Angle$ -89.885Q�T� �� -      AccentedSquareButtonStyle#��[  $
��Back��$
��Left=�$��
87,587,0,0q�$	��Top=�$	��168��$��57��$��Segoe UI Light)�$ 0.538,-0.615&�$��16(����	#FF874CEE
���   �
���   ��� -   #��[  $��Clear��$
��Left=�$��319,587,0,0q�$	��Top=�$	��168��$��57��$��Segoe UI Light)�$ 0.538,-0.615&�$��16(����	#FF874CEE
���   �
���   ��� -   #��[  $��Submit��$
��Left=�$��560,587,0,0q�$	��Top=�$	��168��$��57��$��Segoe UI Light)�$ 0.538,-0.615&�$��16(����	#FF874CEE
���   �
���   ��� -   $ Description�� . $4 .Enter Brief Description About Jobs Your Offer ��$
��Left=�$��73��$��330,455,0,0q�$
	 Wrap=�$	��Top=�$	��386��$��Segoe UI Light)����� *description+$
 PropertyChanged=��� -   $ 
JobTypeBox��$ Content��$
��Left=�$��330,420,0,0q�$	��Top=�$	��386��$��Segoe UI Light)� �� *offeredJobTypes+$
 PropertyChanged=�.@��� $��Select a job type��
��������� $��Information Technology���� $��
Management���� $��Engineering���� $��	Education���� $��Sales and Marketing���� $��Other��