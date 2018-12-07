package SharedInterfaces;

public interface IClientController extends Observer<String>
{
	void update(Observable o, Object arg);
}

