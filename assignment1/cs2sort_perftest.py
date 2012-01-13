import matplotlib
matplotlib.use('TkAgg')

from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg, NavigationToolbar2TkAgg
from matplotlib.figure import Figure

import Tkinter as Tk
import sys

# Replace cs2sort_badsort with the package to test.
import cs2sort_badsort as cs2sort

# The plotter generates data points for list lengths from
# 2**PLOT_RANGEMINPARAM to 2**(PLOT_RANGEMINPARAM+RANGELENPARAM-1).
# Change these constants to change the points sampled.
PLOT_RANGEMINPARAM = 1
PLOT_RANGELENPARAM = 50

import random
import time

# Batch timing of a particular sorter method.

def BatchTime(sorter, length, num_runs):
    t = 0
    c = 0
    for i in range(num_runs):
        k = sorter.TimedRun(length)
        t += k[0]
        c += k[1]
    print >> sys.stderr, "Run of length", length, "finished"
    return (t / num_runs, c / num_runs)
    
def main():
    root = Tk.Tk()
    root.wm_title("Performance Testing")
    root.resizable(False, False)

    sorter = cs2sort.SorterApplication()
    NUM_RUNS = 5

	# Do the plotting and calculation.
	# If you know matplotlib (http://matplotlib.sourceforge.net/) then
	# you can modify the below to create your own plots.

    f = Figure(figsize=(5,4), dpi=100)
    a = f.add_subplot(211, xlabel='List length', ylabel='Time (s)')
    a.set_position([0.2,0.6,0.7,0.3])
    t = map(lambda x: 10*(x+PLOT_RANGEMINPARAM), range(PLOT_RANGELENPARAM))
    s = map(lambda x: BatchTime(sorter, x, NUM_RUNS), t)
    p = [x[0] for x in s]
    q = [x[1] for x in s]
    a.plot(t,p)

    b = f.add_subplot(212, xlabel='List length', ylabel='Operations')
    b.set_position([0.2,0.15,0.7,0.3])
    b.plot(t,q)

    # a tk.DrawingArea
    canvas = FigureCanvasTkAgg(f, master=root)
    canvas.show()
    canvas.get_tk_widget().pack(side=Tk.TOP, fill=Tk.BOTH, expand=1)

    toolbar = NavigationToolbar2TkAgg( canvas, root )
    toolbar.update()
    canvas._tkcanvas.pack(side=Tk.TOP, fill=Tk.BOTH, expand=1)

    Tk.mainloop()
    
if __name__ == "__main__":
    main()
