library ieee;
use ieee.std_logic_1164.all;

entity MyOr1 is
    port (
    x1, x2 : in std_logic;
    f1     : out std_logic);
end MyOr1;

architecture gate_level_MyOr1 of MyOr1 is
begin
    f1<= x1 or x2;
end gate_level_MyOr1;


library ieee;
use ieee.std_logic_1164.all;

entity MyOr2 is
    port(
    x1 , x2 , x3 : in std_logic;
    f1           : out std_logic);
end MyOr2;

architecture gate_level_MyOr2 of MyOr2 is
begin
    f1<= x1 or x2 or x3;
end gate_level_MyOr2;


library ieee;
use ieee.std_logic_1164.all;

entity MyAnd is
    port(
    x1,x2,x3 : in std_logic;
    f1       : out std_logic);
end MyAnd;

architecture gate_level_MyAnd of MyAnd is
begin 
    f1<= x1 and x2 and x3;
end gate_level_MyAnd;


library ieee;
USE ieee.std_logic_1164.all;

entity Cirquit1 is
    port( x1,x2,x3,x4,x5 : in std_logic;
            f             : out std_logic);
end Cirquit1;

architecture Structural of Cirquit1 is
    component MyOr1
    port (x1,x2 : in std_logic;
            f1    : out std_logic);
    end component;

    component MyOr2
    port(x1,x2,x3 : in std_logic;
          f1      :  out std_logic);
    end component;

    component MyAnd 
    port(x1,x2,x3 : in std_logic;
         f1       : out std_logic);
    end component;

    signal out1,out2,out3: std_logic;

begin
    I0: MyOr1 port map (not x2,x4,out1);
    I1: MyOr1 port map (x2,not x5,out2);
    I2: MyOr2 port map (not x1, x2,not x4 ,out3);
    I3: MyAnd port map (out1,out2,out3,f);
end Structural;