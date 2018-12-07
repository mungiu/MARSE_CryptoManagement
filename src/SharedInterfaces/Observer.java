package SharedInterfaces;

import java.io.IOException;
import java.rmi.Remote;

public interface Observer<T> extends Remote
{
	void notify(Observable obs, T arg) throws IOException;
}


