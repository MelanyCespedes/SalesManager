package com.una.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.application.Platform;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

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

        // Obtener clientes desde la API REST
        loadClientsFromApi();

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

    private void loadClientsFromApi() {
        new Thread(() -> {
            try {
                URL url = new URL("http://localhost:8000/clientes");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                ObservableList<Client> clientsData = FXCollections.observableArrayList();
                JSONArray jsonArray = new JSONArray(response.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject cliente = jsonArray.getJSONObject(i);
                    int id = cliente.getInt("id");
                    String name = cliente.getString("nombre");
                    clientsData.add(new Client(id, name));
                }

                Platform.runLater(() -> {
                    clientsTable.setItems(clientsData);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
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
