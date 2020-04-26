package codecooler.michal.com;

import java.util.List;

public interface LibraryDAO {
    void addBook(long ISBN, int author_id, String title, String publisher_id, int publication_year, int price);
    void updateBook(long ISBN, int author_id, String title, String publisher_id, int publication_year, int price);
    void removeBook(long author_id);
    Book findBookByAuthorSurname(String surname);
    List<Book> getAllBooksByTitleAsc();
    void getNumberOfBooksByAuthor(String surname);
    void getBooksForLastTenYears();
    int priceOfAllBooks();

}
