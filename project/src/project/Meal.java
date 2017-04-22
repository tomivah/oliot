package project;

public class Meal {

	private String name;
	private double price;
	private int workAmount;

	public Meal(String name, double price, int workAmount) {
		this.name = name;
		this.price = price;
		this.workAmount = workAmount;
	}

	public String getName() {
		return this.name;
	}

	public double getPrice() {
		return this.price;
	}

	public int getWorkAmount() {
		return this.workAmount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setWorkAmount(int workAmount) {
		this.workAmount = workAmount;
	}

}
