package com.sm.net.mo.view;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import com.sm.net.easy.h2.db.EasyH2Database;
import com.sm.net.easy.h2.db.EasyH2ResultSet;
import com.sm.net.easy.h2.execute.EasyH2QueryBuilder;
import com.sm.net.fx.AlertDesigner;
import com.sm.net.mo.AppInfos;
import com.sm.net.mo.References;
import com.sm.net.mo.db.Database;
import com.sm.net.mo.enm.FileTypes;
import com.sm.net.mo.model.Video;
import com.sm.net.util.FilesFolders;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainViewEventHandler {

	private EasyH2Database database;
	private Stage stage;

	private ObservableList<com.sm.net.mo.model.Foto> listFoto = FXCollections.observableArrayList();
	private ObservableList<com.sm.net.mo.model.Image> listImage = FXCollections.observableArrayList();
	private ObservableList<com.sm.net.mo.model.Video> listVideo = FXCollections.observableArrayList();
	private ObservableList<com.sm.net.mo.model.File> listFile = FXCollections.observableArrayList();

	@FXML
	private Button buttonOpenFolderTemp;
	@FXML
	private Button buttonCheckFolderTemp;
	@FXML
	private TableView<com.sm.net.mo.model.Foto> tableViewFoto;
	@FXML
	private TableColumn<com.sm.net.mo.model.Foto, Integer> tableColumnFotoId;
	@FXML
	private TableColumn<com.sm.net.mo.model.Foto, Integer> tableColumnFotoIdFile;
	@FXML
	private TableColumn<com.sm.net.mo.model.Foto, String> tableColumnFotoFilePath;
	@FXML
	private ImageView imageViewFoto;
	@FXML
	private TableView<com.sm.net.mo.model.Image> tableViewImage;
	@FXML
	private TableColumn<com.sm.net.mo.model.Image, Integer> tableColumnImageId;
	@FXML
	private TableColumn<com.sm.net.mo.model.Image, Integer> tableColumnImageIdFile;
	@FXML
	private TableColumn<com.sm.net.mo.model.Image, String> tableColumnImageFilePath;
	@FXML
	private ImageView imageViewImage;
	@FXML
	private TableView<Video> tableViewVideo;
	@FXML
	private TableColumn<Video, Integer> tableColumnVideoId;
	@FXML
	private TableColumn<Video, Integer> tableColumnVideoIdFile;
	@FXML
	private TableColumn<Video, String> tableColumnVideoFilePath;
	@FXML
	private TableView<com.sm.net.mo.model.File> tableViewFile;
	@FXML
	private TableColumn<com.sm.net.mo.model.File, Integer> tableColumnFileId;
	@FXML
	private TableColumn<com.sm.net.mo.model.File, Integer> tableColumnFileIdFile;
	@FXML
	private TableColumn<com.sm.net.mo.model.File, String> tableColumnFileFilePath;

	@FXML
	private void initialize() {

		tableColumnFotoId.setCellValueFactory(cellValue -> cellValue.getValue().getId().asObject());
		tableColumnFotoIdFile.setCellValueFactory(cellValue -> cellValue.getValue().getIdFile().asObject());
		tableColumnFotoFilePath.setCellValueFactory(cellValue -> cellValue.getValue().getFilePath());

		tableColumnImageId.setCellValueFactory(cellValue -> cellValue.getValue().getId().asObject());
		tableColumnImageIdFile.setCellValueFactory(cellValue -> cellValue.getValue().getIdFile().asObject());
		tableColumnImageFilePath.setCellValueFactory(cellValue -> cellValue.getValue().getFilePath());

		tableColumnVideoId.setCellValueFactory(cellValue -> cellValue.getValue().getId().asObject());
		tableColumnVideoIdFile.setCellValueFactory(cellValue -> cellValue.getValue().getIdFile().asObject());
		tableColumnVideoFilePath.setCellValueFactory(cellValue -> cellValue.getValue().getFilePath());

		tableColumnFileId.setCellValueFactory(cellValue -> cellValue.getValue().getId().asObject());
		tableColumnFileIdFile.setCellValueFactory(cellValue -> cellValue.getValue().getIdFile().asObject());
		tableColumnFileFilePath.setCellValueFactory(cellValue -> cellValue.getValue().getFilePath());
	}

	public void init() {
		loadList();
		setListenerTableViewFoto();
		setListenerTableViewImage();
	}

	private void setListenerTableViewFoto() {

		tableViewFoto.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if (newValue.intValue() > -1)
					imageViewFoto.setImage(
							new Image(new File(tableViewFoto.getSelectionModel().getSelectedItem().getFilePath().get())
									.toURI().toString()));
				else
					imageViewFoto.setImage(null);
			}
		});
	}

	private void setListenerTableViewImage() {

		tableViewImage.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if (newValue.intValue() > -1)
					imageViewImage.setImage(
							new Image(new File(tableViewImage.getSelectionModel().getSelectedItem().getFilePath().get())
									.toURI().toString()));
				else
					imageViewImage.setImage(null);
			}
		});
	}

	private void loadList() {

		listFoto = loadListFoto();
		listImage = loadListImage();
		listVideo = loadListVideo();
		listFile = loadListFile();

		tableViewFoto.setItems(listFoto);
		tableViewImage.setItems(listImage);
		tableViewVideo.setItems(listVideo);
		tableViewFile.setItems(listFile);
	}

	private ObservableList<com.sm.net.mo.model.File> loadListFile() {

		ObservableList<com.sm.net.mo.model.File> list = FXCollections.observableArrayList();

		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_FILE);
		builder.addSelection(Database.TAB_FILE, "ID");
		builder.addSelection(Database.TAB_FILE, "IDFILE");
		builder.addSelection(Database.TAB_FILE, "FILEPATH");
		EasyH2ResultSet easyH2ResultSet = database.runSelection(builder.buildSelection());

		ResultSet resultSet = easyH2ResultSet.getResultSet();

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				int idFile = resultSet.getInt("IDFILE");
				String filePath = resultSet.getString("FILEPATH");

				list.add(new com.sm.net.mo.model.File(id, idFile, filePath));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		easyH2ResultSet.close();

		return list;
	}

	private ObservableList<Video> loadListVideo() {
		ObservableList<Video> list = FXCollections.observableArrayList();

		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_VIDEO);
		builder.addSelection(Database.TAB_VIDEO, "ID");
		builder.addSelection(Database.TAB_VIDEO, "IDFILE");
		builder.addSelection(Database.TAB_VIDEO, "FILEPATH");
		EasyH2ResultSet easyH2ResultSet = database.runSelection(builder.buildSelection());

		ResultSet resultSet = easyH2ResultSet.getResultSet();

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				int idFile = resultSet.getInt("IDFILE");
				String filePath = resultSet.getString("FILEPATH");

				list.add(new Video(id, idFile, filePath));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		easyH2ResultSet.close();

		return list;
	}

	private ObservableList<com.sm.net.mo.model.Image> loadListImage() {

		ObservableList<com.sm.net.mo.model.Image> list = FXCollections.observableArrayList();

		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_IMAGE);
		builder.addSelection(Database.TAB_IMAGE, "ID");
		builder.addSelection(Database.TAB_IMAGE, "IDFILE");
		builder.addSelection(Database.TAB_IMAGE, "FILEPATH");
		EasyH2ResultSet easyH2ResultSet = database.runSelection(builder.buildSelection());

		ResultSet resultSet = easyH2ResultSet.getResultSet();

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				int idFile = resultSet.getInt("IDFILE");
				String filePath = resultSet.getString("FILEPATH");

				list.add(new com.sm.net.mo.model.Image(id, idFile, filePath));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		easyH2ResultSet.close();

		return list;
	}

	private ObservableList<com.sm.net.mo.model.Foto> loadListFoto() {

		ObservableList<com.sm.net.mo.model.Foto> list = FXCollections.observableArrayList();

		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_FOTO);
		builder.addSelection(Database.TAB_FOTO, "ID");
		builder.addSelection(Database.TAB_FOTO, "IDFILE");
		builder.addSelection(Database.TAB_FOTO, "FILEPATH");
		EasyH2ResultSet easyH2ResultSet = database.runSelection(builder.buildSelection());

		ResultSet resultSet = easyH2ResultSet.getResultSet();

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				int idFile = resultSet.getInt("IDFILE");
				String filePath = resultSet.getString("FILEPATH");

				list.add(new com.sm.net.mo.model.Foto(id, idFile, filePath));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		easyH2ResultSet.close();

		return list;
	}

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
		truncateTableImage();
		truncateTableVideo();
		truncateTableFile();
		checkDirectory(References.DIR_TEMP);
		loadList();
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
		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_IMAGE);
		builder.addUpdate("IDFILE", idFile);
		builder.addUpdate("FILEPATH", file.getAbsolutePath());
		database.runOperation(builder.buildInsertQuery());
	}

	private void insertVideoToDb(Integer idFile, File file) {
		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_VIDEO);
		builder.addUpdate("IDFILE", idFile);
		builder.addUpdate("FILEPATH", file.getAbsolutePath());
		database.runOperation(builder.buildInsertQuery());
	}

	private void insertFileToDb(Integer idFile, File file) {
		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_FILE);
		builder.addUpdate("IDFILE", idFile);
		builder.addUpdate("FILEPATH", file.getAbsolutePath());
		database.runOperation(builder.buildInsertQuery());
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

	private void truncateTableImage() {
		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_IMAGE);
		database.runOperation(builder.buildTruncateTable());
	}

	private void truncateTableVideo() {
		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_VIDEO);
		database.runOperation(builder.buildTruncateTable());
	}

	private void truncateTableFile() {
		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_FILE);
		database.runOperation(builder.buildTruncateTable());
	}

	private List<Integer> insertToDb(File file) {
		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_FILETEMP);
		builder.addUpdate("FILEPATH", file.getAbsolutePath());
		return database.runInsert(builder.buildInsertQuery());
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
