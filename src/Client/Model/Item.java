package Client.Model;

public class Item extends Tuple
{
	private String type;
	private String brand;
	private int price;
	private boolean arrivalStatus;
	private String seller;
	private String notes;

	public Item(String type, String brand, String model, int price, boolean arrivalStatus, String seller, String note)
	{
		super(type, brand, model, price, arrivalStatus, seller, note);
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getBrand()
	{
		return brand;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public boolean isArrivalStatus()
	{
		return arrivalStatus;
	}

	public void setArrivalStatus(boolean arrivalStatus)
	{
		this.arrivalStatus = arrivalStatus;
	}

	public String getSeller()
	{
		return seller;
	}

	public void setSeller(String seller)
	{
		this.seller = seller;
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
