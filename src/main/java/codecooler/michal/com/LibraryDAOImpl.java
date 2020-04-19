package codecooler.michal.com;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class LibraryDAOImpl {
    private LibrarySQLConnection dbConn = new LibrarySQLConnection();
    private ResultSet resultSet = null;

    public void editBookData(long ISBN, int author_id, String title, String publisher_id, int publication_year, int price){
        String sql = "UPDATE books "
                + "SET ISBN= ?, title= ?, publisherID= ?, publicationYear= ?, price = ? "
                + "WHERE author_id = ?";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            // set the corresponding parameter

            pst.setLong(1, ISBN);
            pst.setInt(2, author_id);
            pst.setString(3, title);
            pst.setString(4,publisher_id);
            pst.setInt(5,publication_year);
            pst.setInt(6,price);


            // update
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeBook(String title){
        String sql = "DELETE from books WHERE \"title\" = ?";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            // set the corresponding parameter

            pst.setString(1, title);

            // update
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
    public void findBookByAuthorSurname(String authorSurname){
        String sql = "SELECT*FROM from authors WHERE \"author_id\" = ?";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {


            pst.setString(1, authorSurname);

            resultSet = pst.executeQuery();

            if (resultSet.next()) {

                System.out.println("Title: " + resultSet.getString("title"));
            }
            resultSet.close();



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void showAllBooksByTitleAsc(){

        String sql = "SELECT * FROM books ORDER BY \"title\" ASC";

        try
                (Connection con = dbConn.connect();
                 PreparedStatement pst = con.prepareStatement(sql)) {
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                System.out.println("Title: " + resultSet.getString("title"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
