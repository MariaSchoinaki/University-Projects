#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# Onomatepwnumo: Schoinaki Maria
# A.M: 3210191
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		.text
		.globl main
main:

#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------			
	
	arithmos1:			  				
		la $a0, str1	                            #System call code for printing string/ Emfanish "Number: "		 
		li $v0, 4
		syscall					
				
		li $v0, 5				                    #System call code for reading int/ Ton arithmo
		syscall
									
		sw $v0, Apotelesma							#Store int in Apotelesma/ Apothikeush tou akeraiou sto Apotelesma

#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------			
	
	operator:	
		lb $t1, opeq						 		#Load operator '=' to $t1
		lb $t2, opplu								#Load operator '+' to $t2
		lb $t3, opmin						 		#Load operator '-' to $t3
		lb $t4, opmul						 		#Load operator '*' to $t4
		lb $t5, opdiv						 		#Load operator '/' to $t5
		lb $t6, opmod						 		#Load operator '%' to $t6
					
		la $a0, str2								#System call code for printing string/ Emfanish "Operator: "	
		li $v0, 4  		
		syscall			 
							
		li $v0, 12									#System call code for reading char/ To operator
		syscall
					
		sb $v0, op									#Store char in op/ Apothikeush tou char sto op
		lb $t7, op									#Load input operator to $t7
					
		beq $t7, $t1, ifeq							#If input operator == '=' goto ifeq
	    beq $t7, $t2, ifeq							#If input operator == '+' goto ifeq
		beq $t7, $t3, ifeq							#If input operator == '-' goto ifeq
		beq $t7, $t4, ifeq							#If input operator == '*' goto ifeq
		beq $t7, $t5, ifeq							#If input operator == '/' goto ifeq
		beq $t7, $t6, ifeq							#If input operator == '%' goto ifeq
		
#Ayto to kommati toy kwdika tha ektelestei mono an dwthei mh apodektos operator/ Alliws tha metaferthei kateutheian sto label ifeq gia na elenjei an o operator einai isos me '='
											
		la $a0, str4								#System call code for printing string/ Emfanish "Error: Invalid operator. "
		li $v0, 4 		
		syscall
		j exit										#Goto exit
		
#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------				
	
	ifeq:
		lb $t7, op									#Load input operator to $t7/ Oi kataxwrhtes $tx einai proswrinoi
		lb $t1, opeq						 		#Load operator '=' to $t1/ Oi kataxwrhtes $tx einai proswrinoi
		bne $t7, $t1, arithmos2						#If input operator != '=' goto arithmos2

#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

#Ayto to kommati toy kwdika ekteleitai mono an o input telesthis einai isos me '='	
	
		la $a0, str8								#System call code for printing string/ Emfanish "The result is:"
		li $v0, 4  	 
		syscall
		
		lw $a0, Apotelesma							#System call code for printing int/ Emfanish tou telikou apotelesmatos
		li $v0, 1
		syscall
		
		la $a0, str3								#System call code for printing string/ Emfanish "Do you want to continue with a new expression? (y/n) "
		li $v0, 4  			
		syscall			
							
		li $v0, 12									#System call code for reading char/ Thn apantish(y/n)
		syscall
		sb $v0, apantish							#Store char in apantish/ Apothikeush tou char sthn apantish
		lb $t0, apantish							#Load input apantishs ston kataxwrhth $t0
		
		la $a0, str9								#System call code for printing string/ Emfanish duo grammes katw(\n allazei grammh)
		li $v0, 4  					
		syscall
		
		lb $t1, ap1									#Load ston kataxwrhth $t1 to ap1/ Dhladh ton xarakthra 'y'
		beq $t0, $t1, arithmos1                     #If input apantish == 'y' goto arithmos1/ An o xrhsths pathsei 'y' ksekina apo thn arxh thn diadikasia(arithmos1)
		j exit										#else goto exit/ An o xrhsths pathsei 'n' phgaine sto label exit opou termatizei to programma
	
#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	

	arithmos2: 
		la $a0, str5								#System call code for printing string/ Emfanish "Number: "
		li $v0, 4  			 
		syscall			
				
		li $v0, 5									#System call code for reading int/ Ton arithmo
		syscall		
		sw $v0, B									#Store int in B/ Apothikeush tou akeraiou sto B
	
		lw $t0, B									#Load ston kataxwrhth $t0 ton input arithmo B
		lb $t1, op									#Load ston kataxwrhth $t1 ton input operator
		lb $t2, opdiv								#Load ston kataxwrhth $t2 to char '/'
		lb $t3, opmod								#Load ston kataxwrhth $t3 to char '%'
		
