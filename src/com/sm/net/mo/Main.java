package com.sm.net.mo;

import com.sm.net.mo.db.Database;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		System.out.println("Start");
		Database.createDatabase();
		System.out.println("End");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
