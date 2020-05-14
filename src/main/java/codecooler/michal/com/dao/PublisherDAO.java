package codecooler.michal.com.dao;

import codecooler.michal.com.model.Publisher;

import java.util.List;

public interface PublisherDAO {
    void createPublisher(Publisher publisher);
    void updatePublisher(Publisher publisher);
    List<Publisher> getPublishers();
    void deletePublisher(String id);
}
