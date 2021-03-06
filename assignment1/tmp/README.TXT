Part 1)
a) It means that L[i] < L[i+1].
b) You should swap them.
c) No, this does not necessarily sort the list. To illustrate, consider the
following list: [3,2,1]. Running the aforementioned algorithm will first
swap the 3 and the 2, then will switch the 1 and 3, giving us [2,1,3].
Clearly, this is not sorted. If one runs this algorithm on a
given list, and the algorithm doesn't swap any values in the array, then
the list is sorted.
d)
e) My algorithm runs in O(n^2) time. Each time it runs through the list of elements,
it does n-1 comparisons (where n specifies the number of objects in the array). 
Furthermore, the number of runthroughs will be slightly under n, since n is the maximum number
of swaps it could take to move an element from the highest index in the array to 0. Hence, 
The number of comparisons = (n-1)*(C*n) where C is a constant on [0,1]. Thus, we have O(n^2) comparisons. 
The number of swaps is about half the number of comparisons, so the number of swaps is O(n^2).

Part 2) 
a) The worst case occurs when the list is sorted in descending (rather than ascending)
order. When this happens, we will have n-1 comparisons per runthrough, and will have
n runthroughs (the last runthrough checks that we have a sorted list). (n-1)(n) = n^2 - n
b) You can run through the rest of the (unsorted) elements, searching for the minimum. When you
find the minumum, you swap it with the current i+1th element.
c) Get the i+1th element, and run through each of the sorted elements, from the beginning. When
you locate an element which is larger than the i+1th element, insert the i+1th number into the
array behind it. If you don't find any place in the sorted list, do nothing.
d) The average runtime will be on the order of O(n^2), and the worst case runtime is equal to 
the average runtime, so it is O(n^2) too.
e) The average runtime will be O(n^2) because we'll have to do about (1 + 2 + 3 + ... + n) / k
comparisons (where 0 < k <= 1). This is O(n^2) time. In the worst case, k = 1, and the algorithm
will still run in O(n^2) time. The insertion algorithm might be better than bubble sort:
the insertion algorithm employed 209 swap and compare operations on a 20 element array, while
the bubble sort used 359.

Part 3)  
a) It makes progress towards the sorted state because it divides the list into smaller elements
and larger elements. It should be applied recursively to sort the entire list. In other words,
we should choose an L[i], put elements larger than L[i] after it and elements smaller than L[i]
before it. Then, we should continue this process using the elements before L[i] and the ones
after L[i]. Recursive application of the idea sorts the entire list. 

b) The average runtime of this algorithm is O(nlog(n)) because we will be performing about n 
comparisons per iteration (i.e. per depth) and our maximum depth will be log(n) (because list
is split into halves each time -- it will take log(n) depths for it to reach sublists of length
1). Hence it will run in O(n * log(n)) time. If our choices for a divider are the worst they
can possibly be (for instance, if we choose the minimum element, then the second smallest, etc),
then the algorithm is essentially a select sort algorithm, and its runtime is O(n^2).

Part 4)
a)
b) The average number of compare / swap operations depends on the number of partitions my algorithm
uses. As the number of partitions increases to infinity, the average number of compare operations
approaches 0 and the average number of swap operations approaches exactly n. In the worst case, I
would have 0 partitions, and the sort would be just an insertion sort; its worst number of swap / compare operations
would be 0 + 1 + 2 + 3 + ... + n-1 = (n-1)^2 / 2 + (n-1) / 2 = O(n^2).
c)