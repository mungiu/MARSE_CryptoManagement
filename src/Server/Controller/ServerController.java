package Server.Controller;

import Server.Domain.Mediator.CostModelManager;
import Server.Domain.Mediator.ItemModelManager;
import Server.Domain.Mediator.OwnerModelManager;
import SharedInterfaces.IServerController;
import SharedInterfaces.Observer;
import SharedModel.Cost;
import SharedModel.Item;
import SharedModel.Owner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerController implements IServerController
{
	private CostModelManager costModelManager;
	private ItemModelManager itemModelManager;
	private OwnerModelManager ownerModelManager;

	public ServerController() throws RemoteException, MalformedURLException, SQLException
	{
		costModelManager = CostModelManager.getInstance();
		itemModelManager = ItemModelManager.getInstance();
		ownerModelManager = OwnerModelManager.getInstance();

		UnicastRemoteObject.exportObject(this, 0);

		// "rmi://<ip>:<port>/<serverName>
//		iClientController = (IClientController) Naming.lookup("rmi://localhost:1099/iClientController");
	}

	@Override
	public ArrayList<Item> executeItemRelationRequest() throws SQLException
	{
		return itemModelManager.assembleArrayList();
	}

	@Override
	public ArrayList<Cost> executeCostRelationRequest() throws SQLException
	{
		return costModelManager.assembleArrayList();
	}

	@Override
	public ArrayList<Owner> executeOwnerRelationRequest() throws SQLException, RemoteException
	{
		return ownerModelManager.assembleArrayList();
	}

	@Override
	public void executeCostInsert(Cost cost) throws SQLException, RemoteException
	{
		costModelManager.assembleSQLInsertCommand(cost);
	}

	@Override
	public void executeItemInsert(Item item) throws SQLException, RemoteException
	{
		itemModelManager.assembleSQLInsertCommand(item);
	}

	@Override
	public void executeOwnerInsert(Owner owner) throws SQLException, RemoteException
	{
		ownerModelManager.assembleSQLInsertCommand(owner);
	}

	@Override
	public void executeUpdateItemTuple(Item item) throws SQLException, RemoteException
	{
		itemModelManager.assembleSQLUpdateCommand(item);
	}

	@Override
	public void executeUpdateCostTuple(Cost cost) throws SQLException, RemoteException
	{
		costModelManager.assembleSQLUpdateCommand(cost);
	}

	@Override
	public void executeUpdateOwnerTuple(Owner owner) throws SQLException, RemoteException
	{
		ownerModelManager.assembleSQLUpdateCommand(owner);
	}

	@Override
	public void addObserver(Observer<String> obs) throws RemoteException
	{
		observers.add(obs);
	}

	@Override
	public void deleteObserver(Observer<String> obs) throws RemoteException
	{
		observers.remove(obs);
	}

	@Override
	public void notifyObservers(String arg) throws IOException
	{
		// TODO check what you need instead of "something"
		for (Observer<String> obs : observers)
			obs.notify(this, "something");
	}
}
