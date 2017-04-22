package project;

import java.util.Random;

public class Customer {

	private int patience;
	private String name;
	private String shout;
	private static Random random = new Random();

	public Customer() {
		this.patience = random.nextInt(11) + 5;
	 	// Read names and shouts from files or something?
		this.name = "Hitler Hamburger";
		this.shout = "Heil Hamburger!"; 
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
