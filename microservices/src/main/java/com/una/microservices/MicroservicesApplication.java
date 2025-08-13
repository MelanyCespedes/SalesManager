package com.una.microservices;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MicroservicesApplication extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		TableView<Cliente> clientesTable = new TableView<>();
		TableColumn<Cliente, Integer> clienteIdCol = new TableColumn<>("ID");
		clienteIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<Cliente, String> clienteNombreCol = new TableColumn<>("Nombre");
		clienteNombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		clientesTable.getColumns().addAll(clienteIdCol, clienteNombreCol);

		ObservableList<Cliente> clientesData = FXCollections.observableArrayList(
			new Cliente(1, "Juan"),
			new Cliente(2, "Ana"),
			new Cliente(3, "Luis")
		);
		clientesTable.setItems(clientesData);

		TableView<Compra> comprasTable = new TableView<>();
		TableColumn<Compra, Integer> compraIdCol = new TableColumn<>("ID");
		compraIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<Compra, String> compraProductoCol = new TableColumn<>("Producto");
		compraProductoCol.setCellValueFactory(new PropertyValueFactory<>("producto"));
		TableColumn<Compra, Double> compraMontoCol = new TableColumn<>("Monto");
		compraMontoCol.setCellValueFactory(new PropertyValueFactory<>("monto"));
		comprasTable.getColumns().addAll(compraIdCol, compraProductoCol, compraMontoCol);

		ObservableList<Compra> comprasData = FXCollections.observableArrayList(
			new Compra(101, "Laptop", 1200.0),
			new Compra(102, "Mouse", 25.0),
			new Compra(103, "Teclado", 45.0)
		);
		comprasTable.setItems(comprasData);

		HBox root = new HBox(10, clientesTable, comprasTable);
		Scene scene = new Scene(root, 600, 400);

		primaryStage.setTitle("Clientes y Compras");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static class Cliente {
		private final Integer id;
		private final String nombre;

		public Cliente(Integer id, String nombre) {
			this.id = id;
			this.nombre = nombre;
		}

		public Integer getId() { return id; }
		public String getNombre() { return nombre; }
	}

	public static class Compra {
		private final Integer id;
		private final String producto;
		private final Double monto;

		public Compra(Integer id, String producto, Double monto) {
			this.id = id;
			this.producto = producto;
			this.monto = monto;
		}

		public Integer getId() { return id; }
		public String getProducto() { return producto; }
		public Double getMonto() { return monto; }
	}
}

