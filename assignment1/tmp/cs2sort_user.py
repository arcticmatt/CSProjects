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
     
    def Sort(self):
        """My algorithm uses a time-space tradeoff to lower the number
        of comparisons and swaps required to sort the list. Since the
        data consists of randomly generated floats on [0,1], I can approximate
        the location of an element in the final sorted array, given its value.
        The formula is: elementValue * listLength. I use this to initially place
        the data into partitions, which are then swapped into the original 
        data structure. Then I sort each of these sublists using 
        insertion sort, to give me the final sorted list."""
        #make the partitioner, read the elements, swap them in and sort.
        partitioner = Partitioner(self, len(self)*2)
        partitioner.readAllElements()
        partitioner.swapSort()

class insertSorter:
    """This is my implementation of the insert sort algorithm. I use it to sort
    sublists of the sorterApp"""
    def getAppropriateIndex(self, startIndex, elementIndex):
        """Returns the index where the element specified
        by elementIndex should be inserted"""
        for i in xrange(startIndex, elementIndex):
            if (self.sorterApp.Compare(i, elementIndex) > 0):
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
            self.sorterApp.Swap(originalIndex, originalIndex-1)
            originalIndex -= 1

    def __init__(self, sorterApp):
        self.sorterApp = sorterApp

    def Sort(self, startIndex, endIndex):
        """Insertion sort implementation. This algorithm
        works by sorting a portion of the array, say i elements,
        and inserts the i+1th element into the already sorted part.
        Sorts elements with indexes i, startIndex <= i < endIndex"""
        for i in xrange(startIndex, endIndex):
            correctIndex = self.getAppropriateIndex(startIndex, i)
            self.swapInto(i, correctIndex)


class Partitioner:
    """The Partitioner class divides to data set into partitions. Elements are
    added to these partitions based on their values, and these partitions
    are used to sort the data"""
    def __init__(self, sorterApp, partitionCount):
        self.partitions = [Partition(sorterApp) for i in xrange(partitionCount)]
        self.partitionCount = partitionCount
        self.sorterApp = sorterApp
        self.elementCount = len(sorterApp)
    def elementValue(self, elementIndex):
        """Returns the value of the element with the given index"""
        return self.sorterApp[elementIndex]
    def partitionFromElementIndex(self, elementIndex):
        """Returns the partition associated with the given element."""
        partitionIndex = int(self.elementValue(elementIndex) * self.partitionCount)
        return self.partitions[partitionIndex]
    def addElement(self, elementIndex):
        """Adds the given element to a partition structure"""
        self.partitionFromElementIndex(elementIndex).addElement(elementIndex)
    def readAllElements(self):
        """Reads all elements from the sorterApplication, adding them to the 
        partition structures"""
        for i in xrange(self.elementCount):
            self.addElement(i)
    def swapElements(self, index1, index2):
        """Swaps elements with the given indexes in the partition structure and
        in the sorterApp"""
        p1 = self.partitionFromElementIndex(index1)
        p1.updateElementIndex(index1, index2)
        p2 = self.partitionFromElementIndex(index2)
        p2.updateElementIndex(index2, index1)
        #now, swap the elements in the sorterApp
        self.sorterApp.Swap(index1, index2)

    def swapSort(self):
        """Runs through all the partitions, swapping them in & sorting them when 
        they're in the sorterApp."""
        #first, set the index of the next element to be swapped in to 0
        nextIndex = 0
        sorter = insertSorter(self.sorterApp)
        for i in self.partitions:
            #swap the partition into the sorterApp
            newIndex = self.swapInPartition(nextIndex, i)
            #now sort the new values
            sorter.Sort(nextIndex, newIndex)
            nextIndex = newIndex


    def swapInPartition(self, startIndex, partition):
        """Swaps elements from the given partition into the sorterApp. Returns the index of the 
        last element + 1"""
        mapping = partition.map(startIndex)
        nextIndex = startIndex + len(mapping)
        #Run through all the elements, swapping them in.
        for i in mapping:
            self.swapElements(i, mapping[i])
        return nextIndex



class Partition:
    """The Partitioner class represents a grouping of elements. Elements are
    placed into partitions based on their values"""
    def __init__(self, sorterApp):
        self.sorterApp = sorterApp
        self.elements = {}
    def elementValue(self, elementIndex):
        """Returns the value of the element with the given index"""
        return self.sorterApp[elementIndex]
    def addElement(self, elementIndex):
        """Adds an element to the partition"""
        self.elements[elementIndex] = self.elementValue(elementIndex)
    def updateElementIndex(self, oldIndex, newIndex):
        """Updates an element the index given by oldIndex to a new index"""
        value = self.elements[oldIndex]
        self.elements.pop(oldIndex)
        self.elements[newIndex] = value
    def toDict(self):
        """Returns the partition, represented as a dictionary"""
        return self.elements.copy()
    def elementCount(self):
        """Returns the number of elements in this partition"""
        return len(self.elements)
    def map(self, minIndex):
        """Maps each element in the partition to a sublist starting with index minIndex.
        We keep the indexes of elements if possible"""
        maxIndex = self.elementCount() + minIndex
        originalIndexes = self.elements.keys()
        mapping = {}
        for i in originalIndexes:
            #if the index is in the range, we know its index
            if (minIndex <= i < maxIndex):
                mapping[i] = i
        #now loop through all the elements and assign them (if they haven't yet
        #yet been assigned.
        elementFinalIndex = minIndex
        for i in range(self.elementCount()):
            elementOriginalIndex = originalIndexes[i]
            #find next unused elementFinalIndex
            while (elementFinalIndex in mapping.values()):
                elementFinalIndex += 1
            if (not mapping.has_key(elementOriginalIndex)):
                mapping[elementOriginalIndex] = elementFinalIndex
        return mapping

        
def main():
    root = Tk()
    root.title("CS2 Sorting Visualiser")
    root.resizable(False, False)
    app = SorterApplication(root)
    root.mainloop()

if __name__ == "__main__":
    main()
