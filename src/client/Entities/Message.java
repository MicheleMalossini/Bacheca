/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Entities;

import java.sql.Date;

/**
 *
 * @author MM
 */
public class Message {
    int ID;
    String content;
    int like;
    String userID;
    Date date;

    public Message(int ID,String content,int like, String userID, Date date) {
        this.ID=ID;
        this.content = content;
        this.like = like;
        this.userID = userID;
        this.date = date;
    }
    
    public Message(String content, String userID){
        this.content = content;
        this.userID = userID;
    }


    public String getContent() {
        return content;
    }

    public int getLike() {
        return like;
    }

    public String getUserID() {
        return userID;
    }

    public Date getDate() {
        return date;
    }
    
    public int getID() {
        return ID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
