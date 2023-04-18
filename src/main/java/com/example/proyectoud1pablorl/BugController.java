package com.example.proyectoud1pablorl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class BugController implements Initializable {

    ArrayList<Bug> bugArray = new ArrayList<>();
    private Stage stage;
    private Scene scene;
    private Parent root;

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
        try {
            int responseCode;
            int ids = 1;
            do {
                URL ur = new URL("http://acnhapi.com/v1/bugs/" + ids);
                HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                ids++;
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
                        Bug bug = objectMapper.readValue(obs, Bug.class);
                        bugArray.add(bug);
                    }
                }
            } while (responseCode == 200);




        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
