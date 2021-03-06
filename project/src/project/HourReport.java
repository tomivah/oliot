package project;

public class HourReport {

	private double moneyMade;
	private int customersVisited;
	private int totalWaiting;
	private int totalInLine;
	private double averageWaiting;
	private double averageInLine;
	private int reputationChange;
	private int hour;

	public HourReport(int hour) {
		this.hour = hour;
	}

	public double getMoneyMade() {
		return this.moneyMade;
	}

	public int getCustomersVisited() {
		return this.customersVisited;
	}

	public int getWaitingMinutes() {
		return this.totalWaiting;
	}

	public int getInLineMinutes() {
		return this.totalInLine;
	}

	public int getReputationChange() {
		return this.reputationChange;
	}

	public void addMoney(double amount) {
		this.moneyMade += amount;
	}

	public void customerVisited() {
		this.customersVisited++;
	}

	public void addWaitingMinutes(int amount) {
		this.totalWaiting += amount;
	}

	public void addInLineMinutes(int amount) {
		this.totalInLine += amount;
	}

	public void calculateAverages() {
		this.averageInLine = this.totalInLine / this.customersVisited;
		this.averageWaiting = this.totalWaiting / this.customersVisited;
	}

	public void addReputationChange(int amount) {
		this.reputationChange += amount;
	}

	@Override
	public String toString() {
		return "\n----- Hour Report [" + this.hour + "] -----" + "\n"
				+ "Customers visited: " + this.customersVisited + "\n"
				+ "Money made: " + this.moneyMade + "\n"
				+ "Average minutes waited: " + this.averageWaiting + "\n"
				+ "Average minutes in line: " + this.averageInLine + "\n";
	}
}
