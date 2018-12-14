package SharedModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OwnerTupleList implements Serializable
{
	private static OwnerTupleList instance;
	private static Lock lock = new ReentrantLock();

	private static ArrayList<Owner> tupleList;

	public OwnerTupleList()
	{
		tupleList = new ArrayList<>();
	}

	public static OwnerTupleList getInstance()
	{
		if (instance == null)
			synchronized (lock)
			{
				if (instance == null)
					instance = new OwnerTupleList();
			}

		return instance;
	}

	public ArrayList<Owner> getTupleList()
	{
		return tupleList;
	}
}
