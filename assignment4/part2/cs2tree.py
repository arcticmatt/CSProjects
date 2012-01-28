# CS2 Winter 2012
# Lab 4 - Problem 2: Binary Search Tree
# by Ye Lu, Dallin Akagi (2011)

# Part 2.0
#
# Basic structure for a Binary Tree.
# Very similar to a LinkedList node except that it has
# two children, one for the left subtree and one for
# the right subtree.
#
# For a Binary SEARCH Tree, we require that all elements
# on the left subtree of a current node must be SMALLER
# in value and all elements on the right subtree must
# have equal or higher value.
class TreeNode: #Given
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None

    def setLeft(self, leftChild):
        self.left = leftChild

    def setRight(self, rightChild):
        self.right = rightChild

    def setValue(self, value):
        self.value = value

    def getLeft(self):
        return self.left

    def getRight(self):
        return self.right

    def getValue(self):
        return self.value

# Problem 2.1
#
# The binary search tree class itself.
# Because the subtree of a BST will itself be a BST,
# traversing through a tree can easily be done by
# recursion. Just be sure to test for base cases and
# when to stop. 
#
# Iterative solutions are equally practical for manipulating 
# the tree. However, iterative solutions for printing
# a tree is much more complicated than the recursive 
# version.
#
# If you are planning to write a recursive function, writing
# a separate helper function might be a good idea. Do not
# change the headers of the functions we require you
# to write. But you are allowed to write new functions as
# you please.
class BinarySearchTree:
    def __init__(self):
        self.root = None

    # Adds a new node with the given value to the tree.
    # Follows the rule that all values smaller will be
    # on the left subtree and all values larger than the
    # current will be on the right subtree.
    #
    # No return value needed.
    def add(self, value):
        #Check if root is None; then we just initiate root as
        #the value.
        if (self.root == None):
            self.root = TreeNode(value)
            return
        #Otherwise, call our recursive function to place the
        #value on the tree.
        self.addvalue(value, self.root)
        
    #used by add to traverse through the tree and find where
    #to place the value. node cannot be None.
    def addvalue(self, value, node):
        if (value < node.getValue()):
            #it goes on the left side.
            #check if we can immediately place the value
            #on the tree
            if (node.getLeft() == None):
                node.setLeft(TreeNode(value))
                return
            #otherwise, recursively call the function
            self.addvalue(value, node.getLeft())
        else:
            #if it's greater than or equal, it goes on the right
            #side. I'm not sure what to do if they're equal
            #(unspecified), so I'll just place them on the right side.
            if (node.getRight() == None):
                node.setRight(TreeNode(value))
                return
            self.addvalue(value, node.getRight())
                
        

   # Check to see if the given value is contained within
   # the tree.
   #
   # Return value:
   #    - True if value is in the tree
   #    - False if value is not in the tree
    def contains(self, value): 
       #variable specifying the node we're testing.
        node = self.root
        while (node != None):
            if (value == node.getValue()):
                #we've found it!
                return true
            elif (value < node.getValue()):
                #check the left side.
                node = node.getLeft()
            else:
                #check the right side.
                node = node.getRight()
        #if we didn't find anything, return false.
        return false

    # Prints the list in a way such that we first print
    # all the values in the left subtree. Then the value of
    # the current node. And lastly all the values in the right
    # subtree. This print methodology carries for all nodes.
    #
    #. i.e. at no matter which node we are on, we must follow
    #   this ordering rule.
    def print_inorder(self): 
        self.printNodes(self.root)

    def printNodes(self, node):
        #Recursive function that we use to print the entire tree.
        if (node == None):
            #if we don't have a node, we're done.
            return
        #print the left subtree, then the current node, then the right.
        self.printNodes(node.getLeft())
        print(node.getValue())
        self.printNodes(node.getRight())


# Problem 2.4/2.5
# Write your answer here::
'''
2.4) It would look sort of like a line. Each of the first 99 nodes would
have as its right child a node whose value is its value + 1.
2.5) In the limiting case, you'd have to examine 1 node from each depth.
Note that 1 + 2 + 4 + ... + 2^(n-1) = 2^(n) - 1. Thus, the depth is n; in
the worst case, we'd have to examine n nodes. In the other type of tree, we
might have to examine n elements to find it.


'''

