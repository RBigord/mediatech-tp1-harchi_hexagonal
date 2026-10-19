package com.mediatech.emprunt.adapter.out.memoire;
 
import com.mediatech.emprunt.application.port.out.Adherents;
import com.mediatech.emprunt.domaine.Adherent;
import com.mediatech.emprunt.domaine.AdherentId;
 
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
 
/** Adapter secondaire en mémoire : pour les tests ET pour démarrer sans base de données. */
public class AdherentsEnMemoire implements Adherents {
 
    private final Map<AdherentId, Adherent> stock = new LinkedHashMap<>();
 
    @Override
    public Optional<Adherent> parId(AdherentId id) {
        return Optional.ofNullable(stock.get(id));
    }
 
    @Override
    public void enregistrer(Adherent adherent) {
        stock.put(adherent.id(), adherent);
    }
}
