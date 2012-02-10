1b) The space complexity of my dynamicFibonacci implementation is O(1), which is optimal.
2c) The space complexity of the dynamic solution is O(i*w), where i denotes the number 
of elements and w the maximum weight; the time complexity is O(i*w).
3a) First of all, p(x, y) first must check whether it is at the top of the image. It can
accomplish this by comparing y to the height of the image. If it is, then return the 
energy of the pixel at (x,y). Otherwise, the function has to generate the energies of the
3 pixels above it (i.e. (x-1,y+1), (x, y+1), (x+1, y+1)). If x = 0 then we don't have to check
the (x-1, y) pixel; if x is the width of the image, we don't have to check (x+1, y). To
get the energies of these pixels, call p on the aforementioned coordinates. Then, the
minimum energy path from the given pixel to the top is the energy of the current (x, y)
pixel + the minimum of the 3 checked pixels. Return this value.

3b) For an NxN image, most calls to p(x,y) will occur on a non-edge square. Each p(x,y) 
will in turn call p(x,y) ~3 times. Thus, the complexity of this recursive implementation
is exponential, and is on the order of O(3^N). For the N=50 case, the number of calls
to p(x,y) ~ 3^49. If we assume that one call is equivalent to 1 computation (in reality,
of course, it would be more than that), it would take the hypothetical machine 
3^49 / 10^9 s to complete ==> a lot!


