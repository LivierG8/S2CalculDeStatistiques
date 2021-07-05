package com.univ.l3.s2_calculdestatistiques;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistiques {
    /**
     * Initialisation de la liste d'informationq.
     */
    public static final List<Statistiques.Statistique> ITEMS = new ArrayList<Statistiques.Statistique>();

    /**
     * Initialisation de la cartographie des informations par ID.
     */
    public static final Map<String, Statistiques.Statistique> ITEM_MAP = new HashMap<String, Statistiques.Statistique>();

    private static final int COUNT = 25;

    public static String valTailleChaine;

    public static String valNbMots;

    public static String valMotPlusGrand;

    public static String valMotPlusCourt;

    public static String valMotPlusFrequent;

    public static String valNbMiniOccurences;

    static {
        addItem(createStatItem(0, "Taille de la chaîne", valTailleChaine));
        addItem(createStatItem(1, "Nombre de mots", valNbMots));
        addItem(createStatItem(2, "Mot le plus grand", valMotPlusGrand));
        addItem(createStatItem(3, "Mot le pluscourt", valMotPlusCourt));
        addItem(createStatItem(4, "Mot le plus fréquent", valMotPlusFrequent));
        addItem(createStatItem(5, "Nombre minimum d'occurences d'un mot", valNbMiniOccurences));
    }

    private static void addItem(Statistiques.Statistique item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Statistiques.Statistique createStatItem(int position, String labelStat, String valeurStat) {
        return new Statistiques.Statistique(String.valueOf(position), labelStat, valeurStat);
    }

    /**
     * Objet d'information.
     */
    public static class Statistique {
        public final String id;
        public final String intitule;
        public final String calcul;

        public Statistique(String id, String intitule, String calcul) {
            this.id = id;
            this.intitule = intitule;
            this.calcul = calcul;
        }

        @Override
        public String toString() {
            return intitule + " : " + calcul;
        }
    }
}
