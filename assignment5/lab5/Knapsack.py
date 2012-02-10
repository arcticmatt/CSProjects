## Knapsack Problem

## This method uses recursion and is the top-down approach to the problem.
## Memoization is not required in this case.
## Here index = the number of pearls - 1
def maxValueRec(values, weights, index, maxWeight):
    if(len(values) != len(weights)):
        raise Exception("Your pearlValues and pearlWeights \
                        are of different lengths")

    #first check if there are no values / weights we can add
    if (len(values) == 0 or maxWeight == 0):
        return 0

    #the maximum value we've found thus far.
    maxValue = 0
    #index denotes the index of the next weight/value pair to try.
    for i in range(index, -1, -1):
        #check to make sure it's valid to use that value.
        w = weights[i]
        if (w > maxWeight):
            continue
        v = values[i]
        newIndex = i - 1
        newMaxWeight = maxWeight - w
        #try it.
        val = v + maxValueRec(values, weights, newIndex, newMaxWeight)
        if (val > maxValue):
            maxValue = val
    return maxValue


## This takes 3 parameters.  "pearlValue" is a list of each pearl's value. 
## "pearlWeight" is the weight of each corresponding pearl.  "maxWeight" is
## the maximum weight the bag can hold.  
def maxValueDyn(values, weights, maxWeight):
    if(len(values) != len(weights)):
        raise Exception("Your pearlValues and pearlWeights \
                        are of different lengths")
    #how many elements do we have?
    elementCount = len(values)
    #M[i][w] denotes the maximum value found employing the first i elements,
    #and having a weight of w
    M = [[0 for i in range(maxWeight+1)]]
    #loop through all the values/weight pairs, updating M accordingly.
    for i in range(elementCount):
        #the value / weight of the next element we want to add to M
        v = values[i]
        w = weights[i]
        #we'll create M[i+1] and place it here.
        nextMaxValues = list(M[i])
        #M[i] denotes the maximum values we've found using all 
        #elements with indexes <= i.
        maxValues = M[i]
        #now loop through all the elements of M[i] and create 
        #nextMaxValues
        possibleValues = maxWeight - w + 1
        #possibleValues is the maximum index we have to loop to;
        #elements of M after that have weights too high and we
        #don't need to consider them.
        for j in range(possibleValues):
            if (v + maxValues[j] > maxValues[j + w]):
                nextMaxValues[j + w] = v + maxValues[j]
        M.append(nextMaxValues)
    return max(M[elementCount])
                


if __name__ == "__main__":
    ## Basic test code:
    v = [5, 2, 1, 4, 9, 3, 8]
    w = [10, 20, 4, 6, 9, 12, 14]
    maxWeight = 47
    if maxValueRec(v, w,len(v)- 1,maxWeight) != 27:
        print maxValueRec(v, w,len(v)- 1,maxWeight)
        print "Your code doesn't seem to work!"
    else:
        print "Congrats! Your code works!"
    if maxValueDyn(v, w, maxWeight) != 27:
        print "Your code doesn't seem to work!"
    else:
        print "Congrats! Your code works!"
    
