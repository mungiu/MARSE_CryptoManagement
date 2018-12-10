package SharedInterfaces;

import SharedModel.Cost;
import SharedModel.Item;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This class is required for RMI connection, methods MUST be inside it, to be later called even by the client itself.
 */
public interface IClientController extends Observer<String>
{
	ArrayList<Cost> requestCostRelation() throws RemoteException;

	ArrayList<Item> requestItemRelation() throws RemoteException;

	void addThisAsObserver() throws RemoteException;
}
