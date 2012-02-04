## Knapsack Problem

## This method uses recursion and is the top-down approach to the problem.
## Memoization is not required in this case.
## Here index = the number of pearls - 1
def maxValueRec(values, weights, index, maxWeight):
    if(len(values) != len(weights)):
        raise Exception("Your pearlValues and pearlWeights \
                        are of different lengths")

    """We'll run through each of the possible weights, calling 
    our recursive function with each to find the optimum value"""
    valuesList = [0]
    for i in xrange(len(weights)):
        if (weights[i] <= maxWeight):
            #try it, creating the new values / weights.
            newValues = list(values)
            newWeights = list(weights)
            newValues.pop(i)
            newWeights.pop(i)
            #We chose a weight, and trying the max value with the reduced 
            #weights list.
            val = values[i] + maxValueRec(newValues, newWeights, index, \
                                              maxWeight - weights[i])
            valuesList.append(val)
    return max(valuesList)

## This takes 3 parameters.  "pearlValue" is a list of each pearl's value. 
## "pearlWeight" is the weight of each corresponding pearl.  "maxWeight" is
## the maximum weight the bag can hold.  
def maxValueDyn(values, weights, maxWeight):
    if(len(values) != len(weights)):
        raise Exception("Your pearlValues and pearlWeights \
                        are of different lengths")
    ## TODO

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
    
