package com.example.proyectoud1pablorl.Controller;

import com.example.proyectoud1pablorl.DAO;
import com.example.proyectoud1pablorl.Object.Fish;
import com.example.proyectoud1pablorl.Object.FishItem;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

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

public class FishController implements Initializable {

    private Connection con;

    private DAO dao = new DAO();
    private Stage stage;
    private Scene scene;
    private Parent root;

    FileChooser fileChooser = new FileChooser();
    @FXML
    private TableView<Fish> TableFish;
    @FXML
    private TableColumn<Fish,Integer> idfish;
    @FXML
    private TableColumn<Fish,String> fishname;
    @FXML
    private TableColumn<Fish,String> fishshadow;
    @FXML
    private TableColumn<Fish,Integer> fishprice;
    @FXML
    private TableColumn<Fish,Integer> fishpricecj;
    @FXML
    private TableColumn<Fish,String> fishcatch;
    @FXML
    private TextField textfish;

    private FishItem[] fish;

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
     * Consigue de la api toda la informacion sobre los peces en un array y la utiliza para escribir en la tabla
     * @param event
     */
    public void getAllFish(ActionEvent event) {


            ArrayList<Fish> fis = dao.getAllFish(con);

            TableFish.getItems().clear();
            TableFish.getItems().addAll(fis);




    }

    /**
     * inicializa las columnas de la tabla asignandoles valor y añade los filtros al fileChooser
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = dao.connect();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"),new FileChooser.ExtensionFilter("Json","*.JSON"));
        idfish.setCellValueFactory(new PropertyValueFactory<>("i"));
        fishname.setCellValueFactory(new PropertyValueFactory<>("Nam"));
        fishshadow.setCellValueFactory(new PropertyValueFactory<>("shado"));
        fishprice.setCellValueFactory(new PropertyValueFactory<>("Pric"));
        fishpricecj.setCellValueFactory(new PropertyValueFactory<>("Priccj"));
        fishcatch.setCellValueFactory(new PropertyValueFactory<>("Catch"));
    }

    /**
     * Obtine la informacion de un pez desde la api utilizando una id que introduce el usuario
     */
    public void getOneFish() {

            int responseCode;
            int id = 0;
            int ero = 1;
            if (!textfish.getText().isEmpty()) {
                Pattern pattern = Pattern.compile("[A-z]");
                for (int i = 0; i < textfish.getText().length();i++) {
                    String text;
                    if (i == textfish.getText().length() - 1) {
                        text = textfish.getText().substring(i);
                    } else {
                        text = textfish.getText().substring(i,i+1);
                    }

                    Matcher matcher = pattern.matcher(text);
                    if (matcher.find()) {
                        ero = 0;
                    }
                }

                if (ero != 1) {
                    id = 0;
                } else {
                    id = Integer.parseInt(textfish.getText());
                }



            }
                    Fish fis = dao.getFishId(con,id);
                    TableFish.getItems().clear();
                    TableFish.getItems().add(fis);







    }

    /**
     * Utilizando el objeto fileChooser utilizamos un saveDialog donde conseguimos el nombre del documento y su path
     * despues de esto simplemente escribimos en  el documento la informacion que esta en la tabla
     */
    public void saveFile(){

        Window stage = TableFish.getScene().getWindow();
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("Fish");

        try {
            File file = fileChooser.showSaveDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
            if (file != null) {

                file.createNewFile();

                if (TableFish.getItems().size() > 1) {


                    try (var fil = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()))) {

                        URL ur = new URL("http://acnhapi.com/v1a/fish/");
                        HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
                        conn.setRequestMethod("GET");
                        conn.connect();
                        Scanner sc = new Scanner(ur.openStream());
                        while (sc.hasNext()) {
                            fil.write(sc.nextLine());
                        }

                    }
                } else if (TableFish.getItems().size() == 1) {
                    try (var fil = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()))) {

                        int id = TableFish.getItems().get(0).getI();
                        URL ur = new URL("http://acnhapi.com/v1/fish/" + id);
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
