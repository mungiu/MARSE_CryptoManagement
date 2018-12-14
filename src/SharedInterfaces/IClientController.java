package SharedInterfaces;

import SharedModel.Cost;
import SharedModel.Item;
import SharedModel.Owner;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is required for RMI connection, methods MUST be inside it, to be later called even by the client itself.
 */
public interface IClientController extends Observer<String>
{
	void addThisAsObserver() throws RemoteException;

	ArrayList<Cost> requestCostRelation() throws RemoteException;

	ArrayList<Item> requestItemRelation() throws RemoteException;

	ArrayList<Owner> requestOwnerRelation() throws RemoteException;

	void insertCostObject(Cost cost) throws RemoteException, SQLException;

	void insertItemObject(Item item) throws RemoteException, SQLException;

	void insertOwnerObject(Owner owner) throws RemoteException, SQLException;

	void updateItemTuple(Item item) throws RemoteException, SQLException;

	void updateCostTuple(Cost cost) throws RemoteException, SQLException;

	void updateOwnerTuple(Owner owner) throws RemoteException, SQLException;
}
