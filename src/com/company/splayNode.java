package com.company;

/**
 * Created by Spaghetti on 8/22/2016.
 */
public class splayNode{
    private splayNode Left;
    private splayNode Right;
    private int Data;
    private int count = 1;


    public splayNode(int data) {
        Data = data;
    }

    //returns a directional child reference based on the passed direction.
    //if true, we go right, if false, we left.
    public splayNode getBool(boolean dir) {
        if(dir)
            return Right;
        return Left;
    }

    //sets a directional child reference to the passed splaynode according to
    //the passed bool. right is true, left is false.
    public void setBool(boolean dir, splayNode toSet) {
        if(dir)
            Right = toSet;
        else
            Left = toSet;
    }

    public int getData(){
        return Data;
    }

    public boolean goesToRight(splayNode other){
        return other.getData() < Data;
    }

    public boolean goesToRight(int other){
        return other < Data;
    }

    public boolean isEqual(int other){
        return other == Data;
    }

    public boolean isEqual(splayNode other){
        return other.getData() == Data;
    }

    public void Increment(){
        ++count;
    }

    public int getCount(){
        return count;
    }
}
