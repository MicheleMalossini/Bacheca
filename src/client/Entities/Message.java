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
    int id;
    String contenuto;
    int n_like;
    int cod_utente;
    Date date;

    public Message(int id,String contenuto,int n_like, int cod_utente, Date date) {
        this.id=id;
        this.contenuto = contenuto;
        this.n_like = n_like;
        this.cod_utente = cod_utente;
        this.date = date;
    }
    
    public Message(String contenuto, int cod_utente){
        this.contenuto = contenuto;
        this.cod_utente = cod_utente;
    }


    public String getContent() {
        return contenuto;
    }

    public int getLike() {
        return n_like;
    }

    public int getUserID() {
        return cod_utente;
    }

    public Date getDate() {
        return date;
    }
    
    public int getID() {
        return id;
    }

    public void setContent(String contenuto) {
        this.contenuto = contenuto;
    }

    public void setLike(int n_like) {
        this.n_like = n_like;
    }

    public void setUserID(int cod_utente) {
        this.cod_utente = cod_utente;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
