package com.codepractice.dsa;

public class MultiDimensionalArrayQuestions {

	public static void main(String[] args) {
		// TODO: Understand line by line after java course done
		/*
		 * Q1: Use a 2-D array and print it
		 */
		int[][] arr = new int[2][2];
		arr[0][0] = 1;
		arr[0][1] = 2;
		arr[1][0] = 3;
		arr[1][1] = 4;

		System.out.print("2D normal: " + "[");
		for (int i = 0; i < arr.length; i++) {
			System.out.print("[");
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]);
				if (j < arr[i].length - 1) {
					System.out.print(", ");
				}
			}
			System.out.print("]");
			if (i < arr.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
//		System.out.println("2D deepToString: " + Arrays.deepToString(arr)); // from line 14 to 28 can be done with
		// single line
		// Note: arr.length is the length of entire 2D array.
		// arr[i].length is length of 1 inner array which is 2

		/*
		 * Q2: Use a 3-D array and print it
		 */
		int[][][] threeD = { { { 1, 2, 3 }, { 4, 5, 6 } }, { { 7, 8, 9 }, { 10, 11, 12 } } };
		System.out.print("3D normal: " + "[");
		for (int i = 0; i < threeD.length; i++) {
			System.out.print("[");
			for (int j = 0; j < threeD[i].length; j++) {
				System.out.print("[");
				for (int k = 0; k < threeD[i][j].length; k++) {
					System.out.print(threeD[i][j][k]);
					if (k < threeD[i][j].length - 1) {
						System.out.print(", ");
					}
				}
				System.out.print("]");
				if (j < threeD[i].length - 1) {
					System.out.print(", ");
				}
			}
			System.out.print("]");
			if (i < threeD.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
//		System.out.println("3D deepToString: " + Arrays.deepToString(threeD));

		/*
		 * Q3: Use a 2-D array and pad with zero wherever the length of inner-most array
		 * is less than the maximum inner array length
		 */

		int[][] twoD = { { 1, 2 }, { 3 }, { 4 } };
		int maxLengthOfTwoD = 0;
		for (int i = 0; i < twoD.length; i++) {
			if (twoD[i].length > maxLengthOfTwoD) {
				maxLengthOfTwoD = twoD[i].length;
			}
		}

		for (int i = 0; i < twoD.length; i++) {
			if (twoD[i].length < maxLengthOfTwoD) {
				int[] paddedTwoD = new int[maxLengthOfTwoD];
				for (int j = 0; j < twoD[i].length; j++) {
					System.arraycopy(twoD[i], 0, paddedTwoD, 0, twoD[i].length);
					// instead of System.arraycopy
					// paddedTwoD[j] = twoD[i][j];
				}
				// here we are updating the original array, remember in java int[] or int[][] or
				// int[][][] are mutable
				twoD[i] = paddedTwoD;
			}
		}

		System.out.print("Padded 2D normal: " + "[");
		for (int i = 0; i < twoD.length; i++) {
			System.out.print("[");
			for (int j = 0; j < twoD[i].length; j++) {
				System.out.print(twoD[i][j]);
				if (j < twoD[i].length - 1) {
					System.out.print(", ");
				}
			}
			System.out.print("]");
			if (i < twoD.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");

		/*
		 * Q4: Use a 3-D array and pad with zero wherever the length of inner-most array
		 * is less than the maximum inner array length
		 */
		int[][][] threeDArray = { { { 1 }, { 3, 4, 5 }, { 6, 7, 8, 9 }, {} } };
		// Step 1: Find the maximum length of the innermost arrays
		int maxLength = 0;
		for (int i = 0; i < threeDArray.length; i++) {
			for (int j = 0; j < threeDArray[i].length; j++) {
				if (threeDArray[i][j].length > maxLength) {
					maxLength = threeDArray[i][j].length;
				}
			}
		}

		// Step 2: Pad the shorter arrays with zeros
		for (int i = 0; i < threeDArray.length; i++) {
			for (int j = 0; j < threeDArray[i].length; j++) {
				if (threeDArray[i][j].length < maxLength) {
					int[] paddedArray = new int[maxLength];
					// Manually copy elements from the original array to the padded array
					for (int k = 0; k < threeDArray[i][j].length; k++) {
						System.arraycopy(threeDArray[i][j], 0, paddedArray, 0, threeDArray[i][j].length);
						// instead of System.arraycopy
						// paddedArray[k] = threeDArray[i][j][k];
					}
					// The remaining positions are already initialized to 0
					threeDArray[i][j] = paddedArray; // Replace the original array with the padded one
				}
			}
		}

		// Print the modified 3D array
		System.out.print("Padded 3D normal: [");
		for (int i = 0; i < threeDArray.length; i++) {
			System.out.print("[");
			for (int j = 0; j < threeDArray[i].length; j++) {
				System.out.print("[");
				for (int k = 0; k < threeDArray[i][j].length; k++) {
					System.out.print(threeDArray[i][j][k]);
					if (k < threeDArray[i][j].length - 1) {
						System.out.print(", ");
					}
				}
				System.out.print("]");
				if (j < threeDArray[i].length - 1) {
					System.out.print(", ");
				}
			}
			System.out.print("]");
			if (i < threeDArray.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}

}
