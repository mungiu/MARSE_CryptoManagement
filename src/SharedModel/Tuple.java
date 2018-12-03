package SharedModel;

public class Tuple
{
	//cost variables
	private double amount;

	//item variables
	private String type;
	private String brand;
	private String model;
	private int price;
	private boolean arrivalStatus;
	private String seller;
	private String note;

	public Tuple(double amount)
	{
		this.amount = amount;
	}

	public Tuple(String type, String brand, String model, int price, boolean arrivalStatus, String seller, String note)
	{
		this.type = type;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.arrivalStatus = arrivalStatus;
		this.seller = seller;
		this.note = note;
	}
}
