
/*
 * Implementation of question number one
 * 
 * Only contains specific code for the question. Handling of statistics gathering
 * is inherited from QuestionRunner
 */

import java.sql.*;

/**
 * DA_WAE
 */
public class QuestionOne extends QuestionRunner {

	public QuestionOne(Connection iconn) {

		this.conn = iconn;
	}

	public void runCase() throws SQLException {



		// SELECT FOR FINANCE COLUMN

		// SELECT FOR INVENTORY COLUMN

		PreparedStatement stmtMother = conn.prepareStatement("select id, hairColor from mother");
		PreparedStatement stmtChild = conn.prepareStatement("select id, yearBorn from" 
		                                                     + " child where motherID = ?");
		int noOfChildren = 0;


		// table of data representing a detabse result set obtained by querrying the database
		ResultSet rsMother = stmtMother.executeQuery();

		while (rsMother.next()) {
			if (rsMother.getString("hairColor").equals("blond")) {
				stmtChild.setInt(1, rsMother.getInt("id"));
				ResultSet rsChild = stmtChild.executeQuery();
				while (rsChild.next()) {
					if (rsChild.getInt("yearBorn") == 2013) {
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
		System.out.println(" Statistics for CASE ONE follows " );
	}
}
