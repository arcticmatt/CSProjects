1.1) On average, will take n/2 checks. The upper bound is O(n).
1.2) The top of the stack is the first element (head) of the linked list. Pushing a value onto the stack involves placing a new element as the head, and linking this element to the old head. Stacks only allow popping
from the top of the stack, so the pop function will return the head element, then set the new head to be the second element (if there is one) and delete the old head.
