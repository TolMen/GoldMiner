package GoldMiner;

import java.util.Random;
import java.util.Scanner;

public class GoldMiner {

    public static void main(String[] args) {

        // Codes ANSI pour les couleurs d'affichage
        String ANSI_RESET = "\u001B[0m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_RED = "\u001B[31m";

        Random nbRandom = new Random();
        Scanner saisie = new Scanner(System.in);

        boolean reset = true;  // Variable pour redémarrer le jeu

        while (reset) {  // Boucle principale du jeu

            // Configuration du plateau par l'utilisateur
            System.out.println("Taille du plateau");
            System.out.print("Ligne : ");
            int nbLignes = saisie.nextInt();
            System.out.print("Colonne : ");
            int nbColonnes = saisie.nextInt();

            int[][] plateau = new int[nbLignes][nbColonnes];  // Matrice de jeu représentant le plateau

            // Placement aléatoire des pépites sur chaque ligne
            for (int indexLigne = 0; indexLigne < plateau.length; indexLigne++) {
                int colRandom = nbRandom.nextInt(nbColonnes);
                plateau[indexLigne][colRandom] = 1;  // 1 indique la présence d'une pépite
            }

            // Affichage des numéros de colonnes en en-tête
            System.out.print(" ");
            for (int iCol = 1; iCol <= nbColonnes; iCol++) {
                System.out.print(" " + iCol + " ");
            }
            System.out.println("");

            // Initialisation de l'affichage du plateau masqué (non creusé)
            for (int indexLigne = 0; indexLigne < plateau.length; indexLigne++) {
                System.out.print(indexLigne + 1);  // Numéro de ligne
                for (int indexColonne = 0; indexColonne < plateau[indexLigne].length; indexColonne++) {
                    System.out.print(" - ");  // Affichage par défaut des cases non creusées
                }
                System.out.println();
            }

            int nbTours = 0;  // Compteur de tours
            int nbPepite = 0;  // Compteur de pépites trouvées
            boolean test = false;  // Indicateur pour continuer le jeu

            // Boucle de jeu principale où le joueur creuse jusqu'à trouver toutes les pépites
            while (!test) {

                System.out.println("");
                System.out.println("Tours n° " + nbTours);
                nbTours++;

                boolean creuse = false;  // Indicateur pour le tour actuel
                while (!creuse) {
                    System.out.println("");
                    System.out.println("Ou voulez vous creusez ?");
                    System.out.println("Numéro de ligne puis colonne : ");
                    int ligne = saisie.nextInt() - 1;  // Coordonnée ligne
                    int colonne = saisie.nextInt() - 1;  // Coordonnée colonne

                    // Vérification des cases
                    if (ligne < 0 || ligne >= nbLignes || colonne < 0 || colonne >= nbColonnes) {
                        // Si les coordonnées sont en dehors du plateau
                        System.out.println("");
                        System.out.println(ANSI_RED + "Coordonnées invalides. Veuillez réessayer !" + ANSI_RESET);

                    } else if (plateau[ligne][colonne] == 2 || plateau[ligne][colonne] == 3) {
                        // Si la case a déjà été creusée
                        System.out.println("");
                        System.out.println(ANSI_RED + "Cette case a déjà été creusée. Veuillez en choisir une autre !" + ANSI_RESET);

                    } else if (plateau[ligne][colonne] == 0) {
                        // Si la case est vide
                        System.out.println("");
                        System.out.println("Creuse encore");
                        plateau[ligne][colonne] = 2;  // Marque la case comme creusée
                        creuse = true;

                    } else {  // Si la case contient une pépite
                        System.out.println("");
                        System.out.println(ANSI_YELLOW + "Pépite trouvé" + ANSI_RESET);
                        nbPepite++;
                        plateau[ligne][colonne] = 3;  // Marque la case avec une pépite comme creusée
                        creuse = true;
                    }
                }

                // Affichage du plateau mis à jour après chaque tour
                System.out.print(" ");
                for (int iCol = 1; iCol <= nbColonnes; iCol++) {
                    System.out.print(" " + iCol + " ");
                }
                System.out.println("");

                for (int indexLigne = 0; indexLigne < nbLignes; indexLigne++) {
                    System.out.print(indexLigne + 1);  // Numéro de ligne
                    for (int indexColonne = 0; indexColonne < plateau[indexLigne].length; indexColonne++) {
                        // Affiche l'état de chaque case
                        switch (plateau[indexLigne][indexColonne]) {
                            case 2 -> System.out.print(" o ");  // Case vide creusée
                            case 3 -> System.out.print(" x ");  // Pépite trouvée
                            default -> System.out.print(" - ");  // Case non creusée
                        }
                    }
                    System.out.println();
                }

                // Vérifie si toutes les pépites sont trouvées
                if (nbPepite == nbColonnes) {
                    System.out.println("");
                    System.out.println("Toutes les pépites ont été trouvées !");
                    test = true;
                }
            }

            // Demande au joueur s'il veut rejouer
            System.out.println("");
            System.out.println("Voulez-vous rejouer ? (Oui = 1 / Non = 2)");
            int tryAgain = saisie.nextInt();

            // Condition pour réinitialiser le jeu selon le choix du joueur
            reset = tryAgain == 1;
        }
    }
}
