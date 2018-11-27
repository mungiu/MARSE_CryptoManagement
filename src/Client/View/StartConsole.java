package Client.View;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StartConsole
{
	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException
	{
		ClientController c = new ClientController();
	}
}
