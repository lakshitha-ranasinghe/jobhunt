   M S B A M L   `   `   `  ����T  PMahApps.Metro, Version=1.1.2.0, Culture=neutral, PublicKeyToken=f4fb5a3c4d1e5b4fV;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.MetroMahApps.Metro.Controls  5   	    NIBM Project    NIBM_Project.CompanyWindow   5      \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   PControls;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.Metro     5   	     � Title$  Registration��5   	   $	��730��6   $	��800��6+    � WindowStartupLocation$ CenterScreen=�67   $��14(�6\    � 
ResizeMode$ CanMinimize=�6j    � 	Resources 5       �� MergedDictionaries �� Source�� 5   
    5      �� 5	      $O Ipack://application:,,,/MahApps.Metro;component/Styles/Accents/Purple.xaml?�6%   %    5      .��5      � -   5        $��CompanyWindowGrid��5      $��Stretch=�6$   .+�5   
   �� $$��Tell us about your Company..!!��6   $
��Left=�69   $��	50,24,0,0q�6T   $	��Top=�6g   $	��483��6   $��61��6�   $��36(�6�   
���   �$��Segoe UI Light)�6�   �� 5   
   $��Company Details��6   $
��Left=�6,   $	��248��6G   $��
50,108,0,0q�6T   $	��Top=�6h   $	��699��6�   $��18(�6�   ���	#FF874CEE$��Segoe UI Light)�6�   $��0.3,1,0.3,0.3q�6�   
���   ����	#FF874CEE6  �� 5   
   $��Company Name��6   $
��Left=�6'   $��
70,155,0,0q�6B   $	��Top=�6V   $��28��6n   $	��116��6z   
���   �$��Segoe UI Light)�6�   �� 5   
   -   5         ��Name$ CompanyName��5      *   $MahApps.Metro.Controls.TextBoxHelper   ClearTextButton .    	Watermark$ Enter Company Name��6�   $
��Left=�6�   $��26��6�   $��200,155,0,0q�6�   	 �� TextWrapping$
	 Wrap=�6	  $	��Top=�6  $	��176��65  $��Segoe UI Light)�6A  ��6'   �� *companyName+
 �� UpdateSourceTrigger$
 PropertyChanged=�6A  �� 5   
   $��Company Type��6   $
��Left=�6'   $��
70,190,0,0q�6B   $	��Top=�6V   $��28��6n   $	��116��6z   
���   �$��Segoe UI Light)�6�   �� 5   
   -   5         ��Name$ CompanyTypeBox��5      +   %MahApps.Metro.Controls.ComboBoxHelper'    EnableVirtualizationWithGrouping .  �� SelectedValuePath$ Content��6�   $
��Left=�6�   $��200,190,0,0q�6�   $	��Top=�6  $	��176��6  
���   �$��Segoe UI Light)�69   �� SelectedValue 6+   �� *companyType+$
 PropertyChanged=�.@�5      �� $��Select a Type��6   
������� �� 
IsSelected . 6I   �� 5      $��Private��6   �� 5      $��Public��6   �� 5      $��Subsidiaries��6   �� 5      $��Offshore Company��6   �� 5      $��Joint Venture Company��6   5      �� 5   
   $��	Telephone��6   $
��Left=�6$   $��
70,225,0,0q�6?   $	��Top=�6S   $��28��6k   $��86��6w   
���   �$��Segoe UI Light)�6�   �� 5   
   -   5        $ TeleOne��5       . $ Telephone Number��6�   $
��Left=�6�   $��26��6�   $��200,225,0,0q�6�   $
	 Wrap=�6  $	��Top=�6  $	��176��62   J� RenderTransformOrigin$ 0.475,2.885&�6>  $��Segoe UI Light)�6b  ��6#   �� *firstTelephone+$
 PropertyChanged=�6b  �� 5   
   -   5        $ TeleTwo��5       . $ Mobile Number��6�   $
��Left=�6�   $��26��6�   $��200,260,0,0q�6�   $
	 Wrap=�6  $	��Top=�6  $	��176��60  $ 0.475,2.885&�6<  $��Segoe UI Light)�6`  ��6#   �� *secondTelephone+$
 PropertyChanged=�6`  �� 5   
   $��E-Mail��6   $
��Left=�6!   $��
70,295,0,0q�6<   $	��Top=�6P   $��28��6h   $��69��6t   
���   �$��Segoe UI Light)�6�   �� 5    
   -   5        $ EMail��5        . $ E-Mail Address��6�   $
��Left=�6�   $��26��6�   $��200,295,0,0q�6�   $
	 Wrap=�6�   $	��Top=�6  $	��176��6%  $��Segoe UI Light)�61  ��6!   �� *email+$
 PropertyChanged=�61  �� 5!   
   $��Address��6   $
��Left=�6"   $��435,155,0,0q�6=   $	��Top=�6R   $��28��6j   $��69��6v   
���   �$��Segoe UI Light)�6�   �� 5"   
   -   5        $ AddressLine1��5"   )   . $ Address Line 1��6�   $
��Left=�6�   $��26��6�   $��540,155,0,0q�6�   $
	 Wrap=�6�   $	��Top=�6  $	��176��6  $��Segoe UI Light)�6?  ��6   �� *addressLine1+$
 PropertyChanged=�6?  �� 5#   
   -   5        $ AddressLine2��5#       . $ Address Line 2��6�   $
��Left=�6�   $��26��6�   $��540,190,0,0q�6�   $
	 Wrap=�6  $	��Top=�6  $	��176��63  $��Segoe UI Light)�6?  ��6(   �� *addressLine2+$
 PropertyChanged=�.��6\  5$      �� 5%   
   -	   5        $ AddressLine3Box��5%      $ Content��6y   $
��Left=�6�   $��540,225,0,0q�6�   $	��Top=�6�   $	��176��6�   
���   �$��Segoe UI Light)�6�    6-   �� *addressLine3+$
 PropertyChanged=�.@�5&      �� $��Province��6   
������� . 6D   �� 5'      $��Central��6   �� 5(      $��Eastern��6   �� 5)      $��North Central��6   �� 5*      $��Northern��6   �� 5+      $��
Sabaragamu��6   �� 5,      $��Southern��6   �� 5-      $	��Uva��6   �� 5.      $��Werstern��6   5/      �� 50   
   $��Web Site��6   $
��Left=�6#   $��435,260,0,0q�6>   $	��Top=�6S   $��28��6k   $��69��6w   
���   �$��Segoe UI Light)�6�   �� 51   
   -
   5        $ WebSite��51       . $ Web Site Address��6�   $
��Left=�6�   $��26��6�   $��540,260,0,0q�6�   $
	 Wrap=�6�   $	��Top=�6  $	��176��6+  $��Segoe UI Light)�67  ��6#   �� *	website+$
 PropertyChanged=�67  �� 53   
   $��Preferences��6   $
��Left=�6(   $	��172��6C   $��
50,378,0,0q�6P   $	��Top=�6d   $	��699��6|   $��18(�6�   ���	#FF874CEE$��Segoe UI Light)�6�   $��0.3,1,0.3,0.3q�6�   
���   ����	#FF874CEE6�   �� 54   
   $��Job types you offer��6   $
��Left=�6.   $��
70,420,0,0q�6I   $	��Top=�6]   $��28��6u   $	��137��6�   
���   �$��Segoe UI Light)�6�   �� 55   
   $��Description��6   $
��Left=�6&   $��
70,455,0,0q�6A   $	��Top=�6U   $��28��6m   $��97��6y   
���   �$��Segoe UI Light)�6�   �� 56   
   $
��Left=�6   $	��100��6/   $��	50,35,0,0q�6<   $	��Top=�6O   $	��699��6g   $��Segoe UI Light)�6s   
���   �6�   �� 57   
   $
��Left=�6   $	��100��6/   $��300,190,0,0q�6<   $	��Top=�6Q   $	��196��6i   $ 0.5,0.5&�6u   z�58      V� 59      .~�5:      �� �� 5;      �� 5<       �� Angle$ -89.885Q�6&   T� 5=      5>      5@      �� 5B   
   -   5           AccentedSquareButtonStyle#��[  5B      $
��Back��6E   $
��Left=�6T   $��
87,587,0,0q�6o   $	��Top=�6�   $	��168��6�   $��57��6�   $��Segoe UI Light)�6�   $ 0.538,-0.615&�6�   $��16(�6�   ���	#FF874CEE
���   �
���   �6@  �� 5C   
   -   5        #��[  5C      $��Clear��6E   $
��Left=�6U   $��319,587,0,0q�6p   $	��Top=�6�   $	��168��6�   $��57��6�   $��Segoe UI Light)�6�   $ 0.538,-0.615&�6�   $��16(�6�   ���	#FF874CEE
���   �
���   �6B  �� 5D   
   -   5        #��[  5D      $��Submit��6E   $
��Left=�6V   $��560,587,0,0q�6q   $	��Top=�6�   $	��168��6�   $��57��6�   $��Segoe UI Light)�6�   $ 0.538,-0.615&�6�   $��16(�6�   ���	#FF874CEE
���   �
���   �6C  �� 5E   
   -   5        $ Description��5E       . $4 .Enter Brief Description About Jobs Your Offer ��6�   $
��Left=�6�   $��73��6  $��330,455,0,0q�6  $
	 Wrap=�6%  $	��Top=�69  $	��386��6Q  $��Segoe UI Light)�6]  ��6'   �� *description+$
 PropertyChanged=�6]  �� 5F   
   -   5        $ 
JobTypeBox��5F      $ Content��6v   $
��Left=�6�   $��330,420,0,0q�6�   $	��Top=�6�   $	��386��6�   $��Segoe UI Light)�6�    6'   �� *offeredJobTypes+$
 PropertyChanged=�.@�5G      �� $��Select a job type��6   
�������67   �� 5H      $��Information Technology��6   �� 5I      $��
Management��6   �� 5J      $��Engineering��6   �� 5K      $��	Education��6   �� 5L      $��Sales and Marketing��6   �� 5M      $��Other��6   5N      5O      5P      