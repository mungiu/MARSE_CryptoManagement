package Server.Domain.Mediator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OwnerQuery extends DbCommunication implements IPersistance
{
	Connection conn;

	public OwnerQuery()
	{
		super();
		conn = super.getConn();
	}

	@Override
	public PreparedStatement getPreparedStatement() throws SQLException
	{
		PreparedStatement stmtPullOwnerRelation = conn.prepareCall("select * from owners;");

		// TODO replace with a logger
		System.out.println("Owner query execution finalized.");
		return stmtPullOwnerRelation;
	}

	@Override
	public void pushInsertCommand(String sqlInsertCommand) throws SecurityException, SQLException
	{
		PreparedStatement stmtTupleInsert = conn.prepareCall(sqlInsertCommand);

		stmtTupleInsert.execute();
		stmtTupleInsert.close();

		// TODO replace with a logger
		System.out.println("Owner tuple insertion executed");
	}

	@Override
	public void pushUpdateCommand(String sqlUpdateCommand) throws SecurityException, SQLException
	{
		PreparedStatement stmtTupleUpdate = conn.prepareCall(sqlUpdateCommand);

		stmtTupleUpdate.execute();
		stmtTupleUpdate.close();

		// TODO replace with a logger
		System.out.println("Owner tuple update execute");
	}
}
