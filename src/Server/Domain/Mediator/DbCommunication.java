package Server.Domain.Mediator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton class establishing a connection to the database on instantiation using the java.sql.Connection class.
 * TODO have a connect button in our interface? That will run this?
 */
public class DbCommunication
{
	private Connection conn;

	private static DbCommunication instance;
	private static final Lock lock = new ReentrantLock();

	/* connection details */
	/* connects via TNS */

	final static String connectString = "jdbc:postgresql:database";
	final static String userName = "mungiu";
	final static String password = "1111";

	public DbCommunication() throws SQLException
	{
		try
		{
			// TODO change so it works with postgre SQL
//			DriverManager.registerDriver(/*new oracle.jdbc.OracleDriver()*/);
//			FileSearch.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(connectString, userName, password);

			conn.setAutoCommit(false);
			// TODO have a logger for all print outs
			System.out.println("connection established, autocommit off");
		}
		catch (SQLException e)
		{
			// TODO have a logger for all print outs
			System.out.println("error establishing connection");
			System.out.println("Connection string in use: " + connectString + "(user/pwd " + userName + "/" + password + ")");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		try
		{
			conn.close();
			// TODO have a logger for all print outs
			System.out.println("connection closed");
			conn = null;
		}
		catch (SQLException e)
		{
			// TODO have a logger for all print outs
			System.out.println("error closing connection");
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static DbCommunication getInstance()
	{
		if (instance == null)
			synchronized (lock)
			{
				if (instance == null)
					return instance;
			}

		return instance;
	}


	public Connection getConn()
	{
		return conn;
	}
}
