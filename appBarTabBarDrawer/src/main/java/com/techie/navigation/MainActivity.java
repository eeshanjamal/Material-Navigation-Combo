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

package com.techie.navigation;

import android.R.color;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.techie.navigation.common.PlaceHolderFragment;
import com.techie.navigation.sample1.ActionBarActivity;
import com.techie.navigation.sample2.TabHostFragment;
import com.techie.navigation.sample2.TabLayoutFragment;
import com.techie.navigation.sample2.ToolbarActivity;
import com.techie.navigation.sample3.FullHeightNavDrawerActivity;

public class MainActivity extends AppCompatActivity {

	private ListView optionList;
	private ArrayAdapter<OptionsEnum> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		optionList = (ListView)findViewById(R.id.option_list);
		setupListComponents();
		setupListener();
	}
	
	private void setupListComponents(){
		
		OptionsEnum options[] = OptionsEnum.values();
		
		adapter = new ArrayAdapter<OptionsEnum>(this, android.R.layout.simple_list_item_2, android.R.id.text1,  options){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				
				View vu = super.getView(position, convertView, parent);
				
				TextView tv1 = (TextView)vu.findViewById(android.R.id.text1);
				TextView tv2 = (TextView)vu.findViewById(android.R.id.text2);
				
				tv1.setTextSize(24.0f);
				tv2.setTextColor(getResources().getColor(color.darker_gray));
				OptionsEnum option = getItem(position);
				
				tv1.setText(option.getTitle());
				tv2.setText(option.getDescription());
				
				return vu;
			}
		};
		
		optionList.setAdapter(adapter);
		
	}
	
	private void setupListener(){
		optionList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				OptionsEnum option = adapter.getItem(position);
				startActivity(option.getIntent(MainActivity.this));
			}
		});
	}
	
	enum OptionsEnum{
		
		SAMPLE1 {

			@Override
			String getTitle() {
				return "Sample1";
			}
			
			@Override
			String getDescription() {
				return "This sample screen shows the integration of ActionBar, TabBar & Naivgation Drawer components together.";
			}

			@Override
			Intent getIntent(Context context) {
				Intent i = new Intent(context, ActionBarActivity.class);
				i.putExtra("title", getTitle());
				return i;
			}

		}, 
		SAMPLE2_1 {
			
			@Override
			String getTitle() {
				return "Sample2.1";
			}
			
			@Override
			String getDescription() {
				return "This sample screen shows the integration of Toolbar with TabHost including Navigation Drawer.";
			}

			@Override
			Intent getIntent(Context context) {
				Intent i = new Intent(context, ToolbarActivity.class);
				i.putExtra("fragment", TabHostFragment.class.getName());
				i.putExtra("title", getTitle());
				return i;
			}

		}, 
		SAMPLE2_2 {

			
			@Override
			String getTitle() {
				return "Sample2.2";
			}

			@Override
			String getDescription() {
				return "This sample screen shows the integration of Toolbar with TabLayout including Navigation Drawer.";
			}
			
			@Override
			Intent getIntent(Context context) {
				Intent i = new Intent(context, ToolbarActivity.class);
				i.putExtra("fragment", TabLayoutFragment.class.getName());
				i.putExtra("title", getTitle());
				return i;
			}

		},
		SAMPLE3{

			@Override
			String getTitle() {
				return "Sample3";
			}

			@Override
			String getDescription() {
				return "This sample screen shows the integration of Full length Naivgation Drawer including translucent StatusBar & ToolBar";
			}
			
			@Override
			Intent getIntent(Context context) {
				Intent i = new Intent(context, FullHeightNavDrawerActivity.class);
				i.putExtra("fragment", PlaceHolderFragment.class.getName());
				i.putExtra("title", getTitle());
				return i;
			}

		};
		
		abstract String getTitle();
		abstract String getDescription();
		abstract Intent getIntent(Context context);
	}

}
