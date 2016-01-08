/*
 * Amanda McCarty
 * COSC 311
 * Assignment #3
 * 11/24/2014
 * Version 02
 * 
 */

import java.util.Stack;
public class ThreadedTree 
{
	public class Node 
	{
		private IndexRecordClass data; // node variables
		private Node left, right;
		private boolean leftThread, rightThread;

		public void setData(IndexRecordClass d) // setters and getters
		{
			data = d;
		}

		public String getData()
		{
			return data.getKey();
		}

		public int getRecNum()
		{
			return data.getRecordNumber();
		}

		public void display()
		{
			System.out.println(data + " ");
		}
	}
	/////////end inner node class///////////

	private Node root; // tree variables
	private Stack<Node> stack = new Stack<>();


	public  ThreadedTree () // constructor
	{
		root = new Node();
		root.right = root.left = root;
		root.leftThread = true;
		root.data = new IndexRecordClass();
	}

	public Node search(String findKey) // search
	{
		Node temp = root.left;
		while (true) 
		{
			if (temp.data.getKey().compareTo(findKey) < 0) // if greater than go right
			{
				if (temp.rightThread) //if right thread return null
					return null;
				temp = temp.right; // else continue
			} 
			else if (temp.data.getKey().compareTo(findKey) > 0) // if less than go left
			{
				if (temp.leftThread) //if left thread return null
					return null;
				temp = temp.left; // else continue
			} 
			else 
			{
				return temp;	// return found node
			}
		}
	}

	public void reIndex(String findKey, int index) //reset index to match database index
	{
		Node temp = root.left;
		while (true) 
		{
			if (temp.data.getKey().compareTo(findKey) < 0) // go right
			{
				temp = temp.right; // continue
			} 
			else if (temp.data.getKey().compareTo(findKey) > 0) // go left
			{
				temp = temp.left; // continue
			} 
			else 
			{
				temp.data.setRecordNumber(index); //set index
				break;
			}
		}
	}
	
	public void delete(String delKey) // delete 
	{
		Node temp = root.left; 
		Node p = root;
		while (true) 
		{
			if (temp.data.getKey().compareTo(delKey) < 0) // go right
			{
				if (temp.rightThread) 
					return; // not found
					p = temp;
					temp = temp.right;
			} 
			else if (temp.data.getKey().compareTo(delKey) > 0) // go left
			{
				if (temp.leftThread) 
					return; // not found
				p = temp;
				temp = temp.left;
			} 
			else 
			{
				break; // found
			}
		}
		Node target = temp;
		if (!temp.rightThread && !temp.leftThread) // temp has two children
		{ 
			p = temp; 
			target = temp.left;
			while (!target.rightThread) // find largest node at left child
			{
				p = target;
				target = target.right;
			}
			temp.data = target.data; // using replace node
		}
		if (p.data.getKey().compareTo(target.data.getKey()) >= 0) //if p key greater then target key
		{
			if (target.rightThread && target.leftThread) //if left thread and right thread
			{
				p.left = target.left;
				p.leftThread = true;

			}
			else if (target.rightThread) //right thread only
			{
				Node largest = target.left;
				while (!largest.rightThread) 
				{
					largest = largest.right;
				}
				largest.right = p;
				p.left = target.left;
			} 
			else //left thread only
			{
				Node smallest = target.right;
				while (!smallest.leftThread) 
				{
					smallest = smallest.left;
				}
				smallest.left = target.left;
				p.left = target.right;
			}
		} 
		else //target greater
		{
			if (target.rightThread && target.leftThread) //if left thread and right thread
			{
				p.right = target.right;
				p.rightThread = true;
			} 
			else if (target.rightThread)  //right thread only
			{
				Node largest = target.left;
				while (!largest.rightThread) 
				{
					largest = largest.right;
				}
				largest.right =  target.right;
				p.right = target.left;
			} 
			else // left thread only
			{
				Node smallest = target.right;
				while (!smallest.leftThread) 
				{
					smallest = smallest.left;
				}
				smallest.left = p;
				p.right = target.right;
			}
		}
	}

	public void insert(String key, int num) // insert
	{
		Node p = root;
		while (true) 
		{
			if (p.data.getKey().compareTo(key) < 0) // if greater go right
			{
				if (p.rightThread) 
					break;
				p = p.right;
			} 
			else if (p.data.getKey().compareTo(key) > 0) // if less go left
			{
				if (p.leftThread) 
					break;
				p = p.left;
			} 
			else // redundant key
			{
				return; 
			}
		}
		IndexRecordClass irc = new IndexRecordClass(key, num); // set new irc for node
		Node temp = new Node();
		temp.setData(irc);
		temp.rightThread = temp.leftThread = true;
		if (p.data.getKey().compareTo(key) < 0) // insert to right side
		{ 
			temp.right = p.right;
			temp.left = p;
			p.right = temp;
			p.rightThread = false;
		} 
		else // insert to left
		{
			temp.right = p;
			temp.left = p.left;
			p.left = temp;
			p.leftThread = false;
		}
	}

	public void displayDescending() // display descending
	{     
		Node temp = root; 
		Node t;
		while(true) // travel left pushing visited nodes on stack 
		{
			t = temp;
			temp = temp.right;
			if (!t.rightThread) 
			{
				while (!temp.leftThread)
				{
					temp = temp.left;
				}
			}
			if (temp == root) break;
			stack.push(temp);
		}        
	}
	
	public void displayAscending() // display ascending
	{
		Node temp = root;
		Node t;
		while(true) // travel right pushing visited nodes on stack
		{
			t = temp;
			temp = temp.left;
			if (!t.leftThread) 
			{
				while (!temp.rightThread)
				{
					temp = temp.right;
				}
			}
			if (temp == root) break;
			stack.push(temp);
		}   
	}

	public Node next() // next node on stack
	{ 
		while(!stack.isEmpty())
			return stack.pop(); // pop nodes off if not empty

		return null;
	}
}

