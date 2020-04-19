package codecooler.michal.com;

public interface LibraryDAO {
    void editBookData(long ISBN, int author_id, String title, String publisher_id, int publication_year, int price);
    void removeBook(int author_id);
    void findBookByAuthorSurname(String authorSurname);
    void showAllBooksByTitleAsc();

}
