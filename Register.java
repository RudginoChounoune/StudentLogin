����   =y  FinalProject/Register  java/lang/Object 	cbClasses Ljavafx/scene/control/ComboBox; 	Signature 3Ljavafx/scene/control/ComboBox<Ljava/lang/String;>; 
studentSSN Ljava/lang/String; myClassesRef LFinalProject/MyClasses; primaryStage Ljavafx/stage/Stage; 
allClasses #Ljavafx/collections/ObservableList; 7Ljavafx/collections/ObservableList<Ljava/lang/String;>; <init> -(Ljava/lang/String;LFinalProject/MyClasses;)V Code
     ()V	   	 
	      javafx/stage/Stage
  	     
  " #  start LineNumberTable LocalVariableTable this LFinalProject/Register; ssn * Register for Classes
  , - . setTitle (Ljava/lang/String;)V 0 javafx/scene/layout/VBox@$      
 / 4  5 (D)V 7 javafx/geometry/Insets@.      
 6 4
 / < = > 
setPadding (Ljavafx/geometry/Insets;)V	 @ B A javafx/geometry/Pos C D CENTER Ljavafx/geometry/Pos;
 / F G H setAlignment (Ljavafx/geometry/Pos;)V J javafx/scene/control/Label L Select a class to register:
 I N  . P javafx/scene/control/ComboBox
 O 	  S  
 O U V W setEditable (Z)V Y Available Classes
 O [ \ . setPromptText
  ^ _  loadAvailableClasses
 O a b c 	getEditor "()Ljavafx/scene/control/TextField;
 e g f javafx/scene/control/TextField h i textProperty (()Ljavafx/beans/property/StringProperty;   k l m changed <(LFinalProject/Register;)Ljavafx/beans/value/ChangeListener;
 o q p $javafx/beans/property/StringProperty r s addListener &(Ljavafx/beans/value/ChangeListener;)V u javafx/scene/control/Button w Register
 t N  z { | handle 4(LFinalProject/Register;)Ljavafx/event/EventHandler;
 t ~  � setOnAction (Ljavafx/event/EventHandler;)V � Cancel  z � javafx/scene/layout/HBox � javafx/scene/Node
 � �  � (D[Ljavafx/scene/Node;)V
 � F
 / � � � getChildren %()Ljavafx/collections/ObservableList; � � � !javafx/collections/ObservableList � � addAll ([Ljava/lang/Object;)Z � javafx/scene/Scene@r�     @i      
 � �  � (Ljavafx/scene/Parent;DD)V
  � � � setScene (Ljavafx/scene/Scene;)V 
mainLayout Ljavafx/scene/layout/VBox; 	lblHeader Ljavafx/scene/control/Label; btnRegister Ljavafx/scene/control/Button; 	btnCancel buttonLayout Ljavafx/scene/layout/HBox; scene Ljavafx/scene/Scene;
 � � � FinalProject/DBUtil � � getAvailableClasses $(Ljava/lang/String;)Ljava/util/List;
 � � �  javafx/collections/FXCollections � � observableArrayList ;(Ljava/util/Collection;)Ljavafx/collections/ObservableList;	  �  
 O � � � setItems &(Ljavafx/collections/ObservableList;)V � Database Error
 � � � java/lang/Exception � � 
getMessage ()Ljava/lang/String;  � � � makeConcatWithConstants &(Ljava/lang/String;)Ljava/lang/String;
  � � � 	showAlert '(Ljava/lang/String;Ljava/lang/String;)V � java/sql/SQLException �  java/lang/ClassNotFoundException ex Ljava/lang/Exception; StackMapTable registerClass
 O � � � getSelectionModel -()Ljavafx/scene/control/SingleSelectionModel;
 � � � )javafx/scene/control/SingleSelectionModel � � getSelectedItem ()Ljava/lang/Object; � java/lang/String
 � � � � isEmpty ()Z � Selection Error � "Please select a class to register. �  - 
 � � � � split '(Ljava/lang/String;)[Ljava/lang/String;
 � � � � registerClassForStudent '(Ljava/lang/String;Ljava/lang/String;)Z � Registration Successful � /You have successfully registered for the class.
  � �  close
 �  � FinalProject/MyClasses  reloadClasses Registration Failed 3Could not register for the class. Please try again.  � selectedClass courseId
 javafx/scene/control/Alert	 $javafx/scene/control/Alert$AlertType INFORMATION &Ljavafx/scene/control/Alert$AlertType;
	  )(Ljavafx/scene/control/Alert$AlertType;)V
	 ,
	 . setHeaderText
	 . setContentText
	 showAndWait ()Ljava/util/Optional; title message alert Ljavafx/scene/control/Alert; 	showStage
 %  lambda$0 K(Ljavafx/beans/value/ObservableValue;Ljava/lang/String;Ljava/lang/String;)V )*+ test 2(Ljava/lang/String;)Ljava/util/function/Predicate; �-./ filtered P(Ljava/util/function/Predicate;)Ljavafx/collections/transformation/FilteredList; obs $Ljavafx/beans/value/ObservableValue; oldValue newValue LocalVariableTypeTable 9Ljavafx/beans/value/ObservableValue<+Ljava/lang/String;>; lambda$2 (Ljavafx/event/ActionEvent;)V
 9 �  e Ljavafx/event/ActionEvent; lambda$3 lambda$1
 �?@ � toLowerCase
 �BCD contains (Ljava/lang/CharSequence;)Z item 
SourceFile Register.java BootstrapMethods
JLK "java/lang/invoke/LambdaMetafactoryMN metafactory �(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;I K(Ljavafx/beans/value/ObservableValue;Ljava/lang/Object;Ljava/lang/Object;)VP
 S&'R' (Ljavafx/event/Event;)VV
 Y67X7V
 ^<7]7
bdc $java/lang/invoke/StringConcatFactory �e �(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;ah #Failed to load available classes: j Error registering for class:  (Ljava/lang/Object;)Zk
 n= �m (Ljava/lang/String;)Zp InnerClassest %java/lang/invoke/MethodHandles$Lookupv java/lang/invoke/MethodHandles Lookup 	AlertType !                	 
                      
        p     *� *+� *,� *� Y� � *� !�    $          	        %         & '      ( 
         #     �     �*� )� +� /Y 1� 3L+� 6Y 8� :� ;+� ?� E� IYK� MM*� OY� Q� R*� R� T*� RX� Z*� ]*� R� `� d*� j  � n� tYv� xN-*� y  � }� tY�� x:*� �  � }� �Y 1� �Y-SYS� �:� ?� �+� �� �Y,SY*� RSYS� � W� �Y+ � �� �:*� � ��    $   R     	    ! " " ) $ 3 % > & F ' O ( S * f 4 p 5 z 7 � 8 � : � ; � = � ? � @ � A %   H    � & '    � � �  3 � � �  p y � �  � d � �  � @ � �  � 
 � �   _      �     -**� � �� �� �*� R*� �� �� L*�+� º �  � ̱      �     �  $       E  F  G  H , J %       - & '     � �  �    \ �  �          m*� R� ض �� �L+� 
+� � *�� ̱+� �2M*� ,� � *��� �*� � �*� � ��  *� ̧ N*�-� º  � ̱  + Y \ � + Y \ �  $   :    M  N  O ! P " S + V 6 W > X E Y L Z O [ Y ] ] ^ l ` %   *    m & '    _ 
  + B 
  ]  � �  �    �  �� , �L �  � �     |      �	Y��N-+�-�-,�-�W�    $       c  d  e  f  g  h %   *      & '       
       
   !"  #      6     *� �$�    $   
    k  l %        & '  &'     �     4-� '-� �  *� �-�(  �, :*� R� �� *� R*� �� ��    $   "    +  ,  -  ,  . % / ( 0 3 2 %   4    4 & '     401    42 
    43 
   	.  4       405   	.   �    (
67     9     *�8�    $       5 %        & '     :; <7     <     *� � ��    $       8 %        & '     :; 
= �     6     +�>*�>�A�    $       - %       E 
  F   GH   6 O QTUO WZ[O \_`f gf iO loqr    suw 	x@