package sportsandleisurevillage.ui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sportsandleisurevillage.business.AdvisorController;
import sportsandleisurevillage.domain.Invoice;
import sportsandleisurevillage.business.tableObject;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.lang.reflect.Member;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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

	//search+filters
	@FXML private TextField searchbox;
	@FXML private ChoiceBox<String> searchoptions;
	@FXML private DatePicker datepickerfrom;
	@FXML private DatePicker datepickerto;
	@FXML private TextField pricepickerfrom;
	@FXML private TextField pricepickerto;
	@FXML private CheckBox requestbox;
	@FXML private CheckBox paidbox;
	@FXML private CheckBox overduebox;



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
		//table.getItems().clear();
		table.setItems(control.getTable()); //refresh table
		reFilter(); //reapply search+filter
	}

	@FXML private void paidbutton(ActionEvent e) {
		int selectedRow = getSelectedRow();
		if(selectedRow != 0) {
			control.markInvoiceAsPaid(selectedRow);
		}
		//table.getItems().clear();
		table.setItems(control.getTable()); //refresh table
		reFilter(); //reapply search+filter
	}

	@FXML private void deletebutton(ActionEvent e) {
		int selectedRow = getSelectedRow();
		if(selectedRow != 0) {
			control.deleteInvoice(getSelectedRow());
		}
		//table.getItems().clear();
		//tablelist = control.getTable();
		table.setItems(control.getTable()); //refresh table
		reFilter(); //reapply search+filter
	}

	@FXML private void reFilter(){
		searchFilterAction(new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.K, false, false, false, false));
	}

	@FXML private void searchFilterAction(KeyEvent e) {
		/*
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("I have a great message for you!");
		alert.showAndWait();
		*/
		// TODO implement search/filter

		FilteredList<tableObject> filter = new FilteredList<tableObject>(control.getTable(), a->true);

		filter.setPredicate((Predicate<? super tableObject>) (tableObject std)->{

			if(searchbox.getText().isEmpty()){

				return true;
			}else{
				switch (searchoptions.getValue()) {
					case "Invoice ID":
						if (Integer.toString(std.getInvoiceid()).equals(searchbox.getText())) {
							return true;
						}
						break;
					case "Customer ID":
						if (Integer.toString(std.getCustomerid()).equals(searchbox.getText())) {
							return true;
						}
						break;
					case "Customer Name":
						if (control.getList().get(std.getInvoiceid()).getCustomer().getName().equals(searchbox.getText())) {
							return true;
						}
						break;
				}
			}


			return false;
		});

		SortedList sort = new SortedList(filter);
		sort.comparatorProperty().bind(table.comparatorProperty());//makes the if clause the comparator
		table.setItems(sort);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		//choice box
		ObservableList<String> options = FXCollections.observableArrayList("Invoice ID","Customer ID", "Customer Name");
		searchoptions.setValue(options.get(0)); //default value
		searchoptions.setItems(options); //adds all values in choiceBox

		searchoptions.getSelectionModel().selectedItemProperty().addListener(//creates listener for a change of value to the choiceBox
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> reFilter()
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