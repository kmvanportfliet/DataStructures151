

package test;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import model.*;

/**
 * unit test for venueconfig class
 * tests valid and invald seat configs
 */
class VenueConfigTest 
{
		private ReservationManager manager;
		
		@BeforeEach
		public void setUp()
		{
			VenueConfig config = new VenueConfig(50, 10, 100.0, 50.0);
			manager = new ReservationManager(config);
		}
	
		/**
		 * tests good seat config
		 */
		@Test
		public void testGoodSeatConfig()
		{
			VenueConfig config = new VenueConfig(50, 10, 100.0, 50.);
			assertEquals(50, config.getTotalSeats());
			assertEquals(10, config.getVipSeats());
		}
		
		
		/**
		 * tests bad seat total
		 */
		@Test
		public void testBadTotalSeats()
		{
			assertThrows(IllegalArgumentException.class, () -> new VenueConfig(0, 5, 100.0, 50.0));
			assertThrows(IllegalArgumentException.class, () -> new VenueConfig(51, 5, 100.0, 50.0));
		}

		
		/**
		 * testst that bad seat vip throws exception
		 */
		@Test
		public void testBadVIPSeatNumb()
		{
			assertThrows(IllegalArgumentException.class, () -> new VenueConfig(50, 51, 100.0, 50.0));
		}
		
		
		/**
		 * tests calcs of gen admission seats cost
		 */
		@Test
		public void testGeneralAdminSeatCalc()
		{
			VenueConfig config = new VenueConfig(50, 15, 100.0, 50.0);
			assertEquals(35, config.getGeneralAdmissionSeats());
		}
		
		//tests for duplicate test name
		@Test
		public void testDuplicateGuestNamesAllowed() 
		{
			assertTrue(manager.bookGuest("KVAN"));
			assertTrue(manager.bookGuest("KVAN"));
			assertEquals(2, manager.getBookingQueue().size());
		}
		
		//tests for empty list
		@Test
		public void testSortListEmpty()
		{
			var sorted = manager.getSortedGuestListByName();
			assertTrue(sorted.isEmpty());
		}
}
