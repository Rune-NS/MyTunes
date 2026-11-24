package org.example.mytunes;

import java.util.ArrayList;

public class Playlist {

    private String name;
    private int totalduration;
    private ArrayList<Song> songs = new ArrayList<>();

    public Playlist(String name, int totalduration, ArrayList<Song> songs) {
        this.name = name;
        this.totalduration = totalduration;
        this.songs = songs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public int getTotalDuration(Song s) {
        return totalduration;
    }

    public void addSong(Song s) {
        songs.add(s);
        totalduration += s.getDuration();
    }

}
