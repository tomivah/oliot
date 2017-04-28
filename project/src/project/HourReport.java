package project;

public class HourReport {

	private double moneyMade;
	private int customersVisited;
	private int totalWaiting;
	private int totalInLine;
	private double averageWaiting;
	private double averageInLine;

	public HourReport() {
		this.moneyMade = 0;
		this.customersVisited = 0;
		this.averageWaiting = 0;
		this.averageInLine = 0;
		this.totalInLine = 0;
		this.totalWaiting = 0;
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

	@Override
	public String toString() {
		return "----- Hour Report -----" + "\n"
				+ "Customers visited: " + this.customersVisited + "\n"
				+ "Money made: " + this.moneyMade + "\n"
				+ "Average minutes waited: " + this.averageWaiting + "\n"
				+ "Average minutes in line: " + this.averageInLine + "\n";
	}
}
