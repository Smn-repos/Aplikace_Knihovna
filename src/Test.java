import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        //Preddefinovane knihy
        Book greatGatsby = new Book("Roman", "The Great Gatsby", "F. Scott Fitzgerald", 1925, "Available", "Novel", 9);
        library.addBook(greatGatsby);
        Book harryPotter = new Book("Roman", "Harry Potter", "J.K. Rowling", 1997, "Available", "Fantasy", 6);
        library.addBook(harryPotter);
        Book javaProgramming = new Book("Ucebnice", "Java Programming", "John Doe", 2020, "Available", null, 0);
        library.addBook(javaProgramming);
        Book calculus = new Book("Ucebnice", "Calculus", "John Doe", 2020, "Available", null, 0);
        library.addBook(calculus);

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
            System.out.println("=>");
            int volba = sc.nextInt(); 
            sc.nextLine(); // zkonzumujeme zbytek radku
           
            switch (volba){
                case 1:
                	 int volbaKnihy = sc.nextInt(); 
                     sc.nextLine();
                	String type = "";
                	String genre = "";
                	int forGrade = 0;
 
                	switch (volbaKnihy) {
                	    case 1:
                	        type = "Roman";
                	        System.out.println("Zadej zanr knihy pro kategorii Roman");
                	        genre = sc.nextLine();
                	        break;
                	    case 2:
                	        type = "Ucebnice";
                	        System.out.println("Zadej pro ktery rocnik je ucebnice vhodna");
                	        forGrade = sc.nextInt();
                	        sc.nextLine();
                	        break;
                	    default:
                	        System.out.println("Neplatná volba.");
                	        return; 
                	}                    
                    System.out.println("Zadej nazev knihy: ");
                    String title = sc.nextLine();
                    System.out.println("Zadej autora knihy: ");
                    String author = sc.nextLine();
                    System.out.println("Zadej rok vydani knihy: ");
                    int year = sc.nextInt();
                    //sc.nextLine();
                    System.out.println("Zadej availabilityStatus: ");
                    String availabilityStatus = sc.nextLine();
                    sc.nextLine(); // zkonzumujeme zbytek radku

                    Book newBook = new Book(type ,title ,author, year, availabilityStatus,genre, forGrade);
                    library.addBook(newBook);
                    System.out.println("Gratuluji, kniha uspesne pridana!");
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
                        System.out.println("Kniha nenalezena.");
                    }
                    break;

                case 3:
                    System.out.println("Zadej nazev knihy, pro smazani: ");
                    String titleDelete= sc.nextLine();
                    library.deleteBook(titleDelete);
                    System.out.println("Kniha byla uspesne smazana!");
                    break;

                case 5:
                    System.out.println("Vypis knihovny: ");
                    System.out.println(library.listBooks());
                    break;
            }

        }
    }
}
