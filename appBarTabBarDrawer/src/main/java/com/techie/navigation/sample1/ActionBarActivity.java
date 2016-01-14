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

package com.techie.navigation.sample1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.techie.navigation.R;
import com.techie.navigation.common.PlaceHolderFragment;


public class ActionBarActivity extends AppCompatActivity {

	private DrawerArrayAdapter adapter;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mTitle;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.actionbar_main);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        
        String title = getIntent().getStringExtra("title");
        getSupportActionBar().setTitle(title);
        
		setupSlider();
		setupTabs();
			
		
	}

	private void setupSlider(){
		
		mTitle = getSupportActionBar().getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_options, R.string.app_name) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                setSliderTitle(mTitle);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setSliderTitle(getString(R.string.drawer_options));
            }
            
        };
        
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
	    
		adapter = new DrawerArrayAdapter(this);
        mDrawerList.setAdapter(adapter);
        
	}
	
	@SuppressWarnings("deprecation")
	private void setupTabs(){
	
	    ActionBar actionBar = getSupportActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	
	    String title = getString(R.string.artist);
	    Tab tab = actionBar.newTab()
	                       .setText(title)
	                       .setTabListener(new ActionBarTabListener<PlaceHolderFragment>(
	                               this, title, PlaceHolderFragment.class, PlaceHolderFragment.getBundle(title)));
	    actionBar.addTab(tab);
	
	    title = getString(R.string.album);
	    tab = actionBar.newTab()
	                   .setText(R.string.album)
	                   .setTabListener(new ActionBarTabListener<PlaceHolderFragment>(
	                           this, "album", PlaceHolderFragment.class, PlaceHolderFragment.getBundle(title)));
	    actionBar.addTab(tab);
    
	}


	private void setSliderTitle(CharSequence title){
		getSupportActionBar().setTitle(title);
        supportInvalidateOptionsMenu();
	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});
        
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		 boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        
        // Handle your other action bar items...
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
		
	}
}
