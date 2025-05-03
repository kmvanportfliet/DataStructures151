/**
*Kevin Van Portfliet kmvanportfliet@dmacc.edu
*CIS153; online
*Due: 05/04/2025 
*PROJECT:Final
*DESCRIPTION: TicketBooking app
*
*/

package model;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;



/**
 * class handles guest bookings and manages q of reservations
 */
public class ReservationManager 
{
	private VenueConfig config;
	private Queue<Guest> bookingQueue;
	private int nextBookingNumber;
	
	
	/**
	 * construct reservation manager
	 * @param config
	 */
	public ReservationManager(VenueConfig config)
	{
		this.config = config;
		this.bookingQueue = new LinkedList<>();
		this.nextBookingNumber = 1;
	}
	
	
	/**
	 * booking attempt by guest name
	 * @param guestName
	 * @return
	 */
	public boolean bookGuest(String guestName)
	{
		if (bookingQueue.size() >= config.getTotalSeats())
		{
			//for no seats available
			return false;
		}
		Guest newGuest = new Guest(guestName, nextBookingNumber++);
		bookingQueue.offer(newGuest);
		return true;
	}
	
	/**
	 * guest list sorted alpa
	 * @return
	 */
	public List<Guest> getSortedGuestListByName()
	{
		List<Guest> sortedList = new ArrayList<>(bookingQueue);
		sortGuestsByName(sortedList);
		return sortedList;
	}
	
	
	/**
	 * sorts list by alpha using insertion sort
	 * @param guests
	 */
	private void sortGuestsByName(List<Guest> guests)
	{
		for (int i = 1; i < guests.size(); i++)
		{
			Guest key = guests.get(i);
			int indexElement = i - 1;
			//shifts guest greater than key
			while (indexElement >= 0 && guests.get(indexElement).getName().compareToIgnoreCase(key.getName()) > 0)
			{
				guests.set(indexElement + 1, guests.get(indexElement));
				indexElement--;
			}
			guests.set(indexElement + 1, key);
		}
	}
	
	
	/**
	 * returns numb of remaining seats available
	 * @return
	 */
	public int getRemainingSeats() 
	{
		return config.getTotalSeats() - bookingQueue.size();
	}
	
	
	/**
	 * returns booking q of guests
	 * @return
	 */
	public Queue<Guest> getBookingQueue()
	{
		return bookingQueue;
	}
	
	
	/**
	 * returns venue config 
	 * @return
	 */
	public VenueConfig getVenueConfig()
	{
		return config;
	}
}
