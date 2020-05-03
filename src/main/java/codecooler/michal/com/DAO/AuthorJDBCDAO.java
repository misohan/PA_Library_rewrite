package codecooler.michal.com.DAO;
import codecooler.michal.com.LibrarySQLConnection;
import codecooler.michal.com.model.Author;
import codecooler.michal.com.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorJDBCDAO implements AuthorDAO {
    private final LibrarySQLConnection dbConn = new LibrarySQLConnection();
    private LibrarySQLConnection connection;


    @Override
    public void createAuthor(Author author) {
        String sql = "INSERT INTO authors (\"id\",\"first_name\", \"surname\") " +
                "VALUES (?,?,?)";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, author.getId());
            pst.setString(2, author.getFirstName());
            pst.setString(3, author.getFirstName());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void updateAuthor(Author author) {

    }

    @Override
    public List<Author> getAuthors() {
        return null;
    }

    @Override
    public void deleteAuthor(int id) {

    }
}
