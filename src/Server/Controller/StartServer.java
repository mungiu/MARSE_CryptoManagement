package Server.Controller;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class StartServer
{
	public static void main(String[] args) throws RemoteException, MalformedURLException
	{
		ServerController serverController = new ServerController();
		LocateRegistry.createRegistry(1099);
		Naming.rebind("serverController", serverController);
		System.out.println("Server started...");
	}
}
