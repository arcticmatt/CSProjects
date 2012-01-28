# 
# Makefile for CS2 hw4
#

CC     = gcc
CFLAGS = -g -Wall -Wstrict-prototypes -ansi -pedantic
objects = balancedbraces

all: $(objects)

balancedbraces: balancedbraces.o linked_list.o
	$(CC) balancedbraces.o linked_list.o -o balancedbraces

balancebraces.o: balancedbraces.c linked_list.h
	$(CC) $(CFLAGS) -c balancedbraces.c

linked_list.o: linked_list.c linked_list.h
	$(CC) $(CFLAGS) -c linked_list.c

clean:
	rm -f *.o balancedbraces



