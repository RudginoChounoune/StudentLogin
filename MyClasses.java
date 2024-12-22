����   =  FinalProject/MyClasses  java/lang/Object 
studentSSN Ljava/lang/String; primaryStage Ljavafx/stage/Stage; table  Ljavafx/scene/control/TableView; 	Signature WLjavafx/scene/control/TableView<Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;>; <init> )(Ljava/lang/String;Ljavafx/stage/Stage;)V Code
     ()V	    	      javafx/scene/control/TableView
  	   	 
 LineNumberTable LocalVariableTable this LFinalProject/MyClasses; ssn start # 
My Classes
 % ' & javafx/stage/Stage ( ) setTitle (Ljava/lang/String;)V + javafx/scene/control/Label
 * 
  . / 0 configureTable #(Ljavafx/scene/control/TableView;)V
 2 4 3 FinalProject/DBUtil 5 6 getStudentName &(Ljava/lang/String;)Ljava/lang/String;   8 9 6 makeConcatWithConstants
 * ; < ) setText
 2 > ? @ getStudentClassesWithGrades $(Ljava/lang/String;)Ljava/util/List;
  B C D convertToObservableList 5(Ljava/util/List;)Ljavafx/collections/ObservableList;
  F G H setItems &(Ljavafx/collections/ObservableList;)V
 J L K java/lang/Exception M N 
