package Server.Controller;

import SharedInterfaces.IServerController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

public class StartServer
{
	public static void main(String[] args) throws RemoteException, MalformedURLException, SQLException
	{
		IServerController iServerController = new ServerController();
		LocateRegistry.createRegistry(1099);
		Naming.rebind("iServerController", iServerController);
		System.out.println("Server started...");
	}
}
