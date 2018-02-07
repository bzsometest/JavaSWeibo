package com.one.tool;

import java.io.*;

public class MySaveLog {
	public static String strFilename = "/usr/local/tomcat/webapps/JavaSWeibo/logx.txt";

	public static void save(final String strBuffer) {
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File(strFilename);
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		PrintWriter pw = new PrintWriter(fw);
		pw.println("\n" + strBuffer);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setFile(String filename) {
		strFilename = filename;
	}

}
