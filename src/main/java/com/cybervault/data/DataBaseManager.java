package com.cybervault.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseManager {
    private static final String URL = "jdbc:sqlite:cybervault.db";

    public Connection connect() throws SQLException{
            return DriverManager.getConnection(URL);
    }

    public void createNewDatabase(){
        String sqlCategories = "CREATE TABLE IF NOT EXISTS categories ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name TEXT NOT NULL UNIQUE"
                + ");";

        String sqlVaultItems = "CREATE TABLE IF NOT EXISTS vault_items ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " app_name TEXT NOT NULL,"
                + " username TEXT,"
                + " encrypted_password TEXT NOT NULL,"
                + " url TEXT,"
                + " category_id INTEGER,"
                + " FOREIGN KEY (category_id) REFERENCES categories(id)"
                + ");";

        try(Connection conn = connect();
            Statement stmt = conn.createStatement()){
            stmt.execute(sqlCategories);
            stmt.execute(sqlVaultItems);

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
