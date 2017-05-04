package project;

import java.util.ArrayList;
import java.util.Random;

public class Project {
 
    static final int FAILED_ORDER_REP = -5;
    static ArrayList< Ingredient > ingredients = new ArrayList<>();
    static ArrayList< Meal > meals = new ArrayList<>();
	
    public static void main(String[] args) {
        
        FileIO.readConfig("config.txt", ingredients, meals);

		Menu menu = new Menu();
		// Store gets a couple of employees in the constructor
		Store store = new Store(100, 10000);

        // Need a better way to add initial ingredients
        store.getStorage().addIngredient(ingredients.get(0), 200);
        store.getStorage().addIngredient(ingredients.get(1), 200);

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
		dReport.update(hReport);
		TextInterface.printLine(hReport.toString());
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
			Customer cus = store.getFirstCustomer();
			store.removeCustomerFromLine(cus);

            Random random = new Random();
            Meal meal = meals.get(random.nextInt(meals.size()));
			
            if (store.addOrder(new Order(cus, emp, meal))) {
                store.setEmployeeNotFree(emp);
                store.putCustomerToWaitingList(cus);
                hReport.addMoney(meal.getPrice());
            } else {
                store.addToReputation(FAILED_ORDER_REP);
                hReport.addReputationChange(FAILED_ORDER_REP);
                hReport.addFailedOrder();
            }
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
		return Math.random() < 0.6;

	}
}
