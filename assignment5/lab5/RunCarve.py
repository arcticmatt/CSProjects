import Image
import ImageTk
import ImageFilter
import Tkinter
from Carve import CImage
import time
import numpy

# pressing '0' displays the original image
# pressing '1' displays the shrunken image
# pressing '2' displays the energy image
# pressing space carves a seam
# pressing x inserts a seam

img = 'img2.jpg'
root = Tkinter.Tk()
canvas = Tkinter.Canvas(root,width=100,height=100)


#### Complete this method
def findVSeam(cim):
    '''Finds a single vertical seam and returns it, as a list of x-pixel coords
        cim is a CImage containing the energy function of this image.
        access a pixel value of cim as cim.pix[x,y] '''
    
    # energy is the list with the current sum of pixel values at x, y
    # energy is initialized to all 0's
    energy = numpy.zeros(cim.im.size, dtype=int)
    
    # path contains +1, 0, -1, depending on which way energy[x, y] was drawn 
    # ex: if the best path to energy[x,y] came from energy[x-1, y-1],
    # path[x,y] should be -1
    # path is initialized to all 0's
    path = numpy.zeros(cim.im.size, dtype=int)

    # set energy for initial row
    for x in cim.xrange():
        energy[x,0] = cim.pix[x,0]

    infinity = float('inf')
    # Populate the rest of energy and path arrays
    for y in range(1,cim.height):
        for x in cim.xrange():
            pixelEnergy = cim.pix[x, y]
            #Check the edge cases / get the energies.
            if (x == 0):
                energyDown = energy[x, y-1]
                energyRight = energy[x+1, y-1]
                energyLeft = infinity
            elif (x == cim.width - 1):
                energyDown = energy[x, y-1]
                energyRight = infinity
                energyLeft = energy[x-1, y-1]
            else:
                energyDown = energy[x, y-1]
                energyRight = energy[x+1, y-1]
                energyLeft = energy[x-1, y-1]
            #now figure out which gives the least energy; set the
            #path / energy accordingly
            minEnergy = min([energyDown, energyRight, energyLeft])
            if (energyDown == minEnergy):
                path[x, y] = 0
                energy[x,y] = pixelEnergy + energyDown
            elif (energyLeft == minEnergy):
                path[x, y] = -1
                energy[x, y] = pixelEnergy + energyLeft
            else:
                path[x, y] = 1
                energy[x, y] = pixelEnergy + energyRight
            
    
    # mine: minimum energy in bottom row
    # minx: x-coord of minimum energy
    mine = 255*100*cim.height
    minx = 0
    # find minimum-energy path
    for x in cim.xrange():
        if energy[x,cim.height-1] < mine:
            mine = energy[x,cim.height-1]
            minx = x
    # and traverse it backwards
    curx = minx
    seam = []
    for y in cim.yrange()[::-1]:
        seam.insert(0,int(curx))
        cim.pix[int(curx),y] = 255
        curx += path[curx,y]
    return seam

#### Write this function, It should be very similar to carveVSeam
def insertVSeam(cim, seam):
    print seam
    'Insert a seam into the image and returns resulting image.'
    cimout = CImage(cim.width + 1, cim.height)
    seamIndex = 0
    for y in cim.yrange():
        for x in range(0, seam[y]):
            cimout.pix[x,y] = cim.pix[x,y]
        #average of the surrounding pixels
        cimout.pix[seam[y], y] = getAverage(cim, seam[y], y) 
        for x in range(seam[y]-1, cim.width-1):
            #copy over the pixels
            cimout.pix[x+2, y] = cim.pix[x+1,y] 
    return cimout

def getAverage(cim, x, y):
    #we're gonna place all the near colours into a list, and
    #calculate the new pixel as the average of the near pixels.
    nearColours = []
    for dx in range(-1, 2):
        for dy in range(-1, 2):
            testX = x + dx
            testY = y + dy
            if (testX < 0 or testX >= cim.width or \
                    testY < 0 or testY >= cim.height):
                #invalid.
                continue
            #otherwise, append it.
            nearColours.append(cim.pix[testX, testY])
    #now return the average of all those colours.
    rSum, gSum, bSum = 0,0,0
    for r,g,b in nearColours:
        rSum += r
        gSum += g
        bSum += b
    elements = len(nearColours)
    #return the average.
    return (rSum/elements, gSum/elements, bSum/elements)

def carveVSeam(cim, seam):
    'Removes a given seam from an image, and returns resulting image.'
    cimout = CImage(cim.width-1,cim.height)
    for y in cim.yrange():
        for x in range(0,seam[y]):
            cimout.pix[x,y] = cim.pix[x,y]
        for x in range(seam[y],cim.width-1):
            cimout.pix[x,y] = cim.pix[x+1,y]
    return cimout

def findEdges(cim):
    'Returns a CImage containing the energy function of this image'
    return CImage(cim.im.filter(ImageFilter.FIND_EDGES).filter(ImageFilter.BLUR).convert('I'))

def markVSeam(cim, seam):
    'Fills in an image with yellow along the given seam.'
    for y in cim.yrange():
        cim.pix[seam[y],y] = (255,255,0)


cim0 = CImage(img)
cim0.show(canvas)
cim1 = CImage(cim0.im)
cimold = CImage(cim0.im)
cime = findEdges(cim1)

def show0(event):
    cim0.show(canvas)
def show1(event):
    cim1.show(canvas)
def show2(event):
    cime.show(canvas)
def delseam(event):
    global cime, cim1, cimold
    
    tstart = time.time()
    cime = findEdges(cim1)
    seam = findVSeam(cime)
    cimold = cim1
    cim1 = carveVSeam(cimold, seam)
    markVSeam(cimold,seam)
    cimold.show(canvas)
    print 'Time: %f' % (time.time()-tstart,)
    
def inseam(event):
    global cime, cim1, cimold
    tstart = time.time()
    cimold = cim1
    cime = findEdges(cim1)
    seam = findVSeam(cime)
    cim1 = insertVSeam(cimold, seam)
    cimold.show(canvas)
    print 'Time: %f' % (time.time()-tstart,)

root.bind('0',show0)
root.bind('1',show1)
root.bind('2',show2)
root.bind('<space>',delseam)
root.bind('<x>',inseam)

root.title('CS2 Lab 3: Seam Carving')

root.mainloop()
