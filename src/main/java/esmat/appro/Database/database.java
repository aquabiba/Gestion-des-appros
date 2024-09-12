
package esmat.appro.Database;

import esmat.appro.configurations.ConfigLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
    private static Connection conn;


    public static Connection getConnection() {
        try {
        // Charger les propriétés depuis ConfigLoader
        String url = ConfigLoader.getProperty("db.url");
        String username = ConfigLoader.getProperty("db.username");
        String password = ConfigLoader.getProperty("db.password");
        String driver = ConfigLoader.getProperty("db.driver");

        // Charger le driver PostgreSQL
        Class.forName(driver);

        // Établir la connexion
        conn = DriverManager.getConnection(url, username, password);
        System.out.println("Connexion à la base de données PostgreSQL réussie !");
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }finally {

        }
        return conn;
    }
}



//package esmat.appro.Database;
//
//import esmat.appro.configurations.ConfigLoader;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class database {
//    private static Connection conn;
//
//    // Constructeur privé pour éviter l'instanciation directe
//    private database() {
//        // Empty to prevent instantiation
//    }
//
//    // Méthode pour obtenir la connexion (Singleton)
//    public static Connection getConnection() {
//        if (conn == null) {
//            try {
//                // Charger les propriétés de configuration
//                String url = ConfigLoader.getProperty("db.url");
//                String username = ConfigLoader.getProperty("db.username");
//                String password = ConfigLoader.getProperty("db.password");
//                String driver = ConfigLoader.getProperty("db.driver");
//
//                if (url == null || username == null || password == null || driver == null) {
//                    throw new IllegalArgumentException("Les propriétés de connexion à la base de données ne sont pas correctement configurées.");
//                }
//
//                // Charger le driver PostgreSQL
//                Class.forName(driver);
//
//                // Établir la connexion
//                conn = DriverManager.getConnection(url, username, password);
//                System.out.println("Connexion à la base de données PostgreSQL établie avec succès !");
//            } catch (SQLException | ClassNotFoundException e) {
//                e.printStackTrace();
//                throw new RuntimeException("Échec de la connexion à la base de données.", e);
//            }
//        }
//        return conn;
//    }
//}

//    private static final String URL = "jdbc:postgresql://localhost:5432/appro1esmat";
//    private static final String USER = "postgres";
//    private static final String PASSWORD = "123";





//        Connection conn;
//        conn = DriverManager.getConnection(URL, USER, PASSWORD);
//        Statement stmt = conn.createStatement();
//        String basePath = System.getProperty("user.dir");
//        String sql = new String(Files.readAllBytes(Paths.get(STR."\{basePath}/src/main/resources/db/appro1esmat.sql")));
//        stmt.execute(sql);