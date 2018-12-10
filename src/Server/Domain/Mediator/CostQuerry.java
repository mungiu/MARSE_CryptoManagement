package Server.Domain.Mediator;

import SharedModel.Cost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CostQuerry extends Test_DbConnection<Cost> implements ICostPersistance
{
	public CostQuerry(Connection iconn)
	{
		this.conn = iconn;
	}

	@Override
	public ResultSet runCase() throws SQLException
	{
		// TODO: Double check statement
		PreparedStatement stmtPullCostRelation = conn.prepareCall("select * from costTable");

		// table of data representing a database "result set" obtained by quarrying the database
		ResultSet rsCostTable = stmtPullCostRelation.executeQuery();

		// Closing the doors
		rsCostTable.close();
		stmtPullCostRelation.close();

		System.out.println("Cost Query execution finalized.");
		return rsCostTable;
		//TODO remember to nullify rsItemTable after its sent to the client
	}

	@Override
	public ResultSet pullCostResultSet() throws SQLException
	{
		return runCase();
	}
}
