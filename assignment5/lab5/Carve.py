'''
Created on Dec 24, 2009
@author: Tamas Szalay
'''

import Image
import ImageTk
import Tkinter

class CImage:
    '''A wrapper around PIL images and Tkinter canvases'''
    def __init__(self, *args):
        '''Load an image from a file or create a new one'''
        if len(args) == 1:
            if isinstance(args[0],basestring):
                self.im = Image.open(args[0])
            else:
                self.im = args[0].copy()
        elif len(args) == 2:
            self.im = Image.new('RGB',(args[0],args[1]))
        elif len(args) == 3:
            self.im = Image.new(args[2],(args[0],args[1]))
        else:
            raise TypeError('Too many arguments, %d given' % (len(args),))
        self.pix = self.im.load()
        self.width = self.im.size[0]
        self.height = self.im.size[1]
        
    def show(self, canvas = None):
        '''Display image on tkinter canvas, save handle for later'''
        if canvas == None:
            # create new window and a canvas in said window
            self.imgwin = Tkinter.Toplevel()
            self.canvas = Tkinter.Canvas(self.imgwin,width = self.im.size[0], height = self.im.size[1], bg = 'black')
        else:
            self.canvas = canvas
            self.canvas.config(width=self.im.size[0],height=self.im.size[1])
            self.canvas.delete(Tkinter.ALL)
            
        self.canvas.pack(expand = Tkinter.YES, fill = Tkinter.BOTH)
        self.pim = ImageTk.PhotoImage(self.im)
        self.imhandle = self.canvas.create_image(0, 0, image = self.pim, anchor = Tkinter.NW)
    
    def refresh(self):
        '''Redisplay the image in the previously used canvas object, if it exists''' 
        try:
            self.canvas.delete(self.imhandle)
            self.pim = ImageTk.PhotoImage(self.im)
            self.imhandle = self.canvas.create_image(0, 0, image = self.pim, anchor = Tkinter.NW)
        except NameError:
            # no canvas exists, need to create one
            self.show()
            return
        
    def xrange(self):
        return range(0,self.im.size[0])
    
    def yrange(self):
        return range(0,self.im.size[1])
    
    def copy(self):
        cim = CImage(self.width,self.height)
        cim.im = self.im.copy()
        cim.pix = cim.im.load()
        return cim
