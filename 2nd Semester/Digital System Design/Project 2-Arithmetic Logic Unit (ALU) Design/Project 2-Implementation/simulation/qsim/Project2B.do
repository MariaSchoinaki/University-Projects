onerror {exit -code 1}
vlib work
vcom -work work Project2B.vho
vcom -work work Waveform1.vwf.vht
vsim -novopt -c -t 1ps -L maxv -L altera -L altera_mf -L 220model -L sgate -L altera_lnsim work.Project2B_vhd_vec_tst
vcd file -direction Project2B.msim.vcd
vcd add -internal Project2B_vhd_vec_tst/*
vcd add -internal Project2B_vhd_vec_tst/i1/*
proc simTimestamp {} {
    echo "Simulation time: $::now ps"
    if { [string equal running [runStatus]] } {
        after 2500 simTimestamp
    }
}
after 2500 simTimestamp
run -all
quit -f
