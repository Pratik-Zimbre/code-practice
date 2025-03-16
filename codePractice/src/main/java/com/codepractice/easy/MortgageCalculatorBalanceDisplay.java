package com.codepractice.easy;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MortgageCalculatorBalanceDisplay {

	public static void main(String[] args) throws Exception {
		/*
		 * enter principal, annual rate, number of years formula is (principal *
		 * interest rate per month * (1 + interest rate per month) raised to number of
		 * months) / ( ((1 + interest rate per month) raised to number of months) -1 ).
		 * Do error handling using do while loops here also display the balance
		 * count-down
		 */
		/*
		 * Corrections: The logic for calculating payment schedule was flawed, as user
		 * always wants how much is the he/she paid back to loan giver from the original
		 * principal amount the interest on that principal is calculated separately
		 */
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter your name: ");
			String name = scanner.nextLine().replaceAll("\\s+", " ").trim();

			double principal = validateInputAndGetValue(scanner,
					"Enter the principal amount (must be from 1 Lakh to 1 Crore) : ", 1_00_000, 10_00_00_000,
					"Invalid principal amount, must be from 1 Lakh to 1 Crore");

			double annualInterestRate = validateInputAndGetValue(scanner,
					"Enter the annual interest rate (must be from 1% to 20%): ", 1, 20,
					"Invalid annual interest rate, must be from 1% to 20%");

			int numberOfYears = (int) validateInputAndGetValue(scanner,
					"Enter the number of years (must be from 1 to 30 years): ", 1, 30,
					"Invalid number of years, must be from 1 to 30 years");

			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate inputDate = validateInputDateAndGetValue(scanner,
					"Enter the date you want to start the loan (must be from 01-01-1800 to 31-12-9999 date): ",
					"01-01-1800", "31-12-9999", "Invalid date entered, must be from 01-01-1800 to 31-12-9999 date",
					format);

			double mortgage = calculateMortgage(principal, annualInterestRate, numberOfYears);
			double totalAmountPayable = mortgage * (numberOfYears * 12);
			System.out.println("\n");
			System.out.println("Mortgage");
			System.out.println("---------");
			System.out.println("Mr/Mrs. " + name + ", your mortgage amount will be: "
					+ NumberFormat.getCurrencyInstance().format(mortgage) + " and the total amount payable is: "
					+ NumberFormat.getCurrencyInstance().format(totalAmountPayable));
			getPaymentSchedule(principal, annualInterestRate, numberOfYears, mortgage, inputDate, format);
		}
	}

	private static double validateInputAndGetValue(Scanner scanner, String initalPrompt, int minValue, int maxValue,
			String errorPrompt) {
		double inputValue = 0;
		boolean isValidInput = false;
		do {
			System.out.print(initalPrompt);
			inputValue = scanner.nextDouble();
			isValidInput = inputValue >= minValue && inputValue <= maxValue;
			if (!isValidInput) {
				System.out.println(errorPrompt);
			}
		} while (!isValidInput);
		return inputValue;
	}

	private static LocalDate validateInputDateAndGetValue(Scanner scanner, String initialPrompt, String minValue,
			String maxValue, String errorPrompt, DateTimeFormatter format) {
		LocalDate returnValue = null;
		String inputValue = null;
		boolean isValidInput = false;

		// Parse min and max values once
		LocalDate minDate = LocalDate.parse(minValue, format);
		LocalDate maxDate = LocalDate.parse(maxValue, format);

		do {
			System.out.print(initialPrompt);
			inputValue = scanner.next();
			try {
				LocalDate currentDate = LocalDate.parse(inputValue, format);
				isValidInput = currentDate.isAfter(minDate) && currentDate.isBefore(maxDate);
				if (!isValidInput) {
					System.out.println(errorPrompt);
				} else {
					returnValue = currentDate;
				}
			} catch (DateTimeParseException ex) {
				System.out
						.println("Input date is not in the expected format (" + "dd-MM-yyyy" + "). Please try again.");
				isValidInput = false;
			}
		} while (!isValidInput);

		return returnValue;
	}

	private static double calculateMortgage(double principal, double annualInterestRate, int numberOfYears)
			throws Exception {
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
		return mortgage;
	}

	private static void getPaymentSchedule(double principal, double annualInterestRate, int numberOfYears,
			double mortgage, LocalDate inputDate, DateTimeFormatter format) {
		double monthlyInterestRate = (annualInterestRate / 100) / 12;
		double balance = principal; // Start with the original principal
		int totalMonths = numberOfYears * 12;

		System.out.println("\nPayment Schedule:");
		for (int month = 1; month <= totalMonths; month++) {
			double interest = balance * monthlyInterestRate;
			double principalPaid = mortgage - interest;
			balance -= principalPaid; // Reduce principal

			// Get yearly balance
//			if (month % 12 == 0) {
//				int year = month / 12;
//				System.out.println("Year wise" + year + " (" + inputDate.plusYears(year).format(format)
//						+ "): Balance = " + NumberFormat.getCurrencyInstance().format(balance));
//			}
			if (month == 1 || inputDate.plusMonths(month).getMonth().equals(Month.JANUARY)) {
				System.out.println("Year: " + inputDate.plusMonths(month).getYear());
			}
			System.out
					.println("Payment Number " + month + "." + " On date " + inputDate.plusMonths(month).format(format)
							+ " interest charged is: " + NumberFormat.getCurrencyInstance().format(interest)
							+ " and your balance = " + NumberFormat.getCurrencyInstance().format(balance));

			// Stop if balance is fully paid
			if (balance <= 0)
				break;
		}
	}
