package org.example.mytunes;

import java.util.ArrayList;

public class Playlist {

    private String name;
    private int totalDuration;
    private ArrayList<Song> playlistSongs = new ArrayList<>();

    public Playlist(String name, int totalduration, ArrayList<Song> songs) {
        this.name = name;
        this.totalDuration = totalduration;
        this.playlistSongs = songs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return playlistSongs;
    }

    public int getTotalDuration(Song s) {
        return totalDuration;
    }

    public void addSong(Song s) {
        playlistSongs.add(s);
        totalDuration += s.getDuration();
    }

    public void setSongs(ArrayList<Song> songs) {
        this.playlistSongs = songs;
    }

    public ArrayList<Song> getPlaylistsongs() {
        return playlistSongs;
    }

    public void fjernSong(Song s) {
        playlistSongs.remove(s);
    }
}
