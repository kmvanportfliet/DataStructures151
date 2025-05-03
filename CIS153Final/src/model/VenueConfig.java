

package model;


/**
 * venueconfig class settings for samll venue
 */
public class VenueConfig 
{
	private int totalSeats;
	private int vipSeats;
	private double vipPrice;
	private double generalPrice;
	
	
	/**
	 * construct for venueconfigue
	 * @param totalSeats
	 * @param vipSeats
	 * @param vipPrice
	 * @param generalPrice
	 */
	public VenueConfig(int totalSeats, int vipSeats, double vipPrice, double generalPrice)
	{
		setTotalSeats(totalSeats);
		setVipSeats(vipSeats);
		this.vipPrice = vipPrice;
		this.generalPrice = generalPrice;
	}
	
	//gets the number of seats configured
	public int getTotalSeats() 
	{
		return totalSeats;
	}
	
	//sets total number of seats
	public void setTotalSeats(int totalSeats)
	{
	    if (totalSeats > 0 && totalSeats <= 50)
	    {
	        this.totalSeats = totalSeats;
	    }
	    else
	    {
	        throw new IllegalArgumentException("Seat purchase must be between 1 and 50!");
	    }
	}

	//returns vip sets
	public int getVipSeats()
	{
		return vipSeats;
	}
	
	//sets the number of vipseats
	public void setVipSeats(int vipSeats)
	{
		if (vipSeats >+ 0 && vipSeats <= totalSeats)
		{
			this.vipSeats = vipSeats;
		}
		else
		{
			throw new IllegalArgumentException("VIP seat purchase must be between 0 and total available number of seats designated.");
		}
	}
	
	//get vip price 
	public double getVipPrice()
	{
		return vipPrice;
	}
	//set vip price
	public void setVipPrice(double vipPrice)
	{
		this.vipPrice = vipPrice;
	}
	//get price of general seat
	public double getGeneralPrice()
	{
		return generalPrice;
	}
	
	//set price of general seat
	public void setGeneralPrice(double generalPrice)
	{
		this.generalPrice = generalPrice;
	}
	
	/**
	 * calcs rutrns numb of general admmission seats
	 * @return
	 */
	public int getGeneralAdmissionSeats()
	{
		return totalSeats - vipSeats;
	}
	
	
	/**
	 *string return of venue config
	 */
	@Override
	public String toString()
	{
		return String.format("Total Seats: %d, VIP Seats: %d ($%.2f), General Seats: %d ($%.2f)",
                totalSeats, vipSeats, vipPrice, getGeneralAdmissionSeats(), generalPrice);
	}
}
