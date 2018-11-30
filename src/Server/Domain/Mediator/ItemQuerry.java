package Server.Domain.Mediator;

import Server.Domain.Model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemQuerry extends Test_DbConnection<Item>
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
		rsItemTable = null;
		stmtPullItemRelation.close();

		System.out.println("Cost Query execution finalized.");
		return rsItemTable;
	}
}
