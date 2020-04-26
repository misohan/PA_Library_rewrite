package codecooler.michal.com;

import java.util.List;

public interface LibraryDAO {
    void addBookToLibrary(long ISBN, int author_id, String title, String publisher_id, int publication_year, int price);
    void updateBookInLibrary(long ISBN, int author_id, String title, String publisher_id, int publication_year, int price);
    void removeBook(long author_id);
    void findBookByAuthorSurname(String surname);
    List<Book> showAllBooksByTitleAsc();
    void numberOfBooksByAuthor(String surname);
    void showBooksForLastTenYears();
    int priceOfAllBooks();

}
