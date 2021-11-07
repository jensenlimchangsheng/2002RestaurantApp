package project2002;

import java.util.Scanner;

import project2002.Restaurant.UIType;

public class SalesReportUI extends UI {
	
	private SalesReportManager srManager;

	public SalesReportUI(Scanner scanner){
		super(scanner);
		this.type=UIType.SALES;
	}

	@Override
	protected void printOptions() {
		Scanner sc = new Scanner(System.in);
		int y,m,d;
		int choice=0;
		do {
			System.out.printf("-------------Sales Report Options------------\n"
					+ "Please select one of this 8 options: \n"
					+ "1.	Print Daily Sales Report\n"
					+ "2.	Print Monthly Sales Report\n"
					+ "3.	Print Yearly Sales Report\n"
					+ "4.	Quit\n"
					+ "Please enter your choice: ");
			choice=this.getInt();
			switch(choice) {
			case 1:
				System.out.println("Which Year would you like to print?");
				while(!sc.hasNextInt()){
					System.out.println("Invalid input please try again");
				}
				y = sc.nextInt();
				System.out.println("Which month would you like to print?\n"
									+ "1. January\n"
									+ "2. Feburary\n"
									+ "3. March\n"
									+ "4. April\n"
									+ "5. May\n"
									+ "6. June\n"
									+ "7. July\n"
									+ "8. August\n"
									+ "9. September\n"
									+ "10. October\n"
									+ "11. November\n"
									+ "12. December");
				while(!sc.hasNextInt()){
					System.out.println("Invalid input please try again");
				}
				m = sc.nextInt();
				while(m > 12 || m < 1){
					System.out.println("Invalid input please try again");
					while(!sc.hasNextInt()){
						System.out.println("Invalid input please try again");
					}
					m = sc.nextInt();
				}
				System.out.println("Which date would you like to print?");
				while(!sc.hasNextInt()){
					System.out.println("Invalid input please try again");
				}
				d = this.getInt();
				while(d > 31 || d < 1){
					System.out.println("Invalid input please try again");
					while(!sc.hasNextInt()){
						System.out.println("Invalid input please try again");
					}
					d = sc.nextInt();
				}
				srManager.printDailyReport(y, m, d);
				break;
			case 2:
				System.out.println("Which Year would you like to print?");
				while(!sc.hasNextInt()){
					System.out.println("Invalid input please try again");
				}
				y = sc.nextInt();
				System.out.println("Which month would you like to print?\n"
									+ "1. January\n"
									+ "2. Feburary\n"
									+ "3. March\n"
									+ "4. April\n"
									+ "5. May\n"
									+ "6. June\n"
									+ "7. July\n"
									+ "8. August\n"
									+ "9. September\n"
									+ "10. October\n"
									+ "11. November\n"
									+ "12. December");
				while(!sc.hasNextInt()){
					System.out.println("Invalid input please try again");
				}
				m = sc.nextInt();
				while(m > 12 || m < 1){
					System.out.println("Invalid input please try again");
					while(!sc.hasNextInt()){
						System.out.println("Invalid input please try again");
					}
					m = sc.nextInt();
				}
				srManager.printMonthlyReport(y, m);
				break;
			case 3:
				System.out.println("Which Year would you like to print?");
				while(!sc.hasNextInt()){
					System.out.println("Invalid input please try again");
				}
				y = sc.nextInt();
				srManager.printYearlyReport(y);
				break;
			case 4:
				break;
			default:
				System.out.println("Invalid Input please try again");
			}
			} while(choice!=4);
		
	}

	@Override
	protected void assignUIManager(Manager m) {
		srManager=(SalesReportManager) m;
	}

}
