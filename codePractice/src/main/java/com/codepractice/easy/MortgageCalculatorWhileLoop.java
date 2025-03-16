package com.codepractice.easy;

import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculatorWhileLoop {

	public static void main(String[] args) throws Exception {
		/*
		 * enter principal, annual rate, number of years formula is (principal *
		 * interest rate per month * (1 + interest rate per month) raised to number of
		 * months) / ( ((1 + interest rate per month) raised to number of months) -1 ).
		 * Do error handling using do while loops
		 */

		/*
		 * we can use a do-while as well as a infinite while loop with a mandatory break
		 * statement in it
		 */
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter your name: ");
			String name = scanner.nextLine().replaceAll("\\s+", " ").trim();
			double principal = 0.0;
			double annualInterestRate = 0.0;
			int numberOfYears = 0;
			boolean isValidPrincipal = false;
			do {
				System.out.print("Enter the principal amount (must be from 1K to 1M) : ");
				principal = scanner.nextDouble();
				isValidPrincipal = principal >= 1000 && principal <= 1_000_000;
				if (!isValidPrincipal) {
					System.out.println("Invalid principal amount, must be from 1K to 1M");
				}
			} while (!isValidPrincipal);
			System.out.println("Principal amount accepted!");

			boolean isValidAnnualInterest = false;
			while (true) {
				System.out.print("Enter the annual interest rate (must be from 1% to 30%): ");
				annualInterestRate = scanner.nextDouble();
				isValidAnnualInterest = annualInterestRate >= 1 && annualInterestRate <= 30;
				if (!isValidAnnualInterest) {
					System.out.println("Invalid annual interest rate, must be from 1% to 30%");
				}
				if (isValidAnnualInterest) {
					System.out.println("Annual interest rate accepted!");
					break;
				}
			}

			boolean isValidNumberOfYears = false;
			do {
				System.out.print("Enter the number of years (must be from 1 to 30 years): ");
				numberOfYears = scanner.nextInt();
				isValidNumberOfYears = numberOfYears >= 1 && numberOfYears <= 30;
				if (!isValidNumberOfYears) {
					System.out.println("Invalid number of years, must be from 1 to 30 years");
				}
			} while (!isValidNumberOfYears);
			System.out.println("Number of years accepted!");

			/*
			 * you forgot to divide the input annualInterestRate by 100 as it is a
			 * percentage so we need to convert it into decimal format
			 */
			double monthlyInterestRate = (annualInterestRate / 100) / 12;
			int numberOfMonths = numberOfYears * 12;

			double calculateNumerator = principal * monthlyInterestRate
					* Math.pow((1 + monthlyInterestRate), numberOfMonths);
			double calculateDinominator = Math.pow((1 + monthlyInterestRate), numberOfMonths) - 1;

			double mortgage = 0.0;
			try {
				mortgage = calculateNumerator / calculateDinominator;
			} catch (Exception ex) {
				throw new Exception("Cannot calculate the mortgage for given input, some issue with code.");
			}
			if (mortgage == Double.POSITIVE_INFINITY || mortgage == Double.NaN) {
				throw new Exception("Cannot calculate the mortgage for given input");
			}
			System.out.println("Mr/Mrs. " + name + ", your mortgage amount will be: "
					+ NumberFormat.getCurrencyInstance().format(mortgage));
		}
	}

}
