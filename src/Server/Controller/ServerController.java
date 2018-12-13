package Server.Controller;

import Server.Domain.Mediator.CostModelManager;
import Server.Domain.Mediator.ItemModelManager;
import SharedInterfaces.IServerController;
import SharedInterfaces.Observer;
import SharedModel.Cost;
import SharedModel.Item;

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

	public ServerController() throws RemoteException, MalformedURLException, SQLException
	{
		costModelManager = CostModelManager.getInstance();
		itemModelManager = ItemModelManager.getInstance();

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
