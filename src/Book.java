//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Book {
    private String type;
    private String title;
    private String author;
    private int year; //Rok vydani
    private String availabilityStatus; // Vypujceno, K dispozici
    private String genre;   // Pouze pro romany
    private int forGrade;   // Pouze pro ucebnice



//Konstruktor
    public Book(String type, String title, String author, int year, String availabilityStatus, String genre, int forGrade ) {
        this.type = type;
        this.title = title;
        this.author = author;
        this.year = year;
        this. availabilityStatus = availabilityStatus;
        if (genre !=null) {
            this.genre = genre;
        }
        else {
            this.forGrade = forGrade;
        }
    }

//Predpripravene Gettery a Settery
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }
    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getGenre() { return genre; }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getForGrade() { return forGrade; }
    public void setForGrade(int forGrade) {
        this.forGrade = forGrade;
    }


}