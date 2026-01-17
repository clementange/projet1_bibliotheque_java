package service;

import model.Book;
import repository.BookArrayRepository;
import util.ActivityStack;

import java.util.List;

public class LibraryService {

    private final BookArrayRepository repository;
    private final ActivityStack activityStack;

    public LibraryService(BookArrayRepository repository, ActivityStack activityStack) {
        this.repository = repository;
        this.activityStack = activityStack;
    }

    // Gestion des livres
    public Book ajouterLivre(Book book) {
        validerLivre(book);
        Book savedBook = repository.save(book);
        activityStack.push("Ajout du livre : " + savedBook.getTitre());
        return savedBook;
    }

    public void supprimerLivre(int id) {
        repository.delete(id);
        activityStack.push("Suppression du livre ID : " + id);
    }

    // Recherche (Search Functionality)
    public Book rechercherParTitre(String titre, boolean utiliserBinaire) {
        Book found;
        if (utiliserBinaire) {
            repository.bubbleSortByTitle();
            found = repository.binarySearchByTitle(titre);
            activityStack.push("Recherche binaire par titre : " + titre);
        } else {
            found = repository.linearSearch(titre, "titre");
            activityStack.push("Recherche linéaire par titre : " + titre);
        }
        return found;
    }

    // Tri (Sorting Functionality)
    public void trierLivres(String critere, String algorithme) {
        switch (algorithme.toLowerCase()) {
            case "bulle":
                repository.bubbleSortByTitle();
                activityStack.push("Tri par titre (Bulle)");
                break;
            case "selection":
                repository.selectionSortByAuthor();
                activityStack.push("Tri par auteur (Sélection)");
                break;
            case "rapide":
                repository.quickSortByYear(0, repository.getSize() - 1);
                activityStack.push("Tri par année (QuickSort)");
                break;
            default:
                throw new IllegalArgumentException("Algorithme non reconnu.");
        }
    }

    // Historique d'emprunt (Linked List)
    public void emprunterLivre(int id, String nomEmprunteur) {
        Book book = repository.findById(id);
        book.getHistory().addBorrower(nomEmprunteur);
        activityStack.push("Emprunt : " + book.getTitre() + " par " + nomEmprunteur);
    }

    public List<String> voirHistoriqueLivre(int id) {
        Book book = repository.findById(id);
        return book.getHistory().getBorrowers();
    }

    // Activités Récentes (Stack)
    public List<String> voirActivitesRecentes() {
        return activityStack.getAllActivities();
    }

    // Méthodes existantes conservées
    public List<Book> listerLivres() {
        return repository.findAll();
    }

    public Book trouverLivreParId(int id) {
        return repository.findById(id);
    }

    // Méthode validerLivre
    public void validerLivre(Book book) {
        if (book == null) throw new IllegalArgumentException("Le livre ne peut pas être null.");
        if (book.getTitre() == null || book.getTitre().isBlank()) throw new IllegalArgumentException("Le titre est obligatoire");
        if (book.getAuteur() == null || book.getAuteur().isBlank()) throw new IllegalArgumentException("L'auteur est obligatoire.");
        if (book.getIsbn() == null || book.getIsbn().isBlank()) throw new IllegalArgumentException("L'ISBN est obligatoire.");
    }
}