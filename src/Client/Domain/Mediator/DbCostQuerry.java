package Client.Domain.Mediator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbCostQuerry extends Test_DbConnection
{
	public DbCostQuerry(Connection iconn)
	{
		this.conn = iconn;
	}

	// TODO change all the shit inside but keep the priciples
	@Override
	public void runCase() throws SQLException
	{


		// SELECT FOR FINANCE COLUMN

		PreparedStatement stmtMother = conn.prepareStatement("select id, hairColor from mother");
		PreparedStatement stmtChild = conn.prepareStatement("select id, yearBorn from"
				+ " child where motherID = ?");
		int noOfChildren = 0;


		// table of data representing a detabse result set obtained by querrying the database
		ResultSet rsMother = stmtMother.executeQuery();

		while (rsMother.next())
		{
			if (rsMother.getString("hairColor").equals("blond"))
			{
				stmtChild.setInt(1, rsMother.getInt("id"));
				ResultSet rsChild = stmtChild.executeQuery();
				while (rsChild.next())
				{
					if (rsChild.getInt("yearBorn") == 2013)
					{
						noOfChildren++;
					}
				}
				rsChild.close();
				rsChild = null;
			}
		}

		rsMother.close();
		rsMother = null;
		stmtMother.close();
		stmtChild.close();

		System.out.println("****************************************************************");
		System.out.println(" CASE ONE:number of children born in 2012 by blond mothers: " + noOfChildren);
		System.out.println(" Statistics for CASE ONE follows ");
	}
}