#Se ayto to kommati tou kwdika elegxetai an o input arithmos einai 0 otan exoume diairesh		

		bne $t0, $zero, prakseis                    #If input arithmos != 0 goto prakseis/ An o arithmos den einai 0 phgaine sto label pou ektelountai oi prakseis
		beq $t1, $t2, mnm					     	#If input operator == '/' goto mnm
		beq $t1, $t3, mnm				     		#If input operator == '%' goto mnm

#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	prakseis:
		lb $t0, op									#Load input operator to $t0
		lb $t1, opplu								#Load operator '+' to $t1
		lb $t2, opmin						 		#Load operator '-' to $t2
		lb $t3, opmul						 		#Load operator '*' to $t3
		lb $t4, opdiv						 		#Load operator '/' to $t4
		lb $t5, opmod						 		#Load operator '%' to $t5
		
		beq $t0, $t1, prosthesi						#If input operator == '+' goto prosthesi
		beq $t0, $t2, afairesh						#If input operator == '-' goto afairesh
		beq $t0, $t3, pollaplasiasmos				#If input operator == '*' goto pollaplasiasmos
		beq $t0, $t4, diairesh						#If input operator == '/' goto diairesh
		beq $t0, $t5, mod							#If input operator == '%' goto mod
					
#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------										
													 
	prosthesi:
		lw $t0, Apotelesma							#Load Apotelesma to $t0
		lw $t1, B									#Load ton toulaxiston 2o arithmo eisagwghs ston $t1
		add $t0, $t0, $t1							#Praksh prostheshs
		sw $t0, Apotelesma							#Store to apotelesma ston $t0/ Apothikeush sthn mnhmh to apotelesma
		j operator									#Goto label operator
		
#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	afairesh: 
		lw $t0, Apotelesma							#Load Apotelesma to $t0
		lw $t1, B									#Load ton toulaxiston 2o arithmo eisagwghs ston $t1
		sub $t0, $t0, $t1							#Praksh afaireshs
		sw $t0, Apotelesma							#Store to apotelesma ston $t0/ Apothikeush sthn mnhmh to apotelesma
		j operator									#Goto label operator
		
#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	pollaplasiasmos: 
		lw $t0, Apotelesma							#Load Apotelesma to $t0
		lw $t1, B									#Load ton toulaxiston 2o arithmo eisagwghs ston $t1
		mul $t0, $t0, $t1							#Praksh pollaplasiasmou
		sw $t0, Apotelesma							#Store to apotelesma ston $t0/ Apothikeush sthn mnhmh to apotelesma
		j operator									#Goto label operator
		
#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	diairesh:
		lw $t0, Apotelesma							#Load Apotelesma to $t0
		lw $t1, B									#Load ton toulaxiston 2o arithmo eisagwghs ston $t1
		div $t0, $t0, $t1							#Praksh diaireshs
		sw $t0, Apotelesma							#Store to apotelesma ston $t0/ Apothikeush sthn mnhmh to apotelesma
		j operator									#Goto label operator
		
#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	mod: 
		lw $t0, Apotelesma							#Load Apotelesma to $t0
		lw $t1, B									#Load ton toulaxiston 2o arithmo eisagwghs ston $t1
		rem $t0, $t0, $t1							#Praksh mod
		sw $t0, Apotelesma							#Store to apotelesma ston $t0/ Apothikeush sthn mnhmh to apotelesma
		j operator									#Goto label operator
		
#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	mnm: 
		la $a0, str7								#System call code for printing string/ Emfanish "Error: Divide by zero. "
		li $v0, 4  	 
		syscall
		j exit										#Goto exit
		
#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
	exit:	
		li $v0, 10									#System call cod for ending the programm
		syscall	
		
#-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------			
		
			  .data
Apotelesma:   .space 4																#Int
B         :   .space 4																#Int
apantish  :   .space 1																#Character
op        :   .space 1																#Character
opeq      :   .byte '='																#Character
opplu     :   .byte '+'																#Character
opmin     :   .byte '-'																#Character
opmul     :   .byte '*'																#Character
opdiv     :   .byte '/'																#Character
opmod     :   .byte '%'																#Character
ap1       :   .byte 'y'																#Character
str1      :	  .asciiz "Number:"         											#String
str2      :   .asciiz "Operator:"												 	#String
str3      :   .asciiz "\nDo you want to continue with a new expression? (y/n) "		#String
str4      :   .asciiz "\nError: Invalid operator."									#String
str5      :   .asciiz "\nNumber:"													#String
str6      :   .asciiz "\nOperator:"													#String
str7      :   .asciiz "Error: Divide by zero."										#String
str8      :   .asciiz "\nThe result is:"											#String
str9      :   .asciiz "\n\n"														#String