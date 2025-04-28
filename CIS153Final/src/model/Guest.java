

package model;


public class Guest 
{
	private String name;
	int bookingNumber;
	
	public Guest(String name, int bookingNumber)
	{
		this.name = name;
		this.bookingNumber = bookingNumber;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getBookingNumber()
	{
		return bookingNumber;
	}
	
	@Override
	public String toString()
	{
		return "Guest Number: " + bookingNumber + ", Guest: " + name;
	}
}
