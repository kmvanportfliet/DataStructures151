

package model;


public class Ticket 
{
	public enum TicketType
	{
		VIP, GENERAL
	}
	
	private TicketType type;
	private double price;
	
	public Ticket(TicketType type, double price)
	{
		this.type = type;
		this.price = price;
	}
	
	public TicketType getType()
	{
		return type;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	@Override
	public String toString()
	{
		return type + " Ticket -$" + String.format("%.2f", price);
	}
}
