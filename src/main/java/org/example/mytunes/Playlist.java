package org.example.mytunes;

import java.util.ArrayList;

public class Playlist {

    private String name;
    private int totalduration;
    private ArrayList<Song> playlistsongs = new ArrayList<>();

    public Playlist(String name, int totalduration, ArrayList<Song> songs) {
        this.name = name;
        this.totalduration = totalduration;
        this.playlistsongs = songs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return playlistsongs;
    }

    public int getTotalDuration(Song s) {
        return totalduration;
    }

    public void addSong(Song s) {
        playlistsongs.add(s);
        totalduration += s.getDuration();
    }

    public void setSongs(ArrayList<Song> songs) {
        this.playlistsongs = songs;
    }

    public ArrayList<Song> getPlaylistsongs() {
        return playlistsongs;
    }

    public void fjernSong(Song s) {
        playlistsongs.remove(s);
    }
}
