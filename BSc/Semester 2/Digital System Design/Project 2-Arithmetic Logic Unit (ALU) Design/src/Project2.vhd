-- 1o meros ergasias
 
-------------------------------------------------AND-----------------------------------------------------------
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

-- LOGIC AND
-- Se auto to komati dimiourgitai mia
-- logikh pulh AND me eisodous A,B
-- (oi kuries isodoi sto kikloma) 
-- kai orizetai h epakolou8h ejodos R 

ENTITY OPERATION1_AND IS
    PORT( A, B  : IN STD_LOGIC;
          R     : OUT STD_LOGIC);
END OPERATION1_AND;

-- To timima ARCHITECTURE OP1 ths logic AND 
-- ri8mizei thn leitourgikothta ths and ektelontas
-- thn prajh kai apo8hkeuontas thn sthn metablith R

ARCHITECTURE OP1 OF OPERATION1_AND IS
BEGIN
    R <= A AND B;
END OP1;

-----------------------------------------------------OR--------------------------------------------------------
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

-- LOGIC OR
--Paromios sto sigekrimeno ENTITY dimiourgoume 
--thn logic or me isodous A,B kai ejodo R 

ENTITY OPERATION2_OR IS
    PORT( A, B  : IN STD_LOGIC;
          R     : OUT STD_LOGIC);
END OPERATION2_OR;

-- ginetai ylopoihsh ths or

ARCHITECTURE OP2 OF OPERATION2_OR IS
--
BEGIN
    R <= A OR B;
END OP2;

-------------------------------------------------ADDER---------------------------------------------------------
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

-- Ena ajiosimioto tmhma tou programmatos einai o full ADDER :
-- Xrismopoiei tis kuries isodous A,B kai CARRY_IN
-- H CARRY_IN xrisimvei sthn diaxirish tou kratoumenou
-- Enw h CARRY_OUT metabibazei to kratoumeno ws deuterh ejodos

ENTITY OPERATION3_ADDER IS
    PORT ( A, B, CARRY_IN : IN STD_LOGIC;
           CARRY_OUT, R   : OUT STD_LOGIC);
END OPERATION3_ADDER;

--H kataskeuh tou full ADDER sunistatai apo:
--Tis isodous A,B,CARRY_IN
--Tis ejodous CARRY_OUT,R
--kai 2 logikes ejisoseis
--To apotelesma dinetai sth R eno to kratoumeno sthn CARRY_OUT;
 
ARCHITECTURE OP3 OF OPERATION3_ADDER IS
BEGIN
   CARRY_OUT <= (A AND B) OR (CARRY_IN AND A) OR (CARRY_IN AND B);
    R <= A XOR B XOR CARRY_IN;
END OP3;

---------------------------------------------------XOR---------------------------------------------------------
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

-- H logic XOR xrisimopoiei:
-- Idies isodous me thn logic AND
-- kai mia ejodo R

ENTITY OPERATION4_XOR IS
    PORT( A, B  : IN STD_LOGIC;
          R     : OUT STD_LOGIC);
END OPERATION4_XOR;

-- orizoume thn prajh kai kataxoroume
-- to apotelesma ths sthn metablith R

ARCHITECTURE OP4 OF OPERATION4_XOR IS
BEGIN
    R <= A XOR B;
END OP4;

-----------------------------------------MULTIPLEXER-2-TO-1----------------------------------------------------
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

-- edw orizetai o MULTIPLEXER me diastaseis 2x1
-- ka8os exei 1 sima xinvert to opoio orzei xrisths kai
-- mia isodo enos ari8mou x
-- ejodo apotelei h INVERTER

ENTITY MULT1 IS
    PORT ( X                    : IN STD_LOGIC;
           XINVERT              : IN STD_LOGIC;
           INVERTER             : OUT STD_LOGIC);
END MULT1;

-- H leitourgia tou orizetai ws ejis:
-- An o to sima pou dinei o xrisths einai iso me 1
-- Tote o ari8mos ginetai o anti8etos tou kai
-- kataxoritai sthn ejodo INVERTER

ARCHITECTURE STRUCTURAL OF MULT1 IS
BEGIN
    INVERTER <= NOT X WHEN XINVERT = '1' ELSE 
					 X;
END STRUCTURAL;

---------------------------------------MULTIPLEXER-4-TO-1------------------------------------------------------
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

