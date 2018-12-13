package Server.Domain.Mediator;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IPersistance
{
	ResultSet pullResultSet() throws SQLException;

	void pushInsertCommand(String sqlInsertCommand) throws SecurityException, SQLException;

	void pushUpdateCommand(String sqlUpdateCommand) throws SecurityException, SQLException;
}