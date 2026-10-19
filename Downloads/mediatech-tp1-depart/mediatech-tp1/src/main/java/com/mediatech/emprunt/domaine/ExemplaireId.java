package com.mediatech.emprunt.domaine;

public record ExemplaireId(String valeur) {
    public ExemplaireId {
        if (valeur == null || valeur.isBlank()) {
            throw new IllegalArgumentException(
                    "Un identifiant d'exemplaire ne peut pas être vide"
            );
        }
        valeur = valeur.strip();
    }

    public static ExemplaireId de(String valeur) {
        return new ExemplaireId(valeur);
    }

    @Override
    public String toString() {
        return valeur;
    }
}

