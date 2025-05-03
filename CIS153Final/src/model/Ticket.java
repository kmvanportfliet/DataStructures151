/**
*Kevin Van Portfliet kmvanportfliet@dmacc.edu
*CIS153; online
*Due: 05/04/2025 
*PROJECT:Final
*DESCRIPTION: TicketBooking app
*
*/

package model;


/**
 * class for a purchased ticket, vip or gen, dont mattcer
 */
public class Ticket 
{
	
	/**
	 * enum to represent type of ticket
	 */
	public enum TicketType
	{
		VIP, GENERAL
	}
	
	private TicketType type;
	private double price;
	
	
	/**
	 * constructs for ticket w/ object
	 * @param type
	 * @param price
	 */
	public Ticket(TicketType type, double price)
	{
		this.type = type;
		this.price = price;
	}
	
	//gets ticket type
	public TicketType getType()
	{
		return type;
	}
	
	//gets price of ticket
	public double getPrice()
	{
		return price;
	}
	
	
	/**
	 *returns formatted string
	 */
	@Override
	public String toString()
	{
		return type + " Ticket -$" + String.format("%.2f", price);
	}
}
