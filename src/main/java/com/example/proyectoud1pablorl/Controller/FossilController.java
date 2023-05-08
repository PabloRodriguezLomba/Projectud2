package com.example.proyectoud1pablorl.Controller;

import com.example.proyectoud1pablorl.DAO;
import com.example.proyectoud1pablorl.Object.Bug;
import com.example.proyectoud1pablorl.Object.Fossil;
import com.example.proyectoud1pablorl.Object.FossilItem;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FossilController implements Initializable {

    private Connection con;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private DAO dao = new DAO();

    FileChooser fileChooser = new FileChooser();
    @FXML
    private ComboBox<String> combofossil;
    @FXML
    private TableView<Fossil> TableFossil;
    @FXML
    private TableColumn<Fossil,String> FossilName;
    @FXML
    private TableColumn<Fossil,Integer> FossilPrice;
    @FXML
    private TableColumn<Fossil,String> FossilMuseum;
    @FXML
    private TextField textFossil;
    @FXML
    private TextField adddName;
    @FXML
    private TextField adddPrice;
    @FXML
    private TextField adddMuseum;

    private FossilItem[] foss;

    /**
     * Cambia la escena actual a la de la pagina de inicio
     * @param event
     * @throws IOException
     */
    public void switchToIntro(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cierra la aplicacion
     * @param event
     * @throws IOException
     */
    public void end(MouseEvent event) throws  IOException {
        System.exit(0);
    }

    /**
     * Inicializa las columnas de la tabla y añade los filtras al fileChooser
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = dao.connect();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"),new FileChooser.ExtensionFilter("Json","*.JSON"));
        FossilName.setCellValueFactory(new PropertyValueFactory<>("Nam"));
        FossilPrice.setCellValueFactory(new PropertyValueFactory<>("pric"));
        FossilMuseum.setCellValueFactory(new PropertyValueFactory<>("Museum"));
        combofossil.getItems().add("Name");
        combofossil.getItems().add("Price");
        combofossil.getItems().add("Museum");

    }

    /**
     * Obtiene un array con todos los fosiles de la api y lo utiliza para escribirlos en la tabla
     * @param event
     */
    public void getAllFossil(ActionEvent event){

            ArrayList<Fossil> fossi = dao.getAllFossil(con);
            TableFossil.getItems().clear();
            TableFossil.getItems().addAll(fossi);

    }

    /**
     * Obtiene un fosil de la base de datos con un nombre o precio o museum phrase  que el usuario introduce, despues escribe la informacion en la tabla
     * @param event
     */
    public void getOneFossil(ActionEvent event) {


            String id = "ambe";

        if (combofossil.getValue() == null) {


        } else {
            if (combofossil.getValue().equals("Name")) {
                if (!textFossil.getText().isEmpty()) {
                    id = textFossil.getText();
                }

                Fossil fos = dao.getFossilId(con, id);
                TableFossil.getItems().clear();
                TableFossil.getItems().add(fos);
            } else if (combofossil.getValue().equals("Price")) {
                int price = 0;
                int ero = 1;
                Pattern pattern = Pattern.compile("[A-z]");
                for (int i = 0; i < textFossil.getText().length();i++) {
                    String text;
                    if (i == textFossil.getText().length() - 1) {
                        text = textFossil.getText().substring(i);
                    } else {
                        text = textFossil.getText().substring(i,i+1);
                    }

                    Matcher matcher = pattern.matcher(text);
                    if (matcher.find()) {
                        ero = 0;
                    }
                }

                if (ero != 1) {
                    price = 0;
                } else {
                    price = Integer.parseInt(textFossil.getText());
                }

                ArrayList<Fossil> fos = dao.getFossilPrice(con, price);
                TableFossil.getItems().clear();
                TableFossil.getItems().addAll(fos);

            } else if (combofossil.getValue().equals("Museum")) {
                if (!textFossil.getText().isEmpty()) {
                    id = textFossil.getText();
                }

                Fossil fos = dao.getFossilMuseum(con, id);
                TableFossil.getItems().clear();
                TableFossil.getItems().add(fos);

            }
        }


    }

    /**
     * llama a uno de los metodos dependiendo del value de la combo box , todos los metodos eliminan filas de la base de datos
     */
    public void deletefossil() {
        if (combofossil.getValue() == null) {


        } else {
            if (combofossil.getValue().equals("Name")) {
               dao.Deletefossilbyname(con,textFossil.getText());
            } else if (combofossil.getValue().equals("Price")) {
                dao.Deletefossilbyprice(con,Integer.parseInt(textFossil.getText()));
            } else if (combofossil.getValue().equals("Museum")) {
                dao.DeletefossilbyMuseum(con,textFossil.getText());
            }
        }
    }

    /**
     *  con los datos que introduces en los campos de texto crea un objeto y lo añade a la base de datos utilizando un metodo del dao
     */
    public void addfossil() {
        if(adddName.getText().isEmpty() || adddPrice.getText().isEmpty() || adddMuseum.getText().isEmpty()) {

        } else {
            String id;
            id = adddName.getText();
            String museum;
            museum = adddMuseum.getText();
            int price = 0;
            int ero = 1;
            Pattern pattern = Pattern.compile("[A-z]");
            for (int i = 0; i < adddPrice.getText().length();i++) {
                String text;
                if (i == adddPrice.getText().length() - 1) {
                    text = adddPrice.getText().substring(i);
                } else {
                    text = adddPrice.getText().substring(i,i+1);
                }

                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    ero = 0;
                }
            }

            if (ero != 1) {
                price = 0;
            } else {
                price = Integer.parseInt(adddPrice.getText());
            }
            try{
                Fossil foss = new Fossil(id,price,museum);
                dao.addFossil(con,foss);
            } catch (Exception e) {

            }

        }
    }

    /**
     * Utilizando el objeto fileChooser utilizamos un saveDialog donde conseguimos el nombre del documento y su path
     * despues de esto simplemente escribimos en  el documento la informacion que esta en la tabla
     */
    public void saveFile(){

        Window stage = TableFossil.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("Fossil");
        try {
            File file = fileChooser.showSaveDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
            if (file != null) {

                file.createNewFile();

                if (TableFossil.getItems().size() > 1) {


                    try (var fil = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()))) {

                       TableFossil.getItems();
                        for (int i = 0; i < TableFossil.getItems().size(); i++) {
                            fil.write(TableFossil.getItems().get(i).toString());
                        }

                    }
                } else if (TableFossil.getItems().size() == 1) {
                    try (var fil = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()))) {

                        String id = TableFossil.getItems().get(0).getNam();

                        Fossil fos = dao.getFossilId(con,id);

                            fil.write(fos.toString());




                    }
                }

            }

        } catch (Exception ox) {

        }

    }
}
