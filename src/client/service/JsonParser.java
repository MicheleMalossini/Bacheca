/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.service;

import client.Entities.Comment;
import client.Entities.Message;
import client.Entities.User;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author MM
 */
public class JsonParser {
    
    public static List<Message> getListMessages(String json){
        List<Message> list=new ArrayList<>();
        JSONObject obj=new JSONObject(json);
        JSONArray arr=obj.getJSONArray("array");
        for(int i=0;i<arr.length();i++){
            Message m=new Message(
                    arr.getJSONObject(i).getInt("ID"),
                    arr.getJSONObject(i).getString("content"),
                    arr.getJSONObject(i).getInt("like"),
                    arr.getJSONObject(i).getString("userID"),
                    null //convertire string date dal server a date
            );
            list.add(m);
        }
        
        return list;
    }
    
    public static List<Comment> getListComments(String json){
        List<Comment> list=new ArrayList<>();
        JSONObject obj=new JSONObject(json);
        JSONArray arr=obj.getJSONArray("array");
        for(int i=0;i<arr.length();i++){
            Comment c=new Comment(
                    arr.getJSONObject(i).getInt("ID"),
                    arr.getJSONObject(i).getString("content"),
                    arr.getJSONObject(i).getInt("like"),
                    arr.getJSONObject(i).getString("userID"),
                    arr.getJSONObject(i).getInt("messageID")
            );
            list.add(c);
        }
        
        return list;
    }
    
    public static User getUserFromJson(String json){
        JSONObject obj=new JSONObject(json);
        User us=new User(
                obj.getString("cod_alfanum"),
                obj.getString("nome"),
                obj.getString("cognome"),
                obj.getString("mail"),
                obj.getString("password")
        );
        return us;
    }
    
}
