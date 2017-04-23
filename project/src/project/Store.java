package project;

import java.util.ArrayList;

public class Store {

	private double reputation;
	private double money;
	private ArrayList<Employee> employees;
	private ArrayList<Customer> customerLine;
	private ArrayList<Order> orders;
	private ArrayList<Employee> freeEmployees;

	public Store(double reputation, double money) {
		this.reputation = reputation;
		this.money = money;
		this.employees = new ArrayList();
		this.customerLine = new ArrayList();
		this.orders = new ArrayList();
		this.freeEmployees = new ArrayList();

		// Create a couple of employees right away?
		createStartingEmployees();
	}

	private void createStartingEmployees() {
		Employee emp1 = new Employee();
		Employee emp2 = new Employee();
		Employee emp3 = new Employee();

		this.employees.add(emp1);
		this.employees.add(emp2);
		this.employees.add(emp3);

		this.freeEmployees.add(emp1);
		this.freeEmployees.add(emp2);
		this.freeEmployees.add(emp3);

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

	public void addToLine(Customer customer) {
		this.customerLine.add(customer);
	}

	public double getReputation() {
		return this.reputation;
	}

	public boolean employeeAvailable() {
		return this.freeEmployees.size() > 0;
	}

	public Employee getFreeEmployee() {
		Employee emp = this.freeEmployees.get(0);
		this.freeEmployees.remove(emp);
		return emp;
	}

	public boolean customersInLine() {
		return this.customerLine.size() > 0;
	}

	public Customer getFirstCustomer() {
		Customer cus = this.customerLine.get(0);
		this.customerLine.remove(0);
		return cus;
	}

	public void addOrder(Order order) {
		this.orders.add(order);
	}
}
