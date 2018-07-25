package com.sm.net.mo;

import java.io.File;

import com.sm.net.mo.util.FileUtil;

public class References {

	public static final String PATH_RESOURCES = FileUtil.addSubfolder(System.getProperty("user.dir"), "resources");

	public static final File DIR_RESOURCES = FileUtil.createDirectory(PATH_RESOURCES);

	public static final File DIR_IMAGES = FileUtil.createDirectory(FileUtil.addSubfolder(PATH_RESOURCES, "images"));

	public static final File DIR_TEMP = FileUtil.createDirectory(FileUtil.addSubfolder(PATH_RESOURCES, "temp"));

	public static final File DIR_MEMORIES = FileUtil.createDirectory(FileUtil.addSubfolder(PATH_RESOURCES, "memories"));

	public static final File DIR_DB = FileUtil.createDirectory(FileUtil.addSubfolder(PATH_RESOURCES, "database"));

	public static final File APP_ICON = new File(DIR_IMAGES.getAbsolutePath() + File.separatorChar + "icon.png");

	public static String getAppIconUrl() {
		return APP_ICON.toURI().toString();
	}
}
