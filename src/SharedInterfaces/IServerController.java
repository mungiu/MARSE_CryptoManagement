package SharedInterfaces;

import SharedModel.Cost;
import SharedModel.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IServerController extends Observable<String>
{
	ArrayList<Item> executeItemRelationRequest() throws SQLException;

	ArrayList<Cost> executeCostRelationRequest() throws SQLException;
}
