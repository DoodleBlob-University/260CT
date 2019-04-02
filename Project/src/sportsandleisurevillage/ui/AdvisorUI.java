package sportsandleisurevillage.ui;

import sportsandleisurevillage.business.AdvisorController;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import sportsandleisurevillage.domain.Invoice;

import javax.swing.text.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class AdvisorUI implements Initializable {

	private AdvisorController control = new AdvisorController();
	private ObservableList<String> tablelist;


	@FXML
	private ChoiceBox<String> searchoptions;

	@FXML
	private TableView table;

	@FXML
	private void requestbutton(ActionEvent e) {
		int selected = 0;// TODO
		control.requestInvoice(selected);
	}

	@FXML
	private void paidbutton(ActionEvent e) {
		int selected = 0;// TODO
		control.markInvoiceAsPaid(selected);
	}

	@FXML
	private void deletebutton(ActionEvent e) {
		int selected = 0;// TODO
		control.deleteInvoice(selected);
	}

	@FXML
	private void searchFilterAction(ActionEvent e) {
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

		//tablelist = FXCollections.observableArrayList(control.getTableValues());
		//table.setItems(tablelist);
	}

}