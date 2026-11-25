package org.example.mytunes;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Optional;

public class MyTunesController {

    private File valgt;
    private MediaPlayer mp;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private ObservableList<Song> songs = FXCollections.observableArrayList();
    private final ObservableList<Playlist> playlists = FXCollections.observableArrayList();



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
    private TableView<Playlist> songsOnPlaylistTable;

    @FXML
    private TableColumn<Playlist, Song> songsPlaylists;

    @FXML
    private Label currentlyPlayingName;

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

    @FXML
    private Slider volumeSlider;

    //Tom constructor skal være der!
    public MyTunesController() {
    }

    //Volume slider
    public MyTunesController(final MediaPlayer mp) {
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
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

    private Label welcomeText;
    private static MediaPlayer mediaPlayer;

    @FXML
    void playPause(ActionEvent event) {
        Media media = new Media(getClass().getResource("sample-3s.mp3").toString());
        mp = new MediaPlayer(media);

        playPauseButten.setOnAction(e -> {
            Status status = mp.getStatus();

            if (status == Status.UNKNOWN || status == Status.HALTED) {
                return;
            }

            if (status == Status.PAUSED || status == Status.READY || status == Status.STOPPED) {
                mp.play();
            }
            else {
                mp.pause();
            }
        });

        mp.setOnPlaying(() -> playPauseButten.setText("⏸"));
        mp.setOnPaused(() -> playPauseButten.setText("▶"));
        mp.setOnStopped(() -> playPauseButten.setText("▶"));

        mp.setOnEndOfMedia(() -> mp.stop());
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
        this.valgt = null;
        Dialog<ButtonType> dialogvindue = new Dialog<>();
        dialogvindue.setTitle("Add or Edit Song");
        dialogvindue.setHeaderText("New Song");
        dialogvindue.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        ComboBox<Song> SongComboBox = new ComboBox<>();
        TextField navnefelt = new TextField();
        TextField artistfelt = new TextField();
        SongComboBox.getItems().addAll(songs);
        navnefelt.setPromptText("Name of song");
        artistfelt.setPromptText("By what artist");
        SongComboBox.setPromptText("Choose song");
        FileChooser filechooser = new FileChooser();
        Button b = new Button("Choose fil");
        b.setOnAction((event1) -> {
            File mappe = new File("Musik");
            if (mappe.isDirectory()) {
                filechooser.setInitialDirectory(mappe);
            }
            File fil = filechooser.showOpenDialog(b.getScene().getWindow());
            if (fil != null) {
                this.valgt = fil;
            }
        });

        dialogvindue.getDialogPane().setContent(new VBox(10.0, SongComboBox, navnefelt, artistfelt, b));
        Optional<ButtonType> knap = dialogvindue.showAndWait();
    }

    @FXML
    void songsOnPlayListAdd(ActionEvent event) {

    }

    @FXML
    void songsOnPlaylistDelete(ActionEvent event) {

    }

    protected void updateValues() {
        if (volumeSlider != null) {
            if (volumeSlider.isValueChanging()) {
                volumeSlider.setValue((int) Math.round(mp.getVolume() * 100));
            }
        }
    }
}