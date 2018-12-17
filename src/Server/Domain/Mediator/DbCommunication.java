package Server.Domain.Mediator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton (thread safe) class establishing a connection to the database on instantiation using the java.sql.Connection class.
 */
public class DbCommunication
{
	private Connection conn;

	private static DbCommunication instance;
	private static Lock lock = new ReentrantLock();

	private final static String connectString = "jdbc:postgresql://localhost:5432/postgres";
	private final static String userName = "postgres";
	private final static String password = "1111";
	private int count = 0;

	private DbCommunication()
	{
		try
		{
			// NOTE: Oracle connection is implemented differently, check Oracle documentation
			conn = DriverManager.getConnection(connectString, userName, password);
			conn.setSchema("MARSE");
			// TODO AutoCommit off?
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

	// NOTE: no modifier means its package private
	Connection getConn()
	{
		return conn;
	}

	public static DbCommunication getInstance()
	{
		if (instance == null)
			synchronized ((lock))
			{
				if (instance == null)
					instance = new DbCommunication();
			}

		return instance;
	}
}
