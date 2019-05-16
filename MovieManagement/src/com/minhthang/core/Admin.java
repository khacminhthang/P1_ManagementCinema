/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minhthang.core;

/**
 *
 * @author Minh Thắng
 */
public class Admin {
    private long id;
    private String username;
    private String password;
    private String email;
    private String mobi;

    public Admin(long id, String username, String password, String email, String mobi) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobi = mobi;
    }
    
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getMobi() {
        return mobi;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobi(String mobi) {
        this.mobi = mobi;
    }
    
    
}
