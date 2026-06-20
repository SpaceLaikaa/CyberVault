package com.cybervault.data;

import java.sql.*;

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

    public void addCategory(String categoryName){
        String sql = "INSERT INTO categories(name) VALUES(?)"; //We use VALUES(?) to prevent SQL injection as I learned

        try(Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,categoryName);
            pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
