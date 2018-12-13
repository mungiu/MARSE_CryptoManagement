package Server.Domain.Mediator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton (thread safe) class establishing a connection to the database on instantiation using the java.sql.Connection class.
 * TODO have a connect button in our interface? That will run this?
 */
public class DbCommunication
{
	private Connection conn;

	final static String connectString = "jdbc:postgresql://localhost:5432/postgres";
	final static String userName = "postgres";
	final static String password = "1111";
	private int count = 0;

	public DbCommunication()
	{
		try
		{
			// NOTE: Oracle connection is implemented differently, check Oracle documentation
			conn = DriverManager.getConnection(connectString, userName, password);
			conn.setSchema("MARSE");
			// TODO AutoCommi off?
//			conn.setAutoCommit(false);

			// TODO have a logger for all print outs
			System.out.println("Connection established, autocommit ON");
		}
		catch (SQLException e)
		{
			// TODO have a logger for all print outs
			System.out.println("error establishing connection");
			System.out.println("Connection string in use: " + connectString + "(user/pwd " + userName + "/" + password + ")");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		count++;
		System.out.println(count);
	}

	public Connection getConn()
	{
		return conn;
	}
}
