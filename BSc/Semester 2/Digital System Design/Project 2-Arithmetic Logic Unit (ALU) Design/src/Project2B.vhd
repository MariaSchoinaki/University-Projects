-- 2o meros ergasias

-------------------------------------------CONTROL CIRCUIT-----------------------------------------------------
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

-- To control circuit analoga me to shma
-- tou opcode orizei ta carry_in, operation,
-- ainvert, binvert antistoixa
---------------------------------------------------------------------------------------------------------------
ENTITY CONTROL_CIRCUIT IS 
    PORT( OPCODE              : IN  STD_LOGIC_VECTOR(2 DOWNTO 0);
          CARRY_IN            : OUT  STD_LOGIC;
          OPERATION           : OUT  STD_LOGIC_VECTOR(1 DOWNTO 0);
          AINVERT, BINVERT    : OUT STD_LOGIC);
END CONTROL_CIRCUIT;

ARCHITECTURE STRUCT OF CONTROL_CIRCUIT IS
BEGIN
    CARRY_IN <= '1' WHEN OPCODE="011" ELSE
                '0';

    OPERATION <= "00" WHEN OPCODE="000" ELSE
					  "01" WHEN OPCODE="001" ELSE
                 "10" WHEN OPCODE="010" ELSE
                 "10" WHEN OPCODE="011" ELSE
                 "00" WHEN OPCODE="100" ELSE
                 "01" WHEN OPCODE="101" ELSE
                 "11" WHEN OPCODE="110" ELSE
                 "00";

    AINVERT <= '1' WHEN OPCODE="100" ELSE
               '1' WHEN OPCODE="101" ELSE
               '0';

    BINVERT <= '1' WHEN OPCODE="100" ELSE
               '1' WHEN OPCODE="101" ELSE
               '1' WHEN OPCODE="011" ELSE
               '0';
END STRUCT;

-------------------------------------------------OVERFLOW------------------------------------------------------
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

-- OVERFLAW
-- Elenxos uperxilishs :
-- Dhmiourgoume to ENTITY OVERFLAW me isodous 
-- to teleuteo kai to proigoumeno carry

ENTITY OVERFLOW1 IS 
    PORT(CIN1, CIN2     :IN  STD_LOGIC;
         RESULT1        :OUT STD_LOGIC);
END OVERFLOW1;

-- Se epipedo kataskebhs xrismopoioume thn prajh XOR 
-- me dynates logikes times 0,1
-- etsi mporoume na apofan8oume an uparxei uperxilhsh h oxi

ARCHITECTURE STATEMENT OF OVERFLOW1 IS
BEGIN   
	RESULT1 <= CIN1 XOR CIN2; 
END STATEMENT;

-- Se afto to simio opos sto proto meros ftanoume sthn dimiourgia tou kuriou kyklomatos
-- edo 8a xrismopoihsoume ta slices ths ALU tou 1ou merous me skopo na ftiajoume 1 olokliromeno
-- kikloma twn 16 bit

----------------------------------------------16-bit-ALU-------------------------------------------------------
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

-- To top level ENTITY project2B exei ws isodous to sima opcode , tis A,B
-- kai simata ejodou to overflow kai to result

ENTITY Project2B IS 
    PORT( OPCODE       					 : IN  STD_LOGIC_VECTOR(2 DOWNTO 0);
	       A, B        				    : IN  STD_LOGIC_VECTOR(15 DOWNTO 0);
			 OVERFLOW                   : OUT STD_LOGIC;
			 RESULT       					 : OUT STD_LOGIC_VECTOR(15 DOWNTO 0));
END Project2B;

ARCHITECTURE CONTROL OF Project2B IS

-- 1-BIT ALU COMPONENT
-- Ginetai h eisagwgh ths 1 bit ALU apo to prwto meros me thn morfh COMPONENT

    COMPONENT Project2 
         PORT(A, B, CARRY_IN     : IN  STD_LOGIC;
              OPERATION          : IN  STD_LOGIC_VECTOR(1 DOWNTO 0);
              AINVERT            : IN  STD_LOGIC;
              BINVERT            : IN  STD_LOGIC;
              CARRY_OUT, RESULT  : OUT STD_LOGIC);
    END COMPONENT;
	 
-- CONTROL CIRCUIT COMPONENT

	COMPONENT CONTROL_CIRCUIT IS 
		  PORT( OPCODE              : IN  STD_LOGIC_VECTOR(2 DOWNTO 0);
              CARRY_IN            : OUT  STD_LOGIC;
              OPERATION           : OUT  STD_LOGIC_VECTOR(1 DOWNTO 0);
              AINVERT, BINVERT    : OUT STD_LOGIC);
	END COMPONENT;

-- OVERFLAW

	COMPONENT OVERFLOW1
		  PORT( CIN1, CIN2     : IN STD_LOGIC;
              RESULT1        : OUT STD_LOGIC);
	END COMPONENT;
	 
---------------------------------------------------------------------------------------------------------------
-- Xisrimopoioume 16 slices ths ALU gia thn sun8esh tou kiklomatos

SIGNAL CARRY_OUT: STD_LOGIC_VECTOR(0 TO 15);
SIGNAL OPERATION: STD_LOGIC_VECTOR(1 DOWNTO 0);
SIGNAL CARRY_IN, AINVERT, BINVERT: STD_LOGIC;

BEGIN

-- Kaknoume mia epanalhpsi 15 plhuous wste na ypologistoun ta 16 slices
-- kai arxikopoioume to I0 me tis times tou cirquit_control kai kyriws to 1o carry_in
-- to carry_in kaue apomenoy slice einai to carry_in kaue epomenou

I0: CONTROL_CIRCUIT PORT MAP(OPCODE, CARRY_IN, OPERATION, AINVERT, BINVERT);
I1: Project2 PORT MAP(A(0), B(0), CARRY_IN, OPERATION, AINVERT, BINVERT, CARRY_OUT(0), RESULT(0));
I2: FOR I IN 1 TO 15 GENERATE 
		I3: Project2 PORT MAP (A(I), B(I), CARRY_OUT(I-1), OPERATION, AINVERT, BINVERT, CARRY_OUT(I), RESULT(I));  
	 END GENERATE;
I4: OVERFLOW1 PORT MAP (CARRY_OUT(14), CARRY_OUT(15), OVERFLOW);	 
END CONTROL;          