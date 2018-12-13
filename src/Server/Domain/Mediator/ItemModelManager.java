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

	private IPersistance iPersistanceItem = new ItemQuery();

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
		// Freeing up resources
		rsItemTable.close();

		return temp_arr;
	}

	@Override
	public void assembleSQLInsertCommand(Item object) throws SQLException
	{
		StringBuilder sb = new StringBuilder();

		// SQL INSERT COMMAND COMPONENTS: insert into "table_name" (col1, col2) values (val1, val2)
		sb.append("insert into item ");
		sb.append("serial_id, category, owner, brand, model, price, qty, orderDate, arrivalDate, seller, notes, sn_notes) ");

		sb.append("values (");
		sb.append(object.getSerial_id() + ",");
		sb.append(object.getCategory() + ",");
		sb.append(object.getOwner() + ",");
		sb.append(object.getBrand() + ",");
		sb.append(object.getModel() + ",");
		sb.append(object.getPrice() + ",");
		sb.append(object.getQty() + ",");
		sb.append(object.getOrderDate() + ",");
		sb.append(object.getArrivalDate() + ",");
		sb.append(object.getSeller() + ",");
		sb.append(object.getNotes() + ",");
		sb.append(object.getSn_notes() + ")");

		String sqlInsertCommand = sb.toString();
		iPersistanceItem.pushInsertCommand(sqlInsertCommand);
	}

	@Override
	public void assembleSQLUpdateCommand(Item object) throws SQLException
	{
		StringBuilder sb = new StringBuilder();

		// sql UPDATE command components: update "table_name" set "col1 = val1, col2 = val2" where "condition";
		sb.append("update item set ");

		sb.append("serial_id = " + object.getSerial_id() + ",");
		sb.append("category = " + object.getCategory() + ",");
		sb.append("owner = " + object.getOwner() + ",");
		sb.append("brand = " + object.getBrand() + ",");
		sb.append("model = " + object.getModel() + ",");
		sb.append("price = " + object.getPrice() + ",");
		sb.append("qty = " + object.getQty() + ",");
		sb.append("orderdate = " + object.getOrderDate() + ",");
		sb.append("arrivaldate = " + object.getArrivalDate() + ",");
		sb.append("seller = " + object.getSeller() + ",");
		sb.append("notes = " + object.getNotes() + ",");
		sb.append("sn_notes = " + object.getSn_notes() + ")");

		String sqlUpdateCommand = sb.toString();
		iPersistanceItem.pushUpdateCommand(sqlUpdateCommand);
	}
}
