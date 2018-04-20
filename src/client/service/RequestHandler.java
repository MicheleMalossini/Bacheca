/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.service;

import client.Entities.Comment;
import client.Entities.Message;
import client.Entities.User;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Date;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MM
 */
public class RequestHandler {
    
    private static String URL="http://192.168.47.3:8888/api";
    /**
    * 
    * @return lista di messaggi
    */ 
    public static List<Message> getMessages (){    
        try {
            URL url = new URL(URL+"/messages");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                                    + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String json;
            while ((json= br.readLine()) != null) {    
                sb.append(json+"\n");
            }
            br.close();
      
            conn.disconnect();
            
            return client.service.JsonParser.getListMessages(sb.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                
        }
        return null;
    }
    
    /**
     * 
     * @param messageID ID del messaggio di cui vogliamo i commenti
     * @return lista dei commenti del messaggio
     */
     public static List<Comment> getCommentsByMessageID (int messageID){
        try {
            URL url = new URL(URL+"/messages/" + messageID + "/comments");//percorso server + messageID
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                                    + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String json;
            while ((json= br.readLine()) != null) {    
                sb.append(json+"\n");
            }
            br.close();
            conn.disconnect();
            
            return client.service.JsonParser.getListComments(sb.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                
        }
        return null;
    }
     
    /**
     * 
     * @param ID id dell'utente
     * @return l'utente con quel id
     */ 
    public static User getUserByID(int ID){
        try{
            URL url = new URL(URL+"/users/" + ID);//percorso server + messageID
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                                    + conn.getResponseCode());
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String json;
            while ((json= br.readLine()) != null) {    
                sb.append(json+"\n");
            }
            br.close();
            conn.disconnect();
            
            return client.service.JsonParser.getUserFromJson(sb.toString());
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    /**
     * 
     * @param email l'email dell utente 
     * @return l'utente con quella email
     */
    public static User getUserByEmail(String email){
        try{
            URL url = new URL(URL+"/user/" + email);//percorso server + email
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            /*
            if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                                    + conn.getResponseCode());
            }*/
            
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String json;
            while ((json= br.readLine()) != null) {    
                sb.append(json+"\n");
            }
            br.close();
            conn.disconnect();
            
            
            //if(user.getEmail().equals(email))
                return client.service.JsonParser.getUserFromJson(sb.toString());
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
     /**
      * 
      * @param content il contenuto del messaggio
      * @param userID l'id del utente
      * @param date la data in cui è stato scritto
      */
     public static void createMessage(String content, int userID) {
            Message mess=new Message(content,userID);
            Gson gson=new Gson();
            URL url;
            
            try {
                url = new URL(URL+"/messages"); //percorso server
               
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                String json = gson.toJson(mess);

                OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
                os.write(json);
                os.flush();
                

                if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                            throw new RuntimeException("Failed : HTTP error code : "
                                    + conn.getResponseCode());
                }
                
                conn.disconnect();
     
            }   catch (MalformedURLException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException e) {
                e.printStackTrace(); 
             }
         
    }
    /**
     * 
     * @param user l'oggetto user che vogliamo madare al server
     */ 
    public static void createUser(User user){
        Gson gson=new Gson();
        URL url;
        
        try {
            url=new URL(URL+""); //percorso server
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            
            String json = gson.toJson(user);

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + conn.getResponseCode());
            }

            conn.disconnect();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /**
     * 
     * @param content contenuto del commento
     * @param userID id dell'utente che ha scritto il commento
     * @param messageID id del messaggio a cui è riferito il commento
     * @param date data del commento
     */
    public static void createComment( String content, int userID, int messageID) {
            Comment comment=new Comment(content,userID,messageID);
            Gson gson=new Gson();
            URL url;
            
            try {
            url = new URL(URL+"/messages/"+ messageID + "/comments"); //percorso server
       
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                String json = gson.toJson(comment);

                OutputStream os = conn.getOutputStream();
                os.write(json.getBytes());
                os.flush();
                os.close();

                if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                            throw new RuntimeException("Failed : HTTP error code : "
                                    + conn.getResponseCode());
                }
                
                conn.disconnect();
     
            }   catch (MalformedURLException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException e) {
                e.printStackTrace(); 
             }
         
    }
    /**
     * 
     * @param id id del messaggio
     */
    public static void likeMessage(int id){
        URL url;
        
        try {
            url=new URL(URL+"/messages/"+id);//percorso server+id
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                            throw new RuntimeException("Failed : HTTP error code : "
                                    + conn.getResponseCode());
                }
                
            conn.disconnect();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /**
     * 
     * @param id_c id del commento
     * @param id_m id del messaggio
     */
    public static void likeComment(int id_c,int id_m){
        URL url;
        
        try {
            url=new URL(URL+"/messages7"+id_m+"/comments/" + id_c);//percorso server+id
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                            throw new RuntimeException("Failed : HTTP error code : "
                                    + conn.getResponseCode());
                }
                
            conn.disconnect();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /*public static void main(String args[]) {
        List<Message> meeeeee=getMessages();
        //createMessage("ciaoooo", "1");
    }*/
    
}
