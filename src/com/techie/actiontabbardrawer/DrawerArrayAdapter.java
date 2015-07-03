/*
 * Copyright (C) 2015 Eeshan Jamal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.techie.actiontabbardrawer;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DrawerArrayAdapter extends BaseAdapter {


	private Context context;
	private ArrayList<String> components;
	private OnClickListener onClickListener;
	
	public DrawerArrayAdapter(Context context) {
		this.context = context;
		
		initializeComponents();
		initializeListeners();
	}
	
	private void initializeComponents(){
		
		components = new ArrayList<String>();
		
		components.add("First");
		components.add("Second");
		components.add("third");
		components.add("Fourth");
		components.add("Fifth");
		
	}
	
	private void initializeListeners(){
		 onClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Integer index = Integer.parseInt(v.getTag().toString());
				Toast.makeText(context, components.get(index), Toast.LENGTH_SHORT).show();
			}
		};
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return components.size();
	}

	@Override
	public Object getItem(int position) {
		return components.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null)
			convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
		
		TextView tv = (TextView)convertView.findViewById(android.R.id.text1);
		tv.setText(components.get(position));
		
		convertView.setOnClickListener(onClickListener);
		convertView.setTag(position);
		
		return convertView;
	}

}
