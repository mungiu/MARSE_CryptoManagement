package Server.Domain.Mediator;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ICostPersistance
{
	ResultSet pullCostResultSet() throws SQLException;
}