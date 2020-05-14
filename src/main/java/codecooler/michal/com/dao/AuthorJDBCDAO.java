package codecooler.michal.com.dao;
import codecooler.michal.com.LibrarySQLConnection;
import codecooler.michal.com.model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorJDBCDAO implements AuthorDAO {
    private List<Author> authors = new ArrayList<>();
    private final LibrarySQLConnection dbConn = new LibrarySQLConnection();
    private LibrarySQLConnection connection;


    @Override
    public void createAuthor(Author author) {
        String sql = "INSERT INTO authors (\"first_name\", \"surname\") " +
                "VALUES (?,?,?)";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, author.getFirstName());
            pst.setString(2, author.getFirstName());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateAuthor(Author author) {
        String sql = "UPDATE authors "
                + "SET \"first_name\"= ?, \"surname\"= ?"
                + "WHERE ID = ?";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, author.getFirstName());
            pst.setString(2, author.getSecondName());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Author> getAuthors() {
        ResultSet resultSet = null;
        List<Author> authors = new ArrayList<>();

        String sql = "SELECT * FROM authors";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            resultSet = pst.executeQuery();

            while (resultSet.next()) {

                Author author = new Author();

                author.setId(resultSet.getInt("ID"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setSecondName(resultSet.getString("surname"));

                authors.add(author);
            }
            return authors;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAuthor(int id) {

    }
}
