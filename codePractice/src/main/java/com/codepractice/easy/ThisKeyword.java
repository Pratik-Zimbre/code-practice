package com.codepractice.easy;

public class ThisKeyword {
	int i = 10;

	public static void main(String[] args) {
		double i = 5.0;
		System.out.println(i);
		ThisKeyword obj = new ThisKeyword();
		System.out.println(obj.i);
		int System = 11;
		java.lang.System.out.println(System);
	}
}
