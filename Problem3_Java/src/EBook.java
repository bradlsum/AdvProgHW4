public class EBook extends Book {
    public EBook(){
        super();
    }

    public EBook(String title, String location, int yearPub,
                       Author author, Publisher publisher){
        this.setTitle(title);
        this.setLocationCode("EBook");
        this.setYearPub(yearPub);
        this.setPublisher(publisher);
        this.addAuthor(author);
    }
}
