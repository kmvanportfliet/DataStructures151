

package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Guest;


/**
 * 
 * unit test for guest class
 */
class GuestTest 
{

	/**
	 * tests whether tostring method of guest returns correct format
	 */
	@Test
	public void testGuestToString() 
	{
		Guest guest = new Guest("KevinBob", 1);
		assertEquals("Guest Number: 1, Guest: KevinBob", guest.toString());
	}
	
	//tests the getters
	@Test
	public void testGuestGets()
	{
		Guest guest = new Guest("KVP", 5);
		assertEquals("KVP", guest.getName());
		assertEquals(5, guest.getBookingNumber());
	}

}
