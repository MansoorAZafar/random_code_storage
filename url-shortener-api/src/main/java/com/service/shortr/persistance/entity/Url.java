package com.service.shortr.persistance.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Url implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    private String hash;
    
    @Column(unique = true, nullable = false)    
    private String url;
    
    @Column(nullable = true)    
    private String owner;
    
    public Url() {}
     
    public Url(String uniqueId, String url, String owner) {
        this.hash = uniqueId;
        this.url = url;
        this.owner = owner;
    }

    public String getHash() { return this.hash; }
    public void setHash(String hash) { this.hash = hash; } 


    public String getUrl() { return this.url; }
    public void setUrl(String url) { this.url = url; } 

    public String getOwner() { return this.owner; }
    public void setOwner(String owner) { this.owner = owner; } 

}
