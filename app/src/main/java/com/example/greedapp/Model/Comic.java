package com.example.greedapp.Model;

import java.util.List;

public class Comic {
    String ComicID,ComicName,ComicStatus,ComicAuthor,ComicDescription,UserID,AnotherName;
    List<Category> List;
    List<Chap> Chaps;
    String imageView;
    int ComicView;

    public Comic(String comicID, String comicName, String comicStatus, String comicAuthor, String comicDescription, String userID, String anotherName, java.util.List<Category> list, java.util.List<Chap> chaps, String imageView, int comicView) {
        ComicID = comicID;
        ComicName = comicName;
        ComicStatus = comicStatus;
        ComicAuthor = comicAuthor;
        ComicDescription = comicDescription;
        UserID = userID;
        AnotherName = anotherName;
        List = list;
        Chaps = chaps;
        this.imageView = imageView;
        ComicView = comicView;
    }

    public java.util.List<Chap> getChaps() {
        return Chaps;
    }

    public void setChaps(java.util.List<Chap> chaps) {
        Chaps = chaps;
    }


    public Comic() {
    }

    public String getComicID() {
        return ComicID;
    }

    public String getComicName() {
        return ComicName;
    }

    public java.util.List<Category> getList() {
        return List;
    }


    public String getComicStatus() {
        return ComicStatus;
    }

    public int getComicView() {
        return ComicView;
    }

    public void setComicID(String comicID) {
        ComicID = comicID;
    }

    public void setComicName(String comicName) {
        ComicName = comicName;
    }

    public String getComicAuthor() {
        return ComicAuthor;
    }

    public String getAnotherName() {
        return AnotherName;
    }

    public void setAnotherName(String anotherName) {
        AnotherName = anotherName;
    }

    public void setComicAuthor(String comicAuthor) {
        ComicAuthor = comicAuthor;
    }

    public String getComicDescription() {
        return ComicDescription;
    }

    public void setComicDescription(String comicDescription) {
        ComicDescription = comicDescription;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public void setComicStatus(String comicStatus) {
        ComicStatus = comicStatus;
    }

    public void setComicView(int comicView) {
        ComicView = comicView;
    }

    public void setList(java.util.List<Category> list) {
        List = list;
    }
}
