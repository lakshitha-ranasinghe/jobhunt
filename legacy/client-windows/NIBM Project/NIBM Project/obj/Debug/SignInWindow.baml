   M S B A M L   `   `   `  ����T  PMahApps.Metro, Version=1.1.2.0, Culture=neutral, PublicKeyToken=f4fb5a3c4d1e5b4fV;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.MetroMahApps.Metro.Controls  5   	    NIBM Project   NIBM_Project.SignInWindow   5      \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   PControls;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.Metro     5   	     � Title$  Sign In��5   	   $	��500��6   $	��700��6&    � SizeToContent$ Height=�62    � WindowStartupLocation$ CenterScreen=�6I   $��14(�6n   ���	#FF0A0A0A � 
ResizeMode$ CanMinimize=�5       .��5      �  � RowDefinitions 5   
   �� 5	      .+�5   
   �� $��Sign In...!!��6   $
��Left=�6'   $��
100,35,0,0q�6B   $	��Top=�6V   $	��350��6n   $��61��6z   $��36(�6�   
���   �$��Segoe UI Light)�6�   �� 5   
   $	��300��6,   *   $MahApps.Metro.Controls.TextBoxHelper   ClearTextButton .    	Watermark$ Enter your User name��6f   $��Center=�6�   $
��Left=�6�   $��200,170,0,267q�6�   $��34��6�   $��14.667(�6�   $��Segoe UI Light)�6	  ��6   �� *
userName+6	  G� 5   
   -   5        $��signPassword��5       . $ Enter your Password��6X   $
��Left=�6�   $��200,225,0,0q�6�   $	��Top=�6�   $	��300��6�   $��33��6�   $��14.667(�6�    G� PasswordChar$ *��6  $��Segoe UI Light)�6  �� 5   
   -   5           AccentedSquareButtonStyle#��[  5      $��Login��6D   $	��150��6T   $��57��6`   $
��Left=�6l   $��275,325,0,0q�6�   $	��Top=�6�   
���   �$��Segoe UI Light)�6�    J� RenderTransformOrigin$ 0.538,-0.615&�6�   $��12(�6  ���	#FF49B4E8
���   �6A  �� 5   
   -   5          MetroCircleButtonStyle#���  5      $
��Left=�6C   $��	35,45,0,0q�6^   $	��Top=�6q   $��50��6�   $��50��6�   
���   �
���������5      �� 5      	 �� ImageSource$9	 3/NIBM Project;component/Icons/appbar.arrow.left.png��6   5      �� 5   
   $
��Left=�6   $	��100��6/   $��
100,46,0,0q�6<   $	��Top=�6P   $	��500��6h   $��Segoe UI Light)�6t   
���   �6�   -   'MahApps.Metro.Controls.MetroProgressBar  5   
   -   5        $��progressBar��5   U   $��160,294,160,170q�5   $   ���	#FF49B4E8
 � IsIndeterminate
 .  � Maximum$	 100Q�5   ;    � Minimum$ 0Q�6I   $��25��6h   ���	#FF49B4E86t   �� 5   
   -   5        $��progressName��5      $�� ��6$   $
��Left=�6/   $��	0,443,0,0q�6J   $	��Top=�6]   $	��475��6u   $��28��6�   
�������6�   )   #MahApps.Metro.Controls.ProgressRing  5   
   -   5          Name$ PRing��5          IsLarge .  $��15��6?   $��15��6N   $��15��6\   $��15��6h   6u   5      5      