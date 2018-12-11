package SharedModel;

import java.io.Serializable;
import java.sql.Date;

public class Item implements Serializable
{
	private String model;
	private String type;
	private String brand;
	private String seller;
	private String notes;
	private double price;

	private Date purchaseDate;
	private Date arrivalDate;
	private Person owner;

	public Item(String type, String brand, String model, double price, String seller, String notes, Date purchaseDate, Date arrivalDate, Person owner)
	{
		this.type = type;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.seller = seller;
		this.notes = notes;
		this.purchaseDate = purchaseDate;
		this.arrivalDate = arrivalDate;
		this.owner = owner;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
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

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public Date getPurchaseDate()
	{
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate)
	{
		this.purchaseDate = purchaseDate;
	}

	public Date getArrivalDate()
	{
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate)
	{
		this.arrivalDate = arrivalDate;
	}

	public Person getOwner()
	{
		return owner;
	}

	public void setOwner(Person owner)
	{
		this.owner = owner;
	}
}
