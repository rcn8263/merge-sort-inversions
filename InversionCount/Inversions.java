public class Inversions {

	public static long inversions(int [] a) {
		if (a.length <= 1) {
			//there are no inversions
			return 0;
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
			long countLeft = inversions(left);

			//Sort right
			long countRight = inversions(right);

			//Merge left and right
			int[] output = new int[a.length];
			long countMerge = merge(left, right, output);

			for (int i = 0; i < a.length; i++) {
				a[i] = output[i];
			}

			return countLeft + countRight + countMerge;
		}
	}

	public static long merge(int[] A, int[] B, int[] output) {
		//Maintain a Current pointer into each list, initialized to
		//point to the front elements
		int pointA = 0, pointB = 0;
		long count = 0;	//number of inversions

		//int[] output = new int[A.length + B.length];
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

				//Increment Count by the number of elements remaining in A
				count += A.length - pointA;
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
		return count;
	}

}

