package com.sm.net.mo.db;

import java.io.File;

import com.sm.net.easy.h2.db.EasyH2Column;
import com.sm.net.easy.h2.db.EasyH2Database;
import com.sm.net.easy.h2.db.EasyH2Schema;
import com.sm.net.easy.h2.db.EasyH2Table;
import com.sm.net.easy.h2.util.H2DataTypes;

public class Database {

	public static final File DB_DIRECTORY = getDatabaseDirectory();
	public static final String DB_FILENAME = "database";
	public static final String DB_USERNAME = "admin";
	public static final String DB_PASSWORD = "";

	public static final String SCHEMA = "MEMORGANIZER";
	public static final String TAB_FILETEMP = "FILETEMP";

	public static void createDatabase() {

		EasyH2Database database = new EasyH2Database(DB_DIRECTORY, DB_FILENAME, DB_USERNAME, DB_PASSWORD, true);
		database.createSchema(new EasyH2Schema(SCHEMA, true));
		database.createTable(getTableFileTemp());
	}

	private static EasyH2Table getTableFileTemp() {
		EasyH2Table fileTemp = new EasyH2Table(TAB_FILETEMP, SCHEMA, true);
		fileTemp.addColumn(getColumnsFileTemp());

		return fileTemp;
	}

	private static EasyH2Column[] getColumnsFileTemp() {

		EasyH2Column id = new EasyH2Column("ID", H2DataTypes.INT, true, true);
		EasyH2Column filePath = new EasyH2Column("FILEPATH", H2DataTypes.VARCHAR, true, 260);

		return new EasyH2Column[] { id, filePath };
	}

	private static File getDatabaseDirectory() {

		File folderFile = new File(getDatabaseDirectoryAbsolutePath());
		folderFile.mkdirs();

		return folderFile;
	}

	private static String getDatabaseDirectoryAbsolutePath() {

		String folder = System.getProperty("user.dir");
		folder += File.separatorChar;
		folder += "resources";
		folder += File.separatorChar;
		folder += "database";
		return folder;
	}
}