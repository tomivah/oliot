package project;

public class Project {

	public static void main(String[] args) {
		Menu menu = new Menu();
		Store store = new Store(100);

		while (true) {
			nextDay(store);
			menu.show();

			switch (menu.readSelection()) {
				case 1:
					System.exit(0);
				default:
					TextInterface.printLine("Invalid selection");
			}
		}
	}

	public static void nextDay(Store store) {
		DayReport dReport = new DayReport();

		for (int h = 1; h <= 12; h++) {
			nextHour(store, dReport, h);
		}

		TextInterface.print(dReport.toString());

	}

	public static void nextHour(Store store, DayReport dReport, int hour) {
		HourReport hReport = new HourReport();

		for (int m = 1; m <= 60; m++) {
			nextMinute(store,hReport,hour);
			
		}
	}

	public static void nextMinute(Store store, HourReport hreport, int hour) {
		// jotain
	}
}
