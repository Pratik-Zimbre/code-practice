package com.codepractice.easy;

import java.util.Scanner;

public class DoWhileImpl {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			boolean isValidPassword = false;
			do {
				System.out.print("Enter a password" + "\n"
						+ "(must be at least 8 characters long and cannot contain any white-spaces) : ");
				String inputPass = scanner.nextLine();
				isValidPassword = inputPass.length() >= 8 && !inputPass.contains(" ");
				if (!isValidPassword) {
					System.out.println("Invalid password" + "\n"
							+ "must be atleast 8 characters long and should not contain any white-spaces");
				}
			} while (!isValidPassword);
			System.out.println("Password accepted!");
		}
	}

}
