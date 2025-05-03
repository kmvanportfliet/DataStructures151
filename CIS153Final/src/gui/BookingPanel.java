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
    private JTextField fieldForNumbOfTickets;

    
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
        setLayout(new GridLayout(5, 2, 10, 10));

        //name input
        JLabel nameLabel = new JLabel("Guest Name:");
        nameField = new JTextField();

        //ticket input
        JLabel ticketTypeLabel = new JLabel("Ticket Type:");
        ticketTypeBox = new JComboBox<>(Ticket.TicketType.values());
        
        JLabel quantityOfTickets = new JLabel("# of tickets wanted: ");
        fieldForNumbOfTickets = new JTextField("1");

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
        add(quantityOfTickets);      
        add(fieldForNumbOfTickets);          
        add(new JLabel());           
        add(bookButton);

    }

    
    /**
     * logic, validates input, books guest and displays result
     * prompts user for numb of tickets and validates name of guest
     */
    private void bookTicket() 
    {
        Ticket.TicketType selectedType = (Ticket.TicketType) ticketTypeBox.getSelectedItem();
        double pricePerTicket = selectedType == Ticket.TicketType.VIP ? venueConfig.getVipPrice() : venueConfig.getGeneralPrice();
        
        int quantity;
        //parse quanity input and validate pos inte
        try
        {
        	quantity = Integer.parseInt(fieldForNumbOfTickets.getText());
        	if (quantity <= 0) throw new NumberFormatException();
        }
        catch (NumberFormatException e)
        {
        	JOptionPane.showMessageDialog(this, "enter a valid number of tickets!", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        //checks if there are enough seats available
        if (quantity > reservationManager.getRemainingSeats())
        {
        	JOptionPane.showMessageDialog(this, "Not enough seats available!", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        //prompt for each ticket
        for (int i = 0; i < quantity; i++)
        {
        	String name = JOptionPane.showInputDialog(this, "Name for ticket #" + (i + 1) + ":");
        	if (name == null || name.trim().isEmpty())
        	{
        		JOptionPane.showMessageDialog(this, "Name can't be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        		i--;
        		continue;
        	}
        	reservationManager.bookGuest(name.trim());
        }
        
        //calcs and display cost
        double total = pricePerTicket * quantity;
        JOptionPane.showMessageDialog(this, String.format("%d tickets booked. Total: $%.2f", quantity, total));
        
        //reset for input fields
        nameField.setText("");
        fieldForNumbOfTickets.setText("1");
    }
}
