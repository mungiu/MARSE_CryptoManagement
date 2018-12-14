package Server.Domain.Mediator;

import SharedModel.Item;
import SharedModel.ItemTupleList;

import java.sql.PreparedStatement;
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
		PreparedStatement stmtPullItemRelation = iPersistanceItem.getPreparedStatement();
		ResultSet rsItem = stmtPullItemRelation.executeQuery();

		ItemTupleList temp_itemTupleList = ItemTupleList.getInstance();
		ArrayList<Item> temp_arr = temp_itemTupleList.getTupleList();

		while (rsItem.next())
		{
			temp_arr.add(
					new Item(
							rsItem.getString("serial_id"),
							rsItem.getString("category"),
							rsItem.getString("owner"),
							rsItem.getString("brand"),
							rsItem.getString("model"),
							rsItem.getDouble("price"),
							rsItem.getInt("qty"),
							rsItem.getDate("orderdate"),
							rsItem.getDate("arrivaldate"),
							rsItem.getString("seller"),
							rsItem.getString("notes"),
							rsItem.getString("sn_notes")
					)
			);
		}
		// this also closes the ResultSet
		stmtPullItemRelation.close();

		return temp_arr;
	}

	@Override
	public void assembleSQLInsertCommand(Item object) throws SQLException
	{
		StringBuilder sb = new StringBuilder();

		// SQL INSERT COMMAND COMPONENTS: insert into "table_name" (col1, col2) values (val1, val2)
		sb.append("insert into item ");
		sb.append("(serial_id, category, owner, brand, model, price, qty, orderDate, arrivalDate, seller, notes, sn_notes) ");

		sb.append("values (");
		sb.append(object.getSerial_id() + ",");
		sb.append(object.getCategory() + ",");
		sb.append(object.getOwner() + ",");
		sb.append(object.getBrand() + ",");
		sb.append(object.getModel() + ",");
		sb.append(object.getPrice() + ",");
		sb.append(object.getQty() + ",");
		sb.append(object.getOrderDate().toString() + ",");
		sb.append(object.getArrivalDate().toString() + ",");
		sb.append(object.getSeller() + ",");
		sb.append(object.getNotes() + ",");
		sb.append(object.getSn_notes() + ")");

		iPersistanceItem.pushInsertCommand(sb.toString());
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
		sb.append("orderdate = " + object.getOrderDate().toString() + ",");
		sb.append("arrivaldate = " + object.getArrivalDate().toString() + ",");
		sb.append("seller = " + object.getSeller() + ",");
		sb.append("notes = " + object.getNotes() + ",");
		sb.append("sn_notes = " + object.getSn_notes() + ")");

		iPersistanceItem.pushUpdateCommand(sb.toString());
	}
}
