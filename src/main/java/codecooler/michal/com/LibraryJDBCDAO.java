package codecooler.michal.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LibraryJDBCDAO implements LibraryDAO {
    private List<Book> books = new ArrayList<>();
    private final LibrarySQLConnection dbConn = new LibrarySQLConnection();
    private ResultSet resultSet = null;

    public void addBook(long ISBN, int author_id, String title, String publisher_id, int publication_year, int price){
        String sql = "INSERT INTO books (\"ISBN\",\"author_id\", \"title\", \"publisher_id\", \"publication_year\", \"price\") " +
                "VALUES (?,?,?,?,?,?)";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setLong(1, ISBN);
            pst.setInt(2, author_id);
            pst.setString(3, title);
            pst.setString(4,publisher_id);
            pst.setInt(5,publication_year);
            pst.setInt(6,price);

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBook(long ISBN, int author_id, String title, String publisher_id, int publication_year, int price){
        String sql = "UPDATE books "
                + "SET \"author_id\" = ?, \"title\" = ?, \"publisher_id\"= ?, \"publication_year\"= ?, \"price\" = ?"
                + "WHERE \"ISBN\" = ?";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setLong(6, ISBN);
            pst.setInt(1, author_id);
            pst.setString(2, title);
            pst.setString(3,publisher_id);
            pst.setInt(4,publication_year);
            pst.setInt(5,price);

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeBook(long ISBN){
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

                book.setISBN(resultSet.getLong("ISBN"));
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
    public List<Book> getAllBooksByTitleAsc(){
        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM books ORDER BY \"title\" ASC";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            resultSet = pst.executeQuery();

            while (resultSet.next()) {

                Book book = new Book();

                book.setISBN(resultSet.getLong("ISBN"));
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
    public int getNumberOfBooksByAuthor(String surname){
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
    public List<Book> getBooksForLastTenYears(){
        List<Book> books = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int lastTenYears= 10;


        String sql = "SELECT * FROM books " +
                "WHERE publication_year " +
                "BETWEEN " + (currentYear - lastTenYears) +" AND " + currentYear;

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            resultSet = pst.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();

                book.setISBN(resultSet.getLong("ISBN"));
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
            return null;
        }
    }
    public int getPriceOfAllBooks() {
        String sql = "SELECT SUM(price) " +
                "FROM books;";

        int priceOfBooks = 0;
        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            resultSet = pst.executeQuery();

            if (resultSet.next()) {

                priceOfBooks = resultSet.getInt(1);
            }
            resultSet.close();

            return priceOfBooks;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public void addAuthor(String first_name, String surname){
        String sql = "INSERT INTO authors (\"first_name\", \"surname\") " +
                "VALUES (?,?)";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, first_name);
            pst.setString(2, surname);

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addPublisher(String ID, String name){
        String sql = "INSERT INTO publishers (ID, \"name\") " +
                "VALUES (?,?)";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, ID);
            pst.setString(2, name);

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 }





