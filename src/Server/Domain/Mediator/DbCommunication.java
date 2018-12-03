package Server.Domain.Mediator;

import java.sql.*;

public class DbCommunication
{
	private static Connection conn;

	/* connection details */
	/* connects via TNS */

	final static String connectString = "jdbc:postgresql:database";
	final static String userName = "mungiu";
	final static String password = "1111";

	public static void main(String[] args)
	{
		try
		{
			// TODO change so it works with postgre SQL
//			DriverManager.registerDriver(/*new oracle.jdbc.OracleDriver()*/);
//			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(connectString, userName, password);

			conn.setAutoCommit(false);
			System.out.println("connection established, autocommit off");

			/*
			 * Individual questions are instantiated and run here Each question is a
			 * specialization of the QuestionRunner class
			 */

//			try
//			{
//				QuestionRunner q = new QuestionOne(conn);
//				q.execute();
//				q = null;
//
//				q = new QuestionTwo(conn);
//				q.execute();
//				q = null;
//
//
//				q = new QuestionSeven(conn);
//				q.execute();
//				q = null;
//
//			}
//			catch (Exception e)
//			{
//				System.out.println("error running case, see messages for details");
//				System.out.println(e.getMessage());
//				e.printStackTrace();
//
//			}
		}
		catch (SQLException e)
		{
			System.out.println("error establishing connection");
			System.out.println("Connection string in use: " + connectString + "(user/pwd " + userName + "/" + password + ")");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		try
		{
			conn.close();
			System.out.println("connection closed");
			conn = null;
		}
		catch (SQLException e)
		{
			System.out.println("error closing connection");
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
	}
}
