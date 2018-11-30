package Server.Domain.Model;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton
 */
public class ItemTupleList
{
	private static ItemTupleList instance;
	private static Lock lock = new ReentrantLock();

	private static ArrayList<Item> itemList = new ArrayList<>();

	private ItemTupleList()
	{

	}

	public static ItemTupleList getInstance()
	{
		if (instance == null)
			synchronized (lock)
			{
				if (instance == null)
					instance = new ItemTupleList();
			}
		return instance;
	}

	public ArrayList<Item> getTupleList()
	{
		return itemList;
	}
}
