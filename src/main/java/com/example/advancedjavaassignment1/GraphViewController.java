package com.example.advancedjavaassignment1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
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

    /**
     *An on action event for the radio buttons to switch the data from most streamed to least streamed
     * in the data set
     */
    @FXML
    void toggle(ActionEvent event) {
        if(mostStreamedButton.isSelected())
        {
            populateBarChart("DESC"); //for the most streamed songs
        }
        else if (leastStreamedButton.isSelected())
        {
            populateBarChart("ASC");//for the least
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

    /**
     *A method that populates the Bar Chart
     */
    void populateBarChart(String sortOrder){
        // Clear existing data from the chart
        barChart.getData().clear();

        //uses the getSongsFromDB method to get a limited number of songs from the DB
        //stores them in a list
        List<Song> songs = DBUtility.getSongsFromDB(sortOrder, 20);

        //this came in helpful when I had issues fetching the data from the DB
        //turns out some of my validation was too strict
        System.out.println("Number of songs fetched: " + songs.size());

        /**Got Below Code From CHATGPT to dynamically adjust my Y-axis range and tick units
         *
         */
       /* // Step 2: Ascertain the minimum and maximum stream counts
        int minStreams = songs.stream().mapToInt(Song::getStreamCount).min().orElse(0);
        int maxStreams = songs.stream().mapToInt(Song::getStreamCount).max().orElse(0);

        // Step 3: Dynamically adjust your Y-axis range and tick units
        NumberAxis yAxis = (NumberAxis) barChart.getYAxis();
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(minStreams);
        yAxis.setUpperBound(maxStreams);
        yAxis.setTickUnit((maxStreams - minStreams) / 10);
*/

        /**After finding out we are going to be learning about Streams later in the course
         * I decided to try and find a simpler way of finding the min and max values in the
         * Array
         *Adapted From: https://www.geeksforgeeks.org/finding-the-minimum-or-maximum-value-in-java-arraylist/
         */
        //initializes the min and max to the stream count of the first song
        int minStreams = songs.get(0).getStreamCount();
        int maxStreams = songs.get(0).getStreamCount();

        //a for loop that iterates through each song in the list
        //since we set the min and max to the first element in the array list we can start at i=1
        for(int i=1; i < songs.size();i++)
        {
            //set each iteration to a variable
            int streamCount = songs.get(i).getStreamCount();

            //compare the streamCount variable to minStreams and maxStreams to get the updated values
            if(streamCount < minStreams)
            {
                minStreams = streamCount;
            }

            if(streamCount > maxStreams)
            {
                maxStreams = streamCount;
            }
        }

        //set the yAxis variable to the NumberAxis from the barChart
        NumberAxis yAxis = (NumberAxis) barChart.getYAxis();
        yAxis.setAutoRanging(false);//turn off auto Range feature
        yAxis.setLowerBound(minStreams);//set the lower bound of the axis to the minStreams variable
        yAxis.setUpperBound(maxStreams);//set the upper bound to the maxStreams variable
        yAxis.setTickUnit((maxStreams - minStreams) / 10); // This will give us 10 even intervals

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
     * took me a long time to figure this out
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

    @FXML
    void viewTable(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "table-view.fxml");
    }
}
