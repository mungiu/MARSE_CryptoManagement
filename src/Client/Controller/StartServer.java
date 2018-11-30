package Client.Controller;

import Server.Domain.Model.CostTupleList;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class StartServer
{
	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException
	{
		ClientController clientController = new ClientController(CostTupleList.getInstance());
		LocateRegistry.createRegistry(1099);
		Naming.rebind("clientController", clientController);
		System.out.println("Server started...");
	}
}
