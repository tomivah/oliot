package project;

public class Project {

	public static void main(String[] args) {
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
				default:
					TextInterface.printLine("Invalid selection");
			}
		}
	}
}
