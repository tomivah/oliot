package project;

public class Project {

	public static void main(String[] args) {
		Menu menu = new Menu();
		// Store gets a couple of employees in the constructor
		Store store = new Store(100,10000);
		nextDay(store);

		while (true) {
			menu.show();

			switch (menu.readSelection()) {
				case 1:
					System.exit(0);
				case 2:
					nextDay(store);
					break;
				default:
					TextInterface.printLine("Invalid selection");
			}
		}
	}

	public static void nextDay(Store store) {
		DayReport dReport = new DayReport();

		for (int h = 1; h <= 12; h++) {
			nextHour(store, dReport, h);
		}

		TextInterface.printLine(dReport.toString());
	}

	public static void nextHour(Store store, DayReport dReport, int hour) {
		HourReport hReport = new HourReport();

		for (int m = 1; m <= 60; m++) {
			nextMinute(store,hReport,hour);
		}

		TextInterface.printLine(hReport.toString());
	}

	public static void nextMinute(Store store, HourReport hReport, int hour) {
		// Create customer according to probability 
		if (newCustomer(store)) {
			Customer customer = new Customer();
			store.addToLine(customer);
			hReport.customerVisited();
		}

		// Check if free employee and cutomers in line
		if (store.employeeAvailable() && store.customersInLine()) {
			Employee emp = store.getFreeEmployee();
			Customer cus = store.getFirstCustomer();
			// Where should meals be created? 
			Meal meal = new Meal("Happy meal", 7.5, 10); 
			store.addOrder(new Order(cus,emp,meal));
			hReport.addMoney(meal.getPrice());

		// if customer in line and no free employees
		} else if (store.customersInLine() && !store.employeeAvailable()) {
			// go trough all customers and add waiting minutes?
			hReport.addWaitingMinutes(1);
		}

		// Go trough orders 

	}

	public static boolean newCustomer(Store store) {
		// Do the probability magic here
		if (store.getReputation() > 0) {
			return true;
		}
		return false;

	}
}
