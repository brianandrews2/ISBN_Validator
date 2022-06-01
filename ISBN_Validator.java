/* 
 * ISBN Validation Program
 * Created by:  Brian Andrews 
 * Receives input from user and determines whether or not the input is a valid ISBN
 * ISBN Criteria:  ISBN must include 9 integers, 0 or 3 dashes, and a valid check sum.
*/

import java.util.*;
public class ISBN_Validator {

	public static void main(String[] args) {
		System.out.println("This program validates ISBN numbers entered into the console.");
		System.out.println("ISBN must include 9 integers, 0 or 3 dashes, and a valid check sum.");
		System.out.print("Please enter an ISBN to validate: ");
		checkDigits();
	}	
	//Checks the first 9 digits.  The 10th digit check sum is validated separately.
	public static void checkDigits() {
		//initialize variables
		int checkSum = 0;
		int sumInts = 0;
		Scanner input = new Scanner(System.in);
		String isbn = input.nextLine();
		int stringIndex = 0;
		int dashCount = 0;
		char digit = isbn.charAt(stringIndex);
		boolean doubleDash = false;
		boolean isInt = checkForInt(digit);
		boolean valid = true;
		boolean quit = false;
		
		//begin validation checks
		if (isbn.equals("q") || isbn.equals("Q")) {       //only a single 'q' or 'Q' input will cause program to quit in this way
		System.out.println("Quitting Program.");	
		quit = true;
		}
		if (stringIndex == 0 && valid && !quit) {
			digit = isbn.charAt(stringIndex);
			isInt = checkForInt(digit);
			if (isInt) {
				sumInts++; 
				checkSum = checkSum + sumInts * Character.getNumericValue(digit);
				stringIndex++;
			}
			else {
				System.out.println("First character must be an integer.");
				valid = false;
			}
		}
		while(stringIndex < isbn.length() - 1 && !quit) {
			digit = isbn.charAt(stringIndex);
			isInt = checkForInt(digit);
			if (isInt) {
				sumInts++; 
				checkSum = checkSum + sumInts * Character.getNumericValue(digit);
			}
			if (digit == '-') {
				dashCount++;
				if (isbn.charAt(stringIndex + 1) == '-') {
					doubleDash = true;
				}
			}				
			if (digit != '-' && !isInt) {
				System.out.println("Invalid character - only integers and dashes can be used.");
				valid = false;
			}
			stringIndex++;
		}		
		if (!quit) {
			boolean validCheckSum = checkSumMethod(isbn, checkSum);
			if (!validCheckSum) {
				valid = false;
			}
			if (doubleDash) {
				System.out.println("Consecutive dashes cannot be used.");
				valid = false;
				}
			if (sumInts < 9) {
				System.out.println("Not enough integers - must include 9 integers and check sum.");
				valid = false;
			}
			if (sumInts > 9) {
				System.out.println("Too many integers - must include 9 integers and check sum.");
				valid = false;
			}
			if (dashCount == 1 || dashCount == 2) {							//checks that 0 or 3 dashes are used		
				System.out.println("Not enough dashes - must have 0 or 3 dashes only."); 	
				valid = false;
			}
			if (dashCount > 3) {
				System.out.println("Too many dashes - must have 0 or 3 dashes only.");
				valid = false;
			}
			if (valid) {
				System.out.println("ISBN " + isbn + " is valid!");
				System.out.print("\nPlease enter another ISBN to validate: ");
				checkDigits();
			}
			if (!valid) {
				System.out.print("\nPlease enter another ISBN to validate: ");
				checkDigits();
			}
		}
		input.close();
	}
	
	//Checks current index location for integer values.  If integer is found, returns true, otherwise returns false.
	public static boolean checkForInt(char digit) {
		boolean intCheck = false;
		int numbersIndex = 0;
		String numbersList = "0123456789";
		while(!intCheck && numbersIndex < numbersList.length()) {
			if (digit == numbersList.charAt(numbersIndex)) {
				intCheck = true;
			}
			else {
				intCheck = false;
				numbersIndex++;
			}
		}
		return intCheck; 
	}
	
	//checks that the last character of the ISBN input is a correct check sum value
	public static boolean checkSumMethod(String isbn, int checkSum) {
		boolean valid = true;
		checkSum = checkSum % 11;
		int lastDigit = Character.getNumericValue(isbn.charAt(isbn.length() - 1));		
		char lastChar = isbn.charAt(isbn.length() - 1);
		char digit = isbn.charAt(isbn.length() - 1);
		boolean intCheck = checkForInt(digit);	
		if (intCheck && lastDigit != checkSum) {
			System.out.println("Check sum does not match");
			valid = false;
		}
		if (!intCheck) {
			if (lastChar == 'X' && checkSum != 10) {
				System.out.println("Check sum does not match");
				valid = false;
			}
			if (lastChar == '-') {
				System.out.println("Last digit cannot be a dash");
				valid = false;
			}
			if (lastChar == 'X' && checkSum == 10) {
				valid = true;
			}
			else {
				System.out.println("Invalid last character - last character must be integer or X only");
				valid = false;
			}
		}
		return valid;
	}
}