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

}
