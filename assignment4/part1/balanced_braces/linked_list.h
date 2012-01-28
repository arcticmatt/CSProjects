/*
 * linked_list.h
 *
 * This file contains the declaration of the node struct that we'll 
 * be using to make a linked list (and treating it like a stack). 
 * It also contains other associated functions. 
 * 
 */

#ifndef LINKED_LIST_H
#define LINKED_LIST_H


/*
 * Declaration of the 'node' struct.  An individual list element is
 * often called a "node".  A linked list is a series of nodes linked
 * together through their 'next' pointers.
 */

typedef struct _node
{
  char data;           /* each node stores a single char */
  struct _node *next; /* and a pointer to the next node in the chain */
} node;


/* Create a single node and link it to the node called 'n'. */
node *create_node(char data, node *n);

/* Free all the nodes of a linked list. */
void free_list(node *list);

/* Print the elements of a list. */
void print_list(node *list);

#endif  /* LINKED_LIST_H */

