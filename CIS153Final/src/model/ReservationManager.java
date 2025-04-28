

package model;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;


public class ReservationManager 
{
	private VenueConfig config;
	private Queue<Guest> bookingQueue;
	private int nextBookingNumber;
	
	public ReservationManager(VenueConfig config)
	{
		this.config = config;
		this.bookingQueue = new LinkedList<>();
		this.nextBookingNumber = 1;
	}
	
	public boolean bookGuest(String guestName)
	{
		if (bookingQueue.size() >= config.getTotalSeats())
		{
			return false;
		}
		Guest newGuest = new Guest(guestName, nextBookingNumber++);
		bookingQueue.offer(newGuest);
		return true;
	}
	
	public List<Guest> getSortedGuestListByName()
	{
		List<Guest> sortedList = new ArrayList<>(bookingQueue);
		sortGuestsByName(sortedList);
		return sortedList;
	}
	
	private void sortGuestsByName(List<Guest> guests)
	{
		for (int i = 1; i < guests.size(); i++)
		{
			Guest key = guests.get(i);
			int indexElement = i - 1;
			while (indexElement >= 0 && guests.get(indexElement).getName().compareToIgnoreCase(key.getName()) > 0)
			{
				guests.set(indexElement + 1, guests.get(indexElement));
				indexElement--;
			}
			guests.set(indexElement + 1, key);
		}
	}
	
	public int getRemainingSeats() 
	{
		return config.getTotalSeats() - bookingQueue.size();
	}
	
	public Queue<Guest> getBookingQueue()
	{
		return bookingQueue;
	}
	
	public VenueConfig getVenueConfig()
	{
		return config;
	}
}
