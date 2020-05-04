package codecooler.michal.com;
import codecooler.michal.com.DAO.*;
import codecooler.michal.com.model.*;




public class Library {
    public static void main(String[] args) {

        BookDAO bookDAO = new BookJDBCDAO();

//        Book book = new Book(123456789123L, 6,"Za chujom", "5k4", 1878, 55);

        AuthorDAO authorDAO = new AuthorJDBCDAO();











    }

}
