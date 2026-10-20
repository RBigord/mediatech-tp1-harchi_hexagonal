package com.mediatech.emprunt.domaine;
public final class ErreursMetier {
    private ErreursMetier() {}
    public static class AdherentSuspendu extends RuntimeException { public AdherentSuspendu(){super("Adhérent suspendu");} }
    public static class QuotaAtteint extends RuntimeException { public QuotaAtteint(){super("Maximum 3 emprunts");} }
    public static class ExemplaireIndisponible extends RuntimeException { public ExemplaireIndisponible(){super("Exemplaire indisponible");} }
    public static class AdherentInconnu extends RuntimeException { public AdherentInconnu(){super("Adhérent inconnu");} }
    public static class ExemplaireInconnu extends RuntimeException { public ExemplaireInconnu(){super("Exemplaire inconnu");} }
    public static class ExemplaireNonEmprunte extends RuntimeException { public ExemplaireNonEmprunte(){super("Exemplaire non emprunté");} }
}
