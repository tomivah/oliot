package project;

import java.util.ArrayList;

public class Store {

	private double reputation;
	private ArrayList<Employee> employees;
	private ArrayList<Customer> customerLine;

	public Store (double reputation)	{
		this.reputation = reputation;
		this.employees = new ArrayList();
		this.customerLine = new ArrayList();
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

	public void removeFromLine(Customer customer) {
		this.customerLine.remove(customer);
	}

	public void removeFirstFromLine() {
		if (this.customerLine.size() > 0) {
			this.customerLine.remove(0);
		}
	}
}
