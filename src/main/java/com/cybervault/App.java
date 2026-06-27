package com.cybervault;

import com.cybervault.data.DataBaseManager;
import com.cybervault.model.VaultItem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

public class App extends Application {

    private DataBaseManager dbm = new DataBaseManager();

    private ObservableList<VaultItem> mainList; //Javafx can track this list

    @Override
    public void start(Stage primaryStage){
        mainList = FXCollections.observableArrayList(dbm.getAllItems());

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/cybervault/CyberVault.jpg")));

        TableView<VaultItem> table = new TableView<>();

        TableColumn<VaultItem, String> nameCol = new TableColumn<>("App Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("appName"));

        TableColumn<VaultItem, String> urlCol = new TableColumn<>("URL");
        urlCol.setCellValueFactory(new PropertyValueFactory<>("url"));

        TableColumn<VaultItem, String> usernameCol = new TableColumn<>("User Name");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<VaultItem, String> passwordCol = new TableColumn<>("Password");
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("encryptedPassword"));



        table.getColumns().addAll(nameCol, urlCol, usernameCol, passwordCol);

        table.setItems(mainList);

        TextField appNameInput = new TextField();
        appNameInput.setPromptText("App Name (Ex.: Github)");

        TextField urlInput = new TextField();
        urlInput.setPromptText("URL (Ex.: https://github.com)");

        TextField usernameInput = new TextField();
        usernameInput.setPromptText("User Name");

        TextField passwordInput = new TextField();
        passwordInput.setPromptText("Password");

        Button addButton = new Button("Quick Add (Login)");
        Button refreshButton = new Button("Refresh Data");

        // DATA ADD EVENT

        addButton.setOnAction(event -> {
            String appName = appNameInput.getText();
            String url = urlInput.getText();
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            if (!appName.isEmpty()) {
                dbm.addVaultItem("LOGIN", appName, username, password, null, url, 1);

                mainList.clear();
                mainList.addAll(dbm.getAllItems());

                appNameInput.clear();
                urlInput.clear();
                usernameInput.clear();
                passwordInput.clear();
            }
        });

        // DELETE EVENT

        Button deleteButton = new Button("Delete Selected");

        deleteButton.setOnAction(event -> {
            VaultItem selectedItem = table.getSelectionModel().getSelectedItem();

            if (selectedItem != null) {
                dbm.deleteVaultItem(selectedItem.getId());

                mainList.remove(selectedItem);
            }
        });

        refreshButton.setOnAction(event -> {
            System.out.println("Refreshing...");
            mainList.clear();
            mainList.addAll(dbm.getAllItems());
        });

        HBox inputForm = new HBox(10);
        inputForm.getChildren().addAll(appNameInput, urlInput, usernameInput, passwordInput, addButton);

        HBox actionButtons = new HBox(10);
        actionButtons.getChildren().addAll(refreshButton, deleteButton);

        VBox root = new VBox(15);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(table, inputForm, actionButtons);

        Scene scene = new Scene(root, 850, 500);

        primaryStage.setTitle("CyberVault - Your Password Vault");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
