########################################################################
#
# Original code copyright (c) 2011 Ben Yuan <byuan@caltech.edu>
# 
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions
# are met:
# 1. Redistributions of source code must retain the above copyright
#    notice, this list of conditions and the following disclaimer.
# 2. Redistributions in binary form must reproduce the above copyright
#    notice, this list of conditions and the following disclaimer in the
#    documentation and/or other materials provided with the distribution.
# 3. The name of the author may not be used to endorse or promote products
#    derived from this software without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
# IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
# OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
# IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
# INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
# NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
# DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
# THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
# (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
# THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
#
########################################################################

from Tkinter import *
from random import random, shuffle, randint
from collections import deque
from copy import copy
import time
import quadtree

CANVAS_HEIGHT = 512
CANVAS_WIDTH = 512
POINT_RADIUS = 2

class QuadtreeTesterApplication:
    def __init__(self, master=None):
        """Initializes the QuadtreeTesterApplication.
        
        Arguments:
        master - the parent Tk object (typically root).
        
        Notes:
        If no master is provided, then the UI is not initialized, and bad things 
        can happen if UI-related member functions are subsequently called.
        
        """
        self.master = master

        self.quadtree = quadtree.Quadtree()
        
        self.ui = False
        
        self.Scramble()
        
        if master:
            self.ui = True
            frame = Frame(master)
            frame.pack()
            
            self.canvas = Canvas(frame,height=CANVAS_HEIGHT,width=CANVAS_WIDTH,bg="white")
            self.canvas.bind("<Button-1>", self.CanvasClicked)
            self.UpdateCanvas()
            self.canvas.pack()
            
            subframe = Frame(frame)
            subframe.pack()
            
            self.add_button = Button(subframe,text="Add 50",command=self.AddOne)
            self.add_button.pack(side=LEFT)
            self.reset_button = Button(subframe,text="Reset",command=self.Scramble)
            self.reset_button.pack(side=LEFT)
            
    def CanvasClicked(self, event):
        radius = 0.03125
        point = (float(event.x) / CANVAS_WIDTH, float(event.y) / CANVAS_HEIGHT)
        #print "Queried", point
        query_result = self.quadtree.query(point, radius)
        #self.quadtree.insert(point)
        self.UpdateCanvas(query_result[0], query_result[1])
        r = tuple([i - radius for i in point]) + tuple([i + radius for i in point])
        self.canvas.create_rectangle(r[0]*CANVAS_WIDTH, r[1]*CANVAS_HEIGHT, r[2]*CANVAS_WIDTH, r[3]*CANVAS_HEIGHT,fill='',outline='orange')
    
    def AddOne(self):
        for j in range(50):
            point = tuple([random() for i in range(2)])
            self.quadtree.insert(point)
        self.UpdateCanvas()
         
    def UpdateCanvas(self, points_to_highlight = [], rects_to_highlight = []):
        if self.ui:
            self.canvas.addtag_all("t")
            self.canvas.delete("t")
            for r in self.quadtree.list_rectangles():
                self.canvas.create_rectangle(r[0]*CANVAS_WIDTH, r[1]*CANVAS_HEIGHT, r[2]*CANVAS_WIDTH, r[3]*CANVAS_HEIGHT,fill=('#{0:02x}{1:02x}{2:02x}'.format(randint(0,128),randint(0,255),randint(0,128)) if r in rects_to_highlight else ''),outline='blue')
            for p in self.quadtree.list_points():
                self.canvas.create_oval(p[0]*CANVAS_WIDTH-POINT_RADIUS, p[1]*CANVAS_HEIGHT-POINT_RADIUS, p[0]*CANVAS_WIDTH+POINT_RADIUS, p[1]*CANVAS_HEIGHT+POINT_RADIUS,fill=('blue' if p in points_to_highlight else 'red'),outline='')
            
    def Scramble(self):
        self.quadtree = quadtree.Quadtree()
        ##for i in range(50):
        ##    point = tuple([random() for i in range(2)])
        ##    self.quadtree.insert(point)
        self.UpdateCanvas()

def main():
    root = Tk()
    root.title("CS2 Quadtree Visualiser")
    app = QuadtreeTesterApplication(root)
    root.mainloop()
    
if __name__ == "__main__":
    main()
