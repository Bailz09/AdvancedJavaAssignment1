package com.example.advancedjavaassignment1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class TableViewController {

    @FXML
    private TableColumn<Song, String> artistsNameColumn;

    @FXML
    private Button graphButton;

    @FXML
    private TableColumn<Song, Integer> streamsColumn;

    @FXML
    private TableView<Song> tableView;

    @FXML
    private TableColumn<Song, String> trackNameColumn;

    @FXML
    private TableColumn<Song, Integer> yearReleasedColumn;

    private ArrayList<Song> allSongs;

    @FXML
    void initialize()
    {
        allSongs = DBUtility.getSongsFromDB("DESC", 2000);
        //configure the tableview columns so they know where to find the correct data from the Song class
        trackNameColumn.setCellValueFactory(new PropertyValueFactory<>("songName"));
        artistsNameColumn.setCellValueFactory(new PropertyValueFactory<>("songArtist"));
        yearReleasedColumn.setCellValueFactory(new PropertyValueFactory<>("yearReleased"));
        streamsColumn.setCellValueFactory(new PropertyValueFactory<>("streamCount"));


        tableView.getItems().addAll(allSongs);
    }

    @FXML
    void viewGraph(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "graph-view.fxml");
    }

}