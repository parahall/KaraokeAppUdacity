package com.karaokeapp;

import com.karaokeapp.data.KaraokeContract.KaraokeSongsEntry;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Locale;

public class FragmentSongsByArtist extends Fragment implements DataManager
        .dataIteractionListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LAST_SAVED_POSITION = "LAST_SAVED_POSITION";

    private GridAdapter mAdapter;

    private MenuItem mSearchItem;

    public static final String TAG = FragmentSongsByArtist.class.getSimpleName();

    private ProgressDialog progressDialog;

    private GridView mGvSong;

    private static final int KARAOKE_SONGS_LOADER = 0;

    private int mSavedPosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().restartLoader(KARAOKE_SONGS_LOADER, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_all_songs_by_artist, container, false);
        mGvSong = (GridView) view.findViewById(R.id.gv_song);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LAST_SAVED_POSITION, mGvSong.getFirstVisiblePosition());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mSavedPosition = savedInstanceState.getInt(LAST_SAVED_POSITION, 0);
        }
    }

    @Override
    public void onStop() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        super.onStop();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_main_actions, menu);

        mSearchItem = menu.findItem(R.id.act_search);
        SearchView searchView = (SearchView) menu.findItem(R.id.act_search).getActionView();

        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String searchText = newText.toLowerCase(Locale.getDefault());
                mAdapter.filter(searchText);
                return true;
            }
        };
        searchView.setOnQueryTextListener(textChangeListener);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void dataLoaded(ArrayList<Song> songs) {
        progressDialog.dismiss();
        if (isAdded()) {
            mAdapter = new GridAdapter(getActivity(), songs, TAG);
            mGvSong.setAdapter(mAdapter);
            mGvSong.smoothScrollToPosition(mSavedPosition);
            mGvSong.setOnItemClickListener(new GridView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                        int position, long id) {
                    Song choosedSong = (Song) mAdapter.getItem(position);
                    DialogFragmentSongDetails songFragment = DialogFragmentSongDetails
                            .getInstance(choosedSong);
                    songFragment.show(getFragmentManager(), "Dialog fragment Song Details");
                }

            });
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        // Sort order:  Ascending, by date.

        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        return new CursorLoader(
                getActivity(),
                KaraokeSongsEntry.CONTENT_URI,
                KaraokeSongsEntry.KARAOKE_SONGS_COLUMNS,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst() && isAdded()) {
            DataManager.loadSongs(this, data);
        } else {
            DataManager.loadSongList(getActivity(), this);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
}
