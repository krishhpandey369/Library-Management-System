# Library-Management-System
This is my very first Library Management System project in Java. It manages books and members of a library. Librarian can add/remove books, add members, see details. Member can view books, borrow, return and pay fine. I used the classes, objects, ArrayList and exception handling to build it.
# Library Management System 📚

Hey guys this is my Library Management System project which I made in Java using OOP concepts. I made this for my college assignment and also for learning how real projects work. It is fully console based menu driven program. No GUI nothing just simple black screen with options 😄

I choose this topic because library system is easy to understand and has many different functions like adding books, issuing books, calculating fine etc. I wanted to practice classes, objects, ArrayList, encapsulation and all those things which we study in class but never use properly.

## Why I made this project 🤔

In college we mostly write small programs like factorial, prime number, simple calculator. But I always wanted to make one complete working project where many classes work together. So one day I decided to make Library Management System.

I searched many projects on internet but most of them were either too complicated or copy paste. So I thought why not make my own from scratch. I spent many days on this project. Sometimes code was not working, sometimes logic was wrong, but finally it is working good 🔥

## What this project can do

This system has two type of users:

### 1. Librarian 👨‍💼

Librarian is like admin of library. He can do many things:

- Register new members by taking name, age and phone number
- Remove any member by entering their ID
- Add new books with title, author name and number of copies
- Remove books using Book ID
- See complete list of all members with their borrowed books and fine amount
- See all books present in library with available copies

When librarian register new member, system first check if that phone number is already registered or not. If phone number already exist then it will show duplicate registration message. This way same person cannot register two times.

When any member is removed, all books which that member had issued are automatically returned back to library. issuedCopies of those books get decreased.

### 2. Member 👨‍🎓

Members are normal users who come to library for taking books.

First they need to login using their registered name and phone number. If both match then they can enter member menu.

After login member can:

- See list of all available books (only those which have free copies)
- See books which they currently borrowed
- Issue new book (maximum 2 books allowed)
- Return any book which they issued
- Pay pending fine

## Important Rules I put in system ⚠️

I added some rules so that system look realistic:

1. One member can issue maximum 2 books only at one time. If they already have 2 books then system will not allow more.

2. Every book has time limit of 10 seconds. I made 1 second = 1 day so that testing become easy. In real life it will be 10 days but for testing 10 second is perfect.

3. After 10 second fine start. Fine is Rs 3 for every extra second. So if someone return book after 15 second then fine will be (15-10)*3 = Rs 15.

4. If member has any pending fine then he cannot issue new book until he pay the fine. This is very important rule.

5. Phone number must be exactly 10 digits and only numbers. Name should have only alphabets and spaces. No number or special character allowed in name.

6. Same phone number cannot be used for two different members.

## Classes I created in this project 🛠️

I put all classes in one single file so that compiling become easy. File name is LibraryPortal.java

### 1. Library Class
This is main storage class. It has two private ArrayList:
- One for storing all Book objects
- One for storing all Member objects

Other classes take data from this class only.

### 2. Librarian Class
This class has all functions which librarian can perform:
- addBook()
- removeBook()
- registerMember()
- removeMember()
- listBooks()
- listMembers()

I used two PriorityQueue here for managing unique IDs. When any book or member is deleted, their ID is added back in PriorityQueue. Next time when new book or member is added, system first check PriorityQueue. If any old ID is available then it reuse that ID. This way ID numbers stay small and clean.

### 3. Book Class
Simple class for storing book details:
- title
- author
- totalCopies
- issuedCopies
- unique Book ID

When new book is created issuedCopies start from 0.

### 4. IssuedBook Class
This is special class. Whenever member issue any book, one IssuedBook object is created.

This class keep:
- reference of original Book
- timeElapsed (how many second passed after issuing)
- timeMax (fixed 10 second)
- fine amount

I used ScheduledExecutorService in this class. It start a timer which run every 1 second. Every second timeElapsed increase by 1. When timeElapsed become more than 10, fine start calculating automatically.

### 5. Member Class
This class store everything about one member:
- name
- age
- phone number
- unique Member ID
- list of IssuedBook (books he borrowed)
- balance (pending fine amount)

It has many methods like:
- issueBook()
- returnBook()
- listBorrowedBooks()
- listAvailableBooks()
- calFine()
- payFine()
- compare methods for name, phone and ID

### 6. Main Class
This is starting point of whole program. It show main menu and handle all user inputs.

It has two big methods:
- enterLibrarian() → show librarian menu
- enterMember() → show member menu

Also has two helper methods:
- isAlpha() → check if name has only letters and space
- isPhone() → check if phone number is valid 10 digit

## How to run this project 💻

Very simple steps:

1. First make sure you have JDK installed (Java 8 or above)
2. Copy the code and save it as LibraryPortal.java
3. Open command prompt or terminal in same folder
4. Type this command:
