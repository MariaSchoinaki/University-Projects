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

-- DATE "06/05/2022 20:59:08"

-- 
-- Device: Altera 5M160ZT100C4 Package TQFP100
-- 

-- 
-- This VHDL file should be used for ModelSim-Altera (VHDL) only
-- 

LIBRARY IEEE;
LIBRARY MAXV;
USE IEEE.STD_LOGIC_1164.ALL;
USE MAXV.MAXV_COMPONENTS.ALL;

ENTITY 	Project2B IS
    PORT (
	OPCODE : IN std_logic_vector(2 DOWNTO 0);
	A : IN std_logic_vector(15 DOWNTO 0);
	B : IN std_logic_vector(15 DOWNTO 0);
	OVERFLOW : BUFFER std_logic;
	RESULT : BUFFER std_logic_vector(15 DOWNTO 0)
	);
END Project2B;

-- Design Ports Information


ARCHITECTURE structure OF Project2B IS
SIGNAL gnd : std_logic := '0';
SIGNAL vcc : std_logic := '1';
SIGNAL unknown : std_logic := 'X';
SIGNAL devoe : std_logic := '1';
SIGNAL devclrn : std_logic := '1';
SIGNAL devpor : std_logic := '1';
SIGNAL ww_devoe : std_logic;
SIGNAL ww_devclrn : std_logic;
SIGNAL ww_devpor : std_logic;
SIGNAL ww_OPCODE : std_logic_vector(2 DOWNTO 0);
SIGNAL ww_A : std_logic_vector(15 DOWNTO 0);
SIGNAL ww_B : std_logic_vector(15 DOWNTO 0);
SIGNAL ww_OVERFLOW : std_logic;
SIGNAL ww_RESULT : std_logic_vector(15 DOWNTO 0);
SIGNAL \I0|AINVERT~0_combout\ : std_logic;
SIGNAL \I2:15:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:14:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:11:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:10:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:9:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:8:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:7:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:6:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:4:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:3:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:1:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I0|Equal6~0_combout\ : std_logic;
SIGNAL \I1|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I2:1:I3|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I2:2:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:2:I3|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I2:3:I3|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I2:4:I3|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I2:5:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:5:I3|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I2:6:I3|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I2:7:I3|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I2:8:I3|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I2:9:I3|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I2:10:I3|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I2:11:I3|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I2:12:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:12:I3|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I2:13:I3|I1|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:13:I3|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I2:14:I3|I4|CARRY_OUT~0_combout\ : std_logic;
SIGNAL \I4|RESULT1~0_combout\ : std_logic;
SIGNAL \I1|I6|RESULT~2_combout\ : std_logic;
SIGNAL \I1|I6|RESULT~3_combout\ : std_logic;
SIGNAL \I1|I6|RESULT~4_combout\ : std_logic;
SIGNAL \I2:1:I3|I0|INVERTER~0_combout\ : std_logic;
SIGNAL \I0|OPERATION[0]~1_combout\ : std_logic;
SIGNAL \I2:1:I3|I5|R~0_combout\ : std_logic;
SIGNAL \I0|OPERATION[1]~0_combout\ : std_logic;
SIGNAL \I2:1:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:1:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \I2:2:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \I2:2:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:2:I3|I6|RESULT~2_combout\ : std_logic;
SIGNAL \I2:3:I3|I0|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:3:I3|I5|R~0_combout\ : std_logic;
SIGNAL \I2:3:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:3:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \I2:4:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \I2:4:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:4:I3|I6|RESULT~2_combout\ : std_logic;
SIGNAL \I2:5:I3|I0|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:5:I3|I5|R~0_combout\ : std_logic;
SIGNAL \I2:5:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:5:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \I2:6:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \I2:6:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:6:I3|I6|RESULT~2_combout\ : std_logic;
SIGNAL \I2:7:I3|I0|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:7:I3|I5|R~0_combout\ : std_logic;
SIGNAL \I2:7:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:7:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \I2:8:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \I2:8:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:8:I3|I6|RESULT~2_combout\ : std_logic;
SIGNAL \I2:9:I3|I0|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:9:I3|I5|R~0_combout\ : std_logic;
SIGNAL \I2:9:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:9:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \I2:10:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \I2:10:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:10:I3|I6|RESULT~2_combout\ : std_logic;
SIGNAL \I2:11:I3|I0|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:11:I3|I5|R~0_combout\ : std_logic;
SIGNAL \I2:11:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:11:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \I2:12:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \I2:12:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:12:I3|I6|RESULT~2_combout\ : std_logic;
SIGNAL \I2:13:I3|I0|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:13:I3|I5|R~0_combout\ : std_logic;
SIGNAL \I2:13:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:13:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \I2:14:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \I2:14:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:14:I3|I6|RESULT~2_combout\ : std_logic;
SIGNAL \I2:15:I3|I0|INVERTER~0_combout\ : std_logic;
SIGNAL \I2:15:I3|I5|R~0_combout\ : std_logic;
SIGNAL \I2:15:I3|I6|RESULT~0_combout\ : std_logic;
SIGNAL \I2:15:I3|I6|RESULT~1_combout\ : std_logic;
SIGNAL \OPCODE~combout\ : std_logic_vector(2 DOWNTO 0);
SIGNAL \A~combout\ : std_logic_vector(15 DOWNTO 0);
SIGNAL \B~combout\ : std_logic_vector(15 DOWNTO 0);

BEGIN

ww_OPCODE <= OPCODE;
ww_A <= A;
ww_B <= B;
OVERFLOW <= ww_OVERFLOW;
RESULT <= ww_RESULT;
ww_devoe <= devoe;
ww_devclrn <= devclrn;
ww_devpor <= devpor;

-- Location: PIN_20,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\OPCODE[2]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_OPCODE(2),
	combout => \OPCODE~combout\(2));

-- Location: PIN_100,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\OPCODE[1]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_OPCODE(1),
	combout => \OPCODE~combout\(1));

-- Location: LC_X4_Y2_N1
\I0|AINVERT~0\ : maxv_lcell
-- Equation(s):
-- \I0|AINVERT~0_combout\ = (((\OPCODE~combout\(2) & !\OPCODE~combout\(1))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "00f0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datac => \OPCODE~combout\(2),
	datad => \OPCODE~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I0|AINVERT~0_combout\);

-- Location: PIN_96,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[15]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(15),
	combout => \B~combout\(15));

-- Location: PIN_42,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\OPCODE[0]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_OPCODE(0),
	combout => \OPCODE~combout\(0));

-- Location: LC_X4_Y4_N9
\I2:15:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:15:I3|I1|INVERTER~0_combout\ = \B~combout\(15) $ (((\OPCODE~combout\(1) & (!\OPCODE~combout\(2) & \OPCODE~combout\(0))) # (!\OPCODE~combout\(1) & (\OPCODE~combout\(2)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "969c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(1),
	datab => \B~combout\(15),
	datac => \OPCODE~combout\(2),
	datad => \OPCODE~combout\(0),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:15:I3|I1|INVERTER~0_combout\);

-- Location: PIN_17,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[14]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(14),
	combout => \B~combout\(14));

-- Location: LC_X4_Y2_N8
\I2:14:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:14:I3|I1|INVERTER~0_combout\ = \B~combout\(14) $ (((\OPCODE~combout\(2) & ((!\OPCODE~combout\(1)))) # (!\OPCODE~combout\(2) & (\OPCODE~combout\(0) & \OPCODE~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "c63c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(0),
	datab => \B~combout\(14),
	datac => \OPCODE~combout\(2),
	datad => \OPCODE~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:14:I3|I1|INVERTER~0_combout\);

-- Location: PIN_29,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[11]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(11),
	combout => \B~combout\(11));

-- Location: LC_X2_Y2_N8
\I2:11:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:11:I3|I1|INVERTER~0_combout\ = \B~combout\(11) $ (((\OPCODE~combout\(1) & (!\OPCODE~combout\(2) & \OPCODE~combout\(0))) # (!\OPCODE~combout\(1) & (\OPCODE~combout\(2)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "969c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(1),
	datab => \B~combout\(11),
	datac => \OPCODE~combout\(2),
	datad => \OPCODE~combout\(0),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:11:I3|I1|INVERTER~0_combout\);

-- Location: PIN_16,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[11]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(11),
	combout => \A~combout\(11));

-- Location: PIN_48,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[10]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(10),
	combout => \A~combout\(10));

-- Location: PIN_66,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[10]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(10),
	combout => \B~combout\(10));

-- Location: LC_X4_Y2_N3
\I2:10:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:10:I3|I1|INVERTER~0_combout\ = \B~combout\(10) $ (((\OPCODE~combout\(1) & (\OPCODE~combout\(0) & !\OPCODE~combout\(2))) # (!\OPCODE~combout\(1) & ((\OPCODE~combout\(2))))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "c378",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(0),
	datab => \OPCODE~combout\(1),
	datac => \B~combout\(10),
	datad => \OPCODE~combout\(2),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:10:I3|I1|INVERTER~0_combout\);

-- Location: PIN_61,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[9]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(9),
	combout => \B~combout\(9));

