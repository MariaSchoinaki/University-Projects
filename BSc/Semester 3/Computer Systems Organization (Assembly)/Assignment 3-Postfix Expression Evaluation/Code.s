# Onomatepwnumo: Schoinaki Maria
# A.M: 3210191
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        .text
        .globl main

main:
		li $t0, 0                               #Load ton arithmo 0 ston kataxwrhth $t0 / Stack counter
        la $a0, msg1                            #System call code for printing string / Emfanish msg1 / "Postfix (input): "
        li $v0, 4                       
        syscall

        la $a0, input                           #System call code for reading string / To Postfix Expression
        li $a1, 128                      
        li $v0, 8    
        syscall

        la $t1, input                           #Load to address tou Postfix Expression ston kataxwrhth $t1 / $t1 base register
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
start:     
        lb $t2, ($t1)                           #Load to next char tou Postfix Expression ston kataxwrhth $t2
        sb $t2, ch                              #Store to ch ston $t2 / Apothikeush sthn mnhmh to next char tou Postfix Expression 
        beq $t2, ' ', bwhile                    #If ch==' ' goto bwhile / An dhladh den einai arithmos h sumbolo prakshs 
        li $t3, 0                               #Load ton arithmo 0 ston kataxwrhth $t3 / Number = 0
        sw $t3, number                          #Store to number ston $t3 / Apothikeush sthn mnhmh to number
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------       
#Tha mpei se ayto to label an ch!=' '
#Elegxei an to ch einai arithmos kai an einai, ton metatrepei se int (ch-48) kai kanei mia loopa pou elegxei an to next char pou akolouthei
#sto postfix expression einai epishs arithmos, etsi wste ama einai shmainei oti einai 2pshfios, 3pshfios, ktlp kai ton metatrepei analoga
check:     
        blt $t2, '0', symbol                    #If ch<'0' goto symbol / An dhladh einai mikrotero tou 0 tote sigoura den einai arithmos
        bgt $t2, '9', symbol                    #If ch>'9' goto symbol / Tha mpei edo an ch>'0' kai ama einai KAI ch>'9' den einai arithmos
        mul $t3, $t3, 10                        #Praksh pollaplasiasmou / number = number * 10
        addi $t4, $t2, -48                      #Praksh prosthesis / ch -48
        add $t3, $t3, $t4                       #Praksh prosthesis / number = number + (ch-48)
        addi $t1, $t1, 1                        #Ston base register prostithetai o arithmos 1 (char) gia na allaksei to address(next char) 
        lb $t2, ($t1)                           #Load to next char tou Postfix Expression ston kataxwrhth $t2
        sb $t2, ch                              #Store to ch ston $t2 / Apothikeush sthn mnhmh to next char tou Postfix Expression
        j check                                 #Goto label check / loop
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
#Tha mpei edo mono an to ch den einai arithmos
symbol:
        beq $t2, '+', praksh                    #If ch=='+' goto praksh            
        beq $t2, '-', praksh                    #If ch=='-' goto praksh
        beq $t2, '*', praksh                    #If ch=='*' goto praksh
        beq $t2, '/', praksh                    #If ch=='/' goto praksh
        j elseif                                #Goto elseif / Shmainei oti perase oles tis if ara to ch den einai symbolo prakshs
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
#Tha mpei edo mono an to ch einai symbolo prakshs(dhladh isxue ena if sto label symbol)			
praksh:	
        jal pop                                 #Kaleitai h synarthsh pop
        sw $v0, x2                              #Store to x2 ston $v0 / Apothikeush sthn mnhmh to x2
        jal pop                                 #Kaleitai h synarthsh pop
        sw $v0, x1                              #Store to x1 ston $v0 / Apothikeush sthn mnhmh to x1
        lw $a0, x1                              #Load to x1 ston $a0 / Gia thn synarthsh
        lw $a2, x2                              #Load to x2 ston $a0 / Gia thn synarthsh                            
        move $a1, $t2                           #Move thn timh tou kataxwrhth $t2 ston kataxwrhth $a1 
        jal calc                                #Kaleitai h synarthsh calc
        move $a0, $v0                           #Move thn timh tou kataxwrhth $v0 ston kataxwrhth $a0
        jal push                                #Kaleitai h synarthsh push
        j bwhile                                #Goto bwhile 
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
#Tha mpei edo mono an to ch den einai oute arithmos oute symbolo praksis(ektos to ison)
elseif:
        beq $t2, '=', bwhile                    #If ch=='=' goto bwhile
        move $a0, $t3                           #Move thn timh tou kataxwrhth $t3 ston kataxwrhth $a0
        jal push                                #Kaleitai h synarthsh push
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
bwhile:
        beq $t2, '=', skip2                     #If ch=='=' goto skip2
        addi $t1, $t1, 1                        #Ston base register prostithetai o arithmos 1 (char) gia na allaksei to address(next char)
        j start                                 #Goto start
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
skip2:
        bne $t0, 1, error_msg                   #If stack counter != 1 goto error_msg 
		la $a0, msg2                            #System call code for printing string / Emfanish msg2 / "Postfix Evaluation: "
        li $v0, 4                       
        syscall

        jal pop                                 #Kaleitai h synarthsh pop
        move $a0, $v0                           #Move thn timh tou kataxwrhth $v0 ston kataxwrhth $a0

		li $v0, 1                               #System call code for printing int / Emfanish tou apotelesmatos
        syscall
		j exit									# Go to exit
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
error_msg:
		la $a0, msg4                            #System call code for printing string / Emfanish msg4 / "Invalid Postfix"
		li $v0, 4                       
		syscall		
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
		
