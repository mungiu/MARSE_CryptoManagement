package Client.View;

import Client.Controller.ClientController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class StartClient
{
	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException
	{
		ClientController clientController = new ClientController();

		LocateRegistry.createRegistry(1099);
		Naming.rebind("clientController", clientController);
		System.out.println("Server started...");


		Scanner keyboard = new Scanner(System.in);

		while (true)
		{
			if (keyboard.nextLine().equals("cost"))
				clientController.requestCostRelation();
			else
				clientController.requestItemRelation();
		}
	}
}
