package codecooler.michal.com;

import java.util.Scanner;

public class LibraryController {
    Scanner scanner = new Scanner(System.in);

    public void libraryOptions(LibraryJDBCDAO libraryDAOimpl){

        System.out.println("Welcome to the personal library!");

        boolean quit = false;
        while(!quit) {
            System.out.println("\nEnter action: (6 to show available actions)");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("\nEnding reading..");
                    scanner.close();
                    quit = true;
                    break;

                case 1:
                    
                    break;

                case 2:
                    System.out.println("Coming soon also");
                    break;

                case 3:
                    System.out.println("Choose what book you want to remove by title");
                    String userInputTitle = scanner.nextLine();
//                    libraryDAOimpl.removeBook(userInputTitle);
                    break;

                case 4:
                    System.out.println("Select author");
                    String userInput = scanner.nextLine();
//                    libraryDAOimpl.findBookByAuthorSurname(userInput);
                    break;

                case 5:
//                    libraryDAOimpl.showAllBooksByTitleAsc();
                    break;

                case 6:
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
                "4  - To find book by author surname.\n" +
                "5  - To show all books by title ascending.\n" +
                "6  - To print a list of available actions.");
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
}
