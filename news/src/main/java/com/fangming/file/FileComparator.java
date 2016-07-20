package com.fangming.file;

import java.util.Comparator;

/**
 * @author fangming
 *  文件排序
 */
public class FileComparator implements Comparator<FileInfo> {

	public int compare(FileInfo file1, FileInfo file2) {
		if (file1.IsDirectory && !file2.IsDirectory) {
			return -1000;
		} else if (!file1.IsDirectory && file2.IsDirectory) {
			return 1000;
		}
		return file1.Name.compareTo(file2.Name);
	}
}