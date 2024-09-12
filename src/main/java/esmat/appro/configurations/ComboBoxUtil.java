package esmat.appro.configurations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

public class ComboBoxUtil {


        public static ObservableList<String> getPositions() {
            return convertStringToObservableList(ConfigLoader.getProperty("position.constant"));
        }

        public static ObservableList<String> getGroupes() {
            return convertStringToObservableList(ConfigLoader.getProperty("groupe.constant"));
        }

        public static ObservableList<String> getTypesVehicules() {
            return convertStringToObservableList(ConfigLoader.getProperty("typeV.constant"));
        }

        private static ObservableList<String> convertStringToObservableList(String value) {
            if (value != null) {
                String[] items = value.split(",");
                return FXCollections.observableArrayList(Arrays.asList(items));
            }
            return FXCollections.observableArrayList();
        }
    }


