
package com.mediatech.emprunt.domaine;

import com.mediatech.emprunt.domaine.exceptions.ExemplaireIndisponible;
import java.util.Objects;

public class Exemplaire {
    private final ExemplaireId id;
    private Statut statut;

    public Exemplaire(ExemplaireId id, Statut statut) {
        this.id = Objects.requireNonNull(id);
        this.statut = Objects.requireNonNull(statut);
    }

    public static Exemplaire disponible(ExemplaireId id) {
        return new Exemplaire(id, Statut.DISPONIBLE);
    }

    public ExemplaireId id() {
        return id;
    }

    public Statut statut() {
        return statut;
    }

    void marquerEmprunte() {
        if (statut != Statut.DISPONIBLE) {
            throw new ExemplaireIndisponible(id, statut);
        }
        statut = Statut.EMPRUNTE;
    }
}
