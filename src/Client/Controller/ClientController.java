package Client.Controller;

import SharedInterfaces.IClientController;
import SharedInterfaces.IServerController;
import SharedInterfaces.Observable;
import SharedModel.Cost;
import SharedModel.CostTupleList;
import SharedModel.Item;
import SharedModel.ItemTupleList;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientController implements IClientController
{
	private IServerController iServerController;

	public ClientController() throws RemoteException, MalformedURLException, NotBoundException
	{
		addThisAsObserver();

		UnicastRemoteObject.exportObject(this, 0);
		// "rmi://<ip>:<port>/<serverName>
		iServerController = (IServerController) Naming.lookup("rmi://localhost:1099/iServerController");
	}


	public ArrayList<Cost> requestCostRelation()
	{
		try
		{
			return iServerController.executeCostRelationRequest();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Item> requestItemRelation()
	{
		try
		{
			return iServerController.executeItemRelationRequest();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}

		return null;


	}

	public void addThisAsObserver() throws RemoteException
	{
		iServerController.addObserver(this);
	}

	/**
	 * 
	 * @param obs
	 * @param arg
	 */
	@Override
	public void notify(Observable obs, String arg)
	{
		ArrayList<Cost> temp_costArrayList = CostTupleList.getInstance().getTupleList();
		ArrayList<Item> temp_itemArrayList = ItemTupleList.getInstance().getTupleList();
		// TODO when a client is in a certain view, that view info has to be updated
		// checking what view client is in
		if(temp_costArrayList.isEmpty() && temp_itemArrayList.isEmpty() /*&& check that all other lists .isEmpty() at the same time*/)
			return;
		else if (temp_costArrayList.isEmpty()/* && check if all lists except itemArrList .isEmpty()*/)
			requestItemRelation();
		/*TODO add more ifs to check any other client state*/

		System.out.println("Notified, change this");
	}
}
