package com.company;

/**
 * Created by Spaghetti on 8/22/2016.
 */
public class tests {
    private final float numTests = 100f;

    public tests(int which, int size, int variance){
        if(which == 0){
            testPreviouslySortedArray(size, variance);
        }else
            testUnsortedArray(size, variance);
    }

    //tests average performance previously sorted arrays for each sorting algorithm.
    public void testPreviouslySortedArray(int size, int variance){
        randomIntArray test = new randomIntArray(size, variance);
        test.mergeSort(); //sort test.
        long mergetotal = 0, buckettotal = 0, quicktotal = 0, splaytotal = 0, nativetotal = 0;
        for(int i = 0; i < numTests; ++i){
            randomIntArray test1 = new randomIntArray(test); //duplicate
            mergetotal += test1.mergeSort();
            randomIntArray test2 = new randomIntArray(test); //duplicate
            buckettotal += test2.bucketSort();
            //randomIntArray test3 = new randomIntArray(test); //duplicate
            //quicktotal += test3.Quicksort();
            randomIntArray test4 = new randomIntArray(test); //duplicate
            splaytotal += test4.splaySort();
            randomIntArray test5 = new randomIntArray(test); //duplicate
            nativetotal += test5.nativeSort();
        }
        System.out.println("Total times for sorted test of size " + size + " array with variance " + variance +":");
        System.out.println("Merge total: " + mergetotal/numTests + "ms");
        System.out.println("Bucket total: " + buckettotal/numTests + "ms");
        System.out.println("Quick total: Stack Overflow");
        System.out.println("Splay total: " + splaytotal/numTests + "ms");
        System.out.println("Native total: " + nativetotal/numTests + "ms");
    }

    //tests average performance for unsorted arrays for each sorting algorithm.
    public void testUnsortedArray(int size, int variance){
        long mergetotal = 0, buckettotal = 0, quicktotal = 0, splaytotal = 0, nativetotal = 0;
        for(int i = 0; i < numTests; ++i){
            randomIntArray test = new randomIntArray(size, variance);
            randomIntArray test1 = new randomIntArray(test); //duplicate
            mergetotal += test1.mergeSort();
            randomIntArray test2 = new randomIntArray(test); //duplicate
            buckettotal += test2.bucketSort();
            randomIntArray test3 = new randomIntArray(test); //duplicate
            quicktotal += test3.Quicksort();
            randomIntArray test4 = new randomIntArray(test); //duplicate
            splaytotal += test4.splaySort();
            randomIntArray test5 = new randomIntArray(test); //duplicate
            nativetotal += test5.nativeSort();
        }
        System.out.println("Total times for unsorted test of size " + size + " array with variance " + variance +":");
        System.out.println("Merge total: " + mergetotal/numTests + "ms");
        System.out.println("Bucket total: " + buckettotal/numTests + "ms");
        System.out.println("Quick total: " + quicktotal/numTests + "ms");
        System.out.println("Splay total: " + splaytotal/numTests + "ms");
        System.out.println("Native total: " + nativetotal/numTests + "ms");
    }

}
