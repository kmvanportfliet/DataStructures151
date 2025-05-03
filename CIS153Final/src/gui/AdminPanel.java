/**
*Kevin Van Portfliet kmvanportfliet@dmacc.edu
*CIS153; online
*Due: 05/04/2025 
*PROJECT:Final
*DESCRIPTION: TicketBooking app
*
*/

package gui;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import model.VenueConfig;
import model.ReservationManager;
import model.Guest;


/**
 * admin panel for gui, for admin controls. controls seat count and ticket prices w/ viewing guest list
 * 
 */
public class AdminPanel extends JPanel 
{
    private VenueConfig venueConfig;
    private ReservationManager reservationManager;

    private JTextField totalSeatsField;
    private JTextField vipSeatsField;
    private JTextField vipPriceField;
    private JTextField generalPriceField;
    private JButton applyButton;
    private JButton viewGuestButton;

    
    /**
     * contrsuit for admin panel
     * @param venueConfig
     * @param reservationManager
     */
    public AdminPanel(VenueConfig venueConfig, ReservationManager reservationManager) 
    {
        this.venueConfig = venueConfig;
        this.reservationManager = reservationManager;
        
        //create 5 rows and 2 coloumns
        setLayout(new GridLayout(6, 2, 10, 10));
        
        //input fields and labels
        JLabel totalSeatsLabel = new JLabel("Total Seats (max 50):");
        totalSeatsField = new JTextField(String.valueOf(venueConfig.getTotalSeats()));

        JLabel vipSeatsLabel = new JLabel("VIP Seats:");
        vipSeatsField = new JTextField(String.valueOf(venueConfig.getVipSeats()));

        JLabel vipPriceLabel = new JLabel("VIP Ticket Price:");
        vipPriceField = new JTextField(String.format("%.2f", venueConfig.getVipPrice()));

        JLabel generalPriceLabel = new JLabel("General Ticket Price:");
        generalPriceField = new JTextField(String.format("%.2f", venueConfig.getGeneralPrice()));
        
        //buttons for admin settings
        applyButton = new JButton("Apply Settings");
        applyButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                applySettings();
            }
        });
        
        //guest list button
        viewGuestButton = new JButton("Guest List");
        viewGuestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGuestList();
            }
        });

        //components add
        add(totalSeatsLabel);
        add(totalSeatsField);
        add(vipSeatsLabel);
        add(vipSeatsField);
        add(vipPriceLabel);
        add(vipPriceField);
        add(generalPriceLabel);
        add(generalPriceField);
        add(new JLabel());
        add(applyButton);
        add(viewGuestButton);
    }

    
    /**
     * where the program reads the inputs and apply settings to venue
     * 
     */
    private void applySettings() 
    {
        try 
        {
            int totalSeats = Integer.parseInt(totalSeatsField.getText());
            int vipSeats = Integer.parseInt(vipSeatsField.getText());
            double vipPrice = Double.parseDouble(vipPriceField.getText());
            double generalPrice = Double.parseDouble(generalPriceField.getText());

            venueConfig.setTotalSeats(totalSeats);
            venueConfig.setVipSeats(vipSeats);
            venueConfig.setVipPrice(vipPrice);
            venueConfig.setGeneralPrice(generalPrice);
        }
        catch (NumberFormatException ex) 
        {
            System.out.println("Enter a valid number for seats and number of tickets.");
        } 
        catch (IllegalArgumentException ex) 
        {
            System.out.println("Invalid seat or VIP configuration: " + ex.getMessage());
        }
    }
    
    
    /**
     * display list of guests
     * guests sorted by alpha
     */
    private void showGuestList()
    {
    	java.util.List<Guest> guests = reservationManager.getSortedGuestListByName();
        if (guests.isEmpty()) 
        {
            javax.swing.JOptionPane.showMessageDialog(this, "No guests booked yet.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Guest guest : guests) 
        {
            sb.append("Guest #").append(guest.getBookingNumber())
              .append(": ").append(guest.getName()).append("\n");
        }

        javax.swing.JTextArea textArea = new javax.swing.JTextArea(sb.toString());
        textArea.setEditable(false);
        javax.swing.JOptionPane.showMessageDialog(this, textArea, "Guest List", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
}