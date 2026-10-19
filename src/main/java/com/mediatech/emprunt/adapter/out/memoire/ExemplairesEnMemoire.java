package com.mediatech.emprunt.adapter.out.memoire;

import com.mediatech.emprunt.application.port.out.Exemplaires;
import com.mediatech.emprunt.domaine.Exemplaire;
import com.mediatech.emprunt.domaine.ExemplaireId;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/** Adapter secondaire en mémoire : pour les tests ET pour démarrer sans base de données. */
public class ExemplairesEnMemoire implements Exemplaires {

    private final Map<ExemplaireId, Exemplaire> stock = new LinkedHashMap<>();

    @Override
    public Optional<Exemplaire> parId(ExemplaireId id) {
        return Optional.ofNullable(stock.get(id));
    }

    @Override
    public void enregistrer(Exemplaire exemplaire) {
        stock.put(exemplaire.id(), exemplaire);
    }
}
