package project;

import java.util.ArrayList;

public class Store {

	private double reputation;
	private double money;
	private ArrayList<Employee> employees;
	private ArrayList<Customer> customerLine;
	private ArrayList<Order> orders;
	private ArrayList<Employee> freeEmployees;
	private ArrayList<Customer> waitingCustomers;

	public Store(double reputation, double money) {
		this.reputation = reputation;
		this.money = money;
		this.employees = new ArrayList();
		this.customerLine = new ArrayList();
		this.orders = new ArrayList();
		this.freeEmployees = new ArrayList();
		// These are the customer that have ordered and are now waiting 
		this.waitingCustomers = new ArrayList(); 

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
		// How should the minutes affect patience?
		for (Customer customer : this.customerLine) {
			customer.decreasePatience();
			customer.addLineMinute();
		}

		for (Customer customer : this.waitingCustomers) {
			customer.decreasePatience();
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
}
