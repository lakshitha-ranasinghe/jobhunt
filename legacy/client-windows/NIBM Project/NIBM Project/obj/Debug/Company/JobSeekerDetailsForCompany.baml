   M S B A M L   `   `   `  ����T  PMahApps.Metro, Version=1.1.2.0, Culture=neutral, PublicKeyToken=f4fb5a3c4d1e5b4fV;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.MetroMahApps.Metro.Controls  5   	    NIBM Project-   'NIBM_Project.JobSeekerDetailsForCompany   5      \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   PControls;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.Metro     5   	     � Title$  Job Seekers��5   	   $	��730��6   $	��970��6*    � WindowStartupLocation$ CenterScreen=�66    � 
ResizeMode$ CanMinimize=�6[    � 	Resources 5       �� MergedDictionaries �� Source�� 5   
    5      �� 5	      $O Ipack://application:,,,/MahApps.Metro;component/Styles/Accents/Purple.xaml?�6%   %    5      .��5      � .+�5   
   �� $��Job Seeker Details  ��6   $
��Left=�61   $	��314��6L   $��497,262,0,0q�6Y   $	��Top=�6o   $	��445��6�   $��0.3,1,0.3,0.3q�6�   $��18(�6�   
����������	#FF874CEE���	#FF874CEE6�   �� 5   
   $��Job Seekers ��6   $
��Left=�6)   $	��397��6D   $��
30,223,0,0q�6Q   $	��Top=�6f   $	��445��6~   $��0.3,1,0.3,0.3q�6�   $��18(�6�   
����������	#FF874CEE���	#FF874CEE6�   �� 5   
   $ ��Available Job Seekers. . .��6   $
��Left=�65   $��	30,24,0,0q�6P   $	��Top=�6c   $	��392��6{   $��61��6�   $��36(�6�   
���   �$��Segoe UI Light)�6�   �� 5   
   $
��Left=�6   $	��100��6/   $��	30,39,0,0q�6<   $	��Top=�6O   $	��890��6g   $��Segoe UI Light)�6s   
���   �6�   �� 5   
   $��Qualified Field��6   $
��Left=�6*   $��104,136,0,0q�6E   $	��Top=�6Z   $��28��6r   $��93��6~   
���   �6�   �� 5   
   -   5        $��QualifiedField��5      $
��Left=�6)    �� SelectedValuePath$ Content��6D   $��197,136,0,0q�6`   $	��Top=�6u   $	��148��6�   $��28��6�   ��6�   Z� .@�5      �� $��Information Technology��6   �� 5      $��
Management��6   �� 5      $��Engineering��6   �� 5      $��	Education��6   �� 5      $��Sales and Marketing��6   �� 5      $��Not Specified��6    �� 
IsSelected . 63   5      �� 5   
   -   5        $
��Left=�5      $��750,126,0,0q�6,   $	��Top=�6A   $��48��6Y   $��48��6d   ��5      �� 5        �� ImageSource$: 4/NIBM Project;component/Icons/appbar.page.search.png��6   5#      �� 5%   
   $��Interested Position��6   $
��Left=�6.   $��397,136,0,0q�6I   $	��Top=�6^   $��28��6v   $	��116��6�   
���   �6�   �� 5&   
   -   5        $��InterestedPosition��5&      $
��Left=�6-   $ Content��6H   $��518,136,0,0q�6d   $	��Top=�6y   $	��148��6�   $��28��6�   ��6�   Z� .@�5'      �� $��Select a Interest��6    . 
�������6I   �� 5(      $	��CIO��6   �� 5)      $��Director of Engineering��6   �� 5*      $��Software Engineer��6   �� 5+      $��Technical Lead��6   �� 5,      $��Solution Architecture��6   �� 5-      $��Solution Engineer��6   �� 5.      $��System Engineer��6   �� 5/      $��Sales Representative��6   �� 50      $��General Manager��6   �� 51      $��Accounts Executive��6   �� 52      $��Technical Supervisor��6   �� 53      $��Marketing Executive��6   �� 54      $%��Channel Sales Operation Manager��6   �� 55      $��Other��6   �� 56      $��Not Specified��6    . 63   57      �� 59   
   $
��Left=�6   $	��100��6/   $��
39,142,0,0q�6<   $	��Top=�6P   $	��890��6h   $��Segoe UI Light)�6t   
���   �6�   �� 5:   
   -   5        	 ��Name$	 
SeekerList��5:      $
��Left=�6&   $	��282��6A   $	��Top=�6N   $��
52,316,0,0q�6f   $	��407��6z   
���   �
�������$��14(�6�   ��6�   Z� 6�   �� 5;   
   $��Sort By��6   $
��Left=�6"   $��104,272,0,0q�6=   $	��Top=�6R   $��28��6j   $��51��6v   
���   �6�   �� 5<   
   -   5        
 ��Name$

 Sort��5<      $
��Left=�6!   $ Content��6<   $��197,268,0,0q�6X   $	��Top=�6m   $	��148��6�   $��28��6�   .@�5=      �� $��
Name A > Z��6   �� 5>      $��Default��6    . 6-   5?      �� 5@   
   $��E Mail��6   $
��Left=�6!   $��518,326,0,0q�6<   $	��Top=�6Q   $��28��6i   $	��103��6u   
���   �6�   �� 5A   
   -   5         ��Name$ Email��5A      $��14(�6!   $
��Left=�6/   $��28��6J   $��641,326,0,0q�6V    �� TextWrapping$
 Wrap=�6k   $	��Top=�6   $	��230��6�    J� RenderTransformOrigin$ 0.475,2.885&�6�   {�.  
���   �$��0q�6  ��6�   Z� 6  �� 5B   
   $��Telephone No��6   $
��Left=�6'   $��518,371,0,0q�6B   $	��Top=�6W   $��28��6o   $	��103��6{   
���   �6�   �� 5C   
   -   5        $ TeleOne��5C      $��14(�6#   $
��Left=�61   $��28��6L   $��641,371,0,0q�6X   $
 Wrap=�6m   $	��Top=�6�   $	��230��6�   $ 0.475,2.885&�6�   {�.  
���   �$��0q�6  ��6�   Z� 6  �� 5D   
   -   5        $ TeleTwo��5D      $��14(�6#   $
��Left=�61   $��28��6L   $��641,417,0,0q�6X   $
 Wrap=�6m   $	��Top=�6�   $	��230��6�   $ 0.475,2.885&�6�   {�.  
���   �$��0q�6  ��6�   Z� 6  5F      5G      