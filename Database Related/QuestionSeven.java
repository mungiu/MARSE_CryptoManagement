
/*
 * Implementation of question number seven
 * 
 * Only contains specific code for the question. Handling of statistics gathering
 * is inherited from QuestionRunner
 */

import java.sql.*;

public class QuestionSeven extends QuestionRunner {

	public QuestionSeven(Connection iconn) {

		this.conn = iconn;
	}

	public void runCase() throws SQLException {

		PreparedStatement stmt = conn.prepareStatement(   "select count(*) as noOfChildren" 
                                               +  "  from mother m"
                                               +  "  join child c "
                                               +  "    on (c.motherId = m.id) "
                                               +  "  where m.haircolor = 'blond'"
                                               +  "   and  c.yearBorn = 2013 ");

		ResultSet rs = stmt.executeQuery();

		rs.next();

		System.out.println("****************************************************************");
		System.out.println(" CASE SEVEN:number of children born in 2012 by blond mothers: "
		                    + rs.getInt("noOfChildren"));

		rs.close();
		stmt.close();

		System.out.println(" Statistics for CASE ONE follows " );
	}
}
