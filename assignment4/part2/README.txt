2.4) It would look sort of like a line. Each of the first 99 nodes would
have as its right child a node whose value is its value + 1.
2.5) In the limiting case, you'd have to examine 1 node from each depth.
Note that 1 + 2 + 4 + ... + 2^(n-1) = 2^(n) - 1. Thus, the depth is n; in
the worst case, we'd have to examine n nodes. In the other type of tree, we
might have to examine n elements to find it.
