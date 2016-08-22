package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Spaghetti on 8/22/2016.
 */
//generates a randomized array of integers of size equal to an integer passed into the constructor.
public class randomIntArray {
    private int [] array; //the array in which the random integers are stored.
    private int Size; //the size of the array; saves us method calls.

    //constructor with specified size.
    public randomIntArray(int size){
        Size = size;
        array = new int[Size];
        Random rand = new Random();
        for(int i = 0; i < Size; ++i){
            array[i] = rand.nextInt();
        }
    }

    public randomIntArray(int size, int variance){
        Size = size;
        array = new int[Size];
        Random rand = new Random();
        for(int i = 0; i < Size; ++i){
            array[i] = rand.nextInt()%variance;
        }
    }

    //copy constructor
    public randomIntArray(randomIntArray toCopy){
        Size = toCopy.Size;
        array = new int[Size];
        for(int i = 0; i < Size; ++i){
            array[i] = toCopy.array[i];
        }
    }


    //top-down implementation of merge sort; this implementation is based on pseudocode found on wikipedia:
    //https://en.wikipedia.org/wiki/Merge_sort
    public long mergeSort(){
        long startTime = System.currentTimeMillis(); //store the current time.
        int [] array2 = new int[Size]; //initialize a secondary array to work in.
        SplitMerge(array, 0, Size, array2);

        System.out.println("\nMerge sort completed; time elapsed: " + (System.currentTimeMillis()-startTime) + " ms.");
        return System.currentTimeMillis()-startTime;

        //optional display code
        /*
        for(int i = 0; i < Size; ++i){
            if((i+1)%20 == 0)
                System.out.println(array2[i]);
            else
                System.out.print(array2[i] + ", ");
        }*/
    }

    // startIndex is inclusive; endIndex is exclusive (A[endIndex] is not in the set).
    private void SplitMerge(int [] A, int startIndex, int endIndex, int [] B) {
        if(endIndex - startIndex < 2) //subarrays of size 1 are effectively already sorted.
            return;
        // recursively split runs into two halves until run size == 1,
        // then merge them and return back up the call chain
        int middleIndex = (endIndex + startIndex) / 2; // middleIndex = mid point
        SplitMerge(A, startIndex,  middleIndex, B);  // split / merge left  half
        SplitMerge(A, middleIndex,    endIndex, B);  // split / merge right half
        Merge(A, startIndex, middleIndex, endIndex, B);  // merge the two half runs
        for(int i = startIndex; i < endIndex; ++i) //
            A[i] = B[i];
    }

    //merges the subarrays between startindex and midindex and midindex and endindex.
    private void Merge(int [] A, int startIndex, int middleIndex, int endIndex, int [] B)
    {
        int i = startIndex;
        int j = middleIndex;

        for (int k = startIndex; k < endIndex; ++k) { //for all elements of the subarray...
            if (i < middleIndex && (j >= endIndex || A[i] <= A[j])) {
                B[k] = A[i];
                ++i;
            } else {
                B[k] = A[j];
                ++j;
            }
        }
    }

    //additionally, add implementations for integer class rather than primitive int
    // startIndex is inclusive; endIndex is exclusive (A[endIndex] is not in the set).
    private void SplitMerge(Integer [] A, int startIndex, int endIndex, Integer [] B) {
        if(endIndex - startIndex < 2) //subarrays of size 1 are effectively already sorted.
            return;
        // recursively split runs into two halves until run size == 1,
        // then merge them and return back up the call chain
        int middleIndex = (endIndex + startIndex) / 2; // middleIndex = mid point
        SplitMerge(A, startIndex,  middleIndex, B);  // split / merge left  half
        SplitMerge(A, middleIndex,    endIndex, B);  // split / merge right half
        Merge(A, startIndex, middleIndex, endIndex, B);  // merge the two half runs
        for(int i = startIndex; i < endIndex; ++i) //
            A[i] = B[i];
    }

