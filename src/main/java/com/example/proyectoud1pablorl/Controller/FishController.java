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
import javafx.scene.control.ComboBox;
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
    @FXML
    private ComboBox<String> combofish;
    @FXML
    private TextField adId;
    @FXML
    private TextField adName;
    @FXML
    private TextField adshadow;
    @FXML
    private TextField adprice;
    @FXML
    private TextField adcj;
    @FXML
    private TextField adcatch;

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
        combofish.getItems().add("id");
        combofish.getItems().add("name");
        combofish.getItems().add("shadow");
        combofish.getItems().add("Price");
        combofish.getItems().add("pricecj");
        combofish.getItems().add("Catch");
    }


    public void getFishbyColumn() {


        if (combofish.getValue() == null) {

        } else {
            if (combofish.getValue().equals("id")) {
                getFishbyid();
            } else if (combofish.getValue().equals("name")) {
                getfishbyname();
            } else if (combofish.getValue().equals("shadow")) {
                getfishbyshadow();
            } else if (combofish.getValue().equals("Price")) {
                getFishbyPrice();
            } else if (combofish.getValue().equals("pricecj")) {
                getFishbyPricecj();
            } else if (combofish.getValue().equals("Catch")) {
                getfishbyncatch();
            }
        }

    }









    /**
     * Obtine la informacion de un pez desde la api utilizando una id que introduce el usuario
     */
    public void getFishbyid() {

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

    public void getfishbyname() {
        String id = "";
        if(!textfish.getText().isEmpty()) {
            id = textfish.getText();
        }
        Fish fish1 = dao.getFishname(con,id);
        TableFish.getItems().clear();
        TableFish.getItems().add(fish1);
    }

    public void getfishbyshadow() {
        String id = "";
        if(!textfish.getText().isEmpty()) {
            id = textfish.getText();
        }
        ArrayList<Fish> fish1 = dao.getFishshadow(con,id);
        TableFish.getItems().clear();
        TableFish.getItems().addAll(fish1);
    }

    public void getfishbyncatch() {
        String id = "";
        if(!textfish.getText().isEmpty()) {
            id = textfish.getText();
        }
        Fish fish1 = dao.getFishscatch(con,id);
        TableFish.getItems().clear();
        TableFish.getItems().add(fish1);
    }

    public void getFishbyPrice() {

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
        ArrayList<Fish> fis = dao.getFishPrice(con,id);
        TableFish.getItems().clear();
        TableFish.getItems().addAll(fis);







    }

    public void getFishbyPricecj() {

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
        ArrayList<Fish> fis = dao.getFishPricecj(con,id);
        TableFish.getItems().clear();
        TableFish.getItems().addAll(fis);







    }

    public void deleteFish() {
        if (combofish.getValue() == null) {

        } else {
            if (combofish.getValue().equals("id")) {
                dao.DeleteFishbyId(con,Integer.parseInt(textfish.getText()));
            } else if (combofish.getValue().equals("name")) {
                dao.Deletefishbyname(con,textfish.getText());
            } else if (combofish.getValue().equals("shadow")) {
                dao.Deletefishbyshadow(con,textfish.getText());
            } else if (combofish.getValue().equals("Price")) {
                dao.DeletefishbyPrice(con,Integer.parseInt(textfish.getText()));
            } else if (combofish.getValue().equals("pricecj")) {
                dao.DeletefishbyPricecj(con,Integer.parseInt(textfish.getText()));
            } else if (combofish.getValue().equals("Catch")) {
                dao.DeletefishbyCatch(con,textfish.getText());
            }
        }
    }


    public void addFish() {

        if (adId.getText().isEmpty() || adName.getText().isEmpty() || adshadow.getText().isEmpty() || adprice.getText().isEmpty() || adcj.getText().isEmpty() || adcatch.getText().isEmpty()) {


        } else {

            int id = 0;
            int ero = 1;

                Pattern pattern = Pattern.compile("[A-z]");
                for (int i = 0; i < adId.getText().length();i++) {
                    String text;
                    if (i == adId.getText().length() - 1) {
                        text = adId.getText().substring(i);
                    } else {
                        text = adId.getText().substring(i,i+1);
                    }

                    Matcher matcher = pattern.matcher(text);
                    if (matcher.find()) {
                        ero = 0;
                    }
                }

                if (ero != 1) {
                    id = 0;
                } else {
                    id = Integer.parseInt(adId.getText());
                }

                String name;
                name= adName.getText();
                String shadow;
                shadow = adshadow.getText();

            int price = 0;
            ero = 1;


            for (int i = 0; i < adId.getText().length();i++) {
                String text;
                if (i == adId.getText().length() - 1) {
                    text = adId.getText().substring(i);
                } else {
                    text = adId.getText().substring(i,i+1);
                }

                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    ero = 0;
                }
            }

            if (ero != 1) {
                id = 0;
            } else {
                price = Integer.parseInt(adId.getText());
            }

            int pricecj = 0;
            ero = 1;


            for (int i = 0; i < adcj.getText().length();i++) {
                String text;
                if (i == adcj.getText().length() - 1) {
                    text = adcj.getText().substring(i);
                } else {
                    text = adcj.getText().substring(i,i+1);
                }

                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    ero = 0;
                }
            }

            if (ero != 1) {
                id = 0;
            } else {
                pricecj = Integer.parseInt(adcj.getText());
            }

            String Catch;
            Catch = adcatch.getText();

            try {
                Fish fu = new Fish(id,name,shadow,price,pricecj,Catch);
                dao.addFish(con,fu);

            } catch (Exception e) {

            }
        }

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
