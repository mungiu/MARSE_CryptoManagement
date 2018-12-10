package SharedInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer<T> extends Remote
{
	void notify(Observable obs, T arg) throws RemoteException;
}


