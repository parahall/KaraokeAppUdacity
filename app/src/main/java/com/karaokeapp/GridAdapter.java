package com.karaokeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private static final String ALL_SONGS_TAG = "FragmentAllSongsByArtist";

    private static final String FAVORITES_SONGS_TAG = "FragmentFavoritesSongs";

    private LayoutInflater mInflater;

    private ArrayList<Song> mSongArrayList;

    private ArrayList<Song> mSongList;

    private String TAG;

    public GridAdapter(Context context, ArrayList<Song> songList, String TAG) {
        mInflater = LayoutInflater.from(context);
        mSongArrayList = songList;
        this.mSongList = new ArrayList<Song>();
        this.mSongList.addAll(mSongArrayList);
        this.TAG = TAG;
    }

    @Override
    public int getCount() {
        return mSongArrayList.size();
    }

    @Override
    public Song getItem(int position) {
        return mSongArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {

        public TextView artist;

        public TextView songName;

        public ImageView albumCover;

        public TextView duration;

        public ImageView addToFavorites;

        //TODO need to implement
        public ImageView removeFromFavorites;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_song_list, parent,
                    false);

            holder = new ViewHolder();
            holder.artist = (TextView) convertView
                    .findViewById(R.id.tv_artist_item);
            holder.songName = (TextView) convertView
                    .findViewById(R.id.tv_song_name_item);
            holder.duration = (TextView) convertView
                    .findViewById(R.id.tv_duration_item);
            holder.albumCover = (ImageView) convertView
                    .findViewById(R.id.iv_album_cover_item);
            holder.addToFavorites = (ImageView) convertView
                    .findViewById(R.id.btn_add_to_favorite_item);
//			holder.removeFromFavorites = (ImageView) convertView.findViewById(R.id.btn_remove_from_favorites);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Song song = mSongArrayList.get(position);
        holder.artist.setText(song.getArtist());
        holder.songName.setText(song.getSongName());
        holder.duration.setText(song.getDuration());

//
//        if(song.albumCoverUrl==null || song.albumCoverUrl.length()==0){
//            //TODO download cover from internet. Need to add support
//        }

        holder.albumCover.setImageResource(R.drawable.defcover);

        if (TAG.equals(ALL_SONGS_TAG)) {
//			holder.removeFromFavorites.setVisibility(View.GONE);

        } else if (TAG.equals(FAVORITES_SONGS_TAG)) {
            holder.addToFavorites.setVisibility(View.GONE);
        }

        holder.addToFavorites.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GridView mGvSong = (GridView) parent.findViewById(R.id.gv_song);
                Song song = (Song) mGvSong.getItemAtPosition(position);
                DataManager dataManager = new DataManager();
                try {
                    dataManager.addSongToFavorites(song);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
//TODO need to implement
//		holder.removeFromFavorites.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				GridView mGvSong = (GridView) parent.findViewById(R.id.gv_song);
//				Song song = (Song) mGvSong.getItemAtPosition(position);
//				DataManager dataManager = new DataManager();
//				dataManager.removeSongFromFavorites(mContext, song);
//			}
//		});
        return convertView;
    }

    public void filter(String searchText) {
        searchText = searchText.toLowerCase();
        mSongArrayList.clear();
        if (searchText.length() == 0) {
            mSongArrayList.addAll(mSongList);
        } else {
            for (Song songSearch : mSongList) {
                if (songSearch.getArtist().toLowerCase().contains(searchText)
                        | songSearch.getSongName().toLowerCase()
                        .contains(searchText)) {
                    mSongArrayList.add(songSearch);
                }
            }
        }
        notifyDataSetChanged();
    }
}
