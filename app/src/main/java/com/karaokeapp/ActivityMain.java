package com.karaokeapp;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityMain extends FragmentActivity implements OnEditorActionListener {

    private FragmentManager fm;

    private DrawerLayout mDlNavigation;

    private ListView mLvDrawer;

    private ActionBarDrawerToggle mDrawerToggle;

    private final int FAVORITES_POSITION = 0;

    private final int SONGS_BY_ARTIST_POSITION = 1;

    private final int SONGS_BY_NAME_POSITION = 2;

    private final int QUEUE_OF_SONGS_POSITION = 3;

    private final int SETTINGS_POSITION = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        //adding Navigation drawer to activity
        String[] navigationDrawerTitles = getResources()
                .getStringArray(R.array.navigation_drawer_titles);
        TypedArray navigationDrawerIcons = getResources()
                .obtainTypedArray(R.array.navigation_drawer_icon);
        mDlNavigation = (DrawerLayout) findViewById(R.id.dl_navigation);
        mLvDrawer = (ListView) findViewById(R.id.lv_drawer);
        ArrayList<NavDrawerItem> navigationDraverItems = new ArrayList<NavDrawerItem>();
        navigationDraverItems.add(new NavDrawerItem(navigationDrawerTitles[FAVORITES_POSITION],
                navigationDrawerIcons.getResourceId(FAVORITES_POSITION, -1)));
        navigationDraverItems
                .add(new NavDrawerItem(navigationDrawerTitles[SONGS_BY_ARTIST_POSITION],
                        navigationDrawerIcons.getResourceId(SONGS_BY_ARTIST_POSITION, -1)));
        navigationDraverItems
                .add(new NavDrawerItem(navigationDrawerTitles[SONGS_BY_NAME_POSITION],
                        navigationDrawerIcons.getResourceId(SONGS_BY_NAME_POSITION, -1)));
        navigationDraverItems
                .add(new NavDrawerItem(navigationDrawerTitles[QUEUE_OF_SONGS_POSITION],
                        navigationDrawerIcons.getResourceId(QUEUE_OF_SONGS_POSITION, -1)));
        navigationDraverItems.add(new NavDrawerItem(navigationDrawerTitles[SETTINGS_POSITION],
                navigationDrawerIcons.getResourceId(SETTINGS_POSITION, -1)));

        //recycle the typed array
        navigationDrawerIcons.recycle();

        //setting Navigation Drawer adapter
        NavDrawerAdapter navigationAdapter = new NavDrawerAdapter(this, navigationDraverItems);
        mLvDrawer.setAdapter(navigationAdapter);

        //opening and closing Navigation Drawer with app icon
        mDrawerToggle = new ActionBarDrawerToggle(this, mDlNavigation,
                R.drawable.ic_navigation_drawer, R.string.drawer_open, R.string.drawer_close);
        mDlNavigation.setDrawerListener(mDrawerToggle);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //onItemClickListener on navigation drawer item
        mLvDrawer.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Fragment fragment = null;
                String TAG = "";
                switch (position) {
                    case FAVORITES_POSITION:
                        if (fm.findFragmentByTag(FragmentFavoritesSongs.TAG) == null) {
                            TAG = FragmentFavoritesSongs.TAG;
                            fragment = new FragmentFavoritesSongs();
                            getActionBar().setTitle("Favorites");
                        }

                        break;
                    case SONGS_BY_ARTIST_POSITION:
                        if (fm.findFragmentByTag(FragmentSongsByArtist.TAG) == null) {
                            TAG = FragmentSongsByArtist.TAG;
                            fragment = new FragmentSongsByArtist();
                            getActionBar().setTitle("All songs by Artist");
                        }
                        break;
                    case SONGS_BY_NAME_POSITION:
                        if (fm.findFragmentByTag(FragmentSongsByName.TAG) == null) {
                            TAG = FragmentSongsByName.TAG;
                            fragment = new FragmentSongsByName();
                            getActionBar().setTitle("All songs by Name");
                        }
                        break;
                    case QUEUE_OF_SONGS_POSITION:
                        if (fm.findFragmentByTag(FragmentQueueOfSongs.TAG) == null) {
                            TAG = FragmentQueueOfSongs.TAG;
                            fragment = new FragmentQueueOfSongs();
                            getActionBar().setTitle("Queue of songs");
                        }
                        break;
                    case SETTINGS_POSITION:
                        Toast.makeText(ActivityMain.this,"Sorry,in next version", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                if (fragment != null && TAG.length() > 0) {
                    fm.beginTransaction().replace(R.id.fl_content, fragment, TAG).commit();
                    mDlNavigation.closeDrawer(mLvDrawer);
                } else {
                    Log.e("ActivityMain", "Error in creating fragment");
                    mDlNavigation.closeDrawer(mLvDrawer);
                }

            }
        });

        //starting home fragment
        if (fm.findFragmentByTag(FragmentSongsByArtist.TAG) == null) {
            Fragment fragmentAllSongsByArtist = new FragmentSongsByArtist();
            getActionBar().setTitle("All songs by Artist");
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fl_content, fragmentAllSongsByArtist,
                    FragmentSongsByArtist.TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.activity_main_actions, menu);
//
//		mSearchItem = menu.findItem(R.id.act_search);
//		SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
//		SearchView searchView = (SearchView) MenuItemCompat.getActionView(mSearchItem);
//		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//
//		SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() {
//
//			@Override
//			public boolean onQueryTextSubmit(String query) {
//				return false;
//			}
//
//			@Override
//			public boolean onQueryTextChange(String newText) {
//				String searchText = newText.toLowerCase(Locale.getDefault());
//				mAdapter.filter(searchText);
//				return true;
//			}
//		};
//		searchView.setOnQueryTextListener(textChangeListener);
//
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        // TODO Auto-generated method stub
        return false;
    }


}
