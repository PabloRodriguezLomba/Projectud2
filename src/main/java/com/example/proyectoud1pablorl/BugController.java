package com.example.proyectoud1pablorl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.Window;

import java.io.File;
import java.net.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class BugController implements Initializable {

    ArrayList<Bug> bugArray = new ArrayList<>();
    private Stage stage;
    private Scene scene;
    private Parent root;

    FileChooser fileChooser = new FileChooser();

    @FXML
    private TableView<Bug> tableBug;
    @FXML
    private TableColumn<Bug,Integer> id;
    @FXML
    private TableColumn<Bug,String> Name;
    @FXML
    private TableColumn<Bug,Integer>Price;
    @FXML
    private TableColumn<Bug,Integer>priceFlick;
    @FXML
    private TableColumn<Bug,String> catchPhrase;
    @FXML
    private TextField textForBug;
    BugItem[] bug;

    public void switchToIntro(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void end(MouseEvent event) throws  IOException {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"),new FileChooser.ExtensionFilter("Json","*.JSON"));
        id.setCellValueFactory(new PropertyValueFactory<>("i"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Nam"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Pric"));
        priceFlick.setCellValueFactory(new PropertyValueFactory<>("Flick"));
        catchPhrase.setCellValueFactory(new PropertyValueFactory<>("Catch"));

    }


    public void getAllBug(ActionEvent event) {
        try {
            int responseCode;
                ArrayList<Bug> bu = new ArrayList<>();
                URL ur = new URL("http://acnhapi.com/v1a/bugs/");
                HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                responseCode = conn.getResponseCode();

                if (responseCode != 200) {
                    System.out.println("done all bugs");
                } else {
                    Scanner sc = new Scanner(ur.openStream());
                    while (sc.hasNext()) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.disable(DeserializationFeature
                                .FAIL_ON_UNKNOWN_PROPERTIES);
                        String obs = sc.nextLine();
                        bug = objectMapper.readValue(obs, BugItem[].class);


                    }
                }


                for (int i = 0; i < bug.length;i++) {


                    bu.add(new Bug(bug[i].id,bug[i].fileName,bug[i].price,bug[i].priceFlick,bug[i].catchPhrase));


                }
                tableBug.getItems().clear();
                tableBug.getItems().addAll(bu);





        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void getBugById(ActionEvent event) {
        try {
            int responseCode;
            int id = Integer.parseInt(textForBug.getText());
            URL ur = new URL("http://acnhapi.com/v1/bugs/" + id);
            HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                System.out.println("done all bugs");
            } else {
                Scanner sc = new Scanner(ur.openStream());
                while (sc.hasNext()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                    String obs = sc.nextLine();
                    BugItem bug = objectMapper.readValue(obs, BugItem.class);
                    tableBug.getItems().clear();
                    tableBug.getItems().add(new Bug(bug.id,bug.fileName,bug.price,bug.priceFlick,bug.catchPhrase));

                }
            }





        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFile(){

        Window stage = tableBug.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("Bugs");
        try {
            File file = fileChooser.showSaveDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
            if (file != null) {

                file.createNewFile();

                if (tableBug.getItems().size() > 1) {


                    try (var fil = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()))) {

                        URL ur = new URL("http://acnhapi.com/v1a/bugs/");
                        HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
                        conn.setRequestMethod("GET");
                        conn.connect();
                        Scanner sc = new Scanner(ur.openStream());
                        while (sc.hasNext()) {
                            fil.write(sc.nextLine());
                        }

                    }
                } else if (tableBug.getItems().size() == 1) {
                    try (var fil = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()))) {

                        int id = tableBug.getItems().get(0).getI();
                        URL ur = new URL("http://acnhapi.com/v1/bugs/" + id);
                        HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
                        conn.setRequestMethod("GET");
                        conn.connect();

                        Scanner sc = new Scanner(ur.openStream());
                        while (sc.hasNext()) {

                            fil.write(sc.nextLine());


                        }

                    }
                }

            }

        } catch (Exception ox) {

        }

    }



}
