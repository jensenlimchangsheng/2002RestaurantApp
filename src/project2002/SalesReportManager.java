
package project2002;

import project2002.Restaurant.UIType;

/**
 * a sales report manager to help link the sales report user interface and the
 * sales report
 * 
 * @author Jensen Lim
 * @version 1.0
 * @since 2021-11-01
 */
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

	/**
	 * Assigns handlers to sales report manager
	 * 
	 */
	@Override
	public void assignHandler(Handler h) {
	}

	/**
	 * Assigns sales report UI to sale report manager
	 * 
	 * @param UI salesReport UI
	 */
	@Override
	public void assignUI(UI ui) {
		srUI = (SalesReportUI) ui;
	}

	/**
	 * to add an order into the sales report
	 * 
	 * @param order the order that the user wants to place into the report
	 */
	static public void addOrder(Order order) {
		report.insertOrder(order);
	}

	/**
	 * To print the yearly report of a chosen year
	 * 
	 * @param y the year in which the user would like to print the report on
	 */
	public void printYearlyReport(int y) {
		report.printYearlyReport(y);
	}

	/**
	 * To print the monthly report of a chosen month
	 * 
	 * @param y the year in which the user would like to print the report on
	 * @param m the month in which the user would like to print the report on
	 */
	public void printMonthlyReport(int y, int m) {
		report.printMonthlyReport(y, m);
	}

	/**
	 * To print the daily report of a chosen date
	 * 
	 * @param y the year in which the user would like to print the report on
	 * @param m the month in which the user would like to print the report on
	 * @param d the date in which the user would like to print the report on
	 */
	public void printDailyReport(int y, int m, int d) {
		report.printDailyReport(y, m, d);
	}

}
