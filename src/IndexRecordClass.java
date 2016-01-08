/*
 * Amanda McCarty
 * COSC 311
 * Assignment #3
 * 11/24/2014
 * Version 02
 * 
 */

public class IndexRecordClass 
{

	private String key;
	private int recordNumber;

	public IndexRecordClass() //default constructor  
	{
		key = "zzzzzzzzzzzzzzz";
		recordNumber = -1;
	}

	public IndexRecordClass(String key, int RecordNumber) //parameterized constructor 
	{
		this.key = key;
		this.recordNumber = RecordNumber;
	}

	public IndexRecordClass(IndexRecordClass otherIndexRecordClass) //copy constructor 
	{
		key = otherIndexRecordClass.key;
		recordNumber = otherIndexRecordClass.recordNumber;
	}

	public String getKey() //get key 
	{
		return key;
	}

	public void setKey(String key) //set key 
	{
		this.key = key;
	}

	public int getRecordNumber() //get record number 
	{
		return recordNumber;
	}

	public void setRecordNumber(int RecordNumber) //set record number 
	{
		this.recordNumber = RecordNumber;
	}

	public int compareTo(IndexRecordClass otherRec)//compare to 
	{
		return (key.compareTo(otherRec.key));
	}

	public boolean equals(Object otherObject) //equals 
	{
		if (otherObject == null) {
			return false;
		} else if (getClass() != otherObject.getClass()) {
			return false;
		} else {
			IndexRecordClass otherIndexRecordClass
			= (IndexRecordClass) otherObject;
			return (key.equals(otherIndexRecordClass.key)
					&& recordNumber
					== otherIndexRecordClass.recordNumber);
		}
	}

	public String toString() // to string 
	{
		return (key + " " + recordNumber + " ");
	}
}

