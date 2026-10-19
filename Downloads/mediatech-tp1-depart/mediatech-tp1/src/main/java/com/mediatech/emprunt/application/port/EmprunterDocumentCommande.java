package com.mediatech.emprunt.application.port.in;

import com.mediatech.emprunt.domaine.AdherentId;
import com.mediatech.emprunt.domaine.ExemplaireId;

public record EmprunterDocumentCommande(
        AdherentId adherent,
        ExemplaireId exemplaire) {

    public EmprunterDocumentCommande {
        if (adherent == null || exemplaire == null) {
            throw new IllegalArgumentException(
                    "L'adhérent et l'exemplaire sont obligatoires");
        }
    }
}