package com.mediatech.emprunt.domaine;
public record AdherentId(String valeur) {
    public AdherentId { if (valeur == null || valeur.isBlank()) throw new IllegalArgumentException("id adhérent vide"); }
    public static AdherentId de(String valeur) { return new AdherentId(valeur); }
}
