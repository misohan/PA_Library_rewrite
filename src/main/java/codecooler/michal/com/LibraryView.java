package codecooler.michal.com;

import java.util.List;

public class LibraryView {

    public void viewBook(Book book){
        System.out.println("====================");
        System.out.println("|ISBN: " + book.getISBN());
        System.out.println("|Author id: " + book.getAuthor_id());
        System.out.println("|Title: " + book.getTitle());
        System.out.println("|Publisher id: " + book.getPublisher_id());
        System.out.println("|Publication year: " + book.getPublication_year());
        System.out.println("|Price: " + book.getPrice());
        System.out.println("=====================");

    }
    public void viewBooks(List<Book> books){
        for (Book book: books){
            viewBook(book);
        }
    }
}
