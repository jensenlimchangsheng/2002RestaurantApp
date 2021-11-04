package project2002;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuUI extends UI{
	public enum ItemType { MAIN, DRINK, DESSERT, PROMOSET }  
	Restaurant rest;

	public MenuUI(Restaurant rest) {
		this.rest=rest;
	}

	public void printMenuOptions() {
		int choice=0;
		Scanner scan= new Scanner(System.in);
		do {
			System.out.printf("-------------Menu Options------------\n"
					+ "Please select one of this 8 options: \n"
					+ "1.	Print Menu\n"
					+ "2.	Add Menu Item\n"
					+ "3.	Remove Menu Item\n"
					+ "4.	Update Menu Item\n"
					+ "5.	Add Promo Set\n"
					+ "6.	Remove Promo Set\n"
					+ "7.	Update Promo Set\n"
					+ "8.	Quit");
			choice=this.getInt();
			switch(choice) {
			case 1:
				System.out.println(rest.printMenu());
				break;
			case 2:
				String name=this.getString();
				int price= this.getInt();
				String description=this.getString();
				ItemType i=this.getType();
				if(rest.addMenuItem(name,price,description,i)==1) {
					System.out.println("Item is successfully added.");
				}
				else {
					System.out.println("Item could not be added/ duplicate item exist...");
				}
				break;
			case 3:
				System.out.println(rest.removeMenuItem());
				break;
			case 4:
				System.out.println(rest.updateMenuItem());
			case 5:
				System.out.println(rest.addPromoSet());
			case 6:
				System.out.println(rest.removePromoSet());
			case 7:
				System.out.println(rest.updatePromoSet());
			case 8:
				break;
			default:
				System.out.println("Invalid Input.");
			}
			} while(choice!=8);
		
	}
	public ItemType getType() {
		System.out.println("Please enter menu item type: ");
		String type=this.getString();
		for (ItemType i : ItemType.values())  {
			if(type.toUpperCase()==i.toString()) return i;  
		} 
		return null;
		
	}

}
