import java.util.ArrayList;
import java.util.List;


public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void deleteBook(String title){
        books.removeIf(book -> book.getTitle().equals(title));
    }

    public Book findBook(String title){
        for (Book book : this.books){
            if (book.getTitle().equals(title)){
                return book;
            }
        }
        return null;
    }

}
