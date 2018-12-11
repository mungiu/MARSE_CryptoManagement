package Server.Domain.Mediator;

import SharedModel.Item;
import SharedModel.ItemTupleList;
import SharedModel.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton class acting as an adepter between the client-server communication with regards to Item object types.
 * Converts SQL data into serializable objects to be passed on to the client.
 * Converts client requests into sql statements to be passed on to the database for execution.
 */
public class ItemModelManager implements IModelManager<Item>
{
	private static ItemModelManager instance;
	private static Lock lock = new ReentrantLock();

	private IPersistance iPersistanceItem = new ItemQuerry();

	private ItemModelManager() throws SQLException
	{
		// MUST BE EMPTY - Lazy instantiation
	}

	public static ItemModelManager getInstance() throws SQLException
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
	public ArrayList<Item> assembleArrayList() throws SQLException
	{
		ResultSet rsItemTable = iPersistanceItem.pullResultSet();

		ItemTupleList temp_itemTupleList = ItemTupleList.getInstance();
		ArrayList<Item> temp_arr = temp_itemTupleList.getTupleList();

		while (rsItemTable.next())
		{
			// TODO: Double check statement
			temp_arr.add(
					new Item(
							rsItemTable.getString("serial_id"),
							rsItemTable.getString("category"),
							new Person(
									rsItemTable.getString("name"),
									rsItemTable.getString("coinbaseEmail"),
									rsItemTable.getString("btcWalletAddress")),
							rsItemTable.getString("brand"),
							rsItemTable.getString("model"),
							rsItemTable.getDouble("price"),
							rsItemTable.getInt("qty"),
							rsItemTable.getDate("orderdate"),
							rsItemTable.getDate("arrivaldate"),
							rsItemTable.getString("seller"),
							rsItemTable.getString("notes"),
							rsItemTable.getString("sn_notes")
					)
			);
		}

		return temp_arr;
	}

	@Override
	public void assembleSQLInsertCommand(Item object) throws SQLException
	{
		// TODO ensure proper StringBuilder implementation
		StringBuilder sb = new StringBuilder();

		// TODO correct SQL statement component names + add missing components (check excel)
		// SQL INSERT COMMAND COMPONENTS: insert into "table_name" (col1, col2) values (val1, val2)
		sb.append("insert into item_table ");
		sb.append("( owner, brand, model, type, price, purchaseDate, arrivalDate, seller) ");

		sb.append("values (");
		sb.append(object.getOwner() + ",");
		sb.append(object.getBrand() + ",");
		sb.append(object.getModel() + ",");
		sb.append(object.getType() + ",");
		sb.append(object.getPrice() + ",");
		sb.append(object.getPurchaseDate() + ",");
		sb.append(object.getArrivalDate() + ",");
		sb.append(object.getSeller() + ")");

		String sqlInsertCommand = sb.toString();
		iPersistanceItem.pushInsertCommand(sqlInsertCommand);
	}

	@Override
	public void assembleSQLUpdateCommand(Item object) throws SQLException
	{
		StringBuilder sb = new StringBuilder();

		// TODO correct SQL statement component names + add missing components (check excel)
		// sql UPDATE command components: update "table_name" set "col1 = val1, col2 = val2" where "condition";
		sb.append("update cost_table set ");

		sb.append("owner = " + object.getOwner() + "," +
				"brand = " + object.getBrand() + "," +
				"model = " + object.getModel() + "," +
				"type = " + object.getType() + "," +
				"price = " + object.getPrice() + "," +
				"purchaseDate = " + object.getPurchaseDate() + "," +
				"arrivalDate = " + object.getArrivalDate() + "," +
				"seller = " + object.getSeller() + ")");

		String sqlUpdateCommand = sb.toString();
		iPersistanceItem.pushUpdateCommand(sqlUpdateCommand);
	}
}
