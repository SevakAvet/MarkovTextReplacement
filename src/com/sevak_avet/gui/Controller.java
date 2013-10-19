package com.sevak_avet.gui;

import static com.sevak_avet.MarkovTextReplacement.getSolution;
import static com.sevak_avet.MarkovTextReplacement.getText;
import static com.sevak_avet.MarkovTextReplacement.listToString;
import static com.sevak_avet.MarkovTextReplacement.runReplacing;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import com.sevak_avet.Rules;

public class Controller implements Initializable {
    @FXML
    private static TextField left1;

    @FXML
    private static TextField left2;

    @FXML
    private static TextField left3;

    @FXML
    private static TextField left4;

    @FXML
    private static TextField left5;

    @FXML
    private static TextField left6;

    @FXML
    private static TextField left7;

    @FXML
    private static TextField left8;

    @FXML
    private static TextField right1;

    @FXML
    private static TextField right2;

    @FXML
    private static TextField right3;

    @FXML
    private static TextField right4;

    @FXML
    private static TextField right5;

    @FXML
    private static TextField right6;

    @FXML
    private static TextField right7;

    @FXML
    private static TextField right8;
    
    @FXML
    private static TextArea priorities;
    
    @FXML
    private static TextArea input;
    
    @FXML
    private static TextArea output;
    
    @FXML
    private static Button click;
    
    private static List<TextField> leftTextFields;
    private static List<TextField> rightTextFields;

	private static void fillTextFields() {
		leftTextFields = Arrays.asList(left1, left2, left3, left4, left5, left6, left7, left8);
		rightTextFields = Arrays.asList(right1, right2, right3, right4, right5, right6, right7, right8);
		
		List<String> leftRules = Rules.getRulesLeft();
		List<String> rightRules = Rules.getRulesRight();
		
		for(int i=0; i<leftTextFields.size(); ++i) {
			leftTextFields.get(i).setText(leftRules.get(i));
		}
		
		for(int i=0; i<rightTextFields.size(); ++i) {
			rightTextFields.get(i).setText(rightRules.get(i));
		}
	}
    
    @Override
	public void initialize(URL url, ResourceBundle rb) {
    	fillTextFields();
    	output.setEditable(false);
    	
    	click.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				String prior = priorities.getText().trim();
				String text = input.getText().trim();
				
				runReplacing(prior, text);
				String result = getText();
				
				String used = listToString(getSolution());
				result += "\n";
				result += used;
				
				output.setText(result);
			}
		});
    	
    	System.out.println("Initializing complete!");
	}
	

}
