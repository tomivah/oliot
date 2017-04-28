package project;

import java.util.ArrayList;

public class Store {

	private double reputation;
	private double money;
	private int openingHour;
	private int closingHour;
	private ArrayList<Employee> employees = new ArrayList();
	private ArrayList<Customer> customerLine = new ArrayList();
	private ArrayList<Order> orders = new ArrayList();
	private ArrayList<Employee> freeEmployees = new ArrayList();
	private ArrayList<Customer> waitingCustomers = new ArrayList();

	public Store(double reputation, double money, int openHour,  int closeHour) {
		this.openingHour = openHour;
		this.closingHour = closeHour;
		this.reputation = reputation;
		this.money = money;
		// Create a couple of employees right away?
		createStartingEmployees();
	}

	private void createStartingEmployees() {
		Employee emp1 = new Employee();
		Employee emp2 = new Employee();
		Employee emp3 = new Employee();
		Employee emp4 = new Employee();
		Employee emp5 = new Employee();

		this.employees.add(emp1);
		this.employees.add(emp2);
		this.employees.add(emp3);
		this.employees.add(emp4);
		this.employees.add(emp5);

		this.freeEmployees.add(emp1);
		this.freeEmployees.add(emp2);
		this.freeEmployees.add(emp3);
		this.freeEmployees.add(emp4);
		this.freeEmployees.add(emp5);
	}

	public void addToReputation(double amount) {
		this.reputation += amount;
	}

	public void addMoney(double amount) {
		this.money += amount;
	}


	public void addEmployee(Employee emp) {
		this.employees.add(emp);
	}

	public void removeEmployee(Employee emp) {
		this.employees.remove(emp);
	}

	public void addCustomerToLine(Customer customer) {
		this.customerLine.add(customer);
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

	public boolean employeeAvailable() {
		return this.freeEmployees.size() > 0;
	}

	public Employee getFreeEmployee() {
		return this.freeEmployees.get(0);
	}

	public boolean customersInLine() {
		return this.customerLine.size() > 0;
	}

	public Customer getFirstCustomer() {
		return this.customerLine.get(0);
	}

	public void removeCustomerFromLine(Customer customer) {
		this.customerLine.remove(customer);
	}

	public void putCustomerToWaitingList(Customer customer) {
		this.waitingCustomers.add(customer);
	}

	public void addOrder(Order order) {
		this.orders.add(order);
	}

	public Iterable<Order> getOrders() {
		return this.orders;
	}

	public void setEmployeeFree(Employee emp) {
		this.freeEmployees.add(emp);
	}

	public void setEmployeeNotFree(Employee emp) {
		this.freeEmployees.remove(emp);
	}

	public void removeCustomerFromWaitingList(Customer customer) {
		this.waitingCustomers.remove(customer);
	}

	public Iterable<Customer> getCustomers() {
		// This is unused and redundant
		return this.customerLine;
	}

	public void removeOrder(Order order) {
		this.orders.remove(order);
	}

	public void allCustomersWait() {
		for (Customer customer : this.customerLine) {
			customer.addLineMinute();
		}

		for (Customer customer : this.waitingCustomers) {
			customer.addWaitingMinute();
		}
	}

	public void clearStore() {
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

	@Override
	public String toString() {
		return "\n---- Store ---- \n"
				+ "Reputation: " + this.reputation +"\n"
				+ "Money: " + this.money +"\n"
				+ "Employees: " + this.employees.size() +"\n"
				+ "Daily Wages: " + this.getDailyWages() + "\n";
	}

	public void payWages() {
		for (Employee emp : this.employees) {
			this.money -= emp.getWage();
		}
	}

	public double getDailyWages() {
		double total = 0.0;
		for (Employee emp : this.employees) {
			total += emp.getWage();
		}
		return total * this.closingHour - this.openingHour;
	}
}
