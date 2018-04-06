/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author MM
 */
public class Comment {
    
    String content;
    int like;
    String userID;
    int messageID;
    Timestamp date;

    public Comment(String content, String userID, int messageID, Timestamp date) {
        
        this.content = content;
        this.like = 0;
        this.userID = userID;
        this.messageID = messageID;
        this.date = date;
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

    public Timestamp getDate() {
        return date;
    }
    
    
}
