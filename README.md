# Projet 1: Gestion d'une bibliothèque en langage Java

Application console de gestion de bibliothèque développée en **Java**, axée sur l'implémentation manuelle de structures de données et d'algorithmes fondamentaux.

## Fonctionnalités Clés

* **Gestion CRUD** : Contrôle complet sur la collection de livres (ISBN, Titre, Auteur, etc.).
* **Recherche & Tri** :
* **Algorithmes de Tri** : Bubble Sort (Titre), Selection Sort (Auteur), QuickSort (Année).
* **Algorithmes de Recherche** : Linéaire et Binaire (optimisée).


* **Structures de Données Personnalisées** :
* **Tableaux** : Stockage principal.
* **Listes Chaînées** : Historique des emprunts.
* **Piles (Stack)** : Suivi LIFO des activités récentes.



## Architecture du Code

Le projet adopte une structure multicouche :

* `model/` : Entité Book.
* `repository/` : Persistance des données.
* `service/` : Logique métier et algorithmes.
* `controller/` : Interface utilisateur console.
* `util/ & exception/` : Structures de données et gestion d'erreurs.

## Installation & Lancement

**Prérequis** : JDK 17+

```bash
# Cloner et accéder au projet
git clone https://github.com/clementange/projet1_bibliotheque_java.git
cd projet1_bibliotheque_java

# Compiler et lancer
javac -d out src/**/*.java
java -cp out Main

```

## Objectifs Pédagogiques

Projet réalisé pour le module **Data & Program Structure** afin de maîtriser la complexité algorithmique, la POO et la manipulation de structures de données sans bibliothèques tierces.


**Souhaitez-vous que j'ajoute une section "Exemple d'utilisation" avec une capture d'écran du menu console ?**