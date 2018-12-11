package SharedModel;

import java.io.Serializable;
import java.sql.Date;

public class Cost implements Serializable
{
	private int serialid;
	private String category;
	private Person owner;
	private String description;
	private double ordervalue;
	private double reinbursed;
	private Date paymentdate;
	private String status;
	private String notes;

	public Cost(int serialid, String category, Person owner, String description, double ordervalue,
				double reinbursed, Date paymentdate, String status, String notes)
	{
		this.serialid = serialid;
		this.category = category;
		this.owner = owner;
		this.description = description;
		this.ordervalue = ordervalue;
		this.reinbursed = reinbursed;
		this.paymentdate = paymentdate;
		this.status = status;
		this.notes = notes;
	}

	public int getSerialid()
	{
		return serialid;
	}

	public void setSerialid(int serialid)
	{
		this.serialid = serialid;
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

	public double getReinbursed()
	{
		return reinbursed;
	}

	public void setReinbursed(double reinbursed)
	{
		this.reinbursed = reinbursed;
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
