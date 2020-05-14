package codecooler.michal.com.dao;

import codecooler.michal.com.model.Author;

import java.util.List;

public interface AuthorDAO {
    void createAuthor(Author author);

    void updateAuthor(Author author);

    List<Author> getAuthors();

    void deleteAuthor(int id);
}
