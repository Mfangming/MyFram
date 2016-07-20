package com.fm.testmain;

import java.util.List;

import com.fangming.myframwork.R;
import com.fm.entity.ProjectInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProjectAdapter extends BaseAdapter {

	public List<ProjectInfo> projectInfo;
	private LayoutInflater inflater;

	public ProjectAdapter(Context context, List<ProjectInfo> talkMsgList) {
		this.projectInfo = talkMsgList;
		inflater = LayoutInflater.from(context);
	}

	public void updateList(List<ProjectInfo> talkMsgList) {
		this.projectInfo = talkMsgList;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return projectInfo.size();
	}

	@Override
	public Object getItem(int position) {
		return projectInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_projectadapter, parent,
					false);
			holder.tv_pname = (TextView) convertView.findViewById(R.id.tv_pname);
			holder.tv_describe = (TextView) convertView.findViewById(R.id.tv_describe);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_pname.setText(projectInfo.get(position).getProjectname());
		holder.tv_describe.setText(projectInfo.get(position).getProjectdescribe());
		return convertView;
	}
	

	class ViewHolder {
		public TextView tv_pname;
		public TextView tv_describe;
	}

}
