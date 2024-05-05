//import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.sql.ResultSet;


public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    //Pridani knihy
    public void addBook(Book book) {
        this.books.add(book);
    }

    //Vymazani knihy
    public void deleteBook(String title) {
        books.removeIf(book -> book.getTitle().equals(title));
    }

    //Uprava knihy
    public Book findBook(String title) {
        for (Book book : this.books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public void sortBooksAlphabetically() {
        Collections.sort(this.books, Comparator.comparing(Book::getTitle));
    }

    //Vypis knih
    public String listBooks() {
        StringBuilder sb = new StringBuilder();
        for (Book book : this.books) {
            sb.append("Nazev: ");
            sb.append(book.getTitle());
            sb.append(", Autor: ");
            sb.append(book.getAuthor());
            sb.append(", Rok: ");
            sb.append(book.getYear());
            //Byl pridan vypis zanr/rocnik
            if (book.getGenre() != null) {
                sb.append(", Zanr: ");
                sb.append(book.getGenre());
            } else {
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

    public void saveBooks(Book book) {
        try {
            String fileName = book.getTitle().replaceAll("\\s+", "_") + ".txt";
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println("Title: " + book.getTitle());
            writer.println("Author: " + book.getAuthor());
            writer.println("Year: " + book.getYear());
            writer.println("Availability Status: " + book.getAvailabilityStatus());
            //if ("Roman".equals(book.getType())) {
            //            writer.println("Genre: " + book.getGenre());
            //        } else if ("Ucebnice".equals(book.getType())) {
            //            writer.println("Suitable for grade: " + book.getForGrade());
            //        }
            if (book.getGenre() != null) {
                writer.write(book.getGenre() + "\n");
            } else {
                writer.write(book.getForGrade() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Chyba pri zapisovani do souboru.");
            e.printStackTrace();
        }
    }

    public void loadBook(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String type = reader.readLine().split(": ")[1];
            String title = reader.readLine().split(": ")[1];
            String author = reader.readLine().split(": ")[1];
            int year = Integer.parseInt(reader.readLine().split(": ")[1]);
            String availabilityStatus = reader.readLine().split(": ")[1];
            String genre = null;
            int forGrade = 0;
            if ("Roman".equals(type)) {
                genre = reader.readLine().split(": ")[1];
            } else if ("Ucebnice".equals(type)) {
                forGrade = Integer.parseInt(reader.readLine().split(": ")[1]);
            }
            Book book = new Book(type, title, author, year, availabilityStatus, genre, forGrade);
            this.addBook(book);
            reader.close();
        } catch (IOException e) {
            System.out.println("Chyba pri cteni ze souboru.");
            e.printStackTrace();
        }


    }

    public void saveBooksToDatabase() {
        String url = "jdbc:mysql://localhost:"; // URL databaze
        String user = "JEMNO12"; // replace with your username
        String password = "test12"; // replace with your password

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            for (Book book : this.books) {
                String sql = "INSERT INTO books (type, title, author, year, availabilityStatus, genre, forGrade) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, book.getType());
                pstmt.setString(2, book.getTitle());
                pstmt.setString(3, book.getAuthor());
                pstmt.setInt(4, book.getYear());
                pstmt.setString(5, book.getAvailabilityStatus());
                pstmt.setString(6, book.getGenre());
                pstmt.setInt(7, book.getForGrade());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error pri nacteni knih do databaze.");
            e.printStackTrace();
        }
    }

    public void loadBooksFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/"; // URL adresu databaze
        String user = "JMENO12"; // uzivatelske jmeno
        String password = "test12"; // heslo

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT * FROM books";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int year = rs.getInt("year");
                String availabilityStatus = rs.getString("availabilityStatus");
                String genre = rs.getString("genre");
                int forGrade = rs.getInt("forGrade");

                Book book = new Book(type, title, author, year, availabilityStatus, genre, forGrade);
                this.addBook(book);
            }
        } catch (SQLException e) {
            System.out.println("Error pri nacteni knih z databaze.");
            e.printStackTrace();
        }
    }
}

