package Server.Domain.Mediator;

import SharedModel.Owner;
import SharedModel.OwnerTupleList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton class acting as an adepter between the client-server communication with regards to Owner object types.
 * Converts SQL data into serializable objects to be passed on to the client.
 * Converts client requests into sql statements to be passed on to the database for execution.
 */
public class OwnerModelManager implements IModelManager<Owner>
{
	private static OwnerModelManager instance;
	private static Lock lock = new ReentrantLock();

	private IPersistance iPersistanceOwner = new OwnerQuery();

	private OwnerModelManager() throws SQLException
	{
		// MUST BE EMPTY - Lazy instantiation
	}

	public static OwnerModelManager getInstance() throws SQLException
	{
		if (instance == null)
			synchronized (lock)
			{
				if (instance == null)
					instance = new OwnerModelManager();
			}
		return instance;
	}

	@Override
	public ArrayList<Owner> assembleArrayList() throws SQLException
	{
		PreparedStatement stmtPullOwnerRelation = iPersistanceOwner.getPreparedStatement();
		ResultSet rsOwner = stmtPullOwnerRelation.executeQuery();

		OwnerTupleList temp_ownerTupleList = OwnerTupleList.getInstance();
		ArrayList<Owner> temp_arr = temp_ownerTupleList.getTupleList();

		while (rsOwner.next())
		{
			temp_arr.add(
					new Owner(
							rsOwner.getString("owner"),
							rsOwner.getString("coinbaseEmail"),
							rsOwner.getString("btcWalletAddress")
					)
			);
		}
		// this also closes the ResultSet
		stmtPullOwnerRelation.close();

		return temp_arr;
	}

	@Override
	public void assembleSQLInsertCommand(Owner object) throws SQLException
	{
		StringBuilder sb = new StringBuilder();

		// SQL INSERT COMMAND COMPONENTS: insert into "table_name" (col1, col2) values ('val1', 'val2')
		sb.append("insert into owners ");
		sb.append("(owner, coinbaseEmail, btcWalletAddress) ");

		sb.append("values (");
		sb.append("'" + object.getName() + "'" + ",");
		sb.append("'" + object.getCoinbaseEmail() + "'" + ",");
		sb.append("'" + object.getBtcWalletAddress() + "'" + ")");

		iPersistanceOwner.pushInsertCommand(sb.toString());
	}

	@Override
	public void assembleSQLUpdateCommand(Owner object) throws SQLException
	{
		StringBuilder sb = new StringBuilder();

		// sql UPDATE command components: update "table_name" set "col1 = 'val1', col2 = 'val2'" where "condition";
		sb.append("update owners set ");

		sb.append("coinbaseEmail = " + "'" + object.getCoinbaseEmail() + "'" + ",");
		sb.append("btcWalletAddress = " + "'" + object.getBtcWalletAddress() + "'");

		sb.append(" where ");

		sb.append("owner = " + "'" + object.getName() + "'");

		iPersistanceOwner.pushUpdateCommand(sb.toString());
	}
}
