import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// =====================================================================
// This file merges Book.java, Member.java, IssuedBook.java, Library.java,
// Librarian.java, and Main.java into a single compilable source file.
// Only one top-level class may be public in a .java file, so Main is
// public and the rest are package-private (default access) classes
// defined below it. Behavior is unchanged from the original files.
//
// Compile:  javac LibraryPortal.java
// Run:      java Main
// =====================================================================

// Main CLASS
public class Main {

    // For checking whether the String contains only Alphabets or not
    public static boolean isAlpha(String str) {
        return (str != null) && (str.matches("^[a-zA-Z ]*$"));
    }

    // Checking whether the input is a correct Phone No.
    public static boolean isPhone(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        if (str.length() != 10) {
            return false;
        }

        return true;
    }

    // If we Enter As Librarian
    public static void enterLibrarian(Librarian lib, Library library) throws IOException {
        // do-while loop until we press 7

        do {

            // Giving all the Options that a Librarian Can Do
            System.out.println("1. Register a Member");
            System.out.println("2. Remove a Member");
            System.out.println("3. Add a Book");
            System.out.println("4. Remove a Book");
            System.out.println("5. View all Members along with their Books and Fines to be Paid");
            System.out.println("6. View all Books");
            System.out.println("7. Back");
            System.out.println("--------------------------------");

            // Taking Input from the user
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String inp = reader.readLine();

            System.out.println("--------------------------------");
            Scanner sc = new Scanner(System.in);
            if (inp.equals("1") || inp.equals("2") || inp.equals("3") || inp.equals("4") || inp.equals("5")
                    || inp.equals("6") || inp.equals("7")) {
                // Case 1 --> Register A Member
                if (inp.equals("1")) {

                    // Name Input
                    System.out.print("Name : ");
                    String name = reader.readLine();

                    // Checking Validity
                    if (!isAlpha(name)) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid Name");
                        System.out.println("--------------------------------");

                        continue;
                    }

                    // Age input
                    int age;
                    try {
                        System.out.print("Age : ");
                        age = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid Integer Value of Age");
                        System.out.println("--------------------------------");

                        continue;
                    }

                    // Phone Input
                    String phone;
                    System.out.print("Phone No : ");
                    phone = reader.readLine();

                    // Checking Validity
                    if (!isPhone(phone)) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid Phone Number");
                        System.out.println("--------------------------------");

                        continue;
                    }

                    // Calling the Function to actually Register Member
                    lib.registerMember(name, age, phone, library);

                } else if (inp.equals("2")) { // 2. Remove A Member

                    // Name Input
                    System.out.print("Name : ");
                    String name = reader.readLine();

                    // Checking Validity
                    if (!isAlpha(name)) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid User Name");
                        System.out.println("--------------------------------");
                        continue;
                    }

                    // ID Input
                    int ID;
                    try {
                        System.out.print("Enter the ID of the Member : ");
                        ID = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Enter a Valid Member ID");
                        System.out.println("--------------------------------");
                        continue;
                    }

                    // Calling the Function to Actually Remove Member
                    lib.removeMember(ID, library);

                } else if (inp.equals("3")) { // 3. Add Book

                    // Title Input
                    System.out.print("Title of the Book : ");
                    String title = reader.readLine();

                    // Author Input
                    System.out.print("Author of the Book : ");
                    String author = reader.readLine();
                    // Checking Validity
                    if (!isAlpha(author)) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid Author Name");
                        System.out.println("--------------------------------");
                        continue;
                    }

                    // TotalCopies Input
                    int totalCopies;
                    try {
                        System.out.print("Total Copies : ");
                        totalCopies = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid Integer Value");
                        System.out.println("--------------------------------");
                        continue;
                    }

