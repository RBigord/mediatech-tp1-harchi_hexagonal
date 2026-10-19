
package com.mediatech.emprunt.domaine.exceptions;

import com.mediatech.emprunt.domaine.AdherentId;

public class QuotaAtteint extends ErreurMetier {
    public QuotaAtteint(AdherentId id, int quota) {
        super("L'adhérent " + id + " a déjà " + quota
                + " emprunts en cours");
    }
}
