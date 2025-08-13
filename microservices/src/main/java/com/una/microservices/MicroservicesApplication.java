package com.una.microservices;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MicroservicesApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/frmView.fxml"));
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Clientes y Compras");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

