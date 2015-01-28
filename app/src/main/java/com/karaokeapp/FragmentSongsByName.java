package com.karaokeapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

@SuppressLint("NewApi")
public class FragmentSongsByName extends Fragment {

    private GridAdapter mAdapter;

    private MenuItem mSearchItem;

    public static final String TAG = FragmentSongsByName.class.getSimpleName();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_all_songs_by_name, container, false);
        GridView mGvSong = (GridView) view.findViewById(R.id.gv_song_by_name);
        return view;
    }

//	@Override
//	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//		inflater.inflate(R.menu.activity_main_actions, menu);
//		
//		mSearchItem = menu.findItem(R.id.act_search);
//		SearchView searchView = (SearchView) menu.findItem(R.id.act_search).getActionView();
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
//		super.onCreateOptionsMenu(menu, inflater);
//	}

}
