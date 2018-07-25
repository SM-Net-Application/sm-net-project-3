package com.sm.net.mo;

import java.io.IOException;

import com.sm.net.easy.h2.db.EasyH2Database;
import com.sm.net.mo.db.Database;
import com.sm.net.mo.view.MainViewEventHandler;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

	private EasyH2Database database;

	/**
	 * Method main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Mehtod start - JavaFX
	 */
	@Override
	public void start(Stage primaryStage) {
		this.database = Database.createDatabase();
		loadMainView(primaryStage);
	}

	/**
	 * Load the Main view
	 * 
	 * @param primaryStage
	 */
	private void loadMainView(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Main.class.getResource("view/MainView.fxml"));
			AnchorPane anchorPane;
			anchorPane = (AnchorPane) fxmlLoader.load();

			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);

			primaryStage.setTitle(AppInfos.getAppName());
			primaryStage.getIcons().add(new Image(References.getAppIconUrl()));

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					database.disposeConnectionPool();
					System.exit(0);
				}
			});

			MainViewEventHandler controller = (MainViewEventHandler) fxmlLoader.getController();
			controller.setDatabase(this.database);
			controller.init();

			primaryStage.show();

		} catch (IOException e) {
		}
	}

}
