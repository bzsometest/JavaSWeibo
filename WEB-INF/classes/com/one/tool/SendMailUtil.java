package com.one.tool;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMailUtil {
	private static String title = "邮箱主题";
	private static String content = "邮箱正文";
	private static String nick = "菁英微博-第1组 - ";
	private static String signTitle = " - 菁英微博";
	private static String signContent = "\r\n<br><br><hr>菁英微博邮件发送系统，时间:" + getMyDate();

	public static int send(String toAddress, String title, String content) {
		try {
			SendMailUtil.title = title;
			SendMailUtil.content = content;
			send(toAddress);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			saveLog(e.toString());
		}
		return 0;
	}

	/*
	 * 发送邮件到指定邮箱
	 */
	public static void send(String toAddress) throws Exception {

		/*
		 * 第一步：创建Session，包含邮件服务器网络连接信息
		 */
		// System.setProperty("java.net.preferIPv4Stack", "true");
		Properties props = new Properties();
		// 指定邮件的传输协议，smtp;同时通过验证
		System.setProperty("mail.mime.charset", "UTF-8");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.ssl.enable", "true");
		props.setProperty("mail.smtp.auth", "true");
		// props.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getDefaultInstance(props);

		// 开启调试模式
		session.setDebug(true);
		/*
		 * 第二步：编辑邮件内容
		 */
		Message message = new MimeMessage(session);
		// 设置邮件消息头

		message.setFrom(new InternetAddress(MimeUtility.encodeText(nick) + " <bzsome@163.com>"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
		message.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse("bzsome@163.com"));
		message.setSubject(title + signTitle);
		// 设置邮件消息内容、包含附件
		Multipart msgPart = new MimeMultipart();
		message.setContent(msgPart);

		MimeBodyPart body = new MimeBodyPart(); // 正文
		// MimeBodyPart attach = new MimeBodyPart(); // 附件

		msgPart.addBodyPart(body);
		// msgPart.addBodyPart(attach);

		// 设置正文内容
		body.setContent(content + signContent, "text/html;charset=utf-8");
		// 设置附件内容
		// attach.setDataHandler(new DataHandler(new FileDataSource("E:\\JavaS.war")));
		// attach.setFileName("JavaS.war");

		message.saveChanges();
		/*
		 * 第三步：发送邮件
		 */
		Transport trans = session.getTransport();
		trans.connect("smtp.163.com", "bzsome", "weibo123456");
		trans.sendMessage(message, message.getAllRecipients());
	}

	public static void saveLog(String str) {
		try {
			PrintStream out = System.out;// 保存原输出流
			PrintStream ps = new PrintStream("/root/log.txt");// 创建文件输出流1

			System.setOut(ps);// 设置使用新的输出流
			System.out.println(str);

			System.setOut(out);// 恢复原有输出流
			System.out.println("程序运行完毕，恢复为原输出流。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取标准时间 */
	public static String getMyDate() {
		Date date = new Date();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdFormat.format(date);
	}

	public static void main(String[] args) throws Exception {
		SendMailUtil.send("153355720@qq.com");
	}

}