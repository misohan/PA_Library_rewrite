package codecooler.michal.com;

import codecooler.michal.com.DAO.BookDAO;
import codecooler.michal.com.DAO.BookJDBCDAO;

public class Library {
    public static void main(String[] args) {
        BookDAO bookDAO = new BookJDBCDAO();
        LibraryController libraryController = new LibraryController(bookDAO);

        libraryController.libraryOptions();
    }

}