-- Location: LC_X5_Y2_N8
\I2:9:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:9:I3|I1|INVERTER~0_combout\ = \B~combout\(9) $ (((\OPCODE~combout\(1) & (\OPCODE~combout\(0) & !\OPCODE~combout\(2))) # (!\OPCODE~combout\(1) & ((\OPCODE~combout\(2))))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "c378",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(0),
	datab => \OPCODE~combout\(1),
	datac => \B~combout\(9),
	datad => \OPCODE~combout\(2),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:9:I3|I1|INVERTER~0_combout\);

-- Location: PIN_12,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[8]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(8),
	combout => \A~combout\(8));

-- Location: PIN_57,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[8]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(8),
	combout => \B~combout\(8));

-- Location: LC_X4_Y2_N9
\I2:8:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:8:I3|I1|INVERTER~0_combout\ = \B~combout\(8) $ (((\OPCODE~combout\(1) & (\OPCODE~combout\(0) & !\OPCODE~combout\(2))) # (!\OPCODE~combout\(1) & ((\OPCODE~combout\(2))))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "c378",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(0),
	datab => \OPCODE~combout\(1),
	datac => \B~combout\(8),
	datad => \OPCODE~combout\(2),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:8:I3|I1|INVERTER~0_combout\);

-- Location: PIN_97,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[7]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(7),
	combout => \B~combout\(7));

-- Location: LC_X3_Y2_N8
\I2:7:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:7:I3|I1|INVERTER~0_combout\ = \B~combout\(7) $ (((\OPCODE~combout\(2) & ((!\OPCODE~combout\(1)))) # (!\OPCODE~combout\(2) & (\OPCODE~combout\(0) & \OPCODE~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "9a66",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \B~combout\(7),
	datab => \OPCODE~combout\(2),
	datac => \OPCODE~combout\(0),
	datad => \OPCODE~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:7:I3|I1|INVERTER~0_combout\);

-- Location: PIN_33,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[7]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(7),
	combout => \A~combout\(7));

-- Location: PIN_40,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[6]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(6),
	combout => \B~combout\(6));

-- Location: LC_X4_Y2_N0
\I2:6:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:6:I3|I1|INVERTER~0_combout\ = \B~combout\(6) $ (((\OPCODE~combout\(1) & (\OPCODE~combout\(0) & !\OPCODE~combout\(2))) # (!\OPCODE~combout\(1) & ((\OPCODE~combout\(2))))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "c738",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(0),
	datab => \OPCODE~combout\(1),
	datac => \OPCODE~combout\(2),
	datad => \B~combout\(6),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:6:I3|I1|INVERTER~0_combout\);

-- Location: PIN_98,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[4]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(4),
	combout => \B~combout\(4));

-- Location: LC_X2_Y4_N6
\I2:4:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:4:I3|I1|INVERTER~0_combout\ = \B~combout\(4) $ (((\OPCODE~combout\(2) & ((!\OPCODE~combout\(1)))) # (!\OPCODE~combout\(2) & (\OPCODE~combout\(0) & \OPCODE~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "a65a",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \B~combout\(4),
	datab => \OPCODE~combout\(0),
	datac => \OPCODE~combout\(2),
	datad => \OPCODE~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:4:I3|I1|INVERTER~0_combout\);

-- Location: PIN_3,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[4]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(4),
	combout => \A~combout\(4));

-- Location: PIN_87,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[3]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(3),
	combout => \B~combout\(3));

-- Location: LC_X4_Y3_N2
\I2:3:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:3:I3|I1|INVERTER~0_combout\ = \B~combout\(3) $ (((\OPCODE~combout\(1) & (\OPCODE~combout\(0) & !\OPCODE~combout\(2))) # (!\OPCODE~combout\(1) & ((\OPCODE~combout\(2))))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "c738",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(0),
	datab => \OPCODE~combout\(1),
	datac => \OPCODE~combout\(2),
	datad => \B~combout\(3),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:3:I3|I1|INVERTER~0_combout\);

-- Location: PIN_34,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[3]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(3),
	combout => \A~combout\(3));

-- Location: PIN_99,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[2]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(2),
	combout => \A~combout\(2));

-- Location: PIN_38,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[1]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(1),
	combout => \B~combout\(1));

-- Location: LC_X4_Y1_N2
\I2:1:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:1:I3|I1|INVERTER~0_combout\ = \B~combout\(1) $ (((\OPCODE~combout\(2) & (!\OPCODE~combout\(1))) # (!\OPCODE~combout\(2) & (\OPCODE~combout\(1) & \OPCODE~combout\(0)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "96a6",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \B~combout\(1),
	datab => \OPCODE~combout\(2),
	datac => \OPCODE~combout\(1),
	datad => \OPCODE~combout\(0),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:1:I3|I1|INVERTER~0_combout\);

-- Location: PIN_36,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[0]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(0),
	combout => \A~combout\(0));

-- Location: LC_X4_Y2_N2
\I0|Equal6~0\ : maxv_lcell
-- Equation(s):
-- \I0|Equal6~0_combout\ = ((\OPCODE~combout\(1) & (!\OPCODE~combout\(2) & \OPCODE~combout\(0))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0c00",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datab => \OPCODE~combout\(1),
	datac => \OPCODE~combout\(2),
	datad => \OPCODE~combout\(0),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I0|Equal6~0_combout\);

-- Location: PIN_41,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[0]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(0),
	combout => \B~combout\(0));

-- Location: LC_X4_Y1_N0
\I1|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I1|I4|CARRY_OUT~0_combout\ = (\B~combout\(0) & ((\A~combout\(0) & ((!\I0|AINVERT~0_combout\))) # (!\A~combout\(0) & (\I0|Equal6~0_combout\ & \I0|AINVERT~0_combout\)))) # (!\B~combout\(0) & ((\I0|Equal6~0_combout\) # ((!\A~combout\(0) & 
-- \I0|AINVERT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "4dac",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \A~combout\(0),
	datab => \I0|Equal6~0_combout\,
	datac => \B~combout\(0),
	datad => \I0|AINVERT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I1|I4|CARRY_OUT~0_combout\);

-- Location: PIN_56,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[1]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(1),
	combout => \A~combout\(1));

