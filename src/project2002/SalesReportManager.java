package project2002;

import project2002.Restaurant.UIType;

public class SalesReportManager extends Manager {
	private static SalesReport report;
	SalesReportUI srUI;

	SalesReportManager() {
		report = new SalesReport();
		type = UIType.SALES;
	}

	@Override
	public void assignHandler(Handler h) {
	}

	@Override
	public void assignUI(UI ui) {
		srUI = (SalesReportUI) ui;

	}

	static public void addOrder(Order order) {
		report.InsertOrder(order);
	}

	public void printDailyReport(int y, int m, int d) {
	}

	public void printMonthlyReport(int y, int m) {
	}

	public void printYearlyReport(int y) {
	}

}
