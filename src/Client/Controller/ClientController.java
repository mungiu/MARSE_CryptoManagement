package Client.Controller;

import SharedInterfaces.IClientController;
import SharedInterfaces.IServerController;
import SharedInterfaces.Observable;
import SharedModel.Cost;
import SharedModel.Item;

import java.io.IOException;
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

		return null;



	}

	public void addThisAsObserver() throws RemoteException
	{
		iServerController.addObserver(this);
	}

	@Override
	public void update(SharedInterfaces.Observable o, Object arg)
	{

	}

	@Override
	public void notify(Observable obs, String arg) throws IOException
	{

	}
}
