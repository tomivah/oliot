package project;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    private HashMap<Ingredient, Integer> ingredientCounts = new HashMap<>();

    public Storage() {
    }

    public void setIngredientCount(Ingredient ingredient, int count) {
        ingredientCounts.put(ingredient, count);
    }

    public void addIngredient(Ingredient ingredient, int count ) {
        //Add given amount of given ingredient to storage
        
        Integer stored = ingredientCounts.get(ingredient);

        if (stored == null) {
            ingredientCounts.put(ingredient, count);
        } else {
            ingredientCounts.put(ingredient, stored + count);
        }
    }

    public boolean removeIngredient(Ingredient ingredient, int count) {
        /*
        Remove given amount of given ingredient from storage.
        Returns true if successful and false if not enough of
        the ingredient is in storage.
        */

        Integer stored = ingredientCounts.get(ingredient);
        
        if (stored == null || stored < count) {
            return false;
        }
        
        ingredientCounts.put(ingredient, stored - count);
        return true;
    }

    public void removeIngredients(HashMap<Ingredient, Integer> ingredients) {
        for (Map.Entry<Ingredient, Integer> i : ingredients.entrySet()) {
            removeIngredient(i.getKey(), i.getValue());
        }
    }

    public int getIntegerCount(Ingredient ingredient) {
        Integer count = ingredientCounts.get(ingredient);

        if (count == null) {
            return 0;
        }

        return count;
    }
}
