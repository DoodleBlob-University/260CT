package sportsandleisurevillage.ui;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import sportsandleisurevillage.business.AdvisorController;
import sportsandleisurevillage.domain.Invoice;
import sportsandleisurevillage.business.tableObject;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.lang.reflect.Member;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AdvisorUI implements Initializable {

	private AdvisorController control = new AdvisorController();

	//table
	@FXML private TableView<tableObject> table;
	@FXML private TableColumn<Invoice, Integer> Invoicecol;
	@FXML private TableColumn<Invoice, Integer> Customercol;
	@FXML private TableColumn<Invoice, String> Datecol;
	@FXML private TableColumn<Invoice, Integer> Itemcol;
	@FXML private TableColumn<Invoice, Integer> Costcol;
	@FXML private TableColumn<Invoice, Boolean> Requestcol;
	@FXML private TableColumn<Invoice, Boolean> Paidcol;
	@FXML private TableColumn<Invoice, String> Deadlinecol;

	@FXML private ChoiceBox<String> searchoptions;

	private int getSelectedRow(){
		int selected = 0;
		try{
			selected = table.getSelectionModel().getSelectedItem().getInvoiceid();
		}catch (NullPointerException err){//row not selected
		}
		return selected;
	}

	@FXML private void requestbutton(ActionEvent e) {
		int selectedRow = getSelectedRow();
		if(selectedRow != 0) {
			control.requestInvoice(getSelectedRow());
		}
		table.setItems(control.getTable()); //refresh table
		searchFilterAction(new ActionEvent()); //reapply search+filter
	}

	@FXML private void paidbutton(ActionEvent e) {
		int selectedRow = getSelectedRow();
		if(selectedRow != 0) {
			control.markInvoiceAsPaid(selectedRow);
		}
		table.setItems(control.getTable()); //refresh table
		searchFilterAction(new ActionEvent()); //reapply search+filter
	}

	@FXML private void deletebutton(ActionEvent e) {
		int selectedRow = getSelectedRow();
		if(selectedRow != 0) {
			control.deleteInvoice(getSelectedRow());
		}
		table.setItems(control.getTable()); //refresh table
		searchFilterAction(new ActionEvent()); //reapply search+filter
	}

	@FXML private void searchFilterAction(ActionEvent e) {
		/*
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("I have a great message for you!");
		alert.showAndWait();
		*/
		// TODO implement search/filter


	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		//choice box
		ObservableList<String> options = FXCollections.observableArrayList("Invoice ID","Customer ID", "Customer Name");
		searchoptions.setValue(options.get(0)); //default value
		searchoptions.setItems(options); //adds all values in choiceBox
		searchoptions.getSelectionModel().selectedItemProperty().addListener(//creates listener for a change of value to the choiceBox
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> searchFilterAction(new ActionEvent())
		);

		//table
		Invoicecol.setCellValueFactory(new PropertyValueFactory<>("invoiceid"));
		Customercol.setCellValueFactory(new PropertyValueFactory<>("customerid"));
		Datecol.setCellValueFactory(new PropertyValueFactory<>("invoicedate"));
		Itemcol.setCellValueFactory(new PropertyValueFactory<>("items"));
		Costcol.setCellValueFactory(new PropertyValueFactory<>("totalcost"));
		Requestcol.setCellValueFactory(new PropertyValueFactory<>("requested"));
		Paidcol.setCellValueFactory(new PropertyValueFactory<>("paid"));
		Deadlinecol.setCellValueFactory(new PropertyValueFactory<>("deadline"));

		table.setItems(control.getTable());

	}

}