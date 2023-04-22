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
import javafx.scene.control.Tab;
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

public class FishController implements Initializable {

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

    public void getAllFish(ActionEvent event) {
        try {
            int responseCode;
            ArrayList<Fish> bu = new ArrayList<>();
            URL ur = new URL("http://acnhapi.com/v1a/fish/");
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
                    fish = objectMapper.readValue(obs, FishItem[].class);


                }
            }


            for (int i = 0; i < fish.length;i++) {


                bu.add(new Fish(fish[i].getId(),fish[i].getFileName(),fish[i].getShadow(),fish[i].getPrice(),fish[i].getPriceCj(),fish[i].getCatchPhrase()));


            }
            TableFish.getItems().clear();
            TableFish.getItems().addAll(bu);





        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"),new FileChooser.ExtensionFilter("Json","*.JSON"));
        idfish.setCellValueFactory(new PropertyValueFactory<>("i"));
        fishname.setCellValueFactory(new PropertyValueFactory<>("Nam"));
        fishshadow.setCellValueFactory(new PropertyValueFactory<>("shado"));
        fishprice.setCellValueFactory(new PropertyValueFactory<>("Pric"));
        fishpricecj.setCellValueFactory(new PropertyValueFactory<>("Priccj"));
        fishcatch.setCellValueFactory(new PropertyValueFactory<>("Catch"));
    }

    public void getOneFish() {
        try {
            int responseCode;
            int id = Integer.parseInt(textfish.getText());
            URL ur = new URL("http://acnhapi.com/v1/fish/" + id);
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
                    FishItem bug = objectMapper.readValue(obs, FishItem.class);
                    TableFish.getItems().clear();
                    TableFish.getItems().add(new Fish(bug.id, bug.fileName,bug.shadow, bug.price, bug.priceCj, bug.catchPhrase));

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
