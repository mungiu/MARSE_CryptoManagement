package Client.Controller;

import Client.View.ClientController;
import javafx.beans.InvalidationListener;

import java.rmi.Remote;
import java.util.ArrayList;

public interface IServerController extends Remote
{
	ArrayList<InvalidationListener> observers = new ArrayList<>();

	void executeItemRelation();

	void executeCostRelation();

	void addObserver(ClientController clientController);
}
