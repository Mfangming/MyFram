package mian.com.fm.db.constants;

import java.io.File;

import android.os.Environment;

public class Constants {
	public static String CrashLogDir = Environment.getExternalStorageDirectory() 
			+ File.separator + "CrashLog";
	public static String Username = "username";
}
