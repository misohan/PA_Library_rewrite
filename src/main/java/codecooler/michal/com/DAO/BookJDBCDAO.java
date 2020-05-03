package codecooler.michal.com.DAO;
import codecooler.michal.com.LibrarySQLConnection;
import codecooler.michal.com.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class BookJDBCDAO implements BookDAO {
    private final LibrarySQLConnection dbConn = new LibrarySQLConnection();
    private LibrarySQLConnection connection;

    public BookJDBCDAO() {
        this.connection = new LibrarySQLConnection();
    }

    @Override
    public void createBook(Book book){

        String sql = "INSERT INTO books (\"ISBN\",\"author_id\", \"title\", \"publisher_id\", \"publication_year\", \"price\") " +
                "VALUES (?,?,?,?,?,?)";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setLong(1, book.getISBN());
            pst.setInt(2, book.getAuthor_id());
            pst.setString(3, book.getTitle());
            pst.setString(4, book.getPublisher_id());
            pst.setInt(5, book.getPublication_year());
            pst.setInt(6, book.getPrice());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public List<Book> getBooks() {
        return null;
    }

    @Override
    public void deleteBook(long ISBN) {

    }

}
