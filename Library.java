import java.util.*;

public class Library {
    private final Map<String, Book> booksByIsbn = new HashMap<>();
    private final Map<String, Member> membersById = new HashMap<>();

    public void addBook(Book book) {
        Objects.requireNonNull(book, "Book cannot be null.");
        if (booksByIsbn.containsKey(book.getIsbn()))
            throw new IllegalArgumentException("Book with this ISBN already exists: " + book.getIsbn());
        booksByIsbn.put(book.getIsbn(), book);
    }

    public Book getBookByIsbn(String isbn) {
        if (isbn == null) return null;
        return booksByIsbn.get(isbn.trim());
    }

    public List<Book> listAllBooks() {
        List<Book> list = new ArrayList<>(booksByIsbn.values());
        list.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        return list;
    }

    public boolean removeBook(String isbn) {
        Book book = getBookByIsbn(isbn);
        if (book == null) return false;
        if (!book.isAvailable())
            throw new IllegalStateException("Cannot remove a borrowed book. Return it first.");
        booksByIsbn.remove(book.getIsbn());
        return true;
    }

    public void addMember(Member member) {
        Objects.requireNonNull(member, "Member cannot be null.");
        if (membersById.containsKey(member.getMemberId()))
            throw new IllegalArgumentException("Member with this ID already exists: " + member.getMemberId());
        membersById.put(member.getMemberId(), member);
    }

    public Member getMemberById(String memberId) {
        if (memberId == null) return null;
        return membersById.get(memberId.trim());
    }

    public List<Member> listAllMembers() {
        List<Member> list = new ArrayList<>(membersById.values());
        list.sort(Comparator.comparing(Member::getName, String.CASE_INSENSITIVE_ORDER));
        return list;
    }

    public boolean removeMember(String memberId) {
        Member m = getMemberById(memberId);
        if (m == null) return false;

        // Ensure member has no borrowed books
        for (Book b : booksByIsbn.values()) {
            if (!b.isAvailable() && m.getMemberId().equals(b.getBorrowerId())) {
                throw new IllegalStateException("Cannot remove member: they currently borrowed book ISBN " + b.getIsbn());
            }
        }
        membersById.remove(m.getMemberId());
        return true;
    }

    public void borrowBook(String isbn, String memberId) {
        Book book = getBookByIsbn(isbn);
        if (book == null) throw new NoSuchElementException("Book not found: " + isbn);

        Member member = getMemberById(memberId);
        if (member == null) throw new NoSuchElementException("Member not found: " + memberId);

        book.borrow(member.getMemberId());
    }

    public void returnBook(String isbn) {
        Book book = getBookByIsbn(isbn);
        if (book == null) throw new NoSuchElementException("Book not found: " + isbn);
        book.returnBook();
    }

    public List<Book> searchBooksByTitle(String keyword) {
        String k = (keyword == null) ? "" : keyword.trim().toLowerCase();
        List<Book> results = new ArrayList<>();
        for (Book b : booksByIsbn.values()) {
            if (b.getTitle().toLowerCase().contains(k)) results.add(b);
        }
        results.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        return results;
    }

    public List<Book> listBorrowedBooks() {
        List<Book> results = new ArrayList<>();
        for (Book b : booksByIsbn.values()) {
            if (!b.isAvailable()) results.add(b);
        }
        results.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        return results;
    }
}
