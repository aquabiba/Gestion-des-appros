package esmat.appro;

import esmat.appro.configurations.ConfigLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {

        launch(args);
    }


    @Override
    public void start(Stage stage) throws IOException{
        @SuppressWarnings("ConstantConditions")
        Parent parent = FXMLLoader.load((getClass().getResource(ConfigLoader.getProperty("login.page"))));
        Scene scene = new Scene(parent);

        stage.setTitle("Gestion-Appro-Appro");
        stage.setScene(scene);
        stage.setResizable(true);
        try {
            @SuppressWarnings("ConstantConditions")
            Image img = new Image((getClass().getResourceAsStream("/fxml/FAR1.png")));
            stage.getIcons().add(img);
        } catch (Exception e) {
            System.out.println(STR."Erreur lors du chargement de l'image : \{e.getMessage()}");
        }
        stage.show();
    }
}