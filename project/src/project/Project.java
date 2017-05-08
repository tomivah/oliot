package project;

public class Project {
 
    public static void main(String[] args) {
        
		Menu menu = new Menu();
		Store store = new Store(100, 10000, 7, 19, 5);
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
                case 5:
                    TextInterface.printLine("");
                    menu.buyIngredients(store);

                    int ingredientSelection = menu.readSelection();
                    
                    while ( ingredientSelection < 1 ||
                            ingredientSelection >
                            store.getIngredients().size() ) {
                        TextInterface.printLine(
                                "Invalid selection. Try again.");
                        ingredientSelection = menu.readSelection();
                    }

                    int amount = TextInterface.readInt("How many? ");
                    store.buyIngredient(ingredientSelection - 1, amount);
                    TextInterface.printLine("");
                    break;
				default:
					TextInterface.printLine("Invalid selection");
			}
		}
	}
}
