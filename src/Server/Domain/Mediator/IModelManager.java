package Server.Domain.Mediator;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IModelManager<T>
{
	ArrayList<T> assembleArrayList() throws SQLException;

	void assembleSQLInsertCommand(T object) throws  SQLException;

	void assembleSQLUpdateCommand(T object) throws SQLException;
}


