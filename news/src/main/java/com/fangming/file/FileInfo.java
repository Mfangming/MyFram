package com.fangming.file;

import com.fangming.news.R;

public class FileInfo {
	public String Name;
	public String Path;
	public long Size;
	public boolean IsDirectory = false;
	public int FileCount = 0;
	public int FolderCount = 0;

	public int getIconResourceId() {
		if (IsDirectory) {
			return R.mipmap.folder;
		}
		return R.mipmap.doc;
	}

}
