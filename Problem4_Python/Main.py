class Author:
    def __init__(self):
        self.name = ""

    def __init__(self, name):
        self.name = name

    def get_name(self):
        return self.name

    def set_name(self, name):
        self.name = name


class Book:
    def __init__(self):
        self.title = ""
        self.location_code = ""
        self.year_pub = 0
        self.publisher = ""
        self.authors = []

    def get_authors(self):
        return self.authors

    def get_publisher(self):
        return self.publisher

    def set_publisher(self, pub):
        self.publisher = pub

    def get_yer_pub(self):
        return self.year_pub

    def set_year_pub(self, pub):
        self.year_pub = pub

    def get_location_code(self):
        return self.location_code

    def set_location_code(self, code):
        self.location_code = code

    def get_title(self):
        return self.title

    def set_title(self, title):
        self.title = title

    def print(self):
        print("A book called", self.get_title(),
                "written by", self.author_list(),
                "and published in", self.get_yer_pub(), "by", self.get_publisher(), self.get_location_code())

    def author_list(self):
        list = ""
        for i in range(len(self.authors)):
            list += self.authors[i].get_name()
            list += ", "
        return list

    def add_author(self, author):
        self.authors.append(author)

    def remove_author(self, author):
        self.authors.remove(author)


class EBook(Book):
    def __init__(self):
        self.title = ""
        self.location_code = "EBook"
        self.year_pub = 0
        self.publisher = ""
        self.authors = []


class PrintedBook(Book):
    def __init__(self):
        self.title = ""
        self.location_code = ""
        self.year_pub = 0
        self.publisher = ""
        self.authors = []


def add_book():
    type = 0
    temp = EBook()

    while type != 1 and type != 2:
        type = int(input("Enter 1 for printed and 2 for electronic: "))
        if type == 1:
            temp = PrintedBook()

    temp.set_title(input("Book title: "))

    author = input("Book author: ")
    if author in authors.items():
        temp.add_author(authors.get(author))
    else:
        authors[author] = Author(author)
        temp.add_author(authors.get(author))

    temp.set_year_pub(input("Year published: "))

    temp.set_publisher(input("Book publisher: "))

    if type == 1:
        temp.set_location_code(input("Book location code: "))

    books.append(temp)
    print()

def remove_book():
    print_books()
    entry = input("Enter the title of the book you want to remove: ")

    for i in range(len(books)):
        if entry == books[i].get_title():
            books.remove(books[i])
            return

    print_books("There is no book by that title...")

def edit_book():
    check = 1
    selection = 0
    print_books()
    entry = input("Enter a title to edit.\n" +
                    "Input: ")
    while check == 1:
        for i in range(len(books)):
            if books[i].get_title() == entry:
                check = 0
                selection = i
    entry = 0

    while entry != 7:
        entry = int(input("Select an option:\n" +
                    "\t1. Change Title\n" +
                    "\t2. Change Publisher\n" +
                    "\t3. Add an Author\n" +
                    "\t4. Remove an Author\n" +
                    "\t5. Change Published Year\n" +
                    "\t6. Change Location Code\n" +
                    "\t7. Return\n" +
                    "Input: "))
        if entry == 1:
            books[selection].set_title(input("Enter a new title: "))
        elif entry == 3:
            author = input("Enter another author: ")
            if author in authors.items():
                books[selection].add_author(authors.get(author))
            else:
                authors[author] = Author(author)
                books[selection].add_author(authors.get(author))
        elif entry == 4:
            author = input("Enter an author to remove: ")
            if author in authors.keys():
                books[selection].remove_author(authors.get(author))
            else:
                print(books[selection].get_title(), "does not have author", entry)
        elif entry == 2:
            books[selection].set_title(input("Enter a new title: "))
        elif entry == 5:
            books[selection].set_year_pub(input("Enter a new published year: "))
        elif entry == 6:
            books[selection].set_location_code(input("Enter a new location code: "))

def add_author():
    entry = input("Enter an author to add to the library: ")
    if entry not in authors.keys():
        authors[entry] = Author(entry)
    else:
        print("Author is already in the library.")

def remove_author():
    entry = input("Enter an author to add to the library: ")
    if entry in authors.keys():
        authors.pop(entry)
    else:
        print("Author is not in the library.")

def print_books():
    for i in range(len(books)):
        books[i].print()
    print()

def print_authors():
    for i in authors.keys():
        print(i)
    print()

if __name__ == '__main__':
    books = []
    authors = {}
    selection = 0

    while selection != 8:
        print("Welcome to the library! Please select an operation:\n" +
                        "\t1. Add Book\n" +
                        "\t2. Edit Book\n" +
                        "\t3. Remove Book\n" +
                        "\t4. Add an Author\n" +
                        "\t5. Remove an Author\n" +
                        "\t6. Print Books\n" +
                        "\t7. Print Authors\n" +
                        "\t8. Exit\n")
        selection = int(input("Input: "))
        print()

        if selection == 1:
            add_book()
        elif selection == 2:
            edit_book()
        elif selection == 3:
            remove_book()
        elif selection == 4:
            add_author()
        elif selection == 5:
            remove_author()
        elif selection == 6:
            print_books()
        elif selection == 7:
            print_authors()
        elif selection == 8:
            print("\nThank you for using the Library! Please come again!\n")
        else:
            print("Invalid selection.")

