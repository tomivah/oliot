package project;

public class HourReport {

	private double moneyMade;
	private int customersVisited;
	private int waitingMinutes;

	public HourReport() {
		this.moneyMade = 0;
		this.customersVisited = 0;
		this.waitingMinutes = 0;
	}

	public double getMoneyMade() {
		return this.moneyMade;
	}

	public int getCustomersVisited() {
		return this.customersVisited;
	}

	public int getWaitingMinutes() {
		return this.waitingMinutes;
	}

	public void addMoney(double amount) {
		this.moneyMade += amount;
	}

	public void customerVisited() {
		this.customersVisited++;
	}

	public void addWaitingMinutes(int amount) {
		this.waitingMinutes += amount;
	}

	@Override
	public String toString() {
		return "----- Hour Report -----" + "\n" 
				+ "Customers visited: " + this.customersVisited + "\n"
				+ "Money made: " + this.moneyMade + "\n"
				+ "Minutes waited: " + this.waitingMinutes + "\n";
	}
}
