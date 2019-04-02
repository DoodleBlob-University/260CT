package sportsandleisurevillage.domain;

import sportsandleisurevillage.data.CustomerRepoImpl;
import sportsandleisurevillage.data.BookingRepoImpl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

public class Invoice {

	private int id;
	private Customer customer;
	private ArrayList<Booking> list = new ArrayList<>();
	private boolean paid;
	private boolean requested;
	private Date purchaseDate;
	private Date deadline;

	public int getId() {
		return this.id;
	}

	private Date calcDeadline() {//booking date + 30 days
		Calendar cal = Calendar.getInstance();
		cal.setTime(purchaseDate);
		cal.add(Calendar.DATE, 30);//add 30 days
		return cal.getTime();//return new date
	}

	public Date getDeadline() {
		return deadline;
	}

	public int getTotalCost() {
		int cost = 0;
		for (Booking book: list) {
			cost += book.getCost();
		}
		return cost;
	}

	public boolean isPaid() {
		return this.paid;
	}

	public boolean isRequested() {
		return this.requested;
	}

	public ArrayList<Booking> getItems() {
		return this.list;
	}

	private void getCustomerInfo(int customerId){
		CustomerRepoImpl getCustomerInfo = new CustomerRepoImpl(customerId);
		ResultSet result = getCustomerInfo.read();
		try {
			result.first();
			this.customer = new Customer(customerId, result.getString("Name"),
					result.getString("email"));
		}catch(Exception e){
			e.printStackTrace();
		}
		getCustomerInfo.closeconn();
		getCustomerInfo = null;
	}

	private void getBookingInfo(){
		BookingRepoImpl getBookingInfo = new BookingRepoImpl(this.id);
		ResultSet result = getBookingInfo.read();
		try {
			if (result.next()) {//int id, String type, Date date, int cost
				this.list.add(new Booking(result.getInt("ID"),
						result.getString("Type"),
						result.getString("Date"),
						result.getInt("cost")));
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		getBookingInfo.closeconn();
		getBookingInfo = null;
	}

	public Invoice(int id, boolean paid, boolean requested, int customerId, String purchaseDate) {
		this.id = id;
		this.paid = paid;
		try{
			this.purchaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(purchaseDate);
			this.deadline = this.calcDeadline();
		}catch(Exception e){e.printStackTrace();}
		this.requested = requested;

		//query customer info + create instance + assign values
		getCustomerInfo(customerId);

		//query all bookings related to invoice + create list of instances + assign values
		getBookingInfo();
	}

	public void setPaid(boolean value) {
		this.paid = value;
	}

}