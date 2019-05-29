package it.polito.tdp.bar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class BarController {
	
	private Simulatore sim;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;

    @FXML
    void doSimula(ActionEvent event) {
    	sim = new Simulatore();
    	sim.init();
    	sim.run();
    	
    	txtResult.setText("Numero Clienti: "+sim.getNumClienti()+"\n");
    	txtResult.appendText("Numero Clienti Soddisfatti: "+sim.getNumSoddisfatti()+"\n");
    	txtResult.appendText("Numero Clienti Insoddisfatti: "+sim.getNumInsoddisfatti());
    }

    @FXML
    void initialize() {
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Bar.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Bar.fxml'.";
    }
    
}