package classes;

import java.io.IOException;
import java.util.Scanner;

/**
 * Handles output requests, including debug statements if set
 * @author jonpaulsimonelli
 *
 */
public class Text {


	/**
	 * Handles debug statements, if debugging is enabled in Main
	 * @param args array of strings to be output
	 * @throws InterruptedException 
	 */
	public static void debug(String args) {
		if (Main.DEBUG) {
			System.out.println("DEBUG::"+args);
			try { Thread.sleep(Main.DEBUG_WAIT); } 
			catch(InterruptedException e) {}
		}
	}

	/**
	 * Normal output
	 * @param args string to be output
	 * @throws InterruptedException
	 */
	public static void out(String args) {
		int		i;

		for (i=0;i<args.length();i++) {
			if (args.charAt(i) == '~') {
				try {
					Thread.sleep(Main.TEXT_WAIT);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 

			else {
				System.out.print(args.charAt(i));
				try {
					Thread.sleep(Main.CHAR_WAIT);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static void promptEnter() { (new Scanner(System.in)).nextLine(); }
	public static void outReg(String args) { System.out.print(args); }
	public static void clear() throws IOException { Runtime.getRuntime().exec("clear"); }
}

