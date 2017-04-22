package project;

import java.util.Random;

public class Employee {

	private double performance;
	private static Random rand = new Random();

	public Employee() {
		// Performace in between 1 and 2 ??? 
		this.performance = 1 + rand.nextDouble();
	}

	public double getPerformance() {
		return this.performance;
	}

	public void setPerformance(double performance) {
		this.performance = performance;
	}
}
