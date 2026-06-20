package com.cybervault.model;

public class LoginCredential extends VaultItem{
    private String username;
    private String encryptedPassword;

    public LoginCredential(int id, String appName, String url, String encryptedPassword, String username) {
        super(id, appName, url);
        this.encryptedPassword = encryptedPassword;
        this.username = username;
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getEncryptedPassword() {return encryptedPassword;}
    public void setEncryptedPassword(String encryptedPassword) {this.encryptedPassword = encryptedPassword;}

    @Override
    public void displaySummary(){
        System.out.println("Login To --> "+getAppName()+", Username --> "+username);
    }
}
