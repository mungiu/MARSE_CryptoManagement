package Server.Domain.Mediator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CostQuerry extends DbCommunication implements IPersistance
{
	DbCommunication dbCommunication;
	Connection conn;

	public CostQuerry() throws SQLException
	{
		super();
		dbCommunication = DbCommunication.getInstance();
		conn = dbCommunication.getConn();
	}

	@Override
	public ResultSet pullResultSet() throws SQLException
	{
		// TODO: Double check statement
		PreparedStatement stmtPullCostRelation = conn.prepareCall("select * from costTable");

		// table of data representing a database "result set" obtained by quarrying the database
		ResultSet rsCostTable = stmtPullCostRelation.executeQuery();

		// Closing the doors
		rsCostTable.close();
		stmtPullCostRelation.close();

		// TODO replace with a logger
		System.out.println("Cost Query execution finalized.");
		return rsCostTable;
		//TODO remember to nullify rsItemTable after its sent to the client
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
