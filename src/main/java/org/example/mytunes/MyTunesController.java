package org.example.mytunes;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MyTunesController {

    @FXML
    private TableColumn<Song, String> artistSongs;

    @FXML
    private TableColumn<Song, String> categorySongs;

    @FXML
    private TableColumn<Playlist, String> namePlaylists;

    @FXML
    private TableColumn<Playlist, Song> nameSongsOnPlaylists;

    @FXML
    private TableColumn<Playlist, Integer> numberSongsOnPlaylists;

    @FXML
    private Button playPauseButten;

    @FXML
    private TableView<Playlist> playlistsTable;

    @FXML
    private TableView<Playlist> songsOnPlsylistTable;

    @FXML
    private TableColumn<Playlist, Song> songsPlaylists;

    @FXML
    private TableView<Song> songsTable;

    @FXML
    private TextField søgTextField;

    @FXML
    private TableColumn<Playlist, String> timePlaylists;

    @FXML
    private TableColumn<Song, String> timeSongs;

    @FXML
    private TableColumn<Song, String> titleSongs;

    private ObservableList<Song> songs = FXCollections.observableArrayList();

    private MediaPlayer mp;

    @FXML
    private Slider volumeSlider;

    //Volume Slider
    public void initialize() {
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                if (volumeSlider.isValueChanging()) {
                    mp.setVolume(volumeSlider.getValue() / 100.0);
                }
            }
        });
    }

    @FXML
    void closeMyTunes(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void deletePlaylist(ActionEvent event) {

    }

    @FXML
    void editPlaylist(ActionEvent event) {

    }

    @FXML
    void newPlaylist(ActionEvent event) {

    }

    @FXML
    void nextSong(ActionEvent event) {

    }


    @FXML
    void playPause(ActionEvent event) {

    }

    @FXML
    void previousSong(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void songDown(ActionEvent event) {

    }

    @FXML
    void songUp(ActionEvent event) {

    }

    @FXML
    void songsDelete(ActionEvent event) {

    }

    @FXML
    void songsEdit(ActionEvent event) {

    }

    @FXML
    void songsNew(ActionEvent event) {

    }

    @FXML
    void songsOnPlayListAdd(ActionEvent event) {

    }

    @FXML
    void songsOnPlaylistDelete(ActionEvent event) {

    }

    protected void updateValues() {
        if (volumeSlider != null) {
            if (!volumeSlider.isValueChanging()) {
                volumeSlider.setValue((int) Math.round(mp.getVolume() * 100));
            }
        }
    }
}