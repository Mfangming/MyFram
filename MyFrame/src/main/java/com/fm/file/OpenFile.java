package main.java.com.fm.file;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class OpenFile {
	Context context;
	Map<String, Integer> extensionMap = new HashMap<String, Integer>();// 文件类型集合

	public OpenFile(Context context) {
		this.context = context;
		setExtensionMap();
	}

	// 文件类型
	private void setExtensionMap() {
		extensionMap.put("jpg", 1);
		extensionMap.put("jpeg", 1);
		extensionMap.put("gif", 1);
		extensionMap.put("png", 1);
		extensionMap.put("bmp", 1);
		extensionMap.put("txt", 2);
		extensionMap.put("java", 2);
		extensionMap.put("xml", 2);
		extensionMap.put("log", 2);
		extensionMap.put("doc", 3);
		extensionMap.put("docx", 3);
		extensionMap.put("xls", 4);
		extensionMap.put("xlsx", 4);
		extensionMap.put("ppt", 5);
		extensionMap.put("pptx", 5);
		extensionMap.put("pdf", 6);
		extensionMap.put("amr", 7);
		extensionMap.put("mp3", 7);
		extensionMap.put("wav", 7);
		extensionMap.put("ogg", 7);
		extensionMap.put("midi", 7);
		extensionMap.put("rmvb", 8);
		extensionMap.put("mp4", 8);
		extensionMap.put("rm", 8);
		extensionMap.put("avi", 8);
		extensionMap.put("wmv", 8);
		extensionMap.put("mpg", 8);
		extensionMap.put("3gp", 8);
		extensionMap.put("chm", 9);
		extensionMap.put("apk", 10);
		extensionMap.put("html", 11);
		extensionMap.put("htm", 11);
		extensionMap.put("php", 11);
		extensionMap.put("jsp", 11);
		extensionMap.put("mht", 11);
		extensionMap.put("ceb", 12);// 不能打开
	}

	// 打开文件
	public void openFile(String file) {
		System.out.println("file=" + file);
		try {
			Intent intent = new Intent("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			File _file = new File(file);
			if (_file.exists()) {
				Uri uri = Uri.fromFile(_file);

				intent.setDataAndType(uri, getFileExtension(file));

				context.startActivity(intent);
			}
		} catch (Exception e) {
//			FileLog.fLogException(e);
			e.printStackTrace();
		}

	}

	// 打开文件
	public void openFileNotice(String file) {
		System.out.println("file=" + file);

		try {
			Intent intent = new Intent("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			File _file = new File(file);
			if (_file.exists()) {
				Uri uri = Uri.fromFile(_file);
				String type = getFileExtension(file);
				if ("*/*".equals(type)) {
					Intent intent1 = new Intent();
					// intent1.setClass(context, FileActivity.class);
					intent1.putExtra("path", file);
					context.startActivity(intent1);

				} else {

					intent.setDataAndType(uri, type);

					context.startActivity(intent);
				}

			}
		} catch (Exception e) {
//			FileLog.fLogException(e);
			e.printStackTrace();
		}

	}

	// 获取文件类型
	private String getFileExtension(String file) {
		String contentType = "*/*";
		try {
			String fileExtension = null;
			int index = file.lastIndexOf(".");
			if (index != -1) {
				fileExtension = file.substring(index + 1).trim().toLowerCase();
				switch (extensionMap.get(fileExtension)) {
					case 1:
						contentType = "image/*";
						break;
					case 2:
						contentType = "text/plain";
						break;
					case 3:
						contentType = "application/msword";
						break;
					case 4:
						contentType = "application/vnd.ms-excel";
						break;
					case 5:
						contentType = "application/vnd.ms-powerpoint";
						break;
					case 6:
						contentType = "application/pdf";
						break;
					case 7:
						contentType = "audio/*";
						break;
					case 8:
						contentType = "video/*";
						break;
					case 9:
						contentType = "application/x-chm";
						break;
					case 10:
						contentType = "application/vnd.android.package-archive";
						break;
					case 11:
						contentType = "text/html";
						break;
					case 12:
						contentType = "application/*";
						break;
					default:
						break;
				}
			}
		} catch (Exception e) {
//			FileLog.fLogException(e);
			e.printStackTrace();
		}
		return contentType;
	}
}
