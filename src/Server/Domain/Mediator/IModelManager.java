package Server.Domain.Mediator;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IModelManager<T>
{
	ArrayList<T> assembleRelation() throws SQLException;
}


