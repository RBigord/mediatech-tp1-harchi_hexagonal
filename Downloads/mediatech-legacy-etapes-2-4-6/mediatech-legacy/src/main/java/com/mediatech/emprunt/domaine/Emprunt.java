package com.mediatech.emprunt.domaine;
import java.time.LocalDate;
public record Emprunt(ExemplaireId exemplaireId, LocalDate dateEmprunt, LocalDate retourPrevu, LocalDate dateRetour) {
    public Emprunt(ExemplaireId exemplaireId, LocalDate dateEmprunt, LocalDate retourPrevu) {
        this(exemplaireId, dateEmprunt, retourPrevu, null);
    }
    public boolean enCours() { return dateRetour == null; }
    public Emprunt rendre(LocalDate date) { return new Emprunt(exemplaireId, dateEmprunt, retourPrevu, date); }
}
