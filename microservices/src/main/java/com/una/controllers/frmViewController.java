package com.una.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class frmViewController {

    @FXML
    private TableView<Client> clientsTable;
    @FXML
    private TableColumn<Client, Integer> clientIdCol;
    @FXML
    private TableColumn<Client, String> clientNameCol;

    @FXML
    private TableView<Purchase> purchasesTable;
    @FXML
    private TableColumn<Purchase, Integer> purchaseIdCol;
    @FXML
    private TableColumn<Purchase, String> purchaseProductCol;
    @FXML
    private TableColumn<Purchase, Double> purchaseAmountCol;

    @FXML
    public void initialize() {
        // Configuración de columnas para clientes
        clientIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        ObservableList<Client> clientsData = FXCollections.observableArrayList(
            new Client(1, "Juan"),
            new Client(2, "Ana"),
            new Client(3, "Luis")
        );
        clientsTable.setItems(clientsData);

        // Configuración de columnas para compras
        purchaseIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        purchaseProductCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        purchaseAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        ObservableList<Purchase> purchasesData = FXCollections.observableArrayList(
            new Purchase(101, "Laptop", 1200.0),
            new Purchase(102, "Mouse", 25.0),
            new Purchase(103, "Keyboard", 45.0)
        );
        purchasesTable.setItems(purchasesData);
    }

    public static class Client {
        private final Integer id;
        private final String name;

        public Client(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() { return id; }
        public String getName() { return name; }
    }

    public static class Purchase {
        private final Integer id;
        private final String product;
        private final Double amount;

        public Purchase(Integer id, String product, Double amount) {
            this.id = id;
            this.product = product;
            this.amount = amount;
        }

        public Integer getId() { return id; }
        public String getProduct() { return product; }
        public Double getAmount() { return amount; }
    }
}
