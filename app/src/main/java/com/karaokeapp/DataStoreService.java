package com.karaokeapp;

import com.karaokeapp.data.KaraokeContract;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStoreService extends IntentService {

    public DataStoreService() {
        super("DataStoreService");
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onHandleIntent(Intent intent) {
        Serializable serializable = intent.getExtras()
                .getSerializable(DataManager.SONG_LIST_KEY);
        if (serializable != null) {
            ArrayList<Song> songArrayList = (ArrayList<Song>) serializable;
            for (Song song : songArrayList) {
                ContentValues value = new ContentValues();
                value.put(KaraokeContract.KaraokeSongsEntry.COLUMN_SONG_ID, song.getId());
                value.put(KaraokeContract.KaraokeSongsEntry.COLUMN_ARTIST_NAME, song.getArtist());
                value.put(KaraokeContract.KaraokeSongsEntry.COLUMN_SONG_NAME, song.getSongName());
                value.putNull(KaraokeContract.KaraokeSongsEntry.COLUMN_ALBUM_COVER_URL);
                value.put(KaraokeContract.KaraokeSongsEntry.COLUMN_DURATION, song.getDuration());
                value.put(KaraokeContract.KaraokeSongsEntry.COLUMN_LYRICS_URL, song.getLyrics());
                value.put(KaraokeContract.KaraokeSongsEntry.COLUMN_VERSION, song.getVersion());
                value.put(KaraokeContract.KaraokeSongsEntry.COLUMN_REMARKS, song.getRemarks());
                getApplicationContext().getContentResolver()
                        .insert(KaraokeContract.KaraokeSongsEntry.CONTENT_URI, value);
            }
        }
    }
}
