package project;

import java.util.Random;

public class Customer {

	private int patience;
	private String name;
	private String shout;
	private static Random random = new Random();

	public Customer() {
		this.patience = random.nextInt(11) + 5;
		this.name = "Hitler Hamburger"; // Nimet ja huudot vois tässä kohti
		this.shout = "Heil Hamburger!"; // lukea muualta? 
	}

	public int getPatience() {
		return this.patience;
	}

	public String getName() {
		return this.name;
	}

	public String getShout() {
		return this.shout;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShout(String shout) {
		this.shout = shout;
	}

}
