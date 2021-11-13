package project2002;

import java.util.Scanner;

import project2002.Restaurant.UIType;

/**
 * Abstract UI class which the different UI classes will inherit from
 * 
 * @author Zhi Kai
 * @version 1.0
 * @since 2021-11-07
 */

public abstract class UI {
	/**
	 * Used to read customer inputs
	 */
	protected Scanner scan;

	/**
	 * Type of UI
	 */
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

	/**
	 * Blank constructor for UI.
	 * 
	 * @return UI
	 */
	public UI() {
	};

	/**
	 * To retrieve the UItype that will be used in the assignment by the Restaurant
	 * 
	 * @return UIType
	 */
	protected UIType getType() {
		return this.type;
	}

	/**
	 * Prompts user to input an integer using the prompt provided.
	 * 
	 * @param prompt the prompt to be displayed and will be repeated if the user
	 *               inputs an invalid input
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
	 * Prompts user to input an double using the prompt provided.
	 * 
	 * @param prompt the prompt to be displayed and will be repeated if the user
	 *               inputs an invalid input
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
	 * Prompts user to input an string using the prompt provided.
	 * 
	 * @param prompt the prompt to be displayed and will be repeated if the user
	 *               inputs an invalid input
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