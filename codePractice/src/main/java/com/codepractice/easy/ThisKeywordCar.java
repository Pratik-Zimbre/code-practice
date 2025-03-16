package com.codepractice.easy;

public class ThisKeywordCar {
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	private String model;
	private int year;

	public ThisKeywordCar() {
		this("Unknown Model");
		// System.out.println(this.model + " con " + this.year);
	}

	public ThisKeywordCar(String model) {
		this(model, 2025);
		this.year = 1991;
		// System.out.println(this.model + " " + this.year);
	}

	public ThisKeywordCar(String model, int year) {
		this.model = model;
		this.year = year;
		// System.out.println(this.model + " " + this.year);
	}

	public void show(String modelName) {
		System.out.println("Hello " + modelName + " " + year);
	}

	public void display() {
		show(this.model);
	}

	public int returnThis() {
		return this.year;
	}
}
