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

    private Label welcomeText;
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
    @FXML
    public void initialize() {
        //sætter standard value
        volumeSlider.setValue(25);
        volumeSlider.valueProperty().addListener((obs, oldVal, newValu) ->{
            //cheker om der er en media player og sætter den til listener value.
            if (mp != null) {
                mp.setVolume(volumeSlider.getValue() / 100.0);
            }
        });
    }

    @FXML
    void closeMyTunes(ActionEvent event) {
        //close program
        Platform.exit();
    }

    @FXML
    void deletePlaylist(ActionEvent event) {
        //elevliste opgave
    }

    @FXML
    void editPlaylist(ActionEvent event) {
        //elevliste opgave
    }

    @FXML
    void newPlaylist(ActionEvent event) {
        this.valgt = null;
        Dialog<ButtonType> dialogvindue = new Dialog<>();
        dialogvindue.setTitle("Add or Edit Playlist");
        dialogvindue.setHeaderText("New Playlist");
        dialogvindue.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField navnefelt = new TextField();
        navnefelt.setPromptText("Name");


        dialogvindue.getDialogPane().setContent(new VBox(10.0, navnefelt));
        Optional<ButtonType> knap = dialogvindue.showAndWait();

        //mangler den del som ligger det man skriver i listen med alle playlister.
    }

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
    void nextSong(ActionEvent event) {

    }

    @FXML
    void previousSong(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {
        //elevliste opgave
    }

    @FXML
    void resetFilters(ActionEvent event) {

    }

    @FXML
    void songDown(ActionEvent event) {

    }

    @FXML
    void songUp(ActionEvent event) {

    }

    @FXML
    void songsDelete(ActionEvent event) {
        //elevliste opgave
    }

    @FXML
    void songsEdit(ActionEvent event) {
        //elevliste opgave
    }

    @FXML
    void songsNew(ActionEvent event) {
        this.valgt = null;
        Dialog<ButtonType> dialogvindue = new Dialog<>();
        dialogvindue.setTitle("Add or Edit Song");
        dialogvindue.setHeaderText("New Song");
        dialogvindue.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        ComboBox<Song> CategoryComboBox = new ComboBox<>();
        TextField navnefelt = new TextField();
        TextField artistfelt = new TextField();
        //CategoryComboBox.getItems().addAll(Category); //lav en arrayliste med alle Category's
        TextField timefelt = new TextField();
        navnefelt.setPromptText("Name");
        artistfelt.setPromptText("Artist");
        CategoryComboBox.setPromptText("Category");
        timefelt.setPromptText("Time"); //Hvad skal man angive?? Sekunder (int) eller en String så fx 1:30? Hvis ja, hvordan laver man det så som til noget man kan give programmet? Uanset hvad skal der en try catch på
        FileChooser filechooser = new FileChooser();
        Button b = new Button("Fil");
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

        dialogvindue.getDialogPane().setContent(new VBox(10.0, navnefelt, artistfelt, CategoryComboBox, timefelt, b));
        Optional<ButtonType> knap = dialogvindue.showAndWait();

        //mangler den del som ligger den nye sang i arraylisten med alle de andre.
    }

    @FXML
    void songsOnPlayListAdd(ActionEvent event) {

    }

    @FXML
    void songsOnPlaylistDelete(ActionEvent event) {
        //elevliste opgave?
    }
}