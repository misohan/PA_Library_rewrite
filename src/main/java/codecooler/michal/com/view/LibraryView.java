package codecooler.michal.com.view;

import codecooler.michal.com.model.Book;
import java.util.List;
import java.util.Scanner;

public class LibraryView {

    public void viewBook(Book book){
        System.out.println("|¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|");
        System.out.println("|--------------------------------------|");
        System.out.println("|ISBN: " + book.getISBN() +"                   |");
        System.out.println("|--------------------------------------|");
        System.out.println("|Author id: " + book.getAuthor_id());
        System.out.println("|Title: " + book.getTitle());
        System.out.println("|Publisher id: " + book.getPublisher_id());
        System.out.println("|Publication year: " + book.getPublication_year());
        System.out.println("|Price: " + book.getPrice());
        System.out.println("----------------------------------------");

    }
    public void viewBooks(List<Book> books){
        for (Book book: books){
            viewBook(book);
        }
    }
    public void printCustomersOptions(){
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0  - To end shopping, press 0.\n" +
                "1  - To add book.\n" +
                "2  - To edit book.\n" +
                "3  - To remove.\n" +
                "4  - To find books by author surname.\n" +
                "5  - To show all books by title ascending.\n" +
                "6  - To show number of books by author.\n" +
                "7  - To display all books for last 10 years.\n"+
                "8  - To show value of library.\n" +
                "9  - To show full name of author and his age.\n" +
                "10  - To print a list of available actions.\n");
        System.out.println("Choose your action: ");
    }
    public Book getBookDataFromUser(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is ISBN of a book?");
        long ISBN = scanner.nextLong();

        System.out.println("What is author id of a book?");
        int author_id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("What is title of a book?");
        String title = scanner.nextLine();

        System.out.println("What is publisher id of a book?");
        String publisher_id = scanner.nextLine();

        System.out.println("What is publication year of a book?");
        int publication_year = scanner.nextInt();

        System.out.println("What is price of a book?");
        int price = scanner.nextInt();

        Book book = new Book(ISBN, author_id, title, publisher_id, publication_year, price);
        return book;
    }


}
