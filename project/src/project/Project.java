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
		Store store = new Store(1000, 10000, 7, 19, 5);
		store.nextDay();

		while (true) {
			menu.show();

			switch (menu.readSelection()) {
				case 1:
					System.exit(0);
				case 2:
					store.nextDay();
					break;
				case 3:
					TextInterface.printLine(store.toString());
					break;
				case 4:
					TextInterface.printLine(store.getStorage().toString());
					break;
				default:
					TextInterface.printLine("Invalid selection");
			}
		}
	}
}
