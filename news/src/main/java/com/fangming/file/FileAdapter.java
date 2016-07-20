/**
 * 
 */
package com.fangming.file;

import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangming.news.R;


/**
 * @author fangming
 * file adapter
 */
public class FileAdapter extends BaseAdapter {

	private LayoutInflater _inflater;
	private List<FileInfo> _files;

	public FileAdapter(Context context, List<FileInfo> files) {
		_files = files;
		_inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return _files.size();
	}


	public Object getItem(int position) {
		return _files.get(position);
	}


	public long getItemId(int position) {
		return position;
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null){
			convertView=_inflater.inflate(R.layout.file_item, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.file_name);
			holder.icon = (ImageView) convertView.findViewById(R.id.file_icon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		FileInfo f = _files.get(position);
		holder.name.setText(f.Name);
		holder.icon.setImageResource(f.getIconResourceId());

		return convertView;
	}
	
	/* class ViewHolder */
	private class ViewHolder {
		TextView name;
		ImageView icon;
	}
}
