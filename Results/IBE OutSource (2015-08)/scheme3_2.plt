set xlabel "Number of users"
set ylabel "Time cost (s)"
cd 'D:\Research\Evaluation\OutSource\Results\IBE OutSource (2015-08)'
set terminal postscript eps lw 2 "Times New Roman" 18
set output "scheme3_2.eps"
set key left top
set xrange [-0.1:4.1]
set xtics 1
set xtics add ("2^3" 0, "2^6" 1, "2^9" 2, "2^12" 3, "2^15" 4)
plot "scheme3_2.txt" using 1:2 w lp pt 5 lt 1 linecolor rgb "red" title "PKG", \
"scheme3_2.txt" using 1:3 w lp pt 6 lt 1 linecolor rgb "blue" title "PKG-outsource", \
"scheme3_2.txt" using 1:4 w lp pt 6 lt 4 linecolor rgb "green" title "U1", \
"scheme3_2.txt" using 1:5 w lp pt 6 lt 4 linecolor rgb "brown" title "U2"
set output