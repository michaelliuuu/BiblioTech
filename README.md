# 📖 Bibliotech 📚

#### Description:
This application is a library management system for tracking books, patrons, and transactions. Librarians can
add and remove books, manage patron information, and track checkouts and returns. Patrons can search for books,
check availability, and borrow/return books.
###
#### Who is this for?
This would be used by Librarians or Library Administrators who need to manage and maintain the library's single
or multiple collection.
###
#### Why did I make this?
I have recently started reading again when I purchased a Kindle, and while I enjoyed having all the books I needed
in one device, I realized that physical books are definitely superior. I created this application to learn how to develop
a management system and with my love for books, I decided to make it with a theme of a library.


## User Stories:
- As a Librarian, I want to **VIEW** the collection of all the books in the library.
- As a Librarian, I want to **VIEW** the collection of all the transactions in the library.
- As a Librarian, I want to be able to **ADD** books for patrons to check out.
- As a Librarian, I want to be able to **REMOVE** books when it's no longer in circulation or available for borrowing.
- As a patron, I want to be **VIEW** the collection of all the books in the library.
- As a patron, I want to be able to **CHECK OUT** a book with my library card to update its availability.
- As a patron, I want to be able to **RETURN** a book along with my library card to update its availability.
- As a user, when I click the quit option from the login menu, I want the option to **save** library within the application.
- As a user, when I start the application, I want the option to **load** the library from file.


### Instructions for Grader
- When you open the application, you are offered the option to login as a librarian or patron. First, click "Login as a librarian".
- Next, click on the "Add Book" button, fill in the appropriate information.
- Repeat to add more books if needed.
- Click "Display Library" to display all the books in library.
- Click the sort button next to the textarea to sort the books in the library by book name.
- Next, go to the "Remove Book" button, fill in the appropriate information, then click the "Remove" button.
- Go back to "Display Library" to see that the book has been removed.

- You can locate my first visual component by first entering the session or pressing the "Back to Login" when you're logged in as librarian or patron.
- You can locate my second visual component by logging in as a librarian.
-
- You can save the state of my application by logging in as a librarian and clicking "Save Application" to save the library.
- You can load the state of my application by logging in as a librarian and clicking "Load Application" to load the library.


## Phase 4: Task 2
Fri Nov 24 19:56:31 PST 2023
Added the book 'illegal' to the library.
Fri Nov 24 19:56:43 PST 2023
Added the book 'hyperion' to the library.
Fri Nov 24 19:56:47 PST 2023
Sorted the books in the library.
Fri Nov 24 19:56:58 PST 2023
Removed the book 'illegal' to the library.


## Phase 4: Task 3
If I had more time, I would have been able to improve coupling within my code by identifying methods that I reuse often and putting them into
interface/abstract classes. For example, I have several toString() methods that I could have pulled out into an abstract or interface class
which would allow me to easily change the text in one place if needed, instead of going to each class and changing them.

Regarding my GUI class, I would have loved to put more time into putting unnecessary fields in certain methods and putting them in
their own methods, to reduce the bulk of the method itself. This would allow me to easily find which method needs to be changed or not, instead of
lumping several functions within a method.
