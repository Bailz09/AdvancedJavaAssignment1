package com.example.advancedjavaassignment1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GraphViewController implements Initializable {

    @FXML
    private BarChart<String, Number> barChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        populateBarChart();
    }

    void populateBarChart(){
        //retrieve data from database
        List<Song> songs = DBUtility.getSongsFromDB();

        //create a data series Where the X-axis values are of type String and Y of type Number
        XYChart.Series<String, Number> songSeries = new XYChart.Series<>();

        //iterate through each Song in the songs list
        for (Song song : songs){
            //create a data point for each song for the chart with track name and number of streams
            XYChart.Data<String, Number> songData = new XYChart.Data<>(song.getSongName(), song.getStreamCount());
            //add the data point to the series
            songSeries.getData().add(songData);
        }
        //then add the series to the bar chart
        barChart.getData().add(songSeries);
    }

}
