package model;

import util.BorrowingHistory;

public class Book {
    private int id;
    private String titre;
    private  String auteur;
    private String isbn;
    private  Integer anneePublication;
    private String genre;
    private BorrowingHistory history;


    //Constructeur vide
    public Book(){
        this.history = new BorrowingHistory();
    }

    //Constructeur complet
    public Book(int id, String titre, String auteur, String isbn, Integer anneePublication, String genre) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.anneePublication = anneePublication;
        this.genre = genre;
        this.history = new BorrowingHistory();
    }

    //Constructeur complet (sans id)
    public Book(String titre, String auteur, String isbn, Integer anneePublication, String genre) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.anneePublication = anneePublication;
        this.genre = genre;
        this.history = new BorrowingHistory();
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(Integer anneePublication) {
        this.anneePublication = anneePublication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public BorrowingHistory getHistory() {
        return history;
    }

    public void addBorrower(String name) {
        this.history.addBorrower(name);
    }

    // toString
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", isbn='" + isbn + '\'' +
                ", anneePublication=" + anneePublication +
                ", genre='" + genre + '\'' +
                '}';
    }
}
