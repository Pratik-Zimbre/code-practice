package com.codepractice.easy;

import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
	/*
	 * remember while coding always use the most basic form of the input like
	 * percentages must always be converted into decimals
	 */
	public static void main(String[] args) throws Exception {

		/*
		 * enter principal, annual rate, number of years formula is (principal *
		 * interest rate per month * (1 + interest rate per month) raised to number of
		 * months) / ( ((1 + interest rate per month) raised to number of months) -1 )
		 */

		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter your name: ");
			String name = scanner.nextLine().replaceAll("\\s+", " ").trim();

			System.out.print("Enter your principal amount: ");
			double principal = 0.0;
			try {
				principal = scanner.nextDouble();
			} catch (Exception ex) {
				throw new Exception("Please enter principal only in decimal/integer format.");
			}

			if (principal <= 0.0) {
				throw new Exception("You cannot have principal amount as " + principal);
			}

			System.out.print("Enter your annual interest rate: ");
			double annualInterestRate = 0.0;
			try {
				annualInterestRate = scanner.nextDouble();
			} catch (Exception ex) {
				throw new Exception("Please enter annual interest rate only in decimal/integer format.");
			}

			if (annualInterestRate <= 0.0) {
				throw new Exception("You cannot have annual interest rate as " + annualInterestRate);
			}

			System.out.print("Enter your number of years: ");
			int numberOfYears = 0;
			try {
				numberOfYears = scanner.nextInt();
			} catch (Exception ex) {
				throw new Exception("Please enter number of years only in integer format.");
			}

			if (numberOfYears <= 0) {
				throw new Exception("You cannot have number of years as " + numberOfYears);
			}

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
