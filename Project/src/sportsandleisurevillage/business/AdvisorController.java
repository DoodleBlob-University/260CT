package sportsandleisurevillage.business;

import sportsandleisurevillage.data.InvoiceRepoImpl;

import javafx.scene.control.Alert;
import sportsandleisurevillage.domain.Booking;
import sportsandleisurevillage.domain.Customer;
import sportsandleisurevillage.domain.Invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdvisorController {

	private ArrayList<Invoice> list = new ArrayList<>();

	public void markInvoiceAsPaid(int selected) {
		// TODO
	}

	public void requestInvoice(int selected) {
		// TODO
	}

	public void deleteInvoice(int selected) {
		if(selected != 0){//TODO <-- temporary
			// TODO - implement AdvisorController.deleteInvoice
		}else{
			// TODO - no invoice selected
		}
	}

	public ArrayList<Invoice> getTableValues() {



		return this.list;
	}

	public AdvisorController() {

		//get invoices and add to list
		InvoiceRepoImpl getAllInvoices = new InvoiceRepoImpl();
		ResultSet result = getAllInvoices.read();
		try {
			if (result.next()) {//int id, boolean paid, boolean requested
				this.list.add(new Invoice(result.getInt("ID"),
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
		getAllInvoices = null;
	}

}