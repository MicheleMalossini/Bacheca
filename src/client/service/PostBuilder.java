/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.service;

import client.Entities.Comment;
import client.Entities.Message;
import client.Entities.User;
import java.util.List;

/**
 *
 * @author MM
 */
public class PostBuilder {
    /*
    public static String getPost(){
        String post="";
        List<Message> messages=RequestHandler.getMessages(0);
        
        for(Message message : messages){
            User user=RequestHandler.getUserByID(message.getUserID());//non serve passa dal client
            
            post+=wrapper() + "\n" 
                    + user.getName()+user.getSurname() + "\n"
                    + message.getContent() + "\n"
                    + "Like:"+ String.valueOf(message.getLike()) + "\n";
            List<Comment> comments=RequestHandler.getCommentByMessageID(message.getID());
            for(Comment comment:comments){
                User user_=RequestHandler.getUserByID(comment.getUserID());
                post+=user_.getName()+user.getSurname() + " " + comment.getContent() + "\n"
                        + "Like:"+ String.valueOf(message.getLike()) + "\n";
            }
            post+=wrapper();
        }
        
        return post;
    }
    
    public static String wrapper(){
        return "---------------------------------------------------------------------------------------------------------";
    }
*/
    
    
    
}
