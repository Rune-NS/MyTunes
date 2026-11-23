package org.example.mytunes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MyTunesController {

    @FXML
    private TableColumn<?, ?> artistSongs;

    @FXML
    private TableColumn<?, ?> categorySongs;

    @FXML
    private TextField filter;

    @FXML
    private TableColumn<?, ?> namePlaylists;

    @FXML
    private TableColumn<?, ?> nameSongsOnPlaylists;

    @FXML
    private TableColumn<?, ?> numberSongsOnPlaylists;

    @FXML
    private TableView<?> playlistsTable;

    @FXML
    private TableView<?> songsOnPlsylistTable;

    @FXML
    private TableColumn<?, ?> songsPlaylists;

    @FXML
    private TableView<?> songsTable;

    @FXML
    private TableColumn<?, ?> timePlaylists;

    @FXML
    private TableColumn<?, ?> timeSongs;

    @FXML
    private TableColumn<?, ?> titleSongs;

    @FXML
    private Slider volume;

    @FXML
    void deletePlaylist(ActionEvent event) {

    }

    @FXML
    void editPlaylist(ActionEvent event) {

    }

    @FXML
    void filterSearch(ActionEvent event) {

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
    void songDown(ActionEvent event) {

    }

    @FXML
    void songUp(ActionEvent event) {

    }

    @FXML
    void songsClose(ActionEvent event) {

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