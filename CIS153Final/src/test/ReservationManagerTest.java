
package test;

import static org.junit.jupiter.api.Assertions.*;
import model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



/**
 * tests for reservation manager
 */
class ReservationManagerTest 
{
	
	private ReservationManager manager;
	
	
	/**
	 * sets up reservation manager with venue config before each test
	 */
	@BeforeEach
	public void setUp()
	{
		VenueConfig config = new VenueConfig(50, 10, 100.0, 50.0);
		manager = new ReservationManager(config);
	}

	
	/**
	 * tests for a good test
	 */
	@Test
	public void testBookGuestGood() 
	{
		assertTrue(manager.bookGuest("Kevin"));
	}
	
	
	/**
	 * tests that booking fails once the 50 seat limit hits
	 */
	@Test
	public void testMaxCapacitySeatBooking() 
	{
		for (int i = 0; i < 50; i++)
		{
			assertTrue(manager.bookGuest("Guest" + 1));
		}
		assertFalse(manager.bookGuest("MoreGuests"));
	}
	
	
	/**
	 * tests for guests sorted by alpha correctly
	 */
	@Test
	public void testGuestSortByName() 
	{
		manager.bookGuest("Kevin");
		manager.bookGuest("BillyBob");
		manager.bookGuest("JoeBob");
		
		var sorted = manager.getSortedGuestListByName();
		assertEquals("Kevin", sorted.get(0).getName());
		assertEquals("BillyBob", sorted.get(1).getName());
		assertEquals("JoeBob", sorted.get(2).getName());
	}
	
	
	/**
	 * tests the getremainseats subtracts from bookings
	 */
	@Test
	public void testAvailableSeats() 
	{
		manager.bookGuest("one");
		assertEquals(49, manager.getRemainingSeats());
	}
	
	
	/**
	 * tests that book numbers adds as guests are added
	 */
	@Test
	public void testBookingNumberAdd() 
	{
		manager.bookGuest("1st");
		manager.bookGuest("2nd");
		
		var list = manager.getSortedGuestListByName();
		assertEquals(1, list.get(0).getBookingNumber());
		assertEquals(2, list.get(1).getBookingNumber());
	}

}
