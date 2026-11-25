package org.example.mytunes;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class MyTunesController {

    private Label welcomeText;
    private MediaPlayer mediaPlayer;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;

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

    @FXML
    private Slider volume;

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
        Media media = new Media(getClass().getResource("Sneaky Snitch.wav").toString());
        mediaPlayer = new MediaPlayer(media);

        playPauseButten.setOnAction(e -> {
            Status status = mediaPlayer.getStatus();

            if (status == Status.UNKNOWN || status == Status.HALTED) {
                return;
            }

            if (status == Status.PAUSED || status == Status.READY || status == Status.STOPPED) {
                if (atEndOfMedia) {
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                    atEndOfMedia = false;
                }
                mediaPlayer.play();
            }
            else {
                mediaPlayer.pause();
            }
        });

        /*if (playPauseButten.getText().equals("▶")) {
            playPauseButten.setText("⏸");

            try {
                Media sound = new Media(String.valueOf(getClass().getResource("sample-3s.mp3")));
                mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.seek(mediaPlayer.getStartTime());
                mediaPlayer.play();

                welcomeText.setText("Now Playing:" + mediaPlayer.getMedia().getSource().toString());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } else if (playPauseButten.getText().equals("⏸")) {
            playPauseButten.setText("▶");
        }*/
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
}