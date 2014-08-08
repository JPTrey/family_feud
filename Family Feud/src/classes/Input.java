package classes;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Handles all input requests between classes
 * @author jonpaulsimonelli
 *
 */
public class Input {
	private static Scanner sc = new Scanner(System.in);

	public static int getInt(int limit) {
		int			choice = -1;

		try { choice = sc.nextInt(); } 
		catch (InputMismatchException e) { 
			Text.out("Invalid choice (use integers)\n"); 
			getInt(limit);		// retry
		} 
		if (choice > limit) {
			Text.out("Invalid choice (out of bounds)\n"); 
			getInt(limit); 
		}
		return choice;	
	}

	public static String getString() {
		String		input = null;

		input = sc.nextLine();
		return input;
	}
}
