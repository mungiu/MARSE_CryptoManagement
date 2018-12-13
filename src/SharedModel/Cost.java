package SharedModel;

import java.io.Serializable;
import java.sql.Date;

public class Cost implements Serializable
{
	private int serial_id;
	private String category;
	private Person owner;
	private String description;
	private double ordervalue;
	private double reimbursed;
	private Date paymentdate;
	private String status;
	private String notes;

	public Cost(int serial_id, String category, Person owner, String description, double ordervalue,
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

	public int getSerial_id()
	{
		return serial_id;
	}

	public void setSerial_id(int serial_id)
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

	public Person getOwner()
	{
		return owner;
	}

	public void setOwner(Person owner)
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
}
