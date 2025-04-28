

package model;


public class VenueConfig 
{
	private int totalSeats;
	private int vipSeats;
	private double vipPrice;
	private double generalPrice;
	
	public VenueConfig(int totalSeats, int vipSeats, double vipPrice, double generalPrice)
	{
		setTotalSeats(totalSeats);
		setVipSeats(vipSeats);
		this.vipPrice = vipPrice;
		this.generalPrice = generalPrice;
	}
	
	public int getTotalSeats() 
	{
		return totalSeats;
	}
	
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

	
	public int getVipSeats()
	{
		return vipSeats;
	}
	
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
	
	public double getVipPrice()
	{
		return vipPrice;
	}
	public void setVipPrice(double vipPrice)
	{
		this.vipPrice = vipPrice;
	}
	public double getGeneralPrice()
	{
		return generalPrice;
	}
	public void setGeneralPrice(double generalPrice)
	{
		this.generalPrice = generalPrice;
	}
	public int getGeneralAdmissionSeats()
	{
		return totalSeats - vipSeats;
	}
	
	@Override
	public String toString()
	{
		return String.format("Total Seats: %d, VIP Seats: %d ($%.2f), General Seats: %d ($%.2f)",
                totalSeats, vipSeats, vipPrice, getGeneralAdmissionSeats(), generalPrice);
	}
}
