package Server.Controller;

import Server.Domain.Mediator.CostModelManager;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class StartServer
{
	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException
	{
		ServerController serverController = new ServerController(CostModelManager.getInstance());
		LocateRegistry.createRegistry(1099);
		Naming.rebind("serverController", serverController);
		System.out.println("Server started...");
	}
}
