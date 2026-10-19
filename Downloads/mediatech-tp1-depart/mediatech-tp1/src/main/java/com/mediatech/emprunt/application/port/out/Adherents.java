package com.mediatech.emprunt.application.port.out;

import com.mediatech.emprunt.domaine.Adherent;
import com.mediatech.emprunt.domaine.AdherentId;

import java.util.Optional;

public interface Adherents {
    Optional<Adherent> parId(AdherentId id);
    void enregistrer(Adherent adherent);
}