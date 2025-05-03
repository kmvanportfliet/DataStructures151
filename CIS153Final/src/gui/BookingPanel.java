
package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import model.VenueConfig;
import model.ReservationManager;
import model.Ticket;


/**
 * bookingpanel to book tickets in gui
 */
public class BookingPanel extends JPanel 
{
    private VenueConfig venueConfig;
    private ReservationManager reservationManager;

    private JTextField nameField;
    private JComboBox<Ticket.TicketType> ticketTypeBox;
    private JButton bookButton;

    
    /**
     * construc for bookingpanel
     * @param venueConfig
     * @param reservationManager
     */
    public BookingPanel(VenueConfig venueConfig, ReservationManager reservationManager) 
    {
        this.venueConfig = venueConfig;
        this.reservationManager = reservationManager;

        //layout
        setLayout(new GridLayout(4, 2, 10, 10));

        //name input
        JLabel nameLabel = new JLabel("Guest Name:");
        nameField = new JTextField();

        //ticket input
        JLabel ticketTypeLabel = new JLabel("Ticket Type:");
        ticketTypeBox = new JComboBox<>(Ticket.TicketType.values());

        //button for book ticket
        bookButton = new JButton("Book Ticket");
        bookButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                bookTicket();
            }
        });

        //adds commponents
        add(nameLabel);
        add(nameField);
        add(ticketTypeLabel);
        add(ticketTypeBox);
        add(new JLabel());
        add(bookButton);
    }

    
    /**
     * logic, validates input, books guest and displays result
     */
    private void bookTicket() 
    {
        String name = nameField.getText().trim();
        Ticket.TicketType selectedType = (Ticket.TicketType) ticketTypeBox.getSelectedItem();

        //name validated
        if (name.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Please enter a guest name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //checks for seat avail
        if (reservationManager.getRemainingSeats() <= 0) 
        {
            JOptionPane.showMessageDialog(this, "No seats available.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //book guest attempt
        boolean success = reservationManager.bookGuest(name);

        if (success) 
        {
        	//price determination
            double price = selectedType == Ticket.TicketType.VIP ? venueConfig.getVipPrice() : venueConfig.getGeneralPrice();
            JOptionPane.showMessageDialog(this, String.format("Ticket booked for %s. Price: $%.2f", name, price));
            nameField.setText("");
        } 
        else 
        {
        	//for bad booking
            JOptionPane.showMessageDialog(this, "Failed to book ticket.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
