package Client.View;

import Client.Controller.ClientController;
import SharedInterfaces.IClientController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class StartClient
{
	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException
	{
		IClientController iClientController = new ClientController();

		Naming.rebind("clientController", iClientController);
		System.out.println("Client started...");


		Scanner keyboard = new Scanner(System.in);

		while (true)
		{
			if (keyboard.nextLine().equals("costs"))
				iClientController.requestCostRelation();
			else if (keyboard.nextLine().equals("items"))
				iClientController.requestItemRelation();
		}
	}
}
