package SharedInterfaces;

import SharedModel.Cost;
import SharedModel.Item;
import SharedModel.Owner;

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

	ArrayList<Owner> executeOwnerRelationRequest() throws SQLException, RemoteException;

	void executeCostInsert(Cost cost) throws SQLException, RemoteException;

	void executeItemInsert(Item item) throws SQLException, RemoteException;

	void executeOwnerInsert(Owner owner) throws SQLException, RemoteException;

	void executeUpdateItemTuple(Item item) throws SQLException, RemoteException;

	void executeUpdateCostTuple(Cost cost) throws SQLException, RemoteException;

	void executeUpdateOwnerTuple(Owner owner) throws SQLException, RemoteException;
}
