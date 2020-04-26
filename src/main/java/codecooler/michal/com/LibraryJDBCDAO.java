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
    public Book findBookByAuthorSurname(String surname){
        String sql = "SELECT books.\"title\", authors.\"surname\" " +
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public void getNumberOfBooksByAuthor(String surname){
        String sql = "SELECT COUNT(*)" +
                "FROM authors " +
                "INNER JOIN books ON books.\"author_id\"=authors.\"id\"" +
                " WHERE \"surname\" = '" + surname + "';";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            resultSet = pst.executeQuery();

            while (resultSet.next()) {
                System.out.println("You have " + resultSet.getInt(1) + " books by this author.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void getBooksForLastTenYears(){
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int lastTenYears= 10;

        System.out.println(currentYear);

        String sql = "SELECT * FROM books " +
                "WHERE publication_year " +
                "BETWEEN " + (currentYear - lastTenYears) +" AND " + currentYear;

        try (Connection con = dbConn.connect();

             PreparedStatement pst = con.prepareStatement(sql)) {

            resultSet = pst.executeQuery();

            while (resultSet.next()) {
                System.out.println("Title: " + resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int priceOfAllBooks() {
        String sql = "SELECT SUM(price) " +
                "FROM books;";

        int price = 0;
        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            resultSet = pst.executeQuery();

            if (resultSet.next()) {

                price = resultSet.getInt(1);
            }
            resultSet.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return price;
    }
}





