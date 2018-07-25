package com.sm.net.mo.util;

import java.io.File;

public class FileUtil {

	public static File createDirectory(String absolutePath) {

		File folderFile = new File(absolutePath);
		folderFile.mkdirs();

		return folderFile;
	}

	public static String addSubfolder(String parent, String children) {
		return parent + File.separatorChar + children;
	}
}