                    // Calling the Function to actually add the Book to the Library
                    lib.addBook(title, author, totalCopies, library);

                } else if (inp.equals("4")) { // 4. Removing a Book

                    // ID input
                    int ID;
                    try {
                        System.out.print("Enter ID of the Book that You want to Remove: ");
                        ID = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Enter a Valid Book ID");
                        System.out.println("--------------------------------");
                        continue;
                    }

                    // Calling the function to actually remove the book from the Library
                    lib.removeBook(ID, library);

                } else if (inp.equals("5")) { // 5. View All the Members

                    lib.listMembers(library);

                } else if (inp.equals("6")) { // 6. View All the Books

                    lib.listBooks(library);

                } else if (inp.equals("7")) { // 7. Exiting from the Library Interface
                    return;

                }
            }

            else {
                System.out.println("--------------------------------");
                System.out.println("ERROR: Incorrect Input");
                System.out.println("--------------------------------");
                continue;
            }


        } while (true);

    }

    // ENTERING AS MEMBER
    public static void enterMember(Library library, Librarian lib, Member m) throws IOException {
        // do while loop until the user presses 6
        do {
            // Giving all the Options that a Member Can Do
            System.out.println("1. List Available Books");
            System.out.println("2. List My Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Pay Fine");
            System.out.println("6. Back");
            System.out.println("--------------------------------");

            // Taking Input from the user
            Scanner sc = new Scanner(System.in);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String inp = reader.readLine();
            System.out.println("--------------------------------");

            if (inp.equals("1") || inp.equals("2") || inp.equals("3") || inp.equals("4") || inp.equals("5")
                    || inp.equals("6")) {
                if (inp.equals("1")) {

                    // 1. List All the Available Books
                    m.listAvailableBooks(library);

                } else if (inp.equals("2")) {

                    // 2. List All the Books Borrowed by the Member
                    m.listBorrowedBooks();

                } else if (inp.equals("3")) {
                    // 3. Issuing a Book

                    // Checking if the fine is zero or not, as cannot issue if not zero
                    int fine = m.getFine();

                    // If Due Amount is not zero
                    if (fine > 0) {

                        System.out.println("\nYour have Amount Due to be Paid. Please pay the due amount to continue issuing books");

                        System.out.println("--------------------------------");
                    } else if (m.getBorrowedBooks().size() >= 2) {

                        // Cannot borrow books More than 2
                        System.out.println("Sorry, You already have Two Books Issued. To continue issuing kindly return one of the books");
                        System.out.println("--------------------------------");
                        continue;
                    } else {

                        // Actually Issuing a Book
                        lib.listBooks(library);

                        if (library.getAllBooks().size() == 0) {
                            continue;
                        }
                        // Book ID input
                        int ID;
                        try {
                            System.out.print("Enter ID of the Book that You want to Issue: ");
                            ID = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("--------------------------------");
                            System.out.println("ERROR: Enter a Valid Book ID");
                            System.out.println("--------------------------------");
                            continue;
                        }

                        // Calling the function to actually issue the Book
                        m.issueBook(ID, library);

                    }

                } else if (inp.equals("4")) {
                    // 4. Return Books

                    // Printing a list of all the Borrowed Books
                    m.listBorrowedBooks();
                    ArrayList<IssuedBook> ibk = m.getBorrowedBooks();
                    if (ibk.size() == 0) {
                        continue;
                    }

                    // ID input
                    int ID;
                    try {
                        System.out.print("Enter ID of the Book that You want to Return: ");
                        ID = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: InValid Book ID");
                        System.out.println("--------------------------------");
                        continue;
                    }

                    // Calling the function to return the Book
                    m.returnBook(ID);

                } else if (inp.equals("5")) {

                    // 5. Payment of Fine
                    m.payFine();

                } else if (inp.equals("6")) {
                    // Returning to the main base
                    return;

                }

            }

            else {
                System.out.println("--------------------------------");
                System.out.println("ERROR: Invalid Input");
                System.out.println("--------------------------------");
            }



        } while (true);
    }

    // MAIN FUNCTION
    public static void main(String args[]) throws IOException {

        // Creating a Library And A Librarian
        Library library = new Library();
        Librarian lib = new Librarian();

        System.out.println("--------------------------------");
        System.out.println("Library Portal Initialized...");
        System.out.println("--------------------------------");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("1. Enter as Librarian\n2. Enter as a Member\n3. Exit");
            System.out.println("--------------------------------");

            String inp = reader.readLine();
            System.out.println("--------------------------------");

            if (inp.equals("1") || inp.equals("2") || inp.equals("3")) {
                if (inp.equals("1")) {
                    enterLibrarian(lib, library);
                } else if (inp.equals("2")) {

                    System.out.print("Name : ");
                    String name = reader.readLine();

                    // checking for valid inputs
                    if (!isAlpha(name)) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid User Name");
                        System.out.println("--------------------------------");
                        return;
                    }

                    String phone;
                    System.out.print("Phone No : ");
                    phone = sc.next();
                    if (!isPhone(phone)) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid Phone Number");
                        System.out.println("--------------------------------");
                        continue;
                    }

                    ArrayList<Member> ml = library.getAllMembers();
                    boolean flag = false;
                    for (int i = 0; i < ml.size(); i++) {
                        Member m = ml.get(i);

                        if (m.cmpName(name) && m.cmpPhone(phone)) {

                            System.out.println("\nWelcome " + name + "!!\nMember ID : " + m.getID() + "\n");
                            System.out.println("--------------------------------");
                            enterMember(library, lib, m);

                            flag = true;
                            break;
                        }
                    }

                    if (flag == false) {
                        System.out.println("--------------------------------");
                        System.out.println(

                                "Member with -- Name : " + name + " and Phone No. : " + phone + " Does Not Exists");
                        System.out.println("--------------------------------");
                        continue;

                    }
                } else if (inp.equals("3")) {

                    System.out.println("--------------------------------");
                    System.out.println("Thank-you for Visiting!!");
                    System.out.println("You have Successfully Exited from the Library Portal");
                    System.out.println("--------------------------------");

                    System.exit(1);
                }
            } else {
                System.out.println("--------------------------------");
                System.out.println("ERROR: Wrong Input");
                System.out.println("--------------------------------");
            }

        } while (true);


    }
}


