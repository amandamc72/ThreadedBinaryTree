/*
 * Amanda McCarty
 * COSC 311
 * Assignment #3
 * 11/24/2014
 * Version 02
 * 
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class COSC311Driver {

	static DataStructure myStructure = new DataStructure();
	static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		Scanner inStream = null;

		try {

			inStream = new Scanner(new FileInputStream("Data.txt")); // read  data from file 

			while (inStream.hasNextLine()) // checks to see if the file as another  line
			{
				String lastName, firstName, Id;
				lastName = inStream.next(); //assign data  
				firstName = inStream.next();
				Id = inStream.next();

				myStructure.insert(firstName, lastName, Id); // insert students into database
			}
		} catch (FileNotFoundException e) { // catch any exceptions 
			System.out.println("Error accessing file");
			System.out.println("Ending program");
		} catch (NoSuchElementException e) {
		} finally {
			inStream.close(); // close stream  
		}
		
		int response;

		do {
			System.out.println(" 1 Add a new student");
			System.out.println(" 2 Delete a student");
			System.out.println(" 3 Find a student by ID");
			System.out.println(" 4 List students by ID increasing");
			System.out.println(" 5 List students by first name increasing");
			System.out.println(" 6 List students by last name increasing");
			System.out.println(" 7 List students by ID decreasing");
			System.out.println(" 8 List students by first name decreasing");
			System.out.println(" 9 List students by last name decreasing");
			System.out.println(" ");
			System.out.println(" 0 End");

			response = keyboard.nextInt();

			switch (response) {
			case 1:
				addIt();
				break;
			case 2:
				deleteIt();
				break;
			case 3:
				findIt();
				break;
			case 4:
				listItIncreasingID();   // or see below for programming trick
				break;
			case 5:
				listItIncreasingfName();
				break;
			case 6:
				listItIncreasinglName();
				break;
			case 7:
				listItDecreasingID();
				break;
			case 8:
				listItDecreasingfName();
				break;
			case 9:
				listItDecreasinglName();
				break;
			default:
			}

		} while (response != 0);

	}

	// OK Folks. I won't write all of these, but I'll give you an idea 
	// Case 1: Add a new student. We need a unique ID number 
	public static void addIt() {
		String name1, name2, tempID;
		boolean found;

		do {
			System.out.println("Enter a unique ID number to add");
			tempID = keyboard.next();

			//is it unique ? 
			found = myStructure.search(tempID);
			if (found) {
				System.out.println("ID already in use");
				System.out.println("Please enter a unique ID");
			}
		} while (found);

		// We found a unique ID. Now ask for first and last name 
		System.out.println("Enter first name");
		name1 = keyboard.next();
		System.out.println("Enter last name");
		name2 = keyboard.next();
		
		// add to our data structure 
		myStructure.insert(name1, name2, tempID);
	}

	// Case 2: delete a student. A student ID must be prompted for. If the ID  number does not exist in the database ,
	//    print out a message indicating a such, otherwise delete the entire record
	public static void deleteIt() {
		System.out.println("Enter Student ID to delete: ");
		String searchId = keyboard.next();
		boolean goodData = myStructure.search(searchId);

		if (!goodData) { // if can't find id
			System.out.println("The ID does not exist in database." + "\n");
		} else { // else delete
			myStructure.deleteRecord(searchId);
		}

	}

	//Case 3: find a student. A student ID must be prompted for. If the ID number is not found, print out a
	//    message indicating as such. Otherwise print out the entire record 
	// you must provide the code!! 
	public static void findIt() {
		System.out.println("Enter Student ID: ");
		String searchId = keyboard.next();
		boolean goodData = myStructure.search(searchId);

		if (!goodData) // if can't find id
			System.out.println("The ID number was not found." + "\n");	
	}

	// Cases 4,5,6,7,8,9 
	// A little programming trickery. All of the listing methods below can   call the SAME method in DataStructure.
	// We'll pass 2 parameters: the first indicates which field, the second indicates which order
	// fieldNum=1 first name 
	// fieldNum=2 last name 
	// fieldNum=3 ID 
	// orderNum=1 increasing 
	// orderNum=2 decreasing 
	public static void listItIncreasingID() 
	{
		myStructure.listIt(3, 1);
	}

	public static void listItIncreasingfName() 
	{
		myStructure.listIt(1, 1);
	}

	public static void listItIncreasinglName() 
	{
		myStructure.listIt(2, 1);
	}

	public static void listItDecreasingID() 
	{
		myStructure.listIt(3, 2);
	}

	public static void listItDecreasingfName() 
	{
		myStructure.listIt(1, 2);
	}

	public static void listItDecreasinglName() 
	{
		myStructure.listIt(2, 2);
	}

}
