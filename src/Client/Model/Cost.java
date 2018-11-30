package Client.Model;

public class Cost extends Tuple
{
	private double amount;

	public Cost(double amount)
	{
		super(amount);
	}

	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}
}