// CLASS BOOK
class Book {

    private String title;
    private String author;
    int totalCopies;
    int issuedCopies;
    private int ID;

    public Book(String title, String author, int totalCopies, int bookID) {
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.ID = bookID;
    }

    // GETTER METHODS
    public String getTitle() {return this.title;}

    public String getAuthor() {return this.author;}

    public int getBookID() {return this.ID;}

}


// CLASS FOR ISSUED BOOKS
class IssuedBook {
    private Book book;
    private int timeElapsed;
    private int timeMax;
    private int fine;

    public IssuedBook(Book book) {
        this.book = book;
        this.fine = 0;
        this.timeElapsed = 0;
        this.timeMax = 10;

        // Calculates the Fine for each issued Book separately
        ScheduledExecutorService exe = Executors.newSingleThreadScheduledExecutor();

        exe.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                timeElapsed++;
                if (timeElapsed > timeMax) {
                    int diff = timeElapsed - timeMax;
                    fine = diff * 3;
                }
            }

        }, 1, 1, TimeUnit.SECONDS);
    }

    public Book getBook() {
        return this.book;
    }
    public int retFine() {
        return this.fine;
    }
}


// CLASS MEMBER
class Member {

    private String name;
    private int age;
    private String phoneNo;
    private ArrayList<IssuedBook> booksBorrowed;
    private static int balance;
    private int ID;

    public Member(String name, int age, String phoneNo, int memID) {
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
        this.booksBorrowed = new ArrayList<IssuedBook>();
        this.balance = 0;
        this.ID = memID;
    }

    // GETTERS
    public String getName() {return this.name;}

    public ArrayList<IssuedBook> getBorrowedBooks() {return booksBorrowed;}

    public int getID() {return this.ID;}

    public int getAge() {return this.age;}

    public String getPhone() {return this.phoneNo;}

    public int getFine() {return this.calFine();}

    // COMPARING FUNCTIONS
    public boolean cmpName(String name2) {
        if (this.name.equals(name2)) {
            return true;
        }
        return false;
    }

    public boolean cmpPhone(String phone2) {
        if (this.phoneNo.equals(phone2)) {
            return true;
        }
        return false;
    }
    public boolean cmpID(int id) {
        if (this.ID == id) {
            return true;
        }
        return false;
    }


