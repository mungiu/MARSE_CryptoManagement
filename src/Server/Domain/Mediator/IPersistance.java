package Server.Domain.Mediator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This interface defines the signature of methods that are responsible for creating
 * PreparedStatements based on Client request type.
 * Any method inside this interface must nullify the created PreparedStatement's after these
 * have been execute to free up the resources reserved by them.
 */
public interface IPersistance
{
	/**
	 * Accesses the database and form a result set from the acquired data.
	 * NOTE: Nullifies the PreparedStatement after it has been executed to free up resources reserved by it.
	 * NOTE: Checks if the returned ResultSet is nullified after it is used wherever this method is called.
	 * @return ResultsSet
	 * @throws SQLException
	 */
	ResultSet pullResultSet() throws SQLException;

	/**
	 * Creates a PreparedStatement for the database from the received string and executes it.
	 * NOTE: Nullifies the PreparedStatement after it has been executed to free up resources reserved by it.
	 * @param sqlInsertCommand
	 * @throws SecurityException
	 * @throws SQLException
	 */
	void pushInsertCommand(String sqlInsertCommand) throws SecurityException, SQLException;

	/**
	 * Creates a PreparedStatement for the database from the received string and executes it.
	 * NOTE: Nullifies the PreparedStatement after it has been executed to free up resources reserved by it.
	 * @param sqlUpdateCommand
	 * @throws SecurityException
	 * @throws SQLException
	 */
	void pushUpdateCommand(String sqlUpdateCommand) throws SecurityException, SQLException;
}