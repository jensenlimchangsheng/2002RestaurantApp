/**
 * a sales report manager to help link the sales report user interface and the sales report 
 */
package project2002;

import project2002.Restaurant.UIType;

public class SalesReportManager extends Manager {
	/**
	 * the sales report that is connected to this manager
	 */
	private static SalesReport report;
	/**
	 * The sales report user interface that is connected to this manager
	 */
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
	/**
	 * to add an order into the sales report
	 * @param order the order that the user wants to place into the report
	 */
	static public void addOrder(Order order) {
		report.InsertOrder(order);
	}
	/**
	 * To print the yearly report of a chosen year
	 * @param y the year in which the user would like to print the report on
	 */
	public void printYearlyReport(int y) {
		report.printYearlyReport(y);
	}
	/**
	 * To print the monthly report of a chosen month
	 * @param y the year in which the user would like to print the report on
	 * @param m the month in which the user would like to print the report on
	 */
	public void printMonthlyReport(int y, int m) {
		report.printMonthlyReport(y, m);
	}
	/**
	 * To print the daily report of a chosen date
	 * @param y the year in which the user would like to print the report on
	 * @param m the month in which the user would like to print the report on
	 * @param d the date in which the user would like to print the report on
	 */
	public void printDailyReport(int y, int m, int d) {
		report.printDailyReport(y, m, d);
	}

}
