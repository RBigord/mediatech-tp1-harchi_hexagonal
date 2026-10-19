package com.mediatech.emprunt.domaine;
public record AdherentId(String valeur) {
    public AdherentId {
        if (valeur == null || valeur.isBlank()) {
            throw new IllegalArgumentException(
                    "Un identifiant d'adhérent ne peut pas être vide"
            );
        }
        valeur = valeur.strip();
    }

    public static AdherentId de(String valeur) {
        return new AdherentId(valeur);
    }

    @Override
    public String toString() {
        return valeur;
    }
}

