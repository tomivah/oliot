package project;

import java.util.Random;

public class Customer {

	private int patience;
	private String name;
	private String shout;
	private int minutesInLine;
	private int minutesWaiting;
	private static Random random = new Random();

	public Customer() {
		this.patience = random.nextInt(11) + 5;
		this.minutesInLine = 0;
		this.minutesWaiting = 0;
		// Read names and shouts from files or something?
		this.name = "Hitler Hamburger";
		this.shout = "Heil Hamburger!";
	}

	public int getMinutesInLine() {
		return minutesInLine;
	}

	public int getMinutesWaiting() {
		return minutesWaiting;
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

	public void addWaitingMinute() {
		this.minutesWaiting++;
	}

	public void addLineMinute() {
		this.minutesInLine++;
	}

	public int getSatisfaction() {
		return this.patience - this.minutesInLine;
	}

}
