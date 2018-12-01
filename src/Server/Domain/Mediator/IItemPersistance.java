package Server.Domain.Mediator;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IItemPersistance
{
	ResultSet pullItemResultSet() throws SQLException;
}