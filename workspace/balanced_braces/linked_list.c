/*
 * linked_list.c
 *
 * This file contains the linked list functions, most notably 
 * create_node, and free_list. 
 * 
 * Note: This is heavily based on the cs11 c track (highly recommended)!
 *
 */


#include <stdio.h>
#include <stdlib.h>
#include "linked_list.h"


/*
 * create_node:
 *     Create a single node and link it to the node called 'n'.
 */

node *
create_node(char data, node *n)
{
    node *result = (node *)malloc(sizeof(node));

    if (result == NULL)
    {
        fprintf(stderr, "Fatal error: out of memory. "
                "Terminating program.\n");
        exit(1);
    }

    result->data = data;  /* Fill in the new node with the given value. */
    result->next = n;

    return result;
}


/*
 * free_list:
 *     Free all the nodes of a linked list.
 */

void
free_list(node *list)
{
    node *n;     /* a single node of the list */

    while (list != NULL)
    {
        n = list;
        list = list->next;

        /*
         * 'n' now points to the first element of the list, while
         * 'list' now points to everything but the first element.
         * Since nothing points to 'n', it can be freed.
         */

        free(n);
    }
}



/*
 * Print the elements of a list.
 */

void
print_list(node *list)
{
    node *item;

    for (item = list; item != NULL; item = item->next)
    {
        printf("%c\n", item->data);
    }
}


