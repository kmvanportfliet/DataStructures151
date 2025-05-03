/**
*Kevin Van Portfliet kmvanportfliet@dmacc.edu
*CIS153; online
*Due: 05/04/2025 
*PROJECT:Final
*DESCRIPTION: TicketBooking app
*
*/


package gui;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import javax.swing.JTabbedPane;
import model.VenueConfig;
import model.ReservationManager;


/**
 * mainframe of window application
 * initialise and display admin and booking
 */
public class MainFrame extends JFrame
{
	private VenueConfig venueConfig;
	private ReservationManager reservationManager;
	private AdminPanel adminPanel;
	private BookingPanel bookingPanel;
	
	
	/**
	 * construct for main app frame w/ venue config w/ reservation manager
	 * @param venueConfig
	 * @param reservationManager
	 */
	public MainFrame(VenueConfig venueConfig, ReservationManager reservationManager)
	{
		this.venueConfig = venueConfig;
		this.reservationManager = reservationManager;
		
		//set window title, size, and close
		setTitle("The Small Venue Ticket Reservation System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setIconImage(null);
		
		BufferedImage dummyImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		setIconImage(dummyImg);
		
		//create admin and booking panels
		adminPanel = new AdminPanel(venueConfig, reservationManager);
		bookingPanel = new BookingPanel(venueConfig, reservationManager);
		
		//tab pane to switch
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Admin", adminPanel);
		tabbedPane.addTab("Booking", bookingPanel);
		
		//add to mainframme
		add(tabbedPane);
	}
	
}