exit:
        li $v0, 10                              #System call cod for ending the programm
        syscall
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
push:
        addi $sp, $sp, -4                       #Push sto stack thn timh tou kataxwrhth $a0
        sw $a0, ($sp)
		addi $t0, $t0, 1                        #Auksanetai o stack counter afou mphke ena stoixeio sto stack
        jr $ra                                  #Epistrofi synarthshs
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
pop:
        lw $v0, ($sp)                           #Pop apo to stack thn timh tou kataxwrhth $v0
        addi $sp, $sp, 4                        
		addi $t0, $t0, -1                       #Meiwnetai o stack counter afou bghke ena stoixeio apo to stack
        jr $ra                                  #Epistrofi synarthshs
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
calc:   
        li $t5, 0                               #Load ton arithmo 0 ston kataxwrhth $t5 / Apotelesma sygkekrimenhs praksis
        bne $a1, '+', case2                     #If ch!='+' goto case2 / An den einai prosthesi chekare an einai afairesi
        add $t5, $a0, $a2                       #Praksi ths prosthesis
        j endcalc                               #Goto endcalc
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
case2:  
        bne $a1, '-', case3                     #If ch!='-' goto case3 / An den einai oute afairesh chekare an einai pollaplasiasmos
        sub $t5, $a0, $a2                       #Praksi ths afairesis
        j endcalc                               #Goto endcalc
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
case3:  
        bne $a1, '*', case4                     #If ch!='*' goto case4 / An den einai oute pollaplasiasmos chekare an einai diairesh
        mul $t5, $a0, $a2                       #Praksi tou pollaplasiasmou
        j endcalc                               #Goto endcalc
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
case4:
        beq $a2, 0, div_by_zero                 #If x2==0 goto div_by_zero / dhladh an exoume diairesh me ton arithmo 0
        div $t5, $a0, $a2                       #Praksi ths diaireshs
        j endcalc                               #Goto endcalc
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------           
div_by_zero:  
        la $a0, msg3                            #System call code for printing string / Emfanish msg3 / "Divide by zero"
        li $v0, 4                               
        syscall

        j exit                                  #Goto to exit
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
endcalc:
        move $v0, $t5                           #Move thn timh tou kataxwrhth $t5 ston kataxwrhth $v0
        jr $ra                                  #Epistrofh synarthshs
#-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


        .data

input:      .space 128                          #Input String
ch:         .space 1		                	#Next Char
x1:         .word 0                             #Int
x2:         .word 0                             #Int
number:     .word 0                             #Int
msg1:       .asciiz "Postfix (input): "         #Message String
msg2:       .asciiz "Postfix Evaluation: "      #Message String
msg3:       .asciiz "Divide by zero"            #Message String
msg4:	    .asciiz "Invalid Postfix"           #Message String