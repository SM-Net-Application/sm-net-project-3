package com.sm.net.mo.view;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import com.sm.net.easy.h2.db.EasyH2Database;
import com.sm.net.easy.h2.execute.EasyH2QueryBuilder;
import com.sm.net.fx.AlertDesigner;
import com.sm.net.mo.AppInfos;
import com.sm.net.mo.References;
import com.sm.net.mo.db.Database;
import com.sm.net.mo.enm.FileTypes;
import com.sm.net.util.FilesFolders;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainViewEventHandler {

	private EasyH2Database database;
	private Stage stage;

	@FXML
	private Button buttonOpenFolderTemp;
	@FXML
	private Button buttonCheckFolderTemp;

	public void buttonOpenFolderTempOnClick() {
		FilesFolders.openDirectory(References.DIR_TEMP);
	}

	public void buttonCheckFolderTempOnClick() {

		AlertDesigner alert = new AlertDesigner("Do you want to start the analysis of the temporary folder?",
				"Warning! The previous analysis will be deleted.", stage, AlertType.CONFIRMATION, AppInfos.getAppName(),
				new Image(References.APP_ICON.toURI().toString()));

		if (alert.showAndWait().get() == ButtonType.OK)
			checkTempFolder();

	}

	private void checkTempFolder() {
		truncateTableFileTemp();
		truncateTableFileNotAnalyzable();
		checkDirectory(References.DIR_TEMP);
	}

	private void checkDirectory(File directory) {

		File[] files = FilesFolders.listAllFiles(directory);
		for (File file : files) {
			if (file != null)
				if (file.exists())
					if (file.isDirectory())
						checkDirectory(file);
					else
						checkFile(file);
		}
	}

	private void checkFile(File file) {

		if (file.getName().compareTo("Thumbs.db") != 0) {

			boolean analyzable = false;
			List<Integer> id = insertToDb(file);
			if (id != null)
				if (id.size() > 0) {
					String extension = FilenameUtils.getExtension(file.getAbsolutePath()).toLowerCase();
					checkFileType(id.get(0), file, extension);
					analyzable = true;
				}

			if (!analyzable)
				fileCanNotBeAnalyzed(file);
		}
	}

	private void fileCanNotBeAnalyzed(File file) {
		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_FILENOTANALYZABLE);
		builder.addUpdate("FILEPATH", file.getAbsolutePath());
		database.runOperation(builder.buildInsertQuery());
	}

	private void checkFileType(Integer idFile, File file, String extension) {

		FileTypes ft = checkCategory(file, extension);

		switch (ft) {
		case IMAGES:
			insertImageToDb(idFile, file);
			break;
		case VIDEOS:
			insertVideoToDb(idFile, file);
			break;
		case FILES:
			insertFileToDb(idFile, file);
			break;
		default:
			break;
		}
	}

	private void insertImageToDb(Integer idFile, File file) {
		// TODO Auto-generated method stub

	}

	private void insertVideoToDb(Integer idFile, File file) {
		// TODO Auto-generated method stub

	}

	private void insertFileToDb(Integer idFile, File file) {
		// TODO Auto-generated method stub

	}

	private FileTypes checkCategory(File file, String extension) {

		switch (extension.toLowerCase()) {
		case "jpg":
		case "jpeg":
		case "png":
		case "gif":
		case "bmp":
			return FileTypes.IMAGES;
		case "mkv":
		case "avi":
		case "mov":
		case "wmv":
		case "mp4":
		case "m4v":
		case "mpg":
		case "mpeg":
			return FileTypes.VIDEOS;
		default:
			return FileTypes.FILES;
		}
	}

	private void truncateTableFileTemp() {
		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_FILETEMP);
		database.runOperation(builder.buildTruncateTable());
	}

	private void truncateTableFileNotAnalyzable() {
		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_FILENOTANALYZABLE);
		database.runOperation(builder.buildTruncateTable());
	}

	private List<Integer> insertToDb(File file) {
		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_FILETEMP);
		builder.addUpdate("FILEPATH", file.getAbsolutePath());
		return database.runInsert(builder.buildInsertQuery());
	}

	public void init() {

	}

	public EasyH2Database getDatabase() {
		return database;
	}

	public void setDatabase(EasyH2Database database) {
		this.database = database;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
