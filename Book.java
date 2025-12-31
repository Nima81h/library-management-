
import java.util.Objects;

public class Book {
    private final String isbn;
    private String title;
    private String author;
    private int publishedYear;

    private boolean available;
    private String borrowerId;

    public Book(String isbn, String title, String author, int publishedYear) {
        if (isbn == null || isbn.trim().isEmpty())
            throw new IllegalArgumentException("ISBN cannot be empty.");
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Title cannot be empty.");
        if (author == null || author.trim().isEmpty())
            throw new IllegalArgumentException("Author cannot be empty.");
        if (publishedYear < 0)
            throw new IllegalArgumentException("Published year cannot be negative.");

        this.isbn = isbn.trim();
        this.title = title.trim();
        this.author = author.trim();
        this.publishedYear = publishedYear;

        this.available = true;
        this.borrowerId = null;
    }

    public void borrow(String borrowerId) {
        if (!available) {
            throw new IllegalStateException("Book is already borrowed.");
        }
        if (borrowerId == null || borrowerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Borrower ID cannot be empty.");
        }
        this.available = false;
        this.borrowerId = borrowerId.trim();
    }

    public void returnBook() {
        if (available) {
            throw new IllegalStateException("Book is not currently borrowed.");
        }
        this.available = true;
        this.borrowerId = null;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublishedYear() { return publishedYear; }
    public boolean isAvailable() { return available; }
    public String getBorrowerId() { return borrowerId; }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Title cannot be empty.");
        this.title = title.trim();
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty())
            throw new IllegalArgumentException("Author cannot be empty.");
        this.author = author.trim();
    }

    public void setPublishedYear(int publishedYear) {
        if (publishedYear < 0)
            throw new IllegalArgumentException("Published year cannot be negative.");
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + publishedYear +
                ", available=" + available +
                (available ? "" : ", borrowerId='" + borrowerId + '\'') +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
