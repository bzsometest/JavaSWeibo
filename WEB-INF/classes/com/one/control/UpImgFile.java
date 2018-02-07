package com.one.control;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.one.tool.MySaveLog;

@MultipartConfig
public class UpImgFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String getFile3(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String path = "C:\\TrangleTest\\";
		String string = request.getServletContext().getRealPath("/");
		String path = string;
		Part part = request.getPart("img");
		if (part == null) {
			return null;
		}
		// 图片储存文件夹
		String fileDir = "weiboimg";
		// 图片存储文件名
		String fileName = getDateFile() + ".png";
		File file = new File(path + "/" + fileDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 得到最后图片相对于项目的路径
		String imgPath = fileDir + "/" + fileName;
		part.write(path + "/" + imgPath);
		return imgPath;
	}

	public static String getFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MySaveLog.save("getFile:Start");
		String string = request.getServletContext().getRealPath("/");
		MySaveLog.save("getFile:" + string);
		try {// windows操作系统
			string = string.substring(0, string.lastIndexOf('\\'));
			string = string.substring(0, string.lastIndexOf('\\'));
		} catch (Exception e) {
			System.out.println("可能是Linux操作系统！");
			try {// Linux操作系统
				string = string.substring(0, string.lastIndexOf('/'));
				string = string.substring(0, string.lastIndexOf('/'));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		String path = string;
		Part part = request.getPart("img");
		if (part == null) {
			return null;
		}
		// 图片储存文件夹
		String fileDir = "weiboimg";
		// 图片存储文件名
		String fileName = getDateFile() + ".png";
		File file = new File(path + "/" + fileDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 得到最后图片相对于项目的路径
		String imgPath = fileDir + "/" + fileName;
		part.write(path + "/" + imgPath);
		return imgPath;
	}

	/** 获取时间,主要用于保存文件 */
	public static String getDateFile() {
		Date date = new Date();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		return sdFormat.format(date);
	}

}
