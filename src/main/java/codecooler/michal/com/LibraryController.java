package codecooler.michal.com;

import java.util.Scanner;

public class LibraryController {
    Scanner scanner = new Scanner(System.in);

    public void libraryOptions(){
        LibraryDAO libraryDAO = new LibraryJDBCDAO();
        LibraryView libraryView = new LibraryView();

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
                    System.out.println("Does your book have new author?(1, for yes, 0 for no)");
                    int userInputAuthor = scanner.nextInt();
                    scanner.nextLine();

                    int userInputAuthorCheck = 1;

                    if(userInputAuthor==userInputAuthorCheck)
                        addAuthor();

                    System.out.println(("Does your book have new publisher?(1, for yes, 0 for no)"));

                    int userInputPublisher = scanner.nextInt();
                    scanner.nextLine();

                    int userInputPublisherCheck = 1;

                    if(userInputPublisherCheck == userInputPublisher){
                        addPublisher();
                    }
                    addBook();
                    break;

                case 2:
                    updateBook();
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
                    printCustomersOptions();
                    break;
            }
        }
        scanner.close();
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
    public void addBook(){
        LibraryDAO libraryDAO = new LibraryJDBCDAO();

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

        libraryDAO.addBook(ISBN, author_id, title, publisher_id, publication_year, price);
    }
    public void updateBook(){
        LibraryDAO libraryDAO = new LibraryJDBCDAO();

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

        libraryDAO.updateBook(ISBN, author_id, title, publisher_id, publication_year, price);

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
