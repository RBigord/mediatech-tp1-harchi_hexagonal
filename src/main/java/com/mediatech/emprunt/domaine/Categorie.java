
package com.mediatech.emprunt.domaine;

public enum Categorie {
    ETUDIANT,
    STANDARD;

    public int dureeEmpruntEnJours() {
        return switch (this) {
            case ETUDIANT -> 21;
            case STANDARD -> 14;
        };
    }
}
