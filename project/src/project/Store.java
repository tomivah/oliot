package project;

import java.util.ArrayList;
import java.util.Random;

public class Store {

	private double reputation;
	private double money;
	private int startingEmployeeAmount;
	private int openingHour;
	private int closingHour;
	private ArrayList<Employee> employees = new ArrayList();
	private ArrayList<Customer> customerLine = new ArrayList();
	private ArrayList<Order> orders = new ArrayList();
	private ArrayList<Employee> freeEmployees = new ArrayList();
	private ArrayList<Customer> waitingCustomers = new ArrayList();
    private ArrayList<Ingredient> ingredients = new ArrayList();
    private ArrayList<Meal> meals = new ArrayList();
    private Storage storage = new Storage();

    private static final int FAILED_ORDER_REP = -5;

	public Store(double reputation, double money, int openHour, int closeHour,
			int startingEmployees) {

		this.reputation = reputation;
		this.money = money;
		this.openingHour = openHour;
		this.closingHour = closeHour;
		this.startingEmployeeAmount = startingEmployees;

		// Create a couple of employees right away?
		for (int i = this.startingEmployeeAmount; i > 0; i--) {
			Employee emp = new Employee();
			this.employees.add(emp);
			this.freeEmployees.add(emp);
		}

        FileIO.readConfig("config.txt", ingredients, meals);

        // Need a better way to add initial ingredients
        storage.addIngredient(ingredients.get(0), 200);
        storage.addIngredient(ingredients.get(1), 200);
	}

	public void addEmployee(Employee emp) {
		this.employees.add(emp);
	}

	public void removeEmployee(Employee emp) {
		this.employees.remove(emp);
	}

	public double getReputation() {
		return this.reputation;
	}

	public int getOpeningHour() {
		return openingHour;
	}

	public int getClosingHour() {
		return closingHour;
	}

    public Storage getStorage() {
        return storage;
    }

	public double getDailyWages() {
		double total = 0.0;
		for (Employee emp : this.employees) {
			total += emp.getWage();
		}
		return total * this.closingHour - this.openingHour;
	}

	@Override
	public String toString() {
		return "\n---- Store ---- \n"
				+ "Reputation: " + this.reputation + "\n"
				+ "Money: " + this.money + "\n"
				+ "Employees: " + this.employees.size() + "\n"
				+ "Daily Wages: " + this.getDailyWages() + "\n";
	}

	private void allCustomersWait() {
		for (Customer customer : this.customerLine) {
			customer.addLineMinute();
		}

		for (Customer customer : this.waitingCustomers) {
			customer.addWaitingMinute();
		}
	}

	private void clearStore() {
		// Throw out all customers, all orders and free all employees
		this.customerLine.clear();
		this.waitingCustomers.clear();
		this.orders.clear();
		for (Employee emp : this.employees) {
			if (!this.freeEmployees.contains(emp)) {
				this.freeEmployees.add(emp);
			}
		}
	}

	private void payWages() {
		for (Employee emp : this.employees) {
			this.money -= emp.getWage();
		}
	}

    public boolean addOrder(Order order) {
        if (this.storage.removeIngredients(order.getMeal().getIngredients())) {
            this.orders.add(order);
            return true;
        }

        return false;
 	}

	public void nextDay() {
		DayReport dReport = new DayReport(this);

		for (int h = this.openingHour; h < this.closingHour; h++) {
			nextHour(dReport, h);
		}

		this.clearStore();
		TextInterface.printLine(dReport.toString());
	}

	private void nextHour(DayReport dReport, int hour) {
		HourReport hReport = new HourReport(hour);

		for (int m = 1; m <= 60; m++) {
			nextMinute(hReport, hour);
		}

		dReport.update(hReport);
		hReport.calculateAverages();
		TextInterface.printLine(hReport.toString());
		this.payWages();
		TextInterface.readLine("[Press enter for next hour]");
	}

	private void nextMinute(HourReport hReport, int hour) {
		// Create customer according to probability 
		if (this.newCustomer()) {
			Customer customer = new Customer();
			this.customerLine.add(customer);
			hReport.customerVisited();
		}

		// If employee is free and customers are in line: create order
		if (this.freeEmployees.size() > 0 && this.customerLine.size() > 0) {
			Employee emp = this.freeEmployees.get(0);
			Customer cus = this.customerLine.get(0);
			this.customerLine.remove(cus);

            Random random = new Random();
            Meal meal = meals.get(random.nextInt(meals.size()));

            if (addOrder(new Order(cus, emp, meal))) {
                this.freeEmployees.remove(emp);
                this.waitingCustomers.add(cus);
                this.money += meal.getPrice();
                hReport.addMoney(meal.getPrice());
            } else {
                this.reputation += FAILED_ORDER_REP;
                hReport.addReputationChange(FAILED_ORDER_REP);
                hReport.addFailedOrder();
            }
		}

		// Every minute all customers wait one minute
		this.allCustomersWait();

		// Go trough orders. 
		// The new list is for the orders that are ready and need to be removed
		ArrayList<Order> readyOrders = new ArrayList();
		for (Order order : this.orders) {
			order.work();
			if (order.getWorkRemaining() <= 0) {
				readyOrders.add(order);
				this.freeEmployees.add(order.getEmployee());

				Customer customer = order.getCustomer();
				this.waitingCustomers.remove(customer);

				// get info for hour report 
				hReport.addWaitingMinutes(customer.getMinutesWaiting());
				hReport.addInLineMinutes(customer.getMinutesInLine());
			}
		}

		// Go through orders that are ready
		// Calculate reputation change and remove orders that are ready
		for (Order order : readyOrders) {
			int repChange = order.getCustomer().getSatisfaction();
			this.reputation += repChange;
			hReport.addReputationChange(repChange);
			this.orders.remove(order);
		}
	}

	private boolean newCustomer() {
		// Probability for new customer each minute 
		// 0.6 is just a good constant
		double probValue = (this.reputation / 1000) * 0.6;
		return Math.random() < probValue;

	}
}
