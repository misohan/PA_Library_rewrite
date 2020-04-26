package codecooler.michal.com;

import java.util.List;

public interface LibraryDAO {
    void addBook(long ISBN, int author_id, String title, String publisher_id, int publication_year, int price);
    void updateBook(long ISBN, int author_id, String title, String publisher_id, int publication_year, int price);
    void removeBook(long author_id);
    Book getBookByAuthorSurname(String surname);
    List<Book> getAllBooksByTitleAsc();
    int getNumberOfBooksByAuthor(String surname);
    List<Book> getBooksForLastTenYears();
    int getPriceOfAllBooks();
    void addAuthor(String first_name, String surname);
    void addPublisher(String ID, String name);


}
