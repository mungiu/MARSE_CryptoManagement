package SharedModel;

import java.io.Serializable;
import java.sql.Date;

public class Cost implements Serializable
{
	private double amount;
	private Date incuredDate;
	private Person payee;

	public Cost(double amount, Date incuredDate, Person payee)
	{
		this.amount = amount;
		this.payee = payee;
		this.incuredDate = incuredDate;
	}

	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	public Date getIncuredDate()
	{
		return incuredDate;
	}

	public void setIncuredDate(Date incuredDate)
	{
		this.incuredDate = incuredDate;
	}

	public Person getPayee()
	{
		return payee;
	}

	public void setPayee(Person payee)
	{
		this.payee = payee;
	}


}
