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
 * class for person who has booked ticket for venue
 * 
 */
public class Guest 
{
	private String name;
	//assigned to each gues
	int bookingNumber;
	
	
	/**
	 * construt for new guest
	 * @param name
	 * @param bookingNumber
	 */
	public Guest(String name, int bookingNumber)
	{
		this.name = name;
		this.bookingNumber = bookingNumber;
	}
	
	//gets the name of guest
	public String getName()
	{
		return name;
	}
	
	//gets the booking number
	public int getBookingNumber()
	{
		return bookingNumber;
	}
	
	
	/**
	 *string returned for guest w/booking number
	 */
	@Override
	public String toString()
	{
		return "Guest Number: " + bookingNumber + ", Guest: " + name;
	}
}
