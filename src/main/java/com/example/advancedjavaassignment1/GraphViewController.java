package com.example.advancedjavaassignment1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GraphViewController implements Initializable {

    private ToggleGroup radioButtons;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private RadioButton leastStreamedButton;

    @FXML
    private RadioButton mostStreamedButton;

    @FXML
    void toggle(ActionEvent event) {
        if(mostStreamedButton.isSelected())
        {
            populateBarChart("DESC"); //for the most streamed songs
        }
        else if (leastStreamedButton.isSelected())
        {
            populateBarChart("ASC");
        }

    }


    //originally just had initialize() method, without implementing Initializable, but wanted to try it this way as well
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        radioButtons = new ToggleGroup();//create a new ToggleGroup so only 1 radio can be selected at a time
        mostStreamedButton.setToggleGroup(radioButtons);//add the radio buttons to the toggle group
        leastStreamedButton.setToggleGroup(radioButtons);
        mostStreamedButton.setSelected(true);//default position
        populateBarChart("DESC");
    }

    void populateBarChart(String sortOrder){
        // Clear existing data from the chart
        barChart.getData().clear();

        //retrieve data from database
        List<Song> songs = DBUtility.getSongsFromDB(sortOrder);

        System.out.println("Number of songs fetched: " + songs.size());

        /**Got Below Code From CHATGPT to dynamically adjust my Y-axis range and tick units
         *
         */
        // Step 2: Ascertain the minimum and maximum stream counts
        int minStreams = songs.stream().mapToInt(Song::getStreamCount).min().orElse(0);
        int maxStreams = songs.stream().mapToInt(Song::getStreamCount).max().orElse(0);

        // Step 3: Dynamically adjust your Y-axis range and tick units
        NumberAxis yAxis = (NumberAxis) barChart.getYAxis();
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(minStreams);
        yAxis.setUpperBound(maxStreams);
        yAxis.setTickUnit((maxStreams - minStreams) / 10); // This will give 10 intervals

        //create a data series Where the X-axis values are of type String and Y of type Number
        XYChart.Series<String, Number> songSeries = new XYChart.Series<>();

        //iterate through each Song in the songs list
        for (Song song : songs){

            String shortName = shortSongName(song.getSongName(),20);
            //create a data point for each song for the chart with track name and number of streams
            XYChart.Data<String, Number> songData = new XYChart.Data<>(shortName, song.getStreamCount());
            //add the data point to the series
            songSeries.getData().add(songData);
        }
        //name for the series of data
        songSeries.setName("Songs");
        //then add the series to the bar chart
        barChart.getData().add(songSeries);
    }

    /**
     * A Method to Truncate the Song Name as it was affecting how my Data was displaying on the chart
     */
    public String shortSongName(String songName, int maxLength){
        if(songName.length() > maxLength)
        {
            return songName.substring(0, maxLength) + "..."; //truncate the song name to not overcrowd the chart
        }
        else
        {
            return songName;//return the original name from the DB if it's not too long
        }
    }


}
