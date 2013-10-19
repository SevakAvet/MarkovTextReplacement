package com.sevak_avet.gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("markov_fxml.fxml"));
		Scene scene = new Scene(root, 540, 455);
		
		stage.setMinWidth(540);
		stage.setMinHeight(455);
		stage.setTitle("Тестовая замена Маркова v0.1a");
		stage.setScene(scene);
		stage.show();
	}

}
