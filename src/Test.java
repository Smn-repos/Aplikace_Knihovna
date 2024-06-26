import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        //Preddefinovane knihy
        Book greatGatsby = new Book("Roman", "The Great Gatsby", "F. Scott Fitzgerald", 1925, "Dostupna", "Novel", 9);
        library.addBook(greatGatsby);
//       Book harryPotter = new Book("Roman", "Harry Potter", "J.K. Rowling", 1997, "Dostupna", "Fantasy", 6);
//        library.addBook(harryPotter);
        Book javaProgramming = new Book("Ucebnice", "Java Programming", "John Doe", 2020, "Dostupna", null, 0);
        library.addBook(javaProgramming);
        Book calculus = new Book("Ucebnice", "Calculus", "John Doe", 2020, "Dostupna", null, 0);
        library.addBook(calculus);

        boolean run = true;
        while (run){
            System.out.println("Vyberte z menu knihovny: ");
            System.out.println("1 .. Nova kniha");
            System.out.println("2 .. Uprava knihy");
            System.out.println("3 .. Smazani knihy");
            System.out.println("4 .. Oznaceni knihy: Vypujcena/Vracena");
            System.out.println("5 .. Vypis knihy dle...");
            System.out.println("6 .. Vyhledej knihu");
            System.out.println("7 .. Uloz knihy do souboru");
            System.out.println("8 .. Nacteni knihy ze souboru");
            System.out.println("9 .. KONEC");

            int volba = sc.nextInt();
            sc.nextLine(); // zkonzumujeme zbytek radku

            String title = "";
            String genre = "";

            switch (volba){
                case 1:
                    try {
                        System.out.println("Jedna se o Roman/Ucebnici?");
                        String type = sc.nextLine();
                        System.out.println("Zadej nazev knihy: ");
                        title = sc.nextLine();
                        System.out.println("Zadej autora knihy: ");
                        String author = sc.nextLine();
                        System.out.println("Zadej rok vydani knihy: ");
                        int year = sc.nextInt();
                        //sc.nextLine();
                        String availabilityStatus = sc.nextLine();
                        System.out.println("Zadej zanr knihy pro kategorii Roman");
                        genre = sc.nextLine();
                        System.out.println("Zadej pro ktery rocnik je uccebnice vhodna");
                        int forGrade = sc.nextInt();
                        sc.nextLine(); // zkonzumujeme zbytek radku

                        Book newBook = new Book(type, title, author, year, availabilityStatus, genre, forGrade);
                        library.addBook(newBook);
                        System.out.println("Gratuluji, kniha uspesne pridana!");
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Spatny input. Zadej spravny typ");
                        sc.nextLine();
                    }
                    break;


                case 2:
                    System.out.println("Zadej nazev knihy, kterou chces upravit: ");
                    String titleToEdit = sc.nextLine();
                    Book bookToEdit = library.findBook(titleToEdit);
                    if (bookToEdit != null){
                        System.out.println("Zadej noveho autora: ");
                        String newAuthor = sc.nextLine();
                        System.out.println("Zadej novy rok: ");
                        int newYear = sc.nextInt();
                        sc.nextLine();
                        bookToEdit.setAuthor(newAuthor);
                        bookToEdit.setYear(newYear);
                        System.out.println("Kniha byla nove upravena.");
                    }
                    else {
                        System.out.println("Kniha Nenalezena.");
                    }
                    break;

                case 3:
                    System.out.println("Zadej nazev knihy, pro smazani: ");
                    String titleDelete= sc.nextLine();
                    library.deleteBook(titleDelete);
                    System.out.println("Kniha byla uspesne smazana!");
                    break;

                case 4:
                    System.out.println("Zadej nazev knihy: ");
                    title = sc.nextLine();
                    System.out.println("Choose the new status (Vypujcena/Dostupna): ");
                    String newStatus = sc.nextLine();
                    library.setBookStatus(title, newStatus);
                    break;

                case 5:
                    System.out.println("\nVypis knihovny: ");
                    System.out.println("1 ... Vypis vsech knih");
                    System.out.println("2 ... Vypis knih dle zanru");
                    System.out.println("3 ... Vypis vypujcenych knih");
                    int podVolba = sc.nextInt();
                    sc.nextLine();
                    //Zde dopln dalsi funkce souvisejici s Vypisem
                    switch (podVolba) {
                        case 1:
                            System.out.println("Vypis vsech knih: ");
                            System.out.println(library.listBooks());
                            break;

                            case 2:
                            System.out.println("Vyber zanr roman/ucebnice: ");
                            genre = sc.nextLine();
                            List<Book> booksByGenre = library.getBooksByGenre(genre);
                            for (Book book : booksByGenre){
                                System.out.println(book);
                            }
                            break;
                        case 3:
                            System.out.println("Vypis vypujcenych knih: ");
                            library.printBorrowedBooks();
                            break;

                        default:
                            System.out.println("Neplatna volba.");
                            break;
                    }


                case 6:
                    System.out.println("Zadej nazev knihy, kterou chces vyhledat: ");
                    String titleToSearch = sc.nextLine();
                    Book bookToSearch = library.findBook(titleToSearch);
                    if (bookToSearch != null){
                        System.out.println("Kniha nalezena");
                        System.out.println("Nazev: " + bookToSearch.getTitle());
                        System.out.println("Autor: " + bookToSearch.getAuthor());
                        System.out.println("Rok: " + bookToSearch.getYear());
                        System.out.println("Stav: " + bookToSearch.getAvailabilityStatus());
                        if (bookToSearch.getGenre() != null){
                            System.out.println("Zanr: " + bookToSearch.getGenre());
                        }
                        else {
                            System.out.println("Pro rocnik: " + bookToSearch.getForGrade());
                        }
                    }
                    else {
                        System.out.println("Kniha Nenalezena.");
                    }
                    break;

                case 7:
                    System.out.println("Zadej nazev knihy: ");
                    String titleToSave = sc.nextLine();
                    Book bookToSave = library.findBook(titleToSave);
                    if (bookToSave != null){
                        library.saveBooks(bookToSave);
                        System.out.println("Kniha byla uspesne ulozena do souboru!");
                    }
                    else {
                        System.out.println("Kniha Nenalezena.");
                    }
                case 8:
                    try{
                        System.out.println("Zadej nazev souboru: ");
                        String fileName = sc.nextLine();
                        library.loadBook(fileName);
                        System.out.println("Kniha byla uspesne nactena ze souboru!");
                    }
                    catch (NumberFormatException e){
                        System.out.println("Spatny format. Zkontroluj soubor a zkus znovu");
                    }
                    break;

                case 9:
                    System.out.println(" Nashledanou :)) ");
                    run = false;
                    break;
            }

        }
    }
}
