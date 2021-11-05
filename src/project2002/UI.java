package project2002;

import java.util.InputMismatchException;
import java.util.Scanner;

import project2002.Restaurant.UIType;

public abstract class UI {
	protected Scanner scan;
	protected UIType type;

	abstract protected void printOptions();

	/**
	 * Constructor for UI
	 */
	public UI(Scanner scanner) {
		this.scan = scanner;
	}

	UI() {
	};

	protected UIType getType() {
		return this.type;
	}

	protected int getInt() {
		int choice = 0;
		try {
			choice = scan.nextInt();
		} catch (InputMismatchException e) {
			String temp = scan.nextLine();
			System.out.println("Invalid Input.");
		}
		return choice;
	}

	protected String getString() {
		String choice = "";
		try {
			choice = scan.nextLine();
		} catch (InputMismatchException e) {
			String temp = scan.nextLine();
			System.out.println("Invalid Input.");
		}
		return choice;
	}

	abstract protected void assignUIManager(Manager m);
}