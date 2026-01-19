package com.dsalgo.trees;

/*


                10
                /\
               /  \
              5    13
             /\    /\
            3  6  11 14
           /\   \
          2  4   9


Node to be deleted : 5

When 5 is deleted, either 3 or 6 can become 10's left.

Case 1 : 3 becomes 10's left -
    Whatever is on the right of 5 is greater than 5.
    It is also greater than the greatest on 5's left.
    Greatest on 5's left is 4
    Hence, 6 can get attached to 4's right


Case 2 : 6 becomes 10's left -
    Whatever is on the left of 5 is smaller than 5.
    It is also smaller than the smallest on 5's right.
    Smallest on 5's right is 6
    Hence, 3 can get attached to 6's left

We can go ahead with Case 1. Both of the solutions work.

Edge cases  :

Node to be deleted is root  : 10
We go ahead with Case 1
    Whatever is on the right of 10 is greater than 10.
    It is also greater than the greatest on 10's left.
    Greatest on 10's left is 9
    Hence, 12 can get attached to 9's right
    Then return 5 as the root node

Node to be deleted is having only one child :
Node to be deleted : 6 - when left is NULL and right is NOT NULL
If node 6 is its parent's right, then attach node 6's right to its parent's right
If node 6 were its parent's left, then attach node 6's right to its parent's left

Similar conditions when node to be deleted has NULL right and NOT NULL left

 */

public class _30_DeleteNodeBST {


}
