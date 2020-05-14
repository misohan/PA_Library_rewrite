package codecooler.michal.com;

import codecooler.michal.com.dao.BookDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibrarySQLConnection {

    private Properties readProperties() {
        Properties props = new Properties();
        Path myPath = Paths.get("src/main/resources/database.properties");

        try {
            BufferedReader bf = Files.newBufferedReader(myPath,
                    StandardCharsets.UTF_8);

            props.load(bf);
        } catch (IOException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        return props;
    }

    public Connection connect() {
        // SQL connection
        Properties props = readProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
