package codecooler.michal.com;

public interface LibraryDAO {
    void addBookToLibrary(int ISBN, int author_id, String title, String publisher_id, int publication_year, int price);
    void editBookData(long ISBN, int author_id, String title, String publisher_id, int publication_year, int price);
    void removeBook(int author_id);
    void findBookByAuthorSurname(String surname);
    void showAllBooksByTitleAsc();
    void numberOfBooksByAuthor(String surname);
    void showBooksForLastTenYears();
    void priceOfAllBooks();

}
