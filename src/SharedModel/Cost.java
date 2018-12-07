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
}
