package Client.Domain.Model;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Instantiates itself in itself, cannot be instantiated anywhere else outside the class itself
 */
public class TupleList
{
	private static TupleList instance;
	private static Lock lock = new ReentrantLock();

	private static ArrayList<Cost> costArrayList;
	private static ArrayList<Item> itemArrayList;

	private TupleList()
	{
		costArrayList = new ArrayList<>();
		itemArrayList = new ArrayList<>();
	}

	public static TupleList getInstance()
	{
		if (instance == null)
			synchronized (lock)
			{
				if (instance == null)
					instance = new TupleList();
			}

		return instance;
	}
}
