package codecooler.michal.com;

public class Library {
    public static void main(String[] args) {

        LibraryDAOImpl libraryDAO = new LibraryDAOImpl();

//        libraryDAO.editBookData(97800995,6,"Hag Seed","5k4",2017,45);
        libraryDAO.removeBook(4);
        libraryDAO.showAllBooksByTitleAsc();

    }

}
