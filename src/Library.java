import java.util.ArrayList;
import java.util.List;


public class Library
{
    private List<Book> books;

    public Library()
    {
        books = new ArrayList<>();
    }

//Pridani knihy
    public void addBook(Book book)
    {
        this.books.add(book);
    }

//Vymazani knihy
    public void deleteBook(String title){
        books.removeIf(book -> book.getTitle().equals(title));
    }

//Uprava knihy
    public Book findBook(String title)
    {
        for (Book book : this.books)
        {
            if (book.getTitle().equals(title))
            {
                return book;
            }
        }
        return null;
    }

//Vypis knih
    public String listBooks(){
        StringBuilder sb = new StringBuilder();
        for (Book book : this.books){
            sb.append("Nazev: ");
            sb.append(book.getTitle());
            sb.append(", Autor: ");
            sb.append(book.getAuthor());
            sb.append(", Rok: ");
            sb.append(book.getYear());
            //Byl pridan vypis zanr/rocnik
            if (book.getGenre() != null){
                sb.append(", Zanr: ");
                sb.append(book.getGenre());
            }
            else {
                sb.append(", Pro rocnik: ");
                sb.append(book.getForGrade());
            }
//            sb.append(", Zanr: ");
//            sb.append(book.getGenre());
            sb.append(", Stav: ");
            sb.append(book.getAvailabilityStatus());
            sb.append("\n");
        }
        return sb.toString();
    }

}
