package Client.View;


import Client.Controller.IServerController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;

public class ClientController implements IClientController
{
	IServerController dbController;

	public ClientController() throws RemoteException, MalformedURLException, NotBoundException
	{
		UnicastRemoteObject.exportObject(this, 0);
		dbController = (IServerController) Naming.lookup("rmi://localhost:1099/serverController");
		// adding the player as an observer immediately upon creation
		dbController.addObserver(this);
	}

	@Override
	public void displayItemRelation()
	{
		dbController.executeItemRelation();
	}

	@Override
	public void displayCostRelation()
	{
		dbController.executeCostRelation();
	}

	@Override
	public void update(Observable o, Object arg)
	{

	}
}
