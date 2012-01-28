/* 
 * balancebraces.c
 *
 * This file contains a simple program that determines if the 
 * parentheses and brackets are balanced in a given mathematical 
 * expression (as a string). This program does so using a stack. 
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "linked_list.h"
#define true 1
#define false 0

/*
 * If you choose to make helper functions, either define them up here
 * or be sure to use a function prototype (don't worry too much about 
 * this for now). This should be simple enough that no helper functions 
 * are necessary. 
 */

int isbalanced(char *k) {
  /* Returns true if the given string is balanced; false otherwise */
  int length = strlen(k);
  int i;
  char nextchar;
  /* stackhead points to the top of our stack */
  node* stackhead = NULL;
  node* tmp;
  for (i = 0; i < length; i++) {
    nextchar = k[i];
    /* Check if the next character is a '['; if it is, push a new
       node onto our stack */
    switch (nextchar) {
    case '[':
      /* Add a new node onto the stack denoting that we found a [ */
      stackhead = create_node('[', stackhead);
      break;
    case '(':
      /* Add a new node onto the stack denoting we found a ( */
      stackhead = create_node('(', stackhead); 
      break;
    case ')':
      /* if there's nothing on the stack or the top doesn't have a '(',
	 the string isn't balanced. */
      if (stackhead == NULL || stackhead->data != '(') {
	free_list(stackhead);
	return false;
      }
      /* Otherwise, it's okay */
      tmp = stackhead->next;
      free(stackhead);
      stackhead = tmp;
      break;
    case ']':
      /* Do essentially the same checks as above */
      if (stackhead == NULL || stackhead->data != '[') {
        free_list(stackhead);
        return false;
      }
      /* Otherwise, it's okay */
      tmp = stackhead->next;
      free(stackhead);
      stackhead = tmp;
      break;

    }
  
  }
  /* If there are no more nodes on the stack, all '(' / '[' have 
     been accounted for; we're done. */
  if (stackhead == NULL) {
    return true;
  } else { return false; }
}

int main(int argc, char *argv[]) {
    char *input; 

    /* This will be 1 if the braces are balanced, 0 otherwise. */
    int balanced; 


    /* Test for exactly one argument */
    if (argc != 2) {
        fprintf(stderr, "usage: %s [string] \n", argv[0]);
        exit(1);
    }
    
    /* Analyze the argument, the input string. */
    input = argv[1]; /* The string itself. */
    balanced = isbalanced(input);

    /* We print out our results in the end. */
    if (balanced) {
	printf("Balanced Braces: true\n");
    }
    else {
        printf("Balanced Braces: false\n");
    }
    
    return 0;
}
