
package com.mediatech.emprunt.domaine;

import java.time.LocalDate;
import java.util.Objects;

public record Emprunt(
        ExemplaireId exemplaire,
        LocalDate dateEmprunt,
        LocalDate retourPrevu
) {
    public Emprunt {
        Objects.requireNonNull(exemplaire, "L'exemplaire est obligatoire");
        Objects.requireNonNull(dateEmprunt, "La date d'emprunt est obligatoire");
        Objects.requireNonNull(retourPrevu, "La date de retour est obligatoire");

        if (retourPrevu.isBefore(dateEmprunt)) {
            throw new IllegalArgumentException(
                    "La date de retour ne peut pas précéder la date d'emprunt"
            );
        }
    }
}
