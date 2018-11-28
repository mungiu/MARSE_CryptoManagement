package Client.Controller;

import Client.Domain.Model.TupleList;
import Client.View.ClientController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerController implements IServerController
{
	public ServerController(TupleList tupleList) throws RemoteException
	{
		UnicastRemoteObject.exportObject(this, 0);
	}

	@Override
	public void executeItemRelation()
	{

	}

	@Override
	public void executeCostRelation()
	{

	}

	@Override
	public void addObserver(ClientController clientController)
	{

	}
}
