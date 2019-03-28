package sportsandleisurevillage.domain;

import java.util.ArrayList;

public class Invoice {

	private int id;
	private Customer customer;
	private ArrayList<Booking> list;
	private boolean paid;

	public int getId() {
		return this.id;
	}

	public int getDeadline() {
		// TODO - implement Invoice.getDeadline
		throw new UnsupportedOperationException();
	}

	public int getTotalCost() {
		// TODO - implement Invoice.getTotalCost
		throw new UnsupportedOperationException();
	}

	public boolean isPaid() {
		return this.paid;
	}

	public ArrayList<Booking> getItems() {
		// TODO - implement Invoice.getItems
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customer
	 * @param list
	 */
	public Invoice(Customer customer, ArrayList<Booking> list) {
		// TODO - implement Invoice.Invoice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param value
	 */
	public void setPaid(boolean value) {
		this.paid = value;
	}

	public void deleteInvoice() {
		// TODO - implement Invoice.deleteInvoice
		throw new UnsupportedOperationException();
	}

}