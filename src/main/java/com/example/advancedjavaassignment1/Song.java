package com.example.advancedjavaassignment1;

import java.time.YearMonth;

public class Song {

    //instance variables
    private String songName, songArtist;
    private int yearReleased;
    private int streamCount;

    //constructor
    public Song(String songName, String songArtist, int yearReleased, int streamCount) {
        setSongArtist(songArtist);
        setSongName(songName);
        setYearReleased(yearReleased);
        setStreamCount(streamCount);
    }

    //getters and setters(for validation)
    public String getSongName() {
        return songName;
    }

    /**
     *A method to validate(restrict) the length of the song name using the validString method
     */
    public void setSongName(String songName) {
        if(validString(songName, 250))
        {
            this.songName = songName;
        }
        else
        {
            throw new IllegalArgumentException("Track Name must be between 1 and 250 characters in length");
        }
    }

    public String getSongArtist() {
        return songArtist;
    }

    /**
     * A method to restrict the length of the song Artist
     */
    public void setSongArtist(String songArtist) {
        if(validString(songArtist, 250))
        {
            this.songArtist = songArtist;
        }
        else
        {
            throw new IllegalArgumentException("Artist Name must be between 1 and 250 characters in length");
        }
    }

    //a method to simplify, streamline, and allow for multiple uses. Tests if a string matches the length requirements
    public boolean validString(String testString, int maxLength)
    {
        testString = testString.trim();
        //originally had (testString.length >= 1) but my test cases didn't like that so went with .isEmpty with success
        return(!testString.isEmpty() && testString.length() <= maxLength);
    }
    public int getYearReleased() {
        return yearReleased;
    }

    /**
     * A Method to unsure the Song was released no earlier than the year 1900
     * Originally used <= 2023, but thought for future uses the built-in date method would be better
     * found @ https://www.delftstack.com/howto/java/current-year-in-java/#use-the-javatimeyearmonth-class-to-get-the-integer-value-of-the-current-year-in-java
     *
     * @param yearReleased
     */
    public void setYearReleased(int yearReleased) {
        if(yearReleased >= 1900 && yearReleased <= YearMonth.now().getYear())
        {
            this.yearReleased = yearReleased;
        }
        else
        {
            throw new IllegalArgumentException("Release Year must be between 1900 and the current year");
        }
    }

    public int getStreamCount() {
        return streamCount;
    }

    /**
     *a method to validate the stream count is not a negative number
     */
    public void setStreamCount(int streamCount) {
        if(streamCount >= 0)
        {
            this.streamCount = streamCount;
        }
        else
        {
            throw new IllegalArgumentException("Stream Count must be a positive value");
        }
    }

    /**
     *A toString method to display each Song
     * I figure this would always be a good method to include
     */
    @Override
    public String toString() {
        return "List Of most Streamed Songs Spotify 2023: \n" +
                "Song: " + songName + "\n" +
                "Artist: " + songArtist + "\n" +
                "Year Released: " + yearReleased + "\n" +
                "Stream Count: " + streamCount;
    }
}
