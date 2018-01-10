package main.java.com.fangming.testffmpeg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.content.res.AssetManager;
import android.util.Log;

public class Utils {
	public static void copyAssets(Context pContext, String pAssetFilePath, String pDestDirPath) {
		AssetManager assetManager = pContext.getAssets();
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
		} catch(IOException e) {
			Log.e("tag", "Failed to copy asset file: " + pAssetFilePath, e);
		}
	}
	private static void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024*16];
		int read;
		while((read = in.read(buffer)) != -1){
			out.write(buffer, 0, read);
		}
	}

	//检查自己的手机是否支持GLES2.0
	public static boolean detectOpenGLES20(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ConfigurationInfo info = am.getDeviceConfigurationInfo();
		return (info.reqGlEsVersion >= 0x20000);
	}
}
