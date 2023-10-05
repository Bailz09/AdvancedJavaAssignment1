package com.example.advancedjavaassignment1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableViewController {

    @FXML
    private TableColumn<?, ?> artistsNameColumn;

    @FXML
    private Button graphButton;

    @FXML
    private TableColumn<?, ?> streamsColumn;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> trackNameColumn;

    @FXML
    private TableColumn<?, ?> yearReleasedColumn;

}