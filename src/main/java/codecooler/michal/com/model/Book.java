package codecooler.michal.com.model;

import java.util.ArrayList;

public class Book extends ArrayList<Book> {
    private long isbn;
    private int author_id;
    private String title;
    private String publisher_id;
    private int publication_year;
    private int price;

    public Book() {
    }

    public Book(long isbn, int author_id, String title, String publisher_id, int publication_year, int price) {
        this.isbn = isbn;
        this.author_id = author_id;
        this.title = title;
        this.publisher_id = publisher_id;
        this.publication_year = publication_year;
        this.price = price;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(String publisher_id) {
        this.publisher_id = publisher_id;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
