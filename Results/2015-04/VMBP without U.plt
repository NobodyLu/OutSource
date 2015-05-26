set xlabel "Computing counts"
set ylabel "Time cost (ms)"
cd 'D:\Research\Evaluation\OutSource\Results\2015-04'
set terminal postscript eps lw 2 "Times New Roman" 18
set output "VMBP without U.eps"
set key left top
plot "VMBP.txt" using 1:2 w lp pt 5 lt 1 linecolor 1 title "T",\
"VMBP.txt" using 1:8 w lp pt 4 lt 2 linecolor 0 title "Direct"
set output