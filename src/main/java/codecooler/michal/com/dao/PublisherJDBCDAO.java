package codecooler.michal.com.dao;

import codecooler.michal.com.LibrarySQLConnection;
import codecooler.michal.com.model.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PublisherJDBCDAO implements PublisherDAO{
    private final LibrarySQLConnection dbConn = new LibrarySQLConnection();

    @Override
    public void createPublisher(Publisher publisher) {
        String sql = "INSERT INTO authors (ID, \"name\") " +
                "VALUES (?,?)";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, publisher.getId());
            pst.setString(2, publisher.getName());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        String sql = "UPDATE publishers "
                + "SET \"name\"= ?"
                + "WHERE ID = ?";

        try (Connection con = dbConn.connect();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, publisher.getName());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Publisher> getPublishers() {
        return null;
    }

    @Override
    public void deletePublisher(String id) {

    }
}
