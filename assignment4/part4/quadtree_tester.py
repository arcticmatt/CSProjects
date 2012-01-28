from quadtree_ui import QuadtreeTesterApplication
from Tkinter import Tk

def main():
    root = Tk()
    root.title("CS2 Quadtree Visualiser")
    app = QuadtreeTesterApplication(root)
    root.mainloop()
    
if __name__ == "__main__":
    main()
