## Fibonacci Problem


def dynamicFibonacci(n):
    ''' This function takes in an integer n
        It calculates the nth fibonacci number via dynamic programming.
        The problem should be done bottom up. '''

    """We'll calculate the nth fibonacci number by repetitively finding the
    next number by adding up the past 2 numbers """
    fib0 = 0
    fib1 = 1
    for i in xrange(n):
        fib0, fib1 = fib1, fib1 + fib0
    return fib0
    

if __name__ == "__main__":
    x = dynamicFibonacci(18)
    if (x == 2584):
        print "succes"
    else:
        print "something isn't working"
        
