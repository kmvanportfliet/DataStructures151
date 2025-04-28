
package gui;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import model.VenueConfig;
import model.ReservationManager;


public class MainFrame extends JFrame
{
	private VenueConfig venueConfig;
	private ReservationManager reservationManager;
	private AdminPanel adminPanel;
	private BookingPanel bookingPanel;
	
	public MainFrame(VenueConfig venueConfig, ReservationManager reservationManager)
	{
		this.venueConfig = venueConfig;
		this.reservationManager = reservationManager;
		
		setTitle("The Small Venue Ticket Reservation System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		
		adminPanel = new AdminPanel(venueConfig, reservationManager);
		bookingPanel = new BookingPanel(venueConfig, reservationManager);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Admin", adminPanel);
		tabbedPane.addTab("Booking", bookingPanel);
		
		add(tabbedPane);
	}
	
}
