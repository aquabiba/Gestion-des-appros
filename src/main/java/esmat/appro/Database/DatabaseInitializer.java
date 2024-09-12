package esmat.appro.Database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initializeDatabase() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/appro1esmat";
        String user = "postgres";
        String password = "123";

        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();

        String sql = new String(Files.readAllBytes(Paths.get("src/main/resources/db/database.sql")));
        stmt.execute(sql);

        stmt.close();
        conn.close();
    }
}

