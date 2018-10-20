import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.security.Key;
import java.util.*;

public class App {
    static Map<String, Author> authors = new HashMap<String, Author>();
    static ArrayList<Book> books = new ArrayList();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){

        int input = 0;

        while (1 == 1){ // Exit case
            System.out.print("Welcome to the library! Please select an operation:\n" +    // Display menu
                    "\t1. Add Book\n" +
                    "\t2. Edit Book\n" +
                    "\t3. Remove Book\n" +
                    "\t4. Add an Author\n" +
                    "\t5. Remove an Author\n" +
                    "\t6. Print Books\n" +
                    "\t7. Print Authors\n" +
                    "\t8. Exit\n" +
                    "Input: ");
            input = ((int) sc.nextInt());   // get user input
            sc.nextLine();

            if (input == 1) addBook();      // Menu logic
            else if (input == 2) editBook();
            else if (input == 3) removeBook();
            else if (input == 4) addAuthor();
            else if (input == 5) removeAuthor();
            else if (input == 6) printBooks();
            else if (input == 7) printAuthors();
            else if (input == 8) {
                System.out.println("\nThank you for using the Library! Please come again!\n");
                return;
            }
            else System.out.println("Invalid selection, try again.");
        }
    }

    static public void addBook(){       // Add book to library
        String input = "";
        int type = 0;
        Book temp = new EBook();

        do {
            System.out.print("Enter 1 for printed and 2 for electronic: ");
            type = sc.nextInt();
            sc.nextLine();
            if (type != 1 && type != 2) System.out.println("Invalid input");
        } while (type != 1 && type != 2);

        if (type == 1) temp = new PrintedBook();

        System.out.print("Book Title: ");
        input = sc.nextLine();
        temp.setTitle(input);

        System.out.print("Book Author: ");
        input = sc.nextLine();
        if (authors.containsKey(input)) {
            temp.addAuthor(authors.get(input));
        }
        else {
            authors.put(input, new Author(input));
            temp.addAuthor(authors.get(input));
        }

        System.out.print("Year published: ");
        temp.setYearPub(((int) sc.nextInt()));
        sc.nextLine();

        System.out.print("Book Publisher: ");
        temp.setPublisher(new Publisher(sc.nextLine()));

        if (type == 1) {
            System.out.print("Book location code: ");
            temp.setLocationCode(sc.nextLine());
        }

        books.add(temp);
    }

    static public void editBook(){      // Edit and existing book
        int input = 0, selction = 0;
        String entry = "";
        boolean check = false;

        do {
            printBooks();
            System.out.print("Enter a title to edit.\n" +
                    "Input: ");
            entry = sc.nextLine();

            for (int i = 0; i < books.size(); ++i){
                if (entry.equals(books.get(i).getTitle())) {
                    check = true;
                    selction = i;
                }
            }

        } while (!check);

        while (input != 7){ // Exit case
            System.out.print("Select an option:\n" +
                    "\t1. Change Title\n" +
                    "\t2. Change Publisher\n" +
                    "\t3. Add an Author\n" +
                    "\t4. Remove an Author\n" +
                    "\t5. Change Published Year\n" +
                    "\t6. Change Location Code\n" +
                    "\t7. Return\n" +
                    "Input: ");

            input = ((int) sc.nextInt());   // get user input
            sc.nextLine();

            if (input == 1) {
                System.out.print("Enter new title: ");
                books.get(selction).setTitle(sc.nextLine());
            }
            else if (input == 2) {
                System.out.print("Enter new publisher: ");
                books.get(selction).setPublisher(new Publisher (sc.nextLine()));
            }
            else if (input == 3){
                System.out.print("Enter another author: ");
                entry = sc.nextLine();
                if (authors.containsKey(entry)) {
                    books.get(selction).addAuthor(authors.get(entry));
                }
                else {
                    authors.put(entry, new Author(entry));
                    books.get(selction).addAuthor(authors.get(entry));
                }
            }else if (input == 4){
                System.out.print("Enter an author to remove: ");
                entry = sc.nextLine();
                if (authors.containsKey(entry)) {
                    books.get(selction).removeAuthor(authors.get(entry));
                }
                else {
                    System.out.println("Book " + books.get(selction).getTitle() + " does not have author " + entry + ".");
                }
            }
            else if (input == 5) {
                System.out.print("Enter new published year: ");
                books.get(selction).setYearPub(sc.nextInt());
                sc.nextLine();
            }
            else if (input == 6) {
                System.out.print("Enter new Location Code: ");
                if (books.get(selction).getLocationCode() != "EBook") {
                    books.get(selction).setLocationCode(sc.nextLine());
                }
                else {
                    System.out.println("This book is an EBook and can not have a location code.");
                }
            }
            else if (input == 7) {
                return;
            }
            else System.out.println("Invalid selection, try again.");
        }
    }

    static public void removeBook(){    // Remove a book form the library
        System.out.print("Enter the title of the book you want to remove: ");
        String input = sc.nextLine();
        
        for (int i = 0; i < books.size(); ++i){
            if (input.equals(books.get(i).getTitle())) {
                books.remove(i);
                return;
            }
        }
        System.out.println("There is no book by that title...");
    }

    static public void addAuthor(){     // Add a author to the library
        System.out.print("Enter an author to add to the library: ");
        String input = sc.nextLine();
        if (!authors.containsKey(input)){
            authors.put(input, new Author(input));
        }
        else {
            System.out.println("Author is already in the library.");
        }
    }

    static public void removeAuthor(){     // Add a author to the library
        System.out.print("Enter an author to remove from the library: ");
        String input = sc.nextLine();
        if (authors.containsKey(input)){
            authors.remove(input);
            for (int i = 0; i < books.size(); ++i){
                if (books.get(i).getAuthors().contains(authors.get(input))){
                    books.get(i).getAuthors().remove(authors.get(input));
                }
            }
        }
        else {
            System.out.println("Author is not in the library.");
        }
    }

    static public void printBooks(){
        for (int i = 0; i < books.size(); ++i) {
            System.out.println(books.get(i));
        }
    }

    static public void printAuthors(){
        System.out.println(authors.values());
    }
}