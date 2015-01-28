/*
 * Copyright (C) 2014 The Android Open Source Project
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
package com.karaokeapp.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Defines table and column names for the weather database.
 */
public class KaraokeContract {

    // The "Content authority" is a name for the entire content provider, similar to the
    // relationship between a domain name and its website.  A convenient string to use for the
    // content authority is the package name for the app, which is guaranteed to be unique on the
    // device.
    public static final String CONTENT_AUTHORITY = "com.karaokeapp.android";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Possible paths (appended to base content URI for possible URI's)
    // For instance, content://com.example.android.sunshine.app/weather/ is a valid path for
    // looking at weather data. content://com.example.android.sunshine.app/givemeroot/ will fail,
    // as the ContentProvider hasn't been given any information on what to do with "givemeroot".
    // At least, let's hope not.  Don't be that dev, reader.  Don't be that dev.
    public static final String PATH_KARAOKE_SONGS = "karaoke";

    /* Inner class that defines the table contents of the karaoke songs table */
    public static final class KaraokeSongsEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_KARAOKE_SONGS).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_KARAOKE_SONGS;

        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_KARAOKE_SONGS;

        // Table name
        public static final String TABLE_NAME = "KARAOKE_SONGS";

        public static final String COLUMN_SONG_ID = "SONG_ID";

        public static final String COLUMN_ARTIST_NAME = "ARTIST_NAME";

        public static final String COLUMN_SONG_NAME = "SONG_NAME";

        public static final String COLUMN_ALBUM_COVER_URL = "ALBUM_COVER_URL";

        public static final String COLUMN_DURATION = "DURATION";

        public static final String COLUMN_LYRICS_URL = "LYRICS_URL";

        public static final String COLUMN_VERSION = "VERSION";

        public static final String COLUMN_REMARKS = "REMARKS";


        public static final String[] KARAOKE_SONGS_COLUMNS = {
                KaraokeSongsEntry.TABLE_NAME + "." + KaraokeSongsEntry._ID,
                KaraokeSongsEntry.COLUMN_SONG_ID,
                KaraokeSongsEntry.COLUMN_ARTIST_NAME,
                KaraokeSongsEntry.COLUMN_SONG_NAME,
                KaraokeSongsEntry.COLUMN_ALBUM_COVER_URL,
                KaraokeSongsEntry.COLUMN_DURATION,
                KaraokeSongsEntry.COLUMN_LYRICS_URL,
                KaraokeSongsEntry.COLUMN_VERSION,
                KaraokeSongsEntry.COLUMN_REMARKS
        };

        public static Uri buildKaraokeSongsUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }
}