//  Rough Work
//	private static void getPaymentSchedule(int numberOfYears, double mortgage, double totalAmountPayable,
//			LocalDate inputDate, DateTimeFormatter format) {
//		double newTotalAmountPayable = totalAmountPayable;
//		double mortgagePaidPerYear = mortgage * 12;
//		System.out.println("\n");
//		System.out.println("--------------------------------------------------------");
//		System.out.println("Payment Schdule Per Year from " + inputDate.format(format) + " till "
//				+ inputDate.plusYears(numberOfYears).format(format));
//		System.out.println("--------------------------------------------------------");
//
//		for (int i = 1; i <= numberOfYears; i++) {
//			newTotalAmountPayable = newTotalAmountPayable - mortgagePaidPerYear;
//			System.out.println("By the date " + inputDate.plusYears(i).format(format) + " your balance will be: "
//					+ NumberFormat.getCurrencyInstance().format(newTotalAmountPayable));
//		}
//	}
//
//	private static void getPaymentSchedule(double principal, double annualInterestRate, int numberOfYears,
//			double mortgage, LocalDate inputDate, DateTimeFormatter format, double totalAmountPayable) {
//		double monthlyInterestRate = (annualInterestRate / 100) / 12;
//		double balance = principal; // Remaining principal
//		double totalPaid = 0; // Cumulative payments made
//		int totalMonths = numberOfYears * 12;
//
//		System.out.println("\nYearly Payment Schedule:");
//		System.out.println("--------------------------------------------------------");
//		System.out.printf("%-10s | %-15s | %-15s | %-15s%n", "Date", "Remaining Balance", "Total Paid",
//				"Total Payable");
//		System.out.println("--------------------------------------------------------");
//
//		for (int month = 1; month <= totalMonths; month++) {
//			double interest = balance * monthlyInterestRate;
//			double principalPaid = mortgage - interest;
//			balance -= principalPaid; // Reduce principal
//			totalPaid += mortgage; // Track cumulative payments
//
//			if (month % 12 == 0) {
//				int year = month / 12;
//				System.out.printf("%-10d | %-15s | %-15s | %-15s%n", year,
//						NumberFormat.getCurrencyInstance().format(balance),
//						NumberFormat.getCurrencyInstance().format(totalPaid),
//						NumberFormat.getCurrencyInstance().format(totalAmountPayable));
//			}
//
//			if (balance <= 0)
//				break;
//		}
//	}
}
