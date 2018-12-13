package Server.Domain.Mediator;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IModelManager<T>
{
	/**
	 * Assembles and array list of type "T" using the obtained ResultSet from the database
	 * and the description of what "T" should store from the Model package.
	 * After assembly is completed, the ResultSet .close() method should be called to release the resources.
	 *
	 * @return ArrayList filled with Objects of type "T"
	 * @throws SQLException
	 */
	ArrayList<T> assembleArrayList() throws SQLException;

	/**
	 * Assembles and SQL "insert" command using the "T" object variables.
	 * SQL insert command example: insert into "table_name" (col1, col2) values (val1, val2)
	 *
	 * @param object the passed in object that carries the data necessary to be inserted into the PostgreSQL database.
	 * @throws SQLException
	 */
	void assembleSQLInsertCommand(T object) throws SQLException;

	/**
	 * Assembles and SQL "update" command using the "T" object variables.
	 * SQL update command example: 	update "table_name" set "col1 = val1, col2 = val2" where "condition";
	 *
	 * @param object the passed in object that carries the data necessary to be inserted into the PostgreSQL database.
	 * @throws SQLException
	 */
	void assembleSQLUpdateCommand(T object) throws SQLException;
}


