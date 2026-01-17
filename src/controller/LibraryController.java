package controller;

import model.Book;
import service.LibraryService;
import java.util.List;
import java.util.Scanner;

public class LibraryController {

    private final LibraryService service;
    private final Scanner scanner;

    public LibraryController(LibraryService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void ajouterLivre() {
        System.out.print("Titre : "); String titre = scanner.nextLine();
        System.out.print("Auteur : "); String auteur = scanner.nextLine();
        System.out.print("ISBN : "); String isbn = scanner.nextLine();
        System.out.print("Année : "); int annee = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Genre : "); String genre = scanner.nextLine();

        service.ajouterLivre(new Book(titre, auteur, isbn, annee, genre));
        System.out.println("Livre ajouté !");
    }

    public void rechercherLivre() {
        System.out.println("Type de recherche : 1. Linéaire (Titre) | 2. Binaire (Titre - Tri auto)");
        int type = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Entrez le titre : ");
        String titre = scanner.nextLine();

        Book b = service.rechercherParTitre(titre, (type == 2));
        System.out.println(b != null ? "Trouvé : " + b : "Aucun livre trouvé.");
    }

    public void trierLivres() {
        System.out.println("Algorithme : 1. Bulle (Titre) | 2. Sélection (Auteur) | 3. QuickSort (Année)");
        int algoNum = scanner.nextInt();
        scanner.nextLine();

        String algo = switch (algoNum) {
            case 1 -> "bulle";
            case 2 -> "selection";
            case 3 -> "rapide";
            default -> "";
        };

        service.trierLivres("", algo);
        System.out.println("Tri effectué. Utilisez l'option 2 pour voir le résultat.");
    }

    public void gererEmprunt() {
        System.out.println("1. Nouvel emprunt | 2. Voir historique d'un livre");
        int sousChoix = scanner.nextInt();
        System.out.print("ID du livre : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (sousChoix == 1) {
            System.out.print("Nom de l'emprunteur : ");
            String nom = scanner.nextLine();
            service.emprunterLivre(id, nom);
            System.out.println("Emprunt enregistré.");
        } else {
            List<String> hist = service.voirHistoriqueLivre(id);
            System.out.println("Historique (Linked List) : " + hist);
        }
    }

    public void afficherActivites() {
        List<String> acts = service.voirActivitesRecentes();
        if (acts.isEmpty()) {
            System.out.println("Aucune activité enregistrée.");
        } else {
            System.out.println("Dernières actions (LIFO) :");
            for (int i = acts.size() - 1; i >= 0; i--) {
                System.out.println("-> " + acts.get(i));
            }
        }
    }

    public void afficherTousLesLivres() {
        service.listerLivres().forEach(System.out::println);
    }

    public void supprimerLivre() {
        System.out.print("ID à supprimer : ");
        int id = scanner.nextInt();
        service.supprimerLivre(id);
        System.out.println("Livre supprimé.");
    }
}