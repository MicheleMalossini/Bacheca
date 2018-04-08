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
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MM
 */
public class RequestHandler {
    /**
    * @param i a value that indicate the different type of request
    * @return a list of message
    */ 
    public static List<Message> getMessages (int i){    //i=0 lastday,i=1 lastweek,i=2 lastmonth,i=3 allmessage 
        try {
            List<Message> list =new ArrayList<>();
            TypeToken<List<Message>> listType = new TypeToken<List<Message>>() {};
            Gson gson=new Gson();
            
            
            URL url = new URL("............");//percorso server + i
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
            list = gson.fromJson(sb.toString(), listType.getType());

            conn.disconnect();
            
            return list;

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
     public static List<Comment> getCommentByMessageID (int messageID){
        try {
            List<Comment> list =new ArrayList<>();
            TypeToken<List<Comment>> listType = new TypeToken<List<Comment>>() {};
            Gson gson=new Gson();
            
            
            URL url = new URL("............");//percorso server + messageID
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
            list = gson.fromJson(sb.toString(), listType.getType());

            conn.disconnect();
            
            return list;

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
            Gson gson=new Gson();
            URL url = new URL("............");//percorso server + messageID
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
            User user=gson.fromJson(json, User.class);
            
            return user;
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
    public static User getUserByEmail(String email){
        try{
            Gson gson=new Gson();
            URL url = new URL("............");//percorso server + email
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
            User user=gson.fromJson(json, User.class);
            
            if(user.getEmail().equals(email))
                return user;
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
     /**
      *
      */
     public static void createMessage(int ID,String content, int userID, Date date) {
            Message mess=new Message(ID,content,userID,date);
            Gson gson=new Gson();
            URL url;
            
            try {
            url = new URL("............"); //percorso server
       
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                String json = gson.toJson(mess);

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
     
    public static void createUser(User user){
        Gson gson=new Gson();
        URL url;
        
        try {
            url=new URL("......"); //percorso server
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
    
    public static void createComment( String content, String userID, int messageID, Timestamp date) {
            Comment comment=new Comment(content,userID,messageID,date);
            Gson gson=new Gson();
            URL url;
            
            try {
            url = new URL("............"); //percorso server
       
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
    
    public static void likeMessage(int id){
        URL url;
        
        try {
            url=new URL("  ");//percorso server+id
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
    
    public static void likeComment(int id){
        URL url;
        
        try {
            url=new URL("  ");//percorso server+id
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
    
}