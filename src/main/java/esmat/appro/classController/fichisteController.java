package esmat.appro.classController;

import esmat.appro.Database.database;
import esmat.appro.ExceptionClass.BalanceException;
import esmat.appro.configurations.ComboBoxUtil;
import esmat.appro.configurations.ConfigLoader;
import esmat.appro.model.PDR;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import java.sql.*;
import java.util.ResourceBundle;

import static javafx.scene.control.ComboBox.*;


public class fichisteController  implements Initializable {

    @FXML
    public TextField QTS;
    @FXML
    public ImageView imagePDR;


    @FXML
    public ImageView exitImg;

    @FXML
    public Pane panAjout;
    @FXML
    public Button addPDR;
    @FXML
    public TextField Nomenclature;
    @FXML
    public TextField codeBarre;
    @FXML
    public TextField Designation;
    @FXML
    public Button importImg;
    @FXML
    public ImageView photoPDR;
    @FXML
    public TextField photoURL;


    @FXML
    public Button btnModSupp;

    @FXML
    public Button btnConfig;
    @FXML
    public TextField nomenclatureMS;
    @FXML
    public Button supprimerMS;
    @FXML
    public Pane panMS;
    @FXML
    public Pane panModifier;
    @FXML
    public TextField NomenclatureModif;
    @FXML
    public TextField codeBarreModif;
    @FXML
    public TextField QTSModif;
    @FXML
    public TextField DesignationModif;

    @FXML
    public Pane panMouvement;
    @FXML
    public Pane panRecherche;

    @FXML
    public TextField numeroFolio;

    @FXML
    public ComboBox<String> typeMouvement;

    @FXML
    public TextField searchBar;
    @FXML
    public TextField NomenclatureMvt;
    @FXML
    public TableView<PDR> tablePDR;

    @FXML
    public TableColumn<PDR, String> nomCol;
    @FXML
    public TableColumn<PDR, String> desiCol;
    @FXML
    public TableColumn<PDR, Integer> qteCol;
    @FXML
    public Button btnAjout;
    @FXML
    public Button btnRecherche;
    @FXML
    public Button btnMouvement;
    @FXML
    public Button exitBtn;
    @FXML
    public Button modifierMS;
    @FXML
    public Button enregistrerPDR;
    @FXML
    public Button mouvementAjout;

    @FXML
    public ImageView photoFicheM6;
    @FXML
    public TextField prixUnitaire;
    @FXML
    public DatePicker dateMouvement;
    @FXML
    public TableColumn<PDR, Double> PUcol;
    @FXML
    public ImageView imgLauncher;
    @FXML
    public TableColumn<PDR, Boolean> imgcol;
    @FXML
    public TableColumn<PDR, String> urlcol;
    @FXML
    public AnchorPane Anchore;
    @FXML
    public TextField PUModif;
    @FXML
    public Pane panConfig;
    @FXML
    public CheckBox CBlightblue;
    @FXML
    public CheckBox CBWhite;
    @FXML
    public CheckBox CBBlack;
    @FXML
    public TableColumn<PDR, String> codebarreCol;
    @FXML
    public ComboBox<String> typeVehicule;
    @FXML
    public ComboBox<String> groupe;
    @FXML
    public ComboBox<String> position;
    @FXML
    public TableColumn<PDR, String> typeVCol;
    @FXML
    public TableColumn<PDR, String> groupeCol;
    @FXML
    public TextField qte_mouvement;

    @FXML
    public Button btnstat;


    @FXML public Pane panStat;
    @FXML public Button importImgModif;
    @FXML public TextField photoURL1;
    @FXML public ComboBox<String> groupeModif;
    @FXML public ComboBox<String> positionModif;
    @FXML public ImageView photoPDR2;
    @FXML public ComboBox<String> typeVehiculeModif;
    @FXML public TableView<PDR> tablePDR1;
    @FXML public TableColumn<PDR,String> nomCol1;
    @FXML public TableColumn<PDR,String> desiCol1;
    @FXML public TableColumn<PDR,String> typeVCol1;
    @FXML public TableColumn<PDR, Integer> qteCol1;
    @FXML public TableColumn<PDR,String> groupeCol1;
    @FXML public TableColumn<PDR,Double> PUcol1;
    @FXML public TableColumn<PDR,Boolean> imgcol1;
    @FXML public TableColumn<PDR,String> urlcol1;
    @FXML public TableColumn<PDR,String> codebarreCol1;
    @FXML public ImageView imgLauncher2;
    @FXML public TextField designationMS;
    @FXML public TextField qteMS;
    @FXML public TextField PUMs;
    @FXML public TableColumn<PDR,Boolean> isDeleted;
    @FXML public Text idSession;
    @FXML public TableColumn<PDR,Boolean> isDeleted1;
    private String sessionInfo;
    Connection conn = database.getConnection();
    // Constructeur public sans arguments pour initier sessionInfo

