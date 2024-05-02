import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        boolean run = true;
        while (run){
            System.out.println("Vyberte z menu knihovny: ");
            System.out.println("1 .. Nova kniha");
            System.out.println("2 .. Uprava knihy");
            System.out.println("3 .. Smazani knihy");
            System.out.println("4 .. Oznaceni knihy: Vypujcena/Vracena");
            System.out.println("5 .. Vypis knih dle...");
            System.out.println("6 .. Vyhledej knihu");
            System.out.println("7 .. Uloz knihy do souboru");
            System.out.println("8 .. Nacteni knihy ze souboru");

            int volba = sc.nextInt();
            sc.nextLine(); // zkonzumujeme zbytek radku

            switch (volba){
                case 1:
                    System.out.println("Jedna se o Roman/Ucebnici?");
                    String type = sc.nextLine();
                    System.out.println("Zadej nazev knihy: ");
                    String title = sc.nextLine();
                    System.out.println("Zadej autora knihy: ");
                    String author = sc.nextLine();
                    System.out.println("Zadej rok vydani knihy: ");
                    int year = sc.nextInt();
                    //sc.nextLine();
                    String availabilityStatus = sc.nextLine();
                    System.out.println("Zadej zanr knihy pro kategorii Roman");
                    String genre = sc.nextLine();
                    System.out.println("Zadej pro ktery rocnik je uccebnice vhodna");
                    int forGrade = sc.nextInt();
                    sc.nextLine(); // zkonzumujeme zbytek radku

                    Book newBook = new Book(type ,title ,author, year, availabilityStatus,genre, forGrade);
                    library.addBook(newBook);
                    System.out.println("Gratuluji, kniha uspesne pridana!");

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
                        System.out.println("Kniha nenalezena.");
                    }
                    break;

                case 3:
                    System.out.println("Zadej nazev knihy, pro smazani: ");
                    String titleDelete= sc.nextLine();
                    library.deleteBook(titleDelete);
                    System.out.println("Kniha byla uspesne smazana!");
                    break;

            }

        }
    }
}
