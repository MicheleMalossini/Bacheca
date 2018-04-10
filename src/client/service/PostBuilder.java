/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.service;

import client.Entities.Comment;
import client.Entities.Interface.CommentInterface;
import client.Entities.Interface.MessageInterface;
import client.Entities.Message;
import client.Entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author MM
 */
public class PostBuilder {
    
    public static List<JPanel> getPost(){
        List<JPanel> post=new ArrayList<>();
        List<Message> messages=RequestHandler.getMessages(3);
        JPanel panel=new JPanel();
        
        for(Message message : messages){
            MessageInterface mess=new MessageInterface(message);
            post.add(mess);
            List<Comment> comments=RequestHandler.getCommentByMessageID(message.getID());
            for(Comment comment:comments){
                 CommentInterface comm=new CommentInterface(comment);
                 post.add(comm);
            }
            post.add(panel);
        }
        
        return post;
    }
}
