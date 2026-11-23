package org.example.mytunes;

public class Song {

    private String title;
    private String artist;
    private int duration;

    public Song(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() { //skal man i virkeligheden lave sådan nogen her protected??
        return duration;
    }
}
