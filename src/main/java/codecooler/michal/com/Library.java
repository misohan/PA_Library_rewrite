package codecooler.michal.com;

import codecooler.michal.com.dao.BookDAO;
import codecooler.michal.com.dao.BookJDBCDAO;

public class Library {
    public static void main(String[] args) {
        BookDAO bookDAO = new BookJDBCDAO();
        LibraryController libraryController = new LibraryController(bookDAO);

        libraryController.libraryOptions();
    }

}
