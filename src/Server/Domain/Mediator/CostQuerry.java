package Server.Domain.Mediator;

import Server.Domain.Model.Cost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CostQuerry extends Test_DbConnection<Cost>
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
		rsCostTable = null;
		stmtPullCostRelation.close();

		System.out.println("Cost Query execution finalized.");
		return rsCostTable;
	}
}