getMessage ()Ljava/lang/String;  8 Q javafx/scene/control/ScrollPane
 P S  T (Ljavafx/scene/Node;)V
 P V W X setFitToWidth (Z)V@i      
 P \ ] ^ setPrefHeight (D)V ` javafx/scene/control/Menu b Manage Class
 _ d  ) f javafx/scene/control/MenuItem h Add
 e d  k l m handle 5(LFinalProject/MyClasses;)Ljavafx/event/EventHandler;
 e o p q setOnAction (Ljavafx/event/EventHandler;)V s Remove  k
 _ v w x getItems %()Ljavafx/collections/ObservableList; z | { !javafx/collections/ObservableList } ~ addAll ([Ljava/lang/Object;)Z � javafx/scene/control/MenuBar
  
  � � x getMenus z � � � add (Ljava/lang/Object;)Z � javafx/scene/control/Button � Exit
 � d  k
 � o � javafx/scene/layout/BorderPane
 � 
 � � � T setLeft
 � � � T setRight  k  k � javafx/scene/layout/HBox@$       � javafx/scene/Node
 � �  � (D[Ljavafx/scene/Node;)V	 � � � javafx/geometry/Pos � � CENTER Ljavafx/geometry/Pos;
 � � � � setAlignment (Ljavafx/geometry/Pos;)V � javafx/scene/layout/VBox
 � � � javafx/geometry/Insets
 � �  ^
 � � � � 
setPadding (Ljavafx/geometry/Insets;)V
 � �
 � � � T setTop
 � � � T 	setCenter � javafx/scene/Scene@��     @y      
 � �  � (Ljavafx/scene/Parent;DD)V
 % � � � setScene (Ljavafx/scene/Scene;)V
 % � �  show 
lblWelcome Ljavafx/scene/control/Label; studentName classDetails Ljava/util/List; e Ljava/lang/Exception; 
scrollPane !Ljavafx/scene/control/ScrollPane; 
manageMenu Ljavafx/scene/control/Menu; addMenuItem Ljavafx/scene/control/MenuItem; removeMenuItem menuBar Ljavafx/scene/control/MenuBar; btnExit Ljavafx/scene/control/Button; topPane  Ljavafx/scene/layout/BorderPane; 	addButton removeButton 	buttonBox Ljavafx/scene/layout/HBox; 
centerVBox Ljavafx/scene/layout/VBox; mainPane scene Ljavafx/scene/Scene; LocalVariableTypeTable $Ljava/util/List<Ljava/lang/String;>; StackMapTable openRegisterWindow � FinalProject/Register
 � �  � -(Ljava/lang/String;LFinalProject/MyClasses;)V
 � � �  	showStage registerWindow LFinalProject/Register; removeSelectedClass Z(Ljavafx/scene/control/TableView<Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;>;)V
  � �  getSelectionModel :()Ljavafx/scene/control/TableView$TableViewSelectionModel;
 6javafx/scene/control/TableView$TableViewSelectionModel getSelectedItem ()Ljava/lang/Object; java/util/Map
 
 confirmAndRemoveClass 2(Ljava/util/Map;Ljavafx/scene/control/TableView;)V selected Ljava/util/Map; 5Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>; reloadClasses
 J  printStackTrace  javafx/scene/control/TableColumn 	Course ID
 d  call ()Ljavafx/util/Callback;
  setCellValueFactory (Ljavafx/util/Callback;)V
"# X setSortable% Course Name ( Grade 	
 +, x 
getColumns courseIdColumn "Ljavafx/scene/control/TableColumn; courseNameColumn gradeColumn kLjavafx/scene/control/TableColumn<Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;Ljava/lang/String;>; �(Ljava/util/List<Ljava/lang/String;>;)Ljavafx/collections/ObservableList<Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;>;
465  javafx/collections/FXCollections7 x observableArrayList9;: java/util/List<= iterator ()Ljava/util/Iterator;?A@ java/util/IteratorB nextD java/lang/StringF 	
CHIJ split '(Ljava/lang/String;)[Ljava/lang/String;L java/util/HashMap
K O courseIdQRS put 8(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;U 
courseNameW grade?YZ[ hasNext ()Z list #Ljavafx/collections/ObservableList; detail parts [Ljava/lang/String; map ZLjavafx/collections/ObservableList<Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;>; �(Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;Ljavafx/scene/control/TableView<Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;>;)Ve javafx/scene/control/Alert	gih $javafx/scene/control/Alert$AlertTypejk CONFIRMATION &Ljavafx/scene/control/Alert$AlertType;m +Are you sure you want to remove this class?o javafx/scene/control/ButtonType	nqrs YES !Ljavafx/scene/control/ButtonType;	nuvs NO
dx y ](Ljavafx/scene/control/Alert$AlertType;Ljava/lang/String;[Ljavafx/scene/control/ButtonType;)V{ Confirm Removal
d '
d~ ) setHeaderText
d��� showAndWait ()Ljava/util/Optional; 
��� accept f(LFinalProject/MyClasses;Ljava/util/Map;Ljavafx/scene/control/TableView;)Ljava/util/function/Consumer;
��� java/util/Optional�� 	ifPresent  (Ljava/util/function/Consumer;)V confirmAlert Ljavafx/scene/control/Alert; lambda$0 (Ljavafx/event/ActionEvent;)V
 � �  Ljavafx/event/ActionEvent; lambda$1
 � � 0 lambda$2
 %��  close lambda$3 lambda$4 lambda$5 Y(Ljavafx/scene/control/TableColumn$CellDataFeatures;)Ljavafx/beans/value/ObservableValue;� +javafx/beans/property/ReadOnlyStringWrapper
��� 1javafx/scene/control/TableColumn$CellDataFeatures� getValue��� get &(Ljava/lang/Object;)Ljava/lang/Object;
� d data 3Ljavafx/scene/control/TableColumn$CellDataFeatures; |Ljavafx/scene/control/TableColumn$CellDataFeatures<Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;Ljava/lang/String;>; lambda$6 lambda$7 lambda$8 S(Ljava/util/Map;Ljavafx/scene/control/TableView;Ljavafx/scene/control/ButtonType;)V
 2��� unregisterClass '(Ljava/lang/String;Ljava/lang/String;)Z
  v z�� � remove	g��k INFORMATION
d� � )(Ljavafx/scene/control/Alert$AlertType;)V� Removal Successful� (The class has been successfully removed.
d�� ) setContentText	g��k ERROR� Error� Failed to remove class response 	infoAlert 
errorAlert 
SourceFile MyClasses.java BootstrapMethods
��� $java/lang/invoke/StringConcatFactory 9� �(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;�� 
Welcome, � Error loading classes: 
��� "java/lang/invoke/LambdaMetafactory�� metafactory �(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;� (Ljavafx/event/Event;)V�
 ������
 ������
 ������
 ������
 ������
 ����
 ����
 ��� (Ljava/lang/Object;)V
 �� $(Ljavafx/scene/control/ButtonType;)V InnerClasses %java/lang/invoke/MethodHandles$Lookup java/lang/invoke/MethodHandles Lookup 	AlertType CellDataFeatures TableViewSelectionModel !                 	 
               h     *� *+� *,� *� Y� � �              	                                 !     Z    �*� "� $� *Y� ,L**� � -*� � 1M+,� 7  � :*� � =N*� *-� A� E� M+,� I� O  � :� PY*� � RM,� U, Y� [� _Ya� cN� eYg� i:*� j  � n� eYr� i:*� t  � n-� u� eYSYS� y W� Y� �:� �-� � W� �Y�� �:*� �  � �� �Y� �:� �� �� �Yg� �:		*� �  � �� �Yr� �:

*� �  � �� �Y �� �Y	SY
S� �:� �� �� �Y �� �Y+SY,SYS� �:� �Y �� �� �� �� �� �Y� �:� �� �� �Y � ķ �:*� � �*� � ͱ   ? B J     � )    	    !  $ ! % + & 3 ' ? ( C ) P , \ - a . h 0 r 1 } 2 � 3 � 4 � 5 � 7 � 8 � : � ; � = � > � ? � A B D E$ G> HF Jc Kr Lz N� O� P� R� S� T� U    �   �     � � �  !  �   3  � �  C  � �  \W � �  rA � �  }6 � �  �  � �  � � � �  � � � �  � � � �  � � � 	 � � � 
> u � � c P � � � 0 � � �  � �  �     3  � �  �    � B   *  J  �      N     � �Y*� *� �L+� ��           X  Y  Z               � �   � 0      �    �     +� ���M,� 	*,+�	�           \  ]  ^  `                 	 
     �        	      �    �        �     *� � =L*� *+� A� E� L+��      J         c  d  e  f  h                � �    � �  �       � �  �    W J  / 0      �        j�Y�M,�  �,�!�Y$�N-�&  �-�!�Y'�:�)  ��!+�*�Y,SY-SYS� y W�       .    k  l  m  o $ p - q 2 s > t H u N w i x    4    j       j 	 
   _-.  $ F/.  > ,0.  �   *    j 	    _-1  $ F/1  > ,01   C D     2   3     n�3M+�8 :� V�> �CN-E�G:�KY�M:N2�P WT2�P WV2�P W,� � W�X ���,�       * 
   {  |  } # ~ ,  ; � J � Y � b | l �    >    n       n � �   j\]   H^   # ?_`  , 6a  �        n � �   j\b  , 6a  �    �   9 z ?  � R      c    �     :�dY�fl�nY�pSY�tS�wN-z�|-�}-��*+,��  ���           �  � % � * � 9 �    *    :       :    : 	 
   ��  �       :    : 	  ��     9     *���           2                �� ��     =     	**� ���           4        	       	 �� ��     <     *� ���           ;                �� ��     9     *���           B                �� ��     =     	**� ���           E        	       	 �� 
��     V     ��Y*���N�� �C���           l        ��   �       ��  
��     V     ��Y*���T�� �C���           p        ��   �       ��  
��     V     ��Y*���V�� �C���           t        ��   �       ��  ��    #     �-�p� }*� +N�� �C��� g,��+�� W�dY����:��|�}ö���W� 1:�dY�ȷ�:˶|Ͷ}� I����W�   P S J     >    �  �  � ( � 4 � < � B � J � P � U � a � i � q � { � � �    4    �       ��s  4 ��  U , � �  a  ��  �   	 � S J- �   ��   h � �� �� ���� ���� ���� ���� ����  � 	� 
�    "  gd@� 	 	