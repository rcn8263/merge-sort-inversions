import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class MergeSort {

    public static void main(String [] args) throws FileNotFoundException {
	Scanner sc = new Scanner(new File(args[0]));
	// get the size of the array
	int n = sc.nextInt();

	// construct array and fill	
	int [] a = new int[n];
	for (int i =0;  i < n; i++)
	    a[i] = sc.nextInt();

	// mergeSort the array
	mergeSort(a);
	// if array length < 20, print it
        if (n < 20)
	   System.out.println(Arrays.toString(a));
	// else for longer arrays, check the ordering	
	else {
	    for (int i  = 0; i < a.length-1; i++)
		if (a[i] > a[i+1])
		    System.out.println("UNSORTED");
	    System.out.println("If working, this is the only output.");
	}
    }

	public static void mergeSort(int [] a) {
		if (a.length <= 1) {
			//there are no inversions
		}
		else {
			//Divide the list into two halves:
			int lenLeft = (int) Math.ceil((double) a.length / 2);
			int lenRight = (int) Math.floor((double) a.length / 2);
			int[] left = new int[lenLeft];
			int[] right = new int[lenRight];

			for (int i = 0; i < lenLeft; i++) {
				left[i] = a[i];
			}
			for (int i = 0; i < lenRight; i++) {
				right[i] = a[lenLeft + i];
			}

			//Sort left
			//System.out.println("Left: " + Arrays.toString(left));
			mergeSort(left);
			//System.out.println("Left: " + Arrays.toString(left));

			//Sort right
			mergeSort(right);

			//Merge left and right
			int temp[] = merge(left, right);

			for (int i = 0; i < a.length; i++) {
				a[i] = temp[i];
			}
		}
	}

    public static int[] merge(int[] A, int[] B) {
		//Maintain a Current pointer into each list, initialized to
		//point to the front elements
    	int pointA = 0, pointB = 0;

    	int[] output = new int[A.length + B.length];
    	int pointOutput = 0;

		//While both lists are nonempty:
		while (pointA < A.length && pointB < B.length) {
			//Let ai and bj be the elements pointed to by the Current pointer
			int ai = A[pointA];
			int bj = B[pointB];

			//Append the smaller of these two to the output list
			if (ai <= bj) {
				output[pointOutput] = ai;
				pointA++;
			}
			else {	//bj is the smaller element
				output[pointOutput] = bj;
				pointB++;
			}
			pointOutput++;

		}//end while

		//Once one list is empty, append the remainder of the other list to the output
		if (pointA < A.length) {	//values remain in A
			while (pointA < A.length) {
				output[pointOutput] = A[pointA];
				pointA++;
				pointOutput++;
			}
		}
		else if (pointB < B.length) {	//values remain in B
			while (pointB < B.length) {
				output[pointOutput] = B[pointB];
				pointB++;
				pointOutput++;
			}
		}

		//Return merged list
    	return output;
	}

}

