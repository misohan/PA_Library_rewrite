package codecooler.michal.com.dao;
import codecooler.michal.com.LibrarySQLConnection;
import codecooler.michal.com.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class BookJDBCDAO implements BookDAO {
    private List<Book> books = new ArrayList<>();
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

            pst.setLong(1, book.getIsbn());
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
        String sql = "UPDATE books "
                + "SET \"author_id\" = ?, \"title\" = ?, \"publisher_id\"= ?, \"publication_year\"= ?, \"price\" = ?"
                + "WHERE \"ISBN\" = ?";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setLong(6, book.getIsbn());
            pst.setInt(1, book.getAuthor_id());
            pst.setString(2, book.getTitle());
            pst.setString(3, book.getPublisher_id());
            pst.setInt(4, book.getPublication_year());
            pst.setInt(5, book.getPrice());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Book> getBooks() {
        ResultSet resultSet = null;
        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM books";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            resultSet = pst.executeQuery();

            while (resultSet.next()) {

                Book book = new Book();

                book.setIsbn(resultSet.getLong("ISBN"));
                book.setAuthor_id(resultSet.getInt("author_id"));
                book.setTitle(resultSet.getString("title"));
                book.setPublisher_id(resultSet.getString("publisher_id"));
                book.setPublication_year(resultSet.getInt("publication_year"));
                book.setPrice(resultSet.getInt("price"));

                books.add(book);
            }
            return books;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteBook(long ISBN) {
        String sql = "DELETE from books WHERE \"ISBN\" = ?";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setLong(1, ISBN);

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Book getBookByAuthorSurname(String surname){
        ResultSet resultSet = null;

        String sql = "SELECT books.\"ISBN\", books.\"author_id\", books.\"title\", books.\"publisher_id\", " +
                "books.\"publication_year\", books.\"price\", authors.\"surname\" " +
                "FROM authors " +
                "INNER JOIN books ON books.\"author_id\"=authors.\"id\"" +
                " WHERE \"surname\" = '" + surname + "';";

        Book book = new Book();

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            resultSet = pst.executeQuery();

            while (resultSet.next()) {

                book.setIsbn(resultSet.getLong("ISBN"));
                book.setAuthor_id(resultSet.getInt("author_id"));
                book.setTitle(resultSet.getString("title"));
                book.setPublisher_id(resultSet.getString("publisher_id"));
                book.setPublication_year(resultSet.getInt("publication_year"));
                book.setPrice(resultSet.getInt("price"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return book;
    }
    public int getNumberOfBooksByAuthor(String surname){

        ResultSet resultSet = null;

        String sql = "SELECT COUNT(*)" +
                "FROM authors " +
                "INNER JOIN books ON books.\"author_id\"=authors.\"id\"" +
                " WHERE \"surname\" = '" + surname + "';";

        int numberOfBooks = 0;
        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            resultSet = pst.executeQuery();

            while (resultSet.next()) {
                System.out.println("You have " + resultSet.getInt(1) + " books by this author.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return numberOfBooks;
    }

}
