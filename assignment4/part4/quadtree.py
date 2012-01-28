########################################################################
#
# Original code written by Ben Yuan <byuan@caltech.edu>
# 
# Redistribution and use in source and binary forms, with or without
# modification, are permitted without restrictions.
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

## This is skeleton code you can use if you're stuck on where to begin.
## It's pretty minimal, on purpose.

class Quadtree:

    ## This class will become the top-level object.
    ## You'll instantiate one of these and use it to access your entire data structure.

    def __init__(self, world_size = 1.0):
        ## TODO: Fill out this method.
        pass
        
    def insert(self, point):
        ## TODO: Fill out this method.
        ## Hint: if implemented recursively, this function is quite short.
        pass
        
    def list_rectangles(self):
        ## TODO: Fill out this method.
        ## Hint: if implemented recursively, this function is quite short.
        pass
        
    def list_points(self):
        ## TODO: Fill out this method.
        ## Hint: if implemented recursively, this function is quite short.
        pass
        
    def query(self, center, radius):
        ## TODO: Fill out this method.
        ## Hint: if implemented recursively, this function is quite short.
        pass
        
class QuadtreeNode:    

    ## This class will become the node-level object.
    ## You'll create more of these as necessary.
    
    def __init__(self, *args, **kwargs):
        """Initializes a new QuadtreeNode.
        
        Arguments:
            size:
                the side length of the rectangle encompassed by this node
            upper_left:
                the coordinates of the upper left corner of this node
            
        """
        ## TODO: You'll need to decide what member variables you'll need, and
        ## set them all up here.
        ##
        ## You'll almost certainly need a way to store the four children;
        ## a list is apt here as it can be queried sequentially.
        ## You'll also most likely need to be able to store the node's bounding box.
        ##
        ## You can change the argument list as you please, if you want to enforce
        ## a particular set of arguments. This argument list, though, lets you pass 
        ## any set of arguments you want; use args[integer] to access positional
        ## arguments, and use kwargs['keyword'] to access keyword arguments.
        pass
        
    def node_rect(self):
        """Returns the bounding rectangle of this node."""
        ## TODO: This is a useful helper function to have.
        ## Write it if you decide you need it.
        pass
        
    def insert(self, point):
        """Inserts a point at the given coordinates to the quadtree."""
        ## TODO: Implement this function.
        ## You'll almost certainly need to make a few recursive calls.
        
        
    def list_rectangles(self):
        """Returns a list of bounding boxes (x1, y1, x2, y2) for this node and its children."""
        ## TODO: Implement this function.
        ## You'll almost certainly need to make a few recursive calls.
        
        
    def list_points(self):
        """Returns a list of points contained within this node and its children."""
        ## TODO: Implement this function.
        ## You'll almost certainly need to make a few recursive calls.
        
    def query(self, center, radius):
        """Returns a list of points in this node or its children inside the query area."""
        ## TODO: Implement this function.
        ## You'll almost certainly need to make a few recursive calls.
    
