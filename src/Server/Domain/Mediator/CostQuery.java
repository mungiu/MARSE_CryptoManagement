package Server.Domain.Mediator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CostQuery extends DbCommunication implements IPersistance
{
//	DbCommunication dbCommunication;
	Connection conn;

	public CostQuery() throws SQLException
	{
		super();
//		dbCommunication = new DbCommunication();
		conn = super.getConn();
	}

	@Override
	public PreparedStatement getPreparedStatement() throws SQLException
	{
		PreparedStatement stmtPullCostRelation = conn.prepareCall("select * from costs;");

		// TODO replace with a logger
		System.out.println("Cost query execution finalized.");
		return stmtPullCostRelation;
	}

	@Override
	public void pushInsertCommand(String sqlInsertCommand) throws SecurityException, SQLException
	{
		PreparedStatement stmtTupleInsert = conn.prepareCall(sqlInsertCommand);

		stmtTupleInsert.execute();
		stmtTupleInsert.close();

		// TODO replace with a logger
		System.out.println("Cost tuple insertion executed");
	}

	@Override
	public void pushUpdateCommand(String sqlUpdateCommand) throws SecurityException, SQLException
	{
		PreparedStatement stmtTupleUpdate = conn.prepareCall(sqlUpdateCommand);

		stmtTupleUpdate.execute();
		stmtTupleUpdate.close();

		// TODO replace with a logger
		System.out.println("Cost tuple update execute");
	}
}
