   M S B A M L   `   `   `  ����T  PMahApps.Metro, Version=1.1.2.0, Culture=neutral, PublicKeyToken=f4fb5a3c4d1e5b4fV;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.MetroMahApps.Metro.Controls  5   	    NIBM Project&    NIBM_Project.CompanySignUpWindow   5      \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   PControls;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.Metro     5   	     � Title$  Company Sign Up��5   	   $	��600��6!   $	��600��6.    � WindowStartupLocation$ CenterScreen=�6:   $��14(�6_   
������� � 
ResizeMode$ CanMinimize=�6�    � 	Resources 5       �� MergedDictionaries �� Source�� 5   
    5      �� 5	      $O Ipack://application:,,,/MahApps.Metro;component/Styles/Accents/Purple.xaml?�6%   %    5      .��5      � -   5        $��companySignUpWindowGrid��5      $��Stretch=�6*   .+�5   
   �� $��Sign Up...!!��6   $��2��6'   $
��Left=�67   $��	49,24,0,0q�6R   $	��Top=�6e   $	��350��6}   $��61��6�   $��36(�6�   
���   �$��Segoe UI Light)�6�   �� 5   
   $��Company Name ��6   $
��Left=�6(   $��
49,165,0,0q�6C   $	��Top=�6W   $��28��6o   $	��124��6{   
���   �6�   �� 5   
   $��	User Name��6   $
��Left=�6$   $��
49,225,0,0q�6?   $	��Top=�6S   $��28��6k   $��97��6w   
���   �6�   �� 5   
   $��Password��6   $
��Left=�6#   $��
49,285,0,0q�6>   $	��Top=�6R   $��28��6j   $��97��6v   
���   � J� RenderTransformOrigin$ 0.268,0.464&�6�   G� 5   
   -   5        $��password��5   v   *   $MahApps.Metro.Controls.TextBoxHelper   ClearTextButton .    	Watermark$ Enter Password��6D   $
��Left=�6�   $��228,285,0,0q�6�   $	��Top=�6�   $	��293��6�   $��29��6�   $��16(�6�   	 G� PasswordChar$	 *��6�   
���   �6  �� 5   
   $��Confirm Your Password��6   $
��Left=�60   $��
49,345,0,0q�6K   $	��Top=�6_   $��28��6w   $	��154��6�   
���   �6�   G� 5   
   -   5        $��confirmPassword��5   ~    . $ Re-Enter your password��6D   $
��Left=�6�   $��228,345,0,0q�6�   $	��Top=�6�   $	��293��6�   $��29��6�   $��16(�6�   $	 *��6  
���   �6  �� 5   
   -   5           AccentedSquareButtonStyle#��[  5      $��Cancel��6E   $
��Left=�6V   $��
58,440,0,0q�6q   $	��Top=�6�   $	��145��6�   $��57��6�   $��Segoe UI Light)�6�   
���   ����	#FF874CEE
���   �6  �� 5   
   -   5        #��[  5      $��Clear��6E   $
��Left=�6U   $��231,440,0,0q�6p   $	��Top=�6�   $	��145��6�   $��57��6�   $��Segoe UI Light)�6�   
���   ����	#FF874CEE
���   �6  �� 5   
   -   5        #��[  5      $��Sign Up ��6E   $
��Left=�6X   $��398,440,0,0q�6s   $	��Top=�6�   $	��145��6�   $��57��6�   $��Segoe UI Light)�6�   
���   ����	#FF874CEE
���   �6  �� 5   
   $
��Left=�6   $	��100��6/   $��	49,47,0,0q�6<   $	��Top=�6O   $	��507��6g   $��Segoe UI Light)�6s   
���   �
���   �6�   �� 5   
    . $ Enter your Company Name��6@   $
��Left=�6�   $��28��6�   $��228,165,0,0q�6�   
 �� TextWrapping$

 Wrap=�6�   $	��Top=�6�   $	��293��6  
���   �$��16(�6   ��6{   �� *companyName+6   �� 5   
    . $ Enter User Name��6@   $
��Left=�6�   $��28��6�   $��228,225,0,0q�6�   $

 Wrap=�6�   $	��Top=�6�   $	��293��6�   
���   �$��16(�6  ��6s   �� *
userName+6  �� 5   
   $
��Left=�6   $��80��6+   $��
441,10,0,0q�67   $	��Top=�6K   $��80��6c   $��2q�6n   ��5      �� 5       �� ImageSource$3 -/NIBM Project;component/Icons/appbar.lock.png��6   5!      5#      5$      