    public fichisteController() {

       sessionInfo = (String) loginController.onLoginButtonClicked();

    }

    public void setSessionInfo(String nomUtilisateur, String grade) {
        // Méthode pour définir les informations de session
            this.sessionInfo = STR."\{grade} (\{nomUtilisateur})";

    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idSession.setText(sessionInfo);
        idSession.setVisible(true);
        try {
            statistics();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        pageInitiale();
        selectionnerPDR(tablePDR1);

        configureImageColumn(imgLauncher,imgcol);
        configureImageColumn(imgLauncher2,imgcol1);
        CBBlack.setOnAction(event -> changeBackgroundColor());
        CBlightblue.setOnAction(event -> changeBackgroundColor());
        CBWhite.setOnAction(event -> changeBackgroundColor());

    }

    public void handleBtnAction(ActionEvent event) throws IOException, SQLException {

        panAjout.setVisible(false);
        panAjout.toBack();
        panMS.setVisible(false);
        panMS.toBack();
        panModifier.setVisible(false);
        panModifier.toBack();
        panMouvement.setVisible(false);
        panMouvement.toBack();
        panRecherche.setVisible(false);
        panRecherche.toBack();
        panConfig.setVisible(false);
        panConfig.toBack();
        panStat.setVisible(false);
        panStat.toBack();


        if (event.getSource() == btnAjout) {
            panAjout.setVisible(true);
            panAjout.toFront();
            System.out.println("the adding button was clicked");
        } else if (event.getSource() == btnModSupp) {
            setTableview2();
            //filtringFunction(tablePDR1);
            panMS.setVisible(true);
            panMS.toFront();
        } else if (event.getSource() == modifierMS) {
            System.out.println("the modifier supprimer button was clicked");
            handleUpDatePDR();
            panModifier.setVisible(true);
            panModifier.toFront();
            panMS.setVisible(false);

        } else if (event.getSource() == btnMouvement) {
            panMouvement.setVisible(true);
            panMouvement.toFront();
            System.out.println("the mouvement button was clicked");
        } else if (event.getSource() == btnRecherche) {
            panRecherche.setVisible(true);
            panRecherche.toFront();
            System.out.println("the recherche button was clicked");
            setTableview();
            filtringFunction(tablePDR);
            tablePDR.refresh();
        } else if (event.getSource() == exitBtn) {
            System.out.println("the exit button was clicked");
            Stage stage;
            Parent root;
            Scene scene;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("APPRO-APP");
            stage.setScene(scene);
            stage.show();
        } else if (event.getSource() == importImg) {
            setImportImg(photoPDR,photoURL);
            panAjout.setVisible(true);
        } else if (event.getSource() == supprimerMS) {
            handleDeletePDR();
            setTableview();
            setTableview2();
            panMS.setVisible(true);
        } else if (event.getSource() == enregistrerPDR) {
            upDatePDR();
            tablePDR.refresh();
            tablePDR1.refresh();
        } else if (event.getSource() == supprimerMS) {
            handleDeletePDR();
           // tablePDR.refresh();
            tablePDR1.refresh();
        } else if (event.getSource() == btnConfig) {
            panConfig.setVisible(true);
        }else if(event.getSource() == btnstat) {
            panStat.setVisible(true);
            panStat.toFront();
            statistics();
        }
        else if (event.getSource() == importImgModif) {
            setImportImg(photoPDR2,photoURL1);
            panModifier.setVisible(true);
        }

    }

    private void selectionnerPDR(TableView<PDR> tableViewPDRs) {
        tableViewPDRs.getSelectionModel().selectedItemProperty().addListener((obs, ancienneSelection, nouvelleSelection) -> {
            if (nouvelleSelection != null) {
                afficherDetailsPDR(nouvelleSelection); // Afficher les détails de la PDR sélectionnée
            }
        });
    }

    private void afficherDetailsPDR(PDR pdr) {
        NomenclatureModif.setText(pdr.getNomenclature());  // Récupérer et afficher la nomenclature
        DesignationModif.setText(pdr.getDesignation());    // Récupérer et afficher la désignation
        QTSModif.setText(String.valueOf(pdr.getQte_stock())); // Récupérer et afficher la quantité en stock
        codeBarreModif.setText(pdr.getCodebarre());
        PUModif.setText(String.valueOf(pdr.getPrix_unitaire()));
        typeVehiculeModif.setValue(String.valueOf(pdr.getTypeVehicule()));
        groupeModif.setValue(String.valueOf(pdr.getGroupe()));
        positionModif.setValue(String.valueOf(pdr.getPosition()));
        photoURL1.setText(pdr.getPhoto_url());

        nomenclatureMS.setText(pdr.getNomenclature());
        designationMS.setText(pdr.getDesignation());
        PUMs.setText(String.valueOf(pdr.getPrix_unitaire()));
        qteMS.setText(String.valueOf(pdr.getQte_stock()));


    }

    private void setTableview() {
        configureTableView(tablePDR, nomCol, desiCol, qteCol, PUcol,isDeleted1, codebarreCol, urlcol, typeVCol, groupeCol);
    }

    private void setTableview2() {
        configureTableView(tablePDR1, nomCol1, desiCol1, qteCol1, PUcol1, isDeleted,codebarreCol1, urlcol1, typeVCol1, groupeCol1);
    }

    private void configureTableView(TableView<PDR> tableView, TableColumn<PDR, String> nomCol, TableColumn<PDR, String> desiCol,
                                    TableColumn<PDR, Integer> qteCol, TableColumn<PDR, Double> PUcol,TableColumn<PDR,Boolean> isDeleted ,TableColumn<PDR, String> codebarreCol,
                                    TableColumn<PDR, String> urlcol, TableColumn<PDR, String> typeVCol, TableColumn<PDR, String> groupeCol) {

        nomCol.setCellValueFactory(new PropertyValueFactory<>("nomenclature"));
        desiCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
        qteCol.setCellValueFactory(new PropertyValueFactory<>("qte_stock"));
        PUcol.setCellValueFactory(new PropertyValueFactory<>("prix_unitaire"));
        isDeleted.setCellValueFactory(new PropertyValueFactory<>("isDeleted"));
        codebarreCol.setCellValueFactory(new PropertyValueFactory<>("codebarre"));
        urlcol.setCellValueFactory(new PropertyValueFactory<>("photo_url"));
        typeVCol.setCellValueFactory(new PropertyValueFactory<>("typeVehicule"));
        groupeCol.setCellValueFactory(new PropertyValueFactory<>("groupe"));

        tableView.setItems(PDR.getPDRlist());
    }//done


    ObservableList<PDR> list = PDR.getPDRlist(); // list utilise dans le filtrage
    public void filtringFunction(TableView<PDR> tablePDR){
        // Set up FilteredList
        FilteredList<PDR> filteredData = new FilteredList<>(list, p -> true);
        tablePDR.setItems(list);

        // Add listener to search field
        searchBar.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                 filteredData.setPredicate(pdr -> {
                // If the search field is empty, display all items.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // Compare name and description of each PDR with the search term.
                if (pdr.getNomenclature().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches name.
                } else if (pdr.getDesignation().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches description.
                } else return pdr.getCodebarre().toLowerCase().contains(lowerCaseFilter);
// Does not match.
            });
        });

