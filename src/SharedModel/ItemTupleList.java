package SharedModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton
 */
public class ItemTupleList implements Serializable
{
	private static ItemTupleList instance;
	private static Lock lock = new ReentrantLock();

	private static ArrayList<Item> itemList;

	private ItemTupleList()
	{
		itemList = new ArrayList<>();
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
