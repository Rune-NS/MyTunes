package org.example.mytunes;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.beans.property.SimpleStringProperty;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

public class MyTunesController {

    private Label welcomeText;
    private File valgt;
    private MediaPlayer mp;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private final ObservableList<Song> songs = FXCollections.observableArrayList();
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
    private TableColumn<Playlist, String> songsPlaylists;

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
            //tjekker om der er en media player og sætter den til listener value.
            if (mp != null) {
                mp.setVolume(volumeSlider.getValue() / 100.0);
            }
        });

        //Laver en mediaplayer og styrer play/pause
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

        //Binder playlists til playlistsTable
        playlistsTable.setItems(playlists);

        namePlaylists.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        songsPlaylists.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getSongs().size())));
        timePlaylists.setCellValueFactory(data -> new SimpleStringProperty(formatTime(data.getValue().getTotalDuration())));
    }

    //Ændrer sekunder til minutter og sekunder (m:ss)
    private String formatTime(int seconds) {
        return String.format("%d:%02d", seconds / 60, seconds % 60);
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

    }

    @FXML
    void newPlaylist(ActionEvent event) {
        this.valgt = null;
        Dialog<ButtonType> dialogvindue = new Dialog<>();
        dialogvindue.setTitle("Add Playlist");
        dialogvindue.setHeaderText("New Playlist");
        dialogvindue.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField navnefelt = new TextField();
        navnefelt.setPromptText("Name");


        dialogvindue.getDialogPane().setContent(new VBox(10.0, navnefelt));
        Optional<ButtonType> knap = dialogvindue.showAndWait();

        if (knap.isPresent() && knap.get() == ButtonType.OK) {
            String name = navnefelt.getText().trim();

            if (name.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "The playlist must have a name.").showAndWait();
                return;
            }

            Playlist p = new Playlist(name, 0, new ArrayList<>());

            playlists.add(p);
        }
    }

    @FXML
    void playPause(ActionEvent event) {

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
        Song s1 = this.songsTable.getSelectionModel().getSelectedItem();

        if (s1 != null) {
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


                dialogvindue.getDialogPane().setContent(new VBox(10.0, navnefelt, artistfelt, CategoryComboBox, timefelt, b));
                Optional<ButtonType> knap = dialogvindue.showAndWait();

            });
        }
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

        //Mangler den del som ligger den nye sang i arraylisten med alle de andre.
        //Programmet skal kun tage i mod mp3 og wav. Og time må man kun skrive en valid tid fx 1:30.
    }

    @FXML
    void songsOnPlayListAdd(ActionEvent event) {

    }

    @FXML
    void songsOnPlaylistDelete(ActionEvent event) {
        //elevliste opgave?
    }
}