package Server.Domain.Mediator;

import SharedModel.Cost;
import SharedModel.CostTupleList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton class acting as an adepter between the client-server communication with regards to Cost object types.
 * Converts SQL data into serializable objects to be passed on to the client.
 * Converts client requests into sql statements to be passed on to the database for execution.
 */
public class CostModelManager implements IModelManager<Cost>
{
	private static CostModelManager instance;
	private static Lock lock = new ReentrantLock();

	private IPersistance iPersistanceCost = new CostQuery();

	private CostModelManager() throws SQLException
	{
		// MUST BE EMPTY - Lazy instantiation
	}

	public static CostModelManager getInstance() throws SQLException
	{
		if (instance == null)
			synchronized (lock)
			{
				if (instance == null)
					instance = new CostModelManager();
			}
		return instance;
	}

	@Override
	public ArrayList<Cost> assembleArrayList() throws SQLException
	{
		PreparedStatement stmtPullCostRelation = iPersistanceCost.getPreparedStatement();
		ResultSet rsCost = stmtPullCostRelation.executeQuery();

		CostTupleList temp_costTupleList = CostTupleList.getInstance();
		ArrayList<Cost> temp_arr = temp_costTupleList.getTupleList();

		while (rsCost.next())
		{
			temp_arr.add(
					new Cost(
							rsCost.getInt("serial_id"),
							rsCost.getString("category"),
							rsCost.getString("owner"),
							rsCost.getString("description"),
							rsCost.getDouble("ordervalue"),
							rsCost.getDouble("reimbursed"),
							rsCost.getDate("paymentdate"),
							rsCost.getString("status"),
							rsCost.getString("notes")
					)
			);
		}
		// this also closes the ResultSet
		stmtPullCostRelation.close();

		return temp_arr;
	}

	@Override
	public void assembleSQLInsertCommand(Cost object) throws SQLException
	{
		StringBuilder sb = new StringBuilder();

		// SQL INSERT COMMAND COMPONENTS: insert into "table_name" (col1, col2) values (val1, val2)
		sb.append("insert into cost ");
		sb.append("(serial_id, category, owner, description,  ordervalue, reimbursed, paymentdate, status, notes) ");

		sb.append("values (");
		sb.append(object.getSerial_id() + ",");
		sb.append(object.getCategory() + ",");
		sb.append(object.getOwner() + ",");
		sb.append(object.getDescription() + ",");
		sb.append(object.getOrdervalue() + ",");
		sb.append(object.getReimbursed() + ",");
		sb.append(object.getPaymentdate().toString() + ",");
		sb.append(object.getStatus() + ",");
		sb.append(object.getNotes() + ")");

		iPersistanceCost.pushInsertCommand(sb.toString());
	}

	@Override
	public void assembleSQLUpdateCommand(Cost object) throws SQLException
	{
		StringBuilder sb = new StringBuilder();

		// sql UPDATE command components: update "table_name" set "col1 = val1, col2 = val2" where "condition";
		sb.append("update cost set ");

		sb.append("serial_id = " + object.getSerial_id() + ",");
		sb.append("category = " + object.getCategory() + ",");
		sb.append("owner = " + object.getOwner() + ",");
		sb.append("description = " + object.getDescription() + ",");
		sb.append("ordervalue= " + object.getOrdervalue() + ",");
		sb.append("reimbursed = " + object.getReimbursed() + ",");
		sb.append("paymentdate = " + object.getPaymentdate().toString() + ",");
		sb.append("status = " + object.getStatus() + ",");
		sb.append("notes = " + object.getNotes() + ",");

		iPersistanceCost.pushUpdateCommand(sb.toString());
	}
}
