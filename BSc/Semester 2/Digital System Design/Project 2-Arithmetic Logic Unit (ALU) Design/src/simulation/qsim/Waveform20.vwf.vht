-- Copyright (C) 2019  Intel Corporation. All rights reserved.
-- Your use of Intel Corporation's design tools, logic functions 
-- and other software and tools, and any partner logic 
-- functions, and any output files from any of the foregoing 
-- (including device programming or simulation files), and any 
-- associated documentation or information are expressly subject 
-- to the terms and conditions of the Intel Program License 
-- Subscription Agreement, the Intel Quartus Prime License Agreement,
-- the Intel FPGA IP License Agreement, or other applicable license
-- agreement, including, without limitation, that your use is for
-- the sole purpose of programming logic devices manufactured by
-- Intel and sold by Intel or its authorized distributors.  Please
-- refer to the applicable agreement for further details, at
-- https://fpgasoftware.intel.com/eula.

-- *****************************************************************************
-- This file contains a Vhdl test bench with test vectors .The test vectors     
-- are exported from a vector file in the Quartus Waveform Editor and apply to  
-- the top level entity of the current Quartus project .The user can use this   
-- testbench to simulate his design using a third-party simulation tool .       
-- *****************************************************************************
-- Generated on "06/04/2022 21:32:19"
                                                             
-- Vhdl Test Bench(with test vectors) for design  :          Project2
-- 
-- Simulation tool : 3rd Party
-- 

LIBRARY ieee;                                               
USE ieee.std_logic_1164.all;                                

ENTITY Project2_vhd_vec_tst IS
END Project2_vhd_vec_tst;
ARCHITECTURE Project2_arch OF Project2_vhd_vec_tst IS
-- constants                                                 
-- signals                                                   
SIGNAL A : STD_LOGIC;
SIGNAL AINVERT : STD_LOGIC;
SIGNAL B : STD_LOGIC;
SIGNAL BINVERT : STD_LOGIC;
SIGNAL CARRY_IN : STD_LOGIC;
SIGNAL CARRY_OUT : STD_LOGIC;
SIGNAL OPERATION : STD_LOGIC_VECTOR(1 DOWNTO 0);
SIGNAL RESULT : STD_LOGIC;
COMPONENT Project2
	PORT (
	A : IN STD_LOGIC;
	AINVERT : IN STD_LOGIC;
	B : IN STD_LOGIC;
	BINVERT : IN STD_LOGIC;
	CARRY_IN : IN STD_LOGIC;
	CARRY_OUT : OUT STD_LOGIC;
	OPERATION : IN STD_LOGIC_VECTOR(1 DOWNTO 0);
	RESULT : OUT STD_LOGIC
	);
END COMPONENT;
BEGIN
	i1 : Project2
	PORT MAP (
-- list connections between master ports and signals
	A => A,
	AINVERT => AINVERT,
	B => B,
	BINVERT => BINVERT,
	CARRY_IN => CARRY_IN,
	CARRY_OUT => CARRY_OUT,
	OPERATION => OPERATION,
	RESULT => RESULT
	);

-- A
t_prcs_A: PROCESS
BEGIN
	A <= '0';
	WAIT FOR 40000 ps;
	A <= '1';
WAIT;
END PROCESS t_prcs_A;

-- B
t_prcs_B: PROCESS
BEGIN
LOOP
	B <= '0';
	WAIT FOR 20000 ps;
	B <= '1';
	WAIT FOR 20000 ps;
	IF (NOW >= 80000 ps) THEN WAIT; END IF;
END LOOP;
END PROCESS t_prcs_B;

-- AINVERT
t_prcs_AINVERT: PROCESS
BEGIN
	AINVERT <= '1';
WAIT;
END PROCESS t_prcs_AINVERT;

-- BINVERT
t_prcs_BINVERT: PROCESS
BEGIN
	BINVERT <= '1';
WAIT;
END PROCESS t_prcs_BINVERT;

-- CARRY_IN
t_prcs_CARRY_IN: PROCESS
BEGIN
	CARRY_IN <= '0';
WAIT;
END PROCESS t_prcs_CARRY_IN;
-- OPERATION[1]
t_prcs_OPERATION_1: PROCESS
BEGIN
	OPERATION(1) <= '0';
WAIT;
END PROCESS t_prcs_OPERATION_1;
-- OPERATION[0]
t_prcs_OPERATION_0: PROCESS
BEGIN
	OPERATION(0) <= '1';
WAIT;
END PROCESS t_prcs_OPERATION_0;
END Project2_arch;
