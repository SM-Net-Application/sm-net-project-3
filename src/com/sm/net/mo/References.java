package com.sm.net.mo;

import java.io.File;

import com.sm.net.util.FilesFolders;

public class References {

	public static final String PATH_RESOURCES = FilesFolders.addSubfolder(System.getProperty("user.dir"), "resources");

	public static final File DIR_RESOURCES = FilesFolders.createDirectory(PATH_RESOURCES);

	public static final File DIR_IMAGES = FilesFolders.createDirectory(FilesFolders.addSubfolder(PATH_RESOURCES, "images"));

	public static final File DIR_TEMP = FilesFolders.createDirectory(FilesFolders.addSubfolder(PATH_RESOURCES, "temp"));

	public static final File DIR_MEMORIES = FilesFolders.createDirectory(FilesFolders.addSubfolder(PATH_RESOURCES, "memories"));

	public static final File DIR_DB = FilesFolders.createDirectory(FilesFolders.addSubfolder(PATH_RESOURCES, "database"));

	public static final File APP_ICON = new File(DIR_IMAGES.getAbsolutePath() + File.separatorChar + "icon.png");

	public static String getAppIconUrl() {
		return APP_ICON.toURI().toString();
	}
}
