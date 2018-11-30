package Server.Domain.Model;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton
 */
public class CostTupleList
{
	private static CostTupleList instance;
	private static Lock lock = new ReentrantLock();

	private static ArrayList<Cost> tupleList;

	public CostTupleList()
	{
		tupleList = new ArrayList<>();
	}

	public static CostTupleList getInstance()
	{
		if (instance == null)
			synchronized (lock)
			{
				if (instance == null)
					instance = new CostTupleList();
			}

		return instance;
	}

	public ArrayList<Cost> getTupleList()
	{
		return tupleList;
	}
}
