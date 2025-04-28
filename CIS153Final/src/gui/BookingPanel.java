
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

public class BookingPanel extends JPanel 
{
    private VenueConfig venueConfig;
    private ReservationManager reservationManager;

    private JTextField nameField;
    private JComboBox<Ticket.TicketType> ticketTypeBox;
    private JButton bookButton;

    public BookingPanel(VenueConfig venueConfig, ReservationManager reservationManager) 
    {
        this.venueConfig = venueConfig;
        this.reservationManager = reservationManager;

        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nameLabel = new JLabel("Guest Name:");
        nameField = new JTextField();

        JLabel ticketTypeLabel = new JLabel("Ticket Type:");
        ticketTypeBox = new JComboBox<>(Ticket.TicketType.values());

        bookButton = new JButton("Book Ticket");
        bookButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                bookTicket();
            }
        });

        add(nameLabel);
        add(nameField);
        add(ticketTypeLabel);
        add(ticketTypeBox);
        add(new JLabel());
        add(bookButton);
    }

    private void bookTicket() 
    {
        String name = nameField.getText().trim();
        Ticket.TicketType selectedType = (Ticket.TicketType) ticketTypeBox.getSelectedItem();

        if (name.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Please enter a guest name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (reservationManager.getRemainingSeats() <= 0) 
        {
            JOptionPane.showMessageDialog(this, "No seats available.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = reservationManager.bookGuest(name);

        if (success) 
        {
            double price = selectedType == Ticket.TicketType.VIP ? venueConfig.getVipPrice() : venueConfig.getGeneralPrice();
            JOptionPane.showMessageDialog(this, String.format("Ticket booked for %s. Price: $%.2f", name, price));
            nameField.setText("");
        } 
        else 
        {
            JOptionPane.showMessageDialog(this, "Failed to book ticket.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
