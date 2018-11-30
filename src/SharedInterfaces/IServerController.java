package SharedInterfaces;

import Server.Domain.Model.Cost;
import Server.Domain.Model.Item;

import java.util.ArrayList;

public interface IServerController extends Observable<String>
{
	ArrayList<Item> executeItemRelationRequest(String type);

	ArrayList<Cost> executeCostRelationRequest(String type);
}
