package com.main.entity.response;

public class Song {

    private String name;
    private String userName;
    private int songTime;

    public Song(String name, String userName, int songTime) {
        this.name = name;
        this.userName = userName;
        this.songTime = songTime;
    }

    public Song() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSongTime() {
        return songTime;
    }

    public void setSongTime(int songTime) {
        this.songTime = songTime;
    }
}
