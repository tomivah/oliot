package project;

import java.util.Random;

public class Employee {

	private double performance;
	private double wage;
	private static Random rand = new Random();

	public Employee() {
		// Performance in between 1 and 2 ??? 
		this.performance = 1 + rand.nextDouble();
		this.wage = 9.5;
	}

	public double getPerformance() {
		return this.performance;
	}

	public void setPerformance(double performance) {
		this.performance = performance;
	}

	public double getWage() {
		return this.wage;
	}
}
