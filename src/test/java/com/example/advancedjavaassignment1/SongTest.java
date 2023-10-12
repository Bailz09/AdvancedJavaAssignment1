package com.example.advancedjavaassignment1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SongTest {

    private Song newSong;

    //sets up a new instance of Song before each test
    @BeforeEach
    void setUp() {
        newSong = new Song("new Song", "Kevin Bailey", 2023, 100);
    }

    @Test
    void setSongNameValid() {
        newSong.setSongName("A great new song");
        assertEquals("A great new song", newSong.getSongName());
    }

    @Test
    void setSongNameInvalid(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            newSong.setSongName("");
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            newSong.setSongName("ThiswillbeaSongNamethatistooLongForTheAllowedLengthABCDEFGHI3333334fffdsdJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOThiswillbeaSongNamethatistooLongForTheAllowedLengthABCDEFGHI3333334fffdsdJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNO");
        });
    }

    @Test
    void setSongArtistValid() {
        newSong.setSongArtist("Kevin Bailey");
        assertEquals("Kevin Bailey", newSong.getSongArtist());
    }

    @Test
    void setSongArtistInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            newSong.setSongArtist("");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            newSong.setSongName("ThiswillbeaArtistNamethatistooLongForTheAllowedLddddddddddengthThiswillbeaSongNamethatistooLongForTheAllowedLengthABCDEFGHI3333334fffdsdJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNO");
        });
    }

    @Test
    void setYearReleasedValid() {
        newSong.setYearReleased(2000);
        assertEquals(2000, newSong.getYearReleased());
    }

    @Test
    void setYearReleasedInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            newSong.setYearReleased(1899);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            newSong.setYearReleased(2024);
        });
    }

    @Test
    void setStreamCountValid() {
        newSong.setStreamCount(100);
        assertEquals(100, newSong.getStreamCount());
    }

    @Test
    void setStreamCountInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            newSong.setYearReleased(-1);
        });
    }
}