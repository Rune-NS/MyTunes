package org.example.mytunes;

import javafx.application.Platform;
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
    private TableColumn<Song, Integer> timeSongs;

    @FXML
    private TableColumn<Song, String> titleSongs;

    @FXML
    private Slider volumeSlider;

    //Tom constructor skal være der!
    public MyTunesController() {
    }

    //Volume slider og media player
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

        //Sørger for at knappen ændrer udseende når man klikker på den, og mens noget musik spiller
        mp.setOnPlaying(() -> playPauseButten.setText("⏸"));
        mp.setOnPaused(() -> playPauseButten.setText("▶"));
        mp.setOnStopped(() -> playPauseButten.setText("▶"));
        mp.setOnEndOfMedia(() -> mp.stop());

        //Binder playlists til playlistsTable
        playlistsTable.setItems(playlists);

        namePlaylists.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        songsPlaylists.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getSongs().size())));
        timePlaylists.setCellValueFactory(data -> new SimpleStringProperty(formatTime(data.getValue().getTotalDuration())));

        //Binder songs til songsTable
        songsTable.setItems(songs);

        titleSongs.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        artistSongs.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getArtist()));
        categorySongs.setCellValueFactory(data ->  new SimpleStringProperty(data.getValue().getCategory()));
        //timePlaylists.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().

    }

    //Ændrer sekunder til minutter og sekunder (m:ss)
    private String formatTime(int seconds) {
        return String.format("%d:%02d", seconds / 60, seconds % 60);
    }

    //Lukker programmet
    @FXML
    void closeMyTunes(ActionEvent event) {
        Platform.exit();
    }

    //Gør at man kan slette en playliste
    @FXML
    void deletePlaylist(ActionEvent event) {
        int selectedIndex = playlistsTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex < 0) {
            new Alert(Alert.AlertType.WARNING, "Select a playlist to delete.").showAndWait();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this playlist?", ButtonType.YES, ButtonType.NO);
        confirm.setHeaderText(null);

        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            playlists.remove(selectedIndex);
        }
    }

    //Gør at man kan ændre på en playlistes navn
    @FXML
    void editPlaylist(ActionEvent event) {
        Playlist selected = playlistsTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a playlist to edit.").showAndWait();
            return;
        }

        Dialog<ButtonType> dialogvindue = new Dialog<>();
        dialogvindue.setTitle("Edit Playlist");
        dialogvindue.setHeaderText("Edit Playlist Name");
        dialogvindue.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        TextField navnefelt = new TextField(selected.getName());
        navnefelt.setPromptText("Name");

        dialogvindue.getDialogPane().setContent(new VBox(10.0, navnefelt));

        boolean done = false;

        while (!done) {
            Platform.runLater(() -> {
                navnefelt.requestFocus();
                navnefelt.selectAll();
            });

            Optional<ButtonType> knap = dialogvindue.showAndWait();

            if (knap.isPresent() && knap.get() == ButtonType.OK) {
                String name = navnefelt.getText().trim();

                if (name.isEmpty()) {
                    new Alert(Alert.AlertType.WARNING, "The playlist must have a name.").showAndWait();
                } else {
                    selected.setName(name);
                    playlistsTable.refresh();
                    done = true;
                }
            } else {
                done = true;
            }
        }
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
        Platform.runLater(navnefelt::requestFocus);
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
        //playPause ligger i initialize
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
            ComboBox<String> CategoryComboBox = new ComboBox<>();
            TextField titelfelt = new TextField();
            TextField artistfelt = new TextField();
            CategoryComboBox.getItems().addAll("Pop", "Rock", "Hip Hop", "Rap", "Jazz", " Blues", "Electronic music", "Classical musik", "Country", "Reggae");
            TextField timefelt = new TextField();
            titelfelt.setText(s1.getTitle());
            artistfelt.setText(s1.getArtist());
            CategoryComboBox.setValue(s1.getCategory());
            timefelt.setPromptText("Time"); //Brug et biblotek som kan hente meta data fra mp3 eller wav filen og så sætte det ind i kolonde for brugeren. Og lav feltet ikke redigerbart.
            //setter feltet til at det ikke kan redigeres af brugeren
            timefelt.setEditable(false);
            FileChooser filechooser = new FileChooser();
            Button b = new Button("Fil");
            b.setOnAction((event1) -> {
                File mappe = new File("Musik");
                if (mappe.isDirectory()) {
                    filechooser.setInitialDirectory(mappe);
                }
                //sætter stifinderen til at vise mp3 og wav filer
                FileChooser.ExtensionFilter lydFilter = new FileChooser.ExtensionFilter("Lydfilter", "*.mp3", "*.wav");
                filechooser.getExtensionFilters().add(lydFilter);

                File fil = filechooser.showOpenDialog(b.getScene().getWindow());
                if (fil != null) {
                    this.valgt = fil;
                }
            });

            dialogvindue.getDialogPane().setContent(new VBox(10.0, titelfelt, artistfelt, CategoryComboBox, b, timefelt));
            Optional<ButtonType> knap = dialogvindue.showAndWait();

            if (knap.isPresent() && knap.get() == ButtonType.OK) {

                s1.setTitle(titelfelt.getText());
                s1.setArtist(artistfelt.getText());
                s1.setCategory(CategoryComboBox.getValue());
                //s1.getMusik();

                songs.remove(s1);
                songsTable.getSortOrder().clear();
                songs.add(0, s1);
                songsTable.getSelectionModel().select(s1);

            }
        }
    }

    @FXML
    void songsNew(ActionEvent event) {
        this.valgt = null;
        Dialog<ButtonType> dialogvindue = new Dialog<>();
        dialogvindue.setTitle("Add or Edit Song");
        dialogvindue.setHeaderText("New Song");
        dialogvindue.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        ComboBox<String> CategoryComboBox = new ComboBox<>();
        TextField titelfelt = new TextField();
        TextField artistfelt = new TextField();
        CategoryComboBox.getItems().addAll("Pop", "Rock", "Hip Hop", "Rap", "Jazz", " Blues", "Electronic music", "Classical musik", "Country", "Reggae");
        TextField timefelt = new TextField();
        titelfelt.setPromptText("Titel");
        artistfelt.setPromptText("Artist");
        CategoryComboBox.setPromptText("Category");
        timefelt.setPromptText("Time"); //Brug et biblotek som kan hente meta data fra mp3 eller wav filen og så sætte det ind i kolonde for brugeren. Og lav feltet ikke redigerbart.
        //setter feltet til at det ikke kan redigeres af brugeren
        timefelt.setEditable(false);
        FileChooser filechooser = new FileChooser();
        Button b = new Button("Fil");
        b.setOnAction((event1) -> {
            File mappe = new File("Musik");
            if (mappe.isDirectory()) {
                filechooser.setInitialDirectory(mappe);
            }
            //sætter stifinderen til at vise mp3 og wav filer
            FileChooser.ExtensionFilter lydFilter = new FileChooser.ExtensionFilter("Lydfilter", "*.mp3", "*.wav");
            filechooser.getExtensionFilters().add(lydFilter);

            File fil = filechooser.showOpenDialog(b.getScene().getWindow());
            if (fil != null) {
                this.valgt = fil;
            }
        });

        dialogvindue.getDialogPane().setContent(new VBox(10.0, titelfelt, artistfelt, CategoryComboBox, b, timefelt));
        Optional<ButtonType> knap = dialogvindue.showAndWait();

        if (knap.isPresent() && knap.get() == ButtonType.OK) {

            if (CategoryComboBox.getValue() == null) {
                System.out.println("Choose a category");
                return;
            }

            try {
                //gør obs på at man skal vælge en fil for at opratte en ny sang.
                if (this.valgt == null) {
                    throw new UgyldigFilException("Choose a sound fil");
                }
                //sørger for at man bliver gjort obs på man skal vælger mp3 eller wav.
                String navn = this.valgt.getName().toLowerCase();
                if (!navn.endsWith(".mp3")&& !navn.endsWith(".wav")) {
                    throw new UgyldigFilException("Fil must be mp3 or wav");
                }
                //laver det som står i timefelt om til en int.
                int time = 10; //skal erstattes af jaudiotagger.
                String musik = this.valgt.getName();
                Song ny = new Song(titelfelt.getText(), artistfelt.getText(), CategoryComboBox.getValue(), time, musik);
                songs.add(0, ny);
            } catch (UgyldigFilException e) {
                visFejl(e.getMessage());
            }
        }
    }

    @FXML
    void songsOnPlayListAdd(ActionEvent event) {

    }

    @FXML
    void songsOnPlaylistDelete(ActionEvent event) {
        //elevliste opgave?
    }

    public void visFejl(String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText(null);
        alert.setContentText(besked);
        alert.showAndWait();
    }
}