    // ISSUE A BOOK
    public void issueBook(int bookID, Library library) {

        System.out.println("--------------------------------");

        // List of all Books
        ArrayList<Book> bc = library.getAllBooks();

        // No Books in the Library
        if (bc.size() == 0) {
            System.out.println("No Books Available in the Library!");
            System.out.println("--------------------------------");
            return;
        }

        // If Balance != 0
        if (this.getFine() != 0) {
            System.out.println("Sorry, You cannot Issue!\nYour Balance Amount is not paid");
            System.out.println("--------------------------------");
            return;
        }

        // Issuing the Book
        for (int i = 0; i < bc.size(); i++) {
            Book book = bc.get(i);
            if (book.getBookID() == bookID) {
                if (book.issuedCopies == book.totalCopies) {
                    System.out.println("All the copies present in the library of BookID - " + bookID + " have already been issued");
                    System.out.println("--------------------------------");
                    return;
                }
                IssuedBook newIssuedBook = new IssuedBook(book);
                this.booksBorrowed.add(newIssuedBook);
                book.issuedCopies += 1;
                System.out.println("The Book with ID - " + bookID + " has been successfully Issued");
                System.out.println("--------------------------------");
                return;
            }
        }

        // No book exists with given bookID
        System.out.println("Book not found in the library");
        System.out.println("--------------------------------");
    }


    // RETURNING THE ISSUED BOOK
    public void returnBook(int bookID) {
        System.out.println("--------------------------------");
        boolean flag = false;

        // Searching Book with ID = bookID
        for (int i = 0; i < this.booksBorrowed.size(); i++) {
            IssuedBook bk = this.booksBorrowed.get(i);
            int f = bk.retFine();
            if (bk.getBook().getBookID() == bookID) {
                if (bk.retFine() == 0) {
                    System.out.println("Book ID : " + bk.getBook().getBookID() + " has been successfully returned.");
                    System.out.println("You Returned the Book on Time. No Fine to be Paid!!");
                    System.out.println("--------------------------------");
                } else {
                    System.out.println("Book ID : " + bk.getBook().getBookID() + " has been successfully returned. Rs. " + f + " have been charged for delay of " + f / 3 + " days.");
                    System.out.println("--------------------------------");
                }
                this.booksBorrowed.remove(i);
                bk.getBook().issuedCopies -= 1;
                flag = true;
                this.balance += f;
                return;
            }
        }

        // If the book with "bookID" was not issued by the Member
        if (flag == false) {
            System.out.println("You have not Issued any Book with ID --> " + bookID);
            System.out.println("--------------------------------");
        }
    }


    // LIST ALL THE BORROWED BOOKS
    public void listBorrowedBooks() {

        // If no book borrowed
        if (this.booksBorrowed.size() == 0) {
            System.out.println("You have not Borrowed any Book!!");
            System.out.println("--------------------------------");
            return;
        }

        // Printing books + Properties
        for (int i = 0; i < this.booksBorrowed.size(); i++) {
            Book book = this.booksBorrowed.get(i).getBook();
            System.out.println("Name - " + book.getTitle());
            System.out.println("Book ID - " + book.getBookID());
            System.out.println("Author - " + book.getAuthor());
            System.out.println();
            System.out.println("       ***");
        }
        System.out.println("--------------------------------");
    }


    // LISTING AVAILABLE BOOKS
    public void listAvailableBooks(Library library) {

        System.out.println("--------------------------------");

        // Getting list of all the books in the Library
        ArrayList<Book> bc = library.getAllBooks();

        // If no books in the Library
        if (bc.size() == 0) {
            System.out.println("No Books in the Library Currently!");
            System.out.println("--------------------------------");
            return;
        }

        // Printing Books + Properties
        for (int i = 0; i < bc.size(); i++) {
            Book book = bc.get(i);
            if(book.issuedCopies != book.totalCopies) {
                System.out.println();
                System.out.println("Book ID - " + book.getBookID());
                System.out.println("Name - " + book.getTitle());
                System.out.println("Author - " + book.getAuthor());

                System.out.println("Copies Available for Issuing - " + (book.totalCopies - book.issuedCopies));
                System.out.println();
                System.out.println("       ***");
            }
        }
        System.out.println("--------------------------------");

    }


