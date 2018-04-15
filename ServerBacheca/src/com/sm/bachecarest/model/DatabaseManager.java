/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sm.bachecarest.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pollini
 */
public class DatabaseManager {


    private Connection connection;
    private Properties properties;
    private static DatabaseManager instance;

    public DatabaseManager() {
        try {
            // carica le preferenze dell'app dal file app.properties
            properties = new Properties();
            properties.load(new FileInputStream("app.properties"));
            
            // Se presente carica il driver JDBC
            
            if (properties.getProperty("JDBC_DRIVER_CLASS") != null) {
                try {
                    Class.forName(properties.getProperty("JDBC_DRIVER_CLASS"));
                    
                    
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            createConnection();
            
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        try {
            if (instance == null)
                instance = new DatabaseManager();
            
            
            if (instance.connection.isValid(1) == false)
                instance.createConnection();
            
            return instance.connection;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(properties.getProperty("JDBC_DB_URL"));
    }
    
    
    public static ArrayList getMessages(int when) {
        ArrayList<Message> list = new ArrayList();
        
        Connection conn = getConnection();
        
        java.util.Date  dateUtil = new Date();
        java.sql.Date dateSQL = null;
        
        if (when ==0){
            dateSQL = new java.sql.Date(dateUtil.getTime());
        } else if (when == 1){
              
        } else if (when==2){
            
        }
        
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM MESSAGE WHERE date_time >= ?");
            ps.setDate(1, dateSQL);
            
            ResultSet  rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String contenuto = rs.getString("contenuto");
                java.sql.Date date = rs.getDate("date_time");
                int n_like = rs.getInt("n_like");
                String cod_utente = rs.getString("cod_utente");
                
                Message m = new Message(id, contenuto, date, n_like, cod_utente);
                
                list.add(m);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
       
    
    public static ArrayList getMessages() {
        ArrayList<Message> list = new ArrayList();
        
        Connection conn = getConnection();
        
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM MESSAGE");
            
            ResultSet  rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String contenuto = rs.getString("contenuto");
                java.sql.Date date = rs.getDate("date_time");
                int n_like = rs.getInt("n_like");
                String cod_utente = rs.getString("cod_utente");
                
                Message m = new Message(id, contenuto, date, n_like, cod_utente);
                
                list.add(m);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public static ArrayList getMessageComments(int id_mex) {
        ArrayList<Comment> list = new ArrayList();
        
        Connection conn = getConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM commento WHERE id_m = ?");
            ps.setInt(1, id_mex);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String contenuto = rs.getString("contenuto");
                java.util.Date  date = rs.getDate("date_time");
                int n_like = rs.getInt("n_like");
                String cod_utente = rs.getString("cod_utente");
                
                Comment c = new Comment(id, contenuto, date, n_like, id_mex, cod_utente);
               list.add(c);
            }
        } catch(SQLException ex){
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public static ArrayList getUsers() {
        ArrayList<User> list = new ArrayList();
        
        Connection conn = getConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM utente");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String cod = rs.getString("cod_alfanum");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                
                User u = new User(cod, nome, cognome);
                list.add(u);
            }
        } catch(SQLException ex){
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
    }
    
    public static User getUser(String cod) {
        
        Connection conn = getConnection();
        User u = null;
        
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM utente WHERE cod_alfanum = ?");
            ps.setString(1, cod);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String cod_alfanum = rs.getString("cod_alfanum");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                
                u = new User(cod, nome, cognome);
            }
        } catch(SQLException ex){
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return u;
    }
    
    
}
