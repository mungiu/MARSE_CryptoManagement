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
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class StartClient
{
	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, SQLException
	{
		IClientController iClientController = new ClientController();
		Cost testCost;
		Item testItem;
		Owner testOwner;

		Naming.rebind("clientController", iClientController);
		System.out.println("Client started..." +
				"\n" +
				"\nList of Commands:" +
				"\n\"help\" - list all commands" +
				"\n\"costs\" - list all costs" +
				"\n\"items\" - list all items" +
				"\n\"owners\" - list all owners" +
				"\n\"insert cost\" - insert fake hard coded cost" +
				"\n\"insert item\" - insert fake hard coded item" +
				"\n\"insert owner\" - insert fake hard coded owner" +
				"\n\"update cost\" - update a cost with hard coded data" +
				"\n\"update item\" - update an item with hard coded data" +
				"\n\"update owner\" - update an owner with hard coded data");

		Scanner keyboard = new Scanner(System.in);

		while (true)
		{
			String temp = keyboard.nextLine();

			switch (temp)
			{
				case "help":
					System.out.println("\nList of Commands:" +
							"\n\"help\" - list all commands" +
							"\n\"costs\" - list all costs" +
							"\n\"items\" - list all items" +
							"\n\"owners\" - list all owners" +
							"\n\"insert cost\" - insert fake hard coded cost" +
							"\n\"insert item\" - insert fake hard coded item" +
							"\n\"insert owner\" - insert fake hard coded owner" +
							"\n\"update cost\" - update a cost with hard coded data" +
							"\n\"update item\" - update an item with hard coded data" +
							"\n\"update owner\" - update an owner with hard coded data");
					break;
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
				case "insert cost":
					// faking the build of a new Cost Object
					testCost = new Cost("PROJECT", "testOwner", "testDescrip",
							666, 666, new Date(2018, 8, 18),
							"testStatus", "testNotes");
					iClientController.insertCostObject(testCost);
					break;
				case "insert item":
					testItem = new Item("GPU", "testOwner", "testBrand",
							"testModel", 666, 6, new Date(2018, 8, 18),
							new Date(2018, 8, 18), "testSeller", "testNotes",
							"sn_Notes");
					iClientController.insertItemObject(testItem);
					break;
				case "insert owner":
					testOwner = new Owner("Test", "TestEmail",
							"testBtcWalletAddress23543645^&%^$@#$^%&^");
					iClientController.insertOwnerObject(testOwner);
					break;
				case "update cost":
					// faking the build of a new Cost Object
					testCost = new Cost("1", "PROJECT", "testOwnerUP", "testDescripUP",
							999, 999, new Date(2018, 8, 18),
							"testStatusUP", "testNotesUP");
					iClientController.updateCostTuple(testCost);
					break;
				case "update item":
					testItem = new Item("1", "GPU", "testOwnerUP", "testBrandUP",
							"testModelUP", 666, 6, new Date(2018, 8, 18),
							new Date(2018, 8, 18), "testSellerUP", "testNotesUP",
							"sn_NotesUP");
					iClientController.updateItemTuple(testItem);
					break;
				case "update owner":
					testOwner = new Owner("Test", "TestEmailUP",
							"testBtcWalletAddress23543645^&%^$@#$^%&^UP");
					iClientController.updateOwnerTuple(testOwner);
					break;
			}
		}
	}
}
