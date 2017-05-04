package project;

import java.util.HashMap;

public class Meal {

	private String name;
	private double price;
	private int workAmount;
    private HashMap<Ingredient, Integer> ingredients = new HashMap<>();

	public Meal(String name, double price, int workAmount) {
		this.name = name;
		this.price = price;
		this.workAmount = workAmount;
	}

    public void addIngredient(Ingredient ingredient, Integer count ) {
        ingredients.put(ingredient, count);
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

    public HashMap<Ingredient, Integer> getIngredients() {
        return this.ingredients;
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
