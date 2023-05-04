package com.example.proyectoud1pablorl.Controller;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class HelloController  implements Initializable {

    private Connection con;
    private Stage stage;
    private Scene scene;
    private Parent root;

    FileChooser fileChooser = new FileChooser();

    @FXML
    private AnchorPane pane1,paneOptions,hellopane;




    @FXML
    private ImageView exit,menu;

    /**
     * Crea el menu de la izquieda de la pagina principal
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {






        exit.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });

        pane1.setVisible(false);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5),pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5),paneOptions);
        translateTransition.setByX(-600);
        translateTransition.play();

        menu.setOnMouseClicked(mouseEvent -> {

            pane1.setVisible(true);

            FadeTransition fadeTransition1= new FadeTransition(Duration.seconds(0.5),pane1);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1= new TranslateTransition(Duration.seconds(0.5),paneOptions);
            translateTransition1.setByX(+600);
            translateTransition1.play();
        });

        pane1.setOnMouseClicked(mouseEvent -> {
            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(0.5),pane1);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(actionEvent -> {
                pane1.setVisible(false);
            });

            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(0.5),paneOptions);
            translateTransition1.setByX(-600);
            translateTransition1.play();


        });
    }

    /**
     * Cambia la escena a la de Fish.fxml
     * @param event
     * @throws IOException
     */
    public void switchToFish(MouseEvent event) throws IOException {



        System.out.println(getClass().getResource("Fish.fxml"));
        root = FXMLLoader.load(getClass().getResource("Fish.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cambia la escena a Fossil.fxml
     * @param event
     * @throws IOException
     */
    public void switchToFossil(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Fossil.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cambia la escena a Bug.fxml
     * @param event
     * @throws IOException
     */
    public void switchToBug(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Bug.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}