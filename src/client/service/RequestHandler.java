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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    /**
    * 
    * @return lista di messaggi
    */ 
    public static List<Message> getMessages (){    
        try {
            List<Message> list =new ArrayList<>();
            //TypeToken<List<Message>> listType = new TypeToken<List<Message>>() {};
            Gson gson=new Gson();
            
            
            URL url = new URL("http://192.168.47.3:8888/api/messages");//percorso server 
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
            JsonParser pars=new JsonParser();
            //Object ob = pars.parse(json);
            //JsonObject obj = (JsonObject) ob;
            JsonArray array=(JsonArray)pars.parse(json);
            for(JsonElement e:array){
                list.add(gson.fromJson(e, Message.class));
                
            }
           // list = gson.fromJson(sb.toString(), listType.getType());

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
            
            
            URL url = new URL("http://192.168.47.3:8888/api/messages/" + messageID + "/comments");//percorso server + messageID
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
    public static User getUserByID(String ID){
        try{
            Gson gson=new Gson();
            URL url = new URL("http://192.168.47.3:8888/api/users/" + ID);//percorso server + messageID
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
            //System.out.println(json);
            User user=gson.fromJson(json, User.class);
            
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
     * @param email l'email dell utente 
     * @return l'utente con quella email
     */
    public static User getUserByEmail(String email){
        try{
            Gson gson=new Gson();
            URL url = new URL("http://192.168.47.3:8888/api/user" + email);//percorso server + email
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
      * @param content il contenuto del messaggio
      * @param userID l'id del utente
      * @param date la data in cui è stato scritto
      */
     public static void createMessage(String content, String userID) {
            Message mess=new Message(content,userID);
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
    /**
     * 
     * @param user l'oggetto user che vogliamo madare al server
     */ 
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
    /**
     * 
     * @param content contenuto del commento
     * @param userID id dell'utente che ha scritto il commento
     * @param messageID id del messaggio a cui è riferito il commento
     * @param date data del commento
     */
    public static void createComment( String content, String userID, int messageID, Date date) {
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
    /**
     * 
     * @param id id del messaggio
     */
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
    /**
     * 
     * @param id_c id del commento
     * @param id_m id del messaggio
     */
    public static void likeComment(int id_c,int id_m){
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
