package codecooler.michal.com;

import codecooler.michal.com.dao.*;
import codecooler.michal.com.view.*;
import codecooler.michal.com.model.Book;

import java.util.Collections;
import java.util.List;

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
        List<Book> books = bookDAO.getBooks();

        System.out.println("Welcome to the personal library!");

        boolean quit = false;
        while(!quit) {

            int action = libraryView.askUserToChooseAction();

            switch (action) {
                case 0:
                    libraryView.printEndingOfApplication();
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
                    Collections.sort(books);
                    libraryView.viewBooks(books);
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
                    libraryView.viewValueOfLibrary(getPriceOfAllBooks(books));
                    break;

                case 9:
                    libraryView.viewBooks(books);
                    break;

                case 10:
                    libraryView.printCustomersOptions();
                    break;
            }
        }
    }
    public int getPriceOfAllBooks(List<Book> books){
        int libraryValue = 0;
        for(Book book: books){
            libraryValue += book.getPrice();

        }
        return libraryValue;
    }
}
