����   = �  FinalProject/DBUtil  java/lang/Object JDBC_DRIVER Ljava/lang/String; ConstantValue 	 com.mysql.cj.jdbc.Driver DB_URL  Pjdbc:mysql://database-cuny.c4piq2ndsfvh.us-west-1.rds.amazonaws.com:3306/CUNY_DB USER  cst3613 PASS  	password1 <init> ()V Code
     LineNumberTable LocalVariableTable this LFinalProject/DBUtil; getConnection ()Ljava/sql/Connection; 
Exceptions    java/lang/ClassNotFoundException " java/sql/SQLException
 $ & % java/lang/Class ' ( forName %(Ljava/lang/String;)Ljava/lang/Class;
 * , + java/sql/DriverManager  - M(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/sql/Connection; validateStudentSSN (Ljava/lang/String;)Z 1 4SELECT COUNT(*) AS count FROM Students WHERE ssn = ?
  3   5 7 6 java/sql/Connection 8 9 prepareStatement 0(Ljava/lang/String;)Ljava/sql/PreparedStatement; ; = < java/sql/PreparedStatement > ? 	setString (ILjava/lang/String;)V ; A B C executeQuery ()Ljava/sql/ResultSet; E G F java/sql/ResultSet H I next ()Z K count E M N O getInt (Ljava/lang/String;)I ; Q R  close 5 Q
 U W V java/lang/Throwable X Y addSuppressed (Ljava/lang/Throwable;)V ssn sql conn Ljava/sql/Connection; pstmt Ljava/sql/PreparedStatement; rs Ljava/sql/ResultSet; StackMapTable d java/lang/String getStudentName &(Ljava/lang/String;)Ljava/lang/String; h 6SELECT firstName, lastName FROM Students WHERE ssn = ? j 	firstName E l m f 	getString o lastName   q r s makeConcatWithConstants 8(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String; u Student getStudentClassesWithGrades $(Ljava/lang/String;)Ljava/util/List; 	Signature 8(Ljava/lang/String;)Ljava/util/List<Ljava/lang/String;>; { java/util/ArrayList
 z  ~ �SELECT Course.courseId, Course.title, Enrollment.grade FROM Enrollment INNER JOIN Course ON Enrollment.courseId = Course.courseId WHERE Enrollment.ssn = ? � courseId � title � grade  � r � J(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String; � � � java/util/List � � add (Ljava/lang/Object;)Z classDetails Ljava/util/List; detail LocalVariableTypeTable $Ljava/util/List<Ljava/lang/String;>; unregisterClass '(Ljava/lang/String;Ljava/lang/String;)Z � 5DELETE FROM Enrollment WHERE ssn = ? AND courseId = ? ; � � � executeUpdate ()I 
studentSSN affectedRows I getAvailableClasses � hSELECT courseId, title FROM Course WHERE courseId NOT IN (SELECT courseId FROM Enrollment WHERE ssn = ?)  q E Q availableClasses 	classInfo registerClassForStudent � GINSERT INTO Enrollment (ssn, courseId, dateRegistered) VALUES (?, ?, ?) � java/sql/Date
 � � � java/lang/System � � currentTimeMillis ()J
 � �  � (J)V ; � � � setDate (ILjava/sql/Date;)V 
SourceFile DBUtil.java BootstrapMethods
 � � � $java/lang/invoke/StringConcatFactory r � �(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite; � �   � 		 �  -  InnerClasses � %java/lang/invoke/MethodHandles$Lookup � java/lang/invoke/MethodHandles Lookup !                
                                  /     *� �                        	          !    4      � #W� )�       
             	 . /       !    �     �0LMN� 2:+� 4 :*� : � @ :� D � J� L � � � 
� P � 
� S �M� 
� P ,�N,� -M� ,-� ,-� T� 
� S ,�N,� -M� ,-� ,-� T,�   C \    O k   \ k k    [ �   \ � �       "           (  C  [  ]     4    � Z     � [     \ ]   S ^ _  ( 4 ` a  b   R � B  c c U U 5 ; E  @KK�    c c U U 5 ;  U� A U		� A U		 	 e f       !    �     �gLMN� 2:+� 4 :*� : � @ :� D � 3i� k n� k � p  � 
� P � 
� S �� � P � M� 
� P ,�� E� S � ;N,� -M� ,-� ,-� T� 
� S ,�N,� -M� ,-� ,-� T,�t�   I q    U �   b � �    a �   b � �       * 
          (   2 ! I # a ! b # � $    4    � Z     � [    � \ ]   h ^ _  ( : ` a  b   B � U  c c U U 5 ; E  cK c�  N U� N U		� A U		�  	 v w       !  x    y   �  	   ػ zY� |L}MN:� 2:,� 4 :*� : � @ :� .� k �� k �� k � �  :+� � W� D ���� � P � N� 
� P -�� M� S � C:-� 	N� -� 	-� T� 
� S -�:-� 	N� -� 	-� T-�+�   i x    � �    � �       .    (  )  ,  - ( . 1 / 4 0 V 1 _ / i 3 � 4    H    � Z     � � �   � [    � \ ]   f ^ _  1 8 ` a  V 	 �   �      � � �  b   W � 4  c � c U U 5 ; E  *�   c � c U U 5 ;  U� N U� A U�  	 � �       !    �     ��MN:� 2:,� 4 :*� : +� : � � 6� � � 
� P � 
� S �N� 
� P -�:-� 	N� -� 	-� T� 
� S -�:-� 	N� -� 	-� T-�   < U    H d   U d d    T �   U � �       & 	   9  :  ;   < ) = 2 > < ? T > V ?    >    � �      � �    � [    { \ ]   K ^ _  2 # � �  b   V � ;  c c c U U 5 ;  @KK�    c c c U U 5 ;  U� A U� A U 	 � w       !  x    y   8    � zY� |L�MN:� 2:,� 4 :*� : ::� @ :	� %	� k 	�� k � �  :
+
� � W	� D ���	� =	� � � 3:	� 
	� � �:� 
:� � 
� T�� � P � N� 
� P -�� M� S � C:-� 	N� -� 	-� T� 
� S -�:-� 	N� -� 	-� T-�+�  7 f u   . � �    � �    � �    � �       2    C  D  E  F ( G 7 H : I S J \ H f L � M N    H    �     � �  	 [    � \ ]   � ^ _  7 L ` a 	 S 	 �  
 �      � �  b   R � : 
 c � c U U 5 ; U U E  !X U� B U� N U� N U� A U�  	 � �       !    �     ��MN:� 2:,� 4 :*� : +� : � �Y� �� �� � � � 6� � � 
� P � 
� S �N� 
� P -�:-� 	N� -� 	-� T� 
� S -�:-� 	N� -� 	-� T-�   N g    Z v   g v v    f �   g � �       * 
   S  T  U   V ) W ; X D Y N Z f Y h Z    >    � �      � �    � [    � \ ]   ] ^ _  D # � �  b   V � M  c c c U U 5 ;  @KK�    c c c U U 5 ;  U� A U� A U  �    � �     �  � �  � �  � �   
  � � � 