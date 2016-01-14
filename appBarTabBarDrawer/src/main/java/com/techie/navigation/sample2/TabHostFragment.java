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

package com.techie.navigation.sample2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;

import com.techie.navigation.R;
import com.techie.navigation.common.PlaceHolderFragment;

public class TabHostFragment extends Fragment{

	private TabWidget tabs;
	private FragmentTabHost tabHost;
	
	public TabHostFragment() {
		super();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	    View rootView = inflater.inflate(R.layout.tabhost_fragment, container, false);

		tabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
	    tabHost.setup(getActivity(), getChildFragmentManager(), R.id.tabFrameLayout);
	    
	    tabs 	= (TabWidget)rootView.findViewById(android.R.id.tabs);
	    
	    tabs.setBackgroundColor(getResources().getColor(R.color.primary));
	    createTabs();
		
		return rootView;
		
	}
	
	private void createTabs(){
		
		String title = getString(R.string.artist);
		addTab(title, PlaceHolderFragment.class, PlaceHolderFragment.getBundle(title));
		
		title = getString(R.string.album);
		addTab(title, PlaceHolderFragment.class, PlaceHolderFragment.getBundle(title));
		
	}
	
	private void addTab(String title, Class<?> tabClass, Bundle bundle){
		TabSpec spec = tabHost.newTabSpec(title);
		spec.setIndicator(title);
		tabHost.addTab(spec, tabClass, bundle);
	}
	
}
