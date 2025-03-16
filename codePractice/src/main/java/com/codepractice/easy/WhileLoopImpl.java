package com.codepractice.easy;

import java.util.Scanner;

public class WhileLoopImpl {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
//			System.out.print("Enter anything I will echo if you want to escape enter \"quit\": ");
//			String inputString = scanner.nextLine().trim().replaceAll("\\s+", " ");
//
//			while (!inputString.equalsIgnoreCase("quit")) {
//				if (!inputString.isEmpty()) {
//					System.out.println(inputString);
//				}
//				System.out.print("Enter anything I will echo if you want to escape enter \"quit\": ");
//				inputString = scanner.nextLine().trim().replaceAll("\\s+", " ");
//				// if these 16 and 17 lines are not there the loop will never end
//			}

//          a much cleaner way to do the same code as above
			String inputString = "";
			while (true) {
				if (!inputString.isEmpty() && !inputString.equalsIgnoreCase("pass")) {
					System.out.println(inputString);
				}
				System.out.print("Enter anything I will echo if you want to escape enter \"quit\": ");
				inputString = scanner.nextLine().trim().replaceAll("\\s+", " ");
				// if these 27 and 28 lines are not there the loop will never end
				if (inputString.equalsIgnoreCase("pass")) {
					continue;
				}
				if (inputString.equalsIgnoreCase("quit")) {
					break;
				}
			}
		}
	}
}
