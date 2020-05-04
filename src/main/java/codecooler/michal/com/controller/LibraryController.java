package codecooler.michal.com.controller;

import codecooler.michal.com.DAO.BookDAO;
import codecooler.michal.com.DAO.BookJDBCDAO;
import codecooler.michal.com.LibraryDAO;
import codecooler.michal.com.LibraryJDBCDAO;
import codecooler.michal.com.model.Book;
import codecooler.michal.com.view.LibraryView;

import java.util.Scanner;

public class LibraryController {
    Scanner scanner = new Scanner(System.in);

    public void libraryOptions(){
        LibraryView libraryView = new LibraryView();
        LibraryDAO libraryDAO = new LibraryJDBCDAO();

        BookDAO bookDAO = new BookJDBCDAO();

        System.out.println("Welcome to the personal library!");

        boolean quit = false;
        while(!quit) {
            System.out.println("\nEnter action: (10 to show available actions)");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("\nEnding reading..");
                    scanner.close();
                    quit = true;
                    break;

                case 1:
                    bookDAO.createBook(libraryView.getBookDataFromUser());
                    break;

                case 2:
                    bookDAO.updateBook(libraryView.getBookDataFromUser());
                    break;

                case 3:
                    removeBook();
                    break;

                case 4:
                    getBookByAuthorSurname();
                    break;

                case 5:
                    libraryView.viewBooks(libraryDAO.getAllBooksByTitleAsc());
                    break;

                case 6:
                    getNumberOfBooksByAuthor();
                    break;

                case 7:
                    libraryView.viewBooks(libraryDAO.getBooksForLastTenYears());
                    break;

                case 8:
                    System.out.println("Value of library is: "+ libraryDAO.getPriceOfAllBooks());
                    break;

                case 9:
                    System.out.println("In update.");
                    break;

                case 10:
                    libraryView.printCustomersOptions();
                    break;
            }
        }
        scanner.close();
    }


    public void removeBook(){
        LibraryDAO libraryDAO = new LibraryJDBCDAO();

        System.out.println("Choose what book you want to remove by ISBN");
        long userInputISBN = scanner.nextLong();
        libraryDAO.removeBook(userInputISBN);
        System.out.println("Book removed.");
    }
    public void getBookByAuthorSurname(){
        LibraryDAO libraryDAO = new LibraryJDBCDAO();
        LibraryView libraryView = new LibraryView();

        System.out.println("What is author surname?");
        String userInputAuthor = scanner.nextLine();
        Book book = libraryDAO.getBookByAuthorSurname(userInputAuthor);

        libraryView.viewBook(book);

    }
    public void getNumberOfBooksByAuthor(){
        LibraryDAO libraryDAO = new LibraryJDBCDAO();

        System.out.println("What is author surname?");
        String userInputAuthor = scanner.nextLine();
        int numberOfBooks = libraryDAO.getNumberOfBooksByAuthor(userInputAuthor);
        System.out.println("Number of books by " + userInputAuthor +" is " + numberOfBooks);
    }
    public void addAuthor(){
        LibraryDAO libraryDAO = new LibraryJDBCDAO();

        System.out.println("What is author first name?");
        String userInputName = scanner.nextLine();

        System.out.println("What is author surname name?");
        String userInputSurname = scanner.nextLine();

        libraryDAO.addAuthor(userInputName, userInputSurname);
    }
    public void addPublisher(){
        LibraryDAO libraryDAO = new LibraryJDBCDAO();

        System.out.println("What is publisher id?");
        String userInputID = scanner.nextLine();

        System.out.println("What is publisher name?");
        String userInputName = scanner.nextLine();

        libraryDAO.addPublisher(userInputID, userInputName);
    }
}
