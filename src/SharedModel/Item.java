package SharedModel;

import java.io.Serializable;
import java.sql.Date;

public class Item implements Serializable
{
	private String serial_id;
	private String category;
	private String owner;
	private String brand;
	private String model;
	private double price;
	private int qty;
	private Date orderDate;
	private Date arrivalDate;
	private String seller;
	private String notes;
	private String sn_notes;

	// ctor used for reading from DB
	public Item(String serial_id, String category, String owner, String brand, String model, double price, int qty,
				Date orderDate, Date arrivalDate, String seller, String notes, String sn_notes)
	{
		this.serial_id = serial_id;
		this.category = category;
		this.owner = owner;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.qty = qty;
		this.orderDate = orderDate;
		this.arrivalDate = arrivalDate;
		this.seller = seller;
		this.notes = notes;
		this.sn_notes = sn_notes;
	}

	// ctor used for writing to DB
	public Item(String category, String owner, String brand, String model, double price, int qty,
				Date orderDate, Date arrivalDate, String seller, String notes, String sn_notes)
	{
		this.category = category;
		this.owner = owner;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.qty = qty;
		this.orderDate = orderDate;
		this.arrivalDate = arrivalDate;
		this.seller = seller;
		this.notes = notes;
		this.sn_notes = sn_notes;
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

	public String getBrand()
	{
		return brand;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getQty()
	{
		return qty;
	}

	public void setQty(int qty)
	{
		this.qty = qty;
	}

	public Date getOrderDate()
	{
		return orderDate;
	}

	public void setOrderDate(Date orderDate)
	{
		this.orderDate = orderDate;
	}

	public Date getArrivalDate()
	{
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate)
	{
		this.arrivalDate = arrivalDate;
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

	public String getSn_notes()
	{
		return sn_notes;
	}

	public void setSn_notes(String sn_notes)
	{
		this.sn_notes = sn_notes;
	}

	@Override
	public String toString()
	{
		return serial_id + "\t\t" + category + "\t\t" + owner + "\t\t" + brand +
				"\t\t" + model + "\t\t" + price + "\t\t" + qty + "\t\t" + orderDate +
				"\t\t" + arrivalDate + "\t\t" + seller + "\t\t" + notes + "\t\t" + sn_notes;
	}
}
