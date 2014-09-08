package ca.qc.bdeb.inf.p56.singleton.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * Inventaire de produits selon le pattern Singleton.
 *
 * @author Stéphane Lévesque
 */
public final class Inventaire {
    /**
     * Instance unique.
     */
    private static final Inventaire instance = new Inventaire();
    /**
     * Produits en stock (nom,quantité)
     */
    private Map<String, Integer> inventaire = new HashMap<>();

    /**
     * @throws IllegalStateException Si on essaie d'instancier le singleton par réflexion.
     */
    private Inventaire() {
        if (Inventaire.instance != null) {
            throw new IllegalStateException("N'essayez pas d'instancier cette classe par réflection.");
        }
    }

    public static Inventaire getInstance() {
        return Inventaire.instance;
    }

    /**
     * Vide les stocks du produit spécifié.
     *
     * @param produit Le nom du produit.
     */
    public synchronized void remettreProduitAZero(String produit) {
        this.inventaire.put(produit, 0);
    }

    public int getQuantite(String produit) {
        return this.inventaire.get(produit);
    }

    /**
     * Augmente le stock d'un produit de 1.
     *
     * @param produit Le nom du produit.
     */
    public synchronized void ajouterProduit(String produit) {
        Integer quantite = 1;
        if (this.inventaire.containsKey(produit)) {
            quantite = this.inventaire.get(produit) + 1;
        }
        this.inventaire.put(produit, quantite);
    }
}