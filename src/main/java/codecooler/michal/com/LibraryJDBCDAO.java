package codecooler.michal.com;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class LibraryJDBCDAO implements LibraryDAO {
    private LibrarySQLConnection dbConn = new LibrarySQLConnection();
    private ResultSet resultSet = null;

    public void updateBookInLibrary(long ISBN, int author_id, String title, String publisher_id, int publication_year, int price){
        String sql = "UPDATE books "
                + "SET \"ISBN\" = ?, \"title\" = ?, \"publisher_id\"= ?, \"publication_year\"= ?, \"price\" = ?"
                + "WHERE author_id = ?";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            // set the corresponding parameter

            pst.setLong(1, ISBN);
            pst.setInt(6, author_id);
            pst.setString(2, title);
            pst.setString(3,publisher_id);
            pst.setInt(4,publication_year);
            pst.setInt(5,price);


            // update
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void editBookData(String title, int publication_year, int price, int author_id){
        String sql = "UPDATE books "
                +"SET \"title\" = ?, \"publication_year\" = ?, \"price\" = ?"
                + "WHERE author_id = ?";
        System.out.println(sql);
        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            // set the corresponding parameter

            pst.setString(1, title);
            pst.setInt(2,publication_year);
            pst.setInt(3,price);
            pst.setInt(4,author_id);


            // update
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeBook(long ISBN){
        String sql = "DELETE from books WHERE \"ISBN\" = ?";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            // set the corresponding parameter

            pst.setLong(1, ISBN);

            // update
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
    public void findBookByAuthorSurname(String surname){
        String sql = "SELECT books.\"title\", authors.\"surname\" " +
                "FROM authors " +
                "INNER JOIN books ON books.\"author_id\"=authors.\"id\"" +
                " WHERE \"surname\" = '" + surname + "';";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            resultSet = pst.executeQuery();

            System.out.println("Titles by " + surname);
            while (resultSet.next()) {
                System.out.println("Title: " + resultSet.getString("title"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void showAllBooksByTitleAsc(){

        String sql = "SELECT * FROM books ORDER BY \"title\" ASC";

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
    public void numberOfBooksByAuthor(String surname){
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
    public void showBooksForLastTenYears(){
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

    public void priceOfAllBooks(){
        String sql = "SELECT SUM(price) " +
                "FROM books;";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {


            resultSet = pst.executeQuery();

            if (resultSet.next()) {

                System.out.println("Value of Library: " + resultSet.getInt(1));
            }
            resultSet.close();



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addNewBookToLibrary(Long longy){

    }

}




