/***********
 Roman Alonzo
 CS 241
 Assignment 2
 10/30/2018
 The AVL class is the primary operator of this assignment, it contains methods 
 that build trees, and maintain balance through locations
 ***********/

public class AVL {

    public Node root;
    private int size; // number of nodes in the tree

    public int getSize() {
        return size;
    }

    /**
     * find w in the tree. return the node containing w or
     * null if not found
     */
    public Node search(String w) {
        return search(root, w);
    }

    private Node search(Node n, String w) {
        if (n == null) {
            return null;
        }
        if (w.equals(n.word)) {
            return n;
        } else if (w.compareTo(n.word) < 0) {
            return search(n.left, w);
        } else {
            return search(n.right, w);
        }
    }

//********************BST INSERT******************************************************//

    /**
     * insert w into the tree as a standard BST, ignoring balance
     */
    public void bstInsert(String w) {
        if (root == null) {
            root = new Node(w);
            size++;
            return;
        }
        bstInsert(root, w);
    }

    /* insert w into the tree rooted at n.
     * pre: n is not null */
    private void bstInsert(Node n, String w) {

        int compare = w.compareTo(n.word);

        if (n.word == w) {
            //duplicate
            return;
        }
        //if the word is smaller than the node, insert left
        else if (compare < 0) {
            if (n.left != null) {
                bstInsert(n.left, w);
            } else {
                //attach new node with w to n.left
                n.left = new Node(w);
                n.left.parent = n;
                size++;
            }
        }
        //compare greater than 0
        else {
            if (n.right != null) {
                bstInsert(n.right, w);
            } else {
                n.right = new Node(w);
                n.right.parent = n;
                size++;
            }
        }
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }
//*************************AVL INSERT***************************************************//

    /**
     * insert w into the tree, maintaining AVL balance
     */
    public void avlInsert(String w) {
        if (root == null) {
            root = new Node(w);
            size++;
            return;
        }
        avlInsert(root, w);
    }

    /* insert w into the tree rooted at n, maintaining AVL balance.
     * pre: n is not null */
    private void avlInsert(Node n, String w) {

        int compare = w.compareTo(n.word);

        if (n.word == w) {
            //duplicate
            return;
        }
        //if the word is smaller than the node, insert left
        else if (compare < 0) {
            if (n.left != null) {
                avlInsert(n.left, w);
            } else {
                //attach new node with w to n.left
                n.left = new Node(w);
                n.left.parent = n;
                size++;
            }
        } else{
        // otherwise, insert right
            if (n.right != null) {
                avlInsert(n.right, w);
            } else {
                n.right = new Node(w);
                n.right.parent = n;
                size++;
                
            }
        }
        n.height = 1 + Math.max(height(n.left), height(n.right));
        
        rebalance(n);
    }

//****************************ROTATIONS*****************************************************//

    /**
     * do a left rotation: rotate on the edge from x to its right child.
     * precondition: x has a non-null right child
     */
    public void leftRotate(Node x) {
        //make a node to the right of x
        Node y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        //update parent pointers!
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y; 
        }        
        else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
        //re-evaluate height of x, and then y.
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right)); 
    }

    /**
     * do a right rotation: rotate on the edge from y to its left child.
     * precondition: y has a non-null left child
     */
     //basically the same as leftRotate, but with the x/y's flipped, as well as the right/lefts.
    public void rightRotate(Node y) {
        //make a node x, to the left of y.
        Node x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            this.root = x; 
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }
        x.right = y;
        y.parent = x;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

//*****************************REBALANCE*****************************************************//

    //calculates balance factor
    private int bal(Node n) {
        if (n == null) {
            return 0;
        }
        return height(n.right) - height(n.left);
    }

    /**
     * rebalance a node N after a potentially AVL-violoting insertion.
     * precondition: none of n's descendants violates the AVL property
     */
    public void rebalance(Node n) {

        if (bal(n) < -1) {
            if (bal(n.left) < 0) {
                //case 1
                //System.out.println(":case 1");
                rightRotate(n);
            } else {
                //case 2
                //System.out.println("case 2");
                leftRotate(n.left);
                rightRotate(n);
            }
        } else if (bal(n) > 1) {
            if (bal(n.right) < 0) {
                //case 3
                //System.out.println("case 3");
                rightRotate(n.right);
                leftRotate(n);
            } else {
                //case 4
                //System.out.println("case 4:" + n.word);
                leftRotate(n);
            }
        }
    }

//*******************************REMOVE***************************************************//

    /**
     * remove the word w from the tree
     */
    public void remove(String w) {
        remove(root, w);
    }

    /* remove v from the tree rooted at n */
    private void remove(Node n, String w) {
        return; // (optional TODO - do the base assignment first)
    }

//*********************************PRINT*************************************************//

    /**
     * print a sideways representation of the tree - root at left,
     * right is up, left is down.
     */
    public void printTree() {
        printSubtree(root, 0);
    }

    private void printSubtree(Node n, int level) {
        if (n == null) {
            return;
        }
        printSubtree(n.right, level + 1);
        for (int i = 0; i < level; i++) {
            System.out.print("        ");
        }
        System.out.println(n);
        printSubtree(n.left, level + 1);
    }

//*******************************HEIGHT********************************************//    

    //returns the height of the tree
    public int height() {
        return height(root);
    }

    private int height(Node n) {
        if (n == null) {
            return -1;
        }
        return n.height;
    }

    //calculates height of node
    public int getHeight(Node n) {

        if (n == null) {
            return -1;
        }
        int leftHeight = getHeight(n.left);
        int rightHeight = getHeight(n.right);

        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        } else {
            return rightHeight + 1;
        }
    }

//*****************************NODE***************************************************//

    /**
     * inner class representing a node in the tree.
     */
    public class Node {
        public String word;
        public Node parent;
        public Node left;
        public Node right;
        public int height;

        public String toString() {
            return word + "(" + height + ")";
        }

        /**
         * constructor: gives default values to all fields
         */
        public Node() {
        }

        /**
         * constructor: sets only word
         */
        public Node(String w) {
            word = w;
        }

        /**
         * constructor: sets word and parent fields
         */
        public Node(String w, Node p) {
            word = w;
            parent = p;
        }

        /**
         * constructor: sets all fields
         */
        public Node(String w, Node p, Node l, Node r) {
            word = w;
            parent = p;
            left = l;
            right = r;
        }
    }
}