        SortedList<PDR> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tablePDR.comparatorProperty());

        tablePDR.setItems(sortedData);


    } //   done

    private void configureImageColumn(ImageView imgLauncher, TableColumn<PDR,Boolean> imgcol) {
        Callback<TableColumn<PDR, Boolean>, TableCell<PDR, Boolean>> cellFactory =
                new Callback<TableColumn<PDR, Boolean>, TableCell<PDR, Boolean>>() {
                    @Override
                    public TableCell<PDR, Boolean> call(final TableColumn<PDR, Boolean> param) {

                        return new TableCell<PDR, Boolean>() {

                            private final Button btn = new Button("Voir Image");

                            {
                                btn.setOnAction((ActionEvent event) -> {
                                    System.out.println("boutton voir image was clicked");
                                    //
                                    PDR pdr = getTableView().getItems().get(getIndex());
                                    try {


                                            Image image = new Image(pdr.getPhoto_url());
                                            imgLauncher.setImage(image);
                                            imgLauncher.setVisible(true);
                                            imgLauncher.toFront();



                                        } catch (IllegalArgumentException e) {
                                            e.printStackTrace();
                                        }
                                        // determiner une durée d'affichage!!!

                                        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event1 -> {
                                            imgLauncher.setVisible(false);
                                        }));
                                        timeline.setCycleCount(1);
                                        timeline.play();
                                    
                                });

                            }

                            @Override
                            public void updateItem(Boolean item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(btn);
                                }
                            }
                        };
                    }
                };
        imgcol.setCellFactory(cellFactory);
    }//done

    public void pageInitiale() {
        typeMouvement.setItems(FXCollections.observableArrayList("Entrée", "Sortie"));
        initializeLists();
        panAjout.setVisible(false);
        panMS.setVisible(false);
        panModifier.setVisible(false);
        panMouvement.setVisible(false);
        panRecherche.setVisible(false);
        panStat.setVisible(false);

    }//done

    public void initializeLists() {
        setComboBoxItems(position, ComboBoxUtil.getPositions());
        setComboBoxItems(groupe, ComboBoxUtil.getGroupes());
        setComboBoxItems(typeVehicule, ComboBoxUtil.getTypesVehicules());

        setComboBoxItems(positionModif, ComboBoxUtil.getPositions());
        setComboBoxItems(groupeModif, ComboBoxUtil.getGroupes());
        setComboBoxItems(typeVehiculeModif, ComboBoxUtil.getTypesVehicules());
//        setComboBoxItems(position, Constants.POSITIONS);
//        setComboBoxItems(groupe, Constants.GROUPES);
//        setComboBoxItems(typeVehicule, Constants.TYPES_VEHICULES);
//
//        setComboBoxItems(positionModif, Constants.POSITIONS);
//        setComboBoxItems(groupeModif, Constants.GROUPES);
//        setComboBoxItems(typeVehiculeModif, Constants.TYPES_VEHICULES);
    }// done

    private void setComboBoxItems(ComboBox<String> comboBox, ObservableList<String> items) {
        comboBox.setItems(items);
    }// done

    public void statistics() throws SQLException {
        // 1. Set up the CategoryAxis for X-Axis and NumberAxis for Y-Axis
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Date");
        yAxis.setLabel("Quantité");

        // 2. Create the LineChart with String as X-axis (Date formatted) and Number as Y-axis
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Statistiques des Mouvements");
        XYChart.Series<String, Number> seriesE = new XYChart.Series<>();
        seriesE.setName("Entrée");
        XYChart.Series<String, Number> seriesS = new XYChart.Series<>();
        seriesS.setName("Sortie");

        // 3. Retrieve data from the database
        String query1 = "SELECT date_mouvement, count(*) as quantite_totaleE FROM mouvement WHERE type_m ='Entrée' GROUP BY date_mouvement ORDER BY date_mouvement";
        String query2 = "SELECT date_mouvement, count(*) as quantite_totaleS FROM mouvement WHERE type_m ='Sortie' GROUP BY date_mouvement ORDER BY date_mouvement";

        PreparedStatement pE =null ;
        PreparedStatement pS =null ;
        try {

             pE = conn.prepareStatement(query1);
             ResultSet rE = pE.executeQuery();
            // Process data for "Entrée"
            while (rE.next()) {
                String date = rE.getDate("date_mouvement").toString(); // Convert Date to String
                Number quantiteTotale = rE.getInt("quantite_totaleE");
                seriesE.getData().add(new XYChart.Data<>(date, quantiteTotale));
            }


            pS = conn.prepareStatement(query2);
            ResultSet rS = pS.executeQuery();
            // Process data for "Sortie"
            while (rS.next()) {
                String date = rS.getDate("date_mouvement").toString(); // Convert Date to String
                Number quantiteTotale = rS.getInt("quantite_totaleS");
                seriesS.getData().add(new XYChart.Data<>(date, quantiteTotale));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 5. Add Series to the LineChart
        lineChart.getData().add(seriesE);
        lineChart.getData().add(seriesS);

        // 6. Display the chart in your UI
        // Assuming you have a layout in your FXML to hold the chart, for example, a VBox or BorderPane
        panStat.getChildren().add(lineChart);


    }//done

//    public void importerImage() {
//
//        FileChooser filechooser = new FileChooser();
//
//        filechooser.setTitle("choisissez une image");
//
//        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter
//                ("images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
//
//        File file = filechooser.showOpenDialog(new Stage());
//
//
//
//                String URL = file.toURI().toString();
//                Image image = new Image(URL);
//                photoPDR.setImage(image);
//                photoURL.setText(URL);
//
//
//        }

    public void setImportImg(ImageView photoPDR, TextField photoURL){
          FileChooser filechooser = new FileChooser();

         filechooser.setTitle("choisissez une image");

             filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter
            ("images", "*.png", "*.jpg", "*.jpeg", "*.gif"));

          File file = filechooser.showOpenDialog(new Stage());



          String URL = file.toURI().toString();
          Image image = new Image(URL);
          photoPDR.setImage(image);
          photoURL.setText(URL);
}
//    public void importerImage2() {
//
//        FileChooser filechooser = new FileChooser();
//
//        filechooser.setTitle("choisissez une image");
//
//        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter
//                ("images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
//
//        File file = filechooser.showOpenDialog(new Stage());
//        String URL = file.toURI().toString();
//        Image image = new Image(URL);
//        photoPDR2.setImage(image);
//        photoURL1.setText(URL);
//    } // done

    public void ajouter(String N, String D, String C, String U, int Q, Double P, String POS, String T, String G) throws SQLException {

        String sql = "INSERT INTO PDR (nomenclature, designation, codeBarre, photo_url, qte_stock,prix_unitaire,position,typevehicule,groupe) VALUES (?, ?, ?,?,?, ?, ?, ?,?)";

        PreparedStatement pstmt=null;
        try {

                 pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, N);
            pstmt.setString(2, D);
            pstmt.setString(3, C);
            pstmt.setString(4, U);
            pstmt.setInt(5, Q);
            pstmt.setDouble(6, P);
            pstmt.setString(7, POS);
            pstmt.setString(8, T);
            pstmt.setString(9, G);


            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                showAlert("ajout", "la pièce de rechange à été ajoutée avec succès",
                        Alert.AlertType.valueOf("INFORMATION"));
                System.out.println("A new PDR was inserted successfully!");

            } else {
                showAlert("error", "la PDR n'est pas ajoutée correctement à la base de données!! ",
                        Alert.AlertType.valueOf("ERROR"));
                System.out.println("Error inserting PDR");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt!=null) pstmt.close();
            if(conn != null) conn.close();

        }

    } //done

    public void ajouterPDR(ActionEvent event) throws SQLException, IOException {

            String N = Nomenclature.getText();
            String D = Designation.getText();
            String C = codeBarre.getText();
            String U = photoURL.getText();

            String T = typeVehicule.getValue();
            String G = groupe.getValue();
            String POS = position.getValue();

            if (N.isEmpty() || U.isEmpty() || D.isEmpty() || C.isEmpty() || G.isEmpty() || T.isEmpty()   || POS.isEmpty()) {
                showAlert("error", "champs obligatoires",
                        Alert.AlertType.valueOf("ERROR"));
            }

            if (existancePDR(N)) {
                showAlert("erreur d'ajout", "cette Nomenclature existe dèjà",
                        Alert.AlertType.valueOf("ERROR"));
            }
            String QS = QTS.getText().trim();
            String ps = prixUnitaire.getText();

            try {  int Q = Integer.parseInt(QS);
                Double P = Double.parseDouble(ps);
            ajouter(N, D, C, U, Q, P, POS, T, G);

        } catch (NumberFormatException ee) {
            showAlert("error", "champs Quantité obligatoires",
                    Alert.AlertType.valueOf("ERROR"));
        } catch (NullPointerException e) {
            showAlert("error", "tous les champs sont obligatoires",
                    Alert.AlertType.valueOf("ERROR"));
        } finally {
            Nomenclature.setText("");
            Designation.setText("");
            codeBarre.setText("");
            photoURL.setText("");
            prixUnitaire.setText("");
            photoPDR.setVisible(false);
            QTS.setText("");
        }

    }// done


    public void supprimerPDR() throws SQLException, IOException {

        String N = nomenclatureMS.getText();
        String D = designationMS.getText();
        int S = Integer.parseInt(qteMS.getText());
        String session = sessionInfo;
        boolean exist = existancePDR(N);
        if (!exist) {
            showAlert("error", "cette piece de rechange n'existe pas", Alert.AlertType.valueOf("ERROR"));
            panMS.setVisible(true);
            return ;
        }
        String type="supression";
        String sql = "UPDATE pdr SET isDeleted = TRUE WHERE nomenclature = ? ";

        PreparedStatement ps =null;
        PreparedStatement ps2 =null;
 try {

     ps = conn.prepareStatement(sql);

    ps.setString(1, N);


    int rowDeleted = ps.executeUpdate();
    if (rowDeleted > 0) {
        showAlert("supression", "la PDR est supprimée avec succès",
                Alert.AlertType.valueOf("INFORMATION"));
        System.out.println(STR."la PDR dont la Nomenclature \{N} est suprimée avec succès");
    }

    upDateHistoriqueUpdate(conn,ps2,N,D,S,session,type);

}catch (SQLException e){
    e.printStackTrace();
}  finally {
    if (ps!=null) ps.close();
    if (ps2!=null) ps2.close();
    if(conn != null) conn.close();

}
       // tablePDR.refresh();
    }  // Done

    private void upDateHistoriqueUpdate(Connection conn,PreparedStatement ps2,String N,String D,int S,String session,String type) throws SQLException {
        String sql2 = "INSERT INTO pdr_historique (nomenclature, designation, qte_stock,type, utilisateur) VALUES (?,?,?,?,?)";
        ps2 = conn.prepareStatement(sql2);
        ps2.setString(1, N);
        ps2.setString(2, D);
        ps2.setInt(3, S);
        ps2.setString(4, type);
        ps2.setString(5, session);
        int rowDeleted2 = ps2.executeUpdate();
        if (rowDeleted2 > 0) {
            System.out.println(STR."la PDR dont la Nomenclature \{N} , objet de la suppression est ajouté à la table historique");
        }
    }

    private void handleDeletePDR() {

        // Afficher la boîte de dialogue de saisie du mot de passe
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Autorisation requise");
        dialog.setHeaderText("Droits d'administrateur nécessaires");

        // Créer un PasswordField pour la saisie du mot de passe
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe administrateur");

        // Ajouter le PasswordField à la boîte de dialogue
        dialog.getDialogPane().setContent(passwordField);

        // Ajouter les boutons OK et Annuler
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Convertir le résultat du PasswordField lorsque l'utilisateur clique sur OK
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return passwordField.getText();
            }
            return null;
        });

        // Afficher la boîte de dialogue et attendre la saisie de l'utilisateur
        dialog.showAndWait().ifPresent(password -> {
            if (password.equals(ConfigLoader.getProperty("auth.admin.password"))) {
                // Si le mot de passe est correct, supprimer la PDR
                try {
                    supprimerPDR();
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // Si le mot de passe est incorrect, afficher une alerte
                Alert alert = new Alert(Alert.AlertType.ERROR, "Mot de passe incorrect !", ButtonType.OK);
                alert.showAndWait();
            }
        });
    } //done
    private void handleUpDatePDR() {

        // Afficher la boîte de dialogue de saisie du mot de passe
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Autorisation requise");
        dialog.setHeaderText("Droits d'administrateur nécessaires");

        // Créer un PasswordField pour la saisie du mot de passe
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe administrateur");

        // Ajouter le PasswordField à la boîte de dialogue
        dialog.getDialogPane().setContent(passwordField);

        // Ajouter les boutons OK et Annuler
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Convertir le résultat du PasswordField lorsque l'utilisateur clique sur OK
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return passwordField.getText();
            }
            return null;
        });

        // Afficher la boîte de dialogue et attendre la saisie de l'utilisateur
        dialog.showAndWait().ifPresent(password -> {
            if (password.equals(ConfigLoader.getProperty("auth.admin.password"))) {
                // Si le mot de passe est correct, supprimer la PDR
                panModifier.setVisible(true);
                panModifier.toFront();
            } else {
                // Si le mot de passe est incorrect, afficher une alerte
                Alert alert = new Alert(Alert.AlertType.ERROR, "Mot de passe incorrect !", ButtonType.OK);
                alert.showAndWait();
            }
        });
    }

    public void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        // Get the underlying stage and set the position
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setX(600);
        stage.setY(400);
        alert.showAndWait();
    } // done

    public boolean existancePDR(String Nomenclature) throws SQLException, IOException {

        String sql = " SELECT FROM PDR WHERE Nomenclature = ? AND isdeleted = 'false' ";
        Connection conn = database.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, Nomenclature);
        ResultSet row = ps.executeQuery();
        // If there is at least one row in the ResultSet
        // If the ResultSet is empty
        return row.next();

    } // done

    public void upDatePDR() {
        String TV = typeVehiculeModif.getValue();
        String G = groupeModif.getValue();
        String P = positionModif.getValue();
        String NO = nomenclatureMS.getText();
        String N = NomenclatureModif.getText();
        String D = DesignationModif.getText();
        String C = codeBarreModif.getText();
        String U = photoURL1.getText();
        double Prix;
        int QS;

        try {
            // Validation
            validateFields(NO, N, D, C, TV, G, P, PUModif.getText());

            // Parsing
            Prix = Double.parseDouble(PUModif.getText());
            QS = Integer.parseInt(QTSModif.getText());

            // Database Operation
            if (existancePDR(NO)) {
                updatePDRInDatabase(NO, N, D, C, U, QS, Prix, P, TV, G);
                upDateHistoriqueDelete(conn, N, D, QS);
                showAlert("succès", "Modification mise à jour", Alert.AlertType.INFORMATION);
            } else {
                showAlert("error", "cette Nomenclature n'existe pas", Alert.AlertType.ERROR);
            }

            clearFields();
        } catch (NumberFormatException e) {
            showAlert("error", "champs obligatoires", Alert.AlertType.ERROR);
        } catch (SQLException e) {
            showAlert("error", "Database error: " + e.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("error", "Unexpected error: " + e.getMessage(), Alert.AlertType.ERROR);
        } finally {
            Platform.runLater(() -> panModifier.setVisible(true));
        }
    }

    private void validateFields(String NO, String N, String D, String C, String TV, String G, String P, String priceText) throws NumberFormatException {
        if (NO.isEmpty() || N.isEmpty() || D.isEmpty() || C.isEmpty() || TV.isEmpty() || G.isEmpty() || P.isEmpty() || Double.isNaN(Double.parseDouble(priceText))) {
            throw new NumberFormatException("champs obligatoires");
        }
    }

    private void updatePDRInDatabase(String NO, String N, String D, String C, String U, int QS, double Prix, String P, String TV, String G) throws SQLException {
        String sql2 = "UPDATE PDR SET Nomenclature = ?, Designation = ?, codeBarre = ?, photo_url = ?, Qte_stock = ?, prix_unitaire = ?, position = ?, typevehicule = ?, groupe = ? WHERE Nomenclature = ?;";
        PreparedStatement ps =null;
        try {
            ps = conn.prepareStatement(sql2);
            ps.setString(1, N);
            ps.setString(2, D);
            ps.setString(3, C);
            ps.setString(4, U);
            ps.setInt(5, QS);
            ps.setDouble(6, Prix);
            ps.setString(7, P);
            ps.setString(8, TV);
            ps.setString(9, G);
            ps.setString(10, NO);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
//        }finally {
//            if (ps!=null) {ps.close();}
//            if (conn!=null) {conn.close();}
//        }
    }
    }
//    public void upDatePDR() throws SQLException {
//
//        try {
//            String TV = typeVehiculeModif.getValue();
//            String G= groupeModif.getValue();
//            String P= positionModif.getValue();
//            String NO = nomenclatureMS.getText();
//            String N = NomenclatureModif.getText();
//            String D = DesignationModif.getText();
//            String C = codeBarreModif.getText();
//            String U= photoURL1.getText();
//            double Prix = Double.parseDouble(PUModif.getText());
//            if (NO.isEmpty() || N.isEmpty() || D.isEmpty() || C.isEmpty() || TV.isEmpty() || G.isEmpty() || P.isEmpty() || Double.isNaN(Prix)) {
//                showAlert("error", "champs obligatoires", Alert.AlertType.valueOf("ERROR"));
//            }
//            String Q = QTSModif.getText();
//            int QS = Integer.parseInt(Q);
//
//
//            if (!existancePDR(NO)) {
//                showAlert("error", "cette Nomenclature n'existe pas ",
//                        Alert.AlertType.valueOf("ERROR"));
//            } else {
//
//                String sql2 = "UPDATE PDR SET Nomenclature = ?,Designation = ?, codeBarre = ?,photo_url = ?,Qte_stock = ?, prix_unitaire= ?," +
//                        "position=?, typevehicule = ?, groupe = ? WHERE Nomenclature = ? ;";
//
//                PreparedStatement ps = conn.prepareStatement(sql2);
//
//                ps.setString(1, N);
//                ps.setString(2, D);
//                ps.setString(3, C);
//                ps.setString(4, U);
//                ps.setInt(5, QS);
//                ps.setDouble(6, Prix);
//                ps.setString(7, P);
//                ps.setString(8,TV );
//                ps.setString(9,G);
//                ps.setString(10,NO);
//
//                int rowdated = ps.executeUpdate();
//
//                if (rowdated > 0) {
//                    showAlert("succès", "Modification mise à jour",
//                            Alert.AlertType.valueOf("INFORMATION"));
//                }
//            }
//            upDateHistoriqueDelete(conn,N,D,QS);
//            clearFields();
//        } catch (NumberFormatException e) {
//            showAlert("error", "champs obligatoires", Alert.AlertType.valueOf("ERROR"));
//            panModifier.setVisible(true);
//        } catch (NullPointerException | IOException e) {
//            showAlert("error", "champs Nomenclature obligatoires",
//                    Alert.AlertType.valueOf("ERROR"));
//        } finally {
//            panModifier.setVisible(true);
//
//        }
//    }//done

    public void upDateHistoriqueDelete(Connection conn, String N,String D, int QS) throws SQLException {
        String type = "Modification";
        String session = sessionInfo;
        String sql3 = "INSERT INTO pdr_historique (nomenclature, designation, qte_stock,type, utilisateur) VALUES (?,?,?,?,?)";
        PreparedStatement ps2 = conn.prepareStatement(sql3);
        ps2.setString(1,N);
        ps2.setString(2,D);
        ps2.setInt(3,QS);
        ps2.setString(4,type);
        ps2.setString(5,session);

        int rs = ps2.executeUpdate();
        if (rs > 0) {
            System.out.println(STR."la PDR dont la Nomenclature \{N} , objet de la modification est ajouté à la table historique");
        }

    }

    public void clearFields() {
        NomenclatureModif.setText("");
        DesignationModif.setText("");
        codeBarreModif.setText("");
        QTSModif.setText("");
        PUModif.setText("");
    }

    public void ajouterM() throws SQLException, IOException {

       String F = numeroFolio.getText();
       int Q = 0;
       Date date = null;
       try {
            Q = Q+ Integer.parseInt(qte_mouvement.getText());
            date = Date.valueOf(dateMouvement.getValue());
       }catch (NumberFormatException e){
           showAlert("erreur","champs quantite obligatoir!!", Alert.AlertType.WARNING);
       }catch ( NullPointerException ee){
           showAlert("erreur","champs Date obligatoir!!", Alert.AlertType.WARNING);
       }
       String T = typeMouvement.getValue();

       String N = NomenclatureMvt.getText();

       try {
           if (existancePDR(N)) {
               int qteExistante = loadQTE(N);
               if (T.equals("Sortie") && Q > qteExistante) {
                   showAlert("Quantité Insuffisante",
                           "La quantité du mouvement est supérieure à la quantité existante. Quantité actuelle : "
                                   + qteExistante,
                           Alert.AlertType.WARNING);
                   return; // Stopper l'exécution ici pour éviter l'ajout du mouvement
               }
           }

           String sql = "INSERT INTO mouvement (n_folio, type_m, quantite_m, nomenclature, date_mouvement) VALUES (?, ?, ?, ?, ?)";

           PreparedStatement ps = null;

           try {
               ps = conn.prepareStatement(sql);
               ps.setString(1, F);
               ps.setString(2, T);
               ps.setInt(3, Q);
               ps.setString(4, N);
               ps.setDate(5, date);

               int rowsAffected = ps.executeUpdate();
               if (rowsAffected > 0) {
                   showAlert("Ajout Mouvement", "Le mouvement est ajouté avec succès", Alert.AlertType.INFORMATION);
                   upDateQteS();
               } else if (rowsAffected == -1) {
                   System.out.println("Erreur ajout mouvement");
               }else{
                   showAlert("Erreur", "Cette nomenclature n'existe pas", Alert.AlertType.WARNING);
               }
           } catch (SQLException | BalanceException e) {
               e.printStackTrace();
               showAlert("Erreur SQL", "Une erreur est survenue lors de l'exécution de la requête SQL", Alert.AlertType.ERROR);
           }finally {

               if (ps!=null) {ps.close();
                   if (conn!=null) {conn.close();}
               }
           }
       } catch (SQLException | IOException e) {
           throw new RuntimeException(e);
       }
    }//done

    private void upDateQteS() throws SQLException, IOException, BalanceException {

        String E = "Entrée";
        String S = "Sortie";
        int Q = Integer.parseInt(qte_mouvement.getText());
        String T = typeMouvement.getValue();
        String N = NomenclatureMvt.getText();
        String sqlE = "UPDATE pdr SET qte_stock = qte_stock + ? WHERE nomenclature = ?";
        String sqlS = "UPDATE pdr SET qte_stock = qte_stock - ? WHERE nomenclature = ?";
        //String slqBalance = "SELECT qte_stock FROM pdr WHERE nomenclature = ?";
        Connection con = database.getConnection();
        PreparedStatement ps;
        int qte = loadQTE(N);
        if (T.equals(E)) {
            ps = con.prepareStatement(sqlE);
            ps.setInt(1, Q); // Ajoute la quantité pour une entrée
            ps.setString(2, N);
        } else if (T.equals(S)) {
            ps = con.prepareStatement(sqlS);
            ps.setInt(1, Q); // Ajoute la quantité pour une entrée
            ps.setString(2, N);
        } else {
            throw new IllegalArgumentException(STR."Type de mouvement inconnu : \{T}");
        }

        int rowsAffected = ps.executeUpdate();
        System.out.println(STR."\{rowsAffected} ligne(s) mise(s) à jour.");

        ps.close();
        con.close();
    }//done

    private int loadQTE(String N) throws SQLException, IOException {
        String slqBalance = "SELECT qte_stock FROM pdr WHERE nomenclature = ?";
        Connection con = database.getConnection();
        PreparedStatement ps;
        ps = con.prepareStatement(slqBalance);
        ps.setString(1, N);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException(STR."Aucune donnée trouvée pour la nomenclature : \{N}");
            }
        }
    }//done

    private void changeBackgroundColor() {
            if (CBlightblue.isSelected()) {
                // Change la couleur de fond lorsque la CheckBox est sélectionnée
                Anchore.setStyle("-fx-background-color: lightblue;");
            } else if (CBBlack.isSelected()) {
                // Réinitialise la couleur de fond lorsque la CheckBox n'est pas sélectionnée
                Anchore.setStyle("-fx-background-color: black;");
            } else {
                Anchore.setStyle("-fx-background-color: white;");
            }
        }//done


}
