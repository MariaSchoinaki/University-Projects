library ieee;
use ieee.std_logic_1164.all;

entity or_gate is 
    port (
    x1,x2,x3 : in std_logic;
    f1       : out std_logic);
end or_gate;

architecture gate_level_or of or_gate is 
begin
    f1<= x1 or x2 or x3;
end gate_level_or;

library ieee;
use ieee.std_logic_1164.all;

entity and_gate is
    port(
    x1,x2 : in std_logic;
    f1    : out std_logic);
end and_gate;

architecture gate_level_and_gate of and_gate is
begin
        f1<= x1 and x2;
end gate_level_and_gate;

library ieee;
use ieee.std_logic_1164.all;

entity Cirquit3 is
    port(x1,x2,x3 : in std_logic;
            f      : out std_logic);
end Cirquit3;


architecture Structural  of Cirquit3 is
component or_gate
    port(x1,x2,x3  : in std_logic;
    f1             : out std_logic);
end component;

component and_gate 
    port(x1,x2  :in std_logic;
          f1    :out std_logic);
end component;


signal out1,out2,out3:std_logic;
begin
    I0: and_gate port map(not x1 ,not x3 ,out1);
    I1: and_gate port map(x1,x3,out2);
    I2: and_gate port map(x1,x2,out3);
    I3: or_gate  port map(out1,out2,out3,f);
end Structural;