module esmat.appro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens esmat.appro to javafx.fxml;

    exports esmat.appro;
    exports esmat.appro.Database;
    opens esmat.appro.Database to javafx.fxml;
    exports esmat.appro.model;
    opens esmat.appro.model to javafx.fxml;
    exports esmat.appro.classController;
    opens esmat.appro.classController to javafx.fxml;
    exports esmat.appro.BarcodeDataGenerator;
    opens esmat.appro.BarcodeDataGenerator to javafx.fxml;
}