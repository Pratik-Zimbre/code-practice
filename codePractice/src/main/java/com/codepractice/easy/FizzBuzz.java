package com.codepractice.easy;

import java.util.Scanner;

public class FizzBuzz {

	public static void main(String[] args) {
		/*
		 * Simple fizzBuzz interview question, when the entered number is divisible by 3
		 * print Fizz if divisible by 5 print Buzz if divisible by both print FizzBuzz.
		 * If not divisible by 3 and 5 print the same number
		 */
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter a number: ");
			int inputNumber = 0;
			try {
				inputNumber = scanner.nextInt();
			} catch (Exception ex) {
				System.out.println("Error: Please enter a valid integer number.");
				return;
			}
			boolean isDivisibleBy3 = inputNumber % 3 == 0;
			boolean isDivisibleBy5 = inputNumber % 5 == 0;
			if (isDivisibleBy3 && isDivisibleBy5) {
				System.out.println("FizzBuzz");
			} else if (isDivisibleBy3) {
				System.out.println("Fizz");
			} else if (isDivisibleBy5) {
				System.out.println("Buzz");
			} else {
				System.out.println(inputNumber);
			}
		}
	}
}
