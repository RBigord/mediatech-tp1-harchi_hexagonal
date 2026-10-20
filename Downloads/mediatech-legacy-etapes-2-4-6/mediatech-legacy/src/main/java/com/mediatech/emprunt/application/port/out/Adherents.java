package com.mediatech.emprunt.application.port.out;
import com.mediatech.emprunt.domaine.*;
import java.util.Optional;
public interface Adherents {
 Optional<Adherent> parId(AdherentId id);
 void enregistrer(Adherent adherent);
 Optional<Adherent> empruntantActuellement(ExemplaireId exemplaire);
 String prenom(AdherentId id);
 String email(AdherentId id);
}
