package codecooler.michal.com;

import codecooler.michal.com.DAO.*;
import codecooler.michal.com.view.*;
import codecooler.michal.com.model.Book;

public class LibraryController {
    private LibraryView libraryView;
    private BookDAO bookDAO;

    public LibraryController(BookDAO bookDAO) {
        this.libraryView = new LibraryView();
        this.bookDAO = bookDAO;
    }

    public void libraryOptions(){
        BookDAO bookDAO = new BookJDBCDAO();
        LibraryView libraryView = new LibraryView();

        System.out.println("Welcome to the personal library!");

        boolean quit = false;
        while(!quit) {

            int action = libraryView.askUserToChooseAction();

            switch (action) {
                case 0:
                    libraryView.quitApplication();
                    quit = true;
                    break;

                case 1:
                    Book bookCreate = libraryView.askUserForBookData();
                    bookDAO.createBook(bookCreate);
                    break;

                case 2:
                    Book bookUpdate = libraryView.askUserToUpdateBookData();
                    bookDAO.updateBook(bookUpdate);
                    break;

                case 3:
                    Long isbn = libraryView.askUserForBookISBN();
                    bookDAO.deleteBook(isbn);
                    break;

                case 4:
                    String authorSurname = libraryView.askUserForAuthorSurname();
                    libraryView.viewBooks(bookDAO.getBookByAuthorSurname(authorSurname));
                    break;

                case 5:
                    libraryView.viewBooks(bookDAO.getAllBooksByTitleAsc());
                    break;

                case 6:
                    String authorSurname1 = libraryView.askUserForAuthorSurname();
                    int numberOfBooks = bookDAO.getNumberOfBooksByAuthor(authorSurname1);
                    libraryView.printNumberOfBooksByAuthor(authorSurname1, numberOfBooks);
                    break;

                case 7:
                    libraryView.viewBooks(bookDAO.getBooksForLastTenYears());
                    break;

                case 8:
                    int valueOfLibrary = bookDAO.getPriceOfAllBooks();
                    libraryView.viewValueOfLibrary(valueOfLibrary);
                    break;

                case 9:
                    System.out.println("In update.");
                    break;

                case 10:
                    libraryView.printCustomersOptions();
                    break;
            }
        }
    }
 }
