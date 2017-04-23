package project;

public class Order {
	
	private Customer customer;
	private Employee employee;
	private Meal meal;
	private int workRemaining;

	public Order(Customer customer, Employee employee, Meal meal) {
		this.customer = customer;
		this.employee = employee;
		this.meal = meal;

		// Round down the work amount
		double work = meal.getWorkAmount() * employee.getPerformance();
		this.workRemaining = (int) work;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public Meal getMeal() {
		return this.meal;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	
}
