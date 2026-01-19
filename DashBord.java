package org.example.passwordmanager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DashBord {

    public Scene getScene(Stage stage) {

        /* ===== Top Bar ===== */
        Label title = new Label("Password Vault");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button logoutBtn = new Button("Logout");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox topBar = new HBox(10, title, spacer, logoutBtn);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: #f4f4f4;");

        /* ===== Table ===== */
        TableView<Items> table = new TableView<>();
        table.setPlaceholder(new Label("No passwords stored"));

        TableColumn<Items, String> siteCol = new TableColumn<>("Website");
        siteCol.setCellValueFactory(cell -> cell.getValue().websiteProperty());

        TableColumn<Items, String> userCol = new TableColumn<>("Username");
        userCol.setCellValueFactory(cell -> cell.getValue().usernameProperty());

        TableColumn<Items, String> passCol = new TableColumn<>("Password");
        passCol.setCellValueFactory(cell -> cell.getValue().passwordProperty());

        table.getColumns().addAll(siteCol, userCol, passCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        /* ===== Action Buttons ===== */
        Button addBtn = new Button("Add");
        Button editBtn = new Button("Edit");
        Button deleteBtn = new Button("Delete");
        Button copyBtn = new Button("Copy");

        addBtn.setPrefWidth(90);
        editBtn.setPrefWidth(90);
        deleteBtn.setPrefWidth(90);
        copyBtn.setPrefWidth(90);

        VBox actionBar = new VBox(10, addBtn, editBtn, deleteBtn, copyBtn);
        actionBar.setAlignment(Pos.TOP_CENTER);
        actionBar.setPadding(new Insets(10));
        actionBar.setStyle("-fx-background-color: #fafafa;");

        /* ===== Search Bar ===== */
        TextField searchField = new TextField();
        searchField.setPromptText("Search...");
        HBox.setHgrow(searchField, Priority.ALWAYS);
        HBox searchBox = new HBox(searchField);
        searchBox.setPadding(new Insets(10));

        /* ===== Center Layout ===== */
        BorderPane centerPane = new BorderPane();
        centerPane.setTop(searchBox);
        centerPane.setCenter(table);
        centerPane.setRight(actionBar);

        /* ===== Root ===== */
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(centerPane);
        root.setPadding(new Insets(10));

        /* ===== Load Vault ===== */
        Safe_items vault = Session.getVault();
        System.out.println("DEBUG: Getting vault for user: " +
                (Session.isLoggedIn() ? Session.getUser().get_username() : "no user"));
        Safe_storage.loadData(vault);
        System.out.println("DEBUG: Vault loaded with " + vault.getItems().size() + " items");

        // Print all loaded items for debugging
        for (Items item : vault.getItems()) {
            System.out.println("DEBUG: Loaded item - Website: " + item.getWebsite() +
                    ", Username: " + item.getUsername() +
                    ", Password: " + item.getPassword());
        }

        table.setItems(vault.getItems());

        /* ===== Controls ===== */
        new dashboard_controlls(stage, table, vault, logoutBtn, addBtn, deleteBtn, editBtn, copyBtn);

        /* ===== Scene ===== */
        return new Scene(root, 900, 500);
    }
}