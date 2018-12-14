package Client.Controller;

import SharedInterfaces.IClientController;
import SharedInterfaces.IServerController;
import SharedInterfaces.Observable;
import SharedModel.*;
import org.postgresql.util.PSQLException;

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
		UnicastRemoteObject.exportObject(this, 0);
		// "rmi://<ip>:<port>/<serverName>
		iServerController = (IServerController) Naming.lookup("rmi://localhost:1099/iServerController");

		addThisAsObserver();
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

	@Override
	public ArrayList<Owner> requestOwnerRelation() throws RemoteException
	{
		try
		{
			return iServerController.executeOwnerRelationRequest();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void insertCostObject(Cost cost) throws RemoteException, SQLException
	{
		iServerController.executeCostInsert(cost);
		System.out.println("Inserted: " + cost.toString());
	}

	@Override
	public void insertItemObject(Item item) throws RemoteException, SQLException
	{
		iServerController.executeItemInsert(item);
		System.out.println("Inserted: " + item.toString());
	}

	@Override
	public void insertOwnerObject(Owner owner) throws RemoteException, SQLException
	{
		try
		{
			iServerController.executeOwnerInsert(owner);
			System.out.println("Inserted: " + owner.toString());
		}
		catch (PSQLException e)
		{
			System.out.println("ERROR: duplicate key value violates unique constraint \"owners_pkey\"" +
					"Detail: Key (owner)=(" + owner.getName() + ") already exists.");
		}
	}

	@Override
	public void updateItemTuple(Item item) throws RemoteException, SQLException
	{
		iServerController.executeUpdateItemTuple(item);
		System.out.println("Inserted: " + item.toString());
	}

	@Override
	public void updateCostTuple(Cost cost) throws RemoteException, SQLException
	{
		iServerController.executeUpdateCostTuple(cost);
		System.out.println("Inserted: " + cost.toString());
	}

	@Override
	public void updateOwnerTuple(Owner owner) throws RemoteException, SQLException
	{
		iServerController.executeUpdateOwnerTuple(owner);
		System.out.println("Inserted: " + owner.toString());
	}

	public void addThisAsObserver() throws RemoteException
	{
		iServerController.addObserver(this);
	}

	/**
	 * TODO
	 * Checks what view the client is currently in
	 * and FORCE updates the data for that specific view by making the client request the data itself.
	 *
	 * @param obs The observable object, in this case should be the server.
	 * @param arg Not yet defined, TODO
	 */
	@Override
	public void notify(Observable obs, String arg)
	{
		ArrayList<Cost> temp_costArrayList = CostTupleList.getInstance().getTupleList();
		ArrayList<Item> temp_itemArrayList = ItemTupleList.getInstance().getTupleList();

		if (temp_costArrayList.isEmpty() && temp_itemArrayList.isEmpty() /*&& check that all other lists .isEmpty() at the same time*/)
			return;
		else if (temp_costArrayList.isEmpty()/* && check if all lists except itemArrList .isEmpty()*/)
			requestItemRelation();
		else if (temp_itemArrayList.isEmpty()/* && check if all lists except itemArrList .isEmpty()*/)
			requestCostRelation();
		// TODO add more ifs to check any other client state

		// TODO make a logger class instead of all printouts
		System.out.println("Current view updated");
	}
}
