package com.example.greedapp.Model;

import java.util.Date;

public class Chap {
    String ChapID,ChapName;
    Date UploadDate;

    public Chap(String chapID, String chapName, Date uploadDate) {
        ChapID = chapID;
        ChapName = chapName;
        UploadDate = uploadDate;
    }

    public String getChapID() {
        return ChapID;
    }

    public void setChapID(String chapID) {
        ChapID = chapID;
    }

    public String getChapName() {
        return ChapName;
    }

    public void setChapName(String chapName) {
        ChapName = chapName;
    }

    public Date getUploadDate() {
        return UploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        UploadDate = uploadDate;
    }
}
