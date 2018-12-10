package Server.Domain.Mediator;

import SharedModel.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemQuerry extends Test_DbConnection<Item> implements IPersistance
{
	public ItemQuerry(Connection iconn)
	{
		this.conn = iconn;
	}

	@Override
	public ResultSet runCase() throws SQLException
	{
		// TODO: Double check statement
		PreparedStatement stmtPullItemRelation = conn.prepareCall("select * from itemTable");

		// table of data representing a database "result set" obtained by quarrying the database
		ResultSet rsItemTable = stmtPullItemRelation.executeQuery();

		// Closing the doors
		rsItemTable.close();
		stmtPullItemRelation.close();

		System.out.println("Cost Query execution finalized.");
		return rsItemTable;
		//TODO remember to nullify rsItemTable after its sent to the client
	}

	@Override
	public ResultSet pullResultSet() throws SQLException
	{
		return runCase();
	}

	@Override
	public void pushTupleInsertStatement() throws SecurityException
	{

	}
}
