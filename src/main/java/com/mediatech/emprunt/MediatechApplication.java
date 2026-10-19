package com.mediatech.emprunt;

import com.mediatech.emprunt.adapter.in.console.BorneDePret;
import com.mediatech.emprunt.adapter.out.console.NotificateurConsole;
import com.mediatech.emprunt.adapter.out.memoire.AdherentsEnMemoire;
import com.mediatech.emprunt.adapter.out.memoire.ExemplairesEnMemoire;
import com.mediatech.emprunt.application.EmprunterDocumentService;
import com.mediatech.emprunt.application.port.in.EmprunterDocument;
import com.mediatech.emprunt.domaine.Adherent;
import com.mediatech.emprunt.domaine.AdherentId;
import com.mediatech.emprunt.domaine.Categorie;
import com.mediatech.emprunt.domaine.Emprunt;
import com.mediatech.emprunt.domaine.Exemplaire;
import com.mediatech.emprunt.domaine.ExemplaireId;
import com.mediatech.emprunt.domaine.Statut;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

/** Composition root : crée, branche, lance. Rien d'autre. */
public class MediatechApplication {

    public static void main(String[] args) {
        // 1. Les adapters secondaires et l'horloge système
        AdherentsEnMemoire adherents = new AdherentsEnMemoire();
        ExemplairesEnMemoire exemplaires = new ExemplairesEnMemoire();
        NotificateurConsole notificateur = new NotificateurConsole(System.out);
        Clock horloge = Clock.systemDefaultZone();

        // 2. Le jeu de démonstration
        jeuDeDonnees(adherents, exemplaires, LocalDate.now(horloge));

        // 3. Le service, avec ses dépendances (dans l'ordre de la fiche)
        EmprunterDocument emprunterDocument =
                new EmprunterDocumentService(adherents, exemplaires, notificateur, horloge);

        // 4. La borne, branchée sur le port primaire, puis on la démarre
        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
        BorneDePret borne = new BorneDePret(emprunterDocument, clavier, System.out);

        System.out.println("📚 MédiaTech — borne de prêt. Commandes : emprunter <carte> <exemplaire>, quitter");
        borne.demarrer();
    }


    /** Des adhérents et des exemplaires de démonstration (à recopier tel quel). */
    private static void jeuDeDonnees(AdherentsEnMemoire adherents, ExemplairesEnMemoire exemplaires,
                                     LocalDate jour) {
        for (String code : List.of("E1", "E2", "E3")) {
            exemplaires.enregistrer(Exemplaire.disponible(ExemplaireId.de(code)));
        }
        for (String code : List.of("E4", "E5", "E6")) {
            exemplaires.enregistrer(new Exemplaire(ExemplaireId.de(code), Statut.EMPRUNTE));
        }
        // A1 : étudiante, aucun emprunt
        adherents.enregistrer(Adherent.inscrire(AdherentId.de("A1"), Categorie.ETUDIANT));
        // A2 : suspendu encore 5 jours
        adherents.enregistrer(new Adherent(AdherentId.de("A2"), Categorie.STANDARD,
                jour.plusDays(5), List.of()));
        // A3 : déjà 3 emprunts en cours
        adherents.enregistrer(new Adherent(AdherentId.de("A3"), Categorie.STANDARD, null, List.of(
                new Emprunt(ExemplaireId.de("E4"), jour.minusDays(3), jour.plusDays(11)),
                new Emprunt(ExemplaireId.de("E5"), jour.minusDays(3), jour.plusDays(11)),
                new Emprunt(ExemplaireId.de("E6"), jour.minusDays(18), jour.minusDays(4)))));
    }
}