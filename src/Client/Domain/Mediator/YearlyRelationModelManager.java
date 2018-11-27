package Client.Domain.Mediator;

import Client.Domain.Model.TupleList;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class YearlyRelationModelManager
{
	private static YearlyRelationModelManager instance;
	private static Lock lock = new ReentrantLock();

	private YearlyRelationModelManager()
	{

	}

	public static YearlyRelationModelManager getInstance()
	{
		if (instance == null)
			synchronized (lock)
			{
				if (instance == null)
					instance = new YearlyRelationModelManager();
			}

		return instance;
	}

	public static TupleList getTupleList()
	{
		TupleList.getInstance();
	}
}
