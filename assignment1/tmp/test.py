#!/usr/bin/python

from random import random

global h
h=0
global memberCount
memberCount = 262144
#memberCount = 1000

def makeList(n):
    return [random() for i in xrange(n)]

def swap(l, i, j):
    """swaps the ith and jth component of l"""
    l[i], l[j] = l[j], l[i]

def compare(l, i1, i2):
    return l[i1][1] - l[i2][1]

def bubbleSort(l):
    global h
    while (True):
        swapped = False
        for i in xrange(0, len(l)-1):
            h += 1
            if (compare(l, i, i+1) > 0):
                swap(l, i, i+1)
                swapped = True
        if (not swapped):
            return

def sort(l):
    global memberCount
    global h
    approx = [[] for i in xrange(memberCount)]
    for index in xrange(memberCount):
        value = l[index]
        approximateIndex = int(value * memberCount)
        approx[approximateIndex].append([index , value])
    for i in xrange(memberCount):
        bubbleSort(approx[i])
    next=0
    for i in approx:
        for j in i:
            h += 1
            l[next] = j[1]
            next += 1

l=makeList(memberCount)
sort(l)
print h
