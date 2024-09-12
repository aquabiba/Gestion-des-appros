package esmat.appro.classController;


import esmat.appro.configurations.ConfigLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Constants {
    public static final ObservableList<String> POSITIONS = FXCollections.observableArrayList(ConfigLoader.getProperty("position.constant"));
    public static final ObservableList<String> GROUPES = FXCollections.observableArrayList(ConfigLoader.getProperty("groupe.constant"));
    public static final ObservableList<String> TYPES_VEHICULES = FXCollections.observableArrayList(ConfigLoader.getProperty("typeV.constant"));
}