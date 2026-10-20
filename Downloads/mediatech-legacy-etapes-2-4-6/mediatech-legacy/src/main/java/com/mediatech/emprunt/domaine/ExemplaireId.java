package com.mediatech.emprunt.domaine;
public record ExemplaireId(String valeur) {
    public ExemplaireId { if (valeur == null || valeur.isBlank()) throw new IllegalArgumentException("id exemplaire vide"); }
    public static ExemplaireId de(String valeur) { return new ExemplaireId(valeur); }
}
