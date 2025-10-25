package Week9Prac;

import java.util.Scanner;

public class ArrayPlay {

	  // fields array storage and logical count of elements
	  private int[] array;
	  private int count;

	  // constructor create with given physical size initially empty
	  public ArrayPlay(int size) {
		  if (size <= 0) size = 100;
	    this.array = new int[size];
	    this.count = 0;
	  }
	  
	  // constructor create with the existing array and count
	    public ArrayPlay(int[] array, int count) {
	        this.array = array;
	        this.count = Math.min(count, array.length);
	    }

	    // Getter for internal array (note: returns reference)
	    public int[] getArray() {
	        return array;
	    }

	    // Getter for logical count
	    public int getCount() {
	        return count;
	    }

	    // Setter for count (useful when file reading fills array externally)
	    public void setCount(int count) {
	        this.count = Math.min(count, array.length);
	    }
	    
	    // implemented methods
	    // display all the values from beginning to logical end
	    public void displayArray() {
	    	if (count == 0) {
	    		System.out.println("Array is empty");
	    		return;
	        }
	        System.out.println("Array contents (forward):");
	        for (int i = 0; i < count; i++) {
	            System.out.println(array[i]);
	        }
	    }
	    // calculate the total sum average of logical elements
	    public int totalArray() {
	    	int sum = 0;
	        for (int i = 0; i < count; i++) {
	            sum += array[i];
	        }
	        return sum;
	    }
	    // calculate average of the logical elements
	    public double average() {
	    	if (count == 0) return 0.0;
	    	return (double) totalArray() / count;
	    }
	}

