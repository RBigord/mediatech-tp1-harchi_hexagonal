package com.mediatech.emprunt.application.port.out;

import com.mediatech.emprunt.domaine.Exemplaire;
import com.mediatech.emprunt.domaine.ExemplaireId;

import java.util.Optional;

public interface Exemplaires {
    Optional<Exemplaire> parId(ExemplaireId id);

    void enregistrer(Exemplaire exemplaire);
}
