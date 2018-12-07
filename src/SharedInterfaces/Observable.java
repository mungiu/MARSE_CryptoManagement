package SharedInterfaces;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface Observable<T> extends Remote
{
	List<Observer> observers = new ArrayList<>();

	/**
	 * Ads an observer
	 *
	 * @param obs
	 * @throws RemoteException
	 */
	default void addObserver(Observer<T> obs) throws RemoteException
	{
		observers.add(obs);
	}

	default void deleteObserver(Observer<T> obs) throws RemoteException
	{
		observers.remove(obs);
	}

	default void notifyObservers(T arg) throws IOException
	{
		for (Observer observer : observers)
		{
			observer.notify(this, arg);
		}
	}
}


