package Client.View;

import Client.Controller.ClientController;
import SharedInterfaces.IClientController;
import SharedModel.Cost;
import SharedModel.Item;
import SharedModel.Owner;

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
			String temp = keyboard.nextLine();

			switch (temp)
			{
				case "costs":
					for (Cost c : iClientController.requestCostRelation())
						System.out.println(c.toString());
					break;
				case "items":
					for (Item i : iClientController.requestItemRelation())
						System.out.println(i.toString());
					break;
				case "owners":
					for (Owner o : iClientController.requestOwnerRelation())
						System.out.println(o.toString());
					break;
			}
		}
	}
}
