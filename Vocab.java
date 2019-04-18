/***********
Roman Alonzo
CS 241
Assignment 2
10/30/2018
The Vocab class contains the main method for this assignment, and acts as an input and file reader.
It also makes calls to the AVL and Count classes, for updates and testing.
***********/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Vocab {

    public static void main(String[] args) {
      //couple try blocks to make sure a file is found, switches to standard input if not.
      try{
        try {
            File f = new File(args[0]);
            Scanner sc = new Scanner(f);
            Count c = wordCount(sc);
            System.out.println(c);
         }
         catch(ArrayIndexOutOfBoundsException e){
            System.out.println("No file");  
            
            //if no file, read user input
            System.out.println("Please input words to be counted: ");
            Scanner in = new Scanner(System.in); 
            while (in.hasNext()){
               wordCount(in);
            }        
         }  
       }    
        catch (FileNotFoundException exc) {
          System.out.println("Could not find file " + args[0]);
          
        }

        AVL tree = new AVL();
      
//*****************************Testing********************************//
  
        //BST Insertion tests
        
//         tree.bstInsert("g");
//          tree.bstInsert("b");
//          tree.bstInsert("k");
//          tree.bstInsert("a");
//          tree.bstInsert("c");
//          tree.bstInsert("i");
//          tree.bstInsert("l");
//          tree.bstInsert("x");
//          tree.bstInsert("y");
        //tree.leftRotate(tree.root.right.right);
        //tree.rebalance(tree.root);
        //tree.printTree();
        //System.out.println("left rotation");
//         tree.rightRotate(tree.root);
//                 tree.rebalance(tree.root);
//                 tree.rightRotate(tree.root);
//                 tree.rebalance(tree.root);
//         tree.rebalance(tree.root);

        //tree.printTree();
        //System.out.println("right rotation");
        //tree.rightRotate(tree.root);
        //tree.printTree();
//        
//         System.out.println("Size of the tree: " + tree.getSize());
// 
//         System.out.println("Height of the tree : " + tree.height());
// 
//         System.out.println("Left rotation on root: ");
//         tree.leftRotate(tree.root);
//         tree.printTree();
//         
//         System.out.println("Right rotation on root: ");
//         tree.rightRotate(tree.root);
//         tree.printTree();
//         
        // System.out.println("avl insert: ");
         // tree.avlInsert("a");
//          tree.avlInsert("c");
//          tree.avlInsert("d");
//          tree.avlInsert("b");
//          tree.avlInsert("f");      
//          tree.avlInsert("i");
//         tree.avlInsert("j");
//         tree.avlInsert("k");
//         //tree.leftRotate(tree.root);
//         tree.avlInsert("a");
//         tree.avlInsert("x");
//         tree.avlInsert("z");
//         tree.avlInsert("y");
//         
        //UNCOMMENT TO PRINT TREES MANUALLY
        //tree.printTree();
        //System.out.println("Size of the tree: " + tree.getSize()); 
        //System.out.println("Height of the tree : " + tree.height());
    }
//**************************************************************************//

    /* count the total and unique words in a document being read
     * by the given Scanner. return the two values in a Count object.*/
    private static Count wordCount(Scanner sc) {
    
        AVL tree = new AVL();
        
        Count c = new Count();

        // TODO: fill in the unique and total fields of c
        // before c is returned

        while (sc.hasNext()) {
            // read and parse each word
            String word = sc.next();
            //if the tree doesn't contain the word, add the total and unique counters
            if (tree.search(word) == null){
               c.total++;
               c.unique++;
            }
            //otherwise, just increase the total.
            else{
               c.total++;
            }            

            // remove non-letter characters, convert to lower case:
            word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();
            tree.avlInsert(word);
        }
        return c;

      }
}
