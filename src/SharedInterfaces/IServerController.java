package SharedInterfaces;

import SharedModel.Cost;
import SharedModel.Item;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is required for RMI connection, methods MUST be inside it, to be later called by the client.
 */
public interface IServerController extends Observable<String>
{
	ArrayList<Item> executeItemRelationRequest() throws SQLException, RemoteException;

	ArrayList<Cost> executeCostRelationRequest() throws SQLException, RemoteException;
}
