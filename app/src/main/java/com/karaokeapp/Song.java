package com.karaokeapp;

import java.io.Serializable;

import android.graphics.drawable.Drawable;

public class Song implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8844833022624096921L;
	String artist;
	String songName;
	String albumCoverUrl;
	String duration;

	String lyrics;
	String id;
	String version;
	String remarks;
	
	public Song (){
		
	}
	public Song(String id, String artist, String songName, String albumCover,
			String duration, String lyrics, String version, String remarks) {
		super();
		this.id = id;
		this.artist = artist;
		this.songName = songName;
		this.albumCoverUrl = albumCover;
		this.duration = duration;
		this.lyrics = lyrics;
		this.version = version;
		this.remarks = remarks;
	}

	public String getAlbumCoverUrl() {
		return albumCoverUrl;
	}
	public void setAlbumCoverUrl(String albumCoverUrl) {
		this.albumCoverUrl = albumCoverUrl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getAlbumPicture() {
		return albumCoverUrl;
	}
	public void setAlbumPicture(String albumCoverUrl) {
		this.albumCoverUrl = albumCoverUrl;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getLyrics() {
		return lyrics;
	}
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	
}
