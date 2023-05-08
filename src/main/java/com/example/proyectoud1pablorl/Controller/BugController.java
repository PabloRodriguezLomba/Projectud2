package com.example.proyectoud1pablorl.Controller;

import com.example.proyectoud1pablorl.DAO;
import com.example.proyectoud1pablorl.Object.Bug;
import com.example.proyectoud1pablorl.Object.BugItem;
import com.fasterxml.jackson.databind.DeserializationFeature;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.Window;
import org.w3c.dom.Text;

import java.io.File;
import java.net.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BugController implements Initializable {

    ArrayList<Bug> bugArray = new ArrayList<>();
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Connection con;

    private DAO dao = new DAO();
    FileChooser fileChooser = new FileChooser();

    @FXML
    private ComboBox<String> combobug;
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
    @FXML
    private TextField addid;
    @FXML
    private TextField addName;
    @FXML
    private TextField addPrice;
    @FXML
    private TextField addPriceflick;
    @FXML
    private TextField addCatch;
    BugItem[] bug;

    @FXML
    AnchorPane bugpane;
    /**
     * Cambia la escena actual a la de la pagina de inicio
     * @param event
     * @throws IOException
     */
    public void switchToIntro(MouseEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        bugpane.getChildren().setAll(pane);



       // root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
       // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       // scene = new Scene(root);
       // stage.setScene(scene);
       // stage.show();
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
     * inicializa las columnas de la tabla , añade los filtros para el fileChooser y añade los objetos las combobox
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = dao.connect();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"),new FileChooser.ExtensionFilter("Json","*.JSON"));
        id.setCellValueFactory(new PropertyValueFactory<>("i"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Nam"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Pric"));
        priceFlick.setCellValueFactory(new PropertyValueFactory<>("Flick"));
        catchPhrase.setCellValueFactory(new PropertyValueFactory<>("Catch"));
        combobug.getItems().add("id");
        combobug.getItems().add("name");
        combobug.getItems().add("price");
        combobug.getItems().add("priceflick");
        combobug.getItems().add("catch");

    }

    /**
     * Hace una llamada a la api y obtiene un array de todos los bicho despues de eso utiliza el array para escribir en la tabla
     * @param event
     */
    public void getAllBug(ActionEvent event) {

        ArrayList<Bug> bu = dao.getAllBug(con);
                tableBug.getItems().clear();
                tableBug.getItems().addAll(bu);






    }



    public void getBugbyColumn(ActionEvent event) {


        if (combobug.getValue() == null) {

        } else {
            if (combobug.getValue().equals("id")) {
                getBugById();
            } else if (combobug.getValue().equals("name")) {
                getBugbyname();
            } else if (combobug.getValue().equals("price")) {
                getBugByprice();
            } else if (combobug.getValue().equals("priceflick")) {
                getBugBypriceflick();
            } else if (combobug.getValue().equals("catch")) {
                getBugbycatch();
            }
        }
    }

    /**
     * hace una llamada en la api para obtener un bicho utilizando la id que escribes en la textView
     * @param
     */
    public void getBugById() {

            int responseCode;
            int id= 0;
            int ero = 1;


            if (!textForBug.getText().isEmpty()) {
                Pattern pattern = Pattern.compile("[A-z]");
                for (int i = 0; i < textForBug.getText().length();i++) {
                    String text;
                    if (i == textForBug.getText().length() - 1) {
                        text = textForBug.getText().substring(i);
                    } else {
                        text = textForBug.getText().substring(i,i+1);
                    }

                    Matcher matcher = pattern.matcher(text);
                    if (matcher.find()) {
                        ero = 0;
                    }
                }

                if (ero != 1) {
                    id = 0;
                } else {
                    id = Integer.parseInt(textForBug.getText());
                }



            }
                    Bug bus = dao.getBugId(con,id);
                    tableBug.getItems().clear();
                    tableBug.getItems().add(bus);







    }

    public void getBugbyname() {
        String id = "a";
        if (!textForBug.getText().isEmpty()) {
            id = textForBug.getText();
        }
        Bug bug = dao.getBugName(con,id);
        tableBug.getItems().clear();
        tableBug.getItems().add(bug);
    }

    public void getBugByprice() {

        int responseCode;
        int id= 0;
        int ero = 1;


        if (!textForBug.getText().isEmpty()) {
            Pattern pattern = Pattern.compile("[A-z]");
            for (int i = 0; i < textForBug.getText().length();i++) {
                String text;
                if (i == textForBug.getText().length() - 1) {
                    text = textForBug.getText().substring(i);
                } else {
                    text = textForBug.getText().substring(i,i+1);
                }

                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    ero = 0;
                }
            }

            if (ero != 1) {
                id = 0;
            } else {
                id = Integer.parseInt(textForBug.getText());
            }



        }
        ArrayList<Bug> bus = dao.getBugPrice(con,id);
        tableBug.getItems().clear();
        tableBug.getItems().addAll(bus);







    }

    public void getBugbycatch() {
        String id = "a";
        if (!textForBug.getText().isEmpty()) {
            id = textForBug.getText();
        }
        Bug bug = dao.getBugcatch(con,id);
        tableBug.getItems().clear();
        tableBug.getItems().add(bug);
    }

    public void getBugBypriceflick() {

        int responseCode;
        int id= 0;
        int ero = 1;


        if (!textForBug.getText().isEmpty()) {
            Pattern pattern = Pattern.compile("[A-z]");
            for (int i = 0; i < textForBug.getText().length();i++) {
                String text;
                if (i == textForBug.getText().length() - 1) {
                    text = textForBug.getText().substring(i);
                } else {
                    text = textForBug.getText().substring(i,i+1);
                }

                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    ero = 0;
                }
            }

            if (ero != 1) {
                id = 0;
            } else {
                id = Integer.parseInt(textForBug.getText());
            }



        }
        ArrayList<Bug> bus = dao.getBugflick(con,id);
        tableBug.getItems().clear();
        tableBug.getItems().addAll(bus);







    }



    public void addBug() {



        if (addid.getText().isEmpty() || addName.getText().isEmpty() || addPrice.getText().isEmpty() || addPriceflick.getText().isEmpty() || addCatch.getText().isEmpty()) {

        } else {



        int id= 0;
        int ero = 1;

            Pattern pattern = Pattern.compile("[A-z]");
            for (int i = 0; i < addid.getText().length(); i++) {
                String text;
                if (i == addid.getText().length() - 1) {
                    text = addid.getText().substring(i);
                } else {
                    text = addid.getText().substring(i, i + 1);
                }

                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    ero = 0;
                }
            }

            if (ero != 1) {
                id = 0;
            } else {
                id = Integer.parseInt(addid.getText());
            }




        String name = "a";
        name = addName.getText();


        int price= 0;
        ero = 1;


            for (int i = 0; i < addPrice.getText().length(); i++) {
                String text;
                if (i == addPrice.getText().length() - 1) {
                    text = addPrice.getText().substring(i);
                } else {
                    text = addPrice.getText().substring(i, i + 1);
                }

                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    ero = 0;
                }
            }

            if (ero != 1) {
                price = 0;
            } else {
                price = Integer.parseInt(addPrice.getText());
            }




        int priceflick= 0;
        ero = 1;

            for (int i = 0; i < addPriceflick.getText().length(); i++) {
                String text;
                if (i == addPriceflick.getText().length() - 1) {
                    text = addPriceflick.getText().substring(i);
                } else {
                    text = addPriceflick.getText().substring(i, i + 1);
                }

                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    ero = 0;
                }
            }

            if (ero != 1) {
                price = 0;
            } else {
                priceflick = Integer.parseInt(addPriceflick.getText());
            }




        String cath = "";

        cath = addCatch.getText();



    try {
        Bug but = new Bug(id,name,price,priceflick,cath);
        dao.addBug(con,but);
    } catch (Exception e) {

    }
        }
    }




    public void deletebug() {

        if (combobug.getValue() == null) {

        } else {
            if (combobug.getValue().equals("id")) {
                dao.DeleteBugbyId(con,Integer.parseInt(textForBug.getText()));
            } else if (combobug.getValue().equals("name")) {
                dao.DeleteBugbyname(con,textForBug.getText());
            } else if (combobug.getValue().equals("price")) {
                dao.DeleteBugbyPrice(con,Integer.parseInt(textForBug.getText()));
            } else if (combobug.getValue().equals("priceflick")) {
                dao.DeleteBugbyPriceflick(con,Integer.parseInt(textForBug.getText()));
            } else if (combobug.getValue().equals("catch")) {
                dao.DeleteBugbyCatch(con,textForBug.getText());
            }
        }
    }




    /**
     * Utilizando el objeto fileChooser utilizamos un saveDialog donde conseguimos el nombre del documento y su path
     * despues de esto simplemente escribimos en  el documento la informacion que esta en la tabla
     */
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

                       tableBug.getItems();
                       for (int i = 0;i < tableBug.getItems().size();i++) {
                           fil.write(tableBug.getItems().get(i).toString());
                       }

                    }
                } else if (tableBug.getItems().size() == 1) {
                    try (var fil = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()))) {

                        int id = tableBug.getItems().get(0).getI();


                        Bug buss = dao.getBugId(con,id);


                            fil.write(buss.toString());




                    }
                }

            }

        } catch (Exception ox) {

        }

    }



}
