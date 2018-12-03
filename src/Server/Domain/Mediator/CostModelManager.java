package Server.Domain.Mediator;

import SharedModel.Cost;
import SharedModel.CostTupleList;
import SharedModel.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton
 * <p>
 * The Model Manager keeps the Model’s state –
 * in this case only a Tuple instance variable. The second instance variable is a CdPersistence.
 */
public class CostModelManager implements IModelManager<Cost>
{
	private static CostModelManager instance;
	private static Lock lock = new ReentrantLock();

	private ICostPersistance costPersistance;
	private Connection connection = null;
	private CostQuerry costQuerry = new CostQuerry(connection);

	private CostModelManager()
	{
		// MUST BE EMPTY - Lazy instantiation
	}

	public static CostModelManager getInstance()
	{
		if (instance == null)
			synchronized (lock)
			{
				if (instance == null)
					instance = new CostModelManager();
			}
		return instance;
	}

	@Override
	public ArrayList<Cost> assembleRelation() throws SQLException
	{
		// TODO put info into Model
		// TODO sout(the String)
		// TODO split the tuple strings received from the DB
		ResultSet rsCostTable = costQuerry.runCase();

		CostTupleList temp_costTupleList = CostTupleList.getInstance();
		ArrayList<Cost> temp_arr = temp_costTupleList.getTupleList();

		while (rsCostTable.next())
		{
			// TODO: Double check statement
			// TODO: In here you should set the values of each variable in each tuple from the tupleList
			temp_arr.add(
					new Cost(
							rsCostTable.getDouble("amount"),
							rsCostTable.getDate("date"),
							new Person(
									rsCostTable.getString("name"),
									rsCostTable.getString("coinbaseEmail"),
									rsCostTable.getString("btcWalletAddres"))
					)
			);
		}

		return temp_arr;
	}
}
