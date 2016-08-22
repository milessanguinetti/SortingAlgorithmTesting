package com.company;

import java.io.PrintWriter;

/**
 * Created by Spaghetti on 8/22/2016.
 */
public class splayTree{
    private splayNode root;

    //rotational function. if dir is true, we rotate right. otherwise, we rotate left.
    private splayNode rotate(boolean dir, splayNode toRotate) {
        splayNode temp = toRotate.getBool(!dir);
        toRotate.setBool(!dir, temp.getBool(dir));
        temp.setBool(dir, toRotate);
        return temp;
    }

    //splay function; the function that allows this data structure to function
    //with runtime more on par with a balanced tree than with a common BST
    public splayNode splay(splayNode toSplay, splayNode root) {
        if (root == null)
            return null; //can't splay nothing
        if (root.isEqual(toSplay))
            return root; //can't splay something already at root, either.
        Boolean dir = !root.goesToRight(toSplay); //get whatever direction tosplay goes, relative to
        //root. true is right; we use ! to ensure that we're
        //checking toSplay's direction rather than root's.
        if (root.getBool(dir) == null)
            return root; //nothing in that direction; root will be closest in tree
        Boolean scdir = !root.getBool(dir).goesToRight(toSplay);
        //second comparison value
        if(root.getBool(dir).isEqual(toSplay)) //if these two are equal...
            return rotate(!dir, root); //return rotation
        root.getBool(dir).setBool(scdir, splay(toSplay, root.getBool(dir).getBool(scdir)));
        //set root's l/r child's l/r pointer to the return value of its own splay operation
        if (dir != scdir) {
            if (root.getBool(dir).getBool(scdir) != null)
                root.setBool(dir, rotate(dir, root.getBool(dir)));
            //if the directions are opposites and root's child/grandchild in these two directions is extant
        }   //we set root's child in the first direction to the return value of a rotational operation
        //in the same direction with it.
        else
            root = rotate(!dir, root); //root rotates in the opposite direction
        if(root.getBool(dir) == null)
            return root; //return root if it's child in the first direction is not extant
        return rotate(!dir, root); //otherwise, return a rotation in the opposite of the first direction
    }

    //splay function with a int
    public splayNode splay(int toSplay, splayNode root) {
        if (root == null)
            return null; //can't splay nothing
        if (root.isEqual(toSplay))
            return root; //can't splay something already at root, either.
        Boolean dir = !root.goesToRight(toSplay); //get whatever direction tosplay goes, relative to
        //root. true is right; we use ! to ensure that we're
        //checking toSplay's direction rather than root's.
        if (root.getBool(dir) == null)
            return root; //nothing in that direction.
        Boolean scdir = !root.getBool(dir).goesToRight(toSplay);
        //second comparison value;
        if(root.getBool(dir).isEqual(toSplay)) //if these two are equal...
            return rotate(!dir, root); //return rotation
        root.getBool(dir).setBool(scdir, splay(toSplay, root.getBool(dir).getBool(scdir)));
        //set root's l/r child's l/r pointer to the return value of its own splay operation
        if (dir != scdir) {
            if (root.getBool(dir).getBool(scdir) != null)
                root.setBool(dir, rotate(dir, root.getBool(dir)));
            //if the directions are opposites and root's child/grandchild in these two directions is extant
        }   //we set root's child in the first direction to the return value of a rotational operation
        //in the same direction with it.
        else
            root = rotate(!dir, root); //root rotates in the opposite direction
        if(root.getBool(dir) == null)
            return root; //return root if it's child in the first direction is not extant
        return rotate(!dir, root); //otherwise, return a rotation in the opposite of the first direction
    }

    public int Insert(splayNode nodeToInsert) {
        if(nodeToInsert == null)
            return 0; //can't insert a null reference or a node object that isn't of type BSTnode
        if(root == null) //if the tree is empty...
            root = nodeToInsert; //we insert at root.
        else { //otherwise...
            root = splay(nodeToInsert, root);
            if(!root.isEqual(nodeToInsert)) {
                //if root does not exist in this tree...
                boolean dir = nodeToInsert.goesToRight(root); //directional bool; true is right
                nodeToInsert.setBool(dir, root.getBool(dir)); //toinsert points to root's child in that direction
                root.setBool(dir, null); //root's pointer in that direction becomes null
                nodeToInsert.setBool(!dir, root); //point toinsert's other pointer to root
                root = nodeToInsert; //root now points to toinsert, placing it at the top of the structure
            }
            else{
                root.Increment(); //increment the data.
            }
        }
        return 1;
        //recursive call; return 1 because the method cannot be expected to fail at this point
    }

    public void Display(){
        recursiveDisplay(root);
        //call recursive display function with root as parameter
    }

    //recursive method for display.
    public void recursiveDisplay(splayNode root){
        if(root == null)
            return;
        recursiveDisplay(root.getBool(false));
        for(int i = 0; i < root.getCount(); ++i)
            System.out.println(root.getData());
        recursiveDisplay(root.getBool(true));
        //display root and make recursive display calls
        //with its left and right children as parameters.
    }
}
