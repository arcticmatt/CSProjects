########################################################################
# CS2 2012 Assignment 1
# Sorting in Python
#
# Student: Ryan Batterman
#
########################################################################

########################################################################
# CS2 Assignment 1: Sorting in Python
# 
# This file defines a class SorterApplication that implements a number
# of useful interfaces. The class is capable of generating and storing
# a list of random numbers and is capable of constructing a Tkinter
# window enabling the user to visualise the sorting process.
# 
# Instances of the class also provide an interface to a list that is to
# be sorted. List elements may be read, but not written, by subscripting
# the instance. List elements may be swapped, given their indices, using
# a provided public function.
#
# The functions providing the user interface and API are contained in a
# separate superclass called SorterApplicationBase, defined in a separate
# file. The student's work builds on top of the functionality provided by
# the base class.
#
# The student is tasked with writing a class member function that sorts
# the list in place. The student is only to use the given member functions
# to manipulate the list; the student is not to modify the list
# directly. If implemented correctly, the student should be able to
# verify the correctness of the implementation by clicking a button on
# the Tkinter interface to initiate the animation process.
########################################################################

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
from random import randrange

from cs2sort_ui import SorterApplicationBase

class SorterApplication(SorterApplicationBase):
    """A class that encapsulates a list and provides sorting and visualization.
    
   This class inherits from the SorterApplicationBase class, which provides all 
   of the framework functions. Students are expected to implement their sorting 
   routine here in a function called Sort().
    
    Students are expected to use the Swap() function for list manipulation and 
    use the Compare() function for element comparison. Unfortunately, this 
    requirement (necessary for the style of visualizer used) precludes the 
    implementation of sorting methods that cannot operate in place.
    
    Public functions:
        Sort(self):
            sorts the encapsulated list.
            to be implemented by student.
    
    Non-public functions:
        to be defined by student.
    
    """
     
    def getAppropriateIndex(self, elementIndex):
        """Returns the index where the element specified
        by elementIndex should be inserted"""
        for i in xrange(elementIndex):
            if (self.Compare(i, elementIndex) > 0):
                """If the ith element is greater than
                the specified element, we've found the 
                place"""
                return i
        """If we don't find any place, it goes on the end, 
        so we return the original index"""
        return elementIndex

    def swapInto(self, originalIndex, finalIndex):
        """Swaps the element with originalIndex into
        the position given by finalIndex"""
        while (finalIndex < originalIndex):
            self.Swap(originalIndex, originalIndex-1)
            originalIndex -= 1

    def Sort(self):
        """Insertion sort implementation. This algorithm
        works by sorting a portion of the array, say i elements,
        and inserts the i+1th element into the already sorted part"""
        arrayLength = len(self)
        for i in xrange(arrayLength):
            correctIndex = self.getAppropriateIndex(i)
            self.swapInto(i, correctIndex)
            
            

        
def main():
    root = Tk()
    root.title("CS2 Sorting Visualiser")
    root.resizable(False, False)
    app = SorterApplication(root)
    root.mainloop()

if __name__ == "__main__":
    main()
