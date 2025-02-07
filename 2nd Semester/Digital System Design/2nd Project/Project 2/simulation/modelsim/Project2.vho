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

-- VENDOR "Altera"
-- PROGRAM "Quartus Prime"
-- VERSION "Version 19.1.0 Build 670 09/22/2019 SJ Lite Edition"

-- DATE "06/05/2022 19:55:19"

-- 
-- Device: Altera 5M40ZM64C4 Package MBGA64
-- 

-- 
-- This VHDL file should be used for ModelSim-Altera (VHDL) only
-- 

LIBRARY IEEE;
LIBRARY MAXV;
USE IEEE.STD_LOGIC_1164.ALL;
USE MAXV.MAXV_COMPONENTS.ALL;

ENTITY 	Project2 IS
    PORT (
	A : IN std_logic;
	B : IN std_logic;
	CARRY_IN : IN std_logic;
	OPERATION : IN std_logic_vector(1 DOWNTO 0);
	AINVERT : IN std_logic;
	BINVERT : IN std_logic;
	CARRY_OUT : BUFFER std_logic;
	RESULT : BUFFER std_logic
	);
END Project2;

-- Design Ports Information


ARCHITECTURE structure OF Project2 IS
SIGNAL gnd : std_logic := '0';
SIGNAL vcc : std_logic := '1';
SIGNAL unknown : std_logic := 'X';
SIGNAL devoe : std_logic := '1';
SIGNAL devclrn : std_logic := '1';
SIGNAL devpor : std_logic := '1';
SIGNAL ww_devoe : std_logic;
SIGNAL ww_devclrn : std_logic;
SIGNAL ww_devpor : std_logic;
SIGNAL ww_A : std_logic;
SIGNAL ww_B : std_logic;
SIGNAL ww_CARRY_IN : std_logic;
SIGNAL ww_OPERATION : std_logic_vector(1 DOWNTO 0);
SIGNAL ww_AINVERT : std_logic;
SIGNAL ww_BINVERT : std_logic;
SIGNAL ww_CARRY_OUT : std_logic;
SIGNAL ww_RESULT : std_logic;
SIGNAL \B~combout\ : std_logic;
SIGNAL \BINVERT~combout\ : std_logic;
SIGNAL \I1|INVERTER~0_combout\ : std_logic;
SIGNAL \CARRY_IN~combout\ : std_logic;
SIGNAL \A~combout\ : std_logic;
SIGNAL \AINVERT~combout\ : std_logic;
SIGNAL \I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I6|RESULT~0_combout\ : std_logic;
SIGNAL \I6|RESULT~1_combout\ : std_logic;
SIGNAL \I0|INVERTER~0_combout\ : std_logic;
SIGNAL \I6|RESULT~2_combout\ : std_logic;
SIGNAL \OPERATION~combout\ : std_logic_vector(1 DOWNTO 0);

BEGIN

ww_A <= A;
ww_B <= B;
ww_CARRY_IN <= CARRY_IN;
ww_OPERATION <= OPERATION;
ww_AINVERT <= AINVERT;
ww_BINVERT <= BINVERT;
CARRY_OUT <= ww_CARRY_OUT;
RESULT <= ww_RESULT;
ww_devoe <= devoe;
ww_devclrn <= devclrn;
ww_devpor <= devpor;

-- Location: PIN_D8,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B,
	combout => \B~combout\);

-- Location: PIN_B7,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\BINVERT~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_BINVERT,
	combout => \BINVERT~combout\);

-- Location: LC_X5_Y2_N8
\I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I1|INVERTER~0_combout\ = ((\B~combout\ $ (\BINVERT~combout\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0ff0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datac => \B~combout\,
	datad => \BINVERT~combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I1|INVERTER~0_combout\);

-- Location: PIN_F6,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\CARRY_IN~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_CARRY_IN,
	combout => \CARRY_IN~combout\);

-- Location: PIN_G7,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A,
	combout => \A~combout\);

-- Location: PIN_F8,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\AINVERT~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_AINVERT,
	combout => \AINVERT~combout\);

-- Location: LC_X5_Y2_N4
\I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I4|CARRY_OUT~0_combout\ = (\I1|INVERTER~0_combout\ & ((\CARRY_IN~combout\) # (\A~combout\ $ (\AINVERT~combout\)))) # (!\I1|INVERTER~0_combout\ & (\CARRY_IN~combout\ & (\A~combout\ $ (\AINVERT~combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "8ee8",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I1|INVERTER~0_combout\,
	datab => \CARRY_IN~combout\,
	datac => \A~combout\,
	datad => \AINVERT~combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I4|CARRY_OUT~0_combout\);

-- Location: PIN_F5,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\OPERATION[0]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_OPERATION(0),
	combout => \OPERATION~combout\(0));

-- Location: LC_X5_Y2_N6
\I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I6|RESULT~0_combout\ = \A~combout\ $ (\AINVERT~combout\ $ (\B~combout\ $ (\BINVERT~combout\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "6996",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \A~combout\,
	datab => \AINVERT~combout\,
	datac => \B~combout\,
	datad => \BINVERT~combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I6|RESULT~0_combout\);

-- Location: PIN_C8,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\OPERATION[1]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_OPERATION(1),
	combout => \OPERATION~combout\(1));

-- Location: LC_X5_Y2_N9
\I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I6|RESULT~1_combout\ = (\OPERATION~combout\(0) & (((\I6|RESULT~0_combout\) # (\OPERATION~combout\(1))))) # (!\OPERATION~combout\(0) & (!\CARRY_IN~combout\ & ((\OPERATION~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "bba0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPERATION~combout\(0),
	datab => \CARRY_IN~combout\,
	datac => \I6|RESULT~0_combout\,
	datad => \OPERATION~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I6|RESULT~1_combout\);

-- Location: LC_X5_Y2_N5
\I0|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I0|INVERTER~0_combout\ = ((\A~combout\ $ (\AINVERT~combout\)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0ff0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datac => \A~combout\,
	datad => \AINVERT~combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I0|INVERTER~0_combout\);

-- Location: LC_X5_Y2_N2
\I6|RESULT~2\ : maxv_lcell
-- Equation(s):
-- \I6|RESULT~2_combout\ = \I6|RESULT~1_combout\ $ (((\I1|INVERTER~0_combout\ & (\I0|INVERTER~0_combout\)) # (!\I1|INVERTER~0_combout\ & (!\I0|INVERTER~0_combout\ & \OPERATION~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "696c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I1|INVERTER~0_combout\,
	datab => \I6|RESULT~1_combout\,
	datac => \I0|INVERTER~0_combout\,
	datad => \OPERATION~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I6|RESULT~2_combout\);

-- Location: PIN_A6,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\CARRY_OUT~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I4|CARRY_OUT~0_combout\,
	oe => VCC,
	padio => ww_CARRY_OUT);

-- Location: PIN_H7,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I6|RESULT~2_combout\,
	oe => VCC,
	padio => ww_RESULT);
END structure;


