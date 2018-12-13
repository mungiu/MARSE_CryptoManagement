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

	private IPersistance iPersistanceCost = new CostQuery();

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
		// pulling from Database
		ResultSet rsCostTable = iPersistanceCost.pullResultSet();

		// pulling from Model
		CostTupleList temp_costTupleList = CostTupleList.getInstance();
		ArrayList<Cost> temp_arr = temp_costTupleList.getTupleList();

		// Assembling Data into Model
		while (rsCostTable.next())
		{
			// TODO: Inside the DB edit "serialid" into "serial_id"
			temp_arr.add(
					new Cost(
							rsCostTable.getInt("serial_id"),
							rsCostTable.getString("category"),
							new Person(
									rsCostTable.getString("name"),
									rsCostTable.getString("coinbaseEmail"),
									rsCostTable.getString("btcWalletAddres")),
							rsCostTable.getString("description"),
							rsCostTable.getDouble("ordervalue"),
							rsCostTable.getDouble("reimbursed"),
							rsCostTable.getDate("paymentdate"),
							rsCostTable.getString("status"),
							rsCostTable.getString("notes")
					)
			);
		}
		// Freeing up resources
		rsCostTable.close();

		return temp_arr;
	}

	@Override
	public void assembleSQLInsertCommand(Cost object) throws SQLException
	{
		// TODO ensure proper StringBuilder implementation
		StringBuilder sb = new StringBuilder();

		// TODO correct SQL statement component names + add missing components (check excel)
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
		sb.append("update cost_table set ");

		sb.append("payee = " + object.getPayee() + "," +
				"amount = " + object.getAmount() + "," +
				"incuredDate = " + object.getIncuredDate() + ")");

		String sqlUpdateCommand = sb.toString();
		iPersistanceCost.pushUpdateCommand(sqlUpdateCommand);
	}
}
