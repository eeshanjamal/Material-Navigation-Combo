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

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabLayoutFragment extends Fragment{

	public TabLayoutFragment() {
		// TODO Auto-generated constructor stub
	}
	
	private TabLayout tabLayout;
	private ViewPager viewPager;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.tab_layout_fragment, container,false);
		
		tabLayout = (TabLayout)rootView.findViewById(R.id.tab_layout);
		viewPager = (ViewPager)rootView.findViewById(R.id.viewpager);
		
		setupViewPager(); 
		setupTabs();
		
		return rootView;
		
	}
	
	private void setupViewPager(){
		viewPager.setAdapter(new PagingAdapter(getChildFragmentManager()));
		viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
	}
	
	private void setupTabs(){
	    tabLayout.setupWithViewPager(viewPager);
	}
	
	class PagingAdapter extends FragmentStatePagerAdapter{

		ArrayList<String> titles;
		
		public PagingAdapter(FragmentManager fm) {
			super(fm);
			titles = new ArrayList<String>();
			titles.add(getString(R.string.artist));
			titles.add(getString(R.string.album));
		}

		@Override
		public int getCount() {
			return titles.size();
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			return titles.get(position);
		}

		@Override
		public Fragment getItem(int position) {
			return PlaceHolderFragment.newInstance(titles.get(position));
		}
		
	}


}
