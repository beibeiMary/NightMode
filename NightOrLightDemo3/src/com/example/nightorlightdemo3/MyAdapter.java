package com.example.nightorlightdemo3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class MyAdapter extends BaseAdapter {
	private ArrayList<Map<String, String>> mlist;
	private Context context;

	public MyAdapter(ArrayList<Map<String, String>> mlist, Context context) {
		super();
		this.mlist = mlist;
		this.context = context;
	}

	@Override
	public int getCount() {
		return mlist.size();
	}

	@Override
	public Object getItem(int position) {
		return mlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (null == convertView) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_photo);
			viewHolder.tvtitle = (TextView) convertView.findViewById(R.id.name);
			viewHolder.tvdescribe = (TextView) convertView.findViewById(R.id.age);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Map<String, String> map = (Map<String, String>) getItem(position);
		if (map!=null) {
			viewHolder.tvtitle.setText(map.get("title"));
			viewHolder.tvdescribe.setText(map.get("describe"));
		}
		return convertView;
	}

	private static class ViewHolder {
		ImageView imageView;
		TextView tvtitle;
		TextView tvdescribe;
	}

}
