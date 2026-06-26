package com.cybervault;

import com.cybervault.data.DataBaseManager;
import com.cybervault.model.VaultItem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

public class App extends Application {

    private DataBaseManager dbm = new DataBaseManager();

    private ObservableList<VaultItem> mainList; //Javafx can track this list

    @Override
    public void start(Stage primaryStage){
        mainList = FXCollections.observableArrayList(dbm.getAllItems());

        TableView<VaultItem> table = new TableView<>();

        TableColumn<VaultItem, String> nameCol = new TableColumn<>("Cyber Vault");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("appName"));

        TableColumn<VaultItem, String> urlCol = new TableColumn<>("URL");
        urlCol.setCellValueFactory(new PropertyValueFactory<>("url"));

        table.getColumns().addAll(nameCol, urlCol);

        table.setItems(mainList);

        Button refreshButton = new Button("Refresh Data");

        refreshButton.setOnAction(event -> {
            System.out.println("Refreshing...");
            mainList.clear();
            mainList.addAll(dbm.getAllItems());
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(table, refreshButton);

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("CyberVault - Your Password Vault");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
