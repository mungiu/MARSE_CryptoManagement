package Server.Domain.Mediator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public ResultSet pullResultSet() throws SQLException
	{
		// TODO check is this is the correct way to select from a table AKA should schema be specified or not?
		PreparedStatement stmtPullCostRelation = conn.prepareCall("select * from costs");
		ResultSet rsCostTable = stmtPullCostRelation.executeQuery();
		// freeing up resources used by the PreparedStatement since it has been executed and its result stored
		stmtPullCostRelation.close();
		conn.close();

		// TODO replace with a logger
		System.out.println("Cost query execution finalized.");
		return rsCostTable;
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
