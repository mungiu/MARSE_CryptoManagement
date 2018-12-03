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
}
