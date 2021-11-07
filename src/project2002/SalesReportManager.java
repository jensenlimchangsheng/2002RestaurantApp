package project2002;

public class SalesReportManager extends Manager {
	private static SalesReport report;
	SalesReportUI srUI;

	SalesReportManager(){
		report=new SalesReport();
	}
	@Override
	public void assignHandler(Handler h) {
	}

	@Override
	public void assignUI(UI ui) {
		srUI=(SalesReportUI) ui;
		
	}
	static public void addOrder(Order order) {
		report.InsertOrder(order);
	}
	public void printYearlyReport(int y){
		report.printYearlyReport(y);
	}
	public void printMonthlyReport(int y, int m){
		report.printMonthlyReport(y, m);
	}
	public void printDailyReport(int y , int m, int d){
		report.printDailyReport(y, m, d);
	}

}
