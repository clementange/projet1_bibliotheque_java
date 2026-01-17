import controller.LibraryController;
import repository.BookArrayRepository;
import service.LibraryService;
import util.ActivityStack;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Initialisation des composants
        ActivityStack activityStack = new ActivityStack();
        BookArrayRepository repository = new BookArrayRepository();
        LibraryService service = new LibraryService(repository, activityStack);

        // Récupération de l'entrée de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        int choix = -1;

        //Initialisation du controller
        LibraryController controller = new LibraryController(service, scanner);

        while (choix != 0) {
            afficherMenu();
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choix) {
                    case 1 :
                        controller.ajouterLivre();
                        break;
                    case 2 :
                        controller.afficherTousLesLivres();
                        break;
                    case 3 :
                        controller.rechercherLivre();
                        break;
                    case 4 :
                        controller.trierLivres();
                        break;
                    case 5 :
                        controller.gererEmprunt();
                        break;
                    case 6 :
                        controller.afficherActivites();
                        break;
                    case 7 :
                        controller.supprimerLivre();
                        break;
                    case 0 :
                        System.out.println("Fermeture du système...");
                        break;
                    default :
                        System.out.println("Choix invalide !");
                }
            } catch (Exception e) {
                System.err.println("Erreur : " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println("\nSYSTEME DE GESTION DE BIBLIOTHÈQUE");
        System.out.println("1. Ajouter un livre");
        System.out.println("2. Lister tous les livres");
        System.out.println("3. Rechercher un livre (Linéaire/Binaire)");
        System.out.println("4. Trier les livres (Bulle/Sélection/Quick)");
        System.out.println("5. Emprunter un livre / Voir historique");
        System.out.println("6. Voir les activités récentes (Stack)");
        System.out.println("7. Supprimer un livre");
        System.out.println("0. Quitter");
    }

}