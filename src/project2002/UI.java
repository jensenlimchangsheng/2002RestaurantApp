package project2002;

import java.util.Scanner;

import project2002.Restaurant.UIType;

public abstract class UI {
	protected Scanner scan;
	protected UIType type;

	abstract protected void printOptions();

	/**
	 * Constructor for UI.
	 * 
	 * @param scanner
	 * @return UI
	 */
	public UI(Scanner scanner) {
		this.scan = scanner;
	}

	UI() {
	};

	/**
	 * Getter for UIType.
	 * 
	 * @return UIType
	 */
	protected UIType getType() {
		return this.type;
	}

	/**
	 * Prompts user to input an integer.
	 * 
	 * @param prompt
	 * @return integer input
	 */
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

	/**
	 * Prompts user to input an double.
	 * 
	 * @param prompt
	 * @return double input
	 */
	protected double getDouble(String prompt) {
		boolean validInput = false;
		String input;
		double number = 0;
		while (!validInput) {
			System.out.println(prompt);
			input = scan.nextLine();
			try {
				number = Double.parseDouble(input.trim());
				validInput = true;
			} catch (NumberFormatException nfe) {
				validInput = false;
				System.out.println("Sorry, this input is incorrect! Please try again.");
			}
		}
		return number;
	}

	/**
	 * Prompts user to input an string.
	 * 
	 * @param prompt
	 * @return string input
	 */
	protected String getString(String prompt) {
		String choice = "";
		System.out.println(prompt);
		choice = scan.nextLine();
		return choice;
	}

	/**
	 * Abstract method to assign manager to UI.
	 * 
	 * @param manager
	 */
	abstract protected void assignUIManager(Manager m);
}