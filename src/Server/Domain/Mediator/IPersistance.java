package Server.Domain.Mediator;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IPersistance
{
	ResultSet pullResultSet() throws SQLException;

	void pushTupleInsertStatement() throws SecurityException;
}