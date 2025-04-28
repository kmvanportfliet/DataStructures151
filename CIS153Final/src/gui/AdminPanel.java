

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

public class AdminPanel extends JPanel 
{
    private VenueConfig venueConfig;
    private ReservationManager reservationManager;

    private JTextField totalSeatsField;
    private JTextField vipSeatsField;
    private JTextField vipPriceField;
    private JTextField generalPriceField;
    private JButton applyButton;

    public AdminPanel(VenueConfig venueConfig, ReservationManager reservationManager) 
    {
        this.venueConfig = venueConfig;
        this.reservationManager = reservationManager;

        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel totalSeatsLabel = new JLabel("Total Seats (max 50):");
        totalSeatsField = new JTextField(String.valueOf(venueConfig.getTotalSeats()));

        JLabel vipSeatsLabel = new JLabel("VIP Seats:");
        vipSeatsField = new JTextField(String.valueOf(venueConfig.getVipSeats()));

        JLabel vipPriceLabel = new JLabel("VIP Ticket Price:");
        vipPriceField = new JTextField(String.format("%.2f", venueConfig.getVipPrice()));

        JLabel generalPriceLabel = new JLabel("General Ticket Price:");
        generalPriceField = new JTextField(String.format("%.2f", venueConfig.getGeneralPrice()));

        applyButton = new JButton("Apply Settings");
        applyButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                applySettings();
            }
        });

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
    }

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
}