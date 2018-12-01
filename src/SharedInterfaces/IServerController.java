package SharedInterfaces;

import Server.Domain.Model.Cost;
import Server.Domain.Model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IServerController extends Observable<String>
{
	ArrayList<Item> executeItemRelationRequest(String type) throws SQLException;

	ArrayList<Cost> executeCostRelationRequest(String type) throws SQLException;
}
