package esmat.appro.classController;

import esmat.appro.Database.database;
import esmat.appro.configurations.ConfigLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class loginController implements Serializable {
        @FXML
        public javafx.scene.control.Button btnLogin;
        @FXML
        public ImageView loginimg;
        @FXML
        public  javafx.scene.control.TextField username;
        @FXML public PasswordField passWord;


    static Connection conn;

    static {
        conn = database.getConnection();
        if (conn == null) {
            System.out.println("Erreur : La connexion à la base de données n'a pas été établie.");
        }
    }


static String username1;
    public void Login(ActionEvent event) throws IOException, SQLException {
            Stage stage;
            Parent root ;
            Scene scene ;


            String  userName = username.getText().toLowerCase();
            username1 = userName;
            String Password = passWord.getText();

        String sql = " SELECT * FROM fichiste WHERE nom=? AND mdp=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,userName);
        ps.setString(2,Password);

        ResultSet rs = ps.executeQuery();

            if (rs.next()){
                try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ConfigLoader.getProperty("fichiste.page")));
                root = loader.load();
                fichisteController fichisteCtrl = loader.getController();

                String nomUtilisateur = STR."\{rs.getString("nom")} \{rs.getString("prenom")}";
                String grade = rs.getString("grade");
                // ayoub setSessionInfo est une methode dans fichisteController qui permet d'initier le Label sessionInfo
                fichisteCtrl.setSessionInfo(nomUtilisateur, grade);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("APPRO-APP");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
            else
            {
                showAlert("error", "nom ou mot de passe incorrect", Alert.AlertType.valueOf("ERROR"));

            }

        }

    public static String handleLogin(String inputNom) {
        String query = "SELECT nom, prenom, grade FROM fichiste WHERE nom = ? ";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, inputNom);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Si l'utilisateur est trouvé
                String nomUtilisateur = STR."\{rs.getString("nom")} \{rs.getString("prenom")}";
                String grade = rs.getString("grade");
                String session = STR."""
                    \{grade}
                    \{nomUtilisateur}
                    """;

                System.out.println(STR."Utilisateur connecté : \{nomUtilisateur} avec le grade \{grade}");

                return session;
            } else {

                System.out.println("Nom, prénom ou mot de passe incorrect");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static Serializable onLoginButtonClicked() {

        String nomUtilisateur = handleLogin(username1.toLowerCase());

        if (nomUtilisateur != null) {
            // Connexion réussie
            System.out.println(STR."Bienvenue, \{nomUtilisateur} !");
            // Redirection vers une autre vue ou enregistrement des informations
            return nomUtilisateur;
        } else {
            // Échec de la connexion
            System.out.println("Connexion échouée !");
        }
        return null;
    }

        public void showAlert(String title, String content, Alert.AlertType type) {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setContentText(content);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }


