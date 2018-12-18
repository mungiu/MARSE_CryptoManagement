
/*
 * Implementation of question number two
 * 
 * Only contains specific code for the question. Handling of statistics gathering
 * is inherited from QuestionRunner
 */

import java.sql.*;

public class QuestionTwo extends QuestionRunner {

	public QuestionTwo(Connection iconn) {

		this.conn = iconn;
	}

	public void runCase() throws SQLException {

		PreparedStatement stmtMother = conn.prepareStatement("select haircolor from mother" 
		                                                    + " where ID = ?" );
		PreparedStatement stmtChild = conn.prepareStatement("select id, motherID, yearBorn from child");
		int noOfChildren = 0;

		ResultSet rsChild = stmtChild.executeQuery();

		while (rsChild.next()) {

			if (rsChild.getInt("yearBorn") == 2013) {
				stmtMother.setInt(1, rsChild.getInt("motherID"));
				ResultSet rsMother = stmtMother.executeQuery();
				while (rsMother.next()) {
					if (rsMother.getString("haircolor").equals("blond")) {
						noOfChildren++;
					}
				}
				rsMother.close();
				rsMother = null;

			}
		}

		rsChild.close();
		rsChild = null;

		stmtMother.close();
		stmtChild.close();

		System.out.println("****************************************************************");
		System.out.println(" CASE TWO:number of children born in 2013 by blond mothers: " + noOfChildren);
		System.out.println(" Statistics for CASE TWO follows " );
	}
}
