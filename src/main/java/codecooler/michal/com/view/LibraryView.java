package codecooler.michal.com.view;
import codecooler.michal.com.model.Book;

import java.util.List;
import java.util.Scanner;

public class LibraryView {
    private Scanner scanner;

    public LibraryView() {
        this.scanner = new Scanner(System.in);
    }

    public void viewBook(Book book){
        System.out.println("|¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|");
        System.out.println("|--------------------------------------|");
        System.out.println("|ISBN: " + book.getIsbn() +"                   |");
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

    public void quitApplication(){
        System.out.println("\nEnding reading..");
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

    public int askUserToChooseAction(){
        System.out.println("\nEnter action: (10 to show available actions)");
        int action = scanner.nextInt();
        scanner.nextLine();

        return action;
    }

    public Book askUserForBookData(){

        System.out.println("What is the ISBN of the book?");
        long isbn = scanner.nextLong();
        scanner.nextLine();

        System.out.println("What is the author id of the book?");
        int author_id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("What is the title of the book?");
        String title = scanner.nextLine();

        System.out.println("What is the publisher id of the book?");
        String publisher_id = scanner.nextLine();

        System.out.println("What is the publication year of the book?");
        int publication_year = scanner.nextInt();
        scanner.nextLine();

        System.out.println("What is the price of the book?");
        int price = scanner.nextInt();

        return new Book(isbn, author_id, title, publisher_id, publication_year, price);
    }

    public Book askUserToUpdateBookData(){

        System.out.println("What is the ISBN of the book?");
        long isbn = scanner.nextLong();
        scanner.nextLine();

        System.out.println("What is the author id of the book?");
        int author_id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("What is the title of the book?");
        String title = scanner.nextLine();

        System.out.println("What is the publisher id of the book?");
        String publisher_id = scanner.nextLine();

        System.out.println("What is the publication year of the book?");
        int publication_year = scanner.nextInt();
        scanner.nextLine();

        System.out.println("What is the price of the book?");
        int price = scanner.nextInt();

        return new Book(isbn, author_id, title, publisher_id, publication_year, price);
    }

    public long askUserForBookISBN(){
        System.out.println("Choose what book you want to remove by ISBN");
        long userInputISBN = scanner.nextLong();
        return userInputISBN;
    }

    public String askUserForAuthorSurname(){
        System.out.println("What is author surname?");
        String userInputAuthorSurname = scanner.nextLine();
        return userInputAuthorSurname;
    }

    public void printNumberOfBooksByAuthor(String userInputAuthor, int numberOfBooks){
        System.out.println("Number of books by " + userInputAuthor +" is " + numberOfBooks);
    }
    public void viewValueOfLibrary(int valueOfBooks){
        System.out.println("Value of your library is " + valueOfBooks);
    }
}
