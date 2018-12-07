package Server.Domain.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Create_Table
{
	//TODO understand how this class should be correctly used
	public static void main(String args[])
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			c = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/postgres",
							"postgres", "postgres");
			System.out.println("Database open ok");

			stmt = c.createStatement();
			String sql = "CREATE TABLE \"dreamhome\".tabletest1 " +
					"(ID INT PRIMARY KEY     NOT NULL," +
					" NAME           TEXT    NOT NULL, " +
					" AGE            INT     NOT NULL, " +
					" ADDRESS        CHAR(50), " +
					" SALARY         REAL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		}
		catch (Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Database table ok");
	}
}
