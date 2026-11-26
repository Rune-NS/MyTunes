package org.example.mytunes;

public class Song {

    private String title;
    private String artist;
    private String category;
    private int duration;
    private String musik;


    public Song(String title, String artist, String category, int duration, String musik) {
        this.title = title;
        this.artist = artist;
        this.category = category;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setMusik(String musik) {
        this.musik = musik;
    }

    public String getMusik() {
        return musik;
    }
}