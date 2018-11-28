package Client.View;

import java.rmi.Remote;
import java.util.Observer;

/**
 * An observer class with remote invocation capabilities.
 */
public interface IClientController extends Observer, Remote
{
	void displayItemRelation();

	void displayCostRelation();
}
