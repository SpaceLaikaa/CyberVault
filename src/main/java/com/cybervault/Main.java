package com.cybervault;

import com.cybervault.data.DataBaseManager;
import com.cybervault.model.VaultItem;
import javafx.application.Application;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataBaseManager db = new DataBaseManager();
        db.createNewDatabase();

        Application.launch(App.class, args);
    }
}