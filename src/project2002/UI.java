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

	protected int getInt(String prompt) {
		boolean validInput = false;
		String input;
		int integer = 0;
		while (!validInput) {
			System.out.println(prompt);
			input = scan.nextLine();
			try {
				integer = Integer.parseInt(input.trim());
				validInput = true;
			} catch (NumberFormatException nfe) {
				validInput = false;
				System.out.println("Sorry, this input is incorrect! Please try again.");
			}
		}
		return integer;
	}

	protected String getString(String prompt) {
		String choice = "";
		System.out.println(prompt);
		choice = scan.nextLine();
		return choice;
	}

	abstract protected void assignUIManager(Manager m);
}