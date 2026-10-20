package com.mediatech.emprunt.domaine;
public class Exemplaire {
    private final ExemplaireId id;
    private final String isbn;
    private Statut statut;
    public Exemplaire(ExemplaireId id, String isbn, Statut statut) { this.id=id; this.isbn=isbn; this.statut=statut; }
    public ExemplaireId id(){return id;}
    public String isbn(){return isbn;}
    public Statut statut(){return statut;}
    public void emprunter(){ if(statut != Statut.DISPONIBLE) throw new ErreursMetier.ExemplaireIndisponible(); statut=Statut.EMPRUNTE; }
    public void mettreDeCote(){ if(statut == Statut.DISPONIBLE) statut=Statut.RESERVE; }
    public void rendre(){ statut=Statut.DISPONIBLE; }
}
