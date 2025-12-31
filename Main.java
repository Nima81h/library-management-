//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();
        seedDemoData(library);

        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = readInt(sc, "Choose option: ");

            try {
                switch (choice) {
                    case 1: addBookFlow(sc, library); break;
                    case 2: listBooksFlow(library); break;
                    case 3: searchBooksFlow(sc, library); break;
                    case 4: removeBookFlow(sc, library); break;

                    case 5: addMemberFlow(sc, library); break;
                    case 6: listMembersFlow(library); break;
                    case 7: removeMemberFlow(sc, library); break;

                    case 8: borrowFlow(sc, library); break;
                    case 9: returnFlow(sc, library); break;
                    case 10: listBorrowedFlow(library); break;

                    case 0:
                        System.out.println("Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid option.");
                }
            } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Library Management System ===");
        System.out.println("1) Add Book");
        System.out.println("2) List All Books");
        System.out.println("3) Search Books by Title");
        System.out.println("4) Remove Book (must be available)");

        System.out.println("5) Add Member");
        System.out.println("6) List All Members");
        System.out.println("7) Remove Member (must have no borrowed books)");

        System.out.println("8) Borrow Book");
        System.out.println("9) Return Book");
        System.out.println("10) List Borrowed Books");

        System.out.println("0) Exit");
    }

    private static void addBookFlow(Scanner sc, Library library) {
        System.out.println("\n-- Add Book --");
        String isbn = readLine(sc, "ISBN: ");
        String title = readLine(sc, "Title: ");
        String author = readLine(sc, "Author: ");
        int year = readInt(sc, "Published Year: ");

        library.addBook(new Book(isbn, title, author, year));
        System.out.println("Book added.");
    }

    private static void listBooksFlow(Library library) {
        System.out.println("\n-- All Books --");
        List<Book> books = library.listAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book b : books) System.out.println(b);
    }

    private static void searchBooksFlow(Scanner sc, Library library) {
        System.out.println("\n-- Search Books --");
        String keyword = readLine(sc, "Keyword in title: ");
        List<Book> results = library.searchBooksByTitle(keyword);
        if (results.isEmpty()) {
            System.out.println("No matches.");
            return;
        }
        for (Book b : results) System.out.println(b);
    }

    private static void removeBookFlow(Scanner sc, Library library) {
        System.out.println("\n-- Remove Book --");
        String isbn = readLine(sc, "ISBN: ");
        boolean removed = library.removeBook(isbn);
        System.out.println(removed ? "Book removed." : "Book not found.");
    }

    private static void addMemberFlow(Scanner sc, Library library) {
        System.out.println("\n-- Add Member --");
        String id = readLine(sc, "Member ID: ");
        String name = readLine(sc, "Name: ");
        String email = readLine(sc, "Email (optional): ");

        library.addMember(new Member(id, name, email));
        System.out.println("Member added.");
    }

    private static void listMembersFlow(Library library) {
        System.out.println("\n-- All Members --");
        List<Member> members = library.listAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }
        for (Member m : members) System.out.println(m);
    }

    private static void removeMemberFlow(Scanner sc, Library library) {
        System.out.println("\n-- Remove Member --");
        String id = readLine(sc, "Member ID: ");
        boolean removed = library.removeMember(id);
        System.out.println(removed ? "Member removed." : "Member not found.");
    }

    private static void borrowFlow(Scanner sc, Library library) {
        System.out.println("\n-- Borrow Book --");
        String isbn = readLine(sc, "ISBN: ");
        String memberId = readLine(sc, "Member ID: ");

        library.borrowBook(isbn, memberId);
        System.out.println("Borrowed successfully.");
    }

    private static void returnFlow(Scanner sc, Library library) {
        System.out.println("\n-- Return Book --");
        String isbn = readLine(sc, "ISBN: ");
        library.returnBook(isbn);
        System.out.println("Returned successfully.");
    }

    private static void listBorrowedFlow(Library library) {
        System.out.println("\n-- Borrowed Books --");
        List<Book> borrowed = library.listBorrowedBooks();
        if (borrowed.isEmpty()) {
            System.out.println("No borrowed books.");
            return;
        }
        for (Book b : borrowed) System.out.println(b);
    }


    private static String readLine(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static void seedDemoData(Library library) {
        library.addBook(new Book("978-0134685991", "Effective Java", "Joshua Bloch", 2018));
        library.addBook(new Book("978-0132350884", "Clean Code", "Robert C. Martin", 2008));
        library.addBook(new Book("978-0201633610", "Design Patterns", "GoF", 1994));

        library.addMember(new Member("M001", "Nima", "nima@example.com"));
        library.addMember(new Member("M002", "Alex", "alex@example.com"));
    }
}
