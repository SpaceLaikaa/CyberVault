package com.cybervault;

import com.cybervault.data.DataBaseManager;

public class Main {
    public void main(String[] args) {
        System.out.println("Cyber Vault Starting...");

        DataBaseManager db = new DataBaseManager();

        db.createNewDatabase();

        System.out.println("Data Base is tested. Active");
    }
}
