public class Book {
    private String name;
    private String author;
    private String ISBN;
    private boolean isBorrowed;
    public Book(String name, String author, String ISBN){
        this.name=name;
        this.author=author;
        this.ISBN=ISBN;
        isBorrowed=false;
    }
    public void borrowBook(){
        if (!isBorrowed){
            isBorrowed=true;
        }else {
            System.out.println("the book is not available");
        }
    }
    public void returnBook(){
        isBorrowed=false;
    }

    @Override
    public String toString() {
        return "Book:" +
                "name=" + name + '\'' +
                "author=" + author + '\'' +
                "ISBN=" + ISBN +
                "isBorrowed=" + isBorrowed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
}
