package Server.Domain.Mediator;

import SharedModel.Cost;
import SharedModel.CostTupleList;
import SharedModel.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton class acting as an adepter between the client-server communication with regards to Cost object types.
 * Converts SQL data into serializable objects to be passed on to the client.
 * Converts client requests into sql statements to be passed on to the database for execution.
 */
public class CostModelManager implements IModelManager<Cost>
{
	private static CostModelManager instance;
	private static Lock lock = new ReentrantLock();

	private IPersistance iPersistanceCost = new CostQuerry();

	private CostModelManager() throws SQLException
	{
		// MUST BE EMPTY - Lazy instantiation
	}

	public static CostModelManager getInstance() throws SQLException
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
	public ArrayList<Cost> assembleArrayList() throws SQLException
	{
		ResultSet rsCostTable = iPersistanceCost.pullResultSet();

		CostTupleList temp_costTupleList = CostTupleList.getInstance();
		ArrayList<Cost> temp_arr = temp_costTupleList.getTupleList();

		while (rsCostTable.next())
		{
			// TODO: Double check statement
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

	@Override
	public void assembleSQLInsertCommand(Cost object) throws SQLException
	{
		// TODO ensure proper StringBuilder implementation
		StringBuilder sb = new StringBuilder();

		// TODO correct SQL statement component names + add missing components (check excel)
		// sql INSERT command components: insert into "table_name" (col1, col2) values (val1, val2)
		sb.append("insert into cost_table ( payee, amount, incuredDate ) ");

		sb.append("values (" +
				object.getPayee() + "," +
				object.getAmount() + "," +
				object.getIncuredDate() + ")");

		String sqlInsertCommand = sb.toString();
		iPersistanceCost.pushInsertCommand(sqlInsertCommand);
	}

	@Override
	public void assembleSQLUpdateCommand(Cost object) throws SQLException
	{
		// TODO ensure proper StringBuilder implementation
		StringBuilder sb = new StringBuilder();

		// TODO correct SQL statement component names + add missing components (check excel)
		// sql UPDATE command components: update "table_name" set "col1 = val1, col2 = val2" where "condition";
		sb.append("update cost_table set ");

		sb.append("payee = " + object.getPayee() + "," +
				"amount = " + object.getAmount() + "," +
				"incuredDate = " + object.getIncuredDate() + ")");

		String sqlUpdateCommand = sb.toString();
		iPersistanceCost.pushUpdateCommand(sqlUpdateCommand);
	}
}
