package com.mediatech.emprunt.application;

import com.mediatech.emprunt.domaine.AdherentId;
import com.mediatech.emprunt.domaine.exceptions.ErreurMetier;

public class AdherentInconnu extends ErreurMetier {
    public AdherentInconnu(AdherentId id) {
        super("Aucun adhérent avec la carte " + id);
    }
}