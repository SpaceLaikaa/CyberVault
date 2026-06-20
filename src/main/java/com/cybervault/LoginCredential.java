package com.cybervault;

public class LoginCredential extends VaultItem{
    private String username;
    private String encryptedPassword;

    public LoginCredential(int id, String appName, String url, String encryptedPassword, String username) {
        super(id, appName, url);
        this.encryptedPassword = encryptedPassword;
        this.username = username;
    }

    @Override
    public void displaySummary(){
        System.out.println("Login To --> "+getAppName()+", Username --> "+username);
    }
}
