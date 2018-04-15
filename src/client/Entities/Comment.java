/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Entities;

import java.sql.Date;
import java.sql.Date;

/**
 *
 * @author MM
 */
public class Comment {
    int ID;
    String content;
    int like;
    String userID;
    int messageID;

    public Comment(String content, String userID, int messageID) {
        this.content = content;
        this.like = 0;
        this.userID = userID;
        this.messageID = messageID;
    }

    public Comment(int ID, String content, int like, String userID, int messageID) {
        this.ID = ID;
        this.content = content;
        this.like = like;
        this.userID = userID;
        this.messageID = messageID;
    }
    
    public int getID() {
        return ID;
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

    public int getMessageID() {
        return messageID;
    }

    
    
}