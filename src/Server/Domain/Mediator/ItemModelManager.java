package Server.Domain.Mediator;

import SharedModel.Item;
import SharedModel.ItemTupleList;
import SharedModel.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton
 */
public class ItemModelManager implements IModelManager<Item>
{
	private static ItemModelManager instance;
	private static Lock lock = new ReentrantLock();

	private IItemPersistance itemPersistance;
	private Connection connection = null;
	private ItemQuerry itemQuerry = new ItemQuerry(connection);

	private ItemModelManager()
	{
		// MUST BE EMPTY - Lazy instantiation
	}

	public static ItemModelManager getInstance()
	{
		if (instance == null)
			synchronized (lock)
			{
				if (instance == null)
					instance = new ItemModelManager();
			}
		return instance;
	}

	@Override
	public ArrayList<Item> assembleRelation() throws SQLException
	{
		// TODO put info into Model
		// TODO sout(the String)
		// TODO split the tuple strings received from the DB
		ResultSet rsItemTable = itemQuerry.runCase();

		ItemTupleList temp_itemTupleList = ItemTupleList.getInstance();
		ArrayList<Item> temp_arr = temp_itemTupleList.getTupleList();

		while (rsItemTable.next())
		{
			// TODO: Double check statement
			// TODO: In here you should set the values of each variable in each tuple from the tupleList
			temp_arr.add(
					new Item(
							rsItemTable.getString("type"),
							rsItemTable.getString("brand"),
							rsItemTable.getString("model"),
							rsItemTable.getDouble("price"),
							rsItemTable.getString("seller"),
							rsItemTable.getString("notes"),
							rsItemTable.getDate("purchaseDate"),
							rsItemTable.getDate("arrivalDate"),
							new Person(
									rsItemTable.getString("name"),
									rsItemTable.getString("coinbaseEmail"),
									rsItemTable.getString("btcWalletAddress"))
					)
			);
		}

		return temp_arr;
	}
}
