package repository;

import exception.BookNotFoundException;
import exception.DuplicateIsbnException;
import model.Book;

import java.util.Arrays;
import java.util.List;

public class BookArrayRepository implements BookRepository {

    private Book[] database;
    private int size;
    private int compteur;

    public BookArrayRepository() {
        database = new Book[10];
        size = 0;
        compteur = 1;

        // Données initiales
        save(new Book("Le Petit Prince", "Antoine de Saint-Exupéry", "9782070404375", 1943, "Conte"));
        save(new Book("L'Étranger", "Albert Camus", "9782070360024", 1942, "Philosophie"));
        save(new Book("1984", "George Orwell", "9780451524935", 1949, "Dystopie"));
        save(new Book("Harry Potter à l'école des sorciers", "J.K. Rowling", "9782070541270", 1997, "Fantastique"));
        save(new Book("Les Misérables", "Victor Hugo", "9782070409228", 1862, "Roman social"));
    }

    // Gestion
    @Override
    public Book save(Book book) {
        if (isbnExiste(book.getIsbn())) {
            throw new DuplicateIsbnException(book.getIsbn());
        }
        if (size == database.length) {
            agrandirTableau();
        }
        book.setId(compteur++);
        database[size++] = book;
        return book;
    }

    @Override
    public Book findById(int id) {
        for (int i = 0; i < size; i++) {
            if (database[i].getId() == id) return database[i];
        }
        throw new BookNotFoundException(id);
    }

    @Override
    public List<Book> findAll() {
        return Arrays.asList(Arrays.copyOfRange(database, 0, size));
    }

    @Override
    public Book update(Book book) {
        Book existing = findById(book.getId());
        if (book.getTitre() != null) existing.setTitre(book.getTitre());
        if (book.getAuteur() != null) existing.setAuteur(book.getAuteur());
        if (book.getAnneePublication() != null) existing.setAnneePublication(book.getAnneePublication());
        return existing;
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < size; i++) {
            if (database[i].getId() == id) {
                for (int j = i; j < size - 1; j++) {
                    database[j] = database[j + 1];
                }
                database[--size] = null;
                return;
            }
        }
        throw new BookNotFoundException(id);
    }

    // RECHERCHE (Search Functionality)
    public Book linearSearch(String query, String type) {
        for (int i = 0; i < size; i++) {
            Book b = database[i];
            boolean found = false;
            switch (type.toLowerCase()) {
                case "titre": found = b.getTitre().equalsIgnoreCase(query); break;
                case "auteur": found = b.getAuteur().equalsIgnoreCase(query); break;
                case "isbn": found = b.getIsbn().equals(query); break;
            }
            if (found) return b;
        }
        return null;
    }

    public Book binarySearchByTitle(String title) {
        // Attention : Le tableau DOIT être trié par titre avant !
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int res = database[mid].getTitre().compareToIgnoreCase(title);
            if (res == 0) return database[mid];
            if (res < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    // TRI (Sorting Functionality)
    // 1. Tri à Bulles (Bubble Sort) par Titre
    public void bubbleSortByTitle() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (database[j].getTitre().compareToIgnoreCase(database[j+1].getTitre()) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    // 2. Tri par Sélection (Selection Sort) par Auteur
    public void selectionSortByAuthor() {
        for (int i = 0; i < size - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < size; j++) {
                if (database[j].getAuteur().compareToIgnoreCase(database[minIdx].getAuteur()) < 0) {
                    minIdx = j;
                }
            }
            swap(i, minIdx);
        }
    }

    // 3. Tri Rapide (QuickSort) par Année
    public void quickSortByYear(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSortByYear(low, pi - 1);
            quickSortByYear(pi + 1, high);
        }
    }

    // Méthodes utilitaires privées
    private int partition(int low, int high) {
        int pivot = database[high].getAnneePublication();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (database[j].getAnneePublication() < pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        Book temp = database[i];
        database[i] = database[j];
        database[j] = temp;
    }

    private boolean isbnExiste(String isbn) {
        for (int i = 0; i < size; i++) {
            if (database[i].getIsbn().equals(isbn)) return true;
        }
        return false;
    }

    private void agrandirTableau() {
        database = Arrays.copyOf(database, database.length * 2);
    }

    public int getSize() { return size; }
}