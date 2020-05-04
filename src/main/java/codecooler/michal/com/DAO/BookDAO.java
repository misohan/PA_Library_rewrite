package codecooler.michal.com.DAO;

import java.util.List;
import codecooler.michal.com.model.Book;


public interface BookDAO {
    void createBook(Book book);

    void updateBook(Book book);

    List<Book> getBooks();

    void deleteBook(long ISBN);

    Book getBookByAuthorSurname(String surname);

    List<Book> getAllBooksByTitleAsc();

    int getNumberOfBooksByAuthor(String surname);

    List<Book> getBooksForLastTenYears();

    int getPriceOfAllBooks();
}