-- Location: LC_X4_Y1_N8
\I2:1:I3|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I2:1:I3|I4|CARRY_OUT~0_combout\ = (\I2:1:I3|I1|INVERTER~0_combout\ & ((\I1|I4|CARRY_OUT~0_combout\) # (\I0|AINVERT~0_combout\ $ (\A~combout\(1))))) # (!\I2:1:I3|I1|INVERTER~0_combout\ & (\I1|I4|CARRY_OUT~0_combout\ & (\I0|AINVERT~0_combout\ $ 
-- (\A~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "d4e8",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|AINVERT~0_combout\,
	datab => \I2:1:I3|I1|INVERTER~0_combout\,
	datac => \I1|I4|CARRY_OUT~0_combout\,
	datad => \A~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:1:I3|I4|CARRY_OUT~0_combout\);

-- Location: PIN_58,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[2]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(2),
	combout => \B~combout\(2));

-- Location: LC_X4_Y2_N6
\I2:2:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:2:I3|I1|INVERTER~0_combout\ = \B~combout\(2) $ (((\OPCODE~combout\(1) & (\OPCODE~combout\(0) & !\OPCODE~combout\(2))) # (!\OPCODE~combout\(1) & ((\OPCODE~combout\(2))))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "c738",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(0),
	datab => \OPCODE~combout\(1),
	datac => \OPCODE~combout\(2),
	datad => \B~combout\(2),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:2:I3|I1|INVERTER~0_combout\);

-- Location: LC_X4_Y3_N3
\I2:2:I3|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I2:2:I3|I4|CARRY_OUT~0_combout\ = (\I2:1:I3|I4|CARRY_OUT~0_combout\ & ((\I2:2:I3|I1|INVERTER~0_combout\) # (\A~combout\(2) $ (\I0|AINVERT~0_combout\)))) # (!\I2:1:I3|I4|CARRY_OUT~0_combout\ & (\I2:2:I3|I1|INVERTER~0_combout\ & (\A~combout\(2) $ 
-- (\I0|AINVERT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "d4e8",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \A~combout\(2),
	datab => \I2:1:I3|I4|CARRY_OUT~0_combout\,
	datac => \I2:2:I3|I1|INVERTER~0_combout\,
	datad => \I0|AINVERT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:2:I3|I4|CARRY_OUT~0_combout\);

-- Location: LC_X4_Y3_N4
\I2:3:I3|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I2:3:I3|I4|CARRY_OUT~0_combout\ = (\I2:3:I3|I1|INVERTER~0_combout\ & ((\I2:2:I3|I4|CARRY_OUT~0_combout\) # (\I0|AINVERT~0_combout\ $ (\A~combout\(3))))) # (!\I2:3:I3|I1|INVERTER~0_combout\ & (\I2:2:I3|I4|CARRY_OUT~0_combout\ & (\I0|AINVERT~0_combout\ $ 
-- (\A~combout\(3)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "de48",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|AINVERT~0_combout\,
	datab => \I2:3:I3|I1|INVERTER~0_combout\,
	datac => \A~combout\(3),
	datad => \I2:2:I3|I4|CARRY_OUT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:3:I3|I4|CARRY_OUT~0_combout\);

-- Location: LC_X2_Y4_N8
\I2:4:I3|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I2:4:I3|I4|CARRY_OUT~0_combout\ = (\I2:4:I3|I1|INVERTER~0_combout\ & ((\I2:3:I3|I4|CARRY_OUT~0_combout\) # (\I0|AINVERT~0_combout\ $ (\A~combout\(4))))) # (!\I2:4:I3|I1|INVERTER~0_combout\ & (\I2:3:I3|I4|CARRY_OUT~0_combout\ & (\I0|AINVERT~0_combout\ $ 
-- (\A~combout\(4)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "be28",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:4:I3|I1|INVERTER~0_combout\,
	datab => \I0|AINVERT~0_combout\,
	datac => \A~combout\(4),
	datad => \I2:3:I3|I4|CARRY_OUT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:4:I3|I4|CARRY_OUT~0_combout\);

-- Location: PIN_2,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[5]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(5),
	combout => \B~combout\(5));

-- Location: LC_X5_Y4_N5
\I2:5:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:5:I3|I1|INVERTER~0_combout\ = \B~combout\(5) $ (((\OPCODE~combout\(1) & (!\OPCODE~combout\(2) & \OPCODE~combout\(0))) # (!\OPCODE~combout\(1) & (\OPCODE~combout\(2)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "9b64",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(1),
	datab => \OPCODE~combout\(2),
	datac => \OPCODE~combout\(0),
	datad => \B~combout\(5),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:5:I3|I1|INVERTER~0_combout\);

-- Location: PIN_86,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[5]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(5),
	combout => \A~combout\(5));

-- Location: LC_X5_Y4_N1
\I2:5:I3|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I2:5:I3|I4|CARRY_OUT~0_combout\ = (\I2:4:I3|I4|CARRY_OUT~0_combout\ & ((\I2:5:I3|I1|INVERTER~0_combout\) # (\I0|AINVERT~0_combout\ $ (\A~combout\(5))))) # (!\I2:4:I3|I4|CARRY_OUT~0_combout\ & (\I2:5:I3|I1|INVERTER~0_combout\ & (\I0|AINVERT~0_combout\ $ 
-- (\A~combout\(5)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "d4e8",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|AINVERT~0_combout\,
	datab => \I2:4:I3|I4|CARRY_OUT~0_combout\,
	datac => \I2:5:I3|I1|INVERTER~0_combout\,
	datad => \A~combout\(5),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:5:I3|I4|CARRY_OUT~0_combout\);

-- Location: PIN_88,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[6]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(6),
	combout => \A~combout\(6));

-- Location: LC_X5_Y4_N2
\I2:6:I3|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I2:6:I3|I4|CARRY_OUT~0_combout\ = (\I2:6:I3|I1|INVERTER~0_combout\ & ((\I2:5:I3|I4|CARRY_OUT~0_combout\) # (\A~combout\(6) $ (\I0|AINVERT~0_combout\)))) # (!\I2:6:I3|I1|INVERTER~0_combout\ & (\I2:5:I3|I4|CARRY_OUT~0_combout\ & (\A~combout\(6) $ 
-- (\I0|AINVERT~0_combout\))))

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
	dataa => \I2:6:I3|I1|INVERTER~0_combout\,
	datab => \I2:5:I3|I4|CARRY_OUT~0_combout\,
	datac => \A~combout\(6),
	datad => \I0|AINVERT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:6:I3|I4|CARRY_OUT~0_combout\);

-- Location: LC_X3_Y2_N7
\I2:7:I3|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I2:7:I3|I4|CARRY_OUT~0_combout\ = (\I2:7:I3|I1|INVERTER~0_combout\ & ((\I2:6:I3|I4|CARRY_OUT~0_combout\) # (\A~combout\(7) $ (\I0|AINVERT~0_combout\)))) # (!\I2:7:I3|I1|INVERTER~0_combout\ & (\I2:6:I3|I4|CARRY_OUT~0_combout\ & (\A~combout\(7) $ 
-- (\I0|AINVERT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "b2e8",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:7:I3|I1|INVERTER~0_combout\,
	datab => \A~combout\(7),
	datac => \I2:6:I3|I4|CARRY_OUT~0_combout\,
	datad => \I0|AINVERT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:7:I3|I4|CARRY_OUT~0_combout\);

-- Location: LC_X3_Y2_N1
\I2:8:I3|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I2:8:I3|I4|CARRY_OUT~0_combout\ = (\I2:8:I3|I1|INVERTER~0_combout\ & ((\I2:7:I3|I4|CARRY_OUT~0_combout\) # (\A~combout\(8) $ (\I0|AINVERT~0_combout\)))) # (!\I2:8:I3|I1|INVERTER~0_combout\ & (\I2:7:I3|I4|CARRY_OUT~0_combout\ & (\A~combout\(8) $ 
-- (\I0|AINVERT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "d4e8",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \A~combout\(8),
	datab => \I2:8:I3|I1|INVERTER~0_combout\,
	datac => \I2:7:I3|I4|CARRY_OUT~0_combout\,
	datad => \I0|AINVERT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:8:I3|I4|CARRY_OUT~0_combout\);

-- Location: PIN_69,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[9]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(9),
	combout => \A~combout\(9));

-- Location: LC_X5_Y2_N1
\I2:9:I3|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I2:9:I3|I4|CARRY_OUT~0_combout\ = (\I2:9:I3|I1|INVERTER~0_combout\ & ((\I2:8:I3|I4|CARRY_OUT~0_combout\) # (\I0|AINVERT~0_combout\ $ (\A~combout\(9))))) # (!\I2:9:I3|I1|INVERTER~0_combout\ & (\I2:8:I3|I4|CARRY_OUT~0_combout\ & (\I0|AINVERT~0_combout\ $ 
-- (\A~combout\(9)))))

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
	dataa => \I2:9:I3|I1|INVERTER~0_combout\,
	datab => \I2:8:I3|I4|CARRY_OUT~0_combout\,
	datac => \I0|AINVERT~0_combout\,
	datad => \A~combout\(9),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:9:I3|I4|CARRY_OUT~0_combout\);

-- Location: LC_X5_Y2_N3
\I2:10:I3|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I2:10:I3|I4|CARRY_OUT~0_combout\ = (\I2:10:I3|I1|INVERTER~0_combout\ & ((\I2:9:I3|I4|CARRY_OUT~0_combout\) # (\A~combout\(10) $ (\I0|AINVERT~0_combout\)))) # (!\I2:10:I3|I1|INVERTER~0_combout\ & (\I2:9:I3|I4|CARRY_OUT~0_combout\ & (\A~combout\(10) $ 
-- (\I0|AINVERT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "de48",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \A~combout\(10),
	datab => \I2:10:I3|I1|INVERTER~0_combout\,
	datac => \I0|AINVERT~0_combout\,
	datad => \I2:9:I3|I4|CARRY_OUT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:10:I3|I4|CARRY_OUT~0_combout\);

-- Location: LC_X2_Y2_N4
\I2:11:I3|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I2:11:I3|I4|CARRY_OUT~0_combout\ = (\I2:11:I3|I1|INVERTER~0_combout\ & ((\I2:10:I3|I4|CARRY_OUT~0_combout\) # (\A~combout\(11) $ (\I0|AINVERT~0_combout\)))) # (!\I2:11:I3|I1|INVERTER~0_combout\ & (\I2:10:I3|I4|CARRY_OUT~0_combout\ & (\A~combout\(11) $ 
-- (\I0|AINVERT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "be28",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:11:I3|I1|INVERTER~0_combout\,
	datab => \A~combout\(11),
	datac => \I0|AINVERT~0_combout\,
	datad => \I2:10:I3|I4|CARRY_OUT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:11:I3|I4|CARRY_OUT~0_combout\);

-- Location: PIN_15,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[12]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(12),
	combout => \A~combout\(12));

-- Location: PIN_39,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[12]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(12),
	combout => \B~combout\(12));

-- Location: LC_X4_Y2_N4
\I2:12:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:12:I3|I1|INVERTER~0_combout\ = \B~combout\(12) $ (((\OPCODE~combout\(1) & (\OPCODE~combout\(0) & !\OPCODE~combout\(2))) # (!\OPCODE~combout\(1) & ((\OPCODE~combout\(2))))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "c738",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(0),
	datab => \OPCODE~combout\(1),
	datac => \OPCODE~combout\(2),
	datad => \B~combout\(12),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:12:I3|I1|INVERTER~0_combout\);

-- Location: LC_X2_Y2_N2
\I2:12:I3|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I2:12:I3|I4|CARRY_OUT~0_combout\ = (\I2:11:I3|I4|CARRY_OUT~0_combout\ & ((\I2:12:I3|I1|INVERTER~0_combout\) # (\A~combout\(12) $ (\I0|AINVERT~0_combout\)))) # (!\I2:11:I3|I4|CARRY_OUT~0_combout\ & (\I2:12:I3|I1|INVERTER~0_combout\ & (\A~combout\(12) $ 
-- (\I0|AINVERT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "b2e8",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:11:I3|I4|CARRY_OUT~0_combout\,
	datab => \A~combout\(12),
	datac => \I2:12:I3|I1|INVERTER~0_combout\,
	datad => \I0|AINVERT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:12:I3|I4|CARRY_OUT~0_combout\);

-- Location: PIN_6,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[13]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(13),
	combout => \A~combout\(13));

-- Location: PIN_7,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\B[13]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_B(13),
	combout => \B~combout\(13));

-- Location: LC_X3_Y3_N2
\I2:13:I3|I1|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:13:I3|I1|INVERTER~0_combout\ = \B~combout\(13) $ (((\OPCODE~combout\(1) & (!\OPCODE~combout\(2) & \OPCODE~combout\(0))) # (!\OPCODE~combout\(1) & (\OPCODE~combout\(2)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "96b4",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(1),
	datab => \OPCODE~combout\(2),
	datac => \B~combout\(13),
	datad => \OPCODE~combout\(0),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:13:I3|I1|INVERTER~0_combout\);

-- Location: LC_X3_Y3_N9
\I2:13:I3|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I2:13:I3|I4|CARRY_OUT~0_combout\ = (\I2:12:I3|I4|CARRY_OUT~0_combout\ & ((\I2:13:I3|I1|INVERTER~0_combout\) # (\I0|AINVERT~0_combout\ $ (\A~combout\(13))))) # (!\I2:12:I3|I4|CARRY_OUT~0_combout\ & (\I2:13:I3|I1|INVERTER~0_combout\ & 
-- (\I0|AINVERT~0_combout\ $ (\A~combout\(13)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "de48",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|AINVERT~0_combout\,
	datab => \I2:12:I3|I4|CARRY_OUT~0_combout\,
	datac => \A~combout\(13),
	datad => \I2:13:I3|I1|INVERTER~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:13:I3|I4|CARRY_OUT~0_combout\);

-- Location: PIN_8,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[14]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(14),
	combout => \A~combout\(14));

-- Location: LC_X3_Y3_N6
\I2:14:I3|I4|CARRY_OUT~0\ : maxv_lcell
-- Equation(s):
-- \I2:14:I3|I4|CARRY_OUT~0_combout\ = (\I2:14:I3|I1|INVERTER~0_combout\ & ((\I2:13:I3|I4|CARRY_OUT~0_combout\) # (\I0|AINVERT~0_combout\ $ (\A~combout\(14))))) # (!\I2:14:I3|I1|INVERTER~0_combout\ & (\I2:13:I3|I4|CARRY_OUT~0_combout\ & 
-- (\I0|AINVERT~0_combout\ $ (\A~combout\(14)))))

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
	dataa => \I2:14:I3|I1|INVERTER~0_combout\,
	datab => \I2:13:I3|I4|CARRY_OUT~0_combout\,
	datac => \I0|AINVERT~0_combout\,
	datad => \A~combout\(14),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:14:I3|I4|CARRY_OUT~0_combout\);

-- Location: PIN_91,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: Default
\A[15]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "input")
-- pragma translate_on
PORT MAP (
	oe => GND,
	padio => ww_A(15),
	combout => \A~combout\(15));

-- Location: LC_X4_Y4_N2
\I4|RESULT1~0\ : maxv_lcell
-- Equation(s):
-- \I4|RESULT1~0_combout\ = (\I2:15:I3|I1|INVERTER~0_combout\ & (!\I2:14:I3|I4|CARRY_OUT~0_combout\ & (\I0|AINVERT~0_combout\ $ (\A~combout\(15))))) # (!\I2:15:I3|I1|INVERTER~0_combout\ & (\I2:14:I3|I4|CARRY_OUT~0_combout\ & (\I0|AINVERT~0_combout\ $ 
-- (!\A~combout\(15)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "2418",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|AINVERT~0_combout\,
	datab => \I2:15:I3|I1|INVERTER~0_combout\,
	datac => \I2:14:I3|I4|CARRY_OUT~0_combout\,
	datad => \A~combout\(15),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I4|RESULT1~0_combout\);

-- Location: LC_X4_Y1_N7
\I1|I6|RESULT~2\ : maxv_lcell
-- Equation(s):
-- \I1|I6|RESULT~2_combout\ = (\A~combout\(0) & ((\OPCODE~combout\(1) & ((!\OPCODE~combout\(0)) # (!\OPCODE~combout\(2)))) # (!\OPCODE~combout\(1) & ((\OPCODE~combout\(0)))))) # (!\A~combout\(0) & (\OPCODE~combout\(2) & (!\OPCODE~combout\(1))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "2ea4",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \A~combout\(0),
	datab => \OPCODE~combout\(2),
	datac => \OPCODE~combout\(1),
	datad => \OPCODE~combout\(0),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I1|I6|RESULT~2_combout\);

-- Location: LC_X4_Y1_N4
\I1|I6|RESULT~3\ : maxv_lcell
-- Equation(s):
-- \I1|I6|RESULT~3_combout\ = (\OPCODE~combout\(1) & (\A~combout\(0) $ (((!\OPCODE~combout\(0)) # (!\OPCODE~combout\(2)))))) # (!\OPCODE~combout\(1) & ((\A~combout\(0) & (!\OPCODE~combout\(2))) # (!\A~combout\(0) & ((\OPCODE~combout\(0))))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "9752",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \A~combout\(0),
	datab => \OPCODE~combout\(2),
	datac => \OPCODE~combout\(1),
	datad => \OPCODE~combout\(0),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I1|I6|RESULT~3_combout\);

-- Location: LC_X4_Y1_N5
\I1|I6|RESULT~4\ : maxv_lcell
-- Equation(s):
-- \I1|I6|RESULT~4_combout\ = (\B~combout\(0) & (((\I1|I6|RESULT~3_combout\)))) # (!\B~combout\(0) & (((\I1|I6|RESULT~2_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "fa50",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \B~combout\(0),
	datac => \I1|I6|RESULT~2_combout\,
	datad => \I1|I6|RESULT~3_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I1|I6|RESULT~4_combout\);

-- Location: LC_X4_Y1_N3
\I2:1:I3|I0|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:1:I3|I0|INVERTER~0_combout\ = (\A~combout\(1) $ (((\OPCODE~combout\(2) & !\OPCODE~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "f30c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datab => \OPCODE~combout\(2),
	datac => \OPCODE~combout\(1),
	datad => \A~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:1:I3|I0|INVERTER~0_combout\);

-- Location: LC_X4_Y2_N7
\I0|OPERATION[0]~1\ : maxv_lcell
-- Equation(s):
-- \I0|OPERATION[0]~1_combout\ = ((\OPCODE~combout\(1) & ((\OPCODE~combout\(0)) # (!\OPCODE~combout\(2)))) # (!\OPCODE~combout\(1) & ((!\OPCODE~combout\(0)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "cc3f",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datab => \OPCODE~combout\(1),
	datac => \OPCODE~combout\(2),
	datad => \OPCODE~combout\(0),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I0|OPERATION[0]~1_combout\);

-- Location: LC_X4_Y1_N9
\I2:1:I3|I5|R~0\ : maxv_lcell
-- Equation(s):
-- \I2:1:I3|I5|R~0_combout\ = \B~combout\(1) $ (\A~combout\(1) $ (((!\I0|AINVERT~0_combout\ & \I0|Equal6~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "4bb4",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|AINVERT~0_combout\,
	datab => \I0|Equal6~0_combout\,
	datac => \B~combout\(1),
	datad => \A~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:1:I3|I5|R~0_combout\);

-- Location: LC_X4_Y2_N5
\I0|OPERATION[1]~0\ : maxv_lcell
-- Equation(s):
-- \I0|OPERATION[1]~0_combout\ = (((\OPCODE~combout\(2) & \OPCODE~combout\(0))) # (!\OPCODE~combout\(1)))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "f333",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datab => \OPCODE~combout\(1),
	datac => \OPCODE~combout\(2),
	datad => \OPCODE~combout\(0),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I0|OPERATION[1]~0_combout\);

-- Location: LC_X4_Y1_N1
\I2:1:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:1:I3|I6|RESULT~0_combout\ = (\I0|OPERATION[1]~0_combout\ & (!\I0|OPERATION[0]~1_combout\)) # (!\I0|OPERATION[1]~0_combout\ & (\I2:1:I3|I5|R~0_combout\ $ (((\I0|OPERATION[0]~1_combout\ & \I1|I4|CARRY_OUT~0_combout\)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "565c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|OPERATION[0]~1_combout\,
	datab => \I2:1:I3|I5|R~0_combout\,
	datac => \I0|OPERATION[1]~0_combout\,
	datad => \I1|I4|CARRY_OUT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:1:I3|I6|RESULT~0_combout\);

-- Location: LC_X4_Y1_N6
\I2:1:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:1:I3|I6|RESULT~1_combout\ = (\I2:1:I3|I0|INVERTER~0_combout\ & ((\I2:1:I3|I6|RESULT~0_combout\) # ((\I0|OPERATION[1]~0_combout\ & \I2:1:I3|I1|INVERTER~0_combout\)))) # (!\I2:1:I3|I0|INVERTER~0_combout\ & (\I2:1:I3|I6|RESULT~0_combout\ & 
-- ((\I2:1:I3|I1|INVERTER~0_combout\) # (!\I0|OPERATION[1]~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "ec8c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:1:I3|I0|INVERTER~0_combout\,
	datab => \I2:1:I3|I6|RESULT~0_combout\,
	datac => \I0|OPERATION[1]~0_combout\,
	datad => \I2:1:I3|I1|INVERTER~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:1:I3|I6|RESULT~1_combout\);

-- Location: LC_X4_Y3_N1
\I2:2:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:2:I3|I6|RESULT~1_combout\ = (\I0|OPERATION[0]~1_combout\ & ((\I2:1:I3|I4|CARRY_OUT~0_combout\) # ((\I0|OPERATION[1]~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "aa88",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|OPERATION[0]~1_combout\,
	datab => \I2:1:I3|I4|CARRY_OUT~0_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:2:I3|I6|RESULT~1_combout\);

-- Location: LC_X4_Y3_N9
\I2:2:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:2:I3|I6|RESULT~0_combout\ = \A~combout\(2) $ ((((\OPCODE~combout\(2) & !\OPCODE~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "aa5a",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \A~combout\(2),
	datac => \OPCODE~combout\(2),
	datad => \OPCODE~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:2:I3|I6|RESULT~0_combout\);

-- Location: LC_X4_Y3_N8
\I2:2:I3|I6|RESULT~2\ : maxv_lcell
-- Equation(s):
-- \I2:2:I3|I6|RESULT~2_combout\ = (\I0|OPERATION[1]~0_combout\ & ((\I2:2:I3|I6|RESULT~1_combout\ & (\I2:2:I3|I1|INVERTER~0_combout\ & \I2:2:I3|I6|RESULT~0_combout\)) # (!\I2:2:I3|I6|RESULT~1_combout\ & ((\I2:2:I3|I1|INVERTER~0_combout\) # 
-- (\I2:2:I3|I6|RESULT~0_combout\))))) # (!\I0|OPERATION[1]~0_combout\ & (\I2:2:I3|I6|RESULT~1_combout\ $ (\I2:2:I3|I1|INVERTER~0_combout\ $ (\I2:2:I3|I6|RESULT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "e334",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|OPERATION[1]~0_combout\,
	datab => \I2:2:I3|I6|RESULT~1_combout\,
	datac => \I2:2:I3|I1|INVERTER~0_combout\,
	datad => \I2:2:I3|I6|RESULT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:2:I3|I6|RESULT~2_combout\);

-- Location: LC_X4_Y3_N6
\I2:3:I3|I0|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:3:I3|I0|INVERTER~0_combout\ = (\A~combout\(3) $ (((!\OPCODE~combout\(1) & \OPCODE~combout\(2)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "cf30",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datab => \OPCODE~combout\(1),
	datac => \OPCODE~combout\(2),
	datad => \A~combout\(3),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:3:I3|I0|INVERTER~0_combout\);

-- Location: LC_X4_Y3_N0
\I2:3:I3|I5|R~0\ : maxv_lcell
-- Equation(s):
-- \I2:3:I3|I5|R~0_combout\ = \A~combout\(3) $ (\B~combout\(3) $ (((!\I0|AINVERT~0_combout\ & \I0|Equal6~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "639c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|AINVERT~0_combout\,
	datab => \A~combout\(3),
	datac => \I0|Equal6~0_combout\,
	datad => \B~combout\(3),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:3:I3|I5|R~0_combout\);

-- Location: LC_X4_Y3_N7
\I2:3:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:3:I3|I6|RESULT~0_combout\ = (\I0|OPERATION[1]~0_combout\ & (!\I0|OPERATION[0]~1_combout\)) # (!\I0|OPERATION[1]~0_combout\ & (\I2:3:I3|I5|R~0_combout\ $ (((\I0|OPERATION[0]~1_combout\ & \I2:2:I3|I4|CARRY_OUT~0_combout\)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "5674",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|OPERATION[0]~1_combout\,
	datab => \I0|OPERATION[1]~0_combout\,
	datac => \I2:3:I3|I5|R~0_combout\,
	datad => \I2:2:I3|I4|CARRY_OUT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:3:I3|I6|RESULT~0_combout\);

-- Location: LC_X4_Y3_N5
\I2:3:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:3:I3|I6|RESULT~1_combout\ = (\I2:3:I3|I0|INVERTER~0_combout\ & ((\I2:3:I3|I6|RESULT~0_combout\) # ((\I0|OPERATION[1]~0_combout\ & \I2:3:I3|I1|INVERTER~0_combout\)))) # (!\I2:3:I3|I0|INVERTER~0_combout\ & (\I2:3:I3|I6|RESULT~0_combout\ & 
-- ((\I2:3:I3|I1|INVERTER~0_combout\) # (!\I0|OPERATION[1]~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "f8b0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:3:I3|I0|INVERTER~0_combout\,
	datab => \I0|OPERATION[1]~0_combout\,
	datac => \I2:3:I3|I6|RESULT~0_combout\,
	datad => \I2:3:I3|I1|INVERTER~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:3:I3|I6|RESULT~1_combout\);

-- Location: LC_X2_Y4_N2
\I2:4:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:4:I3|I6|RESULT~1_combout\ = ((\I0|OPERATION[0]~1_combout\ & ((\I0|OPERATION[1]~0_combout\) # (\I2:3:I3|I4|CARRY_OUT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "f0c0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datab => \I0|OPERATION[1]~0_combout\,
	datac => \I0|OPERATION[0]~1_combout\,
	datad => \I2:3:I3|I4|CARRY_OUT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:4:I3|I6|RESULT~1_combout\);

-- Location: LC_X2_Y4_N5
\I2:4:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:4:I3|I6|RESULT~0_combout\ = (\A~combout\(4) $ (((\OPCODE~combout\(2) & !\OPCODE~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "f05a",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(2),
	datac => \A~combout\(4),
	datad => \OPCODE~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:4:I3|I6|RESULT~0_combout\);

-- Location: LC_X2_Y4_N4
\I2:4:I3|I6|RESULT~2\ : maxv_lcell
-- Equation(s):
-- \I2:4:I3|I6|RESULT~2_combout\ = (\I2:4:I3|I1|INVERTER~0_combout\ & ((\I2:4:I3|I6|RESULT~1_combout\ & (\I2:4:I3|I6|RESULT~0_combout\)) # (!\I2:4:I3|I6|RESULT~1_combout\ & ((\I0|OPERATION[1]~0_combout\) # (!\I2:4:I3|I6|RESULT~0_combout\))))) # 
-- (!\I2:4:I3|I1|INVERTER~0_combout\ & ((\I2:4:I3|I6|RESULT~1_combout\ & (!\I2:4:I3|I6|RESULT~0_combout\ & !\I0|OPERATION[1]~0_combout\)) # (!\I2:4:I3|I6|RESULT~1_combout\ & (\I2:4:I3|I6|RESULT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "b296",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:4:I3|I1|INVERTER~0_combout\,
	datab => \I2:4:I3|I6|RESULT~1_combout\,
	datac => \I2:4:I3|I6|RESULT~0_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:4:I3|I6|RESULT~2_combout\);

-- Location: LC_X5_Y4_N0
\I2:5:I3|I0|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:5:I3|I0|INVERTER~0_combout\ = (\A~combout\(5) $ (((!\OPCODE~combout\(1) & \OPCODE~combout\(2)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "af50",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(1),
	datac => \OPCODE~combout\(2),
	datad => \A~combout\(5),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:5:I3|I0|INVERTER~0_combout\);

-- Location: LC_X5_Y4_N9
\I2:5:I3|I5|R~0\ : maxv_lcell
-- Equation(s):
-- \I2:5:I3|I5|R~0_combout\ = \B~combout\(5) $ (\A~combout\(5) $ (((!\I0|AINVERT~0_combout\ & \I0|Equal6~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "639c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|AINVERT~0_combout\,
	datab => \B~combout\(5),
	datac => \I0|Equal6~0_combout\,
	datad => \A~combout\(5),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:5:I3|I5|R~0_combout\);

-- Location: LC_X5_Y4_N4
\I2:5:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:5:I3|I6|RESULT~0_combout\ = (\I0|OPERATION[1]~0_combout\ & (((!\I0|OPERATION[0]~1_combout\)))) # (!\I0|OPERATION[1]~0_combout\ & (\I2:5:I3|I5|R~0_combout\ $ (((\I2:4:I3|I4|CARRY_OUT~0_combout\ & \I0|OPERATION[0]~1_combout\)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "1f4a",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|OPERATION[1]~0_combout\,
	datab => \I2:4:I3|I4|CARRY_OUT~0_combout\,
	datac => \I0|OPERATION[0]~1_combout\,
	datad => \I2:5:I3|I5|R~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:5:I3|I6|RESULT~0_combout\);

-- Location: LC_X5_Y4_N6
\I2:5:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:5:I3|I6|RESULT~1_combout\ = (\I0|OPERATION[1]~0_combout\ & ((\I2:5:I3|I0|INVERTER~0_combout\ & ((\I2:5:I3|I6|RESULT~0_combout\) # (\I2:5:I3|I1|INVERTER~0_combout\))) # (!\I2:5:I3|I0|INVERTER~0_combout\ & (\I2:5:I3|I6|RESULT~0_combout\ & 
-- \I2:5:I3|I1|INVERTER~0_combout\)))) # (!\I0|OPERATION[1]~0_combout\ & (((\I2:5:I3|I6|RESULT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "f8d0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|OPERATION[1]~0_combout\,
	datab => \I2:5:I3|I0|INVERTER~0_combout\,
	datac => \I2:5:I3|I6|RESULT~0_combout\,
	datad => \I2:5:I3|I1|INVERTER~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:5:I3|I6|RESULT~1_combout\);

-- Location: LC_X5_Y4_N7
\I2:6:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:6:I3|I6|RESULT~1_combout\ = ((\I0|OPERATION[0]~1_combout\ & ((\I0|OPERATION[1]~0_combout\) # (\I2:5:I3|I4|CARRY_OUT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "f0a0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|OPERATION[1]~0_combout\,
	datac => \I0|OPERATION[0]~1_combout\,
	datad => \I2:5:I3|I4|CARRY_OUT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:6:I3|I6|RESULT~1_combout\);

-- Location: LC_X5_Y4_N8
\I2:6:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:6:I3|I6|RESULT~0_combout\ = \A~combout\(6) $ (((!\OPCODE~combout\(1) & (\OPCODE~combout\(2)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "9c9c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(1),
	datab => \A~combout\(6),
	datac => \OPCODE~combout\(2),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:6:I3|I6|RESULT~0_combout\);

-- Location: LC_X5_Y4_N3
\I2:6:I3|I6|RESULT~2\ : maxv_lcell
-- Equation(s):
-- \I2:6:I3|I6|RESULT~2_combout\ = (\I0|OPERATION[1]~0_combout\ & ((\I2:6:I3|I6|RESULT~1_combout\ & (\I2:6:I3|I1|INVERTER~0_combout\ & \I2:6:I3|I6|RESULT~0_combout\)) # (!\I2:6:I3|I6|RESULT~1_combout\ & ((\I2:6:I3|I1|INVERTER~0_combout\) # 
-- (\I2:6:I3|I6|RESULT~0_combout\))))) # (!\I0|OPERATION[1]~0_combout\ & (\I2:6:I3|I6|RESULT~1_combout\ $ (\I2:6:I3|I1|INVERTER~0_combout\ $ (\I2:6:I3|I6|RESULT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "e334",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|OPERATION[1]~0_combout\,
	datab => \I2:6:I3|I6|RESULT~1_combout\,
	datac => \I2:6:I3|I1|INVERTER~0_combout\,
	datad => \I2:6:I3|I6|RESULT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:6:I3|I6|RESULT~2_combout\);

-- Location: LC_X3_Y2_N9
\I2:7:I3|I0|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:7:I3|I0|INVERTER~0_combout\ = \A~combout\(7) $ (((\OPCODE~combout\(2) & (!\OPCODE~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "a6a6",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \A~combout\(7),
	datab => \OPCODE~combout\(2),
	datac => \OPCODE~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:7:I3|I0|INVERTER~0_combout\);

-- Location: LC_X3_Y2_N2
\I2:7:I3|I5|R~0\ : maxv_lcell
-- Equation(s):
-- \I2:7:I3|I5|R~0_combout\ = \B~combout\(7) $ (\A~combout\(7) $ (((\I0|Equal6~0_combout\ & !\I0|AINVERT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "5a96",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \B~combout\(7),
	datab => \I0|Equal6~0_combout\,
	datac => \A~combout\(7),
	datad => \I0|AINVERT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:7:I3|I5|R~0_combout\);

-- Location: LC_X3_Y2_N6
\I2:7:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:7:I3|I6|RESULT~0_combout\ = (\I0|OPERATION[1]~0_combout\ & (((!\I0|OPERATION[0]~1_combout\)))) # (!\I0|OPERATION[1]~0_combout\ & (\I2:7:I3|I5|R~0_combout\ $ (((\I2:6:I3|I4|CARRY_OUT~0_combout\ & \I0|OPERATION[0]~1_combout\)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0f6c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:6:I3|I4|CARRY_OUT~0_combout\,
	datab => \I2:7:I3|I5|R~0_combout\,
	datac => \I0|OPERATION[0]~1_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:7:I3|I6|RESULT~0_combout\);

-- Location: LC_X3_Y2_N4
\I2:7:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:7:I3|I6|RESULT~1_combout\ = (\I2:7:I3|I1|INVERTER~0_combout\ & ((\I2:7:I3|I6|RESULT~0_combout\) # ((\I2:7:I3|I0|INVERTER~0_combout\ & \I0|OPERATION[1]~0_combout\)))) # (!\I2:7:I3|I1|INVERTER~0_combout\ & (\I2:7:I3|I6|RESULT~0_combout\ & 
-- ((\I2:7:I3|I0|INVERTER~0_combout\) # (!\I0|OPERATION[1]~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "e8f0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:7:I3|I1|INVERTER~0_combout\,
	datab => \I2:7:I3|I0|INVERTER~0_combout\,
	datac => \I2:7:I3|I6|RESULT~0_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:7:I3|I6|RESULT~1_combout\);

-- Location: LC_X3_Y2_N3
\I2:8:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:8:I3|I6|RESULT~1_combout\ = ((\I0|OPERATION[0]~1_combout\ & ((\I2:7:I3|I4|CARRY_OUT~0_combout\) # (\I0|OPERATION[1]~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "ccc0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datab => \I0|OPERATION[0]~1_combout\,
	datac => \I2:7:I3|I4|CARRY_OUT~0_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:8:I3|I6|RESULT~1_combout\);

-- Location: LC_X3_Y2_N5
\I2:8:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:8:I3|I6|RESULT~0_combout\ = (\A~combout\(8) $ (((\OPCODE~combout\(2) & !\OPCODE~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "f30c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datab => \OPCODE~combout\(2),
	datac => \OPCODE~combout\(1),
	datad => \A~combout\(8),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:8:I3|I6|RESULT~0_combout\);

-- Location: LC_X3_Y2_N0
\I2:8:I3|I6|RESULT~2\ : maxv_lcell
-- Equation(s):
-- \I2:8:I3|I6|RESULT~2_combout\ = (\I2:8:I3|I6|RESULT~1_combout\ & ((\I2:8:I3|I1|INVERTER~0_combout\ & (\I2:8:I3|I6|RESULT~0_combout\)) # (!\I2:8:I3|I1|INVERTER~0_combout\ & (!\I2:8:I3|I6|RESULT~0_combout\ & !\I0|OPERATION[1]~0_combout\)))) # 
-- (!\I2:8:I3|I6|RESULT~1_combout\ & ((\I2:8:I3|I1|INVERTER~0_combout\ & ((\I0|OPERATION[1]~0_combout\) # (!\I2:8:I3|I6|RESULT~0_combout\))) # (!\I2:8:I3|I1|INVERTER~0_combout\ & (\I2:8:I3|I6|RESULT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "d496",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:8:I3|I6|RESULT~1_combout\,
	datab => \I2:8:I3|I1|INVERTER~0_combout\,
	datac => \I2:8:I3|I6|RESULT~0_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:8:I3|I6|RESULT~2_combout\);

-- Location: LC_X5_Y2_N9
\I2:9:I3|I0|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:9:I3|I0|INVERTER~0_combout\ = (\A~combout\(9) $ (((!\OPCODE~combout\(1) & \OPCODE~combout\(2)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "cf30",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datab => \OPCODE~combout\(1),
	datac => \OPCODE~combout\(2),
	datad => \A~combout\(9),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:9:I3|I0|INVERTER~0_combout\);

-- Location: LC_X5_Y2_N5
\I2:9:I3|I5|R~0\ : maxv_lcell
-- Equation(s):
-- \I2:9:I3|I5|R~0_combout\ = \B~combout\(9) $ (\A~combout\(9) $ (((\I0|Equal6~0_combout\ & !\I0|AINVERT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "59a6",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \B~combout\(9),
	datab => \I0|Equal6~0_combout\,
	datac => \I0|AINVERT~0_combout\,
	datad => \A~combout\(9),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:9:I3|I5|R~0_combout\);

-- Location: LC_X5_Y2_N6
\I2:9:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:9:I3|I6|RESULT~0_combout\ = (\I0|OPERATION[1]~0_combout\ & (!\I0|OPERATION[0]~1_combout\)) # (!\I0|OPERATION[1]~0_combout\ & (\I2:9:I3|I5|R~0_combout\ $ (((\I0|OPERATION[0]~1_combout\ & \I2:8:I3|I4|CARRY_OUT~0_combout\)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "3672",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|OPERATION[1]~0_combout\,
	datab => \I0|OPERATION[0]~1_combout\,
	datac => \I2:9:I3|I5|R~0_combout\,
	datad => \I2:8:I3|I4|CARRY_OUT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:9:I3|I6|RESULT~0_combout\);

-- Location: LC_X5_Y2_N0
\I2:9:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:9:I3|I6|RESULT~1_combout\ = (\I0|OPERATION[1]~0_combout\ & ((\I2:9:I3|I0|INVERTER~0_combout\ & ((\I2:9:I3|I6|RESULT~0_combout\) # (\I2:9:I3|I1|INVERTER~0_combout\))) # (!\I2:9:I3|I0|INVERTER~0_combout\ & (\I2:9:I3|I6|RESULT~0_combout\ & 
-- \I2:9:I3|I1|INVERTER~0_combout\)))) # (!\I0|OPERATION[1]~0_combout\ & (((\I2:9:I3|I6|RESULT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "f8d0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|OPERATION[1]~0_combout\,
	datab => \I2:9:I3|I0|INVERTER~0_combout\,
	datac => \I2:9:I3|I6|RESULT~0_combout\,
	datad => \I2:9:I3|I1|INVERTER~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:9:I3|I6|RESULT~1_combout\);

-- Location: LC_X5_Y2_N4
\I2:10:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:10:I3|I6|RESULT~1_combout\ = (\I0|OPERATION[0]~1_combout\ & ((\I0|OPERATION[1]~0_combout\) # ((\I2:9:I3|I4|CARRY_OUT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "cc88",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|OPERATION[1]~0_combout\,
	datab => \I0|OPERATION[0]~1_combout\,
	datad => \I2:9:I3|I4|CARRY_OUT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:10:I3|I6|RESULT~1_combout\);

-- Location: LC_X5_Y2_N2
\I2:10:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:10:I3|I6|RESULT~0_combout\ = \A~combout\(10) $ (((!\OPCODE~combout\(1) & (\OPCODE~combout\(2)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "9a9a",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \A~combout\(10),
	datab => \OPCODE~combout\(1),
	datac => \OPCODE~combout\(2),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:10:I3|I6|RESULT~0_combout\);

-- Location: LC_X5_Y2_N7
\I2:10:I3|I6|RESULT~2\ : maxv_lcell
-- Equation(s):
-- \I2:10:I3|I6|RESULT~2_combout\ = (\I0|OPERATION[1]~0_combout\ & ((\I2:10:I3|I1|INVERTER~0_combout\ & ((\I2:10:I3|I6|RESULT~0_combout\) # (!\I2:10:I3|I6|RESULT~1_combout\))) # (!\I2:10:I3|I1|INVERTER~0_combout\ & (!\I2:10:I3|I6|RESULT~1_combout\ & 
-- \I2:10:I3|I6|RESULT~0_combout\)))) # (!\I0|OPERATION[1]~0_combout\ & (\I2:10:I3|I1|INVERTER~0_combout\ $ (\I2:10:I3|I6|RESULT~1_combout\ $ (\I2:10:I3|I6|RESULT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "cb1c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|OPERATION[1]~0_combout\,
	datab => \I2:10:I3|I1|INVERTER~0_combout\,
	datac => \I2:10:I3|I6|RESULT~1_combout\,
	datad => \I2:10:I3|I6|RESULT~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:10:I3|I6|RESULT~2_combout\);

-- Location: LC_X2_Y2_N1
\I2:11:I3|I0|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:11:I3|I0|INVERTER~0_combout\ = (\A~combout\(11) $ (((\OPCODE~combout\(2) & !\OPCODE~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "cc3c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datab => \A~combout\(11),
	datac => \OPCODE~combout\(2),
	datad => \OPCODE~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:11:I3|I0|INVERTER~0_combout\);

-- Location: LC_X2_Y2_N9
\I2:11:I3|I5|R~0\ : maxv_lcell
-- Equation(s):
-- \I2:11:I3|I5|R~0_combout\ = \A~combout\(11) $ (\B~combout\(11) $ (((!\I0|AINVERT~0_combout\ & \I0|Equal6~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "693c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|AINVERT~0_combout\,
	datab => \A~combout\(11),
	datac => \B~combout\(11),
	datad => \I0|Equal6~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:11:I3|I5|R~0_combout\);

-- Location: LC_X2_Y2_N6
\I2:11:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:11:I3|I6|RESULT~0_combout\ = (\I0|OPERATION[1]~0_combout\ & (((!\I0|OPERATION[0]~1_combout\)))) # (!\I0|OPERATION[1]~0_combout\ & (\I2:11:I3|I5|R~0_combout\ $ (((\I2:10:I3|I4|CARRY_OUT~0_combout\ & \I0|OPERATION[0]~1_combout\)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0f6c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:10:I3|I4|CARRY_OUT~0_combout\,
	datab => \I2:11:I3|I5|R~0_combout\,
	datac => \I0|OPERATION[0]~1_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:11:I3|I6|RESULT~0_combout\);

-- Location: LC_X2_Y2_N0
\I2:11:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:11:I3|I6|RESULT~1_combout\ = (\I2:11:I3|I1|INVERTER~0_combout\ & ((\I2:11:I3|I6|RESULT~0_combout\) # ((\I2:11:I3|I0|INVERTER~0_combout\ & \I0|OPERATION[1]~0_combout\)))) # (!\I2:11:I3|I1|INVERTER~0_combout\ & (\I2:11:I3|I6|RESULT~0_combout\ & 
-- ((\I2:11:I3|I0|INVERTER~0_combout\) # (!\I0|OPERATION[1]~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "e8f0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:11:I3|I1|INVERTER~0_combout\,
	datab => \I2:11:I3|I0|INVERTER~0_combout\,
	datac => \I2:11:I3|I6|RESULT~0_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:11:I3|I6|RESULT~1_combout\);

-- Location: LC_X2_Y2_N3
\I2:12:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:12:I3|I6|RESULT~1_combout\ = ((\I0|OPERATION[0]~1_combout\ & ((\I2:11:I3|I4|CARRY_OUT~0_combout\) # (\I0|OPERATION[1]~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "ccc0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datab => \I0|OPERATION[0]~1_combout\,
	datac => \I2:11:I3|I4|CARRY_OUT~0_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:12:I3|I6|RESULT~1_combout\);

-- Location: LC_X2_Y2_N7
\I2:12:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:12:I3|I6|RESULT~0_combout\ = (\A~combout\(12) $ (((\OPCODE~combout\(2) & !\OPCODE~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "f05a",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(2),
	datac => \A~combout\(12),
	datad => \OPCODE~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:12:I3|I6|RESULT~0_combout\);

-- Location: LC_X2_Y2_N5
\I2:12:I3|I6|RESULT~2\ : maxv_lcell
-- Equation(s):
-- \I2:12:I3|I6|RESULT~2_combout\ = (\I2:12:I3|I6|RESULT~1_combout\ & ((\I2:12:I3|I6|RESULT~0_combout\ & (\I2:12:I3|I1|INVERTER~0_combout\)) # (!\I2:12:I3|I6|RESULT~0_combout\ & (!\I2:12:I3|I1|INVERTER~0_combout\ & !\I0|OPERATION[1]~0_combout\)))) # 
-- (!\I2:12:I3|I6|RESULT~1_combout\ & ((\I2:12:I3|I6|RESULT~0_combout\ & ((\I0|OPERATION[1]~0_combout\) # (!\I2:12:I3|I1|INVERTER~0_combout\))) # (!\I2:12:I3|I6|RESULT~0_combout\ & (\I2:12:I3|I1|INVERTER~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "d496",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:12:I3|I6|RESULT~1_combout\,
	datab => \I2:12:I3|I6|RESULT~0_combout\,
	datac => \I2:12:I3|I1|INVERTER~0_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:12:I3|I6|RESULT~2_combout\);

-- Location: LC_X3_Y3_N3
\I2:13:I3|I0|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:13:I3|I0|INVERTER~0_combout\ = \A~combout\(13) $ (((\OPCODE~combout\(2) & (!\OPCODE~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "a6a6",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \A~combout\(13),
	datab => \OPCODE~combout\(2),
	datac => \OPCODE~combout\(1),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:13:I3|I0|INVERTER~0_combout\);

-- Location: LC_X3_Y3_N8
\I2:13:I3|I5|R~0\ : maxv_lcell
-- Equation(s):
-- \I2:13:I3|I5|R~0_combout\ = \A~combout\(13) $ (\B~combout\(13) $ (((!\I0|AINVERT~0_combout\ & \I0|Equal6~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "6966",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \A~combout\(13),
	datab => \B~combout\(13),
	datac => \I0|AINVERT~0_combout\,
	datad => \I0|Equal6~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:13:I3|I5|R~0_combout\);

-- Location: LC_X3_Y3_N0
\I2:13:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:13:I3|I6|RESULT~0_combout\ = (\I0|OPERATION[1]~0_combout\ & (((!\I0|OPERATION[0]~1_combout\)))) # (!\I0|OPERATION[1]~0_combout\ & (\I2:13:I3|I5|R~0_combout\ $ (((\I2:12:I3|I4|CARRY_OUT~0_combout\ & \I0|OPERATION[0]~1_combout\)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "0f6a",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:13:I3|I5|R~0_combout\,
	datab => \I2:12:I3|I4|CARRY_OUT~0_combout\,
	datac => \I0|OPERATION[0]~1_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:13:I3|I6|RESULT~0_combout\);

-- Location: LC_X3_Y3_N4
\I2:13:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:13:I3|I6|RESULT~1_combout\ = (\I2:13:I3|I0|INVERTER~0_combout\ & ((\I2:13:I3|I6|RESULT~0_combout\) # ((\I2:13:I3|I1|INVERTER~0_combout\ & \I0|OPERATION[1]~0_combout\)))) # (!\I2:13:I3|I0|INVERTER~0_combout\ & (\I2:13:I3|I6|RESULT~0_combout\ & 
-- ((\I2:13:I3|I1|INVERTER~0_combout\) # (!\I0|OPERATION[1]~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "e8f0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:13:I3|I0|INVERTER~0_combout\,
	datab => \I2:13:I3|I1|INVERTER~0_combout\,
	datac => \I2:13:I3|I6|RESULT~0_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:13:I3|I6|RESULT~1_combout\);

-- Location: LC_X3_Y3_N1
\I2:14:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:14:I3|I6|RESULT~1_combout\ = ((\I0|OPERATION[0]~1_combout\ & ((\I2:13:I3|I4|CARRY_OUT~0_combout\) # (\I0|OPERATION[1]~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "f0c0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datab => \I2:13:I3|I4|CARRY_OUT~0_combout\,
	datac => \I0|OPERATION[0]~1_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:14:I3|I6|RESULT~1_combout\);

-- Location: LC_X3_Y3_N7
\I2:14:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:14:I3|I6|RESULT~0_combout\ = (\A~combout\(14) $ (((\OPCODE~combout\(2) & !\OPCODE~combout\(1)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "f30c",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	datab => \OPCODE~combout\(2),
	datac => \OPCODE~combout\(1),
	datad => \A~combout\(14),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:14:I3|I6|RESULT~0_combout\);

-- Location: LC_X3_Y3_N5
\I2:14:I3|I6|RESULT~2\ : maxv_lcell
-- Equation(s):
-- \I2:14:I3|I6|RESULT~2_combout\ = (\I2:14:I3|I1|INVERTER~0_combout\ & ((\I2:14:I3|I6|RESULT~1_combout\ & (\I2:14:I3|I6|RESULT~0_combout\)) # (!\I2:14:I3|I6|RESULT~1_combout\ & ((\I0|OPERATION[1]~0_combout\) # (!\I2:14:I3|I6|RESULT~0_combout\))))) # 
-- (!\I2:14:I3|I1|INVERTER~0_combout\ & ((\I2:14:I3|I6|RESULT~1_combout\ & (!\I2:14:I3|I6|RESULT~0_combout\ & !\I0|OPERATION[1]~0_combout\)) # (!\I2:14:I3|I6|RESULT~1_combout\ & (\I2:14:I3|I6|RESULT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "b296",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:14:I3|I1|INVERTER~0_combout\,
	datab => \I2:14:I3|I6|RESULT~1_combout\,
	datac => \I2:14:I3|I6|RESULT~0_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:14:I3|I6|RESULT~2_combout\);

-- Location: LC_X4_Y4_N8
\I2:15:I3|I0|INVERTER~0\ : maxv_lcell
-- Equation(s):
-- \I2:15:I3|I0|INVERTER~0_combout\ = (\A~combout\(15) $ (((!\OPCODE~combout\(1) & \OPCODE~combout\(2)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "af50",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \OPCODE~combout\(1),
	datac => \OPCODE~combout\(2),
	datad => \A~combout\(15),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:15:I3|I0|INVERTER~0_combout\);

-- Location: LC_X4_Y4_N4
\I2:15:I3|I5|R~0\ : maxv_lcell
-- Equation(s):
-- \I2:15:I3|I5|R~0_combout\ = \B~combout\(15) $ (\A~combout\(15) $ (((\I0|Equal6~0_combout\ & !\I0|AINVERT~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "39c6",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I0|Equal6~0_combout\,
	datab => \B~combout\(15),
	datac => \I0|AINVERT~0_combout\,
	datad => \A~combout\(15),
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:15:I3|I5|R~0_combout\);

-- Location: LC_X4_Y4_N5
\I2:15:I3|I6|RESULT~0\ : maxv_lcell
-- Equation(s):
-- \I2:15:I3|I6|RESULT~0_combout\ = (\I0|OPERATION[1]~0_combout\ & (((!\I0|OPERATION[0]~1_combout\)))) # (!\I0|OPERATION[1]~0_combout\ & (\I2:15:I3|I5|R~0_combout\ $ (((\I0|OPERATION[0]~1_combout\ & \I2:14:I3|I4|CARRY_OUT~0_combout\)))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "336a",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:15:I3|I5|R~0_combout\,
	datab => \I0|OPERATION[0]~1_combout\,
	datac => \I2:14:I3|I4|CARRY_OUT~0_combout\,
	datad => \I0|OPERATION[1]~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:15:I3|I6|RESULT~0_combout\);

-- Location: LC_X4_Y4_N3
\I2:15:I3|I6|RESULT~1\ : maxv_lcell
-- Equation(s):
-- \I2:15:I3|I6|RESULT~1_combout\ = (\I2:15:I3|I0|INVERTER~0_combout\ & ((\I2:15:I3|I6|RESULT~0_combout\) # ((\I0|OPERATION[1]~0_combout\ & \I2:15:I3|I1|INVERTER~0_combout\)))) # (!\I2:15:I3|I0|INVERTER~0_combout\ & (\I2:15:I3|I6|RESULT~0_combout\ & 
-- ((\I2:15:I3|I1|INVERTER~0_combout\) # (!\I0|OPERATION[1]~0_combout\))))

-- pragma translate_off
GENERIC MAP (
	lut_mask => "f8b0",
	operation_mode => "normal",
	output_mode => "comb_only",
	register_cascade_mode => "off",
	sum_lutc_input => "datac",
	synch_mode => "off")
-- pragma translate_on
PORT MAP (
	dataa => \I2:15:I3|I0|INVERTER~0_combout\,
	datab => \I0|OPERATION[1]~0_combout\,
	datac => \I2:15:I3|I6|RESULT~0_combout\,
	datad => \I2:15:I3|I1|INVERTER~0_combout\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	combout => \I2:15:I3|I6|RESULT~1_combout\);

-- Location: PIN_90,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\OVERFLOW~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I4|RESULT1~0_combout\,
	oe => VCC,
	padio => ww_OVERFLOW);

-- Location: PIN_35,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[0]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I1|I6|RESULT~4_combout\,
	oe => VCC,
	padio => ww_RESULT(0));

-- Location: PIN_37,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[1]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:1:I3|I6|RESULT~1_combout\,
	oe => VCC,
	padio => ww_RESULT(1));

-- Location: PIN_92,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[2]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:2:I3|I6|RESULT~2_combout\,
	oe => VCC,
	padio => ww_RESULT(2));

-- Location: PIN_83,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[3]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:3:I3|I6|RESULT~1_combout\,
	oe => VCC,
	padio => ww_RESULT(3));

-- Location: PIN_4,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[4]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:4:I3|I6|RESULT~2_combout\,
	oe => VCC,
	padio => ww_RESULT(4));

-- Location: PIN_71,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[5]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:5:I3|I6|RESULT~1_combout\,
	oe => VCC,
	padio => ww_RESULT(5));

-- Location: PIN_85,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[6]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:6:I3|I6|RESULT~2_combout\,
	oe => VCC,
	padio => ww_RESULT(6));

-- Location: PIN_14,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[7]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:7:I3|I6|RESULT~1_combout\,
	oe => VCC,
	padio => ww_RESULT(7));

-- Location: PIN_30,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[8]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:8:I3|I6|RESULT~2_combout\,
	oe => VCC,
	padio => ww_RESULT(8));

-- Location: PIN_43,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[9]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:9:I3|I6|RESULT~1_combout\,
	oe => VCC,
	padio => ww_RESULT(9));

-- Location: PIN_62,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[10]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:10:I3|I6|RESULT~2_combout\,
	oe => VCC,
	padio => ww_RESULT(10));

-- Location: PIN_28,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[11]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:11:I3|I6|RESULT~1_combout\,
	oe => VCC,
	padio => ww_RESULT(11));

-- Location: PIN_5,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[12]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:12:I3|I6|RESULT~2_combout\,
	oe => VCC,
	padio => ww_RESULT(12));

-- Location: PIN_18,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[13]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:13:I3|I6|RESULT~1_combout\,
	oe => VCC,
	padio => ww_RESULT(13));

-- Location: PIN_95,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[14]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:14:I3|I6|RESULT~2_combout\,
	oe => VCC,
	padio => ww_RESULT(14));

-- Location: PIN_89,	 I/O Standard: 3.3-V LVTTL,	 Current Strength: 16mA
\RESULT[15]~I\ : maxv_io
-- pragma translate_off
GENERIC MAP (
	operation_mode => "output")
-- pragma translate_on
PORT MAP (
	datain => \I2:15:I3|I6|RESULT~1_combout\,
	oe => VCC,
	padio => ww_RESULT(15));
END structure;


