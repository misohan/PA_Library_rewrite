package codecooler.michal.com;

public class Library {
    public static void main(String[] args) {

        LibraryController libraryController = new LibraryController();
        LibraryDAOImpl libraryDAO = new LibraryDAOImpl();

        libraryController.libraryOptions(libraryDAO);

    }

}
