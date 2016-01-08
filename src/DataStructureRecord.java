/*
 * Amanda McCarty
 * COSC 311
 * Assignment #3
 * 11/24/2014
 * Version 02
 * 
 */

public class DataStructureRecord 
{	
	private String iD, firstName, lastName;
	
	public DataStructureRecord() //parameterized constructor 
	{
		this.firstName = " ";
		this.lastName = " ";
		this.iD = " ";
	}
	
	public DataStructureRecord(String firstName, String lastName, String iD) //parameterized constructor 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.iD = iD;
	}

	public DataStructureRecord(DataStructureRecord otherDataStructureRecord) //copy constructor 
	{
		iD = otherDataStructureRecord.iD;
		firstName = otherDataStructureRecord.firstName;
		lastName = otherDataStructureRecord.lastName;
	}

	public String getId() //get id 
	{
		return iD;
	}

	public void setId(String iD) //set id 
	{
		this.iD = iD;
	}

	public String getFirstName() //get first name 
	{
		return firstName;
	}

	public void setFirstName(String firstName) //set first name 
	{
		this.firstName = firstName;
	}

	public String getLastName() //get last name 
	{
		return lastName;
	}

	public void setLastName(String lastName) //set last name 
	{
		this.lastName = lastName;
	}

	public boolean equals(Object otherObject) //equals 
	{
		if (otherObject == null) {
			return false;
		} else if (getClass() != otherObject.getClass()) {
			return false;
		} else {
			DataStructureRecord otherDataStructureRecord
			= (DataStructureRecord) otherObject;
			return (firstName.equals(otherDataStructureRecord.firstName)
					&& iD.equals(otherDataStructureRecord.iD)
					&& lastName.equals(otherDataStructureRecord.lastName));
		}
	}

	public String toString() //to string 
	{
		return ("Id number: " + iD + "  First Name: " + firstName + "  LastName: "
				+ lastName + "\n");
	}

}
