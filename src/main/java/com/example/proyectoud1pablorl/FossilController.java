package com.example.proyectoud1pablorl;

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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class FossilController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    FileChooser fileChooser = new FileChooser();
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
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"),new FileChooser.ExtensionFilter("Json","*.JSON"));
        FossilName.setCellValueFactory(new PropertyValueFactory<>("Nam"));
        FossilPrice.setCellValueFactory(new PropertyValueFactory<>("pric"));
        FossilMuseum.setCellValueFactory(new PropertyValueFactory<>("Museum"));
    }

    /**
     * Obtiene un array con todos los fosiles de la api y lo utiliza para escribirlos en la tabla
     * @param event
     */
    public void getAllFossil(ActionEvent event){
        try {
            int responseCode;
            ArrayList<Fossil> bu = new ArrayList<>();
            URL ur = new URL("http://acnhapi.com/v1a/fossils/");
            HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                System.out.println("done all fish");
            } else {
                Scanner sc = new Scanner(ur.openStream());
                while (sc.hasNext()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.disable(DeserializationFeature
                            .FAIL_ON_UNKNOWN_PROPERTIES);
                    String obs = sc.nextLine();
                    foss = objectMapper.readValue(obs, FossilItem[].class);


                }
            }


            for (int i = 0; i < foss.length;i++) {


                bu.add(new Fossil(foss[i].getFileName(),foss[i].getPrice(),foss[i].getMuseumPhrase()));


            }
            TableFossil.getItems().clear();
            TableFossil.getItems().addAll(bu);





        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtiene un fosil de la api con un nombre que el usuario introduce despues escribe la informacion en la tabla
     * @param event
     */
    public void getOneFossil(ActionEvent event) {
        try {
            int responseCode;
            String id = "ambe";
            if (!textFossil.getText().isEmpty()) {
                 id = textFossil.getText();
            }

            URL ur = new URL("http://acnhapi.com/v1/fossils/" + id);
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
                    FossilItem bug = objectMapper.readValue(obs, FossilItem.class);
                    TableFossil.getItems().clear();
                    TableFossil.getItems().add(new Fossil(bug.fileName, bug.price,bug.museumPhrase));

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

                        URL ur = new URL("http://acnhapi.com/v1a/fossils/");
                        HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
                        conn.setRequestMethod("GET");
                        conn.connect();
                        Scanner sc = new Scanner(ur.openStream());
                        while (sc.hasNext()) {
                            fil.write(sc.nextLine());
                        }

                    }
                } else if (TableFossil.getItems().size() == 1) {
                    try (var fil = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()))) {

                        String id = TableFossil.getItems().get(0).getNam();
                        URL ur = new URL("http://acnhapi.com/v1/fossils/" + id);
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