    // Calculates the Total Fine
    public int calFine() {
        int f = 0;
        for (int i = 0; i < this.booksBorrowed.size(); i++) {
            IssuedBook ib = this.booksBorrowed.get(i);
            if (ib.retFine() != 0) {
                System.out.println((i+1) + ". Book : '" + ib.getBook().getTitle() + "' | Fine: " + ib.retFine());
            }
            f += ib.retFine();
        }
        f += this.balance;
        return f;
    }


    // PAY FINE
    public void payFine() {
        System.out.println("--------------------------------");
        int f = this.calFine();
        if (f != 0) {
            System.out.println("\nOverall Fine : Rs. " + f);

            if (this.balance != 0) {
                System.out.println("\nYou had a Balance Due of : " + this.balance + "." );
                this.balance = 0;
                System.out.println("It has been paid successfully!!");
                System.out.println("--------------------------------");
            } else {
                System.out.println("\nNo Balance is Due!!. Please return the books whose return date has been exceeded");            }
                System.out.println("--------------------------------");
        } else {
            System.out.println("NO Balance is Due!!");
            System.out.println("--------------------------------");
        }

    }
}


// LIBRARY CLASS
class Library {

    // List of all Books and Members in the Library
    private ArrayList<Book> bookCollection = new ArrayList<>();
    private ArrayList<Member> memberList = new ArrayList<>();

    // Returns an Arraylist of all the books in the Library
    public ArrayList<Book> getAllBooks() {
        return bookCollection;
    }

    // Returns an Arraylist of all the members registered in the Library
    public ArrayList<Member> getAllMembers() {
        return memberList;
    }
}


class Librarian {

    // Priority Queues for Correct Assigning of "memberID" and "bookID"
    private static PriorityQueue<Integer> pq1 = new PriorityQueue<>();
    private static PriorityQueue<Integer> pq2 = new PriorityQueue<>();
    private static int memberID = 1;
    private static int bookID = 1;


    // ADDING BOOK TO THE LIBRARY
    public void addBook(String title, String author, int totalCopies, Library library) {

        System.out.println("--------------------------------");

        int i=0;
        while (i<totalCopies) {
            if (!pq2.isEmpty()) {

            int a = pq2.remove();
            Book bk = new Book(title, author, 1, a);
            library.getAllBooks().add(bk);


            } else {

                Book bk = new Book(title, author, 1, bookID);
                library.getAllBooks().add(bk);
                bookID++;

            }
            i++;
        }

        System.out.println("The Book - '" + title + "' added Successfully!! ");
        System.out.println("--------------------------------");


    }

    // REMOVE BOOK FROM LIBRARY
    public void removeBook(int bookID, Library library) {

        System.out.println("--------------------------------");

        // Getting list of all Books
        boolean flag = false;
        ArrayList<Book> bc = library.getAllBooks();
        ArrayList<Member> ml = library.getAllMembers();

        // Removing it from borrowed list of all members
        for (int i = 0; i < ml.size(); i++) {
            Member m = ml.get(i);
            ArrayList<IssuedBook> bb = m.getBorrowedBooks();
            for (int j = 0; j < bb.size(); j++) {
                IssuedBook ibk = bb.get(j);
                int id = ibk.getBook().getBookID();
                if (id == bookID) {
                    bb.remove(j);
                    break;
                }
            }
        }

        // Removing the book from the Library
        for (int i = 0; i < bc.size(); i++) {
            Book b = bc.get(i);

            if (b.getBookID() == bookID) {
                pq2.add(b.getBookID());
                bc.remove(i);
                flag = true;
                System.out.println("The Book - '" + b.getTitle() + "' was Successfully Removed!!");
                System.out.println("--------------------------------");
                break;
            }
        }

        // If the Book ID not found
        if (!flag) {
            System.out.println("The book ID - " + bookID + " is not Available in the Library!!");
            System.out.println("--------------------------------");
        }
    }


