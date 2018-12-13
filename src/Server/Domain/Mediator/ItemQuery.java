package Server.Domain.Mediator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemQuery extends DbCommunication implements IPersistance
{
	DbCommunication dbCommunication;
	Connection conn;

	public ItemQuery() throws SQLException
	{
		super();
		dbCommunication = DbCommunication.getInstance();
		conn = dbCommunication.getConn();
	}

	@Override
	public ResultSet pullResultSet() throws SQLException
	{
		PreparedStatement stmtPullItemRelation = conn.prepareCall("select * from item");
		ResultSet rsItemTable = stmtPullItemRelation.executeQuery();
		// freeing up resources used by the PreparedStatement since it has been executed and its result stored
		stmtPullItemRelation.close();

		// TODO replace with a logger
		System.out.println("Item query execution finalized.");
		return rsItemTable;
	}

	@Override
	public void pushInsertCommand(String sqlInsertCommand) throws SecurityException, SQLException
	{
		PreparedStatement stmtTupleInsert = conn.prepareCall(sqlInsertCommand);

		stmtTupleInsert.execute();
		stmtTupleInsert.close();

		// TODO replace with a logger
		System.out.println("Item tuple insertion executed");
	}

	@Override
	public void pushUpdateCommand(String sqlUpdateCommand) throws SecurityException, SQLException
	{
		PreparedStatement stmtTupleUpdate = conn.prepareCall(sqlUpdateCommand);

		stmtTupleUpdate.execute();
		stmtTupleUpdate.close();

		// TODO replace with a logger
		System.out.println("Item tuple update execute");
	}
}