    //merges the subarrays between startindex and midindex and midindex and endindex.
    private void Merge(Integer [] A, int startIndex, int middleIndex, int endIndex, Integer [] B)
    {
        int i = startIndex;
        int j = middleIndex;

        for (int k = startIndex; k < endIndex; ++k) { //for all elements of the subarray...
            if (i < middleIndex && (j >= endIndex || A[i] <= A[j])) {
                B[k] = A[i];
                ++i;
            } else {
                B[k] = A[j];
                ++j;
            }
        }
    }

    //top-down implementation of merge sort; this implementation is based on code found on the below site:
    //http://www.growingwiththeweb.com/2015/06/bucket-sort.html
    public long bucketSort(){
        long startTime = System.currentTimeMillis(); //store the current time.

        int bucketSize = Math.round(Math.round(Math.sqrt(Size))); //set bucket size to be the square root of Size.
        int min = array[0]; //calculate the array's minimum value.
        int max = array[0]; //calculate the array's maximum value.
        for (int i = 0; i < Size; ++i) {
            if (array[i] > max) {
                max = array[i];
            }
            else if (array[i] < min) {
                min = array[i];
            }
        }

        // Initialise buckets
        int bucketCount = (max - min) / bucketSize + 1;
        List<List<Integer>> buckets = new ArrayList<List<Integer>>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        // Distribute input array values into buckets
        for (int i = 0; i < array.length; i++) {
            buckets.get((array[i] - min) / bucketSize).add(array[i]);
        }

        // Sort buckets and place back into input array
        int currentIndex = 0;
        for (int i = 0; i < buckets.size(); i++) {
            Integer[] bucketArray = new Integer[buckets.get(i).size()];
            bucketArray = buckets.get(i).toArray(bucketArray);
            Integer [] targetArray = new Integer[bucketArray.length];
            SplitMerge(bucketArray, 0, bucketArray.length, targetArray);
            for (int k = 0; k < bucketArray.length; ++k) {
                array[currentIndex++] = bucketArray[k];
            }
        }

        //Optionally, display the sorted list to test the sort's validity.
        ///*
        for(int i = 0; i < Size; ++i){
            if((i+1)%20 == 0)
                System.out.println(array[i]);
            else
                System.out.print(array[i] + ", ");
        }//*/

        System.out.println("\nBucket sort completed; time elapsed: " + (System.currentTimeMillis()-startTime) + " ms.");
        return System.currentTimeMillis()-startTime;
    }

    //Quicksort. This is a wrapper method to call the recursive method on the array.
    //all code for this was based on pseudocode found on wikipedia: https://en.wikipedia.org/wiki/Quicksort
    public long Quicksort(){

        long startTime = System.currentTimeMillis(); //store the current time.

        int p = partition(0, Size-1);
        Quicksort(0, p-1);
        Quicksort(p + 1, Size-1);

        System.out.println("\nQuicksort completed; time elapsed: " + (System.currentTimeMillis()-startTime) + " ms.");

        //optionally display...
        /*
        for(int i = 0; i < Size; ++i){
            if((i+1)%20 == 0)
                System.out.println(array[i]);
            else
                System.out.print(array[i] + ", ");
        }
        */
        return System.currentTimeMillis()-startTime;
    }

    public void Quicksort(int min, int max){
        if(min < max){
            int p = partition(min, max);
            Quicksort(min, p-1);
            Quicksort(p+1, max);
        }
    }

    public int partition(int min, int max){
        //System.out.print(min + " " + max + "; ");
        //int pivot = array[min + randPivot%(max-min)];
        int pivot = array[max];
        int i = min;        // place for swapping
        for(int j = min; j < max; ++j){
            if(array[j] <= pivot) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                ++i;
            }
        }
        int temp = array[i];
        array[i] = array[max];
        array[max] = temp;
        return i;
    }

    public long splaySort(){
        long startTime = System.currentTimeMillis(); //store the current time.

        splayTree test = new splayTree();
        for(int i = 0; i < Size; ++i){
            test.Insert(new splayNode(array[i]));
        }

        System.out.println("\nSplaysort completed; time elapsed: " + (System.currentTimeMillis()-startTime) + " ms.");

        //test.Display();
        return System.currentTimeMillis()-startTime;
    }
}
