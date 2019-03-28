package sportsandleisurevillage.domain;

import java.util.Date;

public class Booking {

	private int id;
	private String type;
	private Date date;
	private int cost;
	private Date purchaseTime;
	private int customerId;

	public int getId() {
		return this.id;
	}

	public String getType() {
		return this.type;
	}

	public Date getDate() {
		return this.date;
	}

	public int getCost() {
		return this.cost;
	}

	public Date getPurchaseTime() {
		return this.purchaseTime;
	}

	public int getCustomerId() {
		return this.customerId;
	}

	/**
	 * 
	 * @param id
	 * @param type
	 * @param date
	 * @param cost
	 * @param purchaseTime
	 * @param customerId
	 */
	public Booking(int id, String type, Date date, int cost, Date purchaseTime, int customerId) {
		// TODO - implement Booking.Booking
		throw new UnsupportedOperationException();
	}

}