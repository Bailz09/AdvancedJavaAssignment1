package com.example.advancedjavaassignment1;

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {

    private static String dbUser = "Kevin200544764";
    private static String password = "y2L7gBDmFo";

    /**
     * jbdc:mysql -> the database driver
     * 127.0.0.1 -> IP address of the database server
     * 3306 -> port number of the database server
     * COMP1011Friday -> name of the database
     */
    private static String connectURL = "jdbc:mysql://172.31.22.43:3306/Kevin200544764";

    /**
     * Get the songs from the database
     * @return An array list of songs
     */
    public static ArrayList<Song> getSongsFromDB(String sortOrder){
        ArrayList<Song> songs = new ArrayList<>();

        //connect to the DB
        //Decided to try and add in a radio button for most and least streamed songs, wish me luck
        String sql = String.format("SELECT track_name, `artist(s)_name`, released_year, streams " +
                "FROM `spotify-2023` " +
                "ORDER BY streams %s " +
                "LIMIT 50", sortOrder);



        //we use a try ...with resources block to ensure the connection, statement and resultSet are automatically closed
        try
                (
                Connection conn = DriverManager.getConnection(connectURL, dbUser, password);
                Statement statement = conn.createStatement();
                ResultSet resultset = statement.executeQuery(sql)
                )
        {
            while (resultset.next())

            {
                String trackName = resultset.getString("track_name");
                String trackArtist = resultset.getString("artist(s)_name");
                int releasedYear = resultset.getInt("released_year");
                int numberOfStreams = resultset.getInt("streams");

                songs.add(new Song(trackName, trackArtist, releasedYear, numberOfStreams));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return songs;
    }
}
