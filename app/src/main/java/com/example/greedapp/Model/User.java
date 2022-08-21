package com.example.greedapp.Model;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.Serializable;

public class User implements Serializable {
    String userID,  Username, Password;
    boolean haveNotification;

    public void setHaveNotification(boolean haveNotification) {
        this.haveNotification = haveNotification;
    }

    public boolean isHaveNotification() {
        return haveNotification;
    }

    public User(String userID, String username, String password) {
        this.userID = userID;
        Username = username;
        Password = password;
    }
    public User( String username, String password) {
        Username = username;
        Password = password;
    }

    public User() {
    }

    ////GET
    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }





    ///SET


    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }




    public boolean isValidPass(){
        return !TextUtils.isEmpty(Password) && Password.length()>=6;
    }
    public boolean isValidUser(){
        return !TextUtils.isEmpty(Username) && Username.length()>=6;
    }
    public boolean CheckRegist(String user, String pass, String confirm, Activity activity)
    {
        if(user.replace(" ","").isEmpty() || pass.replace(" ","").isEmpty() ||
                confirm.replace(" ","").isEmpty()) {
            Toast.makeText(activity, "Empty Information", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!pass.equals(confirm)){
            Toast.makeText(activity, "Wrong Retype", Toast.LENGTH_LONG).show();
            return false;
        }else if (pass.length() < 8) {
            Toast.makeText(activity, "Enough length", Toast.LENGTH_LONG).show();
            return false;
        }else {
            return true;
        }
    }
    public boolean checkSignin(String User,String Pass) {
        if (!User.replace(" ", "").equals("")
                || Pass.replace(" ", "").equals("")) {
            return true;
        }
        else
            return false;
    }
}

