
package com.mediatech.emprunt.domaine.exceptions;

import com.mediatech.emprunt.domaine.ExemplaireId;
import com.mediatech.emprunt.domaine.Statut;

public class ExemplaireIndisponible extends ErreurMetier {
    public ExemplaireIndisponible(ExemplaireId id, Statut statut) {
        super("L'exemplaire " + id
                + " n'est pas disponible (statut : " + statut + ")");
    }
}
