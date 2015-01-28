package com.karaokeapp;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

public class User {
	String userName;
	Drawable userPicture;
	ArrayList<Song> favorites;
	ArrayList<Song> recentSongs;
	String email;
//	int ranking;

	public User(String userName, Drawable userPicture,
			ArrayList<Song> favorites, ArrayList<Song> recentSongs, String email) {
		super();
		this.userName = userName;
		this.userPicture = userPicture;
		this.favorites = favorites;
		this.recentSongs = recentSongs;
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public ArrayList<Song> getFavorites() {
		return favorites;
	}
	public void setFavorites(ArrayList<Song> favorites) {
		this.favorites = favorites;
	}
	public ArrayList<Song> getRecentSongs() {
		return recentSongs;
	}
	public void setRecentSongs(ArrayList<Song> recentSongs) {
		this.recentSongs = recentSongs;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Drawable getUserPicture() {
		return userPicture;
	}
	public void setUserPicture(Drawable userPicture) {
		this.userPicture = userPicture;
	}
	
	
}
