import java.util.ArrayList;

/*
 * Amanda McCarty
 * COSC 311
 * Assignment #3
 * 11/24/2014
 * Version 02
 * 
 */

public class DataStructure 
{
	private ArrayList<DataStructureRecord> database;
	private ThreadedTree fNameIndex;
	private ThreadedTree lNameIndex;
	private ThreadedTree IdIndex;
	private int next = 0;
	
	public DataStructure() // default constructor  
	{
		database = new ArrayList<DataStructureRecord>();
		fNameIndex = new ThreadedTree();
		lNameIndex = new ThreadedTree();
		IdIndex = new ThreadedTree();
	}

	public void insert(String fName, String lName, String Id) // insert person into database and indexes 
	{
		database.add(new DataStructureRecord(fName, lName, Id));
		fNameIndex.insert(fName, next);
		lNameIndex.insert(lName, next);
		IdIndex.insert(Id, next);
		next++;
		
		setIndex();
	}

	public boolean search(String searchVal) // find specified value 
	{
		ThreadedTree.Node temp = IdIndex.search(searchVal); // find where
		if(temp != null) 
		{
			int x = temp.getRecNum(); //index for database
			System.out.println(database.get(x)); // print index 
			return true;
		}
		return false;
	}

	public boolean deleteRecord(String delVal) // delete entire student record 
	{
		boolean isFound;
		for (int j = 0; j < database.size(); j++) // for each element in database
		{
			if (database.get(j).getId().equals(delVal)) // found item? 
			{
				String fName = database.get(j).getFirstName();
				String lName = database.get(j).getLastName();
				fNameIndex.delete(fName); // delete from indexes
				lNameIndex.delete(lName);
				IdIndex.delete(delVal); 				
				database.remove(j); // delete from database	
				isFound = true;
				break;
			}
		}	
		
		isFound = false; // not found
		
		setIndex(); // reset tree indexes on delete
		
		return isFound;
	}
	
	public void setIndex() //reset tree indexes to match database indexes
	{
		for(int i = 0; i < database.size(); i++)
		{
			fNameIndex.reIndex(database.get(i).getFirstName(), i);
			lNameIndex.reIndex(database.get(i).getLastName(), i);
			IdIndex.reIndex(database.get(i).getId(), i);
		}
	}

	public void listIt(int a, int b) 
	{
		ThreadedTree.Node temp;
		if (a == 1 && b == 1)
		{
			fNameIndex.displayAscending(); // push fName nodes on stack ascending
			for (temp = fNameIndex.next(); temp != null; temp = fNameIndex.next()) //get next nodes
			{
				int x = temp.getRecNum(); // get record number
				System.out.println(database.get(x)); // print in big table
			}
		}
		if (a == 2 && b == 1)
		{

			lNameIndex.displayAscending();  // push lName nodes on stack ascending
			for (temp = lNameIndex.next(); temp != null; temp = lNameIndex.next()) //get next nodes
			{
				int x = temp.getRecNum(); // get record number
				System.out.println(database.get(x));
			}
		}		
		if (a == 3 && b == 1) 
		{

			IdIndex.displayAscending();  // push Id nodes on stack ascending
			for (temp = IdIndex.next(); temp != null; temp = IdIndex.next()) //get next nodes
			{
				int x = temp.getRecNum(); // get record number
				System.out.println(database.get(x)); // print in big table
			}
		}
		if (a == 1 && b == 2) 
		{
			fNameIndex.displayDescending(); // push fName nodes on stack descending
			for (temp = fNameIndex.next(); temp != null; temp = fNameIndex.next()) //get next nodes
			{
				int x = temp.getRecNum(); // get record number
				System.out.println(database.get(x)); // print in big table
			}	
		}
		if (a == 2 && b == 2)
		{
			lNameIndex.displayDescending();  // push lName nodes on stack descending
			for (temp = lNameIndex.next(); temp != null; temp = lNameIndex.next()) //get next nodes
			{
				int x = temp.getRecNum(); // get record number
				System.out.println(database.get(x)); // print in big table
			}
		}
		if (a == 3 && b == 2)
		{	
			IdIndex.displayDescending();  // push Id nodes on stack descending
			for (temp = IdIndex.next(); temp != null; temp = IdIndex.next()) //get next nodes
			{
				int x = temp.getRecNum(); // get record number
				System.out.println(database.get(x)); // print in big table
			}
		}
	}
}

