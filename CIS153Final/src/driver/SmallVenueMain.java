

package driver;

import javax.swing.SwingUtilities;
import gui.MainFrame;
import model.VenueConfig;
import model.ReservationManager;

/**
 * smallvenuemain is the entry point, initials the venue config and resrvationmanager and launches gui
 */
public class SmallVenueMain 
{	
	/**
	 * main meth to start app
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//initial creation of venueconfigw/ default values
		VenueConfig venueConfig = new VenueConfig(50, 10, 50.00, 20.00);
		//create reservationManager to handle bookings
		ReservationManager reservationManager = new ReservationManager(venueConfig);
		//gui launcher
		SwingUtilities.invokeLater(new Runnable()
			{

		    @Override
		    public void run() 
		    {
		        MainFrame mainFrame = new MainFrame(venueConfig, reservationManager);
		        mainFrame.setVisible(true);
		    }
		});

	}

}