-- To ENTITY MULT2 orizei enan multiplexer me diastaseis 4X1
-- Exei isodous R0,R1,R2,R3:
-- Ka8e mia apo autes exei antistixia:
-- ston adder sthn or kai stn xor
-- Paratiritai sima OPERATION twn 2 bits

ENTITY MULT2 IS
    PORT( R0, R1, R2, R3 : IN STD_LOGIC;
          OPERATION      : IN STD_LOGIC_VECTOR(1 DOWNTO 0) ;
          RESULT         : OUT STD_LOGIC);
END MULT2;

-- H antistixh kataskeuh apartizetai:
-- apo thn suntaxh ths entolhs when
-- me thn opoia ka8orizetai to apotelesma
-- ths ejodou

ARCHITECTURE STRUCTURAL OF MULT2 IS
BEGIN
    RESULT <= R0 WHEN OPERATION = "00" ELSE
              R1 WHEN OPERATION = "01" ELSE
              R2 WHEN OPERATION = "10" ELSE
              R3;
END STRUCTURAL;

-------------------------------------------------1-BIT-ALU-----------------------------------------------------
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

-- Se auto to simio dhmiourgoume to TOP-LEVEL ENTITY
-- Ka8orizontai oi kuries isodoi kai exodoi
-- simata xristh : operation

ENTITY Project2 IS
    PORT( A, B, CARRY_IN     : IN  STD_LOGIC;
          OPERATION          : IN  STD_LOGIC_VECTOR(1 DOWNTO 0);
          AINVERT            : IN  STD_LOGIC;
          BINVERT            : IN  STD_LOGIC;
          CARRY_OUT, RESULT  : OUT STD_LOGIC);
END Project2;

-- H arxitektonikh tou project2 orizetai parakatw
-- xrismopoioume components gia na isagoue tis leitourgies kai prajeis
-- pou orisame parapano kai sun8etoume ton tropo sunergasias twn ENTITY mas

ARCHITECTURE STRUCTURAL OF Project2 IS

-- COMPONENT 1

    COMPONENT OPERATION1_AND
        PORT ( A, B  : IN STD_LOGIC;
                R    : OUT STD_LOGIC);
    END COMPONENT;

-- COMPONENT 2

    COMPONENT OPERATION2_OR
        PORT ( A, B  : IN STD_LOGIC;
                R    : OUT STD_LOGIC);
    END COMPONENT;

-- COMPONENT 3

    COMPONENT OPERATION3_ADDER
        PORT ( A, B, CARRY_IN : IN STD_LOGIC ;
               CARRY_OUT, R   : OUT STD_LOGIC );
    END COMPONENT;

-- COMPONENT 4

    COMPONENT OPERATION4_XOR
        PORT ( A, B  : IN STD_LOGIC;
                R    : OUT STD_LOGIC);
    END COMPONENT;

-- COMPONENT 5

    COMPONENT MULT1
        PORT ( X                    : IN STD_LOGIC;
               XINVERT              : IN STD_LOGIC;
               INVERTER             : OUT STD_LOGIC);
   END COMPONENT;

-- COMPONENT 6

    COMPONENT MULT2
            PORT( R0, R1, R2, R3     : IN STD_LOGIC;
                  OPERATION          : IN STD_LOGIC_VECTOR(1 DOWNTO 0);
                  RESULT             : OUT STD_LOGIC);
    END COMPONENT;
---------------------------------------------------------------------------------------------------------------
-- orismos simatwn 

SIGNAL AINVERTER, BINVERTER, R0, R1, R2, R3: STD_LOGIC;

-- Sto sigekrimemo stadio arxizei na bainei se leitourgia to kurio programma mas
-- xrisimopohtai h entolh PORT MAP kai pragamatopohtai o elenxos tou ka8e component
-- antistixontas tis kataliles times kai metablites = 

BEGIN

I0: MULT1 PORT MAP(A, AINVERT, AINVERTER);
I1: MULT1 PORT MAP(B, BINVERT, BINVERTER);
I2: OPERATION1_AND PORT MAP(AINVERTER, BINVERTER, R0);
I3: OPERATION2_OR PORT MAP(AINVERTER, BINVERTER, R1);
I4: OPERATION3_ADDER PORT MAP (AINVERTER, BINVERTER, CARRY_IN, CARRY_OUT, R2);
I5: OPERATION4_XOR PORT MAP (AINVERTER, BINVERTER, R3);
I6: MULT2 PORT MAP(R0, R1, R2, R3, OPERATION, RESULT);

END STRUCTURAL;
