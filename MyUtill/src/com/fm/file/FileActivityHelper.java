package com.fm.file;

import android.app.Activity;
import android.widget.Toast;

import com.fm.myutill.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author fangming
 *	get files
 */
public class FileActivityHelper {
	public static ArrayList<FileInfo> getFiles(Activity activity, String path){
		File f=null;
		File[] files=null;
		try {
			f=new File(path);
			files=f.listFiles();
			if(files==null){
				Toast.makeText(activity, activity.getString(R.string.file_cannotopen), Toast.LENGTH_SHORT);
				return null;
			}
		} catch (Exception e) {
			Toast.makeText(activity,e.getMessage(), Toast.LENGTH_SHORT);
		}
		ArrayList<FileInfo> fileList = new ArrayList<FileInfo>();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			FileInfo fileInfo = new FileInfo();
			fileInfo.Name = file.getName();
			fileInfo.IsDirectory = file.isDirectory();
			fileInfo.Path = file.getPath();
			fileInfo.Size = file.length();
			fileList.add(fileInfo);
		}
		
		Collections.sort(fileList, new FileComparator());
		return fileList;
		
	}
}
