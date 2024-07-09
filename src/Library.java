import java.util.ArrayList;
import java.util.Objects;

public class Library {
    private ArrayList<Book> books;
    public Library(){
        books=new ArrayList<>();
    }
    public void addBook(Book book){
        books.add(book);
    }
    public void removeBook(String ISBN){
        for (Book book:books){
            if (Objects.equals(book.getISBN(), ISBN)){
                books.remove(book);
                break;
            }
        }
    }
    public void borrowBook(String ISBN){
        for (Book book:books) {
            if (Objects.equals(book.getISBN(), ISBN)) {
                book.borrowBook();
            }
        }
    }
    public void returnBook(String ISBN){
        for (Book book:books) {
        if (Objects.equals(book.getISBN(), ISBN)) {
            book.returnBook();
        }
     }
    }
    public Book findBook(String ISBN){
        for (Book book:books){
            if (Objects.equals(book.getISBN(), ISBN)){
                return book;
            }
        }
        return null;
    }
    public ArrayList<Book> getBooks() {
        return books;
    }

}
