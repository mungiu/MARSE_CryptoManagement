package SharedModel;

import java.io.Serializable;
import java.sql.Date;

public class Cost implements Serializable
{
	private String serial_id;
	private String category;
	private String owner;
	private String description;
	private double ordervalue;
	private double reimbursed;
	private Date paymentdate;
	private String status;
	private String notes;

	// ctor used for reading from DB
	public Cost(String serial_id, String category, String owner, String description, double ordervalue,
				double reimbursed, Date paymentdate, String status, String notes)
	{
		this.serial_id = serial_id;
		this.category = category;
		this.owner = owner;
		this.description = description;
		this.ordervalue = ordervalue;
		this.reimbursed = reimbursed;
		this.paymentdate = paymentdate;
		this.status = status;
		this.notes = notes;
	}

	// ctor used for writing to DB
	public Cost(String category, String owner, String description, double ordervalue,
				double reimbursed, Date paymentdate, String status, String notes)
	{
		this.category = category;
		this.owner = owner;
		this.description = description;
		this.ordervalue = ordervalue;
		this.reimbursed = reimbursed;
		this.paymentdate = paymentdate;
		this.status = status;
		this.notes = notes;
	}

	public String getSerial_id()
	{
		return serial_id;
	}

	public void setSerial_id(String serial_id)
	{
		this.serial_id = serial_id;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getOwner()
	{
		return owner;
	}

	public void setOwner(String owner)
	{
		this.owner = owner;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public double getOrdervalue()
	{
		return ordervalue;
	}

	public void setOrdervalue(double ordervalue)
	{
		this.ordervalue = ordervalue;
	}

	public double getReimbursed()
	{
		return reimbursed;
	}

	public void setReimbursed(double reimbursed)
	{
		this.reimbursed = reimbursed;
	}

	public Date getPaymentdate()
	{
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate)
	{
		this.paymentdate = paymentdate;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	@Override
	public String toString()
	{
		return serial_id + "\t\t" + category + "\t\t" + owner + "\t\t" + description +
				"\t\t" + ordervalue + "\t\t" + reimbursed + "\t\t" + paymentdate +
				"\t\t" + status + "\t\t" + notes;
	}
}
