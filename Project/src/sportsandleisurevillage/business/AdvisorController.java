package sportsandleisurevillage.business;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import sportsandleisurevillage.data.InvoiceRepoImpl;

import javafx.scene.control.Alert;
import sportsandleisurevillage.domain.Booking;
import sportsandleisurevillage.domain.Customer;
import sportsandleisurevillage.domain.Invoice;

import javax.swing.table.TableColumn;
import java.lang.reflect.Member;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import javafx.scene.control.cell.TextFieldTableCell;

public class AdvisorController {

	private HashMap<Integer, Invoice> list = new HashMap<>();

	public HashMap<Integer, Invoice> getList(){
		return list;
	}

	public void markInvoiceAsPaid(int selected) {
		boolean paidValue = list.get(selected).isPaid();
		list.get(selected).setPaid(!paidValue);
		createTable();

		InvoiceRepoImpl updateRequested = new InvoiceRepoImpl(selected, "paid", !paidValue);
		updateRequested.update();
		updateRequested.closeconn();
	}

	public void requestInvoice(int selected) {
		boolean requestedValue = list.get(selected).isRequested();
		list.get(selected).setRequest(!requestedValue);
		createTable();

		InvoiceRepoImpl requestedUpdate = new InvoiceRepoImpl(selected, "requested", !requestedValue);
		requestedUpdate.update();
		requestedUpdate.closeconn();

		//TODO actually send customer request
		//String customerEmail = list.get(selected).getCustomer().getEmail();
	}

	public void deleteInvoice(int selected) {
		list.remove(selected);//remove invoice from list
		createTable();

		InvoiceRepoImpl invoiceDelete = new InvoiceRepoImpl(selected);
		invoiceDelete.delete();
		invoiceDelete.closeconn();
	}

	private ArrayList<Invoice> getTableValues() {
		Collection<Invoice> listValues = list.values();
		//System.out.println(listValues);
		return new ArrayList<>(listValues);
	}
	private ObservableList<tableObject> tablelist = FXCollections.observableArrayList();

	private void createTable(){
		tablelist.clear();
		for (Invoice invoice: getTableValues()) {

				tablelist.add(new tableObject(//int invoiceid, int customerid, Date invoicedate, int items, int totalcost, Boolean requested, Boolean paid, Date deadline
						invoice.getId(),
						invoice.getCustomer().getId(),
						invoice.getDate(),
						invoice.getItems().size(),
						invoice.getTotalCost(),
						invoice.isRequested(),
						invoice.isPaid(),
						invoice.getDeadline()
				));

		}
	}

	public ObservableList<tableObject> getTable(){
		return tablelist;
	}

	public AdvisorController() {
		//get invoices and add to list
		InvoiceRepoImpl getAllInvoices = new InvoiceRepoImpl();
		ResultSet result = getAllInvoices.read();
		try {
			while(result.next()) {//int id, boolean paid, boolean requested
				System.out.println(">" + result.getInt("ID"));
				this.list.put(result.getInt("ID"),
						new Invoice(result.getInt("ID"),
							result.getBoolean("paid"),
							result.getBoolean("requested"),
							result.getInt("CustomerID"),
							result.getString("purchaseDate")
				));

			}

		}catch(Exception e){
			e.printStackTrace();
		}
		getAllInvoices.closeconn();
		//System.out.println(list.size());
		createTable();
	}

}