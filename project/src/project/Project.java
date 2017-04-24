package project;

import java.util.ArrayList;

public class Project {

	public static void main(String[] args) {
		Menu menu = new Menu();
		// Store gets a couple of employees in the constructor
		Store store = new Store(100, 10000);
		// NextDay() could be store's own method?
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
		
		store.clearStore();
		TextInterface.printLine(dReport.toString());
	}

	public static void nextHour(Store store, DayReport dReport, int hour) {
		HourReport hReport = new HourReport();

		for (int m = 1; m <= 60; m++) {
			nextMinute(store, hReport, hour);
		}
		
		hReport.calculateAverages();
		TextInterface.printLine(hReport.toString());
	}

	public static void nextMinute(Store store, HourReport hReport, int hour) {
		// Create customer according to probability 
		if (newCustomer(store)) {
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
			Meal meal = new Meal("Happy meal", 7.5, 10);
			store.addOrder(new Order(cus, emp, meal));
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
				Customer customer = order.getCustomer();
				store.setEmployeeFree(order.getEmployee());
				store.removeCustomerFromWaitingList(customer);
				readyOrders.add(order);
				
				// get info for hour report 
				hReport.addWaitingMinutes(customer.getMinutesWaiting());
				hReport.addInLineMinutes(customer.getMinutesInLine());
			}
		}

		// Calculate reputation change and remove orders that are ready
		for (Order order : readyOrders) {
			// store.addToReputation( order.calculateReputation() ); 
			store.removeOrder(order);
		}
	}

	public static boolean newCustomer(Store store) {
		// Do the probability magic here
		return Math.random() < 0.9;

	}
}
