package com.sm.net.mo.db;

import com.sm.net.easy.h2.db.EasyH2Column;
import com.sm.net.easy.h2.db.EasyH2Database;
import com.sm.net.easy.h2.db.EasyH2Schema;
import com.sm.net.easy.h2.db.EasyH2Table;
import com.sm.net.easy.h2.util.H2DataTypes;
import com.sm.net.mo.References;

public class Database {

	public static final String DB_FILENAME = "database";
	public static final String DB_USERNAME = "admin";
	public static final String DB_PASSWORD = "";

	public static final String SCHEMA = "MEMORGANIZER";
	public static final String TAB_FILETEMP = "FILETEMP";
	public static final String TAB_FILENOTANALYZABLE = "FILENOTANALYZABLE";
	public static final String TAB_FOTO = "FOTO";
	public static final String TAB_IMAGE = "IMAGE";
	public static final String TAB_VIDEO = "VIDEO";
	public static final String TAB_FILE = "FILE";

	public static EasyH2Database createDatabase() {

		EasyH2Database database = new EasyH2Database(References.DIR_DB, DB_FILENAME, DB_USERNAME, DB_PASSWORD, true);
		database.createSchema(new EasyH2Schema(SCHEMA, true));
		database.createTable(getTableFileTemp());
		database.createTable(getTableFileNotAnalyzable());
		database.createTable(getTableFoto());
		database.createTable(getTableImage());
		database.createTable(getTableVideo());
		database.createTable(getTableFile());

		return database;
	}

	private static EasyH2Table getTableFileTemp() {
		EasyH2Table fileTemp = new EasyH2Table(TAB_FILETEMP, SCHEMA, true);
		fileTemp.addColumn(getColumnsFileTemp());

		return fileTemp;
	}

	private static EasyH2Table getTableFileNotAnalyzable() {
		EasyH2Table fileTemp = new EasyH2Table(TAB_FILENOTANALYZABLE, SCHEMA, true);
		fileTemp.addColumn(getColumnsFileNotAnalyzable());

		return fileTemp;
	}

	private static EasyH2Table getTableFoto() {
		EasyH2Table fileTemp = new EasyH2Table(TAB_FOTO, SCHEMA, true);
		fileTemp.addColumn(getColumnsFoto());

		return fileTemp;
	}

	private static EasyH2Table getTableImage() {
		EasyH2Table fileTemp = new EasyH2Table(TAB_IMAGE, SCHEMA, true);
		fileTemp.addColumn(getColumnsImage());

		return fileTemp;
	}

	private static EasyH2Table getTableVideo() {
		EasyH2Table fileTemp = new EasyH2Table(TAB_VIDEO, SCHEMA, true);
		fileTemp.addColumn(getColumnsVideo());

		return fileTemp;
	}

	private static EasyH2Table getTableFile() {
		EasyH2Table fileTemp = new EasyH2Table(TAB_FILE, SCHEMA, true);
		fileTemp.addColumn(getColumnsFile());

		return fileTemp;
	}

	private static EasyH2Column[] getColumnsFileTemp() {

		EasyH2Column id = new EasyH2Column("ID", H2DataTypes.INT, true, true);
		EasyH2Column filePath = new EasyH2Column("FILEPATH", H2DataTypes.VARCHAR, true, 260);

		return new EasyH2Column[] { id, filePath };
	}

	private static EasyH2Column[] getColumnsFileNotAnalyzable() {

		EasyH2Column id = new EasyH2Column("ID", H2DataTypes.INT, true, true);
		EasyH2Column filePath = new EasyH2Column("FILEPATH", H2DataTypes.VARCHAR, true, 260);

		return new EasyH2Column[] { id, filePath };
	}

	private static EasyH2Column[] getColumnsFoto() {

		EasyH2Column id = new EasyH2Column("ID", H2DataTypes.INT, true, true);
		EasyH2Column idFile = new EasyH2Column("IDFILE", H2DataTypes.INT, true);
		EasyH2Column filePath = new EasyH2Column("FILEPATH", H2DataTypes.VARCHAR, true, 260);

		return new EasyH2Column[] { id, idFile, filePath };
	}

	private static EasyH2Column[] getColumnsImage() {

		EasyH2Column id = new EasyH2Column("ID", H2DataTypes.INT, true, true);
		EasyH2Column idFile = new EasyH2Column("IDFILE", H2DataTypes.INT, true);
		EasyH2Column filePath = new EasyH2Column("FILEPATH", H2DataTypes.VARCHAR, true, 260);

		return new EasyH2Column[] { id, idFile, filePath };
	}

	private static EasyH2Column[] getColumnsVideo() {

		EasyH2Column id = new EasyH2Column("ID", H2DataTypes.INT, true, true);
		EasyH2Column idFile = new EasyH2Column("IDFILE", H2DataTypes.INT, true);
		EasyH2Column filePath = new EasyH2Column("FILEPATH", H2DataTypes.VARCHAR, true, 260);

		return new EasyH2Column[] { id, idFile, filePath };
	}

	private static EasyH2Column[] getColumnsFile() {

		EasyH2Column id = new EasyH2Column("ID", H2DataTypes.INT, true, true);
		EasyH2Column idFile = new EasyH2Column("IDFILE", H2DataTypes.INT, true);
		EasyH2Column filePath = new EasyH2Column("FILEPATH", H2DataTypes.VARCHAR, true, 260);

		return new EasyH2Column[] { id, idFile, filePath };
	}
}
