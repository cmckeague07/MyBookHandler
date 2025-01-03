# mybookhandler
Mybookhandler is a is a Java Spring Boot-based web application that allows users to upload, edit, and manage their text-based books. The application provides a user-friendly interface for file operations such as uploading, viewing, editing, and deleting books stored on the server. It also includes features for searching and analyzing book content.

------------------------------------------------------------------------------
Features
------------------------------------------------------------------------------
Upload Books:

- Supports uploading .txt files through the web interface to local folder destinations.

- Ensures duplicate files are not uploaded.

View Books:

- Displays the content of uploaded books in a scrollable text area.

- Automatically calculates and displays document length and word count.

Edit Books:

- Allows editing the content of uploaded books directly from the interface.

- Saves edited content back to the local.

Delete Books:

- Users can delete unwanted books from the local.

Search Functionality:

- Highlights and counts keyword occurrences within book content.

Content Analysis:

- Identifies and displays the most and least used words in the text.

------------------------------------------------------------------------------
Technologies Used
------------------------------------------------------------------------------

Backend:

- Java Spring Boot

Frontend:

- HTML, CSS, JavaScript

- Bootstrap for responsive design

------------------------------------------------------------------------------
How to Run
------------------------------------------------------------------------------
Clone the repository:

- git clone https://github.com/cmckeague07/MyBookHandler.git

- Navigate to the project directory:

- cd MyBookHandler

Build and run the application:

- mvn spring-boot:run

Access the application in your browser at:

- http://localhost:8080


Backend:

- MyBookController.java: Handles user interactions such as uploading, deleting, and editing books.

- MybookhandlerApplication.java: Initializes and runs the Spring Boot application.

Frontend:

- index.html: The main interface for interacting with the application.

- style.css and myStyle.css: Styling for the web pages.

- scripts.js: Implements client-side functionality, including search, file loading, and word analysis.

------------------------------------------------------------------------------
Application Screenshot
------------------------------------------------------------------------------

![image](https://github.com/user-attachments/assets/9d702e78-fb1b-41e8-b0c1-26b2a353017b)

