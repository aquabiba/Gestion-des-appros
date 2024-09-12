package esmat.appro.BarcodeDataGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BarcodeDataGenerator {

    // Génère les données pour le code-barres
    public static String generateBarcodeData(int pdrId) {
        // Récupère l'horodatage actuel
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());

        // Génère un identifiant unique basé sur le pdrId et l'horodatage
        String uniqueId = String.format("%04d", pdrId) + timestamp;

        return uniqueId;
    }


}
