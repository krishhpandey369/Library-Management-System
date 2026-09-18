# Library-Management-System
This is my very first Library Management System project in Java. It manages books and members of a library. Librarian can add/remove books, add members, see details. Member can view books, borrow, return and pay fine. I used the classes, objects, ArrayList and exception handling to build it.
# Library Management System

This is my Library Management System project which I made in Java using Object Oriented Programming. It is a console based application. I made this project for understanding how different classes work together and how we can manage data using ArrayList and other things.

In this system there is two type of user. One is Librarian and other is Member. Librarian can control the books and members. Member can issue book, return book and pay fine if they return late.

I tried to make it simple so that anyone can understand the code easily.

## Why I made this project

I wanted to practice OOP concepts like class, object, encapsulation, constructor etc. Also I wanted to make one complete working project instead of just small programs. Library system is common project so I decided to make this.

In this project I used:
- ArrayList for storing books and members
- PriorityQueue for giving unique ID and reusing deleted ID
- ScheduledExecutorService for calculating time and fine
- BufferedReader and Scanner for taking input
- Simple validation for name and phone number

## Features of the Project

### Things Librarian can do

1. Register a new Member  
   Librarian enter name, age and phone number of member. System check if phone number is already registered or not. If not then new member is added and unique Member ID is given.

2. Remove a Member  
   Librarian enter Member ID. If member exist then he is removed. All books which that member issued are also returned back to library automatically.

3. Add a Book  
   Librarian enter book title, author name and how many copies. System create that many book entries and give unique Book ID to each.

4. Remove a Book  
   Librarian enter Book ID. If book exist then it is removed from library. If any member has that book then it is also removed from his borrowed list.

5. View all Members  
   This option show all registered members. For every member it show name, age, phone, books he borrowed and total fine he has to pay.

6. View all Books  
   This option show all books present in library with Book ID, title, author and how many copies are still available for issuing.

### Things Member can do

1. List Available Books  
   Member can see all books which have at least one copy free for issuing.

2. List My Books  
   Member can see all books which he currently borrowed.

3. Issue Book  
   Member can issue a book by entering Book ID.  
   But there is some condition:  
   - Member should not have any pending fine  
   - Member should not already have 2 books  
   - Book should have available copies  

4. Return Book  
   Member enter Book ID of book he want to return.  
   If book is returned on time then no fine.  
   If returned late then fine is calculated and added to his balance.

5. Pay Fine  
   Member can see total fine and pay the balance amount.

## Important Rules I Put in System

- Maximum 2 books can be issued to one member at one time.
- Every book has time limit of 10 seconds. I made 1 second equal to 1 day so that testing become easy.
- After 10 second fine start. Fine is Rs 3 for every extra second.
- If member has any balance fine then he cannot issue new book until he pay the fine.
- Same phone number cannot be used for two different members.
- Name should contain only alphabets and spaces. No numbers or special character allowed.
- Phone number must be exactly 10 digits and only numbers.

## Classes I Created

I put all classes in one file so that it is easy to compile and run.

### 1. Library Class
This class has two private ArrayList.  
One ArrayList store all Book objects.  
Second ArrayList store all Member objects.  
It has two simple methods to return these lists.

### 2. Librarian Class
This is most important class for admin work.  
It has methods for:
- addBook()
- removeBook()
- registerMember()
- removeMember()
- listBooks()
- listMembers()

I used two PriorityQueue for managing IDs.  
When any book or member is deleted, its ID is put back in PriorityQueue so that ID can be reused later. This way ID number does not become very big.

### 3. Book Class
This class store information of one book:
- title
- author
- totalCopies
- issuedCopies
- unique ID

Constructor take title, author, total copies and ID.  
issuedCopies start from 0.

### 4. IssuedBook Class
Whenever member issue a book, one IssuedBook object is created.  
This class keep:
- reference of Book
- timeElapsed (how many second passed)
- timeMax (fixed 10 second)
- fine amount

I used ScheduledExecutorService here. It run a task every 1 second.  
Every second timeElapsed increase by 1.  
When timeElapsed become more than 10, fine start calculating as (timeElapsed - 10) * 3.

### 5. Member Class
This class store:
- name
- age
- phone number
- unique Member ID
- list of IssuedBook
- balance (pending fine amount)

It has many methods:
- issueBook()
- returnBook()
- listBorrowedBooks()
- listAvailableBooks()
- calFine()
- payFine()
- some compare methods for name, phone and ID

### 6. Main Class
This is starting point of program.  
It show main menu and handle all user input.  
It also has two helper methods:
- isAlpha() → check if name has only letters and space
- isPhone() → check if phone is 10 digit number

There is two big methods:
- enterLibrarian() → show librarian menu and call related functions
- enterMember() → show member menu and call related functions

## How to Compile and Run

1. Make sure JDK is installed on your system (Java 8 or higher is ok).
2. Copy the code and save it as Main.java
3. Open command prompt or terminal in same folder.
4. Type this command to compile:
