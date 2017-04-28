package project;

import java.util.ArrayList;

public class Project {

	public static void main(String[] args) {
		Menu menu = new Menu();
		Store store = new Store(100, 10000, 7, 19);
		nextDay(store);

		while (true) {
			menu.show();

			switch (menu.readSelection()) {
				case 1:
					System.exit(0);
				case 2:
					nextDay(store);
					break;
				case 3:
					TextInterface.printLine(store.toString());
					break;
				default:
					TextInterface.printLine("Invalid selection");
			}
		}
	}

	public static void nextDay(Store store) {
		DayReport dReport = new DayReport();
		int openingHour = store.getOpeningHour();
		int closingHour = store.getClosingHour();

		for (int h = openingHour; h < closingHour; h++) {
			nextHour(store, dReport, h);
		}

		store.clearStore();
		TextInterface.printLine(dReport.toString());
	}

	public static void nextHour(Store store, DayReport dReport, int hour) {
		HourReport hReport = new HourReport(hour);

		for (int m = 1; m <= 60; m++) {
			nextMinute(store, hReport, hour);
		}

		dReport.update(hReport);
		hReport.calculateAverages();
		TextInterface.printLine(hReport.toString());
		store.payWages();
		TextInterface.readLine("[Press enter for next hour]");
	}

	public static void nextMinute(Store store, HourReport hReport, int hour) {
		// Create customer according to probability 
		if (newCustomer()) {
			Customer customer = new Customer();
			store.addCustomerToLine(customer);
			hReport.customerVisited();
		}

		// If employee is free and customers are in line: create order
		if (store.employeeAvailable() && store.customersInLine()) {
			Employee emp = store.getFreeEmployee();
			store.setEmployeeNotFree(emp);

			Customer cus = store.getFirstCustomer();
			store.removeCustomerFromLine(cus);
			store.putCustomerToWaitingList(cus);

			// Where should meals be created? 
			Meal meal = new Meal("Happy meal", 7.5, 5);
			store.addOrder(new Order(cus, emp, meal));
			store.addMoney(meal.getPrice());
			hReport.addMoney(meal.getPrice());
		}

		// Every minute all customers wait one minute
		store.allCustomersWait();

		// Go trough orders. 
		// The new list is for the orders that are ready and need to be removed
		ArrayList<Order> readyOrders = new ArrayList();
		for (Order order : store.getOrders()) {
			order.work();
			if (order.getWorkRemaining() <= 0) {
				readyOrders.add(order);
				store.setEmployeeFree(order.getEmployee());

				Customer customer = order.getCustomer();
				store.removeCustomerFromWaitingList(customer);

				// get info for hour report 
				hReport.addWaitingMinutes(customer.getMinutesWaiting());
				hReport.addInLineMinutes(customer.getMinutesInLine());
			}
		}

		// Go through orders that are ready
		// Calculate reputation change and remove orders that are ready
		for (Order order : readyOrders) {
			int repChange = order.getCustomer().getSatisfaction();
			store.addToReputation(repChange);
			hReport.addReputationChange(repChange);
			store.removeOrder(order);
		}
	}

	public static boolean newCustomer() {
		// Do the probability magic here
		return Math.random() < 0.1;

	}
}
