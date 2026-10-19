
package com.mediatech.emprunt.domaine;

import com.mediatech.emprunt.domaine.exceptions.AdherentSuspendu;
import com.mediatech.emprunt.domaine.exceptions.QuotaAtteint;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Adherent {
    private static final int QUOTA_MAX = 3;

    private final AdherentId id;
    private final Categorie categorie;
    private final LocalDate suspenduJusquAu;
    private final List<Emprunt> empruntsEnCours;

    public Adherent(
            AdherentId id,
            Categorie categorie,
            LocalDate suspenduJusquAu,
            List<Emprunt> empruntsEnCours) {
        this.id = Objects.requireNonNull(id);
        this.categorie = Objects.requireNonNull(categorie);
        this.suspenduJusquAu = suspenduJusquAu;
        this.empruntsEnCours = new ArrayList<>(
                Objects.requireNonNull(empruntsEnCours));
    }

    public static Adherent inscrire(AdherentId id, Categorie categorie) {
        return new Adherent(id, categorie, null, List.of());
    }

    public Emprunt emprunter(Exemplaire exemplaire, LocalDate aujourdhui) {
        Objects.requireNonNull(exemplaire);
        Objects.requireNonNull(aujourdhui);

        if (empruntsEnCours.size() >= QUOTA_MAX) {
            throw new QuotaAtteint(id, QUOTA_MAX);
        }

        if (estSuspenduLe(aujourdhui)) {
            throw new AdherentSuspendu(id, suspenduJusquAu);
        }

        if (exemplaire.statut() != Statut.DISPONIBLE) {
            throw new com.mediatech.emprunt.domaine.exceptions
                    .ExemplaireIndisponible(exemplaire.id(), exemplaire.statut());
        }

        LocalDate retour = aujourdhui.plusDays(
                categorie.dureeEmpruntEnJours());

        Emprunt emprunt = new Emprunt(
                exemplaire.id(), aujourdhui, retour);

        exemplaire.marquerEmprunte();
        empruntsEnCours.add(emprunt);

        return emprunt;
    }

    public boolean estSuspenduLe(LocalDate jour) {
        Objects.requireNonNull(jour);
        return suspenduJusquAu != null
                && jour.isBefore(suspenduJusquAu);
    }

    public List<Emprunt> empruntsEnCours() {
        return Collections.unmodifiableList(empruntsEnCours);
    }

    public AdherentId id() {
        return id;
    }

    public Categorie categorie() {
        return categorie;
    }

    public Optional<LocalDate> suspenduJusquAu() {
        return Optional.ofNullable(suspenduJusquAu);
    }
}
