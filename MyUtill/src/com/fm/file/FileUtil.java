package com.fm.file;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
	public static String SDPATH = Environment.getExternalStorageDirectory()
			+ "/Photo_LJ/";
	public static int SEARCH_FILES = 0;

	/**
	 * 选择本地文件
	 *
	 * @param _this
	 * @param callbackId
	 */
	public static void toSelectFile(Context _this, String callbackId) {
		Intent intent = new Intent();
//		intent.setClass(_this, FileBrowserActivity.class);
		intent.putExtra("callbackId", callbackId);
		((Activity) _this).startActivityForResult(intent, SEARCH_FILES);

	}

	public static void saveBitmap(Bitmap bm, String picName) {
		try {
			if (!isFileExist("")) {
				File tempf = createSDDir("");
			}
			File f = new File(SDPATH, picName + ".JPEG");
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File createSDDir(String dirName) throws IOException {
		File dir = new File(SDPATH + dirName);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdir());
		}
		return dir;
	}

	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		file.isFile();
		return file.exists();
	}

	/**
	 * 创建文件夹
	 *
	 * @param dirName
	 * @return
	 * @throws IOException
	 */
	public static void createFiles(String dirName) throws IOException {
		if (!hasSdcard()) {
			return;
		}
		File dumpFolder = new File(dirName);
		if (!dumpFolder.exists()) {
			dumpFolder.mkdirs();
		}
	}

	/**
	 * 创建文件
	 *
	 * @param dirName
	 * @return
	 * @throws IOException
	 */
	public static File createFile(String dirName) throws IOException {
		if (hasSdcard()) {
			File dumpFolder = new File(dirName);
			if (!dumpFolder.exists()) {
				dumpFolder.createNewFile();
			}
			return dumpFolder;
		} else {
			return null;
		}

	}

	/**
	 * 复制资源文件到目录
	 *
	 * @param context
	 * @param pAssetFilePath
	 *            资源文件目录
	 * @param pDestDirPath
	 */
	public static void copyAssets(Context context, String pAssetFilePath,
								  String pDestDirPath) {
		AssetManager assetManager = context.getAssets();
		InputStream in = null;
		OutputStream out = null;
		try {
			in = assetManager.open(pAssetFilePath);
			File outFile = new File(pDestDirPath, pAssetFilePath);
			out = new FileOutputStream(outFile);
			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		} catch (IOException e) {
			Log.e("tag", "Failed to copy asset file: " + pAssetFilePath, e);
		}
	}

	/**
	 * 复制文件
	 *
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	private static void copyFile(InputStream in, OutputStream out)
			throws IOException {
		byte[] buffer = new byte[1024 * 16];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

	/**
	 * 1、判断SD卡是否存在
	 */
	public static boolean hasSdcard() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * @param path 路径
	 * @param buffer 写入内容
	 * @param isreset true追加写入，false覆盖
	 * @throws IOException
	 */
	public static void writeFileSdcardFile(String path, byte[] buffer,Boolean isreset)
			throws IOException {
		try {
			FileOutputStream fout = new FileOutputStream(path,isreset);
			fout.write(buffer);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
