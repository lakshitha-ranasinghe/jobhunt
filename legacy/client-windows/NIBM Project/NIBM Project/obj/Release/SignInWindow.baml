   M S B A M L   `   `   `  ���� T  PMahApps.Metro, Version=1.1.2.0, Culture=neutral, PublicKeyToken=f4fb5a3c4d1e5b4fV;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.MetroMahApps.Metro.Controls   NIBM Project   NIBM_Project.SignInWindow   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     PControls;clr-namespace:MahApps.Metro.Controls;assembly=MahApps.Metro       � Title$  Sign In��$	��500��$	��700�� � SizeToContent$ Height=� � WindowStartupLocation$ CenterScreen=�$��14(����	#FF0A0A0A � 
ResizeMode$ CanMinimize=�.���  � RowDefinitions �� .+��� $��Sign In...!!��$
��Left=�$��
100,35,0,0q�$	��Top=�$	��350��$��61��$��36(�
���   �$��Segoe UI Light)��� $	��300��*   $MahApps.Metro.Controls.TextBoxHelper   ClearTextButton .    	Watermark$ Enter your User name��$��Center=�$
��Left=�$��200,170,0,267q�$��34��$��17(�$��Segoe UI Light)����� *
userName+G� -   $��signPassword�� . $ Enter your Password��$
��Left=�$��200,225,0,0q�$	��Top=�$	��300��$��33��$��17(� G� PasswordChar$ *��$��Segoe UI Light)��� -      AccentedSquareButtonStyle#��[  $��Login��$	��150��$��57��$
��Left=�$��275,325,0,0q�$	��Top=�
���   �$��Segoe UI Light)� J� RenderTransformOrigin$ 0.538,-0.615&�$��12(����	#FF49B4E8
���   ��� -     MetroCircleButtonStyle#���  $
��Left=�$��	35,45,0,0q�$	��Top=�$��50��$��50��
���   �
����������� 	 �� ImageSource$9	 3/NIBM Project;component/Icons/appbar.arrow.left.png���� $
��Left=�$	��100��$��
100,46,0,0q�$	��Top=�$	��500��$��Segoe UI Light)�
���   �-   'MahApps.Metro.Controls.MetroProgressBar  -   $��progressBar��$��160,294,160,170q����	#FF49B4E8
 � IsIndeterminate
 .  � Maximum$	 100Q� � Minimum$ 0Q�$��25�����	#FF49B4E8�� -   $��progressName��$�� ��$
��Left=�$��	0,443,0,0q�$	��Top=�$	��475��$��28��
�������)   #MahApps.Metro.Controls.ProgressRing  -     Name$ PRing��   IsLarge .  $��15��$��15��$��15��$��15��