    // REGISTER A NEW MEMBER
    public void registerMember(String name, int age, String phoneNo, Library library) {

        System.out.println("--------------------------------");

        // Getting a list of all Members
        ArrayList<Member> ml = library.getAllMembers();

        // Checking for Duplicate Registrations
        for (int i = 0; i < ml.size(); i++) {
            Member m = ml.get(i);
            if (m.cmpPhone(phoneNo)) {
                System.out.println("Duplicate Registration!! Phone No. " + phoneNo + " already Registered.");
                System.out.println("--------------------------------");
                return;
            }
        }

        if (!pq1.isEmpty()) {

            int a = pq1.remove();
            Member newMember = new Member(name, age, phoneNo, a);
            library.getAllMembers().add(newMember);
            System.out.println("Member Successfully Registered with Member ID : " + newMember.getID() + "!!");
            System.out.println("--------------------------------");

        } else {

            Member newMember = new Member(name, age, phoneNo, memberID);
            library.getAllMembers().add(newMember);
            System.out.println("Member Successfully Registered with Member ID : " + newMember.getID() + "!!");
            memberID++;
            System.out.println("--------------------------------");
        }

    }

    // REMOVING MEMBER FROM LIBRARY
    public void removeMember(int memberID, Library library) {

        System.out.println("--------------------------------");

        // Getting list of all Registered Members
        boolean flag = false;
        ArrayList<Member> ml = library.getAllMembers();

        // Removing all the issued books from the Member's Borrowed list
        for (int i = 0; i < ml.size(); i++) {
            Member m = ml.get(i);
            if (m.cmpID(memberID)) {
                flag = true;
                ArrayList<IssuedBook> bb = m.getBorrowedBooks();
                for (int j = 0; j < bb.size(); j++) {
                    Book b = bb.get(j).getBook();
                    b.issuedCopies -= 1;
                    bb.remove(j);
                }
                // Removing the member from the list
                ml.remove(i);
                System.out.println("The Member : " + m.getName() + " with ID: " + memberID + " was Successfully Removed!!");
                pq1.add(m.getID());
                System.out.println("--------------------------------");
                return;
            }
        }

        // If a member with given ID does not exists
        if (!flag) {
            System.out.println("Member with Member ID: " + memberID + " Does Not Exist!!");
            System.out.println("--------------------------------");
        }
    }

    // LIST ALL BOOKS IN LIBRARY
    public void listBooks(Library library) {

        System.out.println("--------------------------------");

        // Getting list of all the books in the Library
        ArrayList<Book> bc = library.getAllBooks();

        // If no books in the Library at Present
        if (bc.size() == 0) {
            System.out.println("No Books in the Library Currently!");
            System.out.println("--------------------------------");
            return;
        }

        // Printing All the Properties of the Books prent in the Library
        for (int i = 0; i < bc.size(); i++) {
            Book book = bc.get(i);
            System.out.println();
            System.out.println("Book ID - " + book.getBookID());
            System.out.println("Name - " + book.getTitle());
            System.out.println("Author - " + book.getAuthor());

            System.out.println("Copies Available for Issuing - " + (book.totalCopies - book.issuedCopies));
            System.out.println();
            System.out.println("       ***");
        }
        System.out.println("--------------------------------");

    }

    // LIST ALL MEMBERS
    public void listMembers(Library library) {

        System.out.println("--------------------------------");

        // Getting list of all the Members
        ArrayList<Member> ml = library.getAllMembers();

        // IF no members
        if (ml.size() == 0) {
            System.out.println("No Members Currently Registered in the Library");
            System.out.println("--------------------------------");
            return;
        }

        // Printing All the Information of the Members registered in the Library
        for (int i = 0; i < ml.size(); i++) {
            Member member = ml.get(i);

            System.out.println("Member ID - " + member.getID());
            System.out.println("Name - " + member.getName());
            System.out.println("Age - " + member.getAge());
            System.out.println("Phone No - " + member.getPhone());
            System.out.println();
            System.out.print("Books Borrowed --> ");
            ArrayList<IssuedBook> bb = member.getBorrowedBooks();
            if (bb.size() == 0) {
                System.out.print("No Books Borrowed!!");
            } else {
                for (int j = 0; j < bb.size(); j++) {
                    IssuedBook bk = bb.get(j);
                    System.out.print("\n" + (j + 1) + ") " + bk.getBook().getTitle());
                }
            }

            System.out.println("\n\nFINE --> ");
            System.out.println("Balance Due - " + member.getFine());

            System.out.println();
            System.out.println("       ***");
            System.out.println();
        }

        System.out.println("--------------------------------");
    }
}
