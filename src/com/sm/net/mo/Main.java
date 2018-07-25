package com.sm.net.mo;

import java.io.File;

import com.sm.net.easy.h2.db.EasyH2Database;
import com.sm.net.mo.db.Database;
import com.sm.net.mo.util.FileUtil;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static final String RESOURCES_ABSOLUTE_PATH = FileUtil.addSubfolder(System.getProperty("user.dir"),
			"resources");

	public static final File TEMP_DIRECTORY = FileUtil
			.createDirectory(FileUtil.addSubfolder(RESOURCES_ABSOLUTE_PATH, "TemporaryFiles"));

	public static final File MEMORIES_DIRECTORY = FileUtil
			.createDirectory(FileUtil.addSubfolder(RESOURCES_ABSOLUTE_PATH, "Memories"));

	@Override
	public void start(Stage primaryStage) {
		System.out.println("Start");
		EasyH2Database database = Database.createDatabase();
		database.disposeConnectionPool();
		System.out.println("End");

		System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
