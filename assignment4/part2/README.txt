2.4) It would look sort of like a line. Each of the first 99 nodes would
have as its right child a node whose value is its value + 1.
2.5) In the limiting case, you'd have to examine 1 node from each depth.
Note that 1 + 2 + 4 + ... + 2^(n-1) = 2^(n) - 1. Thus, the depth is n; in
the worst case, we'd have to examine n nodes. In the other type of tree, we
might have to examine n elements to find it.
3.5) In the absolute worst case, all the entries would be inside one bucket. In that case, we might have to examine
all entries to determine the value associated with a given key; it would run in O(n) time.
