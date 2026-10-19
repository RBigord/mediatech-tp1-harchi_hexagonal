package com.mediatech.emprunt.application;

import com.mediatech.emprunt.domaine.ExemplaireId;
import com.mediatech.emprunt.domaine.exceptions.ErreurMetier;

public class ExemplaireInconnu extends ErreurMetier {

    public ExemplaireInconnu(ExemplaireId id) {
        super("Aucun exemplaire avec le code-barres " + id);
    }
}
