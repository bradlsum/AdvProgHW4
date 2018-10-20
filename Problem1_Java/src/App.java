public class App {
    public static void main(String[] args){

        Author a1 = new Author("John");
        Publisher p = new Publisher("Pearson");
        Author a2 = new Author("Luke");

        System.out.println("Creating two authors (John and Luke) and one publisher (Pearson)");

        System.out.println(a1);
        System.out.println(a2);
        System.out.println(p);


        System.out.printf("%n%nCreating one book%n");
        PrintedBook b1 = new PrintedBook("Book1","Shelf3", 1999,a1, p);

        System.out.println(b1); //inspect the toString method in the class Book.

        System.out.printf("%n%nAdding author Ben and removing author John%n");
        b1.addAuthor(new Author("Ben"));
        b1.removeAuthor(a1);
        System.out.println(b1); //inspect the toString method in the class Book.

        System.out.printf("%n%nCreating another book.%n");

        PrintedBook b2 = new PrintedBook("Book2","Shelf3", 1999,a2, p);

        System.out.println(b2);

        System.out.printf("%n%nChanging Luke (author) name to 'New Luke'%n");
        a2.setName("New Luke");
        System.out.println(b2);

        System.out.printf("%n%nAdding Luke2 and Luke3 to book%n");
        b2.addAuthor(new Author("Luke2"));
        b2.addAuthor(new Author("Luke3"));
        System.out.println(b2);
    }
}
