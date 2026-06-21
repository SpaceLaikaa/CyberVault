package com.cybervault.data;

import com.cybervault.model.LoginCredential;
import com.cybervault.model.SecureNote;
import com.cybervault.model.VaultItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                + "item_type TEXT NOT NULL,"
                + " app_name TEXT NOT NULL,"
                + " username TEXT,"
                + " encrypted_password TEXT,"
                + " note_content TEXT,"
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

    public void addVaultItem(String appName, String username, String encryptedPassword, String url, int categoryId){
        String sql = "INSERT INTO vault_items (app_name, username, encrypted_password, url, category_id) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,appName);
            pstmt.setString(2,username);
            pstmt.setString(3,encryptedPassword);
            pstmt.setString(4,url);
            pstmt.setInt(5,categoryId);
            pstmt.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<String> getAllCategories(){
        List<String> categories = new ArrayList<>();

        String sql = "SELECT name FROM categories";

        try(Connection con = connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                categories.add(rs.getString("name"));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return categories;
    }

    public List<VaultItem> getAllItems(){
        List<VaultItem> vaultItems = new ArrayList<>();
        String sql = "SELECT * FROM vault_items";

        try(Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                String type = rs.getString("item_type");

                if(type.equals("LOGIN")){
                    int id = rs.getInt("id");
                    String appName = rs.getString("app_name");
                    String username = rs.getString("username");
                    String password = rs.getString("encrypted_password");
                    String url = rs.getString("url");

                    LoginCredential login = new LoginCredential(id, appName, url, username, password);

                    vaultItems.add(login);


                }
                else if (type.equals("NOTE")){
                    int id = rs.getInt("id");
                    String appName = rs.getString("app_name");
                    String noteContent = rs.getString("note_content");
                    String url = rs.getString("url");

                    SecureNote note = new SecureNote(id, appName, url, noteContent);
                    vaultItems.add(note);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return vaultItems;
    }
}
