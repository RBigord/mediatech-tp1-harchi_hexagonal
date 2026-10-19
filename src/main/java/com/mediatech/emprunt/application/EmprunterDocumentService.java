
package com.mediatech.emprunt.application;

import com.mediatech.emprunt.application.port.in.EmprunterDocument;
import com.mediatech.emprunt.application.port.in.EmprunterDocumentCommande;
import com.mediatech.emprunt.application.port.out.Adherents;
import com.mediatech.emprunt.application.port.out.Exemplaires;
import com.mediatech.emprunt.application.port.out.Notificateur;
import com.mediatech.emprunt.domaine.Adherent;
import com.mediatech.emprunt.domaine.Emprunt;
import com.mediatech.emprunt.domaine.Exemplaire;

import java.time.Clock;
import java.time.LocalDate;

public class EmprunterDocumentService implements EmprunterDocument {

    private final Adherents adherents;
    private final Exemplaires exemplaires;
    private final Notificateur notificateur;
    private final Clock horloge;

    public EmprunterDocumentService(
            Adherents adherents,
            Exemplaires exemplaires,
            Notificateur notificateur,
            Clock horloge) {
        this.adherents = adherents;
        this.exemplaires = exemplaires;
        this.notificateur = notificateur;
        this.horloge = horloge;
    }

    @Override
    public Emprunt executer(EmprunterDocumentCommande commande) {

        // 1. Retrouver l'adhérent
        Adherent adherent = adherents.parId(commande.adherent())
                .orElseThrow(() -> new AdherentInconnu(commande.adherent()));

        // 2. Retrouver l'exemplaire
        Exemplaire exemplaire = exemplaires.parId(commande.exemplaire())
                .orElseThrow(() -> new ExemplaireInconnu(commande.exemplaire()));

        // 3. Obtenir la date du jour depuis l'horloge
        LocalDate aujourdhui = LocalDate.now(horloge);

        // 4. Demander au domaine d'effectuer l'emprunt
        Emprunt emprunt = adherent.emprunter(exemplaire, aujourdhui);

        // 5. Enregistrer les modifications
        adherents.enregistrer(adherent);
        exemplaires.enregistrer(exemplaire);

        // 6. Confirmer l'emprunt
        notificateur.confirmerEmprunt(adherent, emprunt);

        return emprunt;
    }
}
