package project;

public class DayReport {

	private double moneyMade;
	private int customersVisited;
	private int waitingMinutes;
	private int reputationChange;
	private Store store;

	public DayReport(Store store) {
		this.store = store;
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

	public double getReputationChange() {
		return this.reputationChange;
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

	public void addReputationChange(double amount) {
		this.reputationChange += amount;
	}

	public void update(HourReport hourReport) {
		// Get stuff from hour report and add to day report
		this.moneyMade += hourReport.getMoneyMade();
		this.customersVisited += hourReport.getCustomersVisited();
		this.reputationChange += hourReport.getReputationChange();
	}

	@Override
	public String toString() {
		double moneyLost = this.store.getDailyWages();
		// Plus sign for reputation change
		String sign = ""; 
		if (this.reputationChange > 0) {
			sign = "+";
		}
		return "\n---- Day Report ---- " + "\n"
				+ "Customers visited: " + this.customersVisited + "\n"
				+ "Money made: " + this.moneyMade + "\n"
				+ "Money lost: " + moneyLost +"\n"
				+ "Money total: " + (this.moneyMade - moneyLost) + "\n"
				+ "Minutes waited: " + this.waitingMinutes + "\n"
				+ "Reputation change: " + sign + this.reputationChange + "\n";

	}